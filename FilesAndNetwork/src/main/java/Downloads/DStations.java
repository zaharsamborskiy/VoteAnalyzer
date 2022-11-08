package Downloads;

public class DStations {
    private String station;
    private String line;


    String date ="";
    String depth ="";
    boolean hasConnect =false;

    public DStations(String station, String line, String date, String depth, boolean hasConnect){
        this.station = station;
        this.line = line;
    }

    public String getStation() {
        return station;
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
