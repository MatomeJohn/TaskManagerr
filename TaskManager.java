import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int nextId;
    private final String fileName = "tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
        loadTasksFromFile();
    }

    public void createTask(String title, String description) {
        Task newTask = new Task(nextId, title, description);
        tasks.add(newTask);
        nextId++;
        System.out.println("Task created successfully.");
        saveTasksToFile();
    }

    public void readTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    public void updateTask(int id, String newTitle, String newDescription) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                System.out.println("Task updated successfully.");
                saveTasksToFile();
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(int id) {
        Task taskToRemove = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToRemove = task;
                break;
            }
        }

        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Task deleted successfully.");
            saveTasksToFile();
        } else {
            System.out.println("Task not found.");
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromFile() {
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.fromString(line);
                    tasks.add(task);
                    nextId = Math.max(nextId, task.getId() + 1);
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }
    }
}
