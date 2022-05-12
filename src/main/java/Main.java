import OSM.Statistics;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException, CompressorException, JAXBException {
        Logger logger = Logger.getLogger(Main.class);
        CompressorInputStream cis = new CompressorStreamFactory().createCompressorInputStream(CompressorStreamFactory.BZIP2,
                new BufferedInputStream(new FileInputStream("RU-NVS.osm.bz2")));

        Statistics statistics = new Statistics(cis);

        System.out.println(statistics.getUsers());
        logger.debug("users printed");
        System.out.println(statistics.getTagNodes());
        logger.debug("nodes printed");
    }
}