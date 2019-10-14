package Guxinyu.bean;

public class ContainBuses {
    private String stationName;
    private String containBuses;

    @Override
    public String toString() {
        return "ContainBuses{" +
                "stationName='" + stationName + '\'' +
                ", containBuses='" + containBuses + '\'' +
                '}';
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getContainBuses() {
        return containBuses;
    }

    public void setContainBuses(String containBuses) {
        this.containBuses = containBuses;
    }

    public ContainBuses(String stationName, String containBuses) {
        this.stationName = stationName;
        this.containBuses = containBuses;
    }

    public ContainBuses() {
    }
}
