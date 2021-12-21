package system;

import users.*;

import java.util.Scanner;

public class RegisterSystem {
    SystemMode mode;
    public MedicalDatabase database;
    Patient patient;
    public Scanner in;
    Registrar registrar;
    Therapist therapist;


    public RegisterSystem(MedicalDatabase database, Scanner in){
        this.database = database;
        this.in = in;
    }

    public void changeMode(SystemMode mode){
        this.mode = mode;
    }

    public void patientCommands(){
        System.out.println("Введите фамилию и имя");
        in.nextLine();
        String name = in.nextLine();
        if (checkPatient(name)){
            System.out.println("Добро пожаловать в систему, пациент" + name);
            this.patient = database.getPatient(name);
            registeredPatientCommands();
            return;
        }
        System.out.println("Здравствуйте, вы еще не зарегистрированы, но вы можете это сделать!");
        this.patient = new NewPatient(name);
        if (newPatientCommands()){
            System.out.println("Добро пожаловать в систему, пациент" + name);
            registeredPatientCommands();
        }
    }

    private boolean newPatientCommands() {
        int i = 0;
        while (true){
            System.out.println("Ваши действия:\n1.Зарегистрироваться\n2.Выйти");
            i = in.nextInt();
            switch(i){
                case (1):
                    registerPatient();
                    return true;
                case (2):
                    return false;
            }
        }

    }

    private void registerPatient() {
        System.out.println("Введите ваш номер паспорта:");
        int passport = in.nextInt();
        System.out.println("Введите вашу медицинскую информацию, если ее нет, введите 'нет':");
        in.nextLine();
        String pathologies = in.nextLine();
        if (pathologies.equals("нет")){
            pathologies = "None";
        }
        String name = patient.getName();
        patient = new RegisteredPatient(name);
        database.newPatient(patient);
        patient.setInfo(passport, pathologies);
    }

    private void registeredPatientCommands() {
        int k = 0;
        int i = 0;
        while (k!=5){
            String pathologies = "";
            int passport = 0;
            System.out.println("Ваши действия:\n1.Поменять свою информацию\n2.Посмотреть сообщения\n3.Посмотреть свою мединформацию\n4.Отказаться от приема\n5.Выйти");
            k = in.nextInt();
            switch (k){
                case (1):
                    while (i!=4){
                        System.out.println("1.Поменять паспорт\n2.Поменять мед.инфо\n3.Добавить мед.инфо\n4.Выйти");
                        i = in.nextInt();
                        switch (i){
                            case (1):
                                System.out.println("Введите новый номер паспорта:");
                                passport = in.nextInt();
                                patient.setPassport(passport);
                                continue;
                            case (2):
                                System.out.println("Введите информацию");
                                in.nextLine();
                                pathologies = in.nextLine();
                                patient.setPathologies(pathologies);
                                continue;
                            case (3):
                                System.out.println("Введите информацию");
                                in.nextLine();
                                pathologies = in.nextLine();
                                patient.addPathologies(pathologies);
                                continue;
                        }
                    }
                    continue;
                case (2):
                    System.out.println(patient.getMessages());
                    continue;
                case (3):
                    System.out.println(patient.getMedInfo());
                    System.out.println(database.checkMedicalRecords(patient.getName()));
                    continue;
                case (4):
                    System.out.println("Введите имя врача, от чьего приема отказываетесь");
                    in.nextLine();
                    String name = in.nextLine();
                    if (database.checkDoctor(name)){
                        System.out.println("Введите время приема:");
                        String time = in.nextLine();
                        patient.message("Отказался от приема на " + time);
                        database.getDoctor(name).setInfo("Отказались от приема на " + time);
                        database.getDoctor(name).addJob(time);
                        database.getDoctor(name).deleteTask(time);
                    }
                    continue;
            }
        }

    }

    public boolean checkPatient(String name) {
        return database.checkPatient(name);
    }

    public void registrarCommands(Registrar registrar) {
        this.registrar = registrar;
        int k = 0;
        while (k!=4){
            System.out.println("Ваши действия:\n1.Составить расписание\n2.Записать пациента к врачу\n3.Посмотреть информацию о пациенте\n4.Выйти");
            k = in.nextInt();
            switch (k){
                case(1):
                    registrar.createSchedule();
                    continue;
                case (2):
                    registrar.makeAppointment();
                    continue;
                case (3):
                    System.out.println("Введите имя пациента");
                    in.nextLine();
                    String name = in.nextLine();
                    Patient patient = database.getPatient(name);
                    System.out.println(patient.getMessages());
                    System.out.println(patient.getName());
                    System.out.println(patient.getMedInfo());
                    System.out.println(database.checkMedicalRecords(patient.getName()));
                    continue;
            }
        }
    }

    public void therapistCommands() {
        System.out.println("Введите ваше имя:");
        in.nextLine();
        String namet = in.nextLine();
        if (!database.checkDoctor(namet)){
            return;
        }
        if (!database.getDoctor(namet).getType().equals("Терапевт")){
            for (String line : database.getDoctor(namet).getInfo()){
                System.out.println(line);
            }
            return;
        }
        MedicalStaff doc = database.getDoctor(namet);
        this.therapist = new Therapist(doc.getName(), doc.getType(), doc.getFreeSchedule(), doc.getBusySchedule(), doc.getInfo());
        database.deleteDoctor(doc);
        database.newDoctor(therapist);
        int i = 0;
        while (i!=5){
            System.out.println("Ваши действия:\n1.Управлять учетом\n2.Записать пациента на анализы\n3.Посмотреть информацию о пациенте\n4.Посмотреть сообщения\n5.Выйти");
            i = in.nextInt();
            switch (i){
                case (1):
                    therapist.control(in, database);
                    continue;
                case (2):
                    therapist.analise(in, database);
                    continue;
                case (3):
                    System.out.println("Введите имя пациента");
                    in.nextLine();
                    String name = in.nextLine();
                    Patient patient = database.getPatient(name);
                    System.out.println(patient.getMessages());
                    System.out.println(patient.getName());
                    System.out.println(patient.getMedInfo());
                    System.out.println(database.checkMedicalRecords(patient.getName()));
                    continue;
                case(4):
                    for (String line : database.getDoctor(namet).getInfo()){
                        System.out.println(line);
                    }
                    continue;
            }
        }

    }
}
