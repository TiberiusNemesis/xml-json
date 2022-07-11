package au.com.marlo.students.tdourado.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;

/**
 * Class for validating .xml files against a schema.
 *
 * @author tiberiusdourado
 */
@Slf4j
public class XmlValidator {
  Source xmlFileSource;
  public SchemaFactory schemaFactory =
      SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
  Schema schema;
  Validator validator;

  /**
   * Determines whether an XML file is valid against its defined schema.
   *
   * @param xmlFile The XML file to be validated.
   * @param xmlSchema The XML schema to be used for validation.
   * @return True if the XML is valid by its schema definitions, False if it is invalid or an
   *     exception occurs.
   */
  public boolean isXmlValid(File xmlFile, File xmlSchema) {
    try {
      xmlFileSource = new StreamSource(xmlFile);
      schema = schemaFactory.newSchema(xmlSchema);
      validator = schema.newValidator();
      validator.validate(xmlFileSource);
      log.debug("XML file {} is valid.", xmlFile.getName());
      return true;
    } catch (SAXException | IOException | NullPointerException e) {
      log.debug("XML file validation failed. \nReason: {}", e.getMessage());
    }
    return false;
  }
}
