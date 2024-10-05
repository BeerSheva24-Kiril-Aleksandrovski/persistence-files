package telran.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CodeCommentsSeparationPlus {
    public static void main(String[] args) throws IOException {
        // TODO - data from args[0] split to two files: args[1], args[2]

        /*
         * Multi-line comment
         * can be
         */

        // Single-line comments also can be
        // Like this.
        // code... //comment.... and like this too.

        // args[0] - path to file containing code and comments
        // args[1] - path to file for placing only code
        // args[2] - path to file for placing only comments

        Path filePath = Paths.get(args[0]);
        String content = new String(Files.readAllBytes(filePath));

        String[] lines = content.split("\n");
        PrintWriter writeCode = new PrintWriter(args[1]);
        PrintWriter writeComments = new PrintWriter(args[2]);
        String commentRegex = "(?<!\".*)//.*";
        boolean insideBlockComment = false;

        for (String line : lines) {
            String trimmedLine = line.trim();

            if (insideBlockComment) {                       // adding all lines of block comment to comments
                writeComments.println(trimmedLine);
                if (trimmedLine.contains("*/")) {           // checking end of block comment
                    insideBlockComment = false;
                }
                continue;                                   // skiping next check because we are in block comment
            }

            if (trimmedLine.matches(".*" + commentRegex)) { // check for single line comment according regex
                int commentIndex = trimmedLine.indexOf("//");
                String codePart = trimmedLine.substring(0, commentIndex).trim();
                String commentPart = trimmedLine.substring(commentIndex).trim();
                if (!codePart.isEmpty()) {
                    writeCode.println(codePart);
                }
                writeComments.println(commentPart);
            } else if (trimmedLine.contains("/*")) {        // check for start of block comment
                writeComments.println(trimmedLine);
                insideBlockComment = true;
            } else if (!trimmedLine.isEmpty()){
                writeCode.println(trimmedLine);
            }
        }
        writeCode.close();
        writeComments.close();
    }
}