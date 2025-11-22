package api.model.object.data.request;

import api.model.object.data.RequestPreparation;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class RequestContactMessage implements RequestPreparation {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("message")
    private String message;

    public RequestContactMessage(HashMap<String, String> testData) {
        prepareObject(testData);
    }


    @Override
    public void prepareObject(HashMap<String, String> testData) {

        for (String key : testData.keySet()) {
            switch (key) {
                case "name":
                    setName(testData.get(key));
                    break;
                case "email":
                    setEmail(testData.get(key));
                    break;
                case "subject":
                    setSubject(testData.get(key));
                    break;
                case "message":
                    setMessage(testData.get(key));
                    break;
            }
        }

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
