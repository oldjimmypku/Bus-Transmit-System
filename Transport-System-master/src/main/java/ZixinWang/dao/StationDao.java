package Guxinyu.dao;

import Guxinyu.bean.ContainBuses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface StationDao {
    @Select(value=" SELECT containBuses FROM station WHERE stationName=#{stationName}")
    public String findContainBuses(String stationName);

    @Select(value=" SELECT stationName,containBuses FROM station")
    public List<ContainBuses> findAllContainBuses();


}
