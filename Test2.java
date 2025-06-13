import java.util.Scanner;
import Btree.BTree;

public class Test2 {
    public static void main(String[] args) {
        BTree<Integer> btree = new BTree<>(4);

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

        System.out.println("\narbol antes de eliminar:");
        System.out.println(btree);

        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngresa una clave para eliminar del arbol:");
        int eliminar = sc.nextInt();

        btree.remove(eliminar);

        System.out.println("\narbol despues de eliminar:");
        System.out.println(btree);
    }
}

