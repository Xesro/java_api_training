package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;


public class SchemaValidator {

    // create inputStreamFromClasspath() method to load the JSON data from the class path
    private InputStream inputStreamFromClasspath(String path ) throws FileNotFoundException {
        return new FileInputStream(path);
    }

    public void validate(InputStream jsonStream, String pathToSchema) throws Exception {

        JSONObject jsonSchema = new JSONObject(
            new JSONTokener(inputStreamFromClasspath(pathToSchema)));
        JSONObject jsonSubject = new JSONObject(
            new JSONTokener(jsonStream));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }
}
