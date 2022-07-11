package au.com.marlo.students.tdourado.json;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class JsonUtilsTest {

  @Test
  void jsonUtilsConstructorTest_shouldThrow() {
    assertThrows(UnsupportedOperationException.class, JsonUtils::new);
  }
}
