package users;

import java.util.ArrayList;

public class NewPatient extends Patient{
    public NewPatient(String name) {
        super(name);
    }

    @Override
    public String getPathologies() {
        return null;
    }

    @Override
    public void setInfo(int passport, String pathologies) {}

    @Override
    public void setPassport(int passport) { }

    @Override
    public void setPathologies(String pathologies) { }

    @Override
    public void addPathologies(String pathologies) { }

    @Override
    public String getMessages() {
        return null;
    }

    @Override
    public String getMedInfo() {
        return null;
    }

    @Override
    public void message(String s) {
    }

    @Override
    public int getPassport() {
        return 0;
    }

    @Override
    public ArrayList<String> getInfo() {
        return null;
    }
}
