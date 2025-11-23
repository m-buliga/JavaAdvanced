package api.model.object.data.response;

import api.model.object.data.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseUserFailed implements ResponseNotNull {

    @JsonProperty("message")
    private String message;

    @JsonProperty("error")
    private String error;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(error);
    }
}
