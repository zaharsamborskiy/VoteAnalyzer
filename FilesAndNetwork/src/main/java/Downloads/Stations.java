package Downloads;

public class Stations{
    private String name;
    private String line;
    String date;

    String depth;
    boolean hasConnect;


    public Stations(String name, String line){
        this.name = name;
        this.line = line;
    }

    public Stations(String name, String line, String date, String depth, boolean hasConnect){
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnect = hasConnect;
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
