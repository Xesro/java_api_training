package utils;

import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



import java.io.FileInputStream;
import java.util.concurrent.ExecutionException;

class SchemaValidatorTest {

    @Test
    void validate() throws Exception {
        SchemaValidator validator = new SchemaValidator();
        FileInputStream inputStream = new FileInputStream("src/test/resources/payload_test_success.json");
        JSONObject jsonObject =  new JSONObject(new String(inputStream.readAllBytes()));
        Assertions.assertDoesNotThrow(
            () -> validator.validate(jsonObject, "src/main/resources/start_game_schema.json")
            );
    }

    @Test
    void validateFail() throws Exception {
        SchemaValidator validator = new SchemaValidator();
        FileInputStream inputStream = new FileInputStream("src/test/resources/payload_test_failure.json");
        JSONObject jsonObject =  new JSONObject(new String(inputStream.readAllBytes()));
        assertThrows(ValidationException.class,
            () -> validator.validate(jsonObject, "src/main/resources/start_game_schema.json")
        );
    }


}
