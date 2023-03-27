package RithursonCW;
/**
 * Shanmugaraja Rithurson
 * IIT NO:2019702
 * Uow : w1810200
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    /**
     * creating methods for menu
     * implements the methods from the interface
     */
    private final Scanner scanner;
    private final ArrayList<Doctor> doctorsList = new ArrayList<>();
    private final ArrayList<Consultation> consultationList = new ArrayList<>();
    private final File doctorsFile = new File("doctors.txt");

    public WestminsterSkinConsultationManager(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        loadTheFile();
    }


    //To print menu
    @Override
    public void printMenu() {
        System.out.println();
        System.out.println("|------------------------------MENU-----------------------------|");
        System.out.println("                  A: Add a new doctor                           ");
        System.out.println("                  D: Delete a doctor                            ");
        System.out.println("                  P: Print the list of the doctors              ");
        System.out.println("                  S: Store data into a file                     ");
        System.out.println("                  L: Load data From a file                      ");
        System.out.println("                  G: Graphical User Interface                   ");
        System.out.println("                  X: Exit the menu                              ");
        System.out.println("|---------------------------------------------------------------|");
        System.out.println();

    }


    //Adding doctor to the arraylist
    @Override
    public Doctor addDoctor() {
        try {
            // Taking input from the user to add doctor name
            System.out.println("Doctor's Name: ");
            String name = scanner.next();
            System.out.println("Doctor's Surname: ");
            String surname = scanner.next();

            //Taking DOb of the details of the doctor
            System.out.println("Date of birth (year): ");
            String year = scanner.next();
            System.out.println("Date of birth (month): ");
            String month = scanner.next();
            System.out.println("Date of birth (day): ");
            String day = scanner.next();

            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            Date date = DateFor.parse(day + "/" + month + "/" + year);

            //Taking mobile number of the details of the doctor
            System.out.println(name +" "+ "Phone number: ");
            String phoneNumber = scanner.next();

            //Taking DOb of the details of the doctor
            System.out.println(name + " "+"Medical Licence Number: ");
            String medicalLicenceNumber = scanner.next();

            //Taking DOb of the details of the doctor
            System.out.println(name +" "+ "Specialisation: ");
            String specialisation = scanner.next();

            //Taking doctor available date
            String yearAv = "2023";
            String monthAv = "01";
            System.out.println(name +" "+ "Enter doctor available date (Within weekdays-> 1-7 [monday to sunday]): ");
            String dayAv = scanner.next();
            SimpleDateFormat DateForAv = new SimpleDateFormat("dd/MM/yyyy");
            Date dateAv = DateForAv.parse(dayAv + "/" + monthAv + "/" + yearAv);

            //Limiting doctor that can added to the list to 10
            Doctor doctor = new Doctor(name, surname, date, phoneNumber, medicalLicenceNumber, specialisation, dateAv);
            if (doctorsList.size() < 10) {
                doctorsList.add(doctor);
                System.out.println(" ");
                System.out.println("The Doctor Details added successfully!!!");
                return doctor;
            }else {
                System.out.println("Doctor capacity of 10 is full. Please remove a doctor and Add new one...");
            }
            return doctor;

        } catch (Exception exception) {
            System.out.println("Something went wrong. Please insert valid inputs and Try again...");
            System.out.println("--------------------------------------------------");
            return addDoctor();
        }

    }

    @Override
    public Doctor deleteDoctor() {

        /**
         * To remove a doctor from user getting the name and the medical licence no
         * if anyone is matches in the system we remove the doctor
         */

        System.out.println("Select the doctor from the below list to delete");
        printDoctorList();
        System.out.print("Enter the name of the doctor to be deleted: ");
        String docName = scanner.next();
        System.out.print("Enter the licence number of the doctor to be deleted: ");
        String docLicence = scanner.next();
        boolean identifier = false;

        for (Doctor doctor : doctorsList) {
            if (doctor.getName().equals(docName) || doctor.getMedicalLicenceNumber().equals(docLicence)) {
                System.out.println("Confirm to delete doctor: " + doctor.getName() + ", Enter Y or y to delete N or n to quit: ");
                String selection = scanner.next();
                if (selection.equalsIgnoreCase("Y") || selection.equalsIgnoreCase("y")) {
                    doctorsList.remove(doctor);
                    identifier = true;
                    System.out.println(docName +" was successfully deleted");
                    System.out.println("|----------------------------------------------------------|");
                    return doctor;
                } else {
                    System.out.println("-----------------Program Exiting-------------------- !!!!");
                }
            }
        }

        if (!identifier) {
            System.out.println("Mentioned doctor was not found");
        }

        return null;
    }

    // Printing the list of Doctors List

    /**
     * Displays Doctors List in Alphabetical order based on the surname
     */
    @Override
    public List<Doctor> displayDoctorsOrderedList() {
        for (int i = 0; i < doctorsList.size(); i++) {
            for (int j = i + 1; j < doctorsList.size(); j++) {
                Doctor temp;
                if (doctorsList.get(j).getSurName().toLowerCase().compareTo(doctorsList.get(i).getSurName().toLowerCase()) < 0) {
                    temp = doctorsList.get(i);
                    doctorsList.set(i, doctorsList.get(j));//swapping the name
                    doctorsList.set(j, temp);
                }
            }
        }

        System.out.println("|-----------------------------------------------LIST OF DOCTORS IN CONSULTATION CENTRE--------------------------------------------------------|");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|        NAME       |      SURNAME     |    DATE OF BIRTH    |  MOBILE NUMBER  |  MEDICAL LICENSE NUMBER  |  SPECIALISATION  | AVAILABLE DATE |");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (Doctor temp : doctorsList) {
            Date date1 = temp.getDateOfBirth();
            Date date2 = temp.getAvailDate();

            String strDate = formatter.format(date1);
            String strDateAv = formatter.format(date2);

            //String Formatting for presentation purposes
            System.out.printf("| %17s | %16s | %19s | %15s | %24s | %16s | %16s |%n",temp.getName(),temp.getSurName(),strDate,temp.getMobileNumber(),
                    temp.getMedicalLicenceNumber(),temp.getSpecialisation(), strDateAv);
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

        return doctorsList;
    }

    @Override
    public void storeToFile() {
        try {
            FileWriter myWriter = new FileWriter(doctorsFile);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            for (Doctor doctor : doctorsList) {
                Date dateDOB = doctor.getDateOfBirth();
                Date dateAv = doctor.getAvailDate();
                String strDate = formatter.format(dateDOB);
                String strDateAv = formatter.format(dateAv);

                String data = doctor.getName() + ","
                        + doctor.getSurName() + ","
                        + strDate + ","
                        + doctor.getMobileNumber() + ","
                        + doctor.getMedicalLicenceNumber() + ","
                        + doctor.getSpecialisation() + ","
                        + strDateAv  + "\n";
                myWriter.write(data);
            }
            myWriter.close();
        } catch (Exception exception) {
            System.out.println("---------------An error occurred----------------");
            exception.printStackTrace();
        }
        System.out.println("Inputs have stored Successfully!!!!");
    }

    @Override
    public void loadTheFile() {
        try {
            doctorsList.removeAll(doctorsList);
            Scanner myReader = new Scanner(doctorsFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArray = data.split(",");

                String name = dataArray[0];
                String surname = dataArray[1];
                String date = dataArray[2];
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                Date realDate = DateFor.parse(date);

                String phoneNumber = dataArray[3];
                String medicalLicenceNumber = dataArray[4];
                String specialisation = dataArray[5];
                String dateAv = dataArray[6];
                SimpleDateFormat DateForAv = new SimpleDateFormat("dd/MM/yyyy");
                Date realDateAv = DateForAv.parse(dateAv);

                Doctor doctor = new Doctor(name, surname, realDate, phoneNumber, medicalLicenceNumber, specialisation, realDateAv);
                doctorsList.add(doctor);
            }
            myReader.close();
            System.out.println("Data loaded Successfully!!!!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public void launchGui() {
        System.out.println("LAUNCHING GRAPHICAL USER INTERFACE....");
        GUI mainWindow = new GUI();
        mainWindow.setLayout(new FlowLayout());
        mainWindow.setVisible(true);
        mainWindow.setTitle("Consultation GUI");
        mainWindow.setSize(850, 650);
        ImageIcon image = new ImageIcon("logo.png"); //create image
        mainWindow.setIconImage(image.getImage()); //change icon to logo
        mainWindow.getContentPane().setBackground(Color.white);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scanner.nextLine();
    }

    @Override
    public ArrayList<Consultation> getConsultation() {

        return consultationList;
    }

    @Override
    public ArrayList<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public String[] arrayDoc() {
        int docCount = doctorsList.size();
        String[] sortedDoc = new String[docCount];
        for (int i = 0; i < sortedDoc.length; i++) {
            Doctor arr = doctorsList.get(i);
            sortedDoc[i] = arr.getName();
        }
        return sortedDoc;
    }


    /**
     * To display doctors in order to delete
     */
    public void printDoctorList() {
        System.out.println("|----------------------------------------------------------|");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < doctorsList.size(); i++) {
            Date date1 = doctorsList.get(i).getDateOfBirth();
            Date date2 = doctorsList.get(i).getAvailDate();
            String strDateDOB = formatter.format(date1);
            String strDateAv = formatter.format(date2);

            String line = (i + 1) + ".  "
                    + doctorsList.get(i).getName() + "  "
                    + doctorsList.get(i).getSurName() + "  "
                    + strDateDOB + "  "
                    + doctorsList.get(i).getMobileNumber() + "  "
                    + doctorsList.get(i).getMedicalLicenceNumber() + "  "
                    + doctorsList.get(i).getSpecialisation() + " "
                    + strDateAv;

            System.out.println(line);
        }
        System.out.println("|----------------------------------------------------------|");
    }
}

