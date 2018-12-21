package comTwo.flow;

public class FlowToJavaTwo {
    private static final FlowToJavaTwo FLOW=new FlowToJavaTwo();
    public static FlowToJavaTwo getFlow(){
        return FLOW;
    }
    public   void flowToJava01() {
        System.out.println("第二种通过单例模式调用Java方法");
    }
}
