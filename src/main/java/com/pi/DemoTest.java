package com.pi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author laizhihui on 2020/8/3
 */
public class DemoTest {
    private List<SystemListDemo> systemList=new ArrayList<>();
    private List<UserListDemo> userList=new ArrayList<>();
    private List<UserListDemo> returnList=new ArrayList<>();

    public List<SystemListDemo> getSystemList() {
        return systemList;
    }

    public void setSystemList(List<SystemListDemo> systemList) {
        this.systemList = systemList;
    }

    public List<UserListDemo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListDemo> userList) {
        this.userList = userList;
    }

    public List<UserListDemo> getReturnList() {

        return returnList;
    }

    public void setReturnList(List<UserListDemo> returnList) {
        this.returnList = returnList;
    }
}
