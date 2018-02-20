package com.code.response;

import com.code.model.ResultCode;
import com.code.model.ResultReason;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponse  implements Serializable {

    private static final long serialVersionUID = -59785844673256919L;

    private ResultCode resultCode;
    private ResultReason resultReason;

    public GlobalResponse() {
    }

    public GlobalResponse(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public GlobalResponse(ResultCode resultCode, ResultReason resultReason) {
        this.resultCode = resultCode;
        this.resultReason = resultReason;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultReason getResultReason() {
        return resultReason;
    }

    public void setResultReason(ResultReason resultReason) {
        this.resultReason = resultReason;
    }
}
