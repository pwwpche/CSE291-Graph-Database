import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;


public class JacksonStreamExample {
    public static void main(String[] args) {

        try {

            JsonFactory jfactory = new JsonFactory();

            /*** read from file ***/
            JsonParser jParser = jfactory.createJsonParser(new File("src/large.csv"));

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldname = jParser.getCurrentName();
                if("row".equals(fieldname)){
                    String value = jParser.getText();
                }
                if(fieldname != null){
                    System.out.println(fieldname);
                }
                jParser.nextToken();

            }
            jParser.close();

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}