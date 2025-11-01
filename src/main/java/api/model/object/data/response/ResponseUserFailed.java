package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUserFailed {

    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
