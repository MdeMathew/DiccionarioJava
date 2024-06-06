import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


/**
 * Clase para pruebas unitarias para la clase JDict
 * @author Tycho Quintana Santana
 * @author Carlos Mathias Osorio Rojas
 * @author Néstor Jesús Henríquez Medina
 * @version 1.0
 * @version JUnit 4.13.1
 * @version JDK 8
 * @since 29-03-2024
 */
public class TestJDict {

    private JDict emptyJDict;
    private JDict oneElementJDict;
    private JDict multipleElementsJDict;
    private String[] keyListEmpty;
    private String[] keyListOneKey;
    private String[] keyListMultipleKey;
    private String[] keyListDifferentSize;
    private Integer[] valueListEmpty;
    private Integer[] valueListOneValue;
    private Integer[] valueListMultipleValue;


    /**
     * Método que avisará de la finalización de los tests de la clase TestJDict
     */
    @AfterClass
    public static void finishMessage() {
        System.out.println("============== Fin tests JDict ==============");
    }

    /**
     * Método que avisará del comienzo de los tests de la clase TestJDict
     */
    @BeforeClass
    public static void startMessage() {
        System.out.println("========== Comienzo tests JDict =============");
    }

    /**
     * Método que inicializará los diferentes contenedores utilizados en los tests
     * cada vez que se vaya a realizar un nuevo test.
     */
    @Before
    public void setup() {

        this.emptyJDict = new JDict();
        this.oneElementJDict = new JDict();
        this.multipleElementsJDict = new JDict();

        this.oneElementJDict.addElement("un solo", 1);

        for (int i = 0; i < 10; i++) {
            this.multipleElementsJDict.addElement(String.valueOf(i), i);
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
     * cuando no hay elementos en el objeto de tipo 'JDict'.
     */
    @Test
    public void testItemsEmptyJDict() {
        assertTrue("La colección de items debe estar vacía", emptyJDict.items().isEmpty());
        System.out.println("TestJDict.testItemsEmptyJDict: Ok.");
    }

    /**
     * Comprueba que items() devuelva un conjunto
     * con un solo par clave-valor en el objeto de tipo 'JDict'
     */
    @Test
    public void testItemsSingleElement() {
        assertEquals("La colección de items debe contener un elemento", 1, oneElementJDict.items().size());
        assertTrue("La colección de items debe contener el par clave-valor ('un solo', 1)",
                oneElementJDict.items().contains(new Container.Pair<>("un solo", 1)));
        System.out.println("TestJDict.testItemsSingleElement: Ok.");
    }

    /**
     * Comprueba que items() retorne todos los pares clave-valor
     * cuando hay múltiples elementos en el objeto de tipo 'JDict'.
     */
    @Test
    public void testItemsMultipleElements() {
        assertEquals("La colección de items debe contener 10 elementos", 10, multipleElementsJDict.items().size());
        assertTrue(multipleElementsJDict.items().contains(new Container.Pair<>("1", 1)));
        assertTrue(multipleElementsJDict.items().contains(new Container.Pair<>("2", 2)));
        assertTrue(multipleElementsJDict.items().contains(new Container.Pair<>("3", 3)));
        System.out.println("TestJDict.testItemsMultipleElements: Ok.");
    }

    /**
     * Comprueba que la colección de pares clave-valor sea consistente
     * después de eliminar un elemento del objeto de tipo 'JDict'.
     */
    @Test
    public void testItemsAfterRemoval() {

        multipleElementsJDict.deleteElement("1");
        multipleElementsJDict.deleteElement("0");
        multipleElementsJDict.deleteElement("5");
        multipleElementsJDict.deleteElement("8");
        multipleElementsJDict.deleteElement("9");

        assertEquals("La colección de items debe contener 5 elementos después de la eliminación", 5,
                multipleElementsJDict.items().size());
        assertFalse("La colección de items no debe contener el par ('0', 0) después de su eliminación",
                multipleElementsJDict.items().contains(new Container.Pair<>("0", 0)));
        assertFalse("La colección de items no debe contener el par ('1', 1) después de su eliminación",
                multipleElementsJDict.items().contains(new Container.Pair<>("1", 1)));
        assertFalse("La colección de items no debe contener el par ('5', 5) después de su eliminación",
                multipleElementsJDict.items().contains(new Container.Pair<>("5", 5)));
        assertFalse("La colección de items no debe contener el par ('8', 8) después de su eliminación",
                multipleElementsJDict.items().contains(new Container.Pair<>("8", 8)));
        assertFalse("La colección de items no debe contener el par ('9', 9) después de su eliminación",
                multipleElementsJDict.items().contains(new Container.Pair<>("9", 9)));

        System.out.println("TestJDict.testItemsAfterRemoval: Ok.");
    }

    /**
     * Prueba que el método values() devuelva contenedor vacío
     * si el objeto de tipo 'JDict' no contiene elementos
     */
    @Test
    public void testValuesEmptyJDict() {
        assertTrue("La lista de valores debe estar vacía", emptyJDict.values().isEmpty());
        System.out.println("TestJDict.testValuesEmptyJDict: Ok.");
    }

    /**
     * Comprueba que values() devuelva una colección con un único valor
     * cuando el objeto de tipo 'JDict' tiene un solo elemento.
     */
    @Test
    public void testValuesSingleElement() {
        assertEquals("La lista de valores debe contener un elemento", 1, oneElementJDict.values().size());
        assertTrue("La lista de valores debe contener '1'", oneElementJDict.values().contains(1));
        System.out.println("TestJDict.testValuesSingleElement: Ok.");
    }

    /**
     * Comprueba que values() devuelva todos los valores
     * cuando el objeto de tipo 'JDict' contiene múltiples elementos.
     */
    @Test
    public void testValuesMultipleElements() {
        assertEquals("La lista de valores debe contener 10 elementos", 10, multipleElementsJDict.values().size());
        assertTrue(multipleElementsJDict.values().contains(1));
        assertTrue(multipleElementsJDict.values().contains(2));
        assertTrue(multipleElementsJDict.values().contains(3));
        System.out.println("TestJDict.testValuesMultipleElements: Ok.");
    }

    /**
     * Comprueba que la colección de valores sea consistente
     * después de eliminar elementos del objeto de tipo 'JDict'.
     */
    @Test
    public void testValuesAfterRemoval() {
        emptyJDict.addElement("Clave 1", "1");
        emptyJDict.addElement("Clave 2", "2");
        emptyJDict.deleteElement("Clave 1");

        assertEquals("La lista de valores debe contener un elemento después de la eliminación",
                1, emptyJDict.values().size());
        assertFalse("La lista de valores no debe contener el valor '1' después de su eliminación",
                emptyJDict.values().contains("1"));
        assertTrue("La lista de valores debe contener el valor '2' después de la eliminación del valor '1'",
                emptyJDict.values().contains("2"));

        System.out.println("TestJDict.testValuesAfterRemoval: Ok.");
    }




    /**
     * Prueba para comprobar el método clear de un contenedor vacío
     */
    @Test
    public void testClearEmptyJDict() {
        this.emptyJDict.clear();
        assertTrue(this.emptyJDict.isEmpty());
    }

    /**
     * Prueba para comprobar el método clear de un contenedor con un elemento
     */
    @Test
    public void testClearOneElementJDict() {
        this.oneElementJDict.clear();
        assertTrue(this.oneElementJDict.isEmpty());
    }

    /**
     * Prueba para comprobar el método clear de un contenedor multiples elementos
     */
    @Test
    public void testClearMultipleElementJDict() {
        this.multipleElementsJDict.clear();
        assertTrue(this.multipleElementsJDict.isEmpty());
    }

    /**
     * Prueba para comprobar la correcta copia de un contenedor
     * vacío
     */
    @Test
    public void testCopyMethodEmptyJDict() {
        JDict copyJDict = this.emptyJDict.copy();
        assertTrue(copyJDict.isEmpty());
    }

    /**
     * Prueba para comprobar la correcta copia de un contenedor
     * con un solo elemento.
     */
    @Test
    public void testCopyOneElementJDict() {
        JDict copyJDict = this.oneElementJDict.copy();
        assertFalse(copyJDict.isEmpty()); // Falta por implementar
        int entero = copyJDict.getValue("un solo");
        assertEquals(1, entero);
    }

    /**
     * Prueba para comprobar la correcta copia de un contenedor
     * con múltiples elementos
     */
    @Test
    public void testCopyMultipleElementJDict() {
        JDict copyJDict = this.multipleElementsJDict.copy();
        assertFalse(copyJDict.isEmpty()); // Falta por implementar
        assertEquals(0, (int) copyJDict.getValue("0"));
        assertEquals(1, (int) copyJDict.getValue("1"));
        assertEquals(2, (int) copyJDict.getValue("2"));
        assertEquals(3,  (int)copyJDict.getValue("3"));
        assertEquals(4, (int) copyJDict.getValue("4"));
        assertEquals(5, (int) copyJDict.getValue("5"));
        assertEquals(6, (int) copyJDict.getValue("6"));
        assertEquals(7, (int) copyJDict.getValue("7"));
        assertEquals(8, (int) copyJDict.getValue("8"));
        assertEquals(9, (int) copyJDict.getValue("9"));
    }


    /**
     * Prueba para el método estático fromkeys con dos listas
     * vacías como parámetros de entrada
     */
    @Test
    public void testFromKeysWithEmptyKeys() {
        JDict contenedor = JDict.fromKeys(keyListEmpty, valueListEmpty);
        assertTrue(contenedor.isEmpty());
    }

    /**
     * Prueba para el método estático fromkeys con dos listas
     * cada una de un elemento
     */
    @Test
    public void testFromKeysWithOneElementList() {
        JDict contenedor = JDict.fromKeys(keyListOneKey, valueListOneValue);
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
        JDict contenedor = JDict.fromKeys(keyListMultipleKey, valueListMultipleValue);
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
        JDict contenedor = JDict.fromKeys(keyListDifferentSize, valueListMultipleValue);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que solo tiene clave como parámetro de entrada
     * en un diccionario vacío
     */
    @Test
    public void testSetDefaultOnlyKeysInEmptyJDict() {
        assertNull(this.emptyJDict.setDefault("Hola"));
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que solo tiene clave como parámetro de entrada
     * en un diccionario con solo una clave
     */
    @Test
    public void testSetDefaultOnlyKeysInOneElementJDict() {
        assertNull(this.oneElementJDict.setDefault("hola"));
        assertNull(this.oneElementJDict.setDefault("un solq"));
        int entero = this.oneElementJDict.setDefault("un solo");
        assertEquals(1, entero);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que solo tiene clave como parámetro de entrada
     * en un diccionario con múltiples claves
     */
    @Test
    public void testSetDefaultOnlyKeysInMultipleElementJDict() {
        assertNull(this.multipleElementsJDict.setDefault("hola"));
        assertNull(this.multipleElementsJDict.setDefault("10"));
        int entero = this.multipleElementsJDict.setDefault("1");
        assertEquals(entero, 1);
        assertNotEquals(entero, 2);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que tiene tanto clave como valor como parámetro
     * de entrada en un contenedor vacío
     */
    @Test
    public void testSetDefaultKeyAndValueInEmptyJDict() {
        int entero = this.emptyJDict.setDefault("Hola", 100);
        assertEquals(100, entero);
        entero =  this.emptyJDict.setDefault("Hola", 200);
        assertEquals(100, entero);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que tiene tanto clave como valor como parámetros
     * de entrada en un contenedor con un elemento
     */
    @Test
    public void testSetDefaultKeyAndValueInOneElementJDict() {
        int entero = this.oneElementJDict.setDefault("un solo", "adios");
        assertEquals(1, entero);
        String cadena = this.oneElementJDict.setDefault("una sola", "dos");
        assertEquals("dos", cadena);
        String cadena2 = this.oneElementJDict.setDefault("una sola", 4);
        assertEquals("dos", cadena2);
    }

    /**
     * Prueba para comprobar el correcto funcionamiento del método
     * setDefault que tiene tanto clave como valor como parámetros
     * de entrada en un contenedor con múltiples elementos
     */
    @Test
    public void testSetDefaultKeyAndValueInMultipleElementsJDict() {
        int entero = this.multipleElementsJDict.setDefault("1",54);
        assertEquals(entero, 1);
        boolean value = this.multipleElementsJDict.setDefault("true", true);
        assertTrue(value);
        boolean value2 = this.multipleElementsJDict.setDefault("true", 4);
        assertTrue(value2);
    }

    /**
     * Test que comprueba el funcionamiento del método update con un sólo elemento para actualizar
     */
    @Test
    public void testUpdateOnce() {
        Object[][] t = {{"uno solo", "uno"}};
        boolean result = this.oneElementJDict.update(t);
        assertTrue(result);
    }

    /**
     * Test que comprueba el funcionamiento del método update con varios elementos para actualizar
     */
    @Test
    public void testUpdateMultipleTimes() {
        Object[][] t = {{"1", "uno"},{"2", "dos"}};
        boolean result = this.multipleElementsJDict.update(t);
        assertTrue(result);
    }

    /**
     * Test que obtiene la key de un JDict con un sólo elemento
     */
    @Test
    public void testGetOneKey() {
        Object[] keyS = this.oneElementJDict.keys();
        assertEquals(new Object[]{"un solo"},keyS);
    }

    /**
     * Test que obtiene la key de un JDict con múltiples elementos
     */
    @Test
    public void testGetMultipleKeys() {
        Object[] keyS = this.multipleElementsJDict.keys();
        assertEquals(new Object[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},keyS);
    }

    /**
     * Método que utiliza el método popItem para obtener el último elemento insertado en un JDict
     */
    @Test
    public void testPopItem() {
        Object[] result = this.multipleElementsJDict.popItem();
        assertEquals(new Object[]{"9",9},result);
    }

    /**
     * Test que comprueba que se recupera el valor dada una clave
     */
    @Test
    public void testGetValue() {
        Object value = this.multipleElementsJDict.getValue("1");
        assertEquals(1, value);
    }

    /**
     * Test para comprobar si se devuelve un valor que no existe
     */
    @Test (expected = NoSuchElementException.class)
    public void testGetValueFromAnEmptyJDict() {
        Object value = this.emptyJDict.getValue("9");   
    }

    /**
     * Test que comprueba que se borra correctamente un elemento de un JDict.
     */
    @Test
    public void testDeleteElement() {
        this.multipleElementsJDict.deleteElement("2");
        JDict expected = new JDict();
        expected.addElement("0",0);
        expected.addElement("1",1);
        for (int i = 3; i < 10; i++) {
            expected.addElement(String.valueOf(i), i);
        }
        assertEquals(expected.values(), multipleElementsJDict.values());
        assertEquals(expected.items(), multipleElementsJDict.items());
    }

    /**
     * Comprueba el funcionamiento del método deleteElement cuando
     * se le pasa una clave que no existe en el diccionario
     */
    @Test
    public void testDeleteElementNonExistingKey() {
        assertFalse(this.multipleElementsJDict.deleteElement(true));
    }

    /**
     * Test que comprueba el borrado de un elemento de un diccionario vacío.
     */
    @Test
    public void testDeleteElementWithEmptyJDict() {
        assertFalse(this.emptyJDict.deleteElement(true));
    }

    /**
     * Comprueba que se lanza la excepción NullPointerException
     * cuando intentamos insertar una nueva clave al diccionari
     * con valor null
     */
    @Test(expected = NullPointerException.class)
    public void TestAddElementWithNullKey() {
        this.emptyJDict.addElement(null,"nul broder");
    }

    /**
     * Test que lanza la excepción NullPointerException si se añade un elemento con un valor null
     */
    @Test(expected = NullPointerException.class)
    public void TestAddElementWithNullValue() {
        this.emptyJDict.addElement("Messi", null);
    }


    /**
     * Comprueba que salta la excepción pertinente
     * cuando se intenta mezclar con un diccionario que
     * tiene una clave con valor null
     */
    @Test(expected = NullPointerException.class)
    public void TestUpdateElementWithNullValue() {
        this.multipleElementsJDict.update(new Object[][]{{"null",null}});
    }

    @Test
    public void TestUpdateWithJDict() {
        JDict prueba = new JDict();
        prueba.addElement("A",1);
        prueba.addElement("B",2);
        multipleElementsJDict.update(prueba);
        assertEquals(new Object[]{"0","1","2","3","4","5","6","7","8","9","A","B"}, multipleElementsJDict.keys());
    }

    /**
     * Comprueba que se lanza la excepcion IllegalArgumentException
     * si se intenta mezclar el contenedor actual con un contenedor
     * vacío.
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestUpdateWithEmptyJDict() {
        this.multipleElementsJDict.update(this.emptyJDict);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestAddElementWithMutableObjectAsKey() {
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        this.emptyJDict.addElement(lista, "ArrayList");
    }
}


