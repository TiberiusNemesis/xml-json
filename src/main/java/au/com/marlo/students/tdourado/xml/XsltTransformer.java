package au.com.marlo.students.tdourado.xml;

import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;

import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.transform.stream.StreamSource;
import lombok.extern.slf4j.Slf4j;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

/**
 * XSLT transformer for XML to XML conversions. Essentially, an over-engineered xQuery.
 *
 * @author tiberiusdourado
 */
@Slf4j
public class XsltTransformer {
  /**
   * Runs the XsltTransformer, outputting a new .xml file based on the XSLT transformation.
   *
   * @param xmlFile The XML file to be transformed.
   * @param xsltFile The XSLT file containing the transformation specifications.
   * @throws SaxonApiException If there is a runtime exception in the XSLT objects.
   * @throws FileNotFoundException If a necessary file is not found.
   */
  public void run(String xmlFile, String xsltFile) throws SaxonApiException, FileNotFoundException {
    Processor proc = new Processor(false);
    XsltCompiler comp = proc.newXsltCompiler();
    XsltExecutable exp = comp.compile(new StreamSource(String.valueOf(getPath(xsltFile))));
    XdmNode source =
        proc.newDocumentBuilder().build(new StreamSource(String.valueOf(getPath(xmlFile))));
    Serializer out = proc.newSerializer();
    out.setOutputProperty(Serializer.Property.METHOD, "xml");
    out.setOutputFile(new File(getPath("result") + "/" + xsltFile + ".xml"));
    net.sf.saxon.s9api.XsltTransformer trans = exp.load();
    trans.setInitialContextNode(source);
    trans.setDestination(out);
    trans.transform();

    log.info("Output written to {}.xml", xsltFile);
  }

  /**
   * Runs the XsltTransformer, outputting a new .xml file based on the XSLT transformation. Takes in
   * an additional parameter into the XSLT file for a query specification.
   *
   * @param xmlFile The XML file to be transformed.
   * @param xsltFile The XSLT file containing the transformation specifications.
   * @param parameter The external parameter for the XSLT query.
   * @throws SaxonApiException If there is a runtime exception in the XSLT objects.
   * @throws FileNotFoundException If a necessary file is not found.
   */
  public void run(String xmlFile, String xsltFile, String parameter)
      throws SaxonApiException, FileNotFoundException {
    Processor proc = new Processor(false);
    Serializer out = proc.newSerializer();
    out.setOutputProperty(Serializer.Property.METHOD, "xml");
    out.setOutputFile(new File(getPath("result") + "/" + xsltFile + ".xml"));
    XsltCompiler xsltCompiler = proc.newXsltCompiler();
    XsltExecutable xsltExecutable =
        xsltCompiler.compile(new StreamSource(String.valueOf(getPath(xsltFile))));
    net.sf.saxon.s9api.XsltTransformer transformer = xsltExecutable.load();
    transformer.setParameter(new QName("external"), XdmValue.makeValue(parameter));
    transformer.setSource(new StreamSource(String.valueOf(getPath(xmlFile))));
    transformer.setDestination(out);
    transformer.transform();

    log.info("Output written to {}.xml", xsltFile);
  }
}
