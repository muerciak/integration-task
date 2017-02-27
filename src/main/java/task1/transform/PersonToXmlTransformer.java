package task1.transform;

import org.springframework.integration.transformer.GenericTransformer;
import task1.model.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class PersonToXmlTransformer implements GenericTransformer<Person, String> {


    @Override
    public String transform(Person source) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Person.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(source, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
