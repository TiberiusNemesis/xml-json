package au.com.marlo.students.tdourado.json.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Publisher object model for JSON deserialization. */
@Data
@JsonRootName("publisher")
@NoArgsConstructor
public class Publisher {
  private String name;
  private List<Book> book;
}
