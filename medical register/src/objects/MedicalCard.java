package objects;

import users.MedicalStaff;
import users.Patient;

public class MedicalCard {
    Patient patient;
    MedicalStaff doctor = null;

    public MedicalCard(Patient patient, MedicalStaff doctor){
        this.patient = patient;
        this.doctor = doctor;
    }

    public MedicalCard(Patient patient){
        this.patient = patient;
    }

    public Patient getPatient(){
        return patient;
    }

    public String getDoctorName(){
        if (doctor == null){
            return "None";
        }
        return doctor.getName();
    }

    public void newDoctor(MedicalStaff doctor){
        this.doctor=doctor;
    }

    public String curator(){
        if (doctor == null){
            return "Не поставлен на учет";
        }
        return ("На учете у доктора:" + doctor.getName());
    }
}
