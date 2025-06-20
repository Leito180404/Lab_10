package Btree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Excepciones.ItemNoFound;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void setRoot(BNode<E> root) {
        this.root = root;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1))
                    mediana = dividedNode(current, mediana, pos[0]);
                else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    private String writeTree(BNode<E> current, BNode<E> parent) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("IdNodo: ").append(current.idNode).append(" | Claves: (");
        for (int i = 0; i < current.count; i++) {
            sb.append(current.keys.get(i));
            if (i < current.count - 1) sb.append(", ");
        }
        sb.append(") | Id.Padre: ");
        sb.append(parent != null ? parent.idNode : "--");
        sb.append(" | Hijos: [");
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null)
                sb.append(current.childs.get(i).idNode);
            else
                sb.append("null");
            if (i < current.count) sb.append(", ");
        }
        sb.append("]\n");
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                sb.append(writeTree(current.childs.get(i), current));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        return writeTree(this.root, null);
    }


    //Ejercicio 1
    public boolean search(E cl) {
        return searchRecursive(this.root, cl);
    }

    private boolean searchRecursive(BNode<E> current, E cl) {
        if (current == null) return false;
        int pos = 0;

        // Buscar dentro del nodo actual
        while (pos < current.count && cl.compareTo(current.keys.get(pos)) > 0) {
            pos++;
        }

        if (pos < current.count && cl.compareTo(current.keys.get(pos)) == 0) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posicion " + pos);
            return true;
        }

        // Buscar en el hijo correspondiente
        return searchRecursive(current.childs.get(pos), cl);
    }

    //ejercicio 2

    public void remove(E cl) {
        if (!removeRecursive(this.root, cl)) {
            System.out.println("Clave no encontrada para eliminar.");
            return;
        }
        if (root.count == 0 && root.childs.get(0) != null) {
            root = root.childs.get(0); // Eliminamos raiz que no tiene nada
        } else if (root.count == 0) {
            root = null; // arbol vacio
        }
    }

    private boolean removeRecursive(BNode<E> node, E cl) {
        if (node == null) return false;
        int i = 0;

        while (i < node.count && cl.compareTo(node.keys.get(i)) > 0) i++;

        if (i < node.count && cl.compareTo(node.keys.get(i)) == 0) {
            if (node.childs.get(i) == null) { // Nodo hoja
                for (int j = i; j < node.count - 1; j++) {
                    node.keys.set(j, node.keys.get(j + 1));
                    node.childs.set(j + 1, node.childs.get(j + 2));
                }
                node.keys.set(node.count - 1, null);
                node.childs.set(node.count, null);
                node.count--;
            } else { // Nodo interno
                BNode<E> predNode = node.childs.get(i);
                while (predNode.childs.get(predNode.count) != null)
                    predNode = predNode.childs.get(predNode.count);
                E pred = predNode.keys.get(predNode.count - 1);
                node.keys.set(i, pred);
                removeRecursive(node.childs.get(i), pred);
            }
        } else {
            boolean result = removeRecursive(node.childs.get(i), cl);
            if (node.childs.get(i) != null && node.childs.get(i).count < (orden - 1) / 2) {
                fixUnderflow(node, i);
            }
            return result;
        }
        return true;
    }

    private void fixUnderflow(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        BNode<E> left = idx > 0 ? parent.childs.get(idx - 1) : null;
        BNode<E> right = idx < parent.count ? parent.childs.get(idx + 1) : null;

        if (left != null && left.count > (orden - 1) / 2) {
            // Redistribucion con hermano izquierdo
            for (int j = child.count; j > 0; j--) {
                child.keys.set(j, child.keys.get(j - 1));
                child.childs.set(j + 1, child.childs.get(j));
            }
            child.childs.set(1, child.childs.get(0));
            child.keys.set(0, parent.keys.get(idx - 1));
            child.childs.set(0, left.childs.get(left.count));
            child.count++;

            parent.keys.set(idx - 1, left.keys.get(left.count - 1));
            left.keys.set(left.count - 1, null);
            left.childs.set(left.count, null);
            left.count--;
        } else if (right != null && right.count > (orden - 1) / 2) {
            // Redistribucin con hermano derecho
            child.keys.set(child.count, parent.keys.get(idx));
            child.childs.set(child.count + 1, right.childs.get(0));
            child.count++;

            parent.keys.set(idx, right.keys.get(0));

            for (int j = 0; j < right.count - 1; j++) {
                right.keys.set(j, right.keys.get(j + 1));
                right.childs.set(j, right.childs.get(j + 1));
            }
            right.childs.set(right.count - 1, right.childs.get(right.count));
            right.keys.set(right.count - 1, null);
            right.childs.set(right.count, null);
            right.count--;
        } else {
            // Fusion
            if (left != null) {
                fuse(parent, idx - 1);
            } else if (right != null) {
                fuse(parent, idx);
            }
        }
    }

    private void fuse(BNode<E> parent, int idx) {
        BNode<E> left = parent.childs.get(idx);
        BNode<E> right = parent.childs.get(idx + 1);

        left.keys.set(left.count, parent.keys.get(idx));
        left.count++;

        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count, right.childs.get(i));
            left.count++;
        }
        left.childs.set(left.count, right.childs.get(right.count));

        for (int i = idx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    //ejercicio 3
    public static BTree<Integer> building_Btree(String filename) throws ItemNoFound {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int orden = Integer.parseInt(br.readLine().trim());
            BTree<Integer> tree = new BTree<>(orden);

            Map<Integer, BNode<Integer>> nodosPorId = new HashMap<>();
            Map<Integer, Integer> niveles = new HashMap<>();
            List<Integer> ordenArchivo = new ArrayList<>();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int nivel = Integer.parseInt(partes[0]);
                int id = Integer.parseInt(partes[1]);
                BNode<Integer> nodo = new BNode<>(orden, id);
                int i = 0;
                for (int j = 2; j < partes.length; j++) {
                    nodo.keys.set(i++, Integer.parseInt(partes[j]));
                }
                nodo.count = i;
                nodosPorId.put(id, nodo);
                niveles.put(id, nivel);
                ordenArchivo.add(id);
            }

            for (int idPadre : ordenArchivo) {
                BNode<Integer> padre = nodosPorId.get(idPadre);
                int nivelPadre = niveles.get(idPadre);

                for (int idHijo : ordenArchivo) {
                    if (niveles.get(idHijo) == nivelPadre + 1) {
                        BNode<Integer> hijo = nodosPorId.get(idHijo);
                        if (!padre.childs.contains(hijo)) {
                            for (int i = 0; i <= padre.count; i++) {
                                if (padre.childs.get(i) == null) {
                                    padre.childs.set(i, hijo);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            BNode<Integer> raiz = null;
            for (int id : ordenArchivo) {
                if (niveles.get(id) == 0) {
                    raiz = nodosPorId.get(id);
                    break;
                }
            }

            if (raiz == null) throw new ItemNoFound("No se encontro raiz para construir el arbol.");
            tree.setRoot(raiz);
            return tree;
        } catch (IOException | NumberFormatException e) {
            throw new ItemNoFound("Error al construir el arbol desde archivo: " + e.getMessage());
        }
    }
}
