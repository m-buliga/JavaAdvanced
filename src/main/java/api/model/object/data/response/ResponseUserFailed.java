package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUserFailed {

    @JsonProperty("message")
    private String message;

    @JsonProperty("error")
    private String error;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
