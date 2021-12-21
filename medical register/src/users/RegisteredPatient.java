package users;

import java.util.ArrayList;

public class RegisteredPatient extends Patient{
    int passport;
    String pathologies;
    ArrayList<String> info;
    public RegisteredPatient(String name) {
        super(name);
    }

    @Override
    public String getPathologies() {
        return pathologies;
    }


    public RegisteredPatient(String name, int passport, String pathologies, ArrayList<String> info){
        this.name = name;
        this.passport = passport;
        this.pathologies = pathologies;
        this.info = info;
    }

    @Override
    public void setInfo(int passport, String pathologies){
        this.passport = passport;
        this.pathologies = pathologies;
        this.info = new ArrayList<>();
    }

    public void setPassport(int passport){
        this.passport = passport;
    }

    @Override
    public int getPassport(){
        return passport;
    }

    @Override
    public ArrayList<String> getInfo() {
        return info;
    }

    @Override
    public void setPathologies(String pathologies) {
        this.pathologies = pathologies;
    }

    @Override
    public void addPathologies(String pathologies) {
        this.pathologies+=pathologies;
    }

    @Override
    public String getMessages() {
        StringBuilder list = new StringBuilder();
        for (String string : info){
            list.append(string).append("\n");
        }
        return list.toString();
    }


    @Override
    public String getMedInfo() {
        return pathologies;
    }

    @Override
    public void message(String s) {
        info.add(s);
    }


}
