public class NodoAvl {
    private Object Dato;
    private Object Contenido;
    private NodoAvl Izquierda;
    private NodoAvl Derecha;
    private int Fe;

    public NodoAvl() {}

    public NodoAvl(Object dato, Object contenido) {
        this.Dato = dato;
        this.Contenido = contenido;
    }

    public void setDato(Object o) {
        Dato = o;
    }

    public Object getDato() {
        return Dato;
    }

    public void setContenido(Object o) {
        Contenido = o;
    }

    public Object getContenido() {
        return Contenido;
    }

    public void setIzquierda(NodoAvl i) {
        Izquierda = i;
    }

    public NodoAvl getIzquierda() {
        return Izquierda;
    }

    public void setDerecha(NodoAvl d) {
        Derecha = d;
    }

    public NodoAvl getDerecha() {
        return Derecha;
    }

    public void setFe(int i) {
        Fe = i;
    }

    public int getFe() {
        return Fe;
    }
}
