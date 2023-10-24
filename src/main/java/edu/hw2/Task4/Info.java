package edu.hw2.Task4;

public class Info {

    private Info() {

    }

    public static CallingInfo callingInfo() {
        StackTraceElement traceElement = new Throwable().getStackTrace()[1];
        return new CallingInfo(traceElement.getClassName(), traceElement.getMethodName());
    }

}
