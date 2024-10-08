public class Task {
    private int id;
    private String title;
    private String description;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + description;
    }

    public static Task fromString(String taskString) {
        String[] parts = taskString.split(",");
        int id = Integer.parseInt(parts[0]);
        String title = parts[1];
        String description = parts[2];
        return new Task(id, title, description);
    }
}
