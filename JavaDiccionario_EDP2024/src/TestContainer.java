import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


/**
 * Clase para pruebas unitarias para la clase container
 * @author Tycho Quintana Santana
 * @author Carlos Mathias Osorio Rojas
 * @author Néstor Jesús Henríquez Medina
 * @version 1.0
 * @version JUnit 4.13.1
 * @version JDK 8
 * @since 24-03-2024
 */
public class TestContainer {

    private Container emptyContainer;
    private Container oneElementContainer;
    private Container multipleElementsContainer;
    private String[] keyListEmpty;
    private String[] keyListOneKey;
    private String[] keyListMultipleKey;
    private String[] keyListDifferentSize;
    private Integer[] valueListEmpty;
    private Integer[] valueListOneValue;
    private Integer[] valueListMultipleValue;


    /**
     * Método que avisará de la finalización de los tests de la clase TestContainer
     */
    @AfterClass
    public static void finishMessage() {
        System.out.println("============== Fin tests Container ==============");
    }

    /**
     * Método que avisará del comienzo de los tests de la clase TestContainer
     */
    @BeforeClass
    public static void startMessage() {
        System.out.println("========== Comienzo tests Container =============");
    }

    /**
     * Método que inicializará los diferentes contenedores utilizados en los tests
     * cada vez que se vaya a realizar un nuevo test.
     */
    @Before
    public void setup() {

        this.emptyContainer = new Container();
        this.oneElementContainer = new Container();
        this.multipleElementsContainer = new Container();

        this.oneElementContainer.addElement("un solo", 1);

        for (int i = 0; i < 10; i++) {
            this.multipleElementsContainer.addElement(String.valueOf(i), i);
        }

        this.keyListEmpty = new String[0];
        this.keyListOneKey = new String[] {"Uno"};
        this.keyListMultipleKey = new String[] {"Uno", "Dos", "Tres", "Cuatro", "Cinco"};
        this.keyListDifferentSize = new String[] {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete"};

        this.valueListEmpty = new Integer[0];
        this.valueListOneValue = new Integer[] {1};
        this.valueListMultipleValue = new Integer[] {1, 2, 3, 4, 5};

    }

    /**
     * Comprueba que items() devuelva una colección vacía
     * cuando no hay elementos en el objeto de tipo 'Container'.
     */
    @Test
    public void testItemsEmptyContainer() {
        assertTrue("La colección de items debe estar vacía", emptyContainer.items().isEmpty());
        System.out.println("TestContainer.testItemsEmptyContainer: Ok.");
    }

    /**
     * Comprueba que items() devuelva un conjunto
     * con un solo par clave-valor en el objeto de tipo 'Container'
     */
    @Test
    public void testItemsSingleElement() {
        assertEquals("La colección de items debe contener un elemento", 1, oneElementContainer.items().size());
        assertTrue("La colección de items debe contener el par clave-valor ('un solo', 1)",
                oneElementContainer.items().contains(new Container.Pair<>("un solo", 1)));
        System.out.println("TestContainer.testItemsSingleElement: Ok.");
    }

    /**
     * Comprueba que items() retorne todos los pares clave-valor
     * cuando hay múltiples elementos en el objeto de tipo 'Container'.
     */
    @Test
    public void testItemsMultipleElements() {
        assertEquals("La colección de items debe contener 10 elementos", 10, multipleElementsContainer.items().size());
        assertTrue(multipleElementsContainer.items().contains(new Container.Pair<>("1", 1)));
        assertTrue(multipleElementsContainer.items().contains(new Container.Pair<>("2", 2)));
        assertTrue(multipleElementsContainer.items().contains(new Container.Pair<>("3", 3)));
        System.out.println("TestContainer.testItemsMultipleElements: Ok.");
    }

    /**
     * Comprueba que la colección de pares clave-valor sea consistente
     * después de eliminar un elemento del objeto de tipo 'Container'.
     */
    @Test
    public void testItemsAfterRemoval() {

        multipleElementsContainer.deleteElement("1");
        multipleElementsContainer.deleteElement("0");
        multipleElementsContainer.deleteElement("5");
        multipleElementsContainer.deleteElement("8");
        multipleElementsContainer.deleteElement("9");

        assertEquals("La colección de items debe contener 5 elementos después de la eliminación", 5,
                multipleElementsContainer.items().size());
        assertFalse("La colección de items no debe contener el par ('0', 0) después de su eliminación",
                multipleElementsContainer.items().contains(new Container.Pair<>("0", 0)));
        assertFalse("La colección de items no debe contener el par ('1', 1) después de su eliminación",
                multipleElementsContainer.items().contains(new Container.Pair<>("1", 1)));
        assertFalse("La colección de items no debe contener el par ('5', 5) después de su eliminación",
                multipleElementsContainer.items().contains(new Container.Pair<>("5", 5)));
        assertFalse("La colección de items no debe contener el par ('8', 8) después de su eliminación",
                multipleElementsContainer.items().contains(new Container.Pair<>("8", 8)));
        assertFalse("La colección de items no debe contener el par ('9', 9) después de su eliminación",
                multipleElementsContainer.items().contains(new Container.Pair<>("9", 9)));

        System.out.println("TestContainer.testItemsAfterRemoval: Ok.");
    }

    /**
     * Prueba que el método values() devuelva contenedor vacío
     * si el objeto de tipo 'Container' no contiene elementos
     */
    @Test
    public void testValuesEmptyContainer() {
        assertTrue("La lista de valores debe estar vacía", emptyContainer.values().isEmpty());
        System.out.println("TestContainer.testValuesEmptyContainer: Ok.");
    }

    /**
     * Comprueba que values() devuelva una colección con un único valor
     * cuando el objeto de tipo 'Container' tiene un solo elemento.
     */
    @Test
    public void testValuesSingleElement() {
        assertEquals("La lista de valores debe contener un elemento", 1, oneElementContainer.values().size());
        assertTrue("La lista de valores debe contener '1'", oneElementContainer.values().contains(1));
        System.out.println("TestContainer.testValuesSingleElement: Ok.");
    }

    /**
     * Comprueba que values() devuelva todos los valores
     * cuando el objeto de tipo 'Container' contiene múltiples elementos.
     */
    @Test
    public void testValuesMultipleElements() {
        assertEquals("La lista de valores debe contener 10 elementos", 10, multipleElementsContainer.values().size());
        assertTrue(multipleElementsContainer.values().contains(1));
        assertTrue(multipleElementsContainer.values().contains(2));
        assertTrue(multipleElementsContainer.values().contains(3));
        System.out.println("TestContainer.testValuesMultipleElements: Ok.");
    }

    /**
     * Comprueba que la colección de valores sea consistente
     * después de eliminar elementos del objeto de tipo 'Container'.
     */
    @Test
    public void testValuesAfterRemoval() {
        emptyContainer.addElement("Clave 1", "1");
        emptyContainer.addElement("Clave 2", "2");
        emptyContainer.deleteElement("Clave 1");

        assertEquals("La lista de valores debe contener un elemento después de la eliminación",
                1, emptyContainer.values().size());
        assertFalse("La lista de valores no debe contener el valor '1' después de su eliminación",
                emptyContainer.values().contains("1"));
        assertTrue("La lista de valores debe contener el valor '2' después de la eliminación del valor '1'",
                emptyContainer.values().contains("2"));

        System.out.println("TestContainer.testValuesAfterRemoval: Ok.");
    }




    /**
     * Prueba para comprobar el método clear de un contenedor vacío
     */
    @Test
    public void testClearEmptyContainer() {
        this.emptyContainer.clear();
        assertTrue(this.emptyContainer.isEmpty());
    }

    /**
     * Prueba para comprobar el método clear de un contenedor con un elemento
     */
    @Test
    public void testClearOneElementContainer() {
        this.oneElementContainer.clear();
        assertTrue(this.oneElementContainer.isEmpty());
    }

    /**
     * Prueba para comprobar el método clear de un contenedor multiples elementos
     */
    @Test
    public void testClearMultipleElementContainer() {
        this.multipleElementsContainer.clear();
        assertTrue(this.multipleElementsContainer.isEmpty());
    }

    /**
     * Prueba para comprobar la correcta copia de un contenedor
     * vacío
     */
    @Test
    public void testCopyMethodEmptyContainer() {
        Container copyContainer = this.emptyContainer.copy();
        assertTrue(copyContainer.isEmpty());
    }

    /**
     * Prueba para comprobar la correcta copia de un contenedor
     * con un solo elemento.
     */
    @Test
    public void testCopyOneElementContainer() {
        Container copyContainer = this.oneElementContainer.copy();
        assertFalse(copyContainer.isEmpty()); // Falta por implementar
        int entero = copyContainer.getValue("un solo");
        assertEquals(1, entero);
    }

    /**
     * Prueba para comprobar la correcta copia de un contenedor
     * con múltiples elementos
     */
    @Test
    public void testCopyMultipleElementContainer() {
        Container copyContainer = this.multipleElementsContainer.copy();
        assertFalse(copyContainer.isEmpty()); // Falta por implementar
        assertEquals(0, (int) copyContainer.getValue("0"));
        assertEquals(1, (int) copyContainer.getValue("1"));
        assertEquals(2, (int) copyContainer.getValue("2"));
        assertEquals(3,  (int)copyContainer.getValue("3"));
        assertEquals(4, (int) copyContainer.getValue("4"));
        assertEquals(5, (int) copyContainer.getValue("5"));
        assertEquals(6, (int) copyContainer.getValue("6"));
        assertEquals(7, (int) copyContainer.getValue("7"));
        assertEquals(8, (int) copyContainer.getValue("8"));
        assertEquals(9, (int) copyContainer.getValue("9"));
    }


    /**
     * Prueba para el método estático fromkeys con dos listas
     * vacías como parámetros de entrada
     */
    @Test
    public void testFromKeysWithEmptyKeys() {
        Container contenedor = Container.fromKeys(keyListEmpty, valueListEmpty);
        assertTrue(contenedor.isEmpty());
    }

    /**
     * Prueba para el método estático fromkeys con dos listas
     * cada una de un elemento
     */
    @Test
    public void testFromKeysWithOneElementList() {
        Container contenedor = Container.fromKeys(keyListOneKey, valueListOneValue);
        assertFalse(contenedor.isEmpty());
        int entero = contenedor.getValue("Uno");
        assertEquals(1, entero);
    }

    /**
     * Prueba para el método estático fromkeys con dos
     * listas con múltiples elementos
     */
    @Test
    public void testFromKeysWithMultipleElementList() {
        Container contenedor = Container.fromKeys(keyListMultipleKey, valueListMultipleValue);
        assertFalse(contenedor.isEmpty());
        for (int i = 0; i < 5; i++) {
            int entero = contenedor.getValue(this.keyListMultipleKey[i]);
            assertEquals(i+1, entero);
        }
    }

    /**
     * Prueba para comprobar el correcto funcionamineto del método
     * estático fromkeys cuando las listas pasadas como parámetros
     * tienen tamaños diferentes
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFromKeysWithDifferentSizeLists() {
        Container contenedor = Container.fromKeys(keyListDifferentSize, valueListMultipleValue);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que solo tiene clave como parámetro de entrada
     * en un diccionario vacío
     */
    @Test
    public void testSetDefaultOnlyKeysInEmptyContainer() {
        assertNull(this.emptyContainer.setDefault("Hola"));
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que solo tiene clave como parámetro de entrada
     * en un diccionario con solo una clave
     */
    @Test
    public void testSetDefaultOnlyKeysInOneElementContainer() {
        assertNull(this.oneElementContainer.setDefault("hola"));
        assertNull(this.oneElementContainer.setDefault("un solq"));
        int entero = this.oneElementContainer.setDefault("un solo");
        assertEquals(1, entero);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que solo tiene clave como parámetro de entrada
     * en un diccionario con múltiples claves
     */
    @Test
    public void testSetDefaultOnlyKeysInMultipleElementContainer() {
        assertNull(this.multipleElementsContainer.setDefault("hola"));
        assertNull(this.multipleElementsContainer.setDefault("10"));
        int entero = this.multipleElementsContainer.setDefault("1");
        assertEquals(entero, 1);
        assertNotEquals(entero, 2);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que tiene tanto clave como valor como parámetro
     * de entrada en un contenedor vacío
     */
    @Test
    public void testSetDefaultKeyAndValueInEmptyContainer() {
        int entero = this.emptyContainer.setDefault("Hola", 100);
        assertEquals(100, entero);
        entero =  this.emptyContainer.setDefault("Hola", 200);
        assertEquals(100, entero);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que tiene tanto clave como valor como parámetros
     * de entrada en un contenedor con un elemento
     */
    @Test
    public void testSetDefaultKeyAndValueInOneElementContainer() {
        int entero = this.oneElementContainer.setDefault("un solo", "adios");
        assertEquals(1, entero);
        String cadena = this.oneElementContainer.setDefault("una sola", "dos");
        assertEquals("dos", cadena);
        String cadena2 = this.oneElementContainer.setDefault("una sola", 4);
        assertEquals("dos", cadena2);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que tiene tanto clave como valor como parámetros
     * de entrada en un contenedor con múltiples elementos
     */
    @Test
    public void testSetDefaultKeyAndValueInMultipleElementsContainer() {
        int entero = this.multipleElementsContainer.setDefault("1",54);
        assertEquals(entero, 1);
        boolean value = this.multipleElementsContainer.setDefault("true", true);
        assertTrue(value);
        boolean value2 = this.multipleElementsContainer.setDefault("true", 4);
        assertTrue(value2);
    }

    // AÑADIDO POR MATHIAS

    /**
     * Test que comprueba el funcionamiento del método update con un sólo elemento para actualizar
     */
    @Test
    public void testUpdateOnce() {
        Object[][] t = {{"uno solo", "uno"}};
        boolean result = this.oneElementContainer.update(t);
        assertTrue(result);
    }

    /**
     * Test que comprueba el funcionamiento del método update con varios elementos para actualizar
     */
    @Test
    public void testUpdateMultipleTimes() {
        Object[][] t = {{"1", "uno"},{"2", "dos"}};
        boolean result = this.multipleElementsContainer.update(t);
        assertTrue(result);
    }

    /**
     * Test que obtiene la key de un Container con un sólo elemento
     */
    @Test
    public void testGetOneKey() {
        Object[] keyS = this.oneElementContainer.keys();
        assertEquals(new Object[]{"un solo"},keyS);
    }

    /**
     * Test que obtiene la key de un Container con múltiples elementos
     */
    @Test
    public void testGetMultipleKeys() {
        Object[] keyS = this.multipleElementsContainer.keys();
        assertEquals(new Object[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},keyS);
    }

    /**
     * Método que utiliza el método popItem para obtener el último elemento insertado en un JDict
     */
    @Test
    public void testPopItem() {
        Object[] result = this.multipleElementsContainer.popItem();
        assertEquals(new Object[]{"9",9},result);
    }

    /**
     * Test que comprueba que se recupera el valor dada una clave
     */
    @Test
    public void testGetValue() {
        Object value = this.multipleElementsContainer.getValue("1");
        assertEquals(1, value);
    }

    /**
     * Test para comprobar si se devuelve un valor que no existe.
     */
    @Test (expected = NoSuchElementException.class)
    public void testGetValueFromAnEmptyContainer() {
        Object value = this.emptyContainer.getValue("9");
    }

    /**
     * Test que comprueba que se borra correctamente un elemento de un Container.
     */
    @Test
    public void testDeleteElement() {
        this.multipleElementsContainer.deleteElement("2");
        Container expected = new Container();
        expected.addElement("0",0);
        expected.addElement("1",1);
        for (int i = 3; i < 10; i++) {
            expected.addElement(String.valueOf(i), i);
        }
        assertEquals(expected.values(), multipleElementsContainer.values());
        assertEquals(expected.items(), multipleElementsContainer.items());
    }

    /**
     * Comprueba que se lanza la excepción NullPointerException
     * cuando intentamos insertar una nueva clave al diccionari
     * con valor null
     */
    @Test(expected = NullPointerException.class)
    public void TestAddElementWithNullKey() {
        this.emptyContainer.addElement(null,"nul broder");
    }

    /**
     * Comprueba el funcionamiento del método deleteElement cuando
     * se le pasa una clave que no existe en el contenedor
     */
    @Test
    public void testDeleteElementNonExistingKey() {
        assertFalse(this.multipleElementsContainer.deleteElement(true));
    }

    /**
     * Test que comprueba el borrado de un elemento de un contenedor vacío.
     */
    @Test
    public void testDeleteElementInEmptyContainer() {
        assertFalse(this.emptyContainer.deleteElement(true));
    }

    /**
     * Test que lanza la excepción NullPointerException si se añade un elemento con un valor null
     */
    @Test(expected = NullPointerException.class)
    public void TestAddElementWithNullValue() {
        this.emptyContainer.addElement("Messi", null);
    }


    /**
     * Comprueba que salta la excepción pertinente
     * cuando se intenta mezclar con un diccionario que
     * tiene una clave con valor null
     */
    @Test(expected = NullPointerException.class)
    public void TestUpdateElementWithNullValue() {
        this.multipleElementsContainer.update(new Object[][]{{"null",null}});
    }

    @Test
    public void TestUpdateWithContainer() {
        Container prueba = new Container();
        prueba.addElement("A",1);
        prueba.addElement("B",2);
        multipleElementsContainer.update(prueba);
        assertEquals(new Object[]{"0","1","2","3","4","5","6","7","8","9","A","B"},multipleElementsContainer.keys());
    }

    /**
     * Comprueba que se lanza la excepcion IllegalArgumentException
     * si se intenta mezclar el contenedor actual con un contenedor
     * vacío.
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestUpdateWithEmptyContainer() {
        this.multipleElementsContainer.update(this.emptyContainer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestAddElementWithMutableObjectAsKey() {
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        this.emptyContainer.addElement(lista, "ArrayList");
    }
}


