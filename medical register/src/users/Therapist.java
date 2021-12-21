package users;

import objects.MedicalTest;
import objects.Schedule;
import system.MedicalDatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class Therapist extends MedicalStaff{

    public Therapist(String name, String type, ArrayList<String> busySchedule, ArrayList<String> freeSchedule, ArrayList<String> info) {
        super(name, type, busySchedule, freeSchedule, info);
    }


    public void control(Scanner in, MedicalDatabase database) {
        System.out.println("Ваши действия:\n1.Поставить на учет\n2.Убрать с учета");
        int i = in.nextInt();
        System.out.println("Введите имя пациента");
        in.nextLine();
        String name = in.nextLine();
        if (!database.checkPatient(name)){
            System.out.println("Такого пациента нет");
            return;
        }
        if (i == 1){
            System.out.println("Введите имя врача, к которому нужно поставить на учет");
            String named = in.nextLine();
            if (!database.checkDoctor(named)){
                System.out.println("Такого доктора нет");
                return;
            }
            database.record(name, named);
        }
        if (i == 2){
            database.unrecord(name);
        }
    }

    public void analise(Scanner in, MedicalDatabase database) {
        System.out.println("Введите имя пациента");
        in.nextLine();
        String name = in.nextLine();
        if (!database.checkPatient(name)){
            System.out.println("Такого пациента нет");
            return;
        }
        for (MedicalTest test : database.getMedicalTestList()){
            System.out.println(test.getName());
            for (String string : test.getSchedule()){
                System.out.println(string);
            }
        }
        System.out.println("Введите название выбранного анализа:");
        String namea = in.nextLine();
        System.out.println(name);
        System.out.println(namea);
        for (MedicalTest test : database.getMedicalTestList()){
            if (test.getName().equals(namea)) {
                for (String string : test.getSchedule()) {
                    System.out.println(string);
                }
            }
        }
    }
}
