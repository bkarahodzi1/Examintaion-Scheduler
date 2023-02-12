package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Home {

    public TableView tableViewId;
    public TableColumn<Patient, String> patientId;
    public TableColumn<Examination, String> diagnosisId;
}
