package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Exceptions.HospitalException;
import ba.unsa.etf.rpr.domain.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class SignUp implements Initializable {

    public ChoiceBox<String> SpecId;
    public Spinner<Integer> SeniorityId;
    public TextField NameId;
    public TextField UsernameId;
    public PasswordField PasswordId;
    public PasswordField RepeatId;
    public Button CreateId;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpecId.setValue("--Select value--");
        SpecId.getItems().setAll("Anatomical Pathology", "Anesthesiology", "Cardiology", "Cardiovascular/Thoracic Surgery", "Clinical Immunology/Allergy", "Critical Care Medicine", "Dermatology", "Diagnostic Radiology", "Emergency Medicine", "Endocrinology and Metabolism", "Family Medicine", "Gastroenterology", "General Internal Medicine", "General Surgery", "General/Clinical Pathology", "Geriatric Medicine", "Hematology", "Medical Biochemistry", "Medical Genetics", "Medical Microbiology and Infectious Diseases", "Medical Oncology", "Nephrology", "Neurology", "Neurosurgery", "Nuclear Medicine", "Obstetrics/Gynecology", "Occupational Medicine", "Ophthalmology", "Orthopedic Surgery", "Otolaryngology", "Pediatrics", "Physical Medicine and Rehabilitation (PM & R)", "Plastic Surgery", "Psychiatry", "Public Health and Preventive Medicine (PhPm)", "Radiation Oncology", "Respirology", "Rheumatology", "Urology");
        SpinnerValueFactory<Integer> spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,45,0);
        this.SeniorityId.setValueFactory(spin);
        SeniorityId.setEditable(true);
    }

    public void CreateClicked(ActionEvent actionEvent) throws HospitalException {
        if(NameId.getText().trim().isEmpty()){
            label1.setPrefHeight(USE_COMPUTED_SIZE);
            if(SpecId.getSelectionModel().getSelectedItem().isEmpty()) {

            }
        }
        Doctor d1 = new Doctor();
        d1.setName(NameId.getText());
        d1.setSpecialization(SpecId.getSelectionModel().getSelectedItem());
        d1.setSeniority(SeniorityId.getValue());
        d1.setUsername(UsernameId.getText());
        d1.setPassword(PasswordId.getText());
        DaoFactory.DoctorDao().add(d1);
    }
}
