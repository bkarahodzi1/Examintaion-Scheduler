package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    public TableView tableViewId;
    public TableColumn<Patient, String> patientId;
    public TableColumn<Examination, String> diagnosisId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientId.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
    }
}
