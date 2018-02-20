import com.code.config.Application;
import com.code.model.Message;
import com.code.model.ResultCode;
import com.code.model.User;
import com.code.request.CreateNewUserRequest;
import com.code.response.GlobalResponse;
import com.code.service.IUserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Before
    public void setUp() throws Exception {
        userService.createNewUser(new CreateNewUserRequest("Mike"));
        userService.createNewUser(new CreateNewUserRequest("John"));
        userService.createNewUser(new CreateNewUserRequest("Kate"));
        userService.createNewUser(new CreateNewUserRequest("Michael"));
    }

    @Test
    public void addNewMessageNewUser() {
        List<User> userList = userService.getListOfUsers();

        Assert.assertTrue(!CollectionUtils.isEmpty(userList));
        Assert.assertTrue(userList.size() == 4);
        Assert.assertTrue(userList.get(0).getName().equals("Mike"));
    }

    @Test
    public void createNewUser() {
        GlobalResponse response = userService.createNewUser(new CreateNewUserRequest("Karl"));
        List<User> userList = userService.getListOfUsers();

        Assert.assertTrue(response.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(!CollectionUtils.isEmpty(userList));
        Assert.assertTrue(userList.size() == 5);
        Assert.assertTrue(userList.get(userList.size() - 1).getName().equals("Karl"));
    }

    @Test
    public void createExistedUser() {
        GlobalResponse response = userService.createNewUser(new CreateNewUserRequest("Michael"));
        List<User> userList = userService.getListOfUsers();

        Assert.assertTrue(response.getResultCode().equals(ResultCode.FALSE));
        Assert.assertTrue(!CollectionUtils.isEmpty(userList));
        Assert.assertTrue(userList.size() == 4);
        Assert.assertTrue(userList.get(userList.size() - 1).getName().equals("Michael"));
    }


    @Test
    public void userFollowers() {
        User user = userService.getUser("Michael");
        GlobalResponse response = userService.followUser("Michael", "Kate");
        GlobalResponse response2 = userService.followUser("Michael", "John");

        Assert.assertTrue(response.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(response2.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(!CollectionUtils.isEmpty(user.getFollowers()));
        Assert.assertTrue( user.getFollowers().size() == 2);
        Assert.assertTrue( user.getFollowers().get(0).getName().equals("Kate"));
    }

    @Test
    public void userUnFollowers() {
        User user = userService.getUser("Michael");
        GlobalResponse response = userService.followUser("Michael", "Kate");
        GlobalResponse response2 = userService.followUser("Michael", "John");

        GlobalResponse response3 = userService.unFollowUser("Michael", "Kate");
        GlobalResponse response4 = userService.unFollowUser("Michael", "John");

        Assert.assertTrue(response3.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(response4.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(CollectionUtils.isEmpty(user.getFollowers()));
        Assert.assertTrue( user.getFollowers().size() == 0);
    }

}
