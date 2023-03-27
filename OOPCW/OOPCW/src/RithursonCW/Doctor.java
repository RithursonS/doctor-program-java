package RithursonCW;

import java.util.Date;

/**
 * Shanmugaraja Rithurson
 * IIT NO:2019702
 * Uow : w1810200
 */
public class Doctor extends Person {

    /**
     * Initializing variable for Doctor class
     */
    private String medicalLicenceNumber;
    private String specialisation;
    private Date availDate;


    //Constructor
    public Doctor(String name, String surName, Date dateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation, Date availDate) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
        this.availDate = availDate;
    }

    //Getters and Setters
    public Date getAvailDate() {
        return availDate;
    }

    public void setAvailDate(Date availDate) {
        this.availDate = availDate;
    }

    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}