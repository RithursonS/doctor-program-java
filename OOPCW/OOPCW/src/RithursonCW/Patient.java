package RithursonCW;
/**
 * Shanmugaraja Rithurson
 * IIT NO:2019702
 * Uow : w1810200
 */
import java.util.Date;

public class Patient extends Person {

    /**
     * Initializing variable for Patient class
     */
    private int id;


    public Patient(String name, String surName, Date dateOfBirth, String mobileNumber, int id) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
