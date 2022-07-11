package au.com.marlo.students.tdourado;

import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import au.com.marlo.students.tdourado.xml.XsltTransformer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import net.sf.saxon.s9api.SaxonApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class XsltTransformerTest {

  private XsltTransformer xsltTransformer;
  private String pathToExpectedResult;
  private String pathToResult;

  @BeforeEach
  void setUp() {
    xsltTransformer = new XsltTransformer();
    pathToExpectedResult = "";
    pathToResult = "";
  }

  @Test
  void booksTest_shouldTransformIntoAuthors() throws IOException, SaxonApiException {
    xsltTransformer.run("books", "authorsLt");
    pathToResult = getPath("result").toString() + "/authorsLt.xml";
    pathToExpectedResult = getPath("expected").toString() + "/books/expectedAuthorsLt.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }

  @Test
  void booksTest_shouldTransformIntoExpensiveBooks() throws IOException, SaxonApiException {
    xsltTransformer.run("books", "expensiveBooksLt");
    pathToResult = getPath("result").toString() + "/expensiveBooksLt.xml";
    pathToExpectedResult = getPath("expected").toString() + "/books/expectedExpensiveBooksLt.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }

  @Test
  void booksTest_shouldTransformIntoChosenAuthors() throws IOException, SaxonApiException {
    xsltTransformer.run("books", "booksByAuthorLt", "Frank Herbert");
    pathToResult = getPath("result").toString() + "/booksByAuthorLt.xml";
    pathToExpectedResult = getPath("expected").toString() + "/books/expectedBooksByAuthorLt.xml";
    byte[] result = Files.readAllBytes(Path.of(pathToResult));
    byte[] expected = Files.readAllBytes(Path.of(pathToExpectedResult));
    assertArrayEquals(result, expected);
  }
}
