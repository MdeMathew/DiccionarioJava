import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * Implementa una lista enlazada de nodos {@link JDictItem}. Esta lista enlazada permite
 * la inserción y eliminación de nodos tanto en la cabeza como en la cola de la lista,
 * así como la búsqueda y actualización de nodos basados en su clave. Su implementación
 * esta diseñada para su uso en la clase {@link Container}, no como una lista encadenada
 * de próposito general.
 * <p>
 * La implementación soporta iteración a través de los nodos de la lista de forma secuencial.
 * </p>
 *
 * @author Carlos Mathias Osorio Rojas
 * @author Francisco Javier Monleón Peña
 * @author Tycho Quintana Santana
 * @author Néstor J. Henríquez Medina
 * @version 1.0
 * @since 03-03-2024
 */
public class MyLinkedList implements Iterable<JDictItem> {

    private JDictItem root;
    private JDictItem last;
    private int length;

    /**
     * Constructor de la clase MyLinkedList
     */
    public MyLinkedList() {
        this.root = null;
        this.last = null;
        this.length = 0;
    }

    /**
     * Obtiene el nodo raíz de la lista encadenada
     *
     * @return El nodo raíza de la lista encadendada
     */
    public JDictItem getRootNode() {
        return this.root;
    }

    /**
     * Getter del último nodo de la lista encadenada.
     *
     * @return El último nodo de la lista encadenada
     */
    public JDictItem getLastNode() {

        return this.last;

    }

    /**
     * Inserta un nodo al inicio de la lista. Si la lista está vacía, el nodo insertado
     * se convierte en la raíz de la lista.
     *
     * @param node el nodo a insertar en la lista; no debe ser {@code null}.
     * @throws NullPointerException si el nodo es {@code null}.
     */
    public void fastHeadInsert(JDictItem node) { // Revisar coincidencias del mismo value en las LE

        if (node == null) throw new NullPointerException("No se puede insertar un null como un Nodo");

        if (length == 0) {

            this.root = node;
            this.last = node;

        } else {
            JDictItem aux = this.root;
            this.root = node; // copiamos node raiz, ponemos nueva raíz, y conectamos con la anterior raíz
            node.setNextNode(aux);
            aux.setPreviousNode(node);
        }
        length++; // aumentamos longitud en 1.

    }

    /**
     * Inserta un nodo al final de la lista encadenada. Si la lista esta vacía, el nodo
     * insertado se convierte en la raíz de la lista.
     *
     * @param node El nodo a insertar al final de la lista; no debe ser {@code null}.
     * @throws NullPointerException si el nodo es {@code null}.
     */
    public void fastTailInsert(JDictItem node) {

        if (node == null) throw new NullPointerException("No se puede insertar un null como un Nodo");

        if (length == 0) {

            this.root = node;
            this.last = node;

        } else {
            this.last.setNextNode(node);
            node.setPreviousNode(this.last);
            this.last=node;

        }

        this.length++;

    }

    /**
     * Elimina el nodo pasado como parámetro de la lista encadenada.
     *
     * @param node El nodo que se quiere eliminar de la lista encadenada
     * @return true si se encontró el nodo y se elimino, false en caso de que
     * el nodo no estuviese en la lista encadenada.
     */
    public boolean fastRemoveByNode(JDictItem node) {

        if (this.length == 0) { // si está vacío
            return false;

        } else if (this.length == 1) { // si sólo hay 1 node comprobar si es el q se pide borrar
            JDictItem currentNode = this.root;
            if (currentNode.equals(node)) {
                this.root = null;
                this.last = null;
                this.length--;
                return true;
            }

            return false;

        } else { // Lista.length > 1

            JDictItem currentNode = this.root;

            if(node.equals(this.last)){
                this.last.getPreviousNode().setNextNode(null);
                this.last=this.last.getPreviousNode();
                this.length--;
                return true;
            }

            if(node.equals(this.root)){
                currentNode.getNextNode().setPreviousNode(null); // enlazamos el siguiente del borrado a (<-)null para
                // que no apunte a ninguno y se convierta en root
                this.root = currentNode.getNextNode(); // actualizamos root
                this.length--; // Actualizamos valor de length
                return true;
            }

            while ((currentNode != null) && (!node.equals(currentNode))) { // seguimos caminando hasta llegar al final o
                currentNode = currentNode.getNextNode();                   // encontrarlo
            }

            if (currentNode == null) { // no está en la lista
                return false;
            }

            // está en el medio

            currentNode.getPreviousNode().setNextNode(currentNode.getNextNode()); // enlaza el anterior con el siguiente
            // del actual

            currentNode.getNextNode().setPreviousNode(currentNode.getPreviousNode()); // enlaza el siguiente con el
            // anterior del actual

            this.length--;
            return true;
        }

    }

    /**
     * Elimina la primera ocurrencia de nodo que contenga la clave
     * pasada como parámetro
     *
     * @param key La clave que se quiere borrar
     * @return true si se ha borrado un nodo que contiene esa clave, false
     * si no se ha encontrada ningún nodo que contenga esa clave.
     */
    public <K> boolean fastRemoveNodeByKey(K key) {

        if (this.length == 0) { // si está vacío
            return false;

        } else if (this.length == 1) { // si sólo hay 1 node comprobar si es el q se pide borrar

            if (this.root.getKey().equals(key)) {
                this.root = null;
                this.last = null;
                this.length--;
                return true;
            }

            return false;

        } else { // Lista.length > 1


            if (key.equals(this.root.getKey())) {
                this.root.getNextNode().setPreviousNode(null);
                this.root=this.root.getNextNode();
                this.length--;
                return true;
            }

            if (key.equals(this.last.getKey())) {
                this.last=this.last.getPreviousNode();
                this.last.setNextNode(null);
                this.length--;
                return true;
            }

            JDictItem currentNode = this.root;

            while ((currentNode != null) && (!key.equals(currentNode.getKey()))) { // seguimos caminando hasta llegar al final o
                // encontrarlo
                currentNode = currentNode.getNextNode();

            }

            if (currentNode == null) { // no está en la lista
                return false;
            }

            // está en el medio

            currentNode.getPreviousNode().setNextNode(currentNode.getNextNode()); // enlaza el anterior con el siguiente
            // del actual

            currentNode.getNextNode().setPreviousNode(currentNode.getPreviousNode()); // enlaza el siguiente con el
            // anterior del actual

            this.length--;
            return true;
        }

    }

    /**
     * Comprueba si una clave está en la lista.
     *
     * @param key El valor de la clave a comprobar
     * @return true si existe un nodo que tenga ese valor como clave, falso
     * en caso contrario
     */
    public <K> boolean keyIsExisting(K key){

        if (length == 0) {
            return false;
        }

        if (length == 1) {
            return key.equals(this.root.getKey());
        }

        for (JDictItem n: this) {

            if (n.getKey().equals(key)) {
                return true;
            }

        }

        return false;

    }

    /**
     * Comprueba si el nodo pasado como parámetro esta en la
     * lista encadenada.
     *
     * @param node El nodo a buscar en la lista encadenada
     * @return true si se ha encontrado el nodo en la lista encadenada, o falso
     * en caso de que el nodo no se encuentre en la lista encadenada
     */
    public boolean isExisting(JDictItem node) {

        if (this.root.equals(node)) {
            return true;
        }

        JDictItem currentNode = this.root;

        while ((currentNode != null) && (!node.equals(currentNode))) { // seguimos caminando hasta llgar al final o
            // encontrarlo
            currentNode = currentNode.getNextNode();
        }

        return node.equals(currentNode);

    }

    /**
     * Actualiza el valor del primer nodo en la lista encadenada cuya clave
     * sea igual a la pasada por parámetro por el valor pasado como segundo
     * parámetro
     *
     * @param key La clave del nodo al que se le quiere cambiar el valor
     * @param newValue El nuevo valor a establecer en el nodo
     */
    public <K, V> boolean updateNode(K key, V newValue) {
        /*
         * while current.getKey != key:
         * current = current.next
         *
         * if null:
         * creamos node con key y value
         *
         * si no:
         * current.setKey = key
         */
        if (this.length == 0)
            return false;

        JDictItem currentNode = this.root;
        while (currentNode.getNextNode() != null) { // Recorremos los nodos mientras el siguiente sea diferente de null
            if (currentNode.getKey().equals(key)) { // Si las claves son iguales cambiamos el valor y retornamos true
                currentNode.setValue(newValue);
                return true;
            }
            currentNode = currentNode.getNextNode(); // Avanzamos al siguiente nodo
        }

        if (currentNode.getKey().equals(key)) { // Comprobamos el último nodo al salir del bucle
            currentNode.setValue(newValue);
            return true;
        }
        return false;
    }

    /**
     * Getter de la longuitud de la lista encadenada, que será el número
     * de nodos que hay en la misma.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Implementación del método hashCode heredado de la clase Object
     */
    @Override
    public int hashCode() {
        if (this.length == 0) {
            return Objects.hashCode(this.root);
        }
        int code = 31;
        JDictItem currentNode = this.root;
        while (currentNode.getNextNode() != null) { // Recorremos los nodos mientras el siguiente sea diferente de null
            code += currentNode.hashCode();
            currentNode = currentNode.getNextNode(); // Avanzamos al siguiente nodo
        }
        code += currentNode.hashCode(); // Añadimos hash del nodo final (O inicial si sólo hay un nodo)
        return code;
    }

    /**
     * Implementación del método iterator para permitir que
     * la lista encadenada sea iterable
     */
    @Override
    public Iterator<JDictItem> iterator() {
        return new Iterator<JDictItem>(){
            private JDictItem currentNode = root;
            @Override
            public boolean hasNext() {
                return currentNode !=null;
            }

            @Override
            public JDictItem next() {
                JDictItem nextNode = currentNode;
                currentNode = currentNode.getNextNode();
                return nextNode;
            }
        };
    }

    /**
     * Implementación del método equals heredado de la clase Object
     *
     * @param o Cualquier objeto
     * @return verdadero si el objeto pasado como parámetro es igual a la
     * lista encadenada que llama al método, falso en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;         //Comprobamos si son iguales
        if (o == null || this.getClass() != o.getClass()) return false;     //Comprobamos las clases y si son null

        MyLinkedList myNodes = (MyLinkedList) o;                //Creamos una LE de nodos

        if(length != myNodes.getLength()) {                     //Comprobamos longitudes
            return false;
        }

        if (length == 0) {
            return true;
        }

        JDictItem thisNode = this.root;                            //Nodos root de esta lista y la otra
        JDictItem otherNode = myNodes.root;

        while (thisNode.hasNext()) {                            //Bucle de comprobación nodo por nodo

            if (!thisNode.equals(otherNode)) {
                return false;
            }

            thisNode = thisNode.next();
            otherNode = otherNode.next();

        }

        return true;

    }
}



/**
 * Representa un nodo en la lista enlazada {@link MyLinkedList}. Cada nodo almacena
 * una clave y un valor, así como referencias al nodo anterior y siguiente en la lista.
 *
 * @param <K> el tipo de la clave.
 * @param <V> el tipo del valor.
 * @see MyLinkedList
 */
class JDictItem<K, V> {

    private K key;
    private V value;
    private JDictItem<K, V> nextNode; // Nodo siguiente
    private JDictItem<K, V> previousNode; // Nodo anterior

    /**
     * Constructor de la clase.
     *
     * @param key Clave del Nodo
     * @param value Valor del Nodo
     * @throws NullPointerException Cuando se intenta establecer una clave o un valor nulos
     * @throws IllegalArgumentException Cuando se intenta establecer como clave un objeto mutable
     */
    public JDictItem(K key, V value) {

        if (key == null || value == null) {
            throw new NullPointerException("La key y el value no pueden ser nulos.");
        }
        // fijarnos tipos de excepciones

        if (isMutableType(key)) {
            throw new IllegalArgumentException("La key del diccionario no puede ser un objeto mutable");
        }

        this.key = key;
        this.value = value;
        this.nextNode = null;
        this.previousNode = null;

    }

    /**
     * Método para comprobar si una clave es mutable
     *
     * @param key Clave a comprobar
     * @return Devuelve si verdadero si la clave es mutable y falso en caso contrario
     */
    private boolean isMutableType(Object key) {
        return key instanceof JDictItem<?, ?> ||
                key.getClass().isArray() ||
                key instanceof Collection ||
                key instanceof JDict ||
                key instanceof MyLinkedList;
    }


    /**
     * Getter de la clave del nodo
     *
     * @return El valor de la clave
     */
    public K getKey() {
        return key;
    }


    /**
     * Getter del valor del nodo
     *
     * @return El valor del nodo
     */
    public V getValue() {
        return value;
    }


    /**
     * Setter del valor del nodo
     *
     * @param value Nuevo valor a almacenar en el nodo
     */
    public void setValue(V value) {
        if (value == null) throw new NullPointerException("El value no pueden ser nulo");
        this.value = value;
    }


    /**
     * Getter del nodo siguiente
     *
     * @return El nodo siguiente al actual como objeto {@link JDictItem}
     */
    public JDictItem<K, V> getNextNode() {
        return nextNode;
    }


    /**
     * Setter del nodo siguiente al nodo actual
     *
     * @param nextNode El nodo a establecer como nodo siguiente.
     */
    public void setNextNode(JDictItem<K, V> nextNode) {
        this.nextNode = nextNode;
    }


    /**
     * Getter del nodo anterior al nodo actual
     *
     * @return El nodo  anterior al actual como objeto {@link JDictItem}
     */
    public JDictItem<K, V> getPreviousNode() {
        return previousNode;
    }


    /**
     * Setter del nodo anterior al nodo actual
     *
     * @param previousNode El nodo a establecer como nodo anterior
     */
    public void setPreviousNode(JDictItem<K, V> previousNode) {
        this.previousNode = previousNode;
    }


    /**
     * Método para evaluar si un objeto es igual al nodo
     *
     * @param obj Cualquier objeto
     * @return Verdadero si son iguales, falso si no lo son
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JDictItem<?, ?> node = (JDictItem<?, ?>) obj;
        return Objects.equals(key, node.key) && Objects.equals(value, node.value);

    }

    /**
     * Método para calcular la clave de hash del nodo
     *
     * @return El valor de la clave de hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    /**
     * Método para representar como cadena de texto un nodo
     *
     * @return La representación como cadena de texto del nodo
     */
    @Override
    public String toString(){
        return this.key.toString().concat(" : ").concat(this.value.toString());
    }

    /**
     * Método para comprobar si un nodo tiene un nodo siguiente
     *
     * @return Devuelve verdadero si tiene un nodo siguiente, falso en caso contrario
     */
    public boolean hasNext() {
        if (this.getNextNode() == null) return false;
        return true;
    }


    /**
     * Método para obtener el siguiente nodo
     *
     * @return El siguiente nodo
     */
    public JDictItem<K,V> next() {
        return this.getNextNode();
    }

}
