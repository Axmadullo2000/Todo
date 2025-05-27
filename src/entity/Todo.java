package entity;

public class Todo extends Parent {
    private int userId;
    private int todoId;
    private String title;
    private String description;
    private boolean isCompleted = false;

    public Todo() {
    }

    public int getUserId() {
        return userId;
    }

    public Todo(int todoId, String title, String description) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getTodoData() {
        return "id: %d, title: %s, description: %s".formatted(todoId, title, description);
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }
}
