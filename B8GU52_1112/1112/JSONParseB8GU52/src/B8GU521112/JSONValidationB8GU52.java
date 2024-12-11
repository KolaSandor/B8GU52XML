package B8GU521112;

import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONValidationB8GU52 {

    public static void main(String[] args) {
        String jsonFilePath = "C:\\Users\\Kola Sándor\\Desktop\\XML\\1112\\1112\\B8GU52_orarend.json";
        String schemaFilePath = "C:\\Users\\Kola Sándor\\Desktop\\XML\\1112\\1112\\B8GU52_orarend_schema.json";

        try {
         
            com.fasterxml.jackson.databind.JsonNode jsonNode = JsonLoader.fromReader(new FileReader(jsonFilePath));
            com.fasterxml.jackson.databind.JsonNode schemaNode = JsonLoader.fromReader(new FileReader(schemaFilePath));

            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);

            ProcessingReport report = schema.validate(jsonNode);

          
            if (report.isSuccess()) {
                System.out.println("Validation successful.");
            } else {
                System.out.println("Validation failed.");
                System.out.println(report);
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (ProcessingException e) {
            System.out.println("Schema processing error: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("JSON parsing error: " + e.getMessage());
        }
    }
}