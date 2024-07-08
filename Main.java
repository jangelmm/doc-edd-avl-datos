public class Main {
    public static void main(String[] args) {
        ArbolBalanceado arbol = new ArbolBalanceado();
        
        // Insertar nodos con Dato y Contenido
        arbol.insertar(234, "Contenido de 234");
        arbol.insertar(222, "Contenido de 222");
        arbol.insertar(100, "Contenido de 100");
        arbol.insertar(10, "Contenido de 10");
        arbol.insertar(10000, "Contenido de 10000");
        arbol.insertar(345, "Contenido de 345");
        arbol.insertar(2, "Contenido de 2");
        arbol.insertar(456, "Contenido de 456");
        arbol.insertar(756, "Contenido de 756");

        // Mostrar el tamaño del árbol
        System.out.println("\nTamaño del árbol: " + arbol.size());

        // Recorridos en diferentes órdenes
        System.out.print("\nRecorrido en InOrden:");
        arbol.inOrden();

        System.out.print("\nRecorrido en PreOrden:");
        arbol.preOrden();

        System.out.print("\nRecorrido en PosOrden:");
        arbol.posOrden();

        // Buscar un nodo
        NodoAvl encontrado = arbol.buscar(100);
        if (encontrado != null) {
            System.out.println("\nNodo encontrado: " + encontrado.getDato() + " con contenido: " + encontrado.getContenido());
        } else {
            System.out.println("\nNodo no encontrado");
        }

        // Eliminar un nodo
        boolean eliminado = arbol.eliminar(100, 1);
        System.out.println("\nNodo 100 eliminado: " + eliminado);

        // Mostrar el tamaño del árbol después de la eliminación
        System.out.println("\nTamaño del árbol después de la eliminación: " + arbol.size());

        // Recorridos en diferentes órdenes después de la eliminación
        System.out.print("\nRecorrido en InOrden después de la eliminación:");
        arbol.inOrden();

        System.out.print("\nRecorrido en PreOrden después de la eliminación:");
        arbol.preOrden();

        System.out.print("\nRecorrido en PosOrden después de la eliminación:");
        arbol.posOrden();
    }
}
