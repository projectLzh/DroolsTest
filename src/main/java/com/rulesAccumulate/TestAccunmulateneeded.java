package com.rulesAccumulate;

import org.kie.api.runtime.rule.AccumulateFunction;

import java.io.*;

public class TestAccunmulateneeded implements AccumulateFunction{



    public static class Factorial implements Externalizable {
        public Factorial(){}

        public double total = 1;
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeDouble(total);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            total=in.readDouble();
        }
    }


    @Override
    public Class<?> getResultType() {
        System.out.println("getResultType");
        return Number.class;

    }

    @Override
    public Serializable createContext() {
        System.out.println("createContext");
        return new Factorial();
    }

    @Override
    public void init(Serializable serializable) throws Exception {
        Factorial factorial= (Factorial) serializable;
        factorial.total=1;
        System.out.println("init");
    }

    @Override
    public void accumulate(Serializable serializable, Object o) {
        Factorial factorial= (Factorial) serializable;
        factorial.total *= ((Number)o).doubleValue();
        System.out.println("accumulate");
    }

    @Override
    public void reverse(Serializable serializable, Object o) throws Exception {
        Factorial factorial= (Factorial) serializable;
        factorial.total /= ((Number)o).doubleValue();
        System.out.println("reverse");
    }

    @Override
    public Object getResult(Serializable serializable) throws Exception {
        Factorial factorial= (Factorial) serializable;
        Double d  =new Double(((Factorial) serializable).total ==1?1:((Factorial) serializable).total);
        return d;
    }


    @Override
    public boolean supportsReverse() {
        System.out.println("supportsReverse");
        return true;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal");
    }
}
