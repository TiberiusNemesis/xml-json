package au.com.marlo.students.tdourado.xml;

import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;

import java.io.File;
import lombok.extern.slf4j.Slf4j;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmValue;

/**
 * Class to handle all XQuery related methods and operations through usage of the Saxon S9 API.
 *
 * @author tiberiusdourado
 */
@Slf4j
public class SaxonXQuery {

  /**
   * Fetches an .xq file and executes it, generating an XdmValue containing the query results.
   *
   * @param xQueryFilename Name of the xQuery file to be executed.
   * @return An XdmValue object containing the query results.
   */
  public XdmValue executeXQuery(String xQueryFilename) {
    try {
      Processor saxon = new Processor(false);
      XQueryCompiler compiler = saxon.newXQueryCompiler();
      XQueryExecutable exec = compiler.compile(new File(String.valueOf(getPath(xQueryFilename))));
      XQueryEvaluator query = exec.load();
      return query.evaluate();
    } catch (Exception e) {
      log.debug("Error while executing XQuery: {}", e.getMessage());
    }
    return null;
  }

  /**
   * Fetches an .xq file and executes it, generating an XdmValue containing the query results.
   *
   * @param xQueryFilename Name of the xQuery file to be executed.
   * @param parameter The extra parameter for the desired query.
   * @return An XdmValue object containing the query results.
   */
  public XdmValue executeXQuery(String xQueryFilename, String parameter) {
    try {
      Processor saxon = new Processor(false);
      XQueryCompiler compiler = saxon.newXQueryCompiler();
      XQueryExecutable exec = compiler.compile(new File(String.valueOf(getPath(xQueryFilename))));
      XQueryEvaluator query = exec.load();
      query.setExternalVariable(new QName("param"), XdmValue.makeValue(parameter));
      return query.evaluate();
    } catch (Exception e) {
      log.debug("Error while executing XQuery: {}", e.getMessage());
    }
    return null;
  }
}
