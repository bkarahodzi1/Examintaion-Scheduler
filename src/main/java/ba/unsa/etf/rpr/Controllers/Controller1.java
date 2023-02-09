package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class Controller1 {
    public Button LoginId;
    public Hyperlink CreateAccId;
    public PasswordField PasswordId;
    public TextField UsernameId;

    public void LogInProcess(ActionEvent actionEvent) {
        try {
            List lista = DaoFactory.PatientDao().getAll();
            if(lista.size()==0){

                return;
            }
        } catch (HospitalException e) {
            throw new RuntimeException(e);
        }
    }
}
