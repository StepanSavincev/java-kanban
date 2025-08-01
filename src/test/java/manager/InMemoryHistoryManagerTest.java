import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.model.Task;
import ru.yandex.javacourse.model.Status;
import ru.yandex.javacourse.manager.InMemoryHistoryManager;
import ru.yandex.javacourse.manager.HistoryManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для InMemoryHistoryManager — менеджера истории просмотров")
class InMemoryHistoryManagerTest {
    private static final String TASK_NAME = "Test Task";
    private static final String TASK_DESCRIPTION = "Test Description";
    private static final int MAX_HISTORY_SIZE = 10;

    private HistoryManager historyManager;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();

        // given: подготовка двух тестовых задач
        task1 = new Task("Task 1", "Desc1", Status.NEW);
        task2 = new Task("Task 2", "Desc2", Status.NEW);
    }

    @Test
    @DisplayName("Добавление задачи в историю")
    void addAndRetrieveHistory() {
        // when: добавить задачу в историю
        historyManager.add(task1);
        // then: в истории должна появиться эта задача
        List<Task> history = historyManager.getHistory();

        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "Размер истории должен быть 1");
        assertEquals(task1, history.get(0), "Задача в истории должна совпадать с добавленной");
    }
    @Test
    @DisplayName("Добавление нескольких задач и проверка порядка")
    void should_AddMultipleTasks_AndPreserveOrder_Test() {
        // when
        historyManager.add(task1);
        historyManager.add(task2);

        // then
        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size(), "Размер истории должен быть 2");
        assertEquals(task1, history.get(0), "Первая задача должна быть первой добавленной");
        assertEquals(task2, history.get(1), "Вторая задача должна быть второй добавленной");
    }

    @Test
    @DisplayName("История не превышает лимит в 10 задач")
    void history_DoesNot_ExceedTen_Test() {
        for (int i = 1; i <= 12; i++) {
            Task t = new Task(TASK_NAME + " " + i, TASK_DESCRIPTION, Status.NEW);
            historyManager.add(t);
        }

        // when
        List<Task> history = historyManager.getHistory();

        // then
        assertEquals(MAX_HISTORY_SIZE, history.size(), "Размер истории не должен превышать 10 задач");
    }

    @Test
    @DisplayName("Получение списка истории возвращает копию, а не внутренний список")
    void getHistory_ShouldReturnCopy_NotInternalList_Test() {
        // when
        historyManager.add(task1);
        List<Task> history1 = historyManager.getHistory();
        List<Task> history2 = historyManager.getHistory();

        // then
        assertNotSame(history1, history2, "Метод getHistory должен возвращать новый список");
    }
}