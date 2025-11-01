package core.utils.xml.xmlFile.xmlNode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "configuration")
public class Configuration {

    @XmlElement(name = "backendConfig")
    public BackendConfig backendConfig;
}
