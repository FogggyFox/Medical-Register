package objects;

import java.util.ArrayList;
import java.util.Collections;

public class Schedule {
    ArrayList<String> schedule;

    public Schedule(ArrayList<String> schedule){
        this.schedule = schedule;
    }

    public ArrayList<String> getSchedule(){
        return schedule;
    }

    public void sort(){
        Collections.sort(schedule);
    }

    public void add(String line) {
        schedule.add(line);
        sort();
    }
    public void change(String line, String line2) {
        schedule.removeIf(string -> string.equals(line));
        add(line2);
    }
    public void delete(String line) {
        schedule.removeIf(string -> string.equals(line));
    }

}
