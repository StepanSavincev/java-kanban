import org.testng.annotations.Test;
import ru.yandex.javacourse.model.Status;
import ru.yandex.javacourse.model.Task;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

class TaskTest {

    @Test
    void tasksWithSameIdAreEqual() {
        Task task1 = new Task(1, "Task1", "Desc", Status.NEW);
        Task task2 = new Task(1, "Task1", "Desc", Status.NEW);

        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны");
    }

    @Test
    void tasksWithDifferentIdAreNotEqual() {
        Task task1 = new Task(1, "Task1", "Desc", Status.NEW);
        Task task2 = new Task(2, "Task1", "Desc", Status.NEW);

        assertNotEquals(task1, task2, "Задачи с разными id не должны быть равны");
    }
}
