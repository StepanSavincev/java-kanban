package ru.yandex.javacourse.Main;

import ru.yandex.javacourse.model.Status;
import ru.yandex.javacourse.service.TaskManager;
import ru.yandex.javacourse.model.Epic;
import ru.yandex.javacourse.model.Subtask;
import ru.yandex.javacourse.model.Task;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task washDishes = new Task("Помыть посуду", "С мылом, чтоб мама не наругала");
        Task washDishesCreated = taskManager.addTask(washDishes);
        System.out.println(washDishesCreated);

        Task washDishesToUpdate = new Task(washDishes.getId(), "Не забыть помыть посуду", "Можно и без мыла",
                Status.IN_PROGRESS);
        Task washDishesUpdated = taskManager.updateTask(washDishesToUpdate);
        System.out.println(washDishesUpdated);


        Epic repairCar   = new Epic("Отремантировать машину", "Желательно за выходные");
        taskManager.addEpic(repairCar  );
        System.out.println(repairCar  );
        Subtask repairCarSubtask1 = new Subtask("Поклеить тонировку", "Не светлее 70-ки!",
                repairCar  .getId());
        Subtask repairCarSubtask2 = new Subtask("Установить новые фары", "Старую отдать соседу",
                repairCar  .getId());
        taskManager.addSubtask(repairCarSubtask1);
        taskManager.addSubtask(repairCarSubtask2);
        System.out.println(repairCar  );
        repairCarSubtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(repairCarSubtask2);
        System.out.println(repairCar  );
    }
}
