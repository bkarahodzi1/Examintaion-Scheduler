package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Domain.PatientExam;
import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {

    public TableView<PatientExam> tableViewId;
    public TableColumn<PatientExam, String> patientId;
    public TableColumn<PatientExam, String> diagnosisId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            patientId.setCellValueFactory(new PropertyValueFactory<PatientExam, String>("name"));
            diagnosisId.setCellValueFactory(new PropertyValueFactory<PatientExam, String>("diagnosis"));
            tableViewId.setItems(getPatientInfo());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<PatientExam> getPatientInfo() throws IOException {
        ObservableList<PatientExam> patientInfo = FXCollections.observableArrayList();

        patientInfo.add(new PatientExam());
        return patientInfo;
    }
}
