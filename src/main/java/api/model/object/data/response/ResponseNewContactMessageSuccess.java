package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseNewContactMessageSuccess implements ResponseNotNull {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("message")
    private String message;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String created_at;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(name);
        Assert.assertNotNull(email);
        Assert.assertNotNull(subject);
        Assert.assertNotNull(message);
        Assert.assertNotNull(userId);
        Assert.assertNotNull(status);
        Assert.assertNotNull(id);
        Assert.assertNotNull(created_at);

    }
}
