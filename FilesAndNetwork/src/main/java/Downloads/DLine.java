package Downloads;


import java.util.TreeSet;

public class DLine {

        private String number;
        private String name;


    private TreeSet<DLine> setLine;

        public DLine(String number, String name) {
            this.number = number;
            this.name = name;
        }
    public TreeSet<DLine> getSetLine() {
        return setLine;
    }

    public String getNumber() {
            return number;
        }

    public String getName() {
            return name;
        }

}
