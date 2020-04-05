import java.io.*;

public class ReaderFileTest {
    public static void main(String[] args) {

        try {
            readLines1();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readLines1() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static void readLines2() throws IOException {
        StringWriter wr = new StringWriter();
        char[] cbuf = new char[8192];
        Reader r = new FileReader("test.txt");
        while (true) {
            int read = r.read(cbuf);
            if (read == -1) {
                break;
            }
            wr.write(cbuf, 0, read);
        }
        System.out.println(wr.toString());
        wr.close();
    }


    private static void readLines3() throws IOException {
        StringBuilder res = new StringBuilder();
        char[] cbuf = new char[8192];

        Reader r = new FileReader("test.txt");
        while (true) {
            int read = r.read(cbuf);
            if (read == -1) {
                break;
            }
            res.append(new String(cbuf, 0, read));
        }
        System.out.println(res.toString());
        r.close();
    }

    private static void readLines4() throws IOException {
        StringBuilder res = new StringBuilder();
        Reader r = new FileReader("test.txt");
        while (true) {
            int symbol = r.read();
            if (symbol == -1) {
                break;
            }
            if (symbol == ' ') {
                res.append('\n');
            } else {
                res.append((char) symbol);
            }
        }
        System.out.println(res.toString());
        r.close();
    }
}
