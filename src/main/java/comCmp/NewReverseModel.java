package comCmp;

import java.io.Serializable;
import java.util.Date;

public class NewReverseModel implements Serializable {
    private static final long serialVersionUID = 8571879269498235280L;
    //分公司
    private String branchOffice;
    //部门
    private String department;
    //白名单类型
    private String whiteListType;
    //金额区间类型
    private String sameType;
    //最小金额  区间开始
    private double minMoney;
    //最大金额  敬意结束
    private double maxMoney;
    //创建人
    private String createrId;
    //创建时间
    private Date createrDate;



    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWhiteListType() {
        return whiteListType;
    }

    public void setWhiteListType(String whiteListType) {
        this.whiteListType = whiteListType;
    }

    public String getSameType() {
        return sameType;
    }

    public void setSameType(String sameType) {
        this.sameType = sameType;
    }

    public double getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(double minMoney) {
        this.minMoney = minMoney;
    }

    public double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(double maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public Date getCreaterDate() {
        return createrDate;
    }

    public void setCreaterDate(Date createrDate) {
        this.createrDate = createrDate;
    }
}
