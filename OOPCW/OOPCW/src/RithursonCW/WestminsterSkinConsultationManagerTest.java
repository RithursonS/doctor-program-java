package RithursonCW;
/**
 * Shanmugaraja Rithurson
 * IIT NO:2019702
 * Uow : w1810200
 */
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WestminsterSkinConsultationManagerTest {

    @Test
    public void addDoctorTest() {
        String input = "Joe\nBiden\n2000\n10\n10\n0784841512\n0001\nsample\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager consultationManager = new WestminsterSkinConsultationManager(inputStream);
        Doctor doctor = consultationManager.addDoctor();
        assertEquals("0001", doctor.getMedicalLicenceNumber());
        assertEquals("Joe", doctor.getName());
        assertEquals("Biden", doctor.getSurName());
        assertEquals("0784841512", doctor.getMobileNumber());
        assertEquals("sample", doctor.getSpecialisation());
    }

    @Test
    public void deleteDoctorTest() {
        String input = "Justin\n852147963\nY";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager consultationManager = new WestminsterSkinConsultationManager(inputStream);
        Doctor doctor = consultationManager.deleteDoctor();
        assertEquals("Justin", doctor.getName());
        assertEquals("852147963", doctor.getMedicalLicenceNumber());
    }

    @Test
    public void printDoctorTest() {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager consultationManager = new WestminsterSkinConsultationManager(inputStream);
        List<Doctor> doctorsInSystem = consultationManager.getDoctorsList();
        List<Doctor> doctors = consultationManager.displayDoctorsOrderedList();
        assertEquals(doctorsInSystem, doctors);
    }

    @Test
    public void storeDoctorTest() {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager consultationManager = new WestminsterSkinConsultationManager(inputStream);
        consultationManager.storeToFile();
    }

    @Test
    public void loadTheFileTest() {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager consultationManager = new WestminsterSkinConsultationManager(inputStream);
        consultationManager.loadTheFile();
    }
}
