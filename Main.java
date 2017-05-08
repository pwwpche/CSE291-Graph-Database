// java -Xmx500M -cp "library/antlr-4.6-complete.jar:$CLASSPATH" org.antlr.v4.Tool src/Cypher.g4 -visitor

// Deployment:
// Maven add commons-csv-1.4, mongodb-driver-3.4.2

import java.io.BufferedReader;
import java.io.FileReader;
import javax.json.*;

public class Main {

    public static void main(String[] args){
        // Parsing this CSV file:

        try (BufferedReader br = new BufferedReader(new FileReader("src/sample.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String line2 = line.replaceAll("\"\"([A-Za-z0-9]+)\"\" ", "'$1'")
                                    .replaceAll("\"\"", "\"")
                                    .substring(1);
                line2 = line2.substring(0, line2.length() - 1);
                System.out.println(line2);

            }
        }catch(Exception e){

        }
    }

}
