import java.util.Scanner;

import Btree.BTree;

public class Test1 {
    public static void main(String[] args) {
        BTree<Integer> btree = new BTree<>(4); // Orden 4 como en la figura 10.14

        int[] claves = {
            31,
            12, 19,
            3, 10,
            13, 16,
            22, 25, 28,
            41, 57, 63,
            33, 35, 40,
            49, 52, 55,
            60, 62,
            67, 70, 72
        };

        for (int clave : claves) {
            btree.insert(clave);
        }

        System.out.println("\nContenido del B-Tree:");
        System.out.println(btree);

        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngresa una clave para buscar en el arbol:");
        int buscar = sc.nextInt();
        boolean resultado = btree.search(buscar);

        System.out.println("Resultado: " + resultado);
        sc.close();
    }
}

