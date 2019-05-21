package interpreter;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
        ArrayList<String> lines = new ArrayList<>();
        String curLine = reader.readLine();
        while (curLine != null) {
            lines.add(curLine);
            curLine = reader.readLine();
        }
        writer.write(Interpreter.interpretate(lines.toArray(String[]::new)));
        reader.close();
        writer.close();
    }
}
