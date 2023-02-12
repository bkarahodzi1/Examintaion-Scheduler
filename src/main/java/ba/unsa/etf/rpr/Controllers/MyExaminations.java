package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Examination;
import ba.unsa.etf.rpr.Domain.Patient;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
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

public class MyExaminations implements Initializable {
    public TableView<Examination> tableViewId;
    public TableColumn<Examination, String> DateId;
    public TableColumn<Examination, String> PatientId;
    public TableColumn<Examination, String> DiagnosisId;
    public TableColumn<Examination, String> TreatmentId;
    public TextField nameSearch;
    public Button searchId;
    public Button myPatientsId;
    public Button allPatientsId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DateId.setCellValueFactory(new PropertyValueFactory<Examination, String>("date"));
            PatientId.setCellValueFactory(new PropertyValueFactory<Examination, String>("patient"));
            DiagnosisId.setCellValueFactory(new PropertyValueFactory<Examination, String>("diagnosis"));
            TreatmentId.setCellValueFactory(new PropertyValueFactory<Examination, String>("treatment"));
            tableViewId.setItems(getPatientInfo(""));
        } catch (IOException | HospitalException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Examination> getPatientInfo(String name) throws IOException, HospitalException {
        ObservableList<Examination> patientInfo = FXCollections.observableArrayList();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/LogIn.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        LogIn controller = loader.getController();
        String user = controller.getDoctor().getUsername();
        List<Examination> exams = DaoFactory.ExaminationDao().getAll();
        for(Examination e : exams) {
            if((name.equals("") || e.getPatient().getName().equals(name)) && e.getDoctor().getUsername().equals(user))
                patientInfo.add(new Examination(e.getDate(), e.getPatient(),e.getDiagnosis(),e.getTreatment()));
        }
        return patientInfo;
    }

    public void SearchForPatient(ActionEvent actionEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    public void KEyPressed(KeyEvent keyEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }


    public void AllPatients(ActionEvent actionEvent) throws IOException {
        Parent allpateints = FXMLLoader.load(getClass().getResource("/fxml/AllPatients.fxml"));
        Scene allpatientsscene = new Scene (allpateints);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(allpatientsscene);
    }
    public void MyPatients(ActionEvent actionEvent) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene homescene = new Scene (home);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(homescene);
    }
}
