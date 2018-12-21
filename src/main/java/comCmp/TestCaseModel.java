package comCmp;

/**
 * 功能描述: 测试类，用于统一规范模板  Model 类
 *
 * @auther: lai.zhihui
 * @date: 2018/10/18 10:41
 */
public class TestCaseModel {
    //测试属性名称
    private String testName;
    //测试属性年龄
    private int testAge;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestAge() {
        return testAge;
    }

    public void setTestAge(int testAge) {
        this.testAge = testAge;
    }

    public TestCaseModel(String testName, int testAge) {
        this.testName = testName;
        this.testAge = testAge;
    }

    public TestCaseModel() {
    }

    @Override
    public String toString() {
        return "TestCaseModel{" +
                "testName='" + testName + '\'' +
                ", testAge=" + testAge +
                '}';
    }
}
