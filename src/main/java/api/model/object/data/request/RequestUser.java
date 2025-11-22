package api.model.object.data.request;

import api.model.object.data.RequestPreparation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;

public class RequestUser implements RequestPreparation {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public RequestUser(HashMap<String, String> testData) {
        prepareObject(testData);
    }


    @Override
    public void prepareObject(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "first_name" : setFirstName(testData.get(key));
                    break;
                case "last_name" : setLastName(testData.get(key));
                    break;
                case "email" : setEmail(testData.get(key));
                    break;
                case "password" : setPassword(testData.get(key));
                    break;
            }
        }
        adjustObjectVariable();
    }


    public void adjustObjectVariable() {
        if (email != null && email.contains("${timestamp}")) {
            email = email.replace("${timestamp}", String.valueOf(System.currentTimeMillis()));
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
