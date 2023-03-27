package RithursonCW;
/**
 * Shanmugaraja Rithurson
 * IIT NO:2019702
 * Uow : w1810200
 */
import java.util.Scanner;

public class SkinConsultationCentre {

    /**
     * Creating object for WestminsterSkinConsultationManager
     * Creating a menu and accessing WestminsterSkinConsultationManager methods
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager(System.in);
        System.out.println("\n\n|------------Westminster Skin Consultation Manager--------------|\n\n");

        String userOption;

        do {
            westminsterSkinConsultationManager.printMenu();
            System.out.print("Choose your option from the menu: ");
            userOption = scanner.nextLine().toUpperCase();

            switch (userOption) {
                case "A":
                    westminsterSkinConsultationManager.addDoctor();
                    break;
                case "D":
                    westminsterSkinConsultationManager.deleteDoctor();
                    break;
                case "P":
                    westminsterSkinConsultationManager.displayDoctorsOrderedList();
                    break;
                case "S":
                    westminsterSkinConsultationManager.storeToFile();
                    break;
                case "L":
                    westminsterSkinConsultationManager.loadTheFile();
                    break;
                case "G":
                    westminsterSkinConsultationManager.launchGui();
                    break;
                case "X":
                    break;
                default:
                    System.out.println("Enter a correct option!");
                    break;
            }

        } while (!userOption.equals("X"));
    }

}
