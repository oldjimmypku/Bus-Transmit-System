package Guxinyu.bean;

public class BusInformation {
    private Integer busNum;
    private String route;
    private String startTime;
    private String endTime;

    public BusInformation(Integer busNum, String route, String startTime, String endTime) {
        this.busNum = busNum;
        this.route = route;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BusInformation() {
    }

    public Integer getBusNum() {
        return busNum;
    }

    public void setBusNum(Integer busNum) {
        this.busNum = busNum;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BusInformation{" +
                "busNum=" + busNum +
                ", route='" + route + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
