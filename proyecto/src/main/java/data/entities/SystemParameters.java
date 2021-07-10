package data.entities;

import java.util.List;

import config.Pair;

import java.util.ArrayList;

public class SystemParameters {

    private List<Pair<Integer,String>> numberStudents;

    SystemParameters(){
        numberStudents = new ArrayList<>();
    }

    public List<Pair<Integer,String>> getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(List<Pair<Integer,String>> numberStudents) {
        this.numberStudents = numberStudents;
    }

}