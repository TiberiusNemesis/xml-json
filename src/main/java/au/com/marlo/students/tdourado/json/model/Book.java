package au.com.marlo.students.tdourado.json.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Book object model for JSON deserialization. */
@Data
@JsonRootName("book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  private String author;
  private String title;
  private BigDecimal price;
}
