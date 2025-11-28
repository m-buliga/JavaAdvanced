package api.model.object.data;

import api.model.object.data.response.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testng.Assert;


@NoArgsConstructor
@Data
public class MessageReplyObject implements ResponseNotNull {

    @JsonProperty("id")
    private String id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("user")
    private MessageUserObject user;


    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(id);
        Assert.assertNotNull(message);
        Assert.assertNotNull(createdAt);
        Assert.assertNotNull(user);

    }
}
