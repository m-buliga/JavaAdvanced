package api.model.object.data.response;

import api.model.object.data.MessageDataObject;
import api.model.object.data.ResponseNotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;

import java.util.List;

@Getter
public class ResponseGetContactMessageSuccess implements ResponseNotNull {

    @JsonProperty("current_page")
    private int current_page;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("data")
    private List<MessageDataObject> data;

    @JsonProperty("from")
    private int from;

    @JsonProperty("last_page")
    private int last_page;

    @JsonProperty("per_page")
    private int per_page;

    @JsonProperty("to")
    private int to;

    @JsonProperty("total")
    private int total;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(current_page);
        Assert.assertNotNull(from);
        Assert.assertNotNull(last_page);
        Assert.assertNotNull(per_page);
        Assert.assertNotNull(to);
        Assert.assertNotNull(total);

        for (MessageDataObject messageDataObject : data) {
            messageDataObject.validateNotNullFields();
        }

    }
}
