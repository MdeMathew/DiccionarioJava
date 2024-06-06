import org.junit.*;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Clase para pruebas unitarias para la clase MyLinkedList.
 *
 * @author Tycho Quintana Santana
 * @author Carlos Mathias Osorio Rojas
 * @author Néstor Jesús Henríquez Medina
 * @version 1.2
 * @version JUnit 4.13.1
 * @version JDK 8
 *
 */

public class TestLinkedList {

    private MyLinkedList myListEmpty;
    private MyLinkedList myListOneElement;
    private MyLinkedList myListVariousElementsNoDupes;
    private MyLinkedList myListVariousElementsWithDupes;

    private JDictItem<?, ?> node1;
    private JDictItem<?, ?> node2;
    private JDictItem<?, ?> node3;
    private JDictItem<?, ?> node4;

    /**
     * Método que avisará de la finalización de las pruebas de la clase.
     */
    @AfterClass
    public static void finishMessage() {
        System.out.println("============== Fin tests TestLinkedList ==============");
    }

    /**
     * Método que avisa del inicio de los tests de la clase.
     */
    @BeforeClass
    public static void startMessage() {
        System.out.println("========== Comienzo tests TestLinkedList =============");
    }

    /**
     * Método para inicializar listas encadenadas antes de la ejecución de
     * cada uno de los tests.
     */
    @Before
    public void setUp() {

        node1 = new JDictItem("A", 1);
        node2 = new JDictItem("B", 2);
        node3 = new JDictItem("C", 3);
        node4 = new JDictItem("D", 4);

        myListEmpty = new MyLinkedList(); // Lista vacía
        myListOneElement = new MyLinkedList(); // Lista de un Elemento
        myListOneElement.fastHeadInsert(new JDictItem("Uno", "Uno"));

        myListVariousElementsNoDupes = new MyLinkedList(); // Lista múltiples elementos sin duplicados
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Dos", "Dos"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Tres", "Tres"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Cuatro", "Cuatro"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Cinco", "Cinco"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Seis", "Seis"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Siete", "Siete"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Ocho", "Ocho"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Nueve", "Nueve"));
        myListVariousElementsNoDupes.fastHeadInsert(new JDictItem("Diez", "Diez"));

        myListVariousElementsWithDupes = new MyLinkedList(); // Lista múltiples elementos con duplicados
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Tres", "Tres"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Cuatro", "Cuatro"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Tres", "Tres"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Cuatro", "Cuatro"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));
        myListVariousElementsWithDupes.fastHeadInsert(new JDictItem("Uno", "Uno"));


    }

    /**
     * Prueba para comprobar la inserción en una lista vacía.
     */
    @Test
    public void testHeadInsertIntoEmptyList() {
        myListEmpty.fastHeadInsert(node1);
        assertEquals(1, myListEmpty.getLength());
        assertEquals(node1, myListEmpty.getRootNode());
        assertNull(node1.getNextNode());
        assertNull(node1.getPreviousNode());

        System.out.println("TestLinkedList.testHeadInsertIntoEmptyList: Ok.");
    }

    /**
     * Test que prueba que una lista vacía debería devolver nodo raíz nulo.
     */
    @Test
    public void testGetRootNodeOnEmptyList() {
        myListEmpty.fastHeadInsert(node1);
        myListEmpty.fastRemoveByNode(node1);
        assertNull("getRootNode() debería devolver null en una lista vacía.", myListEmpty.getRootNode());
        System.out.println("TestLinkedList.testGetRootNodeOnEmptyList: Ok.");
    }

    /**
     * Test que comprueba que el nodo raíz debe ser el mismo que al insertarlo en una lista vacía
     */
    @Test
    public void testGetRootNodeAfterHeadInsert() {
        myListEmpty.fastHeadInsert(node1);
        assertEquals("El nodo raíz debe ser el mismo que el insertado al inicio después de la inserción.",
                node1, myListEmpty.getRootNode());

        System.out.println("TestLinkedList.testGetRootNodeAfterHeadInsert Ok.");
    }

    /**
     * Test que comprueba que el nodo raíz debe ser el mismo cuando se inserta por el final de la lista.
     */
    @Test
    public void testGetRootNodeAfterTailInsertOnEmptyList() {
        myListEmpty.fastTailInsert(node1);
        assertEquals("El nodo raíz debe ser el mismo que el insertado al final en una lista vacía.",
                node1, myListEmpty.getRootNode());

        System.out.println("TestLinkedList.testGetRootNodeAfterTailInsertOnEmptyList: Ok.");
    }

    /**
     * Comprueba que el nodo raíz permanece igual después de inserción por el final de la lista.
     */
    @Test
    public void testGetRootNodeRemainsAfterTailInsertOnNonEmptyList() {
        myListEmpty.fastHeadInsert(node1);
        myListEmpty.fastTailInsert(node2);
        assertEquals("El nodo raíz debe permanecer igual después de una inserción al final en una lista no vacía.",
                node1, myListEmpty.getRootNode());

        System.out.println("TestLinkedList.testGetRootNodeRemainsAfterTailInsertOnNonEmptyList: Ok.");
    }

    /**
     * Comprueba que el nodo raíz ha cambiado al siguiente después de eliminarlo.
     */
    @Test
    public void testGetRootNodeAfterRemovingRootNode() {
        myListEmpty.fastHeadInsert(node1);
        myListEmpty.fastTailInsert(node2);
        myListEmpty.fastRemoveByNode(node1);
        assertEquals("El nodo raíz debe actualizarse al siguiente nodo después de eliminar el nodo raíz actual.",
                node2, myListEmpty.getRootNode());

        System.out.println("TestLinkedList.testGetRootNodeAfterRemovingRootNode: Ok.");
    }

    /**
     * Prueba que testea la inserción por delante (primera posición) de lista no vacía.
     */
    @Test
    public void testHeadInsertIntoNonEmptyList() {
        myListEmpty.fastHeadInsert(node1);
        myListEmpty.fastHeadInsert(node2);
        assertEquals(2, myListEmpty.getLength());
        assertEquals(node2, myListEmpty.getRootNode());
        assertEquals("A", myListEmpty.iterator().next().next().getKey());

        System.out.println("TestLinkedList.testHeadInsert: Ok.");
    }

    /**
     * Test que comprueba la inserción de dos nodos iguales.
     */
    @Test
    public void testHeadInsertWithEqualNodes() {
        myListEmpty.fastHeadInsert(node1);
        myListEmpty.fastHeadInsert(node1); // Insertando el mismo nodo
        myListEmpty.fastHeadInsert(node2);
        assertEquals("La lista debe permitir la inserción de nodos 'iguales'.", 3, myListEmpty.getLength());
        assertEquals("El último nodo insertado debe ser la nueva raíz.", node2, myListEmpty.getRootNode());

        System.out.println("TestLinkedList.testHeadInsertWithEqualNodes: Ok.");
    }

    /**
     * Test que comprueba la inserción de un nodo nulo en una lista vacía.
     */
    @Test(expected = NullPointerException.class)
    public void testHeadInsertNullNode() {
        System.out.println("TestLinkedList.testHeadInsertNullNode: Debe lanzar exception.");
        myListEmpty.fastHeadInsert(null);
        assertEquals("La lista debe permanecer vacía después de intentar insertar un nodo null.", 0, myListEmpty.getLength());
        assertNull("La raíz debe ser null en una lista vacía.", myListEmpty.getRootNode());


    }

    /**
     * Prueba para verificar la inserción al final de una lista encadenada vacía.
     */
    @Test
    public void testTailInsertIntoEmptyList() {
        myListEmpty.fastTailInsert(node1);
        assertEquals("La longitud de la lista debería ser 1 después de insertar.", 1, myListEmpty.getLength());
        assertEquals("El nodo insertado debería ser la raíz de la lista.", node1, myListEmpty.getRootNode());
        assertNull("El siguiente del nodo insertado debería ser null en una lista de longitud 1.", node1.getNextNode());
        assertNull("El anterior del nodo insertado debería ser null en una lista de longitud 1.", node1.getPreviousNode());

        System.out.println("TestLinkedList.testTailInsertIntoEmptyList: Ok");
    }

    /**
     * Prueba para verificar la inserción al final de la lista encadenada.
     */
    @Test
    public void testTailInsertNotEmptyList() {
        myListEmpty.fastTailInsert(node1);
        myListEmpty.fastTailInsert(node2);
        assertEquals(2, myListEmpty.getLength());
        assertEquals("B", myListEmpty.iterator().next().next().getKey());

        JDictItem lastNode = myListEmpty.getRootNode().getNextNode();
        assertEquals(node2, lastNode);

        System.out.println("TestLinkedList.testTailInsert: Ok");
    }

    /**
     * Test que comprueba el estado de los nodos después de múltiples inserciones
     */
    @Test
    public void testTailInsertMultipleNodesCheckState() {
        myListEmpty.fastTailInsert(node1);
        myListEmpty.fastTailInsert(node2);

        JDictItem firstNode = myListEmpty.getRootNode();
        JDictItem lastNode = firstNode.getNextNode();

        assertEquals("El primer nodo debería seguir siendo el mismo después de la inserción de otro nodo.", node1, firstNode);
        assertEquals("El último nodo insertado debería ser accesible como el siguiente del primer nodo.", node2, lastNode);
        assertNull(node2.getNextNode());

        System.out.println("TestLinkedList.testTailInsertMultipleNodesCheckState: Ok.");
    }

    /**
     * Test para verificar el valor de un nodo específico después de inserciones
     */
    @Test
    public void testTailInsertAndCheckSpecificValue() {
        myListEmpty.fastTailInsert(node1);
        myListEmpty.fastTailInsert(node2);

        assertEquals("La clave del primer nodo debería ser 'B' después de las inserciones.", "B", myListEmpty.iterator().next().next().getKey());

        System.out.println("TestLinkedList.testTailInsertAndCheckSpecificValue: Ok.");
    }

    /**
     * Prueba que testea la eliminación de nodos y el resultado.
     */
    @Test
    //FALLA porque getPreviousNode devuelve NULL
    public void testRemoveMyNode() {
        myListEmpty.fastHeadInsert(node1);
        myListEmpty.fastHeadInsert(node2);

        assertTrue(myListEmpty.fastRemoveByNode(node1));
        assertFalse(myListEmpty.fastRemoveByNode(node3));
        assertEquals(1, myListEmpty.getLength());

        System.out.println("TestLinkedList.testRemoveMyNode: Ok.");
    }

    /**
     * Prueba para comprobar la correcta eliminación de un nodo
     * pasado por parámetro.
     */
    @Test
    public void testRemoveNodeNotExisting() {
        myListEmpty.fastHeadInsert(node1);
        myListEmpty.fastHeadInsert(node2);
        myListEmpty.fastHeadInsert(node3);

        assertTrue(myListEmpty.fastRemoveByNode(node3));
        assertEquals(2, myListEmpty.getLength());
        assertFalse(myListEmpty.isExisting(node3));
    }

    /**
     * Prueba que testea la actualización del valor de un nodo.
     */
    @Test
    public void testUpdateNode() {
        myListEmpty.fastHeadInsert(new JDictItem<>("A", 1));
        assertTrue(myListEmpty.updateNode("A", 10));
        assertEquals(10, myListEmpty.iterator().next().getValue());
    }

    /**
     * Prueba que testea si un nodo se encuentra en una LinkedList.
     */
    @Test
    public void testIsExisting() {
        myListEmpty.fastHeadInsert(new JDictItem<>("A", 1));
        assertTrue(myListEmpty.isExisting(new JDictItem<>("A", 1)));
        assertFalse(myListEmpty.isExisting(new JDictItem<>("B", 2)));
    }

    /**
     * Prueba que testea si la longitud de una LinkedList es correcta.
     */
    @Test
    public void testLength() {
        myListEmpty.fastHeadInsert(new JDictItem<>("A", 1));
        myListEmpty.fastHeadInsert(new JDictItem<>("B", 2));
        assertEquals(2, myListEmpty.getLength());
    }

    /**
     * Prueba que comprueba que una lista sin ningún nodo añadido tiene
     * ningún nodo y longitud cero.
     */
    @Test
    public void testEmptyList() {
        assertEquals(0, myListEmpty.getLength());
        assertNull(myListEmpty.getRootNode());
        assertFalse(myListEmpty.iterator().hasNext());
    }

    /**
     * Prueba que comprueba el funcionamiento de la interfaz Iterator de la clase MyNode.
     */
    @Test
    public void testIterator() {
        myListEmpty.fastHeadInsert(new JDictItem<>("A", 1));
        myListEmpty.fastHeadInsert(new JDictItem<>("B", 2));
        myListEmpty.fastHeadInsert(new JDictItem<>("C", 3));

        StringBuilder result = new StringBuilder();
        for (JDictItem<String, Integer> node : myListEmpty) {
            result.append(node.getKey()).append("-");
        }

        assertEquals("C-B-A-", result.toString());
    }

    /**
     * Test que se encarga de comprobar el correcto funcionamiento del método
     * getLenght para una lista vacía.
     */
    @Test
    public void testGetLengthEmptyList() {
        assertEquals(myListEmpty.getLength(),0);
    }

    /**
     * Test que se encarga de comprobar el correcto funcionamiento del método
     *      * getLenght para una lista con un elemento.
     */
    @Test
    public void testGetLengthListWithOneElement() {
        assertEquals(1, myListOneElement.getLength());
    }


    /**
     * El test se encarga de comprobar el correcto funcionamiento  del método
     * getLenght para lista con múltiples tamaños
     */
    @Test
    public void testGetLengthListWithMultiplesElements() {
        assertEquals(myListVariousElementsNoDupes.getLength(), 10);
    }

    /**
     * Se encarga de comprobar que el hashCode de una lista
     * vacía se corresponde con el hashCode de null, que es el
     * valor que se ha definido para el mismo.
     */
    @Test
    public void testHashCodeEmptyList() {
        assertEquals(myListEmpty.hashCode(), 0);
    }

    /**
     * Test que comprueba que el hashCode de una lista encadenada
     * con un único elemento
     */
    @Test
    public void testHashCodeOneElementList() {
        int base = 31;
        JDictItem nodo = new JDictItem("Uno", "Uno");
        assertEquals(myListOneElement.hashCode(), 31 + nodo.hashCode());
    }

    /**
     * Test para probar que el hashCode de una lista con varios
     * elementos sin duplicados se realiza de manera correcta
     */
    @Test
    public void testHashCodeListWithMultipleElementsWithNoDupes() {
        JDictItem nodo = new JDictItem<>("Nodo", 1);
        JDictItem nodo1 = new JDictItem<>("Nodo", 2);
        JDictItem nodo2 = new JDictItem<>("Nodo", 3);
        JDictItem nodo3 = new JDictItem<>("Nodo", 4);
        JDictItem nodo4 = new JDictItem<>("Nodo", 5);
        JDictItem nodo5 = new JDictItem<>("Nodo", 6);
        JDictItem nodo6 = new JDictItem<>("Nodo", 7);
        JDictItem nodo7 = new JDictItem<>("Nodo", 8);
        JDictItem nodo8 = new JDictItem<>("Nodo", 9);
        JDictItem nodo9 = new JDictItem<>("Nodo", 10);
        JDictItem arr[] =  {nodo, nodo1, nodo2, nodo3, nodo4, nodo5, nodo6, nodo7, nodo8, nodo9};
        int base = 31;
        for (JDictItem n: arr) {
            base += n.hashCode();
            myListEmpty.fastHeadInsert(n);
        }
        assertEquals(myListEmpty.hashCode(), base);
    }

    /**
     * Test para probar que el hashCode de una lista con varios
     * elementos con duplicados se realiza de manera correcta
     */
    @Test
    public void testHashCodeWithMultiplesElemetsWithDupes() {
        JDictItem nodo = new JDictItem<>("Nodo", 1);
        JDictItem nodo1 = new JDictItem<>("Nodo", 2);
        JDictItem nodo2 = new JDictItem<>("Nodo", 1);
        JDictItem nodo3 = new JDictItem<>("Nodo89", 2);
        JDictItem nodo4 = new JDictItem<>("Nodo89", 3);
        JDictItem nodo5 = new JDictItem<>("Nodo", 6);
        JDictItem nodo6 = new JDictItem<>("Nodo22", 2);
        JDictItem nodo7 = new JDictItem<>("Nodo", 6);
        JDictItem nodo8 = new JDictItem<>("Nodo3", 9);
        JDictItem nodo9 = new JDictItem<>("Nodo22", 1);
        JDictItem arr[] =  {nodo, nodo1, nodo2, nodo3, nodo4, nodo5, nodo6, nodo7, nodo8, nodo9};
        int base = 31;
        for (JDictItem n: arr) {
            base += n.hashCode();
            myListEmpty.fastHeadInsert(n);
        }
        assertEquals(myListEmpty.hashCode(), base);
    }

    /**
     * Test para comprobar que un iterador de una lista vacía
     * no permite iterar.
     */
    @Test
    public void testIteratorWithEmptyList() {
        Iterator<JDictItem> it = myListEmpty.iterator();
        assertFalse(it.hasNext());
    }

    /**
     * Test para comprobar el correcto funcionamiento de un iterador
     * sobre una lista encadenada de un elemento. Además también comprobamos
     * su funcionamiento dentro de un bucle
     */
    @Test
    public void testIteratorWithOneElementList() {
        Iterator<JDictItem> it = myListOneElement.iterator();
        assertTrue(it.hasNext());
        assertEquals(new JDictItem("Uno", "Uno"), it.next());
        for (JDictItem N: myListOneElement) {
            assertEquals(new JDictItem("Uno", "Uno"), N);
        }
    }

    /**
     * Test que comprueba el iterador de la clase lista encadenada para una lista encadenada con
     * múliples valores que NO contiene repetidos. Además se prueba el iterador en un bucle.
     */
    @Test
    public void testIteratorWithMultipleElementsListWithNoDupes() {
        Iterator<JDictItem> it = myListVariousElementsNoDupes.iterator();
        int lenght = myListVariousElementsNoDupes.getLength();
        for (int i = 1; i <= lenght; i++) {
            assertTrue(it.hasNext());
            it.next();
        }
        JDictItem nodo1 = new JDictItem<>("Uno", "Uno");
        JDictItem nodo2 = new JDictItem<>("Dos", "Dos");
        JDictItem nodo3 = new JDictItem<>("Tres", "Tres");
        JDictItem nodo4 = new JDictItem<>("Tres", "Tres");
        JDictItem arr[] = {nodo1, nodo2, nodo3, nodo4};
        myListEmpty.fastTailInsert(nodo1);
        myListEmpty.fastTailInsert(nodo2);
        myListEmpty.fastTailInsert(nodo3);
        myListEmpty.fastTailInsert(nodo4);
        int i = 0;
        for (JDictItem N: myListEmpty) {
            assertEquals(N, arr[i]);
            i++;
        }
    }

    /**
     * Test que comprueba el iterador de la clase lista encadenada para una lista encadenada con
     * múliples valores que contiene repetidos. Además se prueba el iterador en un bucle.
     */
    @Test
    public void testIteratorWithMultipleElementsListWithDupes() {
        Iterator<JDictItem> it = myListVariousElementsWithDupes.iterator();
        int lenght = myListVariousElementsWithDupes.getLength();
        for (int i = 1; i <= lenght; i++) {
            assertTrue(it.hasNext());
            it.next();
        }
        JDictItem nodo1 = new JDictItem<>("Uno", "Uno");
        JDictItem nodo2 = new JDictItem<>("Dos", "Dos");
        JDictItem nodo3 = new JDictItem<>("Uno", "Uno");
        JDictItem nodo4 = new JDictItem<>("Dos", "Dos");
        JDictItem arr[] = {nodo1, nodo2, nodo3, nodo4};
        myListEmpty.fastTailInsert(nodo1);
        myListEmpty.fastTailInsert(nodo2);
        myListEmpty.fastTailInsert(nodo3);
        myListEmpty.fastTailInsert(nodo4);
        int i = 0;
        for (JDictItem N: myListEmpty) {
            assertEquals(N, arr[i]);
            i++;
        }
    }

    /**
     * Test que comprueba el correcto funcionamiento del método equals de una lista encadenada
     * para el caso de una lista vacía. Probamos tanto cos listas iguales como diferentes
     */
    @Test
    public void testEqualsEmptyList() {
        MyLinkedList listaIgual = new MyLinkedList();
        assertTrue(myListEmpty.equals(listaIgual));
        assertTrue(myListEmpty.equals(myListEmpty));
        assertFalse(myListEmpty.equals(myListOneElement));
        assertFalse(myListEmpty.equals(myListVariousElementsNoDupes));

    }

    /**
     * Test que comprueba el correcto funcionamiento del método equals de una lista encadenada
     * para el caso de una lista con un elemento. Probamos tanto cos listas iguales como diferentes
     */
    @Test
    public void testEqualsListWithOneElement() {
        MyLinkedList listaIgual = new MyLinkedList();
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        assertTrue(myListOneElement.equals(myListOneElement));
        assertTrue(myListOneElement.equals(listaIgual));
        assertFalse(myListOneElement.equals(myListEmpty));
        assertFalse(myListOneElement.equals(myListVariousElementsNoDupes));
    }

    /**
     * Test para comprobar el correcto funcionamiento del método equals de la clase
     * lista encadenada en el caso de una lista con múltiples elementos con duplicados
     */
    @Test
    public void testEqualsListWithMultipleElementsWithDupes() {
        MyLinkedList listaIgual = new MyLinkedList();
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Tres", "Tres"));
        listaIgual.fastHeadInsert(new JDictItem("Cuatro", "Cuatro"));
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Tres", "Tres"));
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Cuatro", "Cuatro"));
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        assertTrue(myListVariousElementsWithDupes.equals(myListVariousElementsWithDupes));
        assertTrue(myListVariousElementsWithDupes.equals(listaIgual));
        assertFalse(myListVariousElementsWithDupes.equals(myListEmpty));
        assertFalse(myListVariousElementsWithDupes.equals(myListOneElement));
        assertFalse(myListVariousElementsWithDupes.equals(myListVariousElementsNoDupes));
    }

    /**
     * Test para comprobar el correcto funcionamiento del método equals de la clase
     * lista encadenada en el caso de una lista con múltiples elementos sin duplicados
     */
    @Test
    public void testEqualsWithMultipleElementsWithNoDupes() {
        MyLinkedList listaIgual = new MyLinkedList();
        listaIgual.fastHeadInsert(new JDictItem("Uno", "Uno"));
        listaIgual.fastHeadInsert(new JDictItem("Dos", "Dos"));
        listaIgual.fastHeadInsert(new JDictItem("Tres", "Tres"));
        listaIgual.fastHeadInsert(new JDictItem("Cuatro", "Cuatro"));
        listaIgual.fastHeadInsert(new JDictItem("Cinco", "Cinco"));
        listaIgual.fastHeadInsert(new JDictItem("Seis", "Seis"));
        listaIgual.fastHeadInsert(new JDictItem("Siete", "Siete"));
        listaIgual.fastHeadInsert(new JDictItem("Ocho", "Ocho"));
        listaIgual.fastHeadInsert(new JDictItem("Nueve", "Nueve"));
        listaIgual.fastHeadInsert(new JDictItem("Diez", "Diez"));

        assertTrue(myListVariousElementsNoDupes.equals(myListVariousElementsNoDupes));
        assertTrue(myListVariousElementsNoDupes.equals(listaIgual));
        assertFalse(myListVariousElementsNoDupes.equals(myListEmpty));
        assertFalse(myListVariousElementsNoDupes.equals(myListOneElement));
        assertFalse(myListVariousElementsNoDupes.equals(myListVariousElementsWithDupes));
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método
     * isKeyExisting en una lista vacía
     */
    @Test
    public void testIsKeyExistingWhenKeyNoInEmptyList() {
        assertFalse(myListEmpty.keyIsExisting("Clave que no existe"));
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método
     * isKeyExisting en una lista con un elemento que no lo contiene
     */
    @Test
    public void testIsKeyExistingWhenKeyNoInListWithOneElement() {
        assertFalse(myListOneElement.keyIsExisting("Clave que no existe"));
    }

    /**
     * Prueba que compruea el correcto funcionamiento del método isKeyExisting
     * en una lista con un sólo elemento que contiene la clave
     */
    @Test
    public void testIsKeyExistingWhenKeyInListWithOneElement() {
        assertTrue(myListOneElement.keyIsExisting("Uno"));
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método isKeyExisting
     * en una lista con multiples elementos sin repetidos que no la contiene
     */
    @Test
    public void testIsKeyExistingWhenKeyNoInListWithMulipleElementsWithoutDupesList() {
        assertFalse(myListVariousElementsNoDupes.keyIsExisting("Clave que no existe"));
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método isKeyExisting
     * en una lista con mulitples elementos sin repretidos que si contiene la clave
     */
    @Test
    public void testIsKeyExistingWhenKeyInListWithMultipleElementsWithoutDupesListOnlyOneOcurrence() {
        // Valor central
        assertTrue(myListVariousElementsNoDupes.keyIsExisting("Ocho"));
        //Valor extremo inferior
        assertTrue(myListVariousElementsNoDupes.keyIsExisting("Diez"));
        //Valor extremo inferior +1
        assertTrue(myListVariousElementsNoDupes.keyIsExisting("Nueve"));
        //Valor extremo superior
        assertTrue(myListVariousElementsNoDupes.keyIsExisting("Uno"));
        // Valor extremo superior +1
        assertTrue(myListVariousElementsNoDupes.keyIsExisting("Dos"));
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método isKeyExisting
     * en una lista con multiples elementos con repetidos que no la contiene
     */
    @Test
    public void testIsKeyExistingWhenKeyNoInListWithMulipleElementsWithDupesList() {
        assertFalse(myListVariousElementsWithDupes.keyIsExisting("Clave que no existe"));
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método isKeyExisting
     * en la lista con multiples elementos con repetidos que contiene la clave
     */
    @Test
    public void testIsKeyExistingWhenKeyInListWithMultipleElementsWithDupesList() {
        // Valor central
        assertTrue(myListVariousElementsWithDupes.keyIsExisting("Cuatro"));

        // Valor extremo inferior
        assertTrue(myListVariousElementsWithDupes.keyIsExisting("Uno"));

        // Valor extremo superior
        assertTrue(myListVariousElementsWithDupes.keyIsExisting("Uno"));
    }

    /**
     * Prueba que comprueba el correcto funcionamiento
     * del método deleteNodeByKey en una lista vacía
     */
    @Test
    public void testDeleteNodeByKeyOnEmptyList() {
        int initial_length = myListEmpty.getLength();
        assertFalse(myListEmpty.fastRemoveNodeByKey("Clave que no existe"));
        assertEquals(initial_length, myListEmpty.getLength());
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método deleteNodeByKey
     * en una lista con un solo elemento que no contiene la clave
     */
    @Test
    public void testDeleteNodeByKeyOnListWithOneElementWhenKeyNotInList() {
        int initial_length = myListOneElement.getLength();
        assertFalse(myListOneElement.fastRemoveNodeByKey("Clave que no existe"));
        assertEquals(initial_length, myListOneElement.getLength());
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método deleteNodeByKey
     * en una lista con un solo elemento que contiene la clave.
     */
    @Test
    public void testDeleteNodeByKeyOnListWithOneElementWhenKeyInList() {
        int initial_length = myListOneElement.getLength();
        assertTrue(myListOneElement.fastRemoveNodeByKey("Uno"));
        assertEquals(initial_length, myListOneElement.getLength() + 1);
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método deleteNodeByKey
     * en una lista con un solo elemento que contiene la clave
     */
    @Test
    public void testDeleteNodeByKeyOnListWithMultipleElementsWithNoDupesWhenKeyNotInList() {
        int initial_length = myListVariousElementsNoDupes.getLength();
        assertFalse(myListVariousElementsNoDupes.fastRemoveNodeByKey("Clave que no existe"));
        assertEquals(initial_length, myListVariousElementsNoDupes.getLength());
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método deleteNodeByKey
     * en una lista con un multiples elementos sin repetidos que contiene la clave
     */
    @Test
    public void testDeleteNodeByKeyOnListWithMultipleElementsWithNoDupesWhenKeyInList() {
        int initial_length = myListVariousElementsNoDupes.getLength();
        // Valor central
        assertTrue(myListVariousElementsNoDupes.fastRemoveNodeByKey("Ocho"));
        //Valor extremo inferior
        assertTrue(myListVariousElementsNoDupes.fastRemoveNodeByKey("Diez"));
        //Valor extremo inferior +1
        assertTrue(myListVariousElementsNoDupes.fastRemoveNodeByKey("Nueve"));
        //Valor extremo superior
        assertTrue(myListVariousElementsNoDupes.fastRemoveNodeByKey("Uno"));
        // Valor extremo superior +1
        assertTrue(myListVariousElementsNoDupes.fastRemoveNodeByKey("Dos"));
        assertEquals(initial_length, myListVariousElementsNoDupes.getLength() + 5);
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método deleteNodeByKey
     * en una lista con multiples elementos y repetidos que no contiene la clave
     */
    @Test
    public void testDeleteNodeByKeyOnListWithMultipleElementsWithDupesWhenKeyNotInList() {
        int initial_length = myListVariousElementsWithDupes.getLength();
        assertFalse(myListVariousElementsWithDupes.fastRemoveNodeByKey("Clave que no existe"));
        assertEquals(initial_length, myListVariousElementsWithDupes.getLength());
    }

    /**
     * Prueba que comprueba el correcto funcionamiento del método deleteNodeByKey
     * en una lista con un multiples elementos con repetidos que contiene la clave
     */
    @Test
    public void testDeleteNodeByKeyOnListWithMultipleElementsWithDupesWhenKeyInList() {
        int initial_length = myListVariousElementsWithDupes.getLength();
        // Valor central
        assertTrue(myListVariousElementsWithDupes.fastRemoveNodeByKey("Cuatro"));

        // Valor extremo inferior
        assertTrue(myListVariousElementsWithDupes.fastRemoveNodeByKey("Uno"));

        // Valor extremo superior
        assertTrue(myListVariousElementsWithDupes.fastRemoveNodeByKey("Uno"));
        assertEquals(initial_length, myListVariousElementsWithDupes.getLength() + 3);
    }

    @Test
    public void testRandom() {
        MyLinkedList ll = new MyLinkedList();
        ll.fastHeadInsert(new JDictItem(1, "Hola"));
        ll.fastHeadInsert(new JDictItem(true, "Adiós"));
        assertTrue(ll.fastRemoveNodeByKey(true));
    }


    @Test
    public void testGetLastNodeFromEmptyContainer() {
        assertNull(myListEmpty.getLastNode());
    }

    @Test
    public void testGetLastNodeFromStuffedContainer() {
        assertEquals(new JDictItem("Uno", "Uno"), myListOneElement.getLastNode());
    }
}
