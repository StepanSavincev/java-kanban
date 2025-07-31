import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.model.Epic;
import ru.yandex.javacourse.model.Status;
import ru.yandex.javacourse.model.Subtask;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void epicsWithSameIdAreEqual() {
        Epic epic1 = new Epic(1, "Epic1", "Desc", Status.NEW);
        Epic epic2 = new Epic(1, "Epic1", "Desc", Status.NEW);

        assertEquals(epic1, epic2, "Эпики с одинаковым id должны быть равны");
    }

    @Test
    void cannotAddEpicAsSubtaskToItself() {
        Epic epic = new Epic(1, "Epic1", "Desc", Status.NEW);

        for (Subtask sub : epic.getSubtaskList()) {
            assertNotEquals(epic.getId(), sub.getId(), "Эпик не должен быть подзадачей самому себе");
        }
    }

    @Test
    void toStringContainsSubtaskListSize() {
        Epic epic = new Epic("Epic", "Desc");
        Subtask subtask = new Subtask("Subtask", "Desc", 1);
        epic.addSubtask(subtask);

        String epicString = epic.toString();
        assertTrue(epicString.contains("subtaskList.size=1"));
    }
}