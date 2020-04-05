import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderFileTest {
    public static void main(String[] args) {
        try {
            readLines1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readLines1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
        br.close();
    }
}
