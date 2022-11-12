package Downloads;

import java.util.TreeSet;

public class DStations implements Comparable<DStations>{
    private String name;
    private String line;
    String date;

    String depth;
    boolean hasConnect;

    private TreeSet<DStations> setStations;

    public DStations(String name, String line){
        this.name = name;
        this.line = line;
    }

    public DStations(String name, String line, String date,String depth, boolean hasConnect){
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnect = hasConnect;

    }

    public TreeSet<DStations> getSetStations() {
        return setStations;
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
        return compareTo((DStations) obj) == 0;
    }

    @Override
    public int compareTo(DStations station)
    {
        int lineComparison = line.compareTo(station.getLine());
        if(lineComparison != 0) {
            return lineComparison;
        }
        return name.compareToIgnoreCase(station.getName());
    }
}
