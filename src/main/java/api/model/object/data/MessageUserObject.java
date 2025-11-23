package api.model.object.data;

import api.model.object.data.response.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testng.Assert;

import java.util.List;

@NoArgsConstructor
@Data
public class MessageUserObject implements ResponseNotNull {

    @JsonProperty("id")
    private String id;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("email")
    private String email;

    @JsonProperty("totp_enabled")
    private String totpEnabled;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("address")
    private List<AddressObject> address;

    @JsonProperty("enabled")
    private String enabled;

    @JsonProperty("role")
    private String role;

    @JsonProperty("failed_login_attempts")
    private int failedLoginAttempts;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(id);
        Assert.assertNotNull(firstName);
        Assert.assertNotNull(lastName);
        Assert.assertNotNull(email);
        Assert.assertNotNull(totpEnabled);
        Assert.assertNotNull(createdAt);
        Assert.assertNotNull(enabled);
        Assert.assertNotNull(role);

    }
}
