package system;

import objects.MedicalCard;
import objects.MedicalTest;
import users.MedicalStaff;
import users.Patient;
import users.RegisteredPatient;
import users.Therapist;

import java.io.*;
import java.util.ArrayList;

public class MedicalDatabase {

    ArrayList<MedicalCard> MedicalCardList;
    ArrayList<MedicalStaff> MedicalStaffList;
    ArrayList<MedicalTest> MedicalTestList;
    ArrayList<MedicalCard> MedicalCardRecordsList;

    public MedicalDatabase(){
        init();
    }

    public void init(){
        this.MedicalCardList = new ArrayList<>();
        this.MedicalStaffList = new ArrayList<>();
        this.MedicalTestList = new ArrayList<>();
        this.MedicalCardRecordsList = new ArrayList<>();
        File staff = new File("staff.txt");
        if (!staff.exists()){
            try {
                staff.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try(FileReader fr = new FileReader(staff))
        {
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line == null){
                line = "endend";
            }
            while (!line.equals("endend")){
                String name = reader.readLine();
                reader.readLine();
                String type = reader.readLine();
                reader.readLine();
                ArrayList<String> freeShedule = new ArrayList<>();
                line = reader.readLine();
                while (!line.equals("Busy:")){
                    freeShedule.add(line);
                    line = reader.readLine();
                }
                ArrayList<String> busyShedule = new ArrayList<>();
                line = reader.readLine();
                while (!line.equals("Info:")){
                    busyShedule.add(line);
                    line = reader.readLine();
                }
                line = reader.readLine();
                ArrayList<String> info = new ArrayList<>();
                while (!line.equals("end")){
                    info.add(line);
                    line = reader.readLine();
                }
                line = reader.readLine();
                MedicalStaffList.add(new MedicalStaff(name,type, freeShedule, busyShedule, info));
            }
            reader.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        File cards = new File("cards.txt");
        if (!cards.exists()){
            try {
                cards.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try(FileReader fr = new FileReader(cards))
        {
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line == null){
                line = "endend";
            }
            while (!line.equals("endend")){
                String name = reader.readLine();
                reader.readLine();
                int passport = Integer.parseInt(reader.readLine());
                reader.readLine();
                String pathologies = reader.readLine();
                reader.readLine();
                String nameD = reader.readLine();
                ArrayList<String> info = new ArrayList<>();
                reader.readLine();
                line = reader.readLine();
                while (!line.equals("end")){
                    info.add(line);
                    line = reader.readLine();
                }
                line = reader.readLine();
                if (checkDoctor(nameD)){
                    MedicalCardList.add(new MedicalCard(new RegisteredPatient(name, passport, pathologies, info), getDoctor(nameD)));
                }
                else {
                    MedicalCardList.add(new MedicalCard(new RegisteredPatient(name, passport, pathologies, info)));
                }
            }
            reader.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        File tests = new File("tests.txt");
        if (!tests.exists()){
            try {
                tests.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try(FileReader fr = new FileReader(tests))
        {
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line == null){
                line = "endend";
            }
            while (!line.equals("endend")){
                String name = reader.readLine();
                ArrayList<String> info = new ArrayList<>();
                line = reader.readLine();
                while (!line.equals("end")){
                    info.add(line);
                    line = reader.readLine();
                }
                line = reader.readLine();
                MedicalTestList.add(new MedicalTest(name,info));
            }
            reader.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        for (MedicalCard card : MedicalCardList){
            if (checkDoctor(card.getDoctorName())){
                MedicalCardRecordsList.add(card);
            }
        }

    }

    public boolean checkPatient(String name) {
        for (MedicalCard card : MedicalCardList) {
            if (card.getPatient().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public Patient getPatient(String name) {
        for (MedicalCard card : MedicalCardList) {
            if (card.getPatient().getName().equals(name)) {
                return card.getPatient();
            }
        }
        return null;
    }

    public void newPatient(Patient patient) {
        MedicalCardList.add(new MedicalCard(patient));
    }

    public String checkMedicalRecords(String name) {
        for (MedicalCard card : MedicalCardList) {
            if (card.getPatient().getName().equals(name)) {
                return card.curator();
            }
        }
        return null;
    }

    public ArrayList<MedicalStaff> getMedicalStaffList(){
        return MedicalStaffList;
    }


    public boolean checkDoctor(String name) {
        for (MedicalStaff doctor : MedicalStaffList) {
            if (doctor.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public MedicalStaff getDoctor(String name) {
        for (MedicalStaff doctor : MedicalStaffList) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }


    public void newDoctor(String name, String type, ArrayList<String> schedule) {
        MedicalStaffList.add(new MedicalStaff(name,type, schedule, new ArrayList<>(), new ArrayList<>()));
    }
    public void deleteDoctor(MedicalStaff doctor){
        MedicalStaffList.remove(doctor);
    }

    public void save() {
        try {
            PrintWriter writer = new PrintWriter("staff.txt", "UTF-8");
            for (MedicalStaff staff : MedicalStaffList){
                writer.println("Name:");
                writer.println(staff.getName());
                writer.println("Type:");
                writer.println(staff.getType());
                writer.println("Free:");
                for (String string : staff.getFreeSchedule()){
                    writer.println(string);
                }
                writer.println("Busy:");
                for (String string : staff.getBusySchedule()){
                    writer.println(string);
                }
                writer.println("Info:");
                for (String string : staff.getInfo()){
                    writer.println(string);
                }
                writer.println("end");
            }
            writer.println("endend");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            PrintWriter writer = new PrintWriter("cards.txt", "UTF-8");
            for (MedicalCard card : MedicalCardList){
                writer.println("Name:");
                writer.println(card.getPatient().getName());
                writer.println("Passport:");
                writer.println(card.getPatient().getPassport());
                writer.println("Pathologies:");
                writer.println(card.getPatient().getPathologies());
                writer.println("Doctor:");
                writer.println(card.getDoctorName());
                writer.println("Info:");
                for (String string : card.getPatient().getInfo()){
                    writer.println(string);
                }
                writer.println("end");
            }
            writer.println("endend");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void newDoctor(Therapist therapist) {
        MedicalStaffList.add(therapist);
    }

    public void record(String name, String named) {
        for (MedicalCard card : MedicalCardList) {
            if (card.getPatient().getName().equals(name)) {
                card.newDoctor(getDoctor(named));
                card.getPatient().message("Вы поставлены на учет к " + named);
                getDoctor(named).setInfo("К вам поставлен на учет:" + name);
                MedicalCardRecordsList.add(card);
            }
        }
    }
    public void unrecord(String name) {
        for (MedicalCard card : MedicalCardRecordsList) {
            if (card.getPatient().getName().equals(name) && checkDoctor(card.getDoctorName())) {
                getDoctor(card.getDoctorName()).setInfo("С вашего учета убран:" + name);
                card.getPatient().message("Вы убраны с учета к " + card.getDoctorName());
                card.newDoctor(null);
                MedicalCardRecordsList.remove(card);
                break;
            }
        }
    }

    public ArrayList<MedicalTest> getMedicalTestList() {
        return MedicalTestList;
    }
}
