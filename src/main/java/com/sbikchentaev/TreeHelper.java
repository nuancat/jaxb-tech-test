package com.sbikchentaev;

import com.sbikchentaev.generated.Term;

import static com.sbikchentaev.TreeHelper.OperationsType.*;

public class TreeHelper {
    public static double rec(Term t) throws Exception {
        if (t.getOperation() != null) {
            normalizeOperations(t);
        }

        if (t.getArg() != null) {
            normalizeArguments(t);
        }

        if (t.getOperation1() != null) {
            t.setArg1(rec(t.getOperation1()));
        }
        if (t.getOperation2() != null) {
            t.setArg2(rec(t.getOperation2()));
        }

        return exec(t);

    }

    public static double exec(Term t) throws Exception {
        switch (t.getOperationType()) {
            case SUM:
                return t.getArg1() + t.getArg2();
            case SUB:
                return t.getArg1() - t.getArg2();
            case MULTIPLY:
                return t.getArg1() * t.getArg2();
            case DIVISION:
                return t.getArg1() / t.getArg2();
            default:
                throw new Exception("XML OperationType error");
        }
    }

    public static void normalizeOperations(Term t) {
        t.setOperation1(t.getOperation().get(0));
        t.setOperation2(t.getOperation().get(1));
    }

    public static void normalizeArguments(Term t) {
        t.setArg1(t.getArg().get(0));
        t.setArg2(t.getArg().get(1));
    }

    public static class OperationsType {
        public static final String MULTIPLY = "MUL";
        public static final String DIVISION = "DIV";
        public static final String SUM = "SUM";
        public static final String SUB = "SUB";
    }
}
