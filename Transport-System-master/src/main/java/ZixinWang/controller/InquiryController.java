package Guxinyu.controller;

import Guxinyu.bean.AllRoute;
import Guxinyu.bean.BusInformation;
import Guxinyu.bean.ContainBuses;
import Guxinyu.dao.StationDao;
import Guxinyu.service.HistoryService;
import Guxinyu.service.MapDataService;
import Guxinyu.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/user")
public class InquiryController {

    @Autowired
    MapDataService mapDataService;

    @Autowired
    HistoryService historyService;


    @RequestMapping("/inquiry")
    @PreAuthorize("hasAuthority('P_USER')")
    public String inquiry(Model model, String tt) {
        return "user/inquiry";
    }

    @RequestMapping(value = "/inquiry/inquiryRoute")
    @PreAuthorize("hasAuthority('P_USER')")
    public String inquiryRoute(){
        return "user/inquiry/inquiryRoute";
    }


    @RequestMapping(value = "/inquiry/inquiryStation")
    @PreAuthorize("hasAuthority('P_USER')")
    public String inquiryStation(){
        return "user/inquiry/inquiryStation";
    }

    @RequestMapping(value = "/inquiry/inquiryPlan")
    @PreAuthorize("hasAuthority('P_USER')")
    public String inquiryPlan(){
        return "user/inquiry/inquiryPlan";
    }



    @RequestMapping(value = "/inquiry/inquiryRoute/result",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('P_USER')")
    public String inquiryRouteReturnResult(Model m,@RequestParam(required=true)int busNum){
        BusInformation busInformation=mapDataService.findRoute(busNum);
        if(busInformation==null){
            m.addAttribute("result","没有此条线路！请确认公交名");
            return "user/inquiry/result";
        }
        historyService.addHistory("查询线路",String.valueOf(busNum)+"路公交车",busInformation.getRoute()+
                " 首班车时间："+busInformation.getStartTime()+",末班车时间:"+busInformation.getEndTime());
        m.addAttribute("result",busNum+"的线路是： "+busInformation.getRoute()+
                " 首班车时间："+busInformation.getStartTime()+",末班车时间:"+busInformation.getEndTime());
        return "user/inquiry/result";
    }


    @RequestMapping(value = "/inquiry/inquiryStation/result",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('P_USER')")
    public String inquiryStation(Model m,@RequestParam (required=true)String stationName){
        String route=mapDataService.findContainBuses(stationName);
        System.out.println(route);
        if(route==null){
            m.addAttribute("result","没有此公交站点信息！请确认公交站名");
            return "user/inquiry/result";
        }
        route=route.replace("+"," ");
        System.out.println(route);
        historyService.addHistory("查询站点信息",stationName,route);
        m.addAttribute("result",stationName+"包含的公交车有： "+route);
        //m.addAttribute(stationName,route);
        return "user/inquiry/result";
    }


    @RequestMapping(value = "/inquiry/inquiryPlan/result",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('P_USER')")
    /***
     * Unfinished
     */
    public String inquiryPlan(Model m,@RequestParam (required=true)String start,@RequestParam (required=true) String aim){
        List<ContainBuses> stations=mapDataService.findAllContainBuses();
        List<AllRoute> buses=mapDataService.findAllRoute();
        HashMap<String,ArrayList<Integer>> transferServiceStation=new HashMap<>();
        HashMap<Integer,ArrayList<String>> transferServiceBus=new HashMap<>();

        for (ContainBuses temp:stations){
            ArrayList<Integer> station=new ArrayList<Integer>();
            String[] t=temp.getContainBuses().split("\\+");
            for (int i=0;i<t.length;i++) {
                station.add(Integer.valueOf(t[i]));
            }
            transferServiceStation.put(temp.getStationName(),(ArrayList<Integer>) station.clone());
        }

        for (AllRoute temp:buses){
            ArrayList<String> bus=new ArrayList<>(Arrays.asList(temp.getRoute().split("\\-")));
            transferServiceBus.put(temp.getBusNum(),(ArrayList<String>) bus.clone());
        }
        TransferService transferService=new TransferService(transferServiceBus,transferServiceStation);
        String result=transferService.plan(start,aim);
        historyService.addHistory("查询乘车路线",start+"->"+aim,result);
        m.addAttribute("result",result);
        return "user/inquiry/result";

    }



}
