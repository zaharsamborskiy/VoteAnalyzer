package core;

public class Stations {

    private String name;
    private String numberLine;

    public Stations(String name, String numberLine){
        this.name = name;
        this.numberLine = numberLine;
    }
    public String getName() {
        return name;
    }

    public String getNumberLine() {
        return numberLine;
    }
    @Override
    public String toString() {
        return name;
    }
}
