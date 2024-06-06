import org.junit.*;
import org.junit.runner.JUnitCore;

/**
 * Clase principal de la jerarquía de clases de test
 * del proyecto de implementación de diccionario en Java.
 *
 * @author Tycho Quintana Santana
 * @author Carlos Mathias Osorio Rojas
 * @author Néstor Jesús Henríquez Medina
 * @version 1.0
 * @since 8
 */
public class TestClass {

    /**
     * El método imprimirá un mensaje cuando comiencen a realizarse los tests
     */
    @BeforeClass
    public static void startMessage() {
        System.out.println("#################### COMIENZAN LOS TESTS ####################");
    }

    /**
     * El método imprimirá un mensaje cuando terminen de realizarse los tests
     */
    @AfterClass
    public static void finishMessage() {
        System.out.println("#################### FINALIZAN LOS TESTS ####################");
    }

    /**
     * El método main de la clase preparará un array con los nombres de las
     * clases que implementan tests y los mandará a ejecutar
     */
    public static void main(String[] args) {
        String[] testClasses = {"TestNode", "TestLinkedList", "TestContainer", "TestJDict"};
        JUnitCore.main(testClasses);
    }
}
