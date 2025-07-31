import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacourse.manager.TaskManager;
import ru.yandex.javacourse.model.Epic;
import ru.yandex.javacourse.model.Status;
import ru.yandex.javacourse.model.Subtask;
import ru.yandex.javacourse.model.Task;
import ru.yandex.javacourse.manager.InMemoryTaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    void addNewTaskAndGetTask() {
        Task task = new Task("Test Task", "Description", Status.NEW);
        int taskId = taskManager.addNewTask(task);

        Task retrieved = taskManager.getTask(taskId);
        assertNotNull(retrieved);
        assertEquals(task, retrieved);
    }

    @Test
    void addNewEpicAndGetEpic() {
        Epic epic = new Epic("Test Epic", "Epic Description");
        int epicId = taskManager.addNewEpic(epic);

        Epic retrieved = taskManager.getEpic(epicId);
        assertNotNull(retrieved);
        assertEquals(epic, retrieved);
    }

    @Test
    void addNewSubtaskAndGetSubtask() {
        Epic epic = new Epic("Epic for Subtask", "Desc");
        int epicId = taskManager.addNewEpic(epic);

        Subtask subtask = new Subtask("Test Subtask", "Subtask Desc", epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);

        Subtask retrieved = taskManager.getSubtask(subtaskId);
        assertNotNull(retrieved);
        assertEquals(subtask, retrieved);
        assertEquals(epicId, retrieved.getEpicId());

        // Проверяем, что подзадача добавлена в эпик
        Epic updatedEpic = taskManager.getEpic(epicId);
        assertTrue(updatedEpic.getSubtaskList().contains(subtask));
    }

    @Test
    void updateTaskSuccessfully() {
        Task task = new Task("Old Task", "Old Desc", Status.NEW);
        int id = taskManager.addNewTask(task);

        Task updatedTask = new Task(id, "Updated Task", "Updated Desc", Status.DONE);
        taskManager.updateTask(updatedTask);

        Task retrieved = taskManager.getTask(id);
        assertEquals(updatedTask, retrieved);
    }

    @Test
    void updateEpicSuccessfully() {
        Epic epic = new Epic("Epic", "Desc");
        int id = taskManager.addNewEpic(epic);

        Epic updatedEpic = new Epic(id, "Epic Updated", "Desc Updated", Status.IN_PROGRESS);
        taskManager.updateEpic(updatedEpic);

        Epic retrieved = taskManager.getEpic(id);
        assertEquals(updatedEpic, retrieved);
    }

    @Test
    void updateSubtaskSuccessfully() {
        Epic epic = new Epic("Epic", "Desc");
        int epicId = taskManager.addNewEpic(epic);

        Subtask subtask = new Subtask("Subtask", "Desc", epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);

        Subtask updatedSubtask = new Subtask(subtaskId, "Subtask Updated", "Desc Updated", Status.DONE, epicId);
        taskManager.updateSubtask(updatedSubtask);

        Subtask retrieved = taskManager.getSubtask(subtaskId);
        assertEquals(updatedSubtask, retrieved);

        // Проверяем, что эпик обновился
        Epic updatedEpic = taskManager.getEpic(epicId);
        assertTrue(updatedEpic.getSubtaskList().stream().anyMatch(st -> st.getId() == subtaskId));
    }

    @Test
    void getEpicSubtasksReturnsCorrectList() {
        Epic epic = new Epic("Epic", "Desc");
        int epicId = taskManager.addNewEpic(epic);

        Subtask subtask1 = new Subtask("Subtask1", "Desc1", epicId);
        Subtask subtask2 = new Subtask("Subtask2", "Desc2", epicId);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        List<Subtask> subtasks = taskManager.getEpicSubtasks(epicId);
        assertEquals(2, subtasks.size());
        assertTrue(subtasks.contains(subtask1));
        assertTrue(subtasks.contains(subtask2));
    }

    @Test
    void getHistoryContainsViewedTasks() {
        Task task = new Task("Task", "Desc", Status.NEW);
        int taskId = taskManager.addNewTask(task);
        Epic epic = new Epic("Epic", "Desc");
        int epicId = taskManager.addNewEpic(epic);
        Subtask subtask = new Subtask("Subtask", "Desc", epicId);
        int subtaskId = taskManager.addNewSubtask(subtask);

        // Сначала история пустая
        assertTrue(taskManager.getHistory().isEmpty());

        // Просматриваем задачи
        taskManager.getTask(taskId);
        taskManager.getEpic(epicId);
        taskManager.getSubtask(subtaskId);

        List<Task> history = taskManager.getHistory();
        assertEquals(3, history.size());
        assertEquals(taskId, history.get(0).getId());
        assertEquals(epicId, history.get(1).getId());
        assertEquals(subtaskId, history.get(2).getId());
    }
}
