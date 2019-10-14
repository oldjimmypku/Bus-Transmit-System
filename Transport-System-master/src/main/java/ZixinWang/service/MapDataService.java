package Guxinyu.service;
import Guxinyu.bean.AllRoute;
import Guxinyu.bean.BusInformation;
import Guxinyu.bean.ContainBuses;
import Guxinyu.dao.BusDao;
import Guxinyu.dao.StationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MapDataService {
    @Autowired
    BusDao busDao;
    @Autowired
    StationDao stationDao;

    public BusInformation findRoute(int busNum){return busDao.findRoute(busNum);}

    public List<AllRoute> findAllRoute(){return busDao.findAllRoute();}

    public String findContainBuses(String stationName){
        return stationDao.findContainBuses(stationName);
    }

    public List<ContainBuses>  findAllContainBuses(){return stationDao.findAllContainBuses();}


}
