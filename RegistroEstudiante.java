public class RegistroEstudiante implements Comparable<Object> {
    private int codigo;
    private String nombre;

    public RegistroEstudiante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    @Override
    public int compareTo(Object obj) {
        if (!(obj instanceof RegistroEstudiante)) {
            return -1;
        }
        RegistroEstudiante otro = (RegistroEstudiante) obj;
        return Integer.compare(this.codigo, otro.codigo);
    }

}
