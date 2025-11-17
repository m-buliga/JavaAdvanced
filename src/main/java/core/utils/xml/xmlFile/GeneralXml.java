package core.utils.xml.xmlFile;

import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class GeneralXml {
    // clasa care reprezinta maparea fisierului XML cu constructia din xmlNode
    @SneakyThrows(JAXBException.class)
    public static <T> T createConfig(Class<T> klass){
        JAXBContext jaxbContext = JAXBContext.newInstance(klass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return klass.cast(unmarshaller.unmarshal(new File("src/test/resources/atfConfig.xml")));
    }

}
