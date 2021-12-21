import system.MedicalDatabase;
import system.RegisterSystem;
import system.SystemMode;
import users.Registrar;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String name = "";
        MedicalDatabase database = new MedicalDatabase();
        Scanner in = new Scanner(System.in);
        RegisterSystem system = new RegisterSystem(database, in);
        int i = 0;
        while (i != 4){
            System.out.println("Choose mode:\n1.Therapist\n2.Registrar\n3.Patient\n4.Exit");
            i = in.nextInt();
            switch (i){
                case (3):
                    system.changeMode(SystemMode.Patient);
                    system.patientCommands();
                    database.save();
                    continue;
                case (2):
                    system.changeMode(SystemMode.Registrar);
                    System.out.println("Ваше имя?");
                    in.nextLine();
                    name = in.nextLine();
                    Registrar registrar = new Registrar(name, system);
                    system.registrarCommands(registrar);
                    database.save();
                    continue;
                case (1):
                    system.changeMode(SystemMode.Therapist);
                    system.therapistCommands();
                    database.save();
                    continue;

            }

        }
    }
}
