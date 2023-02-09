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

    public void LogInProcess(ActionEvent actionEvent) {
        Label lab = new Label();
        try{
            List<Doctor> lista = DaoFactory.DoctorDao().getAll();
            if(UsernameId.getText().trim().isEmpty()) {
                RowConstraints con = new RowConstraints();
                UsernameId.setStyle("-fx-border-color: #E92929");
                lab.setText("Invalid username!");
                lab.setStyle("-fx-text-fill: #E92929");
                WindowId.add(lab, 1, 2);
                con.setPrefHeight(USE_COMPUTED_SIZE);
                WindowId.getRowConstraints().set(2, con);
                return;
            }
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
        try{
            Doctor doc = DaoFactory.DoctorDao().getByUsername(UsernameId.getText());
        } catch (HospitalException e) {
            RowConstraints con = new RowConstraints();
            UsernameId.setStyle("-fx-border-color: #E92929");
            lab.setText("Username doesn't exist!");
            lab.setStyle("-fx-text-fill: #E92929");
            WindowId.add(lab, 1, 2);
            con.setPrefHeight(USE_COMPUTED_SIZE);
            WindowId.getRowConstraints().set(2, con);
            return;
        }
    }
}
