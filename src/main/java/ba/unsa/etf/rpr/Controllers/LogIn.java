package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Business.DoctorManager;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.Domain.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/***
 * JavaFX controller for showing the login window
 *
 * @author Berin Karahodžić
 */

public class LogIn {
    //window fields
    private static Doctor doctor;
    public Button LoginId;
    public Hyperlink CreateAccId;
    public PasswordField PasswordId;
    public TextField UsernameId;
    public GridPane WindowId;
    public Label ErrorId;
    public Label PasswordError;
    public static final DoctorManager doctorManager = new DoctorManager();

    /**
     * button for beginning the log in process
     * @param ignoredActionEvent
     * @throws IOException
     */
    public void LogInProcess(ActionEvent ignoredActionEvent) throws IOException {
        login();
    }

    /**
     * changes the typing cursor to the password field when enter is pressed on username entering
     * @param keyEvent
     */
    public void KeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER))
        {
            PasswordId.requestFocus();
        }
    }
    /**
     * does the same as clicking the log in button when enter is pressed
     * @param keyEvent
     */
    public void KeyPressed2(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode().equals(KeyCode.ENTER))
        {
            login();
        }
    }

    /**
     * function that is used to log a user in
     * @throws IOException
     */
    private void login() throws IOException{
        try{
            doctorManager.getAll();
            if(UsernameId.getText().trim().isEmpty()) {
                UsernameId.setStyle("-fx-border-color: #E92929");
                PasswordId.setStyle("-fx-border-color: #00000000");
                PasswordError.setPrefHeight(0);
                ErrorId.setText("Invalid username!");
                ErrorId.setStyle("-fx-text-fill: #E92929");
                ErrorId.setPrefHeight(USE_COMPUTED_SIZE);
                ErrorId.setPrefWidth(USE_COMPUTED_SIZE);
                return;
            }
            doctor = doctorManager.getByUsername(UsernameId.getText());
            if(doctor.getPassword().equals(PasswordId.getText())){
                UsernameId.setStyle("-fx-border-color: #00FF00 ");
                PasswordId.setStyle("-fx-border-color: #00FF00 ");
                ErrorId.setPrefHeight(0);
                PasswordError.setPrefHeight(0);
                Stage closing = (Stage) LoginId.getScene().getWindow();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Home.fxml")));
                primaryStage.setTitle("Hospital");
                primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                primaryStage.setResizable(true);
                primaryStage.setMinHeight(500);
                primaryStage.setMinWidth(996);
                primaryStage.getIcons().add(new Image("C:\\Users\\Svage\\IdeaProjects\\projekatB\\src\\main\\resources\\Images\\v987-18a.jpg"));
                primaryStage.show();
                closing.close();
                return;
            }
            UsernameId.setStyle("-fx-border-color: #00000000");
            PasswordId.setStyle("-fx-border-color: #E92929");
            ErrorId.setPrefHeight(0);
            PasswordError.setPrefHeight(USE_COMPUTED_SIZE);
        } catch (HospitalException e) {
            UsernameId.setStyle("-fx-border-color: #E92929");
            PasswordId.setStyle("-fx-border-color: #00000000");
            PasswordError.setPrefHeight(0);
            ErrorId.setText("Username not found!");
            ErrorId.setStyle("-fx-text-fill: #E92929");
            ErrorId.setPrefHeight(USE_COMPUTED_SIZE);
            ErrorId.setPrefWidth(USE_COMPUTED_SIZE);
        }
    }

    /**
     * method that changes stages to the sign-up when clicked
     * @param ignoredActionEvent
     * @throws IOException
     */
    public void UrlClicked(ActionEvent ignoredActionEvent) throws IOException {
        Stage closing = (Stage) LoginId.getScene().getWindow();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignUp.fxml")));
        primaryStage.setTitle("Hospital sign up");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("C:\\Users\\Svage\\IdeaProjects\\projekatB\\src\\main\\resources\\Images\\v987-18a.jpg"));
        primaryStage.show();
        closing.close();
    }

    /**
     * method that returns the current user
     * @return
     */
    public Doctor getDoctor(){
        return doctor;
    }
}
