package users;

import objects.Schedule;

import java.util.ArrayList;

public class MedicalStaff {
    String name;
    Schedule busySchedule;
    Schedule freeSchedule;
    String type;
    ArrayList<String> info;

    public MedicalStaff(String name, String type, ArrayList<String> freeSchedule, ArrayList<String> busySchedule, ArrayList<String> info){
        this.name = name;
        this.busySchedule = new Schedule(busySchedule);
        this.freeSchedule= new Schedule(freeSchedule);
        this.type = type;
        this.info = info;
    }
    public String getName() {
        return name;
    }

    public ArrayList<String> getFreeSchedule() {
        return freeSchedule.getSchedule();
    }
    public ArrayList<String> getBusySchedule() {
        return busySchedule.getSchedule();
    }


    public String getType() {
        return type;
    }
    public void addTask(String line){
        busySchedule.add(line);
    }
    public void deleteTask(String line){
        busySchedule.delete(line);
    }

    public void addJob(String line) {
        freeSchedule.add(line);
    }
    public void changeJob(String line, String line2) {
        freeSchedule.change(line, line2);
    }
    public void deleteJob(String line) {
        freeSchedule.delete(line);
    }
    public void setInfo(String line){
        info.add(line);
    }
    public ArrayList<String> getInfo(){
        return info;
    }

}
