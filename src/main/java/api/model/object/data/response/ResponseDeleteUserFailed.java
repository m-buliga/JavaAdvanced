package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDeleteUserFailed {

    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }
}
