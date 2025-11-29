package ui.object.data.ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonObject {
    public List<String> getValueList(String value) {
        String[] splitValue = value.split(",");
        return Arrays.stream(splitValue).collect(Collectors.toList());
    }
}
