import java.math.BigInteger;
import java.util.*;

/**
 * Implementación en Java de un diccionario de Python. Permite
 * hacer la gran mayoría de operaciones que realiza un diccionario
 * de Python, estructurándose como una colección de {@link JDictItem}
 * que almacenan una clave y un valor.
 *
 * @author Tycho Quintana Santana
 * @author Carlos Mathias Osorio Rojas
 * @author Néstor J. Henríquez Medina
 * @author Francisco Javier Monleón Peña
 *
 * @version 1.0
 */
public class JDict implements Iterable<JDictItem> {

    private Container container;

    /**
     * Constructor que permite crear un diccionario
     */
    public JDict() {
        container = new Container();
    }

    /**
     * Constructor privado para elaborar una shallow copy con el mismo contenido
     * @param container
     */
    private JDict(Container container) {
        this.container = container;
    }

    /**
     * Añade un nuevo elemento clave-valor al diccionario. Si la clave ya existe, el valor será actualizado.
     *
     * @param key La clave asociada con el valor a añadir. No debe ser {@code null}.
     * @param value El valor a asociar con la clave dada. Puede ser {@code null}.
     */
    public <K,V> void addElement(K key, V value){
        container.addElement(key,value);
    }

    /**
     * Obtiene el valor asociado a la clave específica en el diccionario.
     *
     * @param key La clave cuyo valor asociado se desea recuperar.
     * @param <K> Tipo de la clave.
     * @param <V> Tipo del valor.
     * @return El valor asociado a la clave proporcionada.
     * @throws NoSuchElementException si la clave no se encuentra en el contenedor.
     */
    public <K,V> V getValue(K key) {
        return container.getValue(key);
    }

    /**
     * Elimina el elemento asociado con la clave especificada del diccionario.
     *
     * @param key La clave del elemento a eliminar.
     * @return {@code true} si el elemento fue eliminado exitosamente; {@code false} si la clave no se encontraba en el diccionario.
     */
    public <K> boolean deleteElement(K key) {
        return container.deleteElement(key);
    }

    /**
     * Verifica si el diccionario está vacío.
     * @return {@code true} si el diccionario no contiene elementos; {@code false} en caso contrario.
     */
    public boolean isEmpty() {
        return container.isEmpty();
    }

    /**
     * Vacía el diccionario, eliminando todos los elementos contenidos en él.
     */
    public void clear() {
        container.clear();
    }

    /**
     * Crea y devuelve una copia del diccionario (shallowcopy).
     *
     * @return Una nueva instancia del diccionario, que es una copia del objeto actual.
     */
    public JDict copy() {
        Container copyContainer = this.container.copy();
        return new JDict(copyContainer);
    }

    /**
     * Crea un diccionario a partir de un conjunto de claves
     * y un conjunto de valores pasados como dos arrays, donde
     * la n-ésima clave será emparejada con el n-ésimo valor.
     *
     * @param keys Un array que contiene las claves.
     * @param values Un array que contiene los valores.
     * @return Un nuevo objeto 'JDict' con duplas clave-valor.
     *
     * @exception IllegalArgumentException si se pasan dos arrays que no tengan la misma longitud.
     */
    public static <K,V> JDict fromKeys(K[] keys, V[] values) {
        Container newContainer = Container.fromKeys(keys, values);
        return new JDict(newContainer);
    }

    /**
     * Devuelve el valor de la clave (key) y, si no existe, inserta el valor especificado (value).
     *
     * @param key Valor de la clave.
     * @param value Valor a insertar si la clave no existe.
     * @return El valor de la clave (key) si ya existe; sino existe la clave, se devuelve value.
     */
    public <K,V,X> X setDefault(K key, V value) {
        return this.container.setDefault(key, value);
    }

    /**
     * Devuelve el valor de la clave (key) y si no se encuentra devuelve null.
     *
     * @param key El valor de la clave a buscar en el diccionario.
     * @return El valor de la clave o null si la clave no está en el diccionario.
     */
    public <K, V> V setDefault(K key) {
        return this.container.setDefault(key);
    }

    /**
     * Retorna una colección de todos los valores almacenados en el Container.
     * Utiliza valores genéricos para permitir un retorno de tipos específicos,
     * coincidiendo con el valor V almacenado.
     * @return Una colección de valores de tipo V.
     */
    public <V> Collection<V> values() {
        return container.values();
    }

    /**
     * Devuelve una lista de objetos Pair que contienen los pares clave-valor almacenados en el diccionario.
     * @return Una lista de pares clave-valor.
     */
    public Collection<Container.Pair> items() {
        return container.items();
    }

    /**
     * Método para obtener todas las claves de los elementos del JDict
     * @return Una lista de objetos los cuales son las claves de los elementos del JDict
     */
    public Object[] keys() {
        return  container.keys();
    }

    /**
     * Método que añade a un diccionario un par de clave-valor o actualiza el valor
     * de una clave si esta ya existe en el JDict
     * @param jd Un objeto de la clase Diccionario
     * @return True si consigue actualizar o añadir, False si encuentra algún error
     * @exception IllegalArgumentException cuando el contenedor pasado como parámetro está vacío
     */
    public boolean update(JDict jd) {
        return container.update(jd.container);
    }

    /**
     * Método que añade a un diccionario un par de clave-valor o actualiza el valor
     * de una clave si esta ya existe en el JDict
     * @param iterable Un Array de Arrays de objetos
     * @return True si consigue actualizar o añadir, False si encuentra algún error
     */
    public boolean update(Object[][] iterable) {
        return container.update(iterable);
    }

    /**
     * Método que devuelve un array con el par de clave-valor añadido por última vez
     * @return Un Array de objetos con el último par clave-valor insertado en el JDict
     */
    public Object[] popItem() {
        return container.popItem();
    }

    /**
     * Proporciona un iterador para recorrer los elementos del JDict.
     * Este iterador comienza desde el primer elemento insertado y avanza secuencialmente.
     *
     * @return Un iterador que permite iterar a través de los elementos de tipo JDictItem.
     */
    public Iterator<JDictItem> iterator() {
        return this.container.iterator();
    }

}

/**
 * Clase Container encargada de manejar la tabla de dispersión
 * del diccionario.
 */
class Container implements Iterable<JDictItem> {
    private final BigInteger primeGenerator = BigInteger.valueOf(11);
    private int primeLength = 11;
    private static final long PRIME_LONG = 3000000019L;
    private static final int MAX_LOAD_FACTOR = 75;
    private MyLinkedList[] container;
    private MyLinkedList insertionList;
    private final Random constantGenerator;
    private int a = 0; // Inicializamos a cero para que pueda entrar en el bucle
    private int b = 0; // Inicializamos a cero para que pueda entrar en el bucle
    private int contador;
    private int contadorFactorCarga;

    /**
     * Constructor de la clase Container.
     */
    public Container() {

        container = new MyLinkedList[this.primeLength];       //array sencillo de LinkedLists
        insertionList = new MyLinkedList();
        this.contador = 0;
        this.contadorFactorCarga = 0;

        this.constantGenerator = new Random();

        while (this.a < 2 || this.b < 2) {
            this.a = this.constantGenerator.nextInt();            //a y b siempre será menores que P
            this.b = this.constantGenerator.nextInt();
        }
    }

    /**
     * Función de dispersión de la tabla de hash para calcular la
     * posición de los valores en la misma
     *
     * @param value El valor del que se quiere calcular su valor de la función de hash
     * @return El valor correspondiente al parámetro pasado tras aplicarle la función de dispersión
     * que nos dirá en que posición de la tabla va posicionado
     */
    private <V> int hashFunction(V value) {
        return  Math.abs((int)((this.a * Math.abs(value.hashCode()) + this.b) % Container.PRIME_LONG) % this.container.length);
    }

    /**
     * Comprueba si hemos llegado al factor de carga de nuestra tabla de hash.
     * @return boolean
     */
    private boolean isMaximumLoad() {
        //Si supera el 75% de la capacidad de carga
        return (this.contadorFactorCarga / container.length) * 100 >= Container.MAX_LOAD_FACTOR;
    }

    /**
     * Función que se encarga de cambiar los valores de la función de dispersión
     * si fuese necesario cambiarlos
     */
    private void changeUniversalFunctionParameters() {

        int aux_a = a;
        int aux_b = b;

        while (this.a < 2 || this.a == aux_a || this.b == aux_b || this.b < 2) {
            this.a = this.constantGenerator.nextInt();            //a y b siempre serán menores que P(long)
            this.b = this.constantGenerator.nextInt();
        }
    }

    /**
     * Añade un elemento al contenedor, o en caso de que
     * exista, actualiza su valor correspondiente.
     *
     * @param key Clave del elemento que queremos añadir
     * @param value El valor correspondiente asoicado a la clave
     * @exception NullPointerException Cuando se intenta insertar una clave
     * o valor null
     * @exception IllegalArgumentException Si se intenta añadir una clave que sea mutable.
     * Entendiéndose como mutable, cualquier Collection, JDict o MyLinkedList
     */
    public <K, V> void addElement(K key, V value) {
        //System.out.println("addElement");
        if (this.isMaximumLoad()) {                 // Estamos preguntando si se ha llegado al 80% de carga
            this.reDimension();                     // En caso afirmativo redimensionamos y remapeamos
        }

        int hashPosition = this.hashFunction(key);

        if (container[hashPosition] == null) { // No había lista en esa posición
            container[hashPosition] = new MyLinkedList();
            JDictItem tableNode = new JDictItem(key, value);
            container[hashPosition].fastHeadInsert(tableNode);
            JDictItem listNode = new JDictItem(key, value);
            this.insertionList.fastTailInsert(listNode);          // Añadimos a la lista del orden el elemento añadido
            contadorFactorCarga++;
            return;
        } else { // Si había lista en esa posición
            if (container[hashPosition].keyIsExisting(key)){            // Si existe, actualizamos

                container[hashPosition].updateNode(key, value);
                this.insertionList.updateNode(key, value);

            } else {                                                    // Si no, creamos e insertamos

                JDictItem tableNode = new JDictItem(key, value);
                container[hashPosition].fastHeadInsert(tableNode);
                JDictItem listNode = new JDictItem(key, value);
                this.insertionList.fastTailInsert(listNode);          // Añadimos a la lista del orden el elemento añadido
                contadorFactorCarga++;

            }
        }
    }

    /**
     * Inserta o actualiza un elemento con clave y valor especificados en una posición hash determinada.
     * Si no existe una lista en la posición hash, se crea una nueva y se inserta el elemento.
     * Si ya existe la lista y la clave ya está presente, se actualiza el valor.
     * De lo contrario, se añade un nuevo nodo a la lista.
     *
     * @param key La clave del elemento a insertar o actualizar.
     * @param value El valor asociado a la clave.
     * @param <K> Tipo de la clave.
     * @param <V> Tipo del valor.
     */

    private  <K, V> void redimAddElement(K key, V value) {

        int hashPosition = this.hashFunction(key);
        if (this.container[hashPosition] == null) { // No había lista en esa posición
            this.container[hashPosition] = new MyLinkedList();
            JDictItem tableNode = new JDictItem(key, value);
            this.container[hashPosition].fastHeadInsert(tableNode);
        } else { // Si había lista en esa posición
            if (this.container[hashPosition].keyIsExisting(key)){            // Si existe, actualizamos
                this.container[hashPosition].updateNode(key, value);
                return;

            } else {                                                    // Si no, creamos e insertamos
                JDictItem tableNode = new JDictItem(key, value);
                this.container[hashPosition].fastHeadInsert(tableNode);
            }
        }
    }

    /**
     * Realiza la redimensión del contenedor de datos, duplicando su tamaño y reubicando los elementos existentes.
     * Este proceso incluye cambiar los parámetros de la función hash y crear un nuevo arreglo de listas enlazadas
     * con el tamaño actualizado. Cada elemento existente es reinsertado en la nueva estructura para mantener la
     * integridad de la distribución hash.
     */
    private void reDimension() {
        //System.out.println(String.format("Tamaño de nuevo array: %d", this.primeLength));
        //2) Cambia los parámetros a y b de la función universal de dispersión de entero
        changeUniversalFunctionParameters();

        this.primeLength = this.primeLength << 1;
        //3) Creamos el nuevo contenedor
        this.container = new MyLinkedList[this.primeLength]; //Cambiamos de tabla de dispersión
        //System.out.println("Array nuevo creado");
        //5) Insertamos elementos existentes
        //System.out.println(String.format("Empezamos a insertar los elementos de nuevo"));
        for (JDictItem N: this.insertionList) {
            this.redimAddElement(N.getKey(), N.getValue()); // Usamos reAddElement, porque la insertion y el contador no hace falta modificarlos
        }
    }

    /**
     * Recupera el valor asociado a una clave específica en el contenedor.
     *
     * @param key La clave cuyo valor asociado se desea recuperar.
     * @param <K> Tipo de la clave.
     * @param <V> Tipo del valor.
     * @return El valor asociado a la clave proporcionada.
     * @throws NoSuchElementException si la clave no se encuentra en el contenedor.
     */
    public <K, V> V getValue(K key) {
        int hashValue = this.hashFunction(key);
        if (this.container[hashValue] == null) throw new NoSuchElementException("La clave proporcionada no se encuentra en el diccionario");
        for (JDictItem N: this.container[hashValue]) {
            if (N.getKey().equals(key)) return (V) N.getValue();
        }
        throw new NoSuchElementException("La clave proporcionada no se encuentra en el diccionario");
    }

    /**
     * Borra un elemento del Container dándole una clave del Container
     * @param key El valor de la clave del diccionario que se quiere eliminar
     * @return boolean true si encuentra la clave y lo borra, false en caso contrario
     */
    public <K> boolean deleteElement(K key) {
        int hashValue = this.hashFunction(key);
        if (this.container[hashValue] == null) return false;
        if (this.container[hashValue].fastRemoveNodeByKey(key)) {
            this.contadorFactorCarga--;
            this.insertionList.fastRemoveNodeByKey(key);
            return true;
        }
        return false;
    }

    /**
     * Comprueba si el diccionario está vacío.
     * @return boolean
     */
    public boolean isEmpty() {
        if (this.contadorFactorCarga == 0) return true;
        return false;
    }

    /**
     * Vacía el contenedor y restablece su información
     * interna a la que se tiene cuando se crea un
     * contenedor nuevo.
     */
    public void clear() {
        this.primeLength = 11; // Restablecemos el tamaño incial de la tabla de hash
        this.container = new MyLinkedList[this.primeLength]; // Creamos nueva tabla de hash
        this.insertionList = new MyLinkedList();
        this.contador = 0; // Restablecemos valor del contador
        this.contadorFactorCarga = 0; // Restablecemos el valor del factor de carga
        this.changeUniversalFunctionParameters(); // Aprovechamos y cambiamos a y b
    }

    /**
     * Realiza una shallow copy del diccionario que
     * llama a este método
     *
     * @return La shallow copy del diccionario
     */
    public Container copy() { // Shallow Copy
        Container copyContainer = new Container(); // Creamos un nuevo contenedor
        for (JDictItem N: this.insertionList) { // Iteramos en los nodos de la insertionList
            copyContainer.addElement(N.getKey(), N.getValue()); // Lo añadimos a la copia
        }
        return copyContainer; // Devolvemos la copia
    }

    /**
     * Crea un contenedor a partir de un conjunto de claves
     * y un conjunto de valores pasados como dos arrays, donde
     * la n-ésima clave será emparejada con el n-ésimo valor.
     *
     * @param keys Un array que contiene las claves.
     * @param values Un array que contiene los valores.
     * @return Un nuevo objeto 'Container' con duplas clave-valor.
     *
     * @exception IllegalArgumentException si se pasan dos arrays que no tengan la misma longitud.
     */
    public static <K, V> Container fromKeys(K[] keys, V[] values) { // Creo que se puede fromKey solo con
        // la lista de claves, pero poniendo null
        if (keys.length != values.length) {
            throw new IllegalArgumentException("Los array pasados deben ser del mismo tamaño");
        }
        Container result = new Container();

        if (keys.length == 0) return result;

        for (int i = 0; i < keys.length; i++) {
            result.addElement(keys[i], values[i]);
        }
        return result;
    }

    /**
     * Devuelve el valor de la clave (key) y, si no existe, inserta el valor especificado (value).
     *
     * @param key Valor de la clave.
     * @param value Valor a insertar si la clave no existe.
     * @return El valor de la clave (key) si ya existe; sino existe la clave, se devuelve value.
     */
    public <K, V, X> X setDefault(K key, V value) {
        try {
            return this.getValue(key);
        } catch (NoSuchElementException exception) {
            this.addElement(key, value);
            return (X) value;
        }
    }

    /**
     * Devuelve el valor de la clave (key) y si no se encuentra devuelve null.
     ** @param key El valor de la clave a buscar en el contenedor
     * @return El valor de la clave o null si la clave no está en el contenedor.
     */
    public <K, V> V setDefault(K key) {
        try {
            return this.getValue(key);
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    /**
     * Retorna una colección de todos los valores almacenados en el Container.
     * Utiliza valores genéricos para permitir un retorno de tipos específicos,
     * coincidiendo con el valor V almacenado.
     * @return Una colección de valores de tipo V.
     */
    public <V> Collection<V> values() {
        Collection<V> valoresDict = new ArrayList<>();
        for (JDictItem node : this.insertionList) {    // Recorremos la lista donde tenemos
            V value = (V) node.getValue();          // los elementos insertados en orden
            valoresDict.add(value);                 // y añadimos los valores <V> a un ArrayList.
        }
        return valoresDict;
    }

    /**
     * Clase para representar la dupla clave (K)-valor(V), mediante la cual podremos
     * recuperar los pares clave-valor del diccionario.
     * @param <K> el tipo de la clave, que es genérico.
     * @param <V> el tipo del valor, que es genérico.
     */
    public static class Pair <K, V>{
        private K key;
        private V value;

        /**
         * Constructor que inicializa un nuevo par con la clave y el valor especificados.
         *
         * @param key La clave del par.
         * @param value El valor asociado con la clave.
         */
        public Pair (K key, V value){
            this.key = key;
            this.value = value;
        }

        /**
         * Devuelve la clave del nodo.
         *
         * @return La clave almacenada en el nodo.
         */
        public K getKey() {
            return key;
        }

        /**
         * Devuelve el valor asociado a la clave del nodo.
         *
         * @return El valor almacenado en el nodo.
         */
        public V getValue() {
            return value;
        }

        /**
         * Comprueba si este par (self) es igual a otro objeto. Se considera que dos pares son iguales si
         * tienen la misma clave y el mismo valor.
         *
         * @param o El objeto con el que comparar este par.
         * @return true si los objetos son iguales; false en caso contrario.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;  //True si son exactamente el mismo objeto
            if (o == null || this.getClass() != o.getClass()) return false;  // False si objeto nulo o clase diferente
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) &&     // Comparo claves de ambos objetos
                    Objects.equals(value, pair.value);  // así como sus valores para establecer su igualdad o no.
        }

        /**
         * Genera el código hash para este par. El hash se calcula tanto de la clave como del valor.
         *
         * @return El código hash del par.
         */
        @Override
        public int hashCode() {                 // Método hashCode() requerido
            return Objects.hash(key, value);    // para implementar método equals().
        }

        /**
         * Devuelve una representación en cadena del par, incluyendo la clave y el valor.
         *
         * @return Una cadena que representa el par.
         */
        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    /**
     * Devuelve una lista de objetos Pair que contienen los pares clave-valor almacenados en el diccionario.
     * @return Una lista de pares clave-valor.
     */
    public Collection<Pair> items() {
        Collection<Pair> itemList = new ArrayList<>();
        for (JDictItem node : this.insertionList) {
            Pair pair = new Pair(node.getKey(), node.getValue());
            itemList.add(pair);
        }
        return itemList;
    }

    /**
     * Método para obtener todas las claves de los elementos del JDict
     * @return Una lista de objetos los cuales son las claves de los elementos del JDict
     */
    public Object[] keys(){

        Object result[] = new Object[this.insertionList.getLength()];          //Creamos array con longitud para todas las claves
        int i = -1;                                         //contador de posiciones
        for (JDictItem n: this.insertionList) {
            i=i+1;
            result[i] = n.getKey();
        }

        return result;
    }

    /**
     * Método que muestra si un objeto es iterable o no
     * @param obj Un objeto
     * @return True si es iterable y False en caso contrario
     */
    private boolean isIterable(Object obj) {

        if(obj instanceof Object[]){
            return true;
        }

        // Obtiene la clase del objeto
        Class<?> clase = obj.getClass();

        // Busca entre sus interfaces
        for (Class<?> interfaz : clase.getInterfaces()) {
            if (interfaz.equals(Iterable.class)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Método que añade a un diccionario un par de clave-valor o actualiza el valor
     * de una clave si esta ya existe en el JDict
     * @param iterable Un Array de Arrays de objetos
     * @return True si consigue actualizar o añadir, False si encuentra algún error
     */
    public boolean update(Object[][] iterable){

        if (iterable.length == 0) {
            throw new IllegalArgumentException("El parámetro debe ser un Objeto Iterable con parejas de clave-valor.");
        }

        if (!isIterable(iterable)) {
            throw new IllegalArgumentException("El parámetro debe ser un Objeto Iterable con parejas de clave-valor.");
        }

        for(Object[] o: iterable) {
            if (o.length != 2) {
                throw new IllegalArgumentException("El parámetro debe ser un Objeto Iterable con parejas de clave-valor.");
            }
            addElement(o[0], o[1]);
        }

        return true;
    }


    /**
     * Método que añade a un diccionario un par de clave-valor o actualiza el valor
     * de una clave si esta ya existe en el JDict
     * @param cont Un objeto de la clase Contenedor
     * @return True si consigue actualizar o añadir, False si encuentra algún error
     * @exception IllegalArgumentException cuando el contenedor pasado como parámetro está vacío
     */
    public boolean update(Container cont){

        if (cont.insertionList.getLength() == 0) {
            throw new IllegalArgumentException("El parámetro debe ser un Objeto Iterable con parejas de clave-valor.");
        }

        for (JDictItem item: cont) {
            this.addElement(item.getKey(),item.getValue());
        }

        return true;

    }


    /**
     * Método que devuelve un array con el par de clave-valor añadido por última vez
     * @return Un Array de objetos con el último par clave-valor insertado en el Container
     */
    public Object[] popItem() {
        JDictItem last = this.insertionList.getLastNode();
        Object[] result = new Object[2];
        result[0] = last.getKey();
        result[1] = last.getValue();
        this.deleteElement(last.getKey());
        return result;
    }

    /**
     * Proporciona un iterador para recorrer los elementos del contenedor.
     * Este iterador comienza desde el primer elemento insertado y avanza secuencialmente.
     *
     * @return Un iterador que permite iterar a través de los elementos de tipo JDictItem.
     */
    public Iterator<JDictItem> iterator(){
        return  new Iterator<JDictItem>() {

            private JDictItem currentNode = insertionList.getRootNode();
            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public JDictItem next() {
                JDictItem nextNode = currentNode;
                currentNode = currentNode.getNextNode();
                return nextNode;
            }
        };
    }


    public static void main(String[] args) {
        JDict diccionario = new JDict();
        long initialTime = System.nanoTime();
        for (int i = 0; i < 15000000; i++) {
            diccionario.addElement(i, i);
        }
        System.out.println("Tiempo tardado en nanosegundos: " + String.valueOf(System.nanoTime() - initialTime));
    }
}
