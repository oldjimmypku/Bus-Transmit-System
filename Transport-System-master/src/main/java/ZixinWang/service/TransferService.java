package Guxinyu.service;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ResultSet{
    LinkedList<Integer> busNames;
    int totalNum;
    LinkedList<String> transferStation=null;

    public ResultSet(LinkedList<Integer> busNames, int totalNum, LinkedList<String> transferStation) {
        this.busNames = busNames;
        this.totalNum = totalNum;
        this.transferStation = transferStation;
    }

    public ResultSet() {
    }

    public ResultSet(LinkedList<Integer> busNames, int totalNum) {
        this.busNames = busNames;
        this.totalNum = totalNum;
    }

}

public class TransferService {
    public Map<Integer, ArrayList<String>> bus;
    public Map<String, ArrayList<Integer>> station;

    public TransferService(Map<Integer, ArrayList<String>> bus, Map<String, ArrayList<Integer>> station) {
        this.bus = bus;
        this.station = station;

    }

    public TransferService() {
    }

    public String plan(String start,String aim){
        ResultSet result;
        result=this.try0(start,aim);
        if (result==null){
            result=try1(start,aim);

            if (result==null){
                result=try2(start,aim);
                if (result!=null){
                    return "路径"+start+"->"+aim+" 推荐["+result.busNames.get(0)+"->("+result.transferStation.get(0)+")->"
                            +result.busNames.get(1)+"->("+result.transferStation.get(1)+")->"+result.busNames.get(2)+"]路公交汽车"+" 途径站数："+result.totalNum;
                }
            }


            else
                return "路径"+start+"->"+aim+" 推荐["+result.busNames.get(0)+"->("+result.transferStation.get(0)+")->"+result.busNames.get(1)+"]路公交汽车"+" 途径站数："+result.totalNum;
        }
        else
            return "路径"+start+"->"+aim+" 推荐"+result.busNames+"路公交汽车"+" 途径站数："+result.totalNum;

        return "换成次数大于2，建议乘坐出租车";
    }

    private ResultSet try0(String start,String aim){
        /***
         * 防止station表中没有站名而出现空指针
         */
        if (!station.containsKey(start))
            return null;
        List<Integer> busesPassStart=station.get(start);
        int busNum=-1;
        int stationsNum= Integer.MAX_VALUE;
        System.out.println(start+"*****"+aim);
        for (int i = 0; i < busesPassStart.size(); i++) {
            if (bus.get(busesPassStart.get(i)).contains(aim)){
                int temp;
                temp=Math.abs(bus.get(busesPassStart.get(i)).indexOf(aim)-bus.get(busesPassStart.get(i)).indexOf(start));
                if (stationsNum>temp){
                    stationsNum=temp;
                    busNum=busesPassStart.get(i);
                }
            }
        }
        if (busNum!=-1){
            LinkedList<Integer> temp=new LinkedList<>();
            temp.add(busNum);
            return new ResultSet(temp,stationsNum);
        }
        else
            return null;
    }

    private ResultSet try1(String start,String aim){
        String transferStation=null;
        int stationsNum= Integer.MAX_VALUE;
        /**
         * 防止station表中没有站名而出现空指针
         */
        if (!station.containsKey(start))
            return null;
        List<Integer> busesPassStart=station.get(start);
        ResultSet result;
        LinkedList<Integer> resultRoute=null;

        for (int i = 0; i < busesPassStart.size(); i++) {
            ArrayList<String> stationsForOneBus=bus.get(busesPassStart.get(i));
            LinkedList<Integer> possibleRoute=new LinkedList<>();
            possibleRoute.add(busesPassStart.get(i));

            for (int j = 0; j < stationsForOneBus.size(); j++) {
                int num=Math.abs(stationsForOneBus.indexOf(start)-j);
                if (num==0)
                    continue;
                result=try0(stationsForOneBus.get(j),aim);
                if (result==null)
                    continue;
                else {
                    num+=result.totalNum;
                    if (stationsNum>num){
                        transferStation=stationsForOneBus.get(j);
                        stationsNum=num;
                        possibleRoute.add(result.busNames.getFirst());
                        resultRoute=(LinkedList<Integer>) possibleRoute.clone();
                    }
                }
            }
        }
        if (resultRoute!=null){
            LinkedList<String> temp=new LinkedList<>();
            temp.add(transferStation);
            return new ResultSet(resultRoute,stationsNum,temp);
        }

        else
            return null;
    }

    private ResultSet try2(String start,String aim){
        LinkedList<String> transferStation=new LinkedList<>();
        int stationsNum= Integer.MAX_VALUE;
        /***
         * 防止station表中没有站名而出现空指针
         */
        if (!station.containsKey(aim))
            return null;
        List<Integer> busesPassAim=station.get(aim);
        ResultSet result;
        LinkedList<Integer> resultRoute=null;
        for (int i = 0; i < busesPassAim.size(); i++) {
            ArrayList<String> stationsForOneBus=bus.get(busesPassAim.get(i));
            LinkedList<Integer> possibleRoute=new LinkedList<>();
            possibleRoute.add(busesPassAim.get(i));

            for (int j = 0; j < stationsForOneBus.size(); j++) {
                int num=Math.abs(stationsForOneBus.indexOf(aim)-j);
                if (num==0)
                    continue;
                result=try1(start,stationsForOneBus.get(j));
                if (result==null)
                    continue;
                else {
                    num+=result.totalNum;
                    if (stationsNum>num){
                        transferStation.clear();
                        transferStation.add(stationsForOneBus.get(j));
                        transferStation.addFirst(result.transferStation.getFirst());
                        stationsNum=num;
                        possibleRoute.addFirst(result.busNames.getLast());
                        possibleRoute.addFirst(result.busNames.getFirst());
                        resultRoute=(LinkedList<Integer>) possibleRoute.clone();
                    }
                }
            }
        }
        if (resultRoute!=null)
            return new ResultSet(resultRoute,stationsNum,transferStation);
        else
            return null;
    }



}
