package api.model.object.data.request;

import java.util.HashMap;

public interface RequestPreparation {
    // this interface's purpose is to serialize a specific request body

    void prepareObject(HashMap<String, String> testData);
}
