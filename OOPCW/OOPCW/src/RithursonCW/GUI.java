package RithursonCW;
/**
 * Shanmugaraja Rithurson
 * IIT NO:2019702
 * Uow : w1810200
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    WestminsterSkinConsultationManager consultationManager;

    JTable consultationTable, doctorTable, sortDocTable;
    JTextField textFieldName, textFieldSurName, textFieldId, textFieldMobileNumber;
    JTextArea tadd, tout, resAdd;
    JButton sortDocBtn, docBtn, bookBtn, viewBtn, sub, reset;
    JLabel docLabel, bookLabel, viewLabel, imgLabel;
    JLabel title, name, surName, pId, mno, dob, add, res, takenConsult, docNames, conDate;
    JComboBox<String> date, month, year, avDate, avMonth, avYear, docCombo;
    JRadioButton firstTime, before;
    ButtonGroup consulG;
    JCheckBox term;
    DefaultTableModel docModel, viewModel, sortDocModel;
    JScrollPane docScroll, viewScroll, sortedDocScroll;
    JFrame docMenu, bookMenu, viewMenu, sortedDocMenu;
    ImageIcon icon;


    /**
     * dates , months, year for combo box DOB
     */
    String[] dates = {
            "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13", "14",
            "15", "16", "17", "18", "19", "20", "21",
            "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31"
    };
    String[] months = {
            "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sep", "Oct", "Nov", "Dec"
    };
    String[] years = {
            "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022"
    };


    /**
     * dates , months, year for combo box appointment
     */
    String[] availableYear = { "2023" };
    String[] availableMonth = { "Jan" };
    String[] availableDates = { "1", "2", "3", "4", "5", "6", "7" };

    public GUI() {
        consultationManager = new WestminsterSkinConsultationManager(System.in);



        //Creating Jfram
        docMenu = new JFrame("DOCTOR TABLE");
        docMenu.setLayout(new FlowLayout());
        docMenu.setName("DOCTOR TABLE ");
        docMenu.setSize(850, 700);
        docMenu.setBackground(Color.lightGray);

        sortedDocMenu = new JFrame("DOCTOR TABLE");
        sortedDocMenu.setLayout(new FlowLayout());
        sortedDocMenu.setName("DOCTOR TABLE ");
        sortedDocMenu.setSize(850, 700);
        sortedDocMenu.setBackground(Color.lightGray);

        bookMenu = new JFrame("BOOKING TABLE");
        bookMenu.setBounds(300, 90, 900, 800);

        viewMenu = new JFrame("VIEW TABLE");
        viewMenu.setLayout(new FlowLayout());
        viewMenu.setName("VIEW TABLE");
        viewMenu.setSize(900, 700);
        viewMenu.setBackground(Color.lightGray);

        icon = new ImageIcon("pic.png");

        imgLabel = new JLabel(icon);
        docLabel = new JLabel("--------------------------------------------------------------------------------DOCTOR TABLE--------------------------------------------------------------------------------");
        bookLabel = new JLabel("--------------------------------------------------------------------------------APPOINTMENT TABLE--------------------------------------------------------------------------------");
        viewLabel = new JLabel("--------------------------------------------------------------------------------VIEW TABLE--------------------------------------------------------------------------------");

        title = getLabel("Consultation Form", 30, 900, 700, 150, -300);

        name = getLabel("Name", 18, 100, 20, 100, 100);
        textFieldName = getTextField(190, 100);

        surName = getLabel("Sur Name", 18, 100, 20, 100, 150);
        textFieldSurName = getTextField(190, 150);

        pId = getLabel("Patient ID", 18, 100, 20, 100, 200);
        textFieldId = getTextField(150, 200);

        mno = getLabel("Mobile No", 18, 100, 20, 100, 250);
        textFieldMobileNumber = getTextField(150, 250);

        takenConsult = getLabel("Consultation", 18, 150, 20, 100, 300);
        firstTime = getRadioButton("First Time", 100, 220);
        before = getRadioButton("More than Once", 150, 320);

        consulG = new ButtonGroup();
        consulG.add(firstTime);
        consulG.add(before);

        dob = getLabel("DOB", 18, 100, 20, 100, 350);
        date = getComboBox(dates, 50, 200, 350);
        month = getComboBox(months, 60, 250, 350);
        year = getComboBox(years, 60, 320, 350);

        docNames = getLabel("Doctor Name", 18, 150, 20, 100, 400);
        docCombo = getComboBox(consultationManager.arrayDoc(), 100, 250, 400);

        conDate = getLabel("Appointment On", 18, 150, 20, 100, 450);
        avDate = getComboBox(availableDates, 50, 250, 450);
        avMonth = getComboBox(availableMonth, 60, 300, 450);
        avYear = getComboBox(availableYear, 60, 380, 450);

        add = getLabel("Notes", 18, 100, 20, 100, 500);
        tadd = getTextArea(200, 75, 200, 500);

        term = getCheckBox();

        sub = getButton("Submit", 150);
        sub.addActionListener(this);

        reset = getButton("Reset", 270);
        reset.addActionListener(this);

        tout = getTextArea(300, 400, 500, 100);
        tout.setEditable(false);

        res = getLabel("", 18, 500, 25, 100, 700);
        resAdd = getTextArea(200, 75, 580, 175);

        docBtn = getButton("DOCTOR TABLE", Color.orange);
        bookBtn = getButton("BOOK APPOINTMENT", Color.orange);
        viewBtn = getButton("VIEW APPOINTMENT TABLE", Color.orange);
        sortDocBtn = getButton("SORT TABLE", Color.gray);

        docBtn.addActionListener(this);
        bookBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        sortDocBtn.addActionListener(this);

        imgLabel.setVerticalAlignment(JLabel.CENTER);
        imgLabel.setHorizontalAlignment(JLabel.CENTER);

        add(imgLabel);
        add(docBtn);
        add(viewBtn);

        createDocTable();
    }

    private JLabel getLabel(String text, int fontSize, int width, int height, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        label.setSize(width, height);
        label.setLocation(x, y);
        return label;
    }

    private JTextField getTextField(int width, int y) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setSize(width, 20);
        textField.setLocation(200, y);
        return textField;
    }

    private JRadioButton getRadioButton(String text, int width, int x) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Arial", Font.PLAIN, 15));
        radioButton.setSelected(true);
        radioButton.setSize(width, 20);
        radioButton.setLocation(x, 300);
        return radioButton;
    }

    private JComboBox<String> getComboBox(String[] dates, int width, int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>(dates);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        comboBox.setSize(width, 20);
        comboBox.setLocation(x, y);
        return comboBox;
    }

    private JCheckBox getCheckBox() {
        JCheckBox checkBox = new JCheckBox("Accept Terms And Conditions.");
        checkBox.setFont(new Font("Arial", Font.PLAIN, 15));
        checkBox.setSize(250, 20);
        checkBox.setLocation(150, 600);
        return checkBox;
    }

    private JTextArea getTextArea(int width, int height, int x, int y) {
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea.setSize(width, height);
        textArea.setLocation(x, y);
        textArea.setLineWrap(true);
        return textArea;
    }

    private JButton getButton(String text, int x) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setSize(100, 20);
        button.setLocation(x, 650);
        return button;
    }

    private JButton getButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == docBtn) {
            docTable();
        } else if (e.getSource() == bookBtn) {
            bookTable();
        } else if (e.getSource() == viewBtn) {
            viewTable();
        } else if (e.getSource() == sub) {
            try {
                bookDoctor();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == reset) {
            btnReset();
        } else if (e.getSource() == sortDocBtn) {
            sortDocTable();
            sortedDocTable();
        }
    }

    public void sortedDocTable() {
        sortedDocMenu.add(docLabel);
        sortedDocMenu.add(sortedDocScroll);
        sortedDocMenu.add(sortDocBtn);
        sortedDocMenu.add(bookBtn);
        sortedDocMenu.setVisible(true);
        docMenu.setVisible(false);
        bookMenu.setVisible(false);
        viewMenu.setVisible(false);
    }

    public void docTable() {
        docMenu.add(docLabel);
        docMenu.add(docScroll);
        docMenu.add(sortDocBtn);
        docMenu.add(bookBtn);
        docMenu.setVisible(true);
        bookMenu.setVisible(false);
        viewMenu.setVisible(false);
        sortedDocMenu.setVisible(false);
    }

    public void bookTable() {
        bookMenu.setLayout(null);
        bookMenu.add(title);
        bookMenu.add(name);
        bookMenu.add(textFieldName);
        bookMenu.add(surName);
        bookMenu.add(textFieldSurName);
        bookMenu.add(pId);
        bookMenu.add(textFieldId);
        bookMenu.add(mno);
        bookMenu.add(textFieldMobileNumber);
        bookMenu.add(takenConsult);
        bookMenu.add(firstTime);
        bookMenu.add(before);
        bookMenu.add(dob);
        bookMenu.add(date);
        bookMenu.add(month);
        bookMenu.add(year);
        bookMenu.add(docNames);
        bookMenu.add(docCombo);
        bookMenu.add(conDate);
        bookMenu.add(avDate);
        bookMenu.add(avMonth);
        bookMenu.add(avYear);
        bookMenu.add(add);
        bookMenu.add(tadd);
        bookMenu.add(term);
        bookMenu.add(sub);
        bookMenu.add(reset);
        bookMenu.add(tout);
        bookMenu.add(res);
        bookMenu.add(resAdd);
        bookMenu.setVisible(true);
        docMenu.setVisible(false);
        viewMenu.setVisible(false);
        sortedDocMenu.setVisible(false);
    }

    public void viewTable() {
        // viewMenu.removeAll();
        viewMenu.add(viewLabel);
        viewMenu.add(viewScroll);
        viewMenu.setVisible(true);
        bookMenu.setVisible(false);
        docMenu.setVisible(false);
        sortedDocMenu.setVisible(false);
    }

    public void bookDoctor() throws ParseException {
        String name, surName, mobileNum, notes;
        int patientId;
        name = textFieldName.getText();
        surName = textFieldSurName.getText();
        patientId = Integer.parseInt(textFieldId.getText());
        mobileNum = textFieldMobileNumber.getText();
        notes = tadd.getText();

        int monD = 0;
        for (int i = 1; i < months.length; i++) {
            if (months[i].equals(month.getSelectedItem())) {
                monD = i;

            }
        }

        String monthD = Integer.toString(monD);
        Date dateDOB = formatter .parse(date.getSelectedItem() + "/" + monthD + "/" + year.getSelectedItem());

        String appoYear = "2023";
        String appoMonth = "01";
        String appoDate0 = (String) avDate.getSelectedItem();
        Date dateAv = formatter .parse(appoDate0 + "/" + appoMonth + "/" + appoYear );

        // checking the availability of the doctor
        // If the doctor is not available automatically another doctor will get allocated
        Doctor consultationDoctor = checkDoctorAvailability((String) docCombo.getSelectedItem(), dateAv);


        // Displaying user selected values for confirmation of the consultation
        int data5 = 0;
        if (term.isSelected()) {
            String data1;
            String data = "Name : " + textFieldName.getText() +
                    "\nSur Name :" + textFieldSurName.getText() +
                    "\nPatient Id :" + textFieldId.getText() +
                    "\nMobile : " + textFieldMobileNumber.getText() + "\n";
            if (firstTime.isSelected())
                data1 = "Consultation : First Time\nConsultation fees : 15$\n";
            else
                data1 = "Consultation : More than Once \nConsultation fees : 25$\n";
            String data2
                    = "DOB : "
                    + date.getSelectedItem()
                    + "/" + month.getSelectedItem()
                    + "/" + year.getSelectedItem()
                    + "\n";

            String data3 = "Doctor : " + consultationDoctor.getName() + "\n" + "Note : " + tadd.getText() + "\n";
            String data4
                    = "Appointment Date : "
                    + avDate.getSelectedItem()
                    + "/" + avMonth.getSelectedItem()
                    + "/" + avYear.getSelectedItem()
                    + "\n";
            if (firstTime.isSelected())
                data5 = 15;
            else
                data5 = 25;


            tout.setText(data + data1 + data2 + data3 + data4);
            tout.setEditable(false);
            res.setText("Registration Successfully..");
        } else {
            tout.setText("");
            resAdd.setText("");
            res.setText("Please accept the terms & conditions..");
        }

        Patient patient = new Patient(name, surName, dateDOB, mobileNum, patientId);
        Consultation consultation = new Consultation(consultationDoctor, patient, dateAv, data5, notes);
        consultationManager.getConsultation().add(consultation);

        // Calling consultation update table function
        createConTable();
    }



    public Doctor checkDoctorAvailability(String doctorName, Date dateAv){
        Doctor doctor = null;
        ArrayList<Doctor> tempDocList = new ArrayList();
        ArrayList<Doctor> DocArray = consultationManager.getDoctorsList();

        // Saving doctor who are free on the selected date
        for (Doctor doc : DocArray){
            Date docAvDate = doc.getAvailDate();
            String stringDateDocAv = formatter.format(docAvDate);
            String stringDatePatient = formatter.format(dateAv);

            if (stringDateDocAv.equals(stringDatePatient)){
                tempDocList.add(doc);
            }
        }

        for (Doctor doc1 : DocArray) {
            Date docAvDate = doc1.getAvailDate();
            String stringDateDocAv = formatter.format(docAvDate);
            String stringDatePatient = formatter.format(dateAv);

            if (doc1.getName().equals(doctorName) && stringDateDocAv.equals(stringDatePatient)) {
                doctor = doc1;
                break;

            }else if (stringDateDocAv.equals(stringDatePatient)){
                int randomIndex = (int) (Math.random() * tempDocList.size());
                doctor = tempDocList.get(randomIndex);
            }
        }

        return doctor;
    }

    public void btnReset() {
        String def = "";
        textFieldName.setText(def);
        textFieldSurName.setText(def);
        textFieldId.setText(def);
        tadd.setText(def);
        textFieldMobileNumber.setText(def);
        res.setText(def);
        tout.setText(def);
        term.setSelected(false);
        date.setSelectedIndex(0);
        month.setSelectedIndex(0);
        year.setSelectedIndex(0);
        resAdd.setText(def);
    }

    public void sortDocTable() {
        String[] columns = { "DOCTOR NAME ", "DOCTOR SURNAME", "DOCTOR SPECIALIZATION", "AVAILABLE DATE" };
        sortDocModel = new DefaultTableModel(columns, 0);
        sortDocTable = getTable(sortDocModel);
        sortedDocScroll = new JScrollPane(sortDocTable);
        ArrayList<Doctor> doctorArray = consultationManager.getDoctorsList();

        for (int i = 0; i < doctorArray.size(); i++) {
            for (int j = i + 1; j < doctorArray.size(); j++) {
                Doctor temp;
                if (doctorArray.get(j).getName().toLowerCase().compareTo(doctorArray.get(i).getName().toLowerCase()) < 0) {
                    temp = doctorArray.get(i);
                    doctorArray.set(i, doctorArray.get(j));
                    doctorArray.set(j, temp);
                }
            }
        }

        for (Doctor docT : doctorArray) {
            Date date2 = docT.getAvailDate();
            String strDateAv = formatter.format(date2);
            Object[] docRow = { docT.getName(), docT.getSurName(), docT.getSpecialisation(),  strDateAv };
            sortDocModel.addRow(docRow);
        }
    }

    public void createDocTable() {
        String[] columns = { "DOCTOR NAME ", "DOCTOR SURNAME", "DOCTOR SPECIALIZATION", "AVAILABLE DATE" };
        docModel = new DefaultTableModel(columns, 0);
        doctorTable = getTable(docModel);
        docScroll = new JScrollPane(doctorTable);

        for (Doctor docT : consultationManager.getDoctorsList()) {
            Date date2 = docT.getAvailDate();
            String strDateAv = formatter.format(date2);
            Object[] docRow = { docT.getName(), docT.getSurName(), docT.getSpecialisation(),  strDateAv };
            docModel.addRow(docRow);
        }
    }

    public void createConTable() {
        String[] columns = { "PATIENT NAME ", "DOCTOR NAME", "DOCTOR SPECIALIZATION", "APPOINTMENT DATE", "APPOINTMENT COST" };
        viewModel = new DefaultTableModel(columns, 0);
        consultationTable = getTable(viewModel);
        viewScroll = new JScrollPane(consultationTable);

        for (Consultation consultation : consultationManager.getConsultation()) {
            Date date = consultation.getDoctor().getAvailDate();
            String strDateAv = formatter.format(date);
            Object[] viewRow = {
                    consultation.getPatient().getName(),
                    consultation.getDoctor().getName(),
                    consultation.getDoctor().getSpecialisation(),
                    strDateAv,
                    consultation.getCost()
            };
            viewModel.addRow(viewRow);
        }
    }

    private JTable getTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(750, 200));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.ORANGE);
        return table;
    }
}
