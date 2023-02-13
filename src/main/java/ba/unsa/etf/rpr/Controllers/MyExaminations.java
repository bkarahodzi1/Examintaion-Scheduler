package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Business.ExaminationManager;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/***
 * JavaFX controller for showing all the users examinations
 *
 * @author Berin Karahodžić
 */
public class MyExaminations implements Initializable {
    //window fields
    public TableView<Examination> tableViewId;
    public TableColumn<Examination, String> DateId;
    public TableColumn<Examination, String> PatientId;
    public TableColumn<Examination, String> DiagnosisId;
    public TableColumn<Examination, String> TreatmentId;
    public TextField nameSearch;
    public Button searchId;
    public Button myPatientsId;
    public Button allPatientsId;

    /**
     * initialize method that sets up the tableview
     * @param url
     * @param resourceBundle
     */
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

    /**
     * a method that returns items to be put in the tableview
     * @param name
     * @return
     * @throws IOException
     * @throws HospitalException
     */
    public ObservableList<Examination> getPatientInfo(String name) throws IOException, HospitalException {
        ExaminationManager examinationManager = new ExaminationManager();
        ObservableList<Examination> patientInfo = FXCollections.observableArrayList();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/LogIn.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        LogIn controller = loader.getController();
        String user = controller.getDoctor().getUsername();
        List<Examination> exams = examinationManager.getAll();
        for(Examination e : exams) {
            if((name.equals("") || e.getPatient().getName().equals(name)) && e.getDoctor().getUsername().equals(user))
                patientInfo.add(new Examination(e.getDate(), e.getPatient(),e.getDiagnosis(),e.getTreatment()));
        }
        return patientInfo;
    }

    /**
     * button that begins the search of patient entered in search field
     * @param actionEvent
     * @throws HospitalException
     * @throws IOException
     */
    public void SearchForPatient(ActionEvent actionEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    /**
     * does the same as clicking on search button
     * @param keyEvent
     * @throws HospitalException
     * @throws IOException
     */
    public void KEyPressed(KeyEvent keyEvent) throws HospitalException, IOException {
        tableViewId.setItems(getPatientInfo(nameSearch.getText()));
    }

    /**
     * method that changes the screen to AllPatients
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
     * method that changes the screen to home-screen
     * @param actionEvent
     * @throws IOException
     */
    public void MyPatients(ActionEvent actionEvent) throws IOException {
        Parent home = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
        Scene homescene = new Scene (home);
        Stage window = (Stage) searchId.getScene().getWindow();
        window.setScene(homescene);
    }
}
