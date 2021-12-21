package users;

import system.RegisterSystem;

import java.util.ArrayList;

public class Registrar {
    String name;
    RegisterSystem system;

    public Registrar(String name, RegisterSystem system) {
        this.name = name;
        this.system = system;
    }

    public void createSchedule() {
        for (MedicalStaff doctor : system.database.getMedicalStaffList()) {
            System.out.println(doctor.getName());
            System.out.println(doctor.getType());
            for (String string : doctor.getFreeSchedule()) {
                System.out.println(string);
            }
        }
        int i = 0;
        int k = 0;
        String name = "";
        String line = "";
        String line2 = "";
        MedicalStaff doctor = null;
        while (i != 2) {
            System.out.println("Хотите обновить расписание врача?\n1.Да\n2.Нет");
            i = system.in.nextInt();
            if (i == 1) {
                System.out.println("Введите имя врача:");
                system.in.nextLine();
                name = system.in.nextLine();
                if (system.database.checkDoctor(name)) {
                    doctor = system.database.getDoctor(name);
                    for (String string : doctor.getFreeSchedule()) {
                        System.out.println(string);
                    }
                    while (k != 4) {
                        System.out.println("1.Добавить 2.Поменять 3.Удалить 4.Принять");
                        k = system.in.nextInt();
                        switch (k) {
                            case (1):
                                system.in.nextLine();
                                line = system.in.nextLine();
                                doctor.addJob(line);
                                continue;
                            case (2):
                                System.out.println("C:");
                                system.in.nextLine();
                                line = system.in.nextLine();
                                System.out.println("На:");
                                line2 = system.in.nextLine();
                                doctor.changeJob(line, line2);
                                continue;
                            case (3):
                                system.in.nextLine();
                                line = system.in.nextLine();
                                doctor.deleteJob(line);
                                continue;
                        }
                    }
                    for (String string2 : doctor.getFreeSchedule()) {
                        System.out.println(string2);
                    }
                } else {
                    System.out.println("Неправильное имя");
                }
            }
        }
        i = 0;
        String type = "";
        while (i != 2) {
            System.out.println("Добавить доктора?\n1.Да\n2.Нет");
            i = system.in.nextInt();
            if (i == 1) {
                System.out.println("Напишите имя:");
                system.in.nextLine();
                name = system.in.nextLine();
                System.out.println("Специальность");
                type = system.in.nextLine();
                System.out.println("Вводите расписание, когда хватит, введите q");
                String str = system.in.nextLine();
                ArrayList<String> schedule = new ArrayList<>();
                while (!str.equals("q")) {
                    schedule.add(str);
                    str = system.in.nextLine();
                }
                system.database.newDoctor(name, type, schedule);
            }
        }
        for (MedicalStaff doctor2 : system.database.getMedicalStaffList()) {
            System.out.println(doctor2.getName());
            System.out.println(doctor2.getType());
            for (String string : doctor2.getFreeSchedule()) {
                System.out.println(string);
            }
        }
        i = 0;
        while (i!=2) {
            System.out.println("Надо удалить доктора?\n1.Да\n2.Нет");
            i = system.in.nextInt();
            if (i == 1) {
                System.out.println("Напишите имя");
                system.in.nextLine();
                name = system.in.nextLine();
                if (system.database.checkDoctor(name)){
                    system.database.deleteDoctor(system.database.getDoctor(name));
                }
                else{
                    System.out.println("Нет такого доктора");
                }
            }
        }
        for (MedicalStaff doc : system.database.getMedicalStaffList()) {
            System.out.println(doc.getName());
            System.out.println(doc.getType());
            for (String string : doc.getFreeSchedule()) {
                System.out.println(string);
            }
        }
        System.out.println("Правильно?\n1.Да\n2.Нет");
        i = system.in.nextInt();
        if (i == 2){
            createSchedule();
        }
    }

    public void makeAppointment() {
        System.out.println("Имя пациента:");
        system.in.nextLine();
        String name = system.in.nextLine();
        if (!system.checkPatient(name)){
            System.out.println("Такого пациента нет");
            return;
        }
        Patient patient = system.database.getPatient(name);
        System.out.println("Тип врача:");
        String type = system.in.nextLine();
        ArrayList<MedicalStaff> Doctors = new ArrayList<>();
        for (MedicalStaff doctor : system.database.getMedicalStaffList()){
            if (doctor.getType().equals(type)){
                Doctors.add(doctor);
            }
        }
        System.out.println("Пациент знает нужное имя врача?\n1.Да\n2.Нет");
        int i = system.in.nextInt();
        if (i==1){
            System.out.println("Имя врача:");
            system.in.nextLine();
            name = system.in.nextLine();
            for (MedicalStaff doc : Doctors){
                if (!doc.getName().equals(name)){
                    Doctors.remove(doc);
                }
            }
        }
        for (MedicalStaff doctor : Doctors) {
            System.out.println(doctor.getName());
            System.out.println(doctor.getType());
            for (String string : doctor.getFreeSchedule()) {
                System.out.println(string);
            }
        }
        System.out.println("Пациент выбрал время?\n1.Да\n2.Нет");
        i = system.in.nextInt();
        if (i==1){
            System.out.println("Имя врача:");
            system.in.nextLine();
            name = system.in.nextLine();
            MedicalStaff doctor = system.database.getDoctor(name);
            System.out.println("Время:");
            String time = system.in.nextLine();
            doctor.addTask(time);
            doctor.deleteJob(time);
            doctor.setInfo(patient.getName()+ "на" + time);
            patient.message("К " + doctor.getName() + ", " + doctor.getType() + ":" + time);
        }
    }
}

