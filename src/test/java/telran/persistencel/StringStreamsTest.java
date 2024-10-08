package telran.persistencel;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.persistence.CodeCommentsSeparationPlus;
import telran.persistence.CodeCommentsSeparationHW;

public class StringStreamsTest {
    static final String PRINT_STREAM_FILE = "printStreamFile.txt";
    private static final String PRINT_WRITER_FILE = "printWriterFile.txt";
    @Test
    // @Disabled
    void printStreamTest() throws Exception { 
        PrintStream printStream = new PrintStream(PRINT_STREAM_FILE);
        printStream.println("HELLO");
        printStream.close();
    }
    @Test 
    // @Disabled
    void printWriterTest() throws Exception {
        PrintWriter printWriter = new PrintWriter(PRINT_WRITER_FILE);
        printWriter.println("HELLO");
        printWriter.close();
    }
    @Test
    @Disabled
    void bufferedReaderTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(PRINT_WRITER_FILE));
        assertEquals("HELLO", reader.readLine());
        reader.close();
    }
    @Test
    // @Disabled
    void printingDirectoryTest() throws Exception {
        printDirectoryContent("./target",4);

        
    }
    /**
     * 
     * @param path -  path to a directory
     * @param depth -  number of been walked levels
     */
    void printDirectoryContent(String dirPath, int depth) throws IOException {
        Path rootPath = Paths.get(dirPath);
        Set<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        try {
        Files.walkFileTree(rootPath, options, depth, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attr) throws IOException {
                System.out.printf("   ".repeat(path.getNameCount() - rootPath.getNameCount()));
                System.out.printf("%s\n",path.getFileName());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attr) throws IOException {
                System.out.printf("   ".repeat(path.getNameCount() - rootPath.getNameCount())); //f fsdfsd 
                System.out.printf("%s\n",path.getFileName());
                return FileVisitResult.CONTINUE;
            }
        });
    } catch (IOException e) {
        e.printStackTrace();
    }

    //TODO
    //Dir1
        //dir11
            //file
            //dir111
        //dir12
    //Consider class Path
    //Consider class Files
    //Consider method https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Files.html#walkFileTree(java.nio.file.Path,java.util.Set,int,java.nio.file.FileVisitor)
    }

    @Test
    void CodeCommentsSeparationHWTest() throws IOException { 
        CodeCommentsSeparationHW.main(new String[] {"CCSHW.txt", "codeHW.txt", "commentsHW.txt"});
    }
    @Test
    void CodeCommentsSeparationPlusTest() throws IOException { 
        CodeCommentsSeparationPlus.main(new String[] {"CCSPlus.txt", "codePlus.txt", "commentsPlus.txt"});
    }
}