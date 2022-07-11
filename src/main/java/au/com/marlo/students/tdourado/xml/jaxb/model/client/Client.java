package au.com.marlo.students.tdourado.xml.jaxb.model.client;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
    name = "client",
    propOrder = {"clientName", "age", "gender", "documentId", "availableAccountCredit"})
@XmlRootElement(name = "client", namespace = "http://www.marlo.com.au/training/client")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Client {

  @XmlElement(required = true)
  protected String clientName;

  @XmlElement(required = true)
  protected BigInteger age;

  /** Enumeration for the client's gender. */
  public enum Gender {
    MALE,
    FEMALE
  }

  protected Gender gender;
  protected long documentId;

  @XmlElement(required = true)
  protected BigDecimal availableAccountCredit;

  /**
   * AllArgsConstructor for the Client model. Necessary due to the special treatment that goes into
   * making a BigDecimal with a scale of 2.
   *
   * @param clientName Name of the client.
   * @param age Age of the client.
   * @param gender Social construct belief of the client.
   * @param documentId Document ID of the client.
   * @param availableAccountCredit Total account credit of the client.
   */
  public Client(
      String clientName,
      BigInteger age,
      Gender gender,
      long documentId,
      Double availableAccountCredit) {
    this.clientName = clientName;
    this.age = age;
    this.gender = gender;
    this.documentId = documentId;
    this.availableAccountCredit =
        new BigDecimal(availableAccountCredit).setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * Setter method for the account credit. Necessary due to the special treatment a BigDecimal type
   * object needs.
   *
   * @param credit The amount of credit to be set.
   */
  public void setAvailableAccountCredit(Double credit) {
    this.availableAccountCredit = new BigDecimal(credit).setScale(2, RoundingMode.HALF_UP);
  }
}
