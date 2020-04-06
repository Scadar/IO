import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CopyStreamsExample {
    public static void main(String[] args) throws IOException {
//        try (InputStream in = new FileInputStream("test.txt");
//             OutputStream out = new FileOutputStream("test-copy.txt")) {
//            System.out.println(copy(in, out) + "bytes copied");
//        }
//        try (InputStream in = new URL("https://github.com/Scadar/IO").openStream();
//            OutputStream out = new FileOutputStream("github.html")) {
//            System.out.println(copy(in, out) + "bytes copied");
//        }
//        String str = "Some text which should be saved to file";
//        try (InputStream in = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
//             OutputStream out = new FileOutputStream("text.txt")) {
//            System.out.println(copy(in, out) + "bytes copied");
//        }
//        replaceChars(new FileReader("test.txt"), new FileWriter("test-copy.txt"), "123", "000");
        List<Path> files = findText(Paths.get("."), "qwe", true);

        Objects.requireNonNull(files);
        for(Path file : files){
            System.out.println(file);
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

    private static void replaceChars(Reader in, Writer out, String inChar, String outChar) throws IOException {
        Objects.requireNonNull(in, "");
        Objects.requireNonNull(out, "");
        if(inChar == null){
            inChar = "";
        }
        if(outChar == null){
            outChar = "";
        }
        if(inChar.length() != outChar.length()){
            throw new IllegalArgumentException("length");
        }
        try(Reader r = in; Writer w = out){
            char[] buffer = new char[8192];
            while(true){
                int read = r.read(buffer);
                if(read == -1){
                    break;
                }else{
                    String str = new String(buffer, 0, read);
                    str = str.replaceAll(inChar, outChar);
                    w.write(str);
                }
            }
            w.flush();
        }
    }

    private static List<Path> findText(Path rootDir, final String textToFind, final boolean ignoreCase) throws IOException {
       Objects.requireNonNull(textToFind, "textToFind is null");
       Objects.requireNonNull(rootDir, "rootDir is null");
       if(!Files.isDirectory(rootDir)){
           throw new IllegalArgumentException("rootDir is not dir");
       }
       final List<Path> result= new ArrayList<>();
       Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>(){
           @Override
           public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
                if(fileContainsText(file, textToFind, ignoreCase)){
                    result.add(file);
                }
                return FileVisitResult.CONTINUE;
           }
       });
       return result;
    }

    private static boolean fileContainsText(Path file, String textToFind, boolean ignoreCase) throws IOException {
        String text = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        if(ignoreCase){
            text = text.toLowerCase();
            textToFind = textToFind.toLowerCase();
        }
        return text.contains(textToFind);
    }
}
