package au.com.marlo.students.tdourado.xml.jaxb.model.order;

import au.com.marlo.students.tdourado.xml.jaxb.model.client.Client;
import au.com.marlo.students.tdourado.xml.jaxb.model.product.Product;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
    name = "order",
    propOrder = {"orderNumber", "client", "products"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement
public class Order {

  @XmlElement(required = true)
  protected long orderNumber;

  @XmlElement(required = true)
  protected Client client;

  @XmlElement(required = true, name = "product")
  @XmlElementWrapper(name = "products")
  protected List<Product> products;
}
