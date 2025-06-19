import Excepciones.ItemNoFound;
import java.util.*;

import Btree.BTree;

public class Test4 {
    public static void main(String[] args) {
        SistemaRegistro sistema = new SistemaRegistro();

        // Inserciones iniciales:
        sistema.agregarEstudiante(103, "Ana");
        sistema.agregarEstudiante(110, "Luis");
        sistema.agregarEstudiante(101, "Carlos");
        sistema.agregarEstudiante(120, "Lucia");
        sistema.agregarEstudiante(115, "David");
        sistema.agregarEstudiante(125, "Jorge");
        sistema.agregarEstudiante(140, "Camila");
        sistema.agregarEstudiante(108, "Rosa");
        sistema.agregarEstudiante(132, "Ernesto");
        sistema.agregarEstudiante(128, "Denis");
        sistema.agregarEstudiante(145, "Enrique");
        sistema.agregarEstudiante(122, "Karina");
        sistema.agregarEstudiante(108, "Juan"); 

        System.out.println("Arbol B de codigos:");
        sistema.imprimirArbol();

        // Operaciones de búsqueda:
        sistema.buscarNombre(115);
        sistema.buscarNombre(132);
        sistema.buscarNombre(999);

        // Eliminación:
        sistema.eliminarEstudiante(101);

        // Inserción nueva:
        sistema.agregarEstudiante(106, "Sara");
        sistema.buscarNombre(106);
    }
}

class SistemaRegistro {
    private BTree<Integer> arbolCodigos;
    private Map<Integer, String> mapaEstudiantes;

    public SistemaRegistro() {
        arbolCodigos = new BTree<>(4);
        mapaEstudiantes = new HashMap<>();
    }

    public void agregarEstudiante(int codigo, String nombre) {
        try {
            arbolCodigos.insert(codigo);
            mapaEstudiantes.put(codigo, nombre);
        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public void eliminarEstudiante(int codigo) {
        try {
            arbolCodigos.remove(codigo);
            mapaEstudiantes.remove(codigo);
            System.out.println("Eliminado estudiante con código " + codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public void buscarNombre(int codigo) {
        System.out.print("Buscando estudiante con código " + codigo + ": ");
        if (arbolCodigos.search(codigo)) {
            System.out.println(mapaEstudiantes.get(codigo));
        } else {
            System.out.println("No encontrado");
        }
    }

    public void imprimirArbol() {
        System.out.println(arbolCodigos);
    }
}



