
package com.sbikchentaev.generated;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "expressions",
        "expressionResults"
})
@XmlRootElement(name = "simpleCalculator")
public class SimpleCalculator {

    protected SimpleCalculator.Expressions expressions;
    protected SimpleCalculator.ExpressionResults expressionResults;


    public SimpleCalculator.Expressions getExpressions() {
        return expressions;
    }

    public void setExpressions(SimpleCalculator.Expressions value) {
        this.expressions = value;
    }


    public SimpleCalculator.ExpressionResults getExpressionResults() {
        return expressionResults;
    }

    public void setExpressionResults(SimpleCalculator.ExpressionResults value) {
        this.expressionResults = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "expressionResult"
    })
    public static class ExpressionResults {

        @XmlElement(required = true)
        protected List<SimpleCalculator.ExpressionResults.ExpressionResult> expressionResult;

        public List<SimpleCalculator.ExpressionResults.ExpressionResult> getExpressionResult() {
            if (expressionResult == null) {
                expressionResult = new ArrayList<SimpleCalculator.ExpressionResults.ExpressionResult>();
            }
            return this.expressionResult;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "result"
        })
        public static class ExpressionResult {

            protected double result;

            public double getResult() {
                return result;
            }

            public void setResult(double value) {
                this.result = value;
            }

        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "expression"
    })
    public static class Expressions {

        @XmlElement(required = true)
        protected List<SimpleCalculator.Expressions.Expression> expression;

        public List<SimpleCalculator.Expressions.Expression> getExpression() {
            if (expression == null) {
                expression = new ArrayList<SimpleCalculator.Expressions.Expression>();
            }
            return this.expression;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "operation"
        })
        public static class Expression {

            @XmlElement(required = true)
            protected Term operation;

            public Term getOperation() {
                return operation;
            }

            public void setOperation(Term value) {
                this.operation = value;
            }

        }

    }

}
