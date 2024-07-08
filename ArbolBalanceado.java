public class ArbolBalanceado {
    private NodoAvl raiz;
    private int cont;

    public ArbolBalanceado() {
        raiz = null;
    }

    public NodoAvl buscar(Object x) {
        NodoAvl aux = raiz;
        while (aux != null && !aux.getDato().equals(x)) {
            if (((Comparable) aux.getDato()).compareTo(x) > 0) {
                aux = aux.getIzquierda();
            } else {
                aux = aux.getDerecha();
            }
        }
        return aux;
    }

    // Método para insertar un nodo AVL
    private NodoAvl insertarAVL(NodoAvl nuevo, NodoAvl subAr) {
        NodoAvl nuevoPadre = subAr;
        if (((Comparable) nuevo.getDato()).compareTo(subAr.getDato()) < 0) {
            if (subAr.getIzquierda() == null) {
                subAr.setIzquierda(nuevo);
            } else {
                subAr.setIzquierda(insertarAVL(nuevo, subAr.getIzquierda()));
                if ((obtenerFe(subAr.getIzquierda()) - obtenerFe(subAr.getDerecha()) == 2)) {
                    if (((Comparable) nuevo.getDato()).compareTo(subAr.getIzquierda().getDato()) < 0) {
                        nuevoPadre = rotacionIzquierda(subAr);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        } else if (((Comparable) nuevo.getDato()).compareTo(subAr.getDato()) > 0) {
            if (subAr.getDerecha() == null) {
                subAr.setDerecha(nuevo);
            } else {
                subAr.setDerecha(insertarAVL(nuevo, subAr.getDerecha()));
                if ((obtenerFe(subAr.getDerecha()) - obtenerFe(subAr.getIzquierda()) == 2)) {
                    if (((Comparable) nuevo.getDato()).compareTo(subAr.getDerecha().getDato()) > 0) {
                        nuevoPadre = rotacionDerecha(subAr);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        } else {
            System.out.println("Dato repetido");
            return null;
        }

        // Actualizar altura
        if ((subAr.getIzquierda() == null) && (subAr.getDerecha() != null)) {
            subAr.setFe(subAr.getDerecha().getFe() + 1);
        } else if ((subAr.getDerecha() == null) && (subAr.getIzquierda() != null)) {
            subAr.setFe(subAr.getIzquierda().getFe() + 1);
        } else {
            subAr.setFe(Math.max(obtenerFe(subAr.getIzquierda()), obtenerFe(subAr.getDerecha())) + 1);
        }
        return nuevoPadre;
    }

    // Método para insertar un nodo
    public boolean insertar(Object dato, Object contenido) {
        NodoAvl nuevo = new NodoAvl(dato, contenido);
        if (raiz == null) {
            raiz = nuevo;
            return true;
        }
        NodoAvl aux = insertarAVL(nuevo, raiz);
        if (aux == null) return false;
        raiz = aux;
        return true;
    }

    public void inOrden() {
        ayudaInOrden(raiz);
        System.out.println();
    }

    private void ayudaInOrden(NodoAvl aux) {
        if (aux != null) {
            ayudaInOrden(aux.getIzquierda());
            System.out.print("  " + aux.getDato());
            ayudaInOrden(aux.getDerecha());
        }
    }

    public void posOrden() {
        ayudaPosOrden(raiz);
    }

    private void ayudaPosOrden(NodoAvl aux) {
        if (aux != null) {
            ayudaPosOrden(aux.getIzquierda());
            ayudaPosOrden(aux.getDerecha());
            System.out.print("  " + aux.getDato());
        }
    }

    public void preOrden() {
        ayudaPreOrden(raiz);
    }

    private void ayudaPreOrden(NodoAvl aux) {
        if (aux != null) {
            System.out.print("  " + aux.getDato());
            ayudaPreOrden(aux.getIzquierda());
            ayudaPreOrden(aux.getDerecha());
        }
    }

    public int size() {
        return ayudaSize(raiz);
    }

    private int ayudaSize(NodoAvl aux) {
        if (aux != null) return (1 + ayudaSize(aux.getIzquierda()) + ayudaSize(aux.getDerecha()));
        return 0;
    }

    public boolean eliminar(Object o, int modo) {
        NodoAvl aux = raiz, ant = null;
        while (aux != null && !aux.getDato().equals(o)) {
            ant = aux;
            if (((Comparable) aux.getDato()).compareTo(o) > 0) {
                aux = aux.getIzquierda();
            } else {
                aux = aux.getDerecha();
            }
        }
        if (aux == null) return false;
        if (aux == raiz) raiz = borrarNodo(aux, modo);
        else {
            if (ant.getIzquierda() == aux) ant.setIzquierda(borrarNodo(aux, modo));
            else ant.setDerecha(borrarNodo(aux, modo));
        }

        if (ant == null) return true;
        if ((ant.getIzquierda() == null) && (ant.getDerecha() != null)) {
            ant.setFe(ant.getDerecha().getFe() + 1);
        } else if ((raiz.getDerecha() == null) && (ant.getIzquierda() != null)) {
            ant.setFe(ant.getIzquierda().getFe() + 1);
        } else {
            ant.setFe(Math.max(obtenerFe(ant.getIzquierda()), obtenerFe(ant.getDerecha())) + 1);
        }
        return true;
    }

    private NodoAvl borrarNodo(NodoAvl n, int modo) {
        if (n.getIzquierda() == null) {
            return n.getDerecha();
        } else {
            if (n.getDerecha() == null) {
                return n.getIzquierda();
            } else {
                NodoAvl aux = n.getDerecha(), ant = null;
                while (aux.getIzquierda() != null) {
                    ant = aux;
                    aux = aux.getIzquierda();
                }
                n.setDato(aux.getDato());
                if (modo == 1) cont++;
                if (ant != null) {
                    ant.setIzquierda(aux.getDerecha());
                    cont++;
                } else {
                    n.setDerecha(aux.getDerecha());
                }
                return n;
            }
        }
    }

    public Object getRaiz() {
        return raiz.getDato();
    }

    public int salvarInOrden(int i) {
        int x = 1;
        if (buscar(x) == null) return 0;
        do {
            salvarInOrden(raiz, x, i);
            x = 0;
        } while (this.size() > 1);
        int salvado = (int) raiz.getDato();
        raiz = null;
        return salvado;
    }

    public int salvarPosOrden(int i) {
        int x = 1;
        if (buscar(x) == null) return 0;
        do {
            salvarPosOrden(raiz, x, i);
            x = 0;
        } while (this.size() > 1);
        int salvado = (int) raiz.getDato();
        raiz = null;
        return salvado;
    }

    public int salvarPreOrden(int i) {
        int x = 1;
        if (buscar(x) == null) return 0;
        do {
            salvarPreOrden(raiz, x, i);
            x = 0;
        } while (this.size() > 1);
        int salvado = (int) raiz.getDato();
        raiz = null;
        return salvado;
    }

    private int salvarInOrden(NodoAvl aux, int x, int i) {
        if (aux != null) {
            salvarInOrden(aux.getIzquierda(), x, i);
            if (x != 0) System.out.println(aux.getDato() + "  " + aux.getContenido());
            else System.out.print(aux.getDato() + "  " + aux.getContenido() + " ");
            salvarInOrden(aux.getDerecha(), x, i);
        }
        return i;
    }

    private int salvarPosOrden(NodoAvl aux, int x, int i) {
        if (aux != null) {
            salvarPosOrden(aux.getIzquierda(), x, i);
            salvarPosOrden(aux.getDerecha(), x, i);
            if (x != 0) System.out.println(aux.getDato() + "  " + aux.getContenido());
            else System.out.print(aux.getDato() + "  " + aux.getContenido() + " ");
        }
        return i;
    }

    private int salvarPreOrden(NodoAvl aux, int x, int i) {
        if (aux != null) {
            if (x != 0) System.out.println(aux.getDato() + "  " + aux.getContenido());
            else System.out.print(aux.getDato() + "  " + aux.getContenido() + " ");
            salvarPreOrden(aux.getIzquierda(), x, i);
            salvarPreOrden(aux.getDerecha(), x, i);
        }
        return i;
    }

    private NodoAvl rotacionIzquierda(NodoAvl n) {
        NodoAvl aux = n.getIzquierda();
        n.setIzquierda(aux.getDerecha());
        aux.setDerecha(n);
        n.setFe(Math.max(obtenerFe(n.getIzquierda()), obtenerFe(n.getDerecha())) + 1);
        aux.setFe(Math.max(obtenerFe(aux.getIzquierda()), obtenerFe(aux.getDerecha())) + 1);
        return aux;
    }

    private NodoAvl rotacionDerecha(NodoAvl n) {
        NodoAvl aux = n.getDerecha();
        n.setDerecha(aux.getIzquierda());
        aux.setIzquierda(n);
        n.setFe(Math.max(obtenerFe(n.getIzquierda()), obtenerFe(n.getDerecha())) + 1);
        aux.setFe(Math.max(obtenerFe(aux.getIzquierda()), obtenerFe(aux.getDerecha())) + 1);
        return aux;
    }

    private NodoAvl rotacionDobleIzquierda(NodoAvl n) {
        n.setIzquierda(rotacionDerecha(n.getIzquierda()));
        return rotacionIzquierda(n);
    }

    private NodoAvl rotacionDobleDerecha(NodoAvl n) {
        n.setDerecha(rotacionIzquierda(n.getDerecha()));
        return rotacionDerecha(n);
    }

    private int obtenerFe(NodoAvl n) {
        if (n == null) return -1;
        return n.getFe();
    }

    public void eliminarArbol() {
        raiz = null;
    }
}
