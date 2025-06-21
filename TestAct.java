import Btree.BTree;

public class TestAct {
    public static void main(String[] args) {
        // Orden fijo en 5 como el ejercicio de la actividad
        BTree<Integer> btree = new BTree<>(5);

        // Insertar las claves de la actividad
        int[] claves = {100, 50, 20, 70, 10, 30, 80, 90, 200, 25, 15, 5, 65, 35, 60, 18, 93, 94};

        for (int clave : claves) {
            btree.insert(clave);
        }

        // Mostrar el contenido del B-Tree
        System.out.println("\nContenido del B-Tree:");
        System.out.println(btree);
    }
}

