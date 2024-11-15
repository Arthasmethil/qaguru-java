package guru.qa.lesson10;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.lesson10.model.Department;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonSerializationTests {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ClassLoader classLoader = getClass().getClassLoader();

    @Test
    void jsonFileParsingTest() throws Exception {
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        try ( InputStream inputStream = classLoader.getResourceAsStream("guru/qa/lesson10/sample.json")) {

            Department department = mapper.readValue(inputStream, Department.class);

            assertThat(department.getName()).isEqualTo("IT Department");
            assertThat(department.getEmployees()).hasSize(2);
            assertThat(department.getEmployees().get(0).getName()).isEqualTo("John Doe");
            assertThat(department.getManager()).isEqualTo("Jane Smith");
        }



    }
}
