package au.com.marlo.students.tdourado.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import net.sf.saxon.s9api.XdmValue;

/** Utility class for XML-related classes and methods. */
@Slf4j
public class XmlUtils {
  public static final Properties properties = getProperties(Path.of("application.properties"));

  /**
   * Constructor that throws an exception, since this utility class is not supposed to be
   * instantiated.
   */
  public XmlUtils() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns a Properties object based on an application.properties file.
   *
   * @param pathToProperties Path to the application.properties file.
   * @return Properties object containing data from the file.
   */
  public static Properties getProperties(Path pathToProperties) {
    final Properties properties = new Properties();
    try (InputStream inputStream =
        Thread.currentThread()
            .getContextClassLoader()
            .getResourceAsStream(String.valueOf(pathToProperties))) {
      properties.load(inputStream);
    } catch (Exception e) {
      log.debug("Error in fetching properties: ", e);
      return null;
    }
    return properties;
  }

  /**
   * Through the usage of an application.properties file, gets the path of the desired directory or
   * file.
   *
   * @param file The file or directory requested.
   * @return An absolute path to a file or directory.
   * @throws FileNotFoundException If the filepath is not found.
   */
  public static Path getPath(String file) throws FileNotFoundException {
    Path path;
    try {
      path = Path.of(properties.getProperty(file));
    } catch (Exception e) {
      log.debug("File or directory {} not found.", file);
      throw new FileNotFoundException();
    }
    return path;
  }

  /**
   * Generates a result file for any XdmValue (likely from an xQuery execution).
   *
   * @param xdm The XDMValue to be transformed into a file.
   * @param filename The desired name for the result file.
   */
  public static void generateResultFile(XdmValue xdm, String filename) {
    try {
      if (!xdm.stream().asString().equals("")) {
        try (PrintWriter out =
            new PrintWriter(
                new File((getPath("result")).toString()) + "/" + filename + ".xml",
                StandardCharsets.UTF_8)) {
          out.println(xdm.stream().asString());
        }
      }
    } catch (IOException | NullPointerException e) {
      log.debug("Error while generating result file: {}", e.getMessage());
    }
  }
}
