import java.util.*;

import Btree.BTree;

public class Test4 {
    public static void main(String[] args) {
        BTree<Integer> arbolCodigos = new BTree<>(4);
        Map<Integer, String> mapaEstudiantes = new HashMap<>();

        // Inserciones iniciales:
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 103, "Ana");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 110, "Luis");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 101, "Carlos");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 120, "Lucia");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 115, "David");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 125, "Jorge");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 140, "Camila");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 108, "Rosa");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 132, "Ernesto");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 128, "Denis");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 145, "Enrique");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 122, "Karina");
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 108, "Juan"); 

        System.out.println("Arbol B de codigos:");
        System.out.println(arbolCodigos);

        // Operaciones de búsqueda:
        buscarEstudiante(arbolCodigos, mapaEstudiantes, 115);
        buscarEstudiante(arbolCodigos, mapaEstudiantes, 132);
        buscarEstudiante(arbolCodigos, mapaEstudiantes, 999);

        // Eliminación:
        eliminarEstudiante(arbolCodigos, mapaEstudiantes, 101);

        // Inserción nueva:
        agregarEstudiante(arbolCodigos, mapaEstudiantes, 106, "Sara");
        buscarEstudiante(arbolCodigos, mapaEstudiantes, 106);
    }

    public static void agregarEstudiante(BTree<Integer> arbol, Map<Integer, String> mapa, int codigo, String nombre) {
        try {
            arbol.insert(codigo);
            mapa.put(codigo, nombre);
        } catch (Exception e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public static void buscarEstudiante(BTree<Integer> arbol, Map<Integer, String> mapa, int codigo) {
        System.out.print("Buscando estudiante con código " + codigo + ": ");
        if (arbol.search(codigo)) {
            System.out.println(mapa.get(codigo));
        } else {
            System.out.println("No encontrado");
        }
    }

    public static void eliminarEstudiante(BTree<Integer> arbol, Map<Integer, String> mapa, int codigo) {
        try {
            arbol.remove(codigo);
            mapa.remove(codigo);
            System.out.println("Eliminado estudiante con código " + codigo);
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}


