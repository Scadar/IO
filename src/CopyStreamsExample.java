import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CopyStreamsExample {
    public static void main(String[] args) throws IOException {
        try (InputStream in = new FileInputStream("test.txt");
             OutputStream out = new FileOutputStream("test-copy.txt")) {
            System.out.println(copy(in, out) + "bytes copied");
        }
        try (InputStream in = new URL("https://github.com/Scadar/IO").openStream();
            OutputStream out = new FileOutputStream("github.html")) {
            System.out.println(copy(in, out) + "bytes copied");
        }
        String str = "Some text which should be saved to file";
        try (InputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
             OutputStream out = new FileOutputStream("text.txt")) {
            System.out.println(copy(in, out) + "bytes copied");
        }
    }

    private static long copy(InputStream in, OutputStream out) throws IOException {
        long countCopied = 0;
        byte[] buffer = new byte[4096];
        while (true) {
            int read = in.read(buffer);
            if (read == -1) {
                break;
            }
            out.write(buffer, 0, read);
            countCopied += read;
        }
        out.flush();//Сброс буфера
        return countCopied;
    }
}
