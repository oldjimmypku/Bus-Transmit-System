package Guxinyu.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Guxinyu.bean.SPermission;
import Guxinyu.bean.SRole;
import Guxinyu.bean.SUser;
import Guxinyu.dao.SPermissionDao;
import Guxinyu.dao.SRoleDao;
import Guxinyu.dao.SUserDao;

/**
 * Security 数据服务
 *
 * @author Gu
 */
@Service
public class SecurityDataService {
    @Autowired
    private SUserDao sUserDao;
    @Autowired
    private SRoleDao sRoleDao;
    @Autowired
    private SPermissionDao sPermissionDao;

    public SUser findSUserByName(String name) {
        return sUserDao.findSUserByName(name);
    }

    public boolean addUser(String userName,String password){
        boolean flag=false;
        flag=sUserDao.addUser(userName,password);
        if (flag){
            sRoleDao.addUser(userName);
        }
        return flag;
    }

    public List<SRole> findSRoleListBySUserId(int sUserId) {
        return sRoleDao.findSRoleListBySUserId(sUserId);
    }

    public List<SRole> findSRoleListBySPermissionUrl(String sPermissionUrl) {
        return sRoleDao.findSRoleListBySPermissionUrl(sPermissionUrl);
    }

    public List<SPermission> findSPermissionListBySUserId(int sUserId) {
        return sPermissionDao.findSPermissionListBySUserId(sUserId);
    }

    public List<SPermission> findSPermissionListBySPermissionUrl(String sPermissionUrl) {
        return sPermissionDao.findSPermissionListBySPermissionUrl(sPermissionUrl);
    }
}