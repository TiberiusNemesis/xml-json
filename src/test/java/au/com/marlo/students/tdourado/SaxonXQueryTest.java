package au.com.marlo.students.tdourado;

import static au.com.marlo.students.tdourado.xml.XmlUtils.generateResultFile;
import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;
import static org.junit.jupiter.api.Assertions.*;

import au.com.marlo.students.tdourado.xml.SaxonXQuery;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import net.sf.saxon.s9api.XdmValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaxonXQueryTest {
  private String pathToResult;
  private String pathToExpectedResult;
  private final SaxonXQuery xQueryManager = new SaxonXQuery();

  @BeforeEach
  void setUp() {
    pathToResult = null;
    pathToExpectedResult = null;
  }

  @Test
  public void executeXQueryTest_queryForId() throws IOException {
    // The below xdm->string->xdm conversion ensures there are no XdmMultipleItemValue exceptions.
    String query = xQueryManager.executeXQuery("returnId").stream().asString();
    generateResultFile(XdmValue.makeValue(query), "returnId");
    pathToResult = getPath("result").toString() + "/returnId.xml";
    pathToExpectedResult = getPath("expected").toString() + "/books/expectedReturnId.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }

  @Test
  public void executeXQueryTest_queryForPriority() throws IOException {
    int expected, result;
    String query = xQueryManager.executeXQuery("returnPriority").stream().asString();
    generateResultFile(XdmValue.makeValue(query), "returnPriority");
    pathToResult = getPath("result").toString() + "/returnPriority.xml";
    pathToExpectedResult = getPath("expected").toString() + "/books/expectedReturnPriority.xml";
    // This query contains a printout of the current datetime,
    // which makes testing it rather difficult. Any suggestions, please message me.
    try (FileReader expectedReader = new FileReader(pathToExpectedResult, StandardCharsets.UTF_8)) {
      expected = expectedReader.read();
    }
    try (FileReader resultReader = new FileReader(pathToResult, StandardCharsets.UTF_8)) {
      result = resultReader.read();
    }
    assertEquals(expected, result);
  }

  @Test
  public void executeXQueryTest_queryForValue() throws IOException {
    String query = xQueryManager.executeXQuery("returnValue").stream().asString();
    generateResultFile(XdmValue.makeValue(query), "returnValue");
    pathToResult = getPath("result").toString() + "/returnValue.xml";
    pathToExpectedResult = getPath("expected").toString() + "/books/expectedReturnValue.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }

  @Test
  public void executeXQueryTest_queryForBookAuthors() throws IOException {
    String query = xQueryManager.executeXQuery("returnAuthors").toString();
    generateResultFile(XdmValue.makeValue(query), "returnAuthors");
    pathToResult = getPath("result").toString() + "/returnAuthors.xml";
    pathToExpectedResult = getPath("expected").toString() + "/books/expectedReturnAuthors.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }

  @Test
  public void executeXQueryTest_queryForExpensiveBooks() throws IOException {
    String query = xQueryManager.executeXQuery("returnExpensiveBooks").toString();
    generateResultFile(XdmValue.makeValue(query), "returnExpensiveBooks");
    pathToResult = getPath("result").toString() + "/returnExpensiveBooks.xml";
    pathToExpectedResult =
        getPath("expected").toString() + "/books/expectedReturnExpensiveBooks.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }

  @Test
  public void executeXQueryTest_queryForSpecificAuthor() throws IOException {
    String query = xQueryManager.executeXQuery("returnSpecificAuthor", "Frank Herbert").toString();
    generateResultFile(XdmValue.makeValue(query), "returnSpecificAuthor");
    pathToResult = getPath("result").toString() + "/returnSpecificAuthor.xml";
    pathToExpectedResult =
        getPath("expected").toString() + "/books/expectedReturnSpecificAuthor.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }

  @Test
  public void executeXQueryTest_whenFailed_shouldReturnNull() {
    assertNull(xQueryManager.executeXQuery("nail file"));
  }

  @Test
  public void executeXQueryWithParamsTest_whenFailed_shouldReturnNull() {
    assertNull(xQueryManager.executeXQuery("file for bankruptcy", ""));
  }
}
