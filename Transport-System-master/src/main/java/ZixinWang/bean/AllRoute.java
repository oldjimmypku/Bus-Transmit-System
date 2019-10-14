package Guxinyu.bean;

public class AllRoute {
    private Integer busNum;
    private String route;

    @Override
    public String toString() {
        return "AllRoute{" +
                "busNum=" + busNum +
                ", route='" + route + '\'' +
                '}';
    }

    public AllRoute(Integer busNum, String route) {
        this.busNum = busNum;
        this.route = route;
    }

    public AllRoute() {
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
}
