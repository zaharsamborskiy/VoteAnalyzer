package Downloads;


import java.util.List;

public class Line {

    private String number;
    private String name;

    private List<Stations> stationsList;


        public Line(String number, String name) {
            this.number = number;
            this.name = name;
        }

    public List<Stations> getStationsList() {
        return stationsList;
    }
    public String getNumber() {
            return number;
        }

    public String getName() {
            return name;
        }


}
