package Guxinyu.dao;

import Guxinyu.bean.AllRoute;
import Guxinyu.bean.BusInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BusDao {
    @Select(value=" SELECT * FROM bus WHERE busNum=#{busNum}")
    public BusInformation findRoute(int busNum);

    @Select(value=" SELECT busNum,route FROM bus")
    public List<AllRoute> findAllRoute();
}
