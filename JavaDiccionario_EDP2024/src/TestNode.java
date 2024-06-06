import org.junit.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Clase para pruebas unitarias para la clase MyNode.
 *
 * @author Tycho Quintana Santana
 * @author Carlos Mathias Osorio Rojas
 * @author Néstor Jesús Henríquez Medina
 * @version 1.0
 * @version JUnit 4.13.1
 * @version JDK 8
 */

public class TestNode{
    private JDictItem testNode;
    private JDictItem testNodeNextNode;
    private JDictItem testNodePreviousNode;

    /**
     * Método que avisará de la finalización de las pruebas de la clase
     */
    @AfterClass
    public static void finishMessage() {
        System.out.println("============== Fin tests TestNode ==============");
    }

    /**
     * Método que avisa del inicio de los tests de la clase.
     */
    @BeforeClass
    public static void startMessage() {
        System.out.println("========== Comienzo tests TestNode =============");
    }

    /**
     * Inicializara antes de realizar cada tests algunos objetos que
     * serán usados para realizar prueba en los mismos.
     */
    @Before
    public void init() {
        testNode = new JDictItem<>("TestBefore", 1);
        testNodeNextNode = new JDictItem<>("Next Node", 2);
        testNodePreviousNode = new JDictItem<>("Previous Node", -1);
        testNode.setNextNode(testNodeNextNode);
        testNode.setPreviousNode(testNodePreviousNode);
    }



    /**
     * Prueba de que el constructor lanza la excepción adecuada
     * cuando se intenta inicializar con alguno de sus valores a null.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorWithNull() {
        JDictItem nodeWithNullKey = new JDictItem<>(null, 1);
        JDictItem nodeWithNullValue = new JDictItem<>(1, null);
    }

    /**
     * Prueba de que se lanzan las excepciones apropiadas cuando se
     * intenta inicializar un nodo con un valor inválido para la clave
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgumentsAsKey() {
        JDictItem nodeWithAnotherNodeAsKey = new JDictItem<>(this.testNode, 1);
        JDictItem nodeWithCollectionAsKey = new JDictItem<>(new ArrayList<>(), 1);
        JDictItem nodeWithJDictAsKey = new JDictItem<>(new JDict(), 1);
        JDictItem nodeWithMyLinkedListAsKey = new JDictItem<>(new MyLinkedList(), 1);

    }

    /**
     * Prueba del constructor de la clase MyNode. Comprobaremos que la clase MyNode se puede
     * inicializar con valores de múltiples tipos diferentes tanto para las claves como los valores.
     * Además comprobaremos también los getters
     */
    @Test
    public void testConstructor() {
        String constructor = "Constructor";
        int entero1 = 1;
        int entero2 = 2;
        double doble = 100.6;
        float flotante = 2.5F;
        boolean verdadero = true;
        JDictItem constructorStringAndIntTestNode = new JDictItem<String, Integer>(constructor, entero1);
        assertEquals(constructor, constructorStringAndIntTestNode.getKey());
        assertEquals(entero1, constructorStringAndIntTestNode.getValue());

        JDictItem constructorIntAndFloatTestNode = new JDictItem<Integer, Float>(entero2, flotante);
        assertEquals(entero2, constructorIntAndFloatTestNode.getKey());
        assertEquals(flotante, constructorIntAndFloatTestNode.getValue());

        JDictItem constructorDoubleAndBoolTestNode = new JDictItem<Double, Boolean>(doble, verdadero);
        assertEquals(doble, constructorDoubleAndBoolTestNode.getKey());
        assertEquals(verdadero, constructorDoubleAndBoolTestNode.getValue());

        JDictItem constructorFloatAndIntegerTestNode = new JDictItem<Float, Integer>(flotante, entero1);
        assertEquals(flotante, constructorFloatAndIntegerTestNode.getKey());
        assertEquals(entero1, constructorFloatAndIntegerTestNode.getValue());

        JDictItem constructorDoubleAndIntegerTestNode = new JDictItem<>(doble, entero1);
        assertEquals(doble, constructorDoubleAndIntegerTestNode.getKey());
        assertEquals(entero1, constructorDoubleAndIntegerTestNode.getValue());




    }

    /**
     *  Método que comprueba la correcta obtención de la clave y valor del nodo en cuestión
     */
    @Test
    public void testGetter() {

        JDictItem<String, Integer> node = new JDictItem<>("clave", 1);
        assertEquals("clave", node.getKey());
        assertEquals(Integer.valueOf(1), node.getValue());
    }

    /**
     * Prueba encargada de comprobar que con los setter podemos cambiar el valor del nodo a tipos iguales
     * y diferentes y que no permite cambiar el valor de un Nodo a null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetter() {
        int entero1 = 1;
        int entero2 = 2;
        String cadena = "clave";

        this.testNode.setValue(100);
        this.testNodeNextNode.setValue(false);
        assertEquals(100, this.testNode.getValue());
        assertEquals(false, this.testNodeNextNode.getValue());

        String[] array = {"array", "prueba"};
        this.testNode.setValue(array);
        assertEquals(array, this.testNode.getValue());

        JDictItem<String, Integer> node = new JDictItem<>(cadena, entero1);
        node.setValue(entero2);
        assertEquals(Integer.valueOf(entero2), node.getValue());

        this.testNodePreviousNode.setValue(null);
    }

    /**
     * Prueba encargada de comprobar que con los setter podemos cambiar el valor del nodo a tipos iguales
     * y diferentes y que no permite cambiar el valor de un Nodo a null.
     */
    @Test(expected = NullPointerException.class)
    public void testValueSetter() {
        this.testNode.setValue(100);
        this.testNodeNextNode.setValue(false);
        assertEquals(100, this.testNode.getValue());
        assertEquals(false, this.testNodeNextNode.getValue());
        this.testNodePreviousNode.setValue(null);
    }

    /**
     * Prueba encargada de comprobar el correcto funcionamiento del getter
     * del campo "previousNode"
     */
    @Test
    public void checkPreviousNode() {
        assertEquals(this.testNode.getPreviousNode(), this.testNodePreviousNode);
    }

    /**
     * Prueba encargada de comprobar el correcto funcionamiento del getter
     * del campo "nextNode"
     */
    @Test
    public void checkNextNode() {
        assertEquals(this.testNode.getNextNode(), this.testNodeNextNode);
    }

    /**
     * Prueba encargada de probar que se cambiar el valor del campo "nextNode" tanto
     * a otros objetos como a null.
     */
    @Test
    public void setNextNodeTest() {
        JDictItem newNode = new JDictItem("Nuevo nodo", 2);
        this.testNode.setNextNode(newNode);
        this.testNodeNextNode.setNextNode(null);
        assertEquals(this.testNode.getNextNode(), newNode);
        assertNull(this.testNodeNextNode.getNextNode());
    }

    /**
     * Prueba encargada de probar que se cambia el valor del campo "previousNode" tanto
     * a otros objetos como a null.
     */
    @Test
    public void setPreviousNodeTest() {
        JDictItem newNode = new JDictItem("Nuevo nodo", 2);
        this.testNode.setPreviousNode(newNode);
        this.testNodeNextNode.setPreviousNode(null);
        assertEquals(this.testNode.getPreviousNode(), newNode);
        assertNull(this.testNodeNextNode.getPreviousNode());
    }



    /**
     * Método de prueba para mostrar que dos objetos iguales de la clase nodo
     * tienen el mismo valor de hash calculado
     */
    @Test
    public void hashCodeTest() {
        assertEquals(this.testNode.hashCode(), this.testNode.hashCode());
        assertNotEquals(this.testNode.hashCode(), this.testNodeNextNode.hashCode());
    }

    /**
     * Método de prueba para mostrar que los nodos anterior y siguiente se enlazan correctamente
     */
    @Test
    public void testNodeLinking() {

        testNode.setPreviousNode(testNodePreviousNode);
        testNode.setNextNode(testNodeNextNode);

        assertEquals("El nodo actual no tiene el nodo anterior correcto", testNodePreviousNode, testNode.getPreviousNode());
        assertEquals("El nodo actual no tiene el nodo siguiente correcto", testNodeNextNode, testNode.getNextNode());
    }

    /**
     * Método que comprueba si dos nodos con la misma clave y valor son iguales
     */
    @Test
    public void testEqualsSameKeyAndValue() {
        JDictItem node1 = new JDictItem<>("clave", 123);
        JDictItem node2 = new JDictItem<>("clave", 123);
        JDictItem equalNode = new JDictItem<>("TestBefore", 1);

        assertTrue(this.testNode.equals(equalNode));
        assertTrue("Dos nodos con la misma clave y valor deberían ser iguales", node1.equals(node2));
    }

    /**
     * Método que comprueba si dos nodos con diferente clave y mismo valor son iguales
     */
    @Test
    public void testEqualsDifferentKeySameValue() {
        JDictItem node1 = new JDictItem<>("clave1", 123);
        JDictItem node2 = new JDictItem<>("clave2", 123);
        JDictItem nonEqualNode = new JDictItem<>("Test", 1);

        assertFalse("Dos nodos con diferentes claves pero el mismo valor no deberían ser iguales", node1.equals(node2));
        assertFalse(this.testNode.equals(nonEqualNode));
    }

    /**
     * Método que comprueba si dos nodos con misma clave y diferente valor son iguales
     */
    @Test
    public void testEqualsSameKeyDifferentValue() {
        JDictItem node1 = new JDictItem<>("clave", 123);
        JDictItem node2 = new JDictItem<>("clave", 456);
        JDictItem nonEqualNode2 = new JDictItem<>("TestBefore", 2);

        assertFalse("Dos nodos con misma clave pero mismo valor no deberían ser iguales", node1.equals(node2));
        assertFalse(this.testNode.equals(nonEqualNode2));

    }

    /**
     * Método que comprueba que un nodo es igual a sí mismo. Propiedad reflexiva.
     */
    @Test
    public void testEqualsWithSelf() {
        JDictItem node1 = new JDictItem<>("clave", 123);

        assertTrue("Un nodo debería ser igual a sí mismo", node1.equals(node1));
    }

    /**
     * Método que comprueba que un nodo no se asigna a un tipo de dato diferente
     */
    @Test
    public void testEqualsWithDifferentType() {
        JDictItem node1 = new JDictItem<>("clave", 123);
        String notNode = "No soy un nodo";

        assertFalse("Un nodo no debería ser igual a un objeto de un tipo diferente", node1.equals(notNode));
    }

    /**
     * Método que comprueba que un nodo no sea nulo
     */
    @Test
    public void testEqualsWithNull() {
        JDictItem node1 = new JDictItem<>("clave", 123);

        assertFalse("Un nodo no debería ser igual a null", node1.equals(null));

    }

    /**
     * Método que comprueba si dos nodos iguales (misma clave, mismo valor) tienen el mismo código hash
     */
    @Test
    public void testHashCodeConsistencyWithEquals() {
        JDictItem node1 = new JDictItem<>("clave", 123);
        JDictItem node2 = new JDictItem<>("clave", 123);

        assertTrue("Dos nodos iguales deben tener el mismo código hash", node1.equals(node2) && (node1.hashCode() == node2.hashCode()));
    }

    /**
     * Método que comprueba si dos nodos diferentes (diferente clave, diferente valor) tienen diferente código hash
     */
    @Test
    public void testHashCodeUniqueForDifferentObjects() {
        JDictItem node1 = new JDictItem<>("clave1", 123);
        JDictItem node2 = new JDictItem<>("clave2", 456);

        assertFalse("Dos nodos diferentes no deben tener el mismo código hash", node1.hashCode() == node2.hashCode());
    }

    /**
     * Método que comprueba la consistencia entre 'equals' y 'hashCode'
     */
    @Test
    public void testEqualsAndHashCodeConsistency() {
        JDictItem<String, Integer> node1        = new JDictItem<>("clave", 123);
        JDictItem<String, Integer> node1Copy    = new JDictItem<>("clave", 123);
        JDictItem<String, Integer> node2        = new JDictItem<>("clave", 456);

        // Consistencia de equals
        assertEquals(node1, node1Copy);
        // Consistencia de hashCode con equals
        assertEquals(node1.hashCode(), node1Copy.hashCode());
        // Desigualdad asegura hashCodes potencialmente diferentes (no se garantiza)
        assertNotEquals(node1.hashCode(), node2.hashCode());

    }

    /**
     * Manejo de valores numéricos grandes, asegurando que se almacenan y recuperan correctamente sin pérdida de precisión.
     */
    @Test
    public void testHandlingLargeValues() {
        BigInteger bigKey = new BigInteger("1234567890123456789012345678901234567890");
        BigInteger bigValue = new BigInteger("9876543210987654321098765432109876543210");
        JDictItem node = new JDictItem<>(bigKey, bigValue);

        assertEquals("La clave del nodo no maneja correctamente valores grandes", bigKey, node.getKey());
        assertEquals("El valor del nodo no maneja correctamente valores grandes", bigValue, node.getValue());
    }

    /**
     * Pruebas de Estrés con Múltiples Nodos y Valores Aleatorios Grandes
     * REVISAR ESTE TEST QUE ENLACE BIEN previousNode y nextNode (debugger)
     */
    @Test
    public void testStressWithLinkedNodesAndLargeRandomValues() {
        final int numberOfNodes = 1000000;
        Random random = new Random();
        JDictItem firstNode = null;
        JDictItem previousNode = null;

        // Crear y enlazar nodos
        for (int i = 0; i < numberOfNodes; i++) {
            long randomKey = Math.abs(random.nextLong());
            long randomValue = Math.abs(random.nextLong());
            JDictItem<Long, Long> newNode = new JDictItem<>(randomKey, randomValue);
            if (i == 0) {
                firstNode = newNode; // Guardar el primer nodo
            } else {
                previousNode.setNextNode(newNode); // Enlazar el nodo anterior con el nuevo
            }
            previousNode = newNode; // Actualizar el nodo anterior
        }

        // Verificar la secuencia de enlaces
        JDictItem<Long, Long> currentNode = firstNode;
        int nodeCount = 0;
        while (currentNode != null) {
            nodeCount++;
            assertNotNull("El nodo no debería ser null", currentNode);
            currentNode = currentNode.getNextNode(); // Avanzar al siguiente nodo
        }

        assertEquals("El número de nodos enlazados debería coincidir con el número esperado", numberOfNodes, nodeCount);
    }

    /**
     * Método para comprobar el funcionamiento del método hasNext de la interfaz iterator
     * cuando si hay más elementos a devolver
     */
    @Test
    public void hasNextTestTrue() {
        assertTrue(this.testNode.hasNext());
    }

    /**
     * Método para comprobar el funcionamiento del método hasNext de la interfaz iterator
     * cuando no hay más elementos a devolver
     */
    @Test
    public void hasNextTestFalse() {
        assertFalse(this.testNodeNextNode.hasNext());
    }

    /**
     * Método para comprobar que se devuelve el nodo siguiente en la llamada al método
     * next de la interfaz iterator
     */
    @Test
    public void testNext() {
        assertEquals(this.testNodeNextNode, this.testNode.next());
    }

}
