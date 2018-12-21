package comCmp;

public class ReturnModel {
    private String returnString;

    public String getReturnString() {
        return returnString;
    }

    public void setReturnString(String returnString) {
        this.returnString = returnString;
    }

    @Override
    public String toString() {
        return "ReturnModel{" +
                "returnString='" + returnString + '\'' +
                '}';
    }
}
