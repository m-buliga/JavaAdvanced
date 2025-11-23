package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseLoginTokenSuccess implements ResponseNotNull {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(accessToken);
        Assert.assertNotNull(tokenType);
        Assert.assertNotNull(expiresIn);
    }
}
