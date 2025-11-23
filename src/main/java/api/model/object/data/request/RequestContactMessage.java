package api.model.object.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
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

}
