package ru.yandex.javacourse.manager;

import ru.yandex.javacourse.model.*;

import java.util.List;

public interface TaskManager {

    int addNewTask(Task task);

    Task getTask(int id);

    List<Task> getTasks();

    void updateTask(Task task);

    void deleteTaskByID(int id);

    void deleteTasks();

    int addNewEpic(Epic epic);

    Epic getEpic(int id);

    List<Epic> getEpics();

    void updateEpic(Epic epic);

    void deleteEpicByID(int id);

    void deleteEpics();

    int addNewSubtask(Subtask subtask);

    Subtask getSubtask(int id);

    List<Subtask> getSubtasks();

    void updateSubtask(Subtask subtask);

    void deleteSubtaskByID(int id);

    void deleteSubtasks();

    List<Subtask> getEpicSubtasks(int epicId);

    List<Task> getHistory();
}
