import java.io.*;

public class ReaderFileTest {
    public static void main(String[] args) {
        try {
            readLines2();
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

    private static void readLines2() throws IOException {
        StringWriter wr = new StringWriter();
        char[] cbuf = new char[8192];
        Reader r = new FileReader("test.txt");
        while(true){
            int read = r.read(cbuf);
            if(read == -1){
                break;
            }
            wr.write(cbuf, 0, read);
        }
        System.out.println(wr.toString());
        wr.close();
    }

}
