package ba.unsa.etf.rpr.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class SignUp {

    public ChoiceBox SpecId;
    @FXML
    private void Initialize(){
        SpecId.getItems().addAll("Anatomical Pathology", "Anesthesiology", "Cardiology", "Cardiovascular/Thoracic Surgery", "Clinical Immunology/Allergy",
                "Critical Care Medicine",
                "Dermatology",
                "Diagnostic Radiology",
                "Emergency Medicine",
                "Endocrinology and Metabolism",
                "Family Medicine",
                "Gastroenterology",
                "General Internal Medicine",
                "General Surgery",
                "General/Clinical Pathology",
                "Geriatric Medicine",
                "Hematology",
                "Medical Biochemistry",
                "Medical Genetics",
                "Medical Microbiology and Infectious Diseases",
                "Medical Oncology",
                "Nephrology",
                "Neurology",
                "Neurosurgery",
                "Nuclear Medicine",
                "Obstetrics/Gynecology",
                "Occupational Medicine",
                "Ophthalmology",
                "Orthopedic Surgery",
                "Otolaryngology",
                "Pediatrics",
                "Physical Medicine and Rehabilitation (PM & R)",
                "Plastic Surgery",
                "Psychiatry",
                "Public Health and Preventive Medicine (PhPm)", "Radiation Oncology", "Respirology", "Rheumatology", "Urology");
    }
}
