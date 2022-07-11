package au.com.marlo.students.tdourado;

import static au.com.marlo.students.tdourado.xml.XmlUtils.getPath;
import static au.com.marlo.students.tdourado.xml.jaxb.model.product.Product.Category.SHOES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import au.com.marlo.students.tdourado.xml.XmlValidator;
import au.com.marlo.students.tdourado.xml.jaxb.JAXBConverter;
import au.com.marlo.students.tdourado.xml.jaxb.model.client.Client;
import au.com.marlo.students.tdourado.xml.jaxb.model.client.ClientFactory;
import au.com.marlo.students.tdourado.xml.jaxb.model.order.Order;
import au.com.marlo.students.tdourado.xml.jaxb.model.order.OrderFactory;
import au.com.marlo.students.tdourado.xml.jaxb.model.product.Product;
import au.com.marlo.students.tdourado.xml.jaxb.model.product.ProductFactory;
import au.com.marlo.students.tdourado.xml.jaxb.model.request.Request;
import au.com.marlo.students.tdourado.xml.jaxb.model.request.RequestFactory;
import au.com.marlo.students.tdourado.xml.jaxb.model.response.Response;
import au.com.marlo.students.tdourado.xml.jaxb.model.response.ResponseFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JAXBConverterTest {
  private Client clientTest;
  private Order orderTest;
  private Product productTest;
  private Request requestTest;
  private Response responseTest;

  @BeforeEach
  void setUp() {
    clientTest = new Client("Little Jimmy", BigInteger.TEN, Client.Gender.MALE, 1507772416L, 3.50);
    productTest = new Product("RAMBO1970", "Rambo Jungle Boots", 2000.00, SHOES);
    List<Product> productList = new ArrayList<>();
    productList.add(productTest);
    orderTest = new Order(100L, clientTest, productList);
    requestTest = new Request(clientTest);
    Response.Orders orders = new Response.Orders();
    List<Order> orderList = new ArrayList<>();
    orderList.add(orderTest);
    orders.setOrder(orderList);
    responseTest = new Response(orders);
  }

  @Test
  void marshalTest_shouldGenerateClientXml() throws JAXBException, IOException {
    JAXBConverter JAXBMachine = new JAXBConverter(clientTest);
    ClientFactory factory = new ClientFactory();
    JAXBElement<Client> clientElement = factory.createClient(clientTest);
    JAXBMachine.marshal(clientElement, new File(getPath("clientResult").toString()));
    assertTrue(
        new XmlValidator()
            .isXmlValid(
                new File(getPath("clientResult").toString()),
                new File(getPath("clientSchema").toString())));
  }

  @Test
  void unmarshalTest_shouldGenerateClientObject() throws JAXBException, FileNotFoundException {
    JAXBConverter JAXBMachine = new JAXBConverter(clientTest);
    Client client = (Client) JAXBMachine.unmarshal(getPath("clientResult").toFile());
    assertEquals(client.getClientName(), "Little Jimmy");
  }

  @Test
  void marshalTest_shouldGenerateOrderXml() throws JAXBException, IOException {
    JAXBConverter JAXBMachine = new JAXBConverter(orderTest);
    OrderFactory factory = new OrderFactory();
    JAXBElement<Order> orderElement = factory.createOrder(orderTest);
    JAXBMachine.marshal(orderElement, new File(getPath("orderResult").toString()));
    assertTrue(
        new XmlValidator()
            .isXmlValid(
                new File(getPath("orderResult").toString()),
                new File(getPath("orderSchema").toString())));
  }

  @Test
  void unmarshalTest_shouldGenerateOrderObject() throws JAXBException, FileNotFoundException {
    JAXBConverter JAXBMachine = new JAXBConverter(orderTest);
    Order order = (Order) JAXBMachine.unmarshal(getPath("orderResult").toFile());
    assertEquals(order.getClient().getClientName(), "Little Jimmy");
  }

  @Test
  void marshalTest_shouldGenerateProductXml() throws JAXBException, IOException {
    JAXBConverter JAXBMachine = new JAXBConverter(productTest);
    ProductFactory factory = new ProductFactory();
    JAXBElement<Product> productElement = factory.createProduct(productTest);
    JAXBMachine.marshal(productElement, new File(getPath("productResult").toString()));
    assertTrue(
        new XmlValidator()
            .isXmlValid(
                new File(getPath("productResult").toString()),
                new File(getPath("productSchema").toString())));
  }

  @Test
  void unmarshalTest_shouldGenerateProductObject() throws JAXBException, FileNotFoundException {
    JAXBConverter JAXBMachine = new JAXBConverter(productTest);
    Product product = (Product) JAXBMachine.unmarshal(getPath("productResult").toFile());
    assertEquals(product.getDescription(), "Rambo Jungle Boots");
  }

  @Test
  void marshalTest_shouldGenerateResponseXml() throws JAXBException, IOException {
    JAXBConverter JAXBMachine = new JAXBConverter(responseTest);
    ResponseFactory factory = new ResponseFactory();
    JAXBElement<Response> responseElement = factory.createResponse(responseTest);
    JAXBMachine.marshal(responseElement, new File(getPath("responseResult").toString()));
    assertTrue(
        new XmlValidator()
            .isXmlValid(
                new File(getPath("responseResult").toString()),
                new File(getPath("responseSchema").toString())));
  }

  @Test
  void unmarshalTest_shouldGenerateResponseObject() throws JAXBException, FileNotFoundException {
    JAXBConverter JAXBMachine = new JAXBConverter(responseTest);
    Response response = (Response) JAXBMachine.unmarshal(getPath("responseResult").toFile());
    assertEquals(
        response.getOrders().getOrder().get(0).getOrderNumber(), orderTest.getOrderNumber());
  }

  @Test
  void marshalTest_shouldGenerateRequestXml() throws JAXBException, IOException {
    JAXBConverter JAXBMachine = new JAXBConverter(requestTest);
    RequestFactory factory = new RequestFactory();
    JAXBElement<Request> requestElement = factory.createRequest(requestTest);
    JAXBMachine.marshal(requestElement, new File(getPath("requestResult").toString()));
    assertTrue(
        new XmlValidator()
            .isXmlValid(
                new File(getPath("requestResult").toString()),
                new File(getPath("requestSchema").toString())));
  }

  @Test
  void unmarshalTest_shouldGenerateRequestObject() throws JAXBException, FileNotFoundException {
    JAXBConverter JAXBMachine = new JAXBConverter(requestTest);
    Request request = (Request) JAXBMachine.unmarshal(getPath("requestResult").toFile());
    assertEquals(request.getClient().getClientName(), clientTest.getClientName());
  }
}
