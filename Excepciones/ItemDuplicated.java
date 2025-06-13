package Excepciones;

public class ItemDuplicated extends Exception {
    public ItemDuplicated(String msg) {
        super(msg);
    }

    public ItemDuplicated() {
        super("La clave ya existe en el B-Tree");
    }
}

