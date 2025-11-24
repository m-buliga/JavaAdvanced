package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;

@Getter
public class ResponseAttachFileMessageFailed implements ResponseNotNull{

    @JsonProperty("errors")
    private List<String> errors;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(errors);
    }
}
