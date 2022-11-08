package core;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private int number;
    private String name;
    private List<Stations> stations;


    public Line(int number, String name) {
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
    public void addStation(Stations station){
        stations.add(station);
    }
    public List<Stations> getStations(){
        return stations;
    }

}
