package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Business.ExaminationManager;
import ba.unsa.etf.rpr.Business.PatientManager;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * JavaFX controller for showing home screen and the patients of the user
 *
 * @author Berin Karahodžić
 */

public class Home implements Initializable {
    //window fields
    public TableView<PatientExam> tableViewId;
    public TableColumn<PatientExam, String> patientId;
    public TableColumn<PatientExam, String> diagnosisId;
    public Button searchId;
    public TextField nameSearch;
    public Button allPatientsId;
    public Button myExaminationsId;

    /**
     * initialize method to set up the tableview
     * @param url
     * @param resourceBundle
     */
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

    /**
     * method for loading the patient's info
     * @param name
     * @return
     * @throws IOException
     * @throws HospitalException
     */
    public ObservableList<PatientExam> getPatientInfo(String name) throws IOException, HospitalException {
        ExaminationManager examinationManager = new ExaminationManager();
        PatientManager patientManager = new PatientManager();
        ObservableList<PatientExam> patientInfo = FXCollections.observableArrayList();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/LogIn.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        LogIn controller = loader.getController();
        String user = controller.getDoctor().getUsername();
        List<Patient> patients = patientManager.getAll();
        List<Examination> exams = examinationManager.getByDoctor(user);
        for(Examination e: exams){
            for(Patient p : patients){
                if(e.getPatient().equals(p) && name.equals(""))patientInfo.add(new PatientExam(p.getName(),e.getDiagnosis()));
                else if(e.getPatient().equals(p) && e.getPatient().getName().contains(name))patientInfo.add(new PatientExam(p.getName(),e.getDiagnosis()));
            }
        }
        return patientInfo;
    }

    /**
     * button for searching up a certain patient
     * @param actionEvent
     * @throws HospitalException
     * @throws IOException
     */
    public void SearchForPatient(ActionEvent actionEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    /**
     * method does the same as clicking the search button when enter is pressed
     * @param keyEvent
     * @throws HospitalException
     * @throws IOException
     */
    public void KEyPressed(KeyEvent keyEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    /**
     * method for loading the AllPatients scene to the window
     * @param actionEvent
     * @throws IOException
     */
    public void AllPatients(ActionEvent actionEvent) throws IOException {
        Parent allpateints = FXMLLoader.load(getClass().getResource("/fxml/AllPatients.fxml"));
        Scene allpatientsscene = new Scene (allpateints);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(allpatientsscene);
    }

    /**
     * method for loading the home-screen to the window
     * @param actionEvent
     * @throws IOException
     */
    public void MyExaminations(ActionEvent actionEvent) throws IOException {
        Parent myexams = FXMLLoader.load(getClass().getResource("/fxml/MyExaminations.fxml"));
        Scene myexamsscene = new Scene (myexams);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(myexamsscene);
    }
}
