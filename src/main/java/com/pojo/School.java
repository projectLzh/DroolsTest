package com.pojo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class School {
    private String className;
    private String classCount;
    private String[] classNameArray;
    private List classNameList;
    private Set classNameSet;
    private Map classNameMap;

    public School() {
    }

    public School(String className) {
        this.className = className;
    }

    public Map getClassNameMap() {
        return classNameMap;
    }

    public void setClassNameMap(Map classNameMap) {
        this.classNameMap = classNameMap;
    }

    public Set getClassNameSet() {
        return classNameSet;
    }

    public void setClassNameSet(Set classNameSet) {
        this.classNameSet = classNameSet;
    }

    public List getClassNameList() {
        return classNameList;
    }

    public void setClassNameList(List classNameList) {
        this.classNameList = classNameList;
    }

    public String[] getClassNameArray() {
        return classNameArray;
    }

    public void setClassNameArray(String[] classNameArray) {
        this.classNameArray = classNameArray;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }
}
