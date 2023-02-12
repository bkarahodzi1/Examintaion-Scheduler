package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.Dao;
import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Doctor;
import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Domain.PatientExam;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Home implements Initializable {

    public TableView<PatientExam> tableViewId;
    public TableColumn<PatientExam, String> patientId;
    public TableColumn<PatientExam, String> diagnosisId;
    public Button searchId;
    public TextField nameSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            patientId.setCellValueFactory(new PropertyValueFactory<PatientExam, String>("name"));
            diagnosisId.setCellValueFactory(new PropertyValueFactory<PatientExam, String>("diagnosis"));
            tableViewId.setItems(getPatientInfo(""));
        } catch (IOException | HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<PatientExam> getPatientInfo(String name) throws IOException, HospitalException {
        ObservableList<PatientExam> patientInfo = FXCollections.observableArrayList();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/LogIn.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        LogIn controller = loader.getController();
        String user = controller.getDoctor().getUsername();
        List<Patient> patients = DaoFactory.PatientDao().getAll();
        List<Examination> exams = DaoFactory.ExaminationDao().getByDoctor(user);
        for(Examination e: exams){
            for(Patient p : patients){
                if(e.getPatient().equals(p) && name.equals(""))patientInfo.add(new PatientExam(p.getName(),e.getDiagnosis()));
                else if(e.getPatient().equals(p) && e.getPatient().getName().contains(name))patientInfo.add(new PatientExam(p.getName(),e.getDiagnosis()));
            }
        }
        return patientInfo;
    }

    public void SearchForPatient(ActionEvent actionEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    public void KEyPressed(KeyEvent keyEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }
}
