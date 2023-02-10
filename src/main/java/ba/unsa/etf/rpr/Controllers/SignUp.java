package ba.unsa.etf.rpr.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    public ChoiceBox SpecId;
    public Spinner SeniorityId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpecId.setValue("--Select value--");
        SpecId.getItems().setAll("Anatomical Pathology", "Anesthesiology", "Cardiology", "Cardiovascular/Thoracic Surgery", "Clinical Immunology/Allergy", "Critical Care Medicine", "Dermatology", "Diagnostic Radiology", "Emergency Medicine", "Endocrinology and Metabolism", "Family Medicine", "Gastroenterology", "General Internal Medicine", "General Surgery", "General/Clinical Pathology", "Geriatric Medicine", "Hematology", "Medical Biochemistry", "Medical Genetics", "Medical Microbiology and Infectious Diseases", "Medical Oncology", "Nephrology", "Neurology", "Neurosurgery", "Nuclear Medicine", "Obstetrics/Gynecology", "Occupational Medicine", "Ophthalmology", "Orthopedic Surgery", "Otolaryngology", "Pediatrics", "Physical Medicine and Rehabilitation (PM & R)", "Plastic Surgery", "Psychiatry", "Public Health and Preventive Medicine (PhPm)", "Radiation Oncology", "Respirology", "Rheumatology", "Urology");
        SpinnerValueFactory<Integer> spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,45,0);
        this.SeniorityId.setValueFactory(spin);
        SeniorityId.setEditable(true);
    }
}
