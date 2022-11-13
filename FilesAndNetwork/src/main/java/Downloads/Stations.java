package Downloads;

public class Stations implements Comparable<Stations>{
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

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Stations) obj) == 0;
    }

    @Override
    public int compareTo(Stations station)
    {
        int lineComparison = line.compareTo(station.getLine());
        if(lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }
}
