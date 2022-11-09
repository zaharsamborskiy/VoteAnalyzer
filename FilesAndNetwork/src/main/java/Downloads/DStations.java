package Downloads;

public class DStations {
    private String name;
    private String line;


    String date ="";

    String depth;
    boolean hasConnect =false;

    public DStations(String station, String line, String date, String depth, boolean hasConnect){
        this.name = station;
        this.line = line;
    }
    public void setDepth(String depth) {
        this.depth = depth;
    }
    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }
    public String getDate() {
        return date;
    }

    public String getDepth() {
        return depth;
    }

    public boolean isHasConnect() {
        return hasConnect;
    }
}
