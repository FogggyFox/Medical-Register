package objects;

import java.util.ArrayList;

public class MedicalTest {

    String name;
    Schedule schedule;

    public MedicalTest(String name, ArrayList<String> schedule){
        this.name = name;
        this.schedule = new Schedule(schedule);
    }

    public String getName(){
        return name;
    }
    public ArrayList<String> getSchedule(){
        return schedule.getSchedule();
    }
}
