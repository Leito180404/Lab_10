import java.util.Scanner;
import Btree.BTree;

public class Test2 {
    public static void main(String[] args) {
        BTree<Integer> btree = new BTree<>(5); // Orden 5 como en la actividad

        // Insertar las claves del árbol final
        int[] claves = {100, 50, 20, 70, 10, 30, 80, 90, 200, 25, 15, 5, 65, 35, 60, 18, 93, 94};

        for (int clave : claves) {
            btree.insert(clave);
        }

        System.out.println("\nÁrbol antes de eliminar:");
        System.out.println(btree);

        Scanner sc = new Scanner(System.in);
        System.out.println("\nIngresa una clave para eliminar del árbol:");
        int eliminar = sc.nextInt();

        btree.remove(eliminar);

        System.out.println("\nÁrbol después de eliminar:");
        System.out.println(btree);
        sc.close();
    }
}

