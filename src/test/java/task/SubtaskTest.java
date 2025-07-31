import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.model.Status;
import ru.yandex.javacourse.model.Subtask;

import static org.junit.jupiter.api.Assertions.*;

public class SubtaskTest {

    @Test
    void subtasksWithSameIdAreEqual() {
        Subtask subtask1 = new Subtask(1, "Subtask1", "Desc", Status.NEW, 10);
        Subtask subtask2 = new Subtask(1, "Subtask1", "Desc", Status.NEW, 10);
        assertEquals(subtask1, subtask2, "Подзадачи с одинаковым id должны быть равны");
    }

    @Test
    void subtasksWithDifferentIdAreNotEqual() {
        Subtask subtask1 = new Subtask(1, "Subtask1", "Desc", Status.NEW, 10);
        Subtask subtask2 = new Subtask(2, "Subtask1", "Desc", Status.NEW, 10);
        assertNotEquals(subtask1, subtask2, "Подзадачи с разными id не должны быть равны");
    }

    @Test
    void getEpicIdReturnsCorrectValue() {
        int epicId = 25;
        Subtask subtask = new Subtask("Test", "Desc", epicId);
        assertEquals(epicId, subtask.getEpicId());
    }

    @Test
    void toStringContainsEpicId() {
        Subtask subtask = new Subtask(1, "Subtask", "Desc", Status.NEW, 42);
        String str = subtask.toString();
        assertTrue(str.contains("epicId=42"));
    }
}