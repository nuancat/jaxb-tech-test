
package com.sbikchentaev.generated;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "term", propOrder = {
        "arg",
        "arg1",
        "operation1",
        "operation2",
        "arg2",
        "operation"
})
public class Term {

    @XmlSchemaType(name = "nonNegativeInteger")
    protected List<Double> arg;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Double arg1;
    protected Term operation1;
    protected Term operation2;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected Double arg2;
    protected List<Term> operation;
    @XmlAttribute(name = "OperationType")
    protected String operationType;

    public List<Double> getArg() {
        return this.arg;
    }

    public Double getArg1() {
        return arg1;
    }

    public void setArg1(Double value) {
        this.arg1 = value;
    }

    public Term getOperation1() {
        return operation1;
    }

    public void setOperation1(Term value) {
        this.operation1 = value;
    }

    public Term getOperation2() {
        return operation2;
    }

    public void setOperation2(Term value) {
        this.operation2 = value;
    }

    public Double getArg2() {
        return arg2;
    }

    public void setArg2(Double value) {
        this.arg2 = value;
    }

    public List<Term> getOperation() {
        return this.operation;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String value) {
        this.operationType = value;
    }

}
