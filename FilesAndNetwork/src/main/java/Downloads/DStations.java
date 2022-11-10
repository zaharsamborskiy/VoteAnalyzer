package Downloads;

public class DStations {
    private String name;
    private String line;


    public DStations(String name, String line){
        this.name = name;
        this.line = line;
    }

    String date ="";

    String depth = "";
    boolean hasConnect = false;
    public DStations(String name, String line, String date,String depth, boolean hasConnect){
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnect = hasConnect;

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
