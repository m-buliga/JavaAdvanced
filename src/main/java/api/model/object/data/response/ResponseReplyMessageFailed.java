package api.model.object.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.testng.Assert;


@Getter
public class ResponseReplyMessageFailed implements ResponseNotNull {

        @JsonProperty("message")
        private String message;

        @Override
        public void validateNotNullFields() {
            Assert.assertNotNull(message);
        }

}
