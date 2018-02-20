import com.code.config.Application;
import com.code.model.Message;
import com.code.model.ResultCode;
import com.code.model.TimeLineMessages;
import com.code.model.User;
import com.code.request.CreateMessageRequest;
import com.code.request.CreateNewUserRequest;
import com.code.response.GlobalResponse;
import com.code.service.IMessageService;
import com.code.service.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class MessageServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    @Before
    public void setUp() throws Exception {
        userService.createNewUser(new CreateNewUserRequest("Mike"));
        userService.createNewUser(new CreateNewUserRequest("John"));
        userService.createNewUser(new CreateNewUserRequest("Kate"));
        userService.createNewUser(new CreateNewUserRequest("Michael"));
    }

    @Test
    public void addMessage() {
        GlobalResponse response = messageService.createMessage(
                new CreateMessageRequest("Simple message", "Mike"));
        List<Message> messageList = messageService.getUserMessages("Mike");

        Assert.assertTrue(response.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(!CollectionUtils.isEmpty(messageList));
        Assert.assertTrue(messageList.size() == 1);
        Assert.assertTrue(messageList.get(0).getMessage().equals("Simple message"));
    }

    @Test
    public void timelineTest() throws InterruptedException {
        GlobalResponse response = userService.followUser("Michael", "Kate");
        GlobalResponse response2 = userService.followUser("Michael", "John");

        GlobalResponse response3 = messageService.createMessage(
                new CreateMessageRequest("Simple message1", "Kate"));
        Thread.sleep(10);
        GlobalResponse response4 = messageService.createMessage(
                new CreateMessageRequest("Simple message2", "John"));
        Thread.sleep(10);
        GlobalResponse response5 = messageService.createMessage(
                new CreateMessageRequest("Simple message3", "Kate"));
        Thread.sleep(10);
        GlobalResponse response6 = messageService.createMessage(
                new CreateMessageRequest("Simple message4", "John"));

        List<TimeLineMessages> timeLineMessagesList = messageService.getFollowerMessages("Michael");

        Assert.assertTrue(response.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(response2.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(response3.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(response4.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(response5.getResultCode().equals(ResultCode.OK));
        Assert.assertTrue(response6.getResultCode().equals(ResultCode.OK));

        Assert.assertTrue(!CollectionUtils.isEmpty(timeLineMessagesList));
        Assert.assertTrue(timeLineMessagesList.size() == 4);
        Assert.assertTrue(timeLineMessagesList.get(0).getUser().equals("John"));
        Assert.assertTrue(timeLineMessagesList.get(1).getUser().equals("Kate"));
        Assert.assertTrue(timeLineMessagesList.get(2).getUser().equals("John"));
        Assert.assertTrue(timeLineMessagesList.get(3).getUser().equals("Kate"));



    }


}
