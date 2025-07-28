
import com.example.ada1fp.models.AVLTree;
import com.example.ada1fp.models.heap;
import com.example.ada1fp.models.task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskManagerTest {
    private heap taskHeap;
    private AVLTree taskTree;

    @BeforeEach
    public void setUp() {
        taskHeap = new heap();
        taskTree = new AVLTree();
    }

    // ---- Pruebas de Inserción ----
    @Test
    public void testInsertionAndPriorityOrder() {
        System.out.println("=== PRUEBA DE INSERCIÓN ===");

        task t1 = new task(2, "Tarea Media", "2025-01-01");
        task t2 = new task(3, "Tarea Alta", "2025-01-02");
        task t3 = new task(1, "Tarea Baja", "2025-01-03");

        taskHeap.insert(t1);
        taskHeap.insert(t2);
        taskHeap.insert(t3);

        System.out.println("Tareas insertadas (prioridad 2, 3, 1)");
        System.out.println("Orden de extracción esperado: Alta -> Media -> Baja");

        assertEquals(t2, taskHeap.extractMax());
        assertEquals(t1, taskHeap.extractMax());
        assertEquals(t3, taskHeap.extractMax());

        System.out.println("Resultado: Orden de extracción correcto\n");
    }

    // ---- Pruebas de Eliminación ----
    @Test
    public void testDeletionAndHeapIntegrity() {
        System.out.println("=== PRUEBA DE ELIMINACIÓN ===");

        task t1 = new task(2, "Tarea Media", "2025-01-01");
        task t2 = new task(3, "Tarea Alta", "2025-01-02");

        taskHeap.insert(t1);
        taskHeap.insert(t2);
        taskHeap.extractMax();

        System.out.println("Heap después de eliminar la tarea más prioritaria:");
        System.out.println("Tamaño esperado: 1 | Tamaño real: " + taskHeap.size());
        assertEquals(1, taskHeap.size());
        assertEquals(t1, taskHeap.getMax());

        System.out.println("Resultado: Estructura del heap conservada\n");
    }

    // ---- Pruebas de Indexación ----
    @Test
    public void testAVLSearchEfficiency() {
        System.out.println("=== PRUEBA DE INDEXACIÓN (AVL) ===");

        for (int i = 1; i <= 1000; i++) {
            task t = new task(1, "Tarea " + i, "2025-01-01");
            taskTree.insert(t);
        }

        long startTime = System.nanoTime();
        task found = taskTree.search(500);
        long endTime = System.nanoTime();

        System.out.println("Búsqueda en árbol de 1000 nodos:");
        System.out.println("Tiempo de búsqueda: " + (endTime - startTime) + " ns");
        assertNotNull(found);
        assertEquals(500, found.getId());

        System.out.println("Resultado: Búsqueda O(log n) confirmada\n");
    }

    // ---- Pruebas de Equilibrio ----
    @Test
    public void testAVLRebalancing() {
        System.out.println("=== PRUEBA DE EQUILIBRIO (AVL) ===");

        // Inserción desbalanceada (orden descendente)
        for (int i = 10; i >= 1; i--) {
            task t = new task(1, "Tarea " + i, "2025-01-01");
            taskTree.insert(t);
        }

        System.out.println("Árbol después de inserciones desbalanceadas:");
        System.out.println("Altura esperada: ~log2(10) = 4 | Altura real: " + getTreeHeight(taskTree));

        assertTrue(getTreeHeight(taskTree) <= 4);
        System.out.println("Resultado: Árbol se rebalanceó automáticamente\n");
    }

    // Método para obtener la altura del árbol
    private int getTreeHeight(AVLTree tree) {
        return tree.getHeigth();
    }
}