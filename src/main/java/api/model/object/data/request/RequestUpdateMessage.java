package api.model.object.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestUpdateMessage {
    @JsonProperty("status")
    private String status;
}
