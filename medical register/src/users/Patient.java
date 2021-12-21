package users;

import java.util.ArrayList;

public abstract class Patient {

    String name;
    public Patient(String name){
        this.name = name;
    }

    protected Patient() {
    }


    public String getName() {
        return name;
    }

    public abstract String getPathologies();
    public abstract void setInfo(int passport, String pathologies);
    public abstract void setPassport(int passport);

    public abstract void setPathologies(String pathologies);

    public abstract void addPathologies(String pathologies);

    public abstract String getMessages();

    public abstract String getMedInfo();

    public abstract void message(String s);

    public abstract int getPassport();

    public abstract ArrayList<String> getInfo();
}
