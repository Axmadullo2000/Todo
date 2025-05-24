package service;

import entity.Todo;
import entity.User;
import util.Util;

import java.util.Arrays;

public class MainService {
    private User [] userList = new User[10];
    private Todo [] todoList = new Todo[10];

    public Todo[] getTodoList() {
        return todoList;
    }

    public void setTodoList(Todo[] todoList) {
        this.todoList = todoList;
    }

    public boolean registerProfile(String name, String phoneNumber, String password) {
        for (User user : userList) {
            if (user != null && user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password)) {
                return false;
            }
        }

        for (int i = 0; i < userList.length; i++) {
            if (userList[i] == null) {
                userList[i] = new User(name, phoneNumber, password);
                return true;
            }
        }

        return false;
    }

    public boolean loginToProfile(String phoneNumber, String password) {
        for (User user: userList) {
            if (user != null && user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password) ) {
                Util.currentUserId = user.getId();
                return true;
            }
        }

        return false;
    }

    public String getUserById(int currentUserId) {
        for (User user : userList) {
            if(user != null && user.getId() == currentUserId){
                return user.getName();
            }
        }

        return null;
    }

    public boolean createTodo(String title, String description) {
        for (int i = 0; i < todoList.length; i++) {
            if (todoList[i] == null) {
                Todo todo = new Todo(Util.currentUserId, Util.todoId++, title, description);
                todoList[i] = todo;
                return true;
            }
        }

        return false;
    }

    public Todo[] getTodosByUser(int currentUserId) {
        Todo[] userTodos = new Todo[todoList.length];

        int index = 0;

        for (Todo todo : todoList) {
            if(todo != null && todo.getUserId() == currentUserId){
                userTodos[index++] = todo;
            }
        }

        return Arrays.copyOf(userTodos, index);
    }

    public void editTodo(int todoId, Todo updateTodo) {
        for (int i = 0; i < todoList.length; i++) {
            if (todoList[i] != null && todoList[i].getTodoId() == todoId ) {
                todoList[i] = updateTodo;
                return;
            }
        }
    }

    public void deleteTodo(int todoId) {
        for (int i = 0; i < todoList.length; i++) {
            if (todoList[i] != null && todoList[i].getTodoId() == todoId) {
                todoList[i] = null;
                return;
            }
        }
    }

    public void completeTodo(int todoId) {
        for (Todo todo : todoList) {
            if (todo != null && todo.getTodoId() == todoId) {
                todo.setCompleted(true);
                return;
            }
        }
    }

    public void checkToExist(int todoId) {
        for (Todo value : todoList) {
            if (value != null && value.getTodoId() == todoId) {
                String title = Util.getStr("Enter title");
                String description = Util.getStr("Enter description");

                Todo todo = new Todo(Util.currentUserId, todoId, title, description);
                editTodo(todoId, todo);

                return;
            }else {
                return;
            }
        }
    }

    public void createDefaultUser() {
        User user1 = new User("Axmadullo", "997494262", "123");
        User user2 = new User("Alyona", "123456789", "123");
        user1.setId(Util.userId++);
        user2.setId(Util.userId++);

        userList[0] = user1;
        userList[1] = user2;
    }

    public void createDefaultTodo() {
        Todo todo1 = new Todo(1, 1, "Features of PDP Academy", "PDP is my favorite place to learn Programming!");
        Todo todo2 = new Todo(1, 2, "Features of My School", "My School is the best place to learn English!");

        Todo todo3 = new Todo(2, 1, "Features of PDP Academy", "PDP is my favorite place to learn Programming!");
        Todo todo4 = new Todo(2, 2, "Features of My School", "My School is the best place to learn English!");

        todoList[0] = todo1;
        todoList[1] = todo2;
        todoList[2] = todo3;
        todoList[3] = todo4;

    }
}
