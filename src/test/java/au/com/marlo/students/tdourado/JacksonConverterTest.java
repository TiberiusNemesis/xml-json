package au.com.marlo.students.tdourado;

import static au.com.marlo.students.tdourado.json.JsonUtils.getJsonNode;
import static au.com.marlo.students.tdourado.json.JsonUtils.getJsonSchema;
import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import au.com.marlo.students.tdourado.json.JacksonConverter;
import au.com.marlo.students.tdourado.json.model.Book;
import au.com.marlo.students.tdourado.json.model.Library;
import au.com.marlo.students.tdourado.json.model.Publisher;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class JacksonConverterTest {
  JacksonConverter JC = new JacksonConverter();

  @Test
  void schemaValidatorTest() throws IOException {
    JsonNode node = getJsonNode(new File(getPath("booksJson").toString()));
    JsonSchema schema = getJsonSchema(new File(getPath("booksSchemaJson").toString()));
    Set<ValidationMessage> errors = schema.validate(node);
    assertEquals(errors.size(), 0);
  }

  @Test
  void serializerTest_whenValidObject_shouldGenerateFile() throws IOException {
    Book veryGoodBook =
        new Book("Brandon Sanderson", "The Way of Kings", BigDecimal.valueOf(29.99));
    JC.serialize(veryGoodBook);
    Path pathToResult = Path.of(getPath("jsonResult") + "/Book.json");
    Path pathToExpected = Path.of(getPath("jsonExpected") + "/expectedBook.json");
    byte[] result = Files.readAllBytes(pathToResult);
    byte[] expected = Files.readAllBytes(pathToExpected);
    assertArrayEquals(result, expected);
  }

  @Test
  void deserializeToObjectTest_whenValid_shouldContainObjects() throws IOException {
    Library libraryWrapper =
        (Library) JC.deserializeToObject(getPath("booksJson").toFile(), Library.class);
    List<Publisher> library = libraryWrapper.getLibrary().getPublisher();
    List<String> titles = new ArrayList<>();
    library.forEach(publisher -> publisher.getBook().forEach(book -> titles.add(book.getTitle())));
    assertTrue(titles.contains("Dune") && titles.size() == 7);
    // a check for an expected book value & expected size
  }

  @Test
  void deserializeToJsonNodeTest_whenValid_shouldReturnObject() {
    JsonNode jsonNode = JC.deserializeToJsonNode("booksJson");
    assertEquals(1, jsonNode.size()); // meaning it has one "Library" root object
  }

  @Test
  void deserializeToJsonNodeTest_whenInvalid_shouldReturnNull() {
    JsonNode jsonNode = JC.deserializeToJsonNode("i am a mistake");
    assertNull(jsonNode);
  }

  @Test
  void deserializeToObjectTest_whenInvalid_shouldReturnNull() throws FileNotFoundException {
    Library libraryWrapper =
        (Library) JC.deserializeToObject(getPath("booksJson").toFile(), Error.class);
    assertNull(libraryWrapper);
  }
}
