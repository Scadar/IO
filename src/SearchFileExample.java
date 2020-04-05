import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchFileExample {
    public static void main(String[] args) {
        List<File> foundFiles = new ArrayList<>();
        File rootDir = new File(".");
        findFiles(foundFiles, rootDir, ".java");
        for (File file : foundFiles) {
            System.out.println(file);
        }
    }

    private static void findFiles(List<File> foundFiles, File rootDir, String extension) {
        if (rootDir.isDirectory()) {
            for (File file : Objects.requireNonNull(rootDir.listFiles())) {
                if (file.isDirectory()) {
                    findFiles(foundFiles, file, extension);
                } else if (file.isFile()) {
                    if (file.getName().endsWith(extension)) {
                        foundFiles.add(file);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("rootDir should be directory: " + rootDir);
        }
    }
}
