package ui.object.data.ui;

import lombok.Data;

import java.util.HashMap;

@Data
public class LoginObject extends CommonObject {
    private String email;
    private String password;

    public LoginObject(HashMap<String, String> testData) {
        populateData(testData);
    }


    public void populateData(HashMap<String, String> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "email":
                    setEmail(testData.get(key));
                    break;
                case "password":
                    setPassword(testData.get(key));
                    break;
            }
        }
    }

}
