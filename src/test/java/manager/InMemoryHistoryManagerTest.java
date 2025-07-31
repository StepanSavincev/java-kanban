import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.model.Task;
import ru.yandex.javacourse.model.Status;
import ru.yandex.javacourse.manager.InMemoryHistoryManager;
import ru.yandex.javacourse.manager.HistoryManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        task1 = new Task("Task 1", "Desc1", Status.NEW);
        task2 = new Task("Task 2", "Desc2", Status.NEW);
    }

    @Test
    void addAndRetrieveHistory() {
        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();

        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(2, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
    }

    @Test
    void historyDoesNotExceedTen() {
        for (int i = 0; i < 15; i++) {
            Task task = new Task("Task " + i, "Desc", Status.NEW);
            historyManager.add(task);
        }
        assertEquals(10, historyManager.getHistory().size(), "История должна содержать не более 10 задач");
    }
}