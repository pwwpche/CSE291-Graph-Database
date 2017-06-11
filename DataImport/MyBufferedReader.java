package DataImport;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by liuche on 6/9/17.
 */
public class MyBufferedReader {
    private FileReader ifs;
    private CharBuffer buffer;
    boolean first = true;
    boolean done = false;
    public MyBufferedReader(String fileName) throws IOException {
        ifs = new FileReader(fileName);
        buffer = CharBuffer.allocate(51200);
        ifs.read(buffer);
        buffer.flip();
    }

    public String readLine() throws IOException {
        if(done){
            return null;
        }
        int c;

        StringBuilder tempStr = new StringBuilder();
        StringBuilder line = new StringBuilder();
        do{
            while (buffer.hasRemaining()) {
                c = buffer.get();
                line.append(((char) c));
                tempStr.append(((char) c));
                if(tempStr.length() > 6){
                    tempStr.deleteCharAt(0);
                }
                String l;
                if(tempStr.toString().equals("{\"row\"")){
                    if(first){
                        first = false;
                        line = new StringBuilder();
                        continue;
                    }
                    int end = line.lastIndexOf("}]}") + 3;
                    l = "{\"row\"" + line.toString().substring(0, end);
                    return l;
                }
            }
            buffer.flip();
            if(ifs.read(buffer) < 0){
                if(line.length() > 0){
                    if(line.toString().trim().endsWith("\"errors\":[]}")){
                        String res = "{\"row\":" + line.toString().substring(1, line.lastIndexOf("\"errors\":[]}") - 4);
                        done = true;
                        return res;
                    }
                    return "{\"row\"" + line.toString();
                }else{
                    return  null;
                }
            }else{
                buffer.flip();
            }
        } while(true);

    }

    public void close() throws IOException {
        ifs.close();
        buffer.clear();
    }
}
