package RithursonCW;
/**
 * Shanmugaraja Rithurson
 * IIT NO:2019702
 * Uow : w1810200
 */
import java.util.ArrayList;
import java.util.List;

public interface SkinConsultationManager {
    void printMenu();

    Doctor addDoctor();

    Doctor deleteDoctor();

    List<Doctor> displayDoctorsOrderedList();

    void storeToFile();

    void loadTheFile();

    void launchGui();

    String[] arrayDoc();

    ArrayList<Consultation> getConsultation();

    ArrayList<Doctor> getDoctorsList();
}