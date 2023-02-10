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
                RowConstraints con = new RowConstraints();
                UsernameId.setStyle("-fx-border-color: #E92929");
                ErrorId.setText("Invalid username!");
                ErrorId.setStyle("-fx-text-fill: #E92929");
                ErrorId.setPrefHeight(USE_COMPUTED_SIZE);
                ErrorId.setPrefWidth(USE_COMPUTED_SIZE);
                con.setPrefHeight(USE_COMPUTED_SIZE);
                WindowId.getRowConstraints().set(2, con);
                return;
            }
            Doctor doc = DaoFactory.DoctorDao().getByUsername(UsernameId.getText());
            if(doc.getPassword().equals(PasswordId.getText())){

            }

        } catch (HospitalException e) {
            RowConstraints con = new RowConstraints();
            UsernameId.setStyle("-fx-border-color: #E92929");
            ErrorId.setText("Username not found!");
            ErrorId.setStyle("-fx-text-fill: #E92929");
            ErrorId.setPrefHeight(USE_COMPUTED_SIZE);
            ErrorId.setPrefWidth(USE_COMPUTED_SIZE);
            con.setPrefHeight(USE_COMPUTED_SIZE);
            WindowId.getRowConstraints().set(2, con);
            return;
        }
    }
}
