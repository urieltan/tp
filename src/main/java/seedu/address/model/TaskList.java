package seedu.address.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

/** Encapsulates a list of tasks */
public class TaskList {

    /** An array list consisting of tasks. */
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList by assigning the list parameter to a newly constructed
     * empty Task ArrayList.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }
    /**
     * Deletes a task from list with at index minus one.
     * @param index the index (plus one) of the task to be removed from the list.
     */
    public void delete(int index) {
        list.remove(index - 1);
    }

    /**
     * Adds a task to list.
     *
     * @param task the task to be added to the list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Retrieves the task at a provided index minus one from list.
     *
     * @param index the index (plus one) of the task to be retrieved from list.
     * @return
     */
    public Task get(int index) {
        return list.get(index - 1);
    }

    /**
     * Returns the size of TaskList.
     *
     * @return the size of list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a boolean value indicating if the TaskList is empty.
     *
     * @return true or false if the list is empty or not empty respectively.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns a TaskList of tasks containing the provided key words or phrases.
     *
     * @param keyWord the key words or phrases that are being searched for in the TaskList.
     * @return a TaskList of tasks containing the provided key words or phrases.
     */
    public TaskList contains(String keyWord) {
        TaskList keyWordTasks = new TaskList();
        for (int i = 1; i < this.size(); i++) {
            if (this.get(i).toString().contains(keyWord)) {
                keyWordTasks.add(this.get(i));
            }
        }
        return keyWordTasks;
    }

    /**
     * Deletes all tasks in the TaskList.
     */
    public void deleteAll() {
        list.clear();
    }

    /**
     * Marks all tasks in the TaskList as done.
     */
    public void markAllDone() {
        for (Task task: list) {
            task.markAsDone();
        }
    }

    public ObservableList<Task> getObservableTaskList() {
        javafx.collections.ObservableList<Task> ObservableList = FXCollections.observableList(list);
        return ObservableList;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

