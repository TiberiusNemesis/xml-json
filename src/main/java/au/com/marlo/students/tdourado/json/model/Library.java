package au.com.marlo.students.tdourado.json.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Library object model for JSON deserialization. */
@Data
@NoArgsConstructor
@JsonRootName(value = "library")
public class Library {
  private Library library;
  private List<Publisher> publisher;
}
