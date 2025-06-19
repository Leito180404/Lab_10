import Excepciones.ItemNoFound;
import java.util.Scanner;
import Btree.BTree;

public class Test4 {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> arbol = new BTree<>(4);

        // Inserciones iniciales:
        arbol.insert(new RegistroEstudiante(103, "Ana"));
        arbol.insert(new RegistroEstudiante(110, "Luis"));
        arbol.insert(new RegistroEstudiante(101, "Carlos"));
        arbol.insert(new RegistroEstudiante(120, "Lucia"));
        arbol.insert(new RegistroEstudiante(115, "David"));
        arbol.insert(new RegistroEstudiante(125, "Jorge"));
        arbol.insert(new RegistroEstudiante(140, "Camila"));
        arbol.insert(new RegistroEstudiante(108, "Rosa"));
        arbol.insert(new RegistroEstudiante(132, "Ernesto"));
        arbol.insert(new RegistroEstudiante(128, "Denis"));
        arbol.insert(new RegistroEstudiante(145, "Enrique"));
        arbol.insert(new RegistroEstudiante(122, "Karina"));
        arbol.insert(new RegistroEstudiante(108, "Juan")); 

        System.out.println("\nArbol construido:");
        System.out.println(arbol);

        // Operaciones:
        System.out.println("\nBuscando estudiante con codigo 115:");
        RegistroEstudiante buscado1 = buscarEstudiante(arbol, 115);
        System.out.println(buscado1 != null ? buscado1.getNombre() : "No encontrado");

        System.out.println("\nBuscando estudiante con código 132:");
        RegistroEstudiante buscado2 = buscarEstudiante(arbol, 132);
        System.out.println(buscado2 != null ? buscado2.getNombre() : "No encontrado");

        System.out.println("\nBuscando estudiante con código 999:");
        RegistroEstudiante buscado3 = buscarEstudiante(arbol, 999);
        System.out.println(buscado3 != null ? buscado3.getNombre() : "No encontrado");

        System.out.println("\nEliminando estudiante con código 101:");
        arbol.remove(new RegistroEstudiante(101, "Carlos"));

        System.out.println("\nInsertando nuevo estudiante (106, Sara):");
        arbol.insert(new RegistroEstudiante(106, "Sara"));

        System.out.println("\nBuscando estudiante con código 106:");
        RegistroEstudiante buscado4 = buscarEstudiante(arbol, 106);
        System.out.println(buscado4 != null ? buscado4.getNombre() : "No encontrado");
    }

    public static RegistroEstudiante buscarEstudiante(BTree<RegistroEstudiante> arbol, int codigo) {
        RegistroEstudiante aux = new RegistroEstudiante(codigo, "");
        boolean encontrado = arbol.search(aux);
        return encontrado ? aux : null;
    }
}

