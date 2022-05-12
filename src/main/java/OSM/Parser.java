package OSM;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlType;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.util.function.Consumer;

public class Parser {
    private static Logger logger = Logger.getLogger(Parser.class);

    public <T> void parseXML(XMLStreamReader xmlStreamReader, Class<T> type, Consumer<T> onParseElement)
            throws XMLStreamException, JAXBException {
        String tagName = getTagName(type);
        logger.debug("Parsing tags");

        JAXBContext context = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        while(xmlStreamReader.hasNext()) {
            int event = xmlStreamReader.next();
            if(event == XMLEvent.START_ELEMENT && tagName.equals(xmlStreamReader.getLocalName())) {
                T element = unmarshaller.unmarshal(xmlStreamReader, type).getValue();
                onParseElement.accept(element);
            }
        }
    }

    private <T> String getTagName(Class<T> type) {
        XmlType xmlType = type.getAnnotation(XmlType.class);
        String tagName;
        if(xmlType == null) {
            tagName = type.getName();
        } else {
            tagName = xmlType.name();
        }

        return tagName;
    }
}
