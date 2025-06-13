package Actividades.Excepciones;

public class ExceptionEmpty extends Exception {
    public ExceptionEmpty(String msg) {
        super(msg);
    }

    public ExceptionEmpty() {
        super("El B-Tree esta vacio.");
    }
}
