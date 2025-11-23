package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

@Getter
public class ResponseUpdateContactMessageSuccess implements ResponseNotNull{

    @JsonProperty("success")
    private Boolean success;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(success);
    }
}
