package Downloads;

public class DStations {
    private String name;
    private String line;


    String date ="";

    String depth = "";
    boolean hasConnect = false;

    public DStations(String station, String line){
        this.name = station;
        this.line = line;

    }
    public Object setDepth(String depth) {
        this.depth = depth;
        return null;
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
