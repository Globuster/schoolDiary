package com.schooldiary.controller;

import com.schooldiary.model.Role;
import com.schooldiary.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MainController {

    // Основные контейнеры
    @FXML private Label userLabel;
    @FXML private VBox menuBox;
    @FXML private StackPane contentArea;

    // Кнопки для управления доступом (fx:id из main.fxml)
    @FXML private Button Teachers;
    @FXML private Button Students;
    @FXML private Button AddTeacher;
    @FXML private Button AddClass;

    private User currentUser;

    /**
     * Вызывается из LoginController после успешного входа
     */
    public void setUser(User user) {
        this.currentUser = user;
        userLabel.setText(user.getLogin() + " (" + user.getRole() + ")");
        configureMenuByRole();

        // По умолчанию при входе показываем главную
        showDashboard();
    }

    /**
     * Скрывает админские функции для обычных пользователей
     */
    private void configureMenuByRole() {
        if (currentUser != null && currentUser.getRole() != Role.ADMIN) {
            // Удаляем кнопки из бокового меню, чтобы их нельзя было нажать
            menuBox.getChildren().remove(AddTeacher);
            menuBox.getChildren().remove(AddClass);

            // Если нужно скрыть списки всех учителей/учеников от учеников:
            // if (currentUser.getRole() == Role.STUDENT) {
            //    menuBox.getChildren().remove(Teachers);
            //    menuBox.getChildren().remove(Students);
            // }
        }
    }

    /**
     * Главный механизм смены экранов
     */
    private void loadView(String fxmlFile) {
        try {
            // Попробуем загрузить файл из папки /view/
            String path = "/view/" + fxmlFile;
            var resource = getClass().getResource(path);

            if (resource == null) {
                // Если не нашел, попробуем без начального слэша (зависит от настроек сборки)
                path = "view/" + fxmlFile;
                resource = getClass().getResource(path);
            }

            if (resource == null) {
                System.err.println("КРИТИЧЕСКАЯ ОШИБКА: Файл [" + fxmlFile + "] не найден по путям /view/ или view/");
                System.err.println("Проверь, что файл лежит в src/main/resources/view/" + fxmlFile);
                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);

            // ВАЖНО: Если ты добавил fx:controller в дочерние FXML,
            // НЕ используй loader.setController(this), иначе будет конфликт!

            Node view = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла FXML: " + fxmlFile);
            e.printStackTrace();
        }
    }

    // --- ОБРАБОТЧИКИ НАЖАТИЙ (onAction) ---

    // Поля для формы добавления учителя
    @FXML private TextField nameField;
    @FXML private TextField subjectField;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    // Поля для формы добавления класса
    @FXML private TextField classNumber;
    @FXML private TextField classLetter;
    @FXML
    private void showDashboard() {
        loadView("dashboard.fxml");
    }

    @FXML
    private void showGrades() {
        loadView("grades.fxml");
    }

    @FXML
    private void showSchedule() {
        loadView("schedule.fxml");
    }

    @FXML
    private void showHomeworks() {
        loadView("homeworks.fxml");
    }

    @FXML
    private void showTeachers() {
        loadView("teachers_list.fxml");
    }

    @FXML
    private void showStudents() {
        loadView("students_list.fxml");
    }

    @FXML
    private void showAddTeacherForm() {
        loadView("add_teacher.fxml");
    }

    @FXML
    private void showAddClassForm() {
        loadView("add_class.fxml");
    }

    @FXML
    private void showProfile() {
        loadView("profile.fxml");
    }

    // --- ЛОГИКА ФОРМ (Пример для кнопок сохранения) ---

    @FXML
    private void processAddTeacher() {
        // Здесь будет код сохранения учителя в базу данных
        System.out.println("Нажата кнопка сохранения учителя");
    }

    @FXML
    public void saveClass() {
        // Здесь будет код сохранения класса
        System.out.println("Нажата кнопка сохранения класса");
    }

    @FXML
    private void handleLogout() {
        // Закрываем текущее окно
        userLabel.getScene().getWindow().hide();
        // Тут можно вызвать метод открытия окна Login.fxml
    }
    // Ссылки на элементы внутри страниц (важно: fx:id должны совпадать!)
    @FXML private HBox teacherHwActions;
    @FXML private Button editScheduleBtn;

    // Обнови метод loadView, чтобы он настраивал видимость после загрузки


    private void applyPermissions() {
        if (currentUser == null) return;

        // Логика для Домашних Заданий
        if (teacherHwActions != null) {
            // Показываем панель добавления ДЗ только Учителю или Админу
            boolean isTeacherOrAdmin = (currentUser.getRole() == Role.TEACHER || currentUser.getRole() == Role.ADMIN);
            teacherHwActions.setVisible(isTeacherOrAdmin);
            teacherHwActions.setManaged(isTeacherOrAdmin); // Чтобы скрытый элемент не занимал место
        }

        // Логика для Расписания
        if (editScheduleBtn != null) {
            editScheduleBtn.setVisible(currentUser.getRole() == Role.ADMIN);
            editScheduleBtn.setManaged(currentUser.getRole() == Role.ADMIN);
        }
    }}

