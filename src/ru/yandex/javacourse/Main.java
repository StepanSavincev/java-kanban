package ru.yandex.javacourse;

import ru.yandex.javacourse.manager.TaskManager;
import ru.yandex.javacourse.model.Epic;
import ru.yandex.javacourse.model.Subtask;
import ru.yandex.javacourse.model.Task;
import ru.yandex.javacourse.manager.*;

public class Main {

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Epic epic : manager.getEpics()) {
            System.out.println(epic);
            for (Subtask subtask : manager.getEpicSubtasks(epic.getId())) {
                System.out.println("--> " + subtask);
            }
        }

        System.out.println("Подзадачи:");
        for (Subtask subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История просмотров:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        Task task1 = new Task("Task 1", "Desc 1");
        Epic epic1 = new Epic("Epic 1", "Epic Desc");
        Subtask subtask1 = new Subtask("Subtask 1", "Subtask Desc", epic1.getId());

        int taskId = manager.addNewTask(task1);
        int epicId = manager.addNewEpic(epic1);
        int subtaskId = manager.addNewSubtask(subtask1);

        manager.getTask(taskId);
        manager.getEpic(epicId);
        manager.getSubtask(subtaskId);

        printAllTasks(manager);
    }
}
