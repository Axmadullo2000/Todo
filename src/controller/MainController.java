package controller;

import entity.Todo;
import service.MainService;
import util.Util;

import static service.MainService.createDefaultUser;
import static util.Util.getNum;
import static util.Util.getStr;

public class MainController {

    private MainService mainService = new MainService();

    public void authMenu() {

        createDefaultUser();

        while (true) {
            System.out.println("""
                    1. Login
                    2. Registration
                    0. Quit
                """);

            int menu = getNum("Choose an option");

            switch (menu) {
                case 1 -> {
                    System.out.println("Login page");
                    signIn();
                }
                case 2 -> {
                    System.out.println("Registration page");
                    signUp();
                }
                case 0 -> {
                    return;
                }
            }

        }
    }

    private void signUp() {
        String name = getStr("Enter your name");
        String phoneNumber = getStr("Enter phone number");
        String password = getStr("Enter password");

        if (mainService.registerProfile(name, phoneNumber, password)) {
            System.out.println("Successfully registered!");

            mainMenu();
        }else {
            System.out.println("This user already exists!");
        }

    }

    private void signIn() {
        String phoneNumber = getStr("Enter phone number");
        String password = getStr("Enter password");

        if (mainService.loginToProfile(phoneNumber, password)) {
            String username = mainService.getUserById(Util.currentUserId);
            System.out.println("Welcome " + username);

            mainMenu();
        }else {
            System.out.println("Throw away!");
        }

    }

    private void mainMenu() {
        while (true) {
            System.out.println("""
                    1. Create todo
                    2. Show todo list
                    3. Edit items from todo list
                    4. Delete from todo list
                    0. Back to Main Menu
                    """);
            int option = getNum("Choose");

            switch (option) {
                case 1 -> {
                    createToDo();
                }
                case 2 -> {
                    showTodos();
                }
                case 3 -> {
                    editTodo();
                }
                case 4 -> {
                    deleteTodo();
                }
                case 0 -> {
                    return;
                }
                default -> {}
            }

        }
    }

    private void editTodo() {
        showTodos();

        int todoId = getNum("Which todo do you want to change?");

        mainService.checkToExist(todoId);

        int markAsComplete = getNum("Do you want to mark it as completed? 1 - Yes, 0 - No");

        if (markAsComplete == 1) {
            mainService.completeTodo(todoId);  // Отмечаем задачу как выполненную
        }
    }

    private void deleteTodo() {
        showTodos();
        int todoId = getNum("Which todo do you want to delete?");
        mainService.deleteTodo(todoId);
    }

    private void showTodos() {
        Todo[] todos = mainService.getTodosByUser(Util.currentUserId);

        System.out.println("""
               1. Incompleted todos
               2. Completed todos
               0. Back to Main Menu
               \s""");
        int option = getNum("");

        if (option == 1) {
            for (Todo todo : todos) {
                if (!todo.isCompleted()) System.out.println(todo.getTodoData());
            }
        }else if (option == 2) {
            for (Todo todo : todos) {
                if(todo.isCompleted()) System.out.println(todo.getTodoData());
            }
        }else {
            return;
        }
    }

    private void createToDo() {
        String title = getStr("Enter title");
        String description = getStr("Enter description");

        if (mainService.createTodo(title, description)) {
            System.out.println("Todo has successfully created!");
        }else {
            System.out.println("Error occured in process!");
        }
    }
}
