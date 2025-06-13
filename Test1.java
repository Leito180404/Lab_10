import java.util.Scanner;

import Btree.BTree;
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el orden del B-Tree:");
        int orden = sc.nextInt();
        BTree<Integer> btree = new BTree<>(orden);

        // Insertar algunas claves para probar el toString
        int[] claves = {31, 12, 19, 3, 10, 13, 16, 22, 25, 28, 41, 57, 63, 33, 35, 40, 49, 52, 55, 60, 62, 67, 70, 72};
        for (int clave : claves) {
            btree.insert(clave);
        }

        // Mostrar el contenido del B-Tree
        System.out.println("\nContenido del B-Tree:");
        System.out.println(btree);
        sc.close();
    }
}

