package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;
import ba.unsa.etf.rpr.domain.Patient;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller1 {
    public Button LoginId;
    public Hyperlink CreateAccId;
    public PasswordField PasswordId;
    public TextField UsernameId;
    public GridPane WindowId;
    public Label ErrorId;
    public Label PasswordError;

    public void LogInProcess(ActionEvent actionEvent) {
        try{
            List<Doctor> lista = DaoFactory.DoctorDao().getAll();
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
            Doctor doc = DaoFactory.DoctorDao().getByUsername(UsernameId.getText());
            if(doc.getPassword().equals(PasswordId.getText())){
                System.out.println("OK");
                UsernameId.setStyle("-fx-border-color: #00FF00 ");
                PasswordId.setStyle("-fx-border-color: #00FF00 ");
                ErrorId.setPrefHeight(0);
                PasswordError.setPrefHeight(0);
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
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
