package br.com.amil;

import br.com.amil.reader.InterpreterException;
import br.com.amil.reader.Interpreter;

import java.io.*;

public class Main {

    public static void main(String... args) throws IOException {
        Interpreter interpreter = new Interpreter();
        try (InputStream in = new FileInputStream(new File("log.txt"));
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                interpreter.processLine(line);
            }
            interpreter.print();
        } catch (InterpreterException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
