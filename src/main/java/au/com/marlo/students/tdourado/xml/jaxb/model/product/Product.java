package au.com.marlo.students.tdourado.xml.jaxb.model.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The following class was automatically generated using JAXB. However, this model has been altered
 * to better fit the schema's original definitions.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "product",
    propOrder = {"productCode", "description", "price", "category"})
@NoArgsConstructor
@XmlRootElement
public class Product {

  @XmlElement(required = true)
  protected String productCode;

  protected String description;

  @XmlElement(required = true)
  protected BigDecimal price;

  @XmlElement(required = true)
  public Category category;

  /** Enumeration for the product category. */
  public enum Category {
    ELECTRONICS,
    SPORTSWEAR,
    SHOES
  }

  /**
   * Constructor method for the Product object. Checks the productCode by using a regex; it will
   * only take in codes with the pattern ABCDE0124.
   *
   * @param productCode Code of the product in a sequence of 5 letters, followed by 4 numbers.
   * @param description Description of the product.
   * @param price Price of the product as a double, to be converted to BigDecimal.
   * @param category Category of the product.
   */
  public Product(String productCode, String description, Double price, Category category) {
    if (Pattern.compile("[a-zA-Z]{5}[0-9]{4}$").matcher(productCode).matches()) {
      this.productCode = productCode;
    } else {
      this.productCode = null;
    }
    this.description = description;
    this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    this.category = category;
  }

  /**
   * Setter method for the productCode. Checks the productCode by using a regex; it will only take
   * in codes with the pattern ABCDE0124.
   *
   * @param productCode Code of the product in a sequence of 5 letters, followed by 4 numbers.
   */
  public void setProductCode(String productCode) {
    if (Pattern.compile("[a-zA-Z]{5}[0-9]{4}$").matcher(productCode).matches()) {
      this.productCode = productCode;
    } else {
      this.productCode = null;
    }
  }
}
