package au.com.marlo.students.tdourado.xml.jaxb.model.request;

import au.com.marlo.students.tdourado.xml.jaxb.model.client.Client;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The following class was automatically generated using JAXB. However, this model has been altered
 * to better fit the schema's original definitions.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "request",
    propOrder = {"client"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Request {

  @XmlElement(namespace = "http://www.marlo.com.au/training/client", required = true)
  protected Client client;
}
