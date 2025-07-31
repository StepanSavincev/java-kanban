package ru.yandex.javacourse.model;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Subtask> subtaskList = new ArrayList<>();

    public Epic(String name, String description) {

        super(name, description);
    }

    public Epic(int id, String name, String description, Status status) {

        super(id, name, description, status);
    }

    public void addSubtask (Subtask subtask) {

        subtaskList.add(subtask);
    }

    public void clearSubtasks() {

        subtaskList.clear();
    }

    public ArrayList<Subtask> getSubtaskList() {

        return subtaskList;
    }

    public void setSubtaskList(ArrayList<Subtask> subtasks) {

        this.subtaskList = subtasks;
    }

    @Override
    public String toString() {
        return "ru.yandex.javacourse.model.Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", subtaskList.size=" + subtaskList.size() +
                ", status=" + getStatus() +
                '}';
    }
}