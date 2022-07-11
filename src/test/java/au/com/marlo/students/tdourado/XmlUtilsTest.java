package au.com.marlo.students.tdourado;

import static au.com.marlo.students.tdourado.xml.XmlUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import au.com.marlo.students.tdourado.xml.XmlUtils;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.sf.saxon.s9api.XdmValue;
import org.junit.jupiter.api.Test;

class XmlUtilsTest {
  @Test
  public void getPathTest_whenFailed_shouldThrow() {
    assertThrows(FileNotFoundException.class, () -> getPath("The X Files"));
  }

  @Test
  void getPropertiesTest_whenCorrectPath_shouldReturnProperties() {
    assertNotNull(getProperties(Path.of("application.properties")));
  }

  @Test
  void getPropertiesTest_whenWrongPath_shouldFail() {
    assertNull(getProperties(Path.of("application.propertoes")));
  }

  @Test
  void generateResultFileTest_whenInvalidXdm_shouldNotGenerateFile()
      throws FileNotFoundException, NullPointerException {
    generateResultFile(null, "test");
    String path = getPath("result") + "/test.xml";
    Path pathToTest = Path.of(path);
    assertTrue(Files.notExists(pathToTest));
  }

  @Test
  void generateResultFileTest_whenEmptyXdm_shouldNotGenerateFile()
      throws FileNotFoundException, NullPointerException {
    XdmValue testXdmValue = XdmValue.makeValue("");
    generateResultFile(testXdmValue, "test");
    String path = getPath("result") + "/test.xml";
    Path pathToTest = Path.of(path);
    assertTrue(Files.notExists(pathToTest));
  }

  @Test
  void xmlUtilsConstructorTest_shouldThrow() {
    assertThrows(UnsupportedOperationException.class, XmlUtils::new);
  }
}
