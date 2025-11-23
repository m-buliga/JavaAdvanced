package api.model.object.data.response;

import api.model.object.data.AddressObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;

@Getter
public class ResponseUserSuccess implements ResponseNotNull {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("totp_enabled")
    private String totpEnabled;

    @JsonProperty("dob")
    private String dob;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("address")
    private List<AddressObject> address;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(firstName);
        Assert.assertNotNull(lastName);
        Assert.assertNotNull(email);
        Assert.assertNotNull(id);
        Assert.assertNotNull(createdAt);
    }
}
