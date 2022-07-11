package au.com.marlo.students.tdourado.json;

import static au.com.marlo.students.tdourado.json.JsonUtils.getJsonNode;
import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

/**
 * A Jackson handler class, providing easy serialization and deserialization of the desired classes
 * or JSON objects.
 */
@Slf4j
public class JacksonConverter {

  ObjectMapper mapper = new ObjectMapper();

  /**
   * Serialization method for any given object. Generates a JSON file containing the object's
   * attributes.
   *
   * @param obj The object to be serialized.
   * @throws IOException In case of serialization error or a FileNotFound error.
   */
  public void serialize(Object obj) throws IOException {
    try (PrintWriter out =
        new PrintWriter(
            getPath("jsonResult") + "/" + obj.getClass().getSimpleName() + ".json",
            StandardCharsets.UTF_8)) {
      mapper.writeValue(out, obj);
      log.debug(
          "{} object serialized into {}.json.",
          obj.getClass().getSimpleName(),
          obj.getClass().getSimpleName());
    }
  }

  /**
   * Deserialization method for a given JSON file and an equivalent T class. Transforms the JSON
   * file into an object of class T.
   *
   * @param file The JSON file to be deserialized.
   * @param t The class model to be used in creating an object from the JSON file.
   * @return Object of T type.
   */
  public Object deserializeToObject(File file, Class t) {
    try {
      return mapper.readValue(file, t);
    } catch (Exception e) {
      log.debug("Error while deserializing file {}: {}", file.getName(), e.getMessage());
      return null;
    }
  }

  /**
   * Deserialization method for a given JSON file. Transforms the JSON file into a JsonNode object,
   * allowing it to be manipulated, queried and parsed without being strictly bound to any object of
   * class T.
   *
   * @param filename The to-be-deserialized JSON filename.
   * @return JsonNode object.
   */
  public JsonNode deserializeToJsonNode(String filename) {
    try {
      return getJsonNode(getPath(filename).toFile());
    } catch (IOException e) {
      log.debug("Error while deserializing {} to JsonNode: {}", filename, e.getMessage());
      return null;
    }
  }
}
