import Excepciones.ItemNoFound;
import java.util.Scanner;

import Btree.BTree;

public class Test3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la ruta del archivo arbolB.txt:");
        String ruta = scanner.nextLine();

        try {
            BTree<Integer> arbol = BTree.building_Btree(ruta);
            System.out.println("\n\u2728 \u00c1rbol B construido con éxito desde archivo:");
            System.out.println(arbol);
        } catch (ItemNoFound e) {
            System.err.println("\n\u274C Error al construir el árbol: " + e.getMessage());
        }
        scanner.close();
    }
}

