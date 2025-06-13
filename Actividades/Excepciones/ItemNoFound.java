package Actividades.Excepciones;

public class ItemNoFound extends Exception {
    public ItemNoFound(String msg) {
        super(msg);
    }

    public ItemNoFound() {
        super("El dato no se encontro en el B-Tree");
    }
}
