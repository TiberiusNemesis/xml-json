package au.com.marlo.students.tdourado;

import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import au.com.marlo.students.tdourado.xml.XmlValidator;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.validation.SchemaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.xml.sax.SAXException;

class XmlValidatorTest {
  XmlValidator validator;
  SchemaFactory mockSchemaFactory;
  File xml, schema;

  @BeforeEach
  void setUp() {
    validator = new XmlValidator();
    mockSchemaFactory = mock(SchemaFactory.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"request", "response"})
  void isValidTest_whenValidSchema_ShouldReturnTrue(String file) throws FileNotFoundException {
    schema = new File(getPath(file + "Schema").toString());
    xml = new File(getPath(file).toString());
    assertTrue(validator.isXmlValid(xml, schema));
  }

  @ParameterizedTest
  @CsvSource({"badOrderResponse, responseSchema", "evenWorseNoOrderResponse, responseSchema"})
  void isValidTest_whenUnusualButValidRequest_ShouldReturnTrue(String file, String schemaFile)
      throws FileNotFoundException {
    schema = new File(getPath(schemaFile).toString());
    xml = new File(getPath(file).toString());
    assertTrue(validator.isXmlValid(xml, schema));
  }

  @Test
  void isValidTest_whenInvalidRequest_shouldReturnFalse() throws FileNotFoundException {
    schema = new File(getPath("clientSchema").toString());
    xml = new File(getPath("badOrderResponse").toString());
    assertFalse(validator.isXmlValid(xml, schema));
  }

  @Test
  void isValidTest_whenExceptionOccurs_shouldReturnFalse()
      throws SAXException, FileNotFoundException {
    doThrow(new SAXException()).when(mockSchemaFactory).newSchema(any(File.class));
    validator.schemaFactory = mockSchemaFactory;
    schema = new File(getPath("requestSchema").toString());
    xml = new File(getPath("request").toString());
    assertFalse(validator.isXmlValid(xml, schema));
  }
}
