package ru.yandex.javacourse.manager;

import ru.yandex.javacourse.model.Task;


import java.util.List;

public interface HistoryManager {
    void add(Task task);

    List<Task> getHistory();
}