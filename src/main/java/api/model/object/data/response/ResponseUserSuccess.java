package api.model.object.data.response;

import api.model.object.data.AddressObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ResponseUserSuccess {

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getProvider() {
        return provider;
    }

    public String getPhone() {
        return phone;
    }

    public String getTotpEnabled() {
        return totpEnabled;
    }

    public String getDob() {
        return dob;
    }

    public List<AddressObject> getAddress() {
        return address;
    }
}
