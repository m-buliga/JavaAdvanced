package api.model.object.data.response;

import api.model.object.data.AddressObject;
import api.model.object.data.MessageDataObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseContactMessageSuccess {

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


    public int getCurrent_page() {
        return current_page;
    }

    public List<MessageDataObject> getData() {
        return data;
    }

    public int getFrom() {
        return from;
    }

    public int getLast_page() {
        return last_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTo() {
        return to;
    }

    public int getTotal() {
        return total;
    }
}
