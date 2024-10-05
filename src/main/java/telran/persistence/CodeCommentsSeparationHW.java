package telran.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeCommentsSeparationHW {
    public static void main(String[] args) throws IOException {
        // TODO - data from args[0] split to two files: args[1], args[2]
        // for sake of simplicity comments may be only on one line, like comments at
        // this file
        // /* */ cannot be
        // code ...// comment .... cannot be
        // However // may be not only at beginning of line, like this
        // args[0] - path to file containing code and comments
        // args[1] - path to file for placing only code
        // args[2] - path to file for placing only comments

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        PrintWriter writeCode = new PrintWriter(args[1]);
        PrintWriter writeComments = new PrintWriter(args[2]);

        reader.lines().forEach(line -> {
            if (isComment(line)) {
                writeComments.println(line);
            } else {
                writeCode.println(line);
            }
        });
        
        writeCode.close();
        writeComments.close();
        reader.close();
    }

    private static boolean isComment(String line) {
        return line.trim().startsWith("//");
    }
}
