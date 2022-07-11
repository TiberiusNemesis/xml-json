package au.com.marlo.students.tdourado.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for containing static JSON-related methods.
 *
 * @author tiberiusdourado
 */
public class JsonUtils {

  /**
   * Constructor that throws an exception, since this utility class is not supposed to be
   * instantiated.
   */
  public JsonUtils() {
    throw new UnsupportedOperationException();
  }

  /**
   * Gets a JsonNode object from a given JSON File object.
   *
   * @param json File object representing a JSON file.
   * @return JsonNode object.
   * @throws IOException In case of deserialization error.
   */
  public static JsonNode getJsonNode(File json) throws IOException {
    try (InputStream is = new FileInputStream(json)) {
      return new ObjectMapper().readTree(is);
    }
  }

  /**
   * Gets a JsonSchema object from a JSON file containing the schema.
   *
   * @param schema File object representing a JSON schema.
   * @return JsonSchema object.
   * @throws IOException In case of deserialization error, or FileNotFound error.
   */
  public static JsonSchema getJsonSchema(File schema) throws IOException {
    JsonNode jsonNode = getJsonNode(schema);
    JsonSchemaFactory factory =
        JsonSchemaFactory.builder(
                JsonSchemaFactory.getInstance(SpecVersionDetector.detect(jsonNode)))
            .objectMapper(new ObjectMapper())
            .build();
    try (InputStream is = new FileInputStream(schema)) {
      return factory.getSchema(is);
    }
  }
}
