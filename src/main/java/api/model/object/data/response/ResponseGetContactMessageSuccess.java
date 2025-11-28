package api.model.object.data.response;

import api.model.object.data.MessageReplyObject;
import api.model.object.data.MessageUserObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;


@Getter
public class ResponseGetContactMessageSuccess implements ResponseNotNull {

    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("user")
    private MessageUserObject user;

    @JsonProperty("replies")
    private List<MessageReplyObject> replies;


    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(id);
        Assert.assertNotNull(userId);
        Assert.assertNotNull(name);
        Assert.assertNotNull(email);
        Assert.assertNotNull(subject);
        Assert.assertNotNull(message);
        Assert.assertNotNull(status);
        Assert.assertNotNull(createdAt);
        Assert.assertNotNull(user);
        Assert.assertNotNull(replies);

    }
}
