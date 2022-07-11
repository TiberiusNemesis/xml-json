package au.com.marlo.students.tdourado.xml.jaxb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * A JAXB handler class, allowing for easy marshalling & unmarshalling of the desired classes or XML
 * objects.
 */
public class JAXBConverter {
  JAXBContext jaxbContext;
  Marshaller marshaller;
  Unmarshaller unmarshaller;

  /**
   * Constructor method that sets parameters for the desired object.
   *
   * @param object The object to be marshalled or unmarshalled.
   * @throws JAXBException Failure to properly create instances or marshallers.
   */
  public JAXBConverter(Object object) throws JAXBException {
    this.jaxbContext = JAXBContext.newInstance(object.getClass());
    marshaller = jaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    unmarshaller = jaxbContext.createUnmarshaller();
  }

  /**
   * Marshal method that converts the object param to XML.
   *
   * @param object The object to be marshalled.
   * @param file Output file.
   * @throws JAXBException Failure to properly marshal.
   * @throws IOException Error in outputting file.
   */
  public void marshal(Object object, File file) throws JAXBException, IOException {
    try (PrintWriter out = new PrintWriter(file, StandardCharsets.UTF_8)) {
      marshaller.marshal(object, out);
    }
  }

  /**
   * Unmarshal method converts the XML file. Returns an object.
   *
   * @param file The XML file to be unmarshalled.
   * @return An object representing the XML file provided.
   * @throws JAXBException Failure to properly unmarshal.
   */
  public Object unmarshal(File file) throws JAXBException {
    return unmarshaller.unmarshal(file);
  }
}
