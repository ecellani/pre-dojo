package br.com.amil.reader;

public class InterpreterException extends RuntimeException {

    public InterpreterException() {
        super();
    }

    public InterpreterException(String s) {
        super(s);
    }

    public InterpreterException(String s, Throwable t) {
        super(s, t);
    }
}
