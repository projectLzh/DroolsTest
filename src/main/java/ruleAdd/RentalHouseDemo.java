package ruleAdd;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author laizhihui on 2020/6/28
 */
public class RentalHouseDemo {
    DecimalFormat df = new DecimalFormat("#.00");


    //TODO 投资收益率
    //TODO 总投入
    //TODO 回本

    /**
     * 出租房屋 年收益总金额
     *
     * @param montRent
     * @param yearRiseMoney
     */
    private List<RentModel> rentModelFunction(double montRent, double yearRiseMoney, int year, int rentNumber) {
        List<RentModel> rentModelList = new ArrayList<>();
        int month = 12;
        for (int i = 1; i <= year; i++) {
            RentModel rentModel = new RentModel();
            rentModel.setMonthRent(montRent);
            rentModel.setYearRiseMoney(yearRiseMoney);
            double yearModel = rentModel.getMonthRent() * month * rentNumber;
            rentModel.setMonthRent(rentModel.getMonthRent() + rentModel.getYearRiseMoney());
            rentModel.setYearSumMoney(yearModel);
            montRent = rentModel.getMonthRent();
            rentModelList.add(rentModel);
        }
        return rentModelList;
    }

    /**
     * 等额本息
     *
     * @param monthRepayment
     */
    private List<HousingLoans> housingLoansFunction(double monthRepayment, int year, int rentNumber) {
        List<HousingLoans> housingLoansList = new ArrayList<>();
        int month = 12;
        for (int i = 1; i <= year; i++) {
            HousingLoans housingLoans = new HousingLoans();
            housingLoans.setMonthRepayment(monthRepayment);
            double yearSumRepayment = housingLoans.getMonthRepayment() * month * rentNumber;
            housingLoans.setYearSumRepayment(yearSumRepayment);
            housingLoansList.add(housingLoans);
        }
        return housingLoansList;
    }


    private List<HousingLoans> loanPersonal(double monthRepayment, int year) {
        List<HousingLoans> housingLoansList = new ArrayList<>();
        int month = 12;
        for (int i = 1; i <= year; i++) {
            HousingLoans housingLoans = new HousingLoans();
            housingLoans.setMonthRepayment(monthRepayment);
            double yearSumRepayment = housingLoans.getMonthRepayment() * month;
            housingLoans.setYearSumRepayment(yearSumRepayment);
            housingLoansList.add(housingLoans);
        }
        return housingLoansList;
    }

    /**
     * 等额本金
     *
     * @param monthRepayment
     * @param monthDiminishing
     * @param year
     */
    private List<HousingLoans> housingLoansFunction(double monthRepayment, double monthDiminishing, int year, int rentNumber) {

        List<HousingLoans> housingLoansList = new ArrayList<>();
        double newMonthRepayment = monthRepayment;
        for (int i = 1; i <= year; i++) {
            HousingLoans housingLoans = new HousingLoans();
            housingLoans.setMonthDiminishing(monthDiminishing);
            housingLoans.setMonthRepayment(newMonthRepayment);
            newMonthRepayment = monthDiminishingFunction(newMonthRepayment, monthDiminishing, 12, housingLoans);
            housingLoans.setYearSumRepayment(housingLoans.getYearSumRepayment() * rentNumber);
            housingLoansList.add(housingLoans);
        }
        return housingLoansList;
    }


    private double monthDiminishingFunction(double monthRepayment, double monthDiminishing, int month, HousingLoans housingLoans) {
        double yearSumRepayment = 0;
        double newMonthRepayment = monthRepayment;
        for (int i = 1; i <= month; i++) {
            if (1 == i) {
                yearSumRepayment += newMonthRepayment;
            } else {
                newMonthRepayment = newMonthRepayment - monthDiminishing;
                yearSumRepayment += newMonthRepayment;
            }
        }
        housingLoans.setYearSumRepayment(yearSumRepayment);
        return newMonthRepayment;
    }

    /**
     * 家庭开销
     *
     * @param monthRiseMoney
     * @param monthIncrement
     * @param month
     * @return
     */
    private List<PersonalSpend> personalSpendDiminishingFunction(double monthRiseMoney, double monthIncrement, int month, int year,int yearIncrement,int yearCancel) {
        List<PersonalSpend> personalSpendList = new ArrayList<>();
        for (int i = 1; i <= year; i++) {
            if (yearCancel >= i) {
                monthRiseMoney += yearIncrement;
            }
            PersonalSpend personalSpend = new PersonalSpend();
            personalSpend.setMonthRiseMoney(monthRiseMoney);
            personalSpend.setMonthIncrement(monthIncrement);
            personalIncrementFunction(monthRiseMoney, monthIncrement, month, personalSpend);
            personalSpendList.add(personalSpend);

        }
        return personalSpendList;
    }

    private void personalIncrementFunction(double monthRiseMoney, double monthIncrement, int month, PersonalSpend personalSpend) {
        double yearSumMonthRiseMoney = 0;
        double newMonthRiseMoney = monthRiseMoney;
        for (int i = 1; i <= month; i++) {
            if (1 == i) {
                yearSumMonthRiseMoney += monthRiseMoney;
                personalSpend.setMonthRiseMoney(monthRiseMoney);
            } else {
                newMonthRiseMoney = newMonthRiseMoney - monthIncrement;
                personalSpend.setMonthRiseMoney(monthRiseMoney + monthIncrement);
                yearSumMonthRiseMoney += newMonthRiseMoney;
            }
        }
        personalSpend.setYearRiseMoney(yearSumMonthRiseMoney);
    }


    @Test
    public void testRentalHouse() {
        int rentNumber = 20;
        int housingLoansYear = 30;
        int yearRiseMoney = 30;
        double loanPersonalSum = 1500000;
        //房屋出租 年收，年递增固定金额
        List<RentModel> rentModelList = rentModelFunction(1500, 100, yearRiseMoney, rentNumber);
        //等额本息还款，
        List<HousingLoans> housingLoansInterestList = housingLoansFunction(1486.03, housingLoansYear, rentNumber);
        //等额本金还款
        List<HousingLoans> housingLoansPrincipalList = housingLoansFunction(1921.11, 3.18, housingLoansYear, rentNumber);
        //年开销， 每年月递增， 每年定增  固定年后不增加
        List<PersonalSpend> personalSpendList = personalSpendDiminishingFunction(4000, 100, 12, 30,1000,15);
        //个人贷款 月供金额  TODO 按150万 3.5利率 30年
        List<HousingLoans> loanPersonalList = loanPersonal(6735.67, housingLoansYear);

        //租房所得收益  正向现金流
        Map<String, Double> yearSumMoneyMap = new LinkedHashMap<>();
        for (int i = 1; i <= rentModelList.size(); i++) {
            //根据指定年份 每年月租金增加指定金额所获取的当前年所得的租金
            double yearSumMoney = rentModelList.get(i - 1).getYearSumMoney();
            yearSumMoneyMap.put(i + "year", yearSumMoney);
        }

        //房贷所得收益  负向现金流
        Map<String, Double> yearSumRepaymentInterestMap = new LinkedHashMap<>();
        for (int i = 1; i <= housingLoansInterestList.size(); i++) {
            //根据指定年份 等额本息  月固定还款 年份金额
            double yearSumRepayment = housingLoansInterestList.get(i - 1).getYearSumRepayment();
            yearSumRepaymentInterestMap.put(i + "year", yearSumRepayment);
        }

        Map<String, Double> yearSumRepaymentPrincipalMap = new LinkedHashMap<>();
        for (int i = 1; i <= housingLoansPrincipalList.size(); i++) {
            //根据指定年份 等额本金  月递减金额
            double yearSumRepayment = housingLoansPrincipalList.get(i - 1).getYearSumRepayment();
            yearSumRepaymentPrincipalMap.put(i + "year", yearSumRepayment);
        }

        Map<String, Double> yearSumPersonalSpendListMap = new LinkedHashMap<>();
        for (int i = 1; i <= personalSpendList.size(); i++) {
            //根据指定年份 等额本金  月递减金额
            double yearSumRepayment = personalSpendList.get(i - 1).getYearRiseMoney();
            yearSumPersonalSpendListMap.put(i + "year", yearSumRepayment);
        }

        //个人商业贷款
        Map<String, Double> yearSumLoanPersonalMap = new LinkedHashMap<>();
        for (int i = 1; i <= loanPersonalList.size(); i++) {
            double yearSumRepayment = loanPersonalList.get(i - 1).getYearSumRepayment();
            yearSumLoanPersonalMap.put(i + "year", yearSumRepayment);
        }


        for (Map.Entry<String, Double> map : yearSumMoneyMap.entrySet()) {
            String key = map.getKey();
            //本息还款 月固定金额
            double yearSumRepaymentInterest = yearSumRepaymentInterestMap.get(key);
            //本金还款，月递减金额
            double yearSumRepaymentPrincipal = yearSumRepaymentPrincipalMap.get(key);
            //每年总开销金额
            double yearSumRepayment = yearSumPersonalSpendListMap.get(key);
            //个人贷款  TODO 按150万
            double yearLoanPersonal = yearSumLoanPersonalMap.get(key);

            double yearSumMoney = map.getValue();
            double yearIncomeInterest = yearSumMoney - yearSumRepaymentInterest - yearSumRepayment - yearLoanPersonal;
            double yearIncomePrincipal = yearSumMoney - yearSumRepaymentPrincipal - yearSumRepayment - yearLoanPersonal;

            System.out.println("等额本息 拥有第" + rentNumber +
                    " 套出租屋时  第" + key + "(年) " + df.format(yearIncomeInterest) + " 的收益数据 " +
                    " 年总收益 " + df.format(yearSumMoney) +
                    " 年总支出 " + df.format(yearSumRepaymentInterest + yearSumRepayment) +
                    " 年开销金额 " + df.format(yearSumRepayment) +
                    " 年总还款 " + df.format(yearSumRepaymentInterest) +
                    " 个人贷款年总还款 " + df.format(yearLoanPersonal));
            System.out.print("等额本金 拥有第" + rentNumber +
                    " 套出租屋时  第" + key + "(年) " + df.format(yearIncomePrincipal) +
                    " 的收益数据 年总收益 " + df.format(yearSumMoney) +
                    " 年总支出 " + df.format(yearSumRepaymentPrincipal + yearSumRepayment) +
                    " 年开销金额 " + df.format(yearSumRepayment) +
                    " 年总还款 " + df.format(yearSumRepaymentPrincipal) +
                    " 个人贷款年总还款 " + df.format(yearLoanPersonal));
            System.out.println("\n");
        }
    }
}

class RentModel {
    private double monthRent;//月租
    private double yearRiseMoney;//年增加
    private double yearSumMoney;//年收

    public double getMonthRent() {
        return monthRent;
    }

    public void setMonthRent(double monthRent) {
        this.monthRent = monthRent;
    }

    public double getYearRiseMoney() {
        return yearRiseMoney;
    }

    public void setYearRiseMoney(double yearRiseMoney) {
        this.yearRiseMoney = yearRiseMoney;
    }

    public double getYearSumMoney() {
        return yearSumMoney;
    }

    public void setYearSumMoney(double yearSumMoney) {
        this.yearSumMoney = yearSumMoney;
    }

    @Override
    public String toString() {
        return "RentModel{" +
                "monthRent=" + monthRent +
                ", yearRiseMoney=" + yearRiseMoney +
                ", yearSumMoney=" + yearSumMoney +
                '}';
    }
}

class HousingLoans {
    private double monthRepayment;//月还款
    private double yearSumRepayment;//年累计还款
    private double monthDiminishing;//等额本金 递减金额

    public double getMonthRepayment() {
        return monthRepayment;
    }

    public void setMonthRepayment(double monthRepayment) {
        this.monthRepayment = monthRepayment;
    }

    public double getYearSumRepayment() {
        return yearSumRepayment;
    }

    public void setYearSumRepayment(double yearSumRepayment) {
        this.yearSumRepayment = yearSumRepayment;
    }

    public double getMonthDiminishing() {
        return monthDiminishing;
    }

    public void setMonthDiminishing(double monthDiminishing) {
        this.monthDiminishing = monthDiminishing;
    }

    @Override
    public String toString() {
        return "HousingLoans{" +
                "monthRepayment=" + monthRepayment +
                ", yearSumRepayment=" + yearSumRepayment +
                ", monthDiminishing=" + monthDiminishing +
                '}';
    }
}

class PersonalSpend {
    private double monthRiseMoney;//月开销
    private double monthIncrement;//每月递增开销
    private double yearRiseMoney;//年开销

    public double getMonthRiseMoney() {
        return monthRiseMoney;
    }

    public void setMonthRiseMoney(double monthRiseMoney) {
        this.monthRiseMoney = monthRiseMoney;
    }

    public double getMonthIncrement() {
        return monthIncrement;
    }

    public void setMonthIncrement(double monthIncrement) {
        this.monthIncrement = monthIncrement;
    }

    public double getYearRiseMoney() {
        return yearRiseMoney;
    }

    public void setYearRiseMoney(double yearRiseMoney) {
        this.yearRiseMoney = yearRiseMoney;
    }

    @Override
    public String toString() {
        return "PersonalSpend{" +
                "monthRiseMoney=" + monthRiseMoney +
                ", monthIncrement=" + monthIncrement +
                ", yearRiseMoney=" + yearRiseMoney +
                '}';
    }
}


class ApartmentValue {
    private Double apartmentTotal;//房屋总金额

    private Double loansTotal; //房屋贷款总金额

    private Double repaymentTotal;//总还款金额

    private Double payInterestTotal;//支付总利息

    private Double payTotal;//首期支付

    public Double getApartmentTotal() {
        return apartmentTotal;
    }

    public void setApartmentTotal(Double apartmentTotal) {
        this.apartmentTotal = apartmentTotal;
    }

    public Double getLoansTotal() {
        return loansTotal;
    }

    public void setLoansTotal(Double loansTotal) {
        this.loansTotal = loansTotal;
    }

    public Double getRepaymentTotal() {
        return repaymentTotal;
    }

    public void setRepaymentTotal(Double repaymentTotal) {
        this.repaymentTotal = repaymentTotal;
    }

    public Double getPayInterestTotal() {
        return payInterestTotal;
    }

    public void setPayInterestTotal(Double payInterestTotal) {
        this.payInterestTotal = payInterestTotal;
    }

    public Double getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Double payTotal) {
        this.payTotal = payTotal;
    }
}