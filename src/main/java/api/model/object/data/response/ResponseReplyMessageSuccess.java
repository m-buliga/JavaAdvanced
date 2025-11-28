package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseReplyMessageSuccess implements ResponseNotNull{

    @JsonProperty("message")
    private String message;

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(message);
        Assert.assertNotNull(id);
        Assert.assertNotNull(createdAt);
    }
}
