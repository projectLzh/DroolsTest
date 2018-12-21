package comCmp;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentDaysRuleEngineBO {

    double enterpriseScore = 0;

    double enterpriseRegisteredsScorce = 0;

    double crmCreateScore = 0;

    double halfYearAvgScore = 0;

    double continuousScore = 0;

    Long crmCreateVal;

    Long enterpriseRegisteredVal;

    private Date enterpriseRegisteredTime;

    private Date crmCreateTime;

    private BigDecimal halfYearAvgAmount;

    private Integer continuousMonths;

    private double enterpriseRiskScore;

    private Integer enterpriseClass;

    private String result;

    public double getEnterpriseScore() {
        return enterpriseScore;
    }

    public void setEnterpriseScore(double enterpriseScore) {
        this.enterpriseScore = enterpriseScore;
    }

    public double getEnterpriseRegisteredsScorce() {
        return enterpriseRegisteredsScorce;
    }

    public void setEnterpriseRegisteredsScorce(double enterpriseRegisteredsScorce) {
        this.enterpriseRegisteredsScorce = enterpriseRegisteredsScorce;
    }

    public double getCrmCreateScore() {
        return crmCreateScore;
    }

    public void setCrmCreateScore(double crmCreateScore) {
        this.crmCreateScore = crmCreateScore;
    }

    public double getHalfYearAvgScore() {
        return halfYearAvgScore;
    }

    public void setHalfYearAvgScore(double halfYearAvgScore) {
        this.halfYearAvgScore = halfYearAvgScore;
    }

    public double getContinuousScore() {
        return continuousScore;
    }

    public void setContinuousScore(double continuousScore) {
        this.continuousScore = continuousScore;
    }

    public Long getCrmCreateVal() {
        return crmCreateVal;
    }

    public void setCrmCreateVal(Long crmCreateVal) {
        this.crmCreateVal = crmCreateVal;
    }

    public Long getEnterpriseRegisteredVal() {
        return enterpriseRegisteredVal;
    }

    public void setEnterpriseRegisteredVal(Long enterpriseRegisteredVal) {
        this.enterpriseRegisteredVal = enterpriseRegisteredVal;
    }

    public Date getEnterpriseRegisteredTime() {
        return enterpriseRegisteredTime;
    }

    public void setEnterpriseRegisteredTime(Date enterpriseRegisteredTime) {
        this.enterpriseRegisteredTime = enterpriseRegisteredTime;
    }

    public Date getCrmCreateTime() {
        return crmCreateTime;
    }

    public void setCrmCreateTime(Date crmCreateTime) {
        this.crmCreateTime = crmCreateTime;
    }

    public BigDecimal getHalfYearAvgAmount() {
        return halfYearAvgAmount;
    }

    public void setHalfYearAvgAmount(BigDecimal halfYearAvgAmount) {
        this.halfYearAvgAmount = halfYearAvgAmount;
    }

    public Integer getContinuousMonths() {
        return continuousMonths;
    }

    public void setContinuousMonths(Integer continuousMonths) {
        this.continuousMonths = continuousMonths;
    }

    public double getEnterpriseRiskScore() {
        return enterpriseRiskScore;
    }

    public void setEnterpriseRiskScore(double enterpriseRiskScore) {
        this.enterpriseRiskScore = enterpriseRiskScore;
    }

    public Integer getEnterpriseClass() {
        return enterpriseClass;
    }

    public void setEnterpriseClass(Integer enterpriseClass) {
        this.enterpriseClass = enterpriseClass;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}