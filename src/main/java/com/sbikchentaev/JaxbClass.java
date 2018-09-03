package com.sbikchentaev;

import com.sbikchentaev.generated.SimpleCalculator;
import com.sbikchentaev.generated.SimpleCalculatorObjectFactory;
import com.sbikchentaev.generated.Term;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JaxbClass {


    public static void main(String[] args) throws Exception {
        SimpleCalculatorObjectFactory factory = new SimpleCalculatorObjectFactory();
        JAXBContext jc = JAXBContext.newInstance(SimpleCalculator.class, Term.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        final SimpleCalculator unmarshal = (SimpleCalculator) unmarshaller.unmarshal(new File("sampleTest.xml"));
        List<SimpleCalculator.Expressions.Expression> expressionList = unmarshal.getExpressions().getExpression();
        List<SimpleCalculator.ExpressionResults.ExpressionResult> collect = expressionList.stream().map(x -> {
            try {
                return TreeHelper.rec(x.getOperation());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).map(x -> {
            SimpleCalculator.ExpressionResults.ExpressionResult expressionResult = factory.createSimpleCalculatorExpressionResultsExpressionResult();
            expressionResult.setResult(x);
            return expressionResult;
        }).collect(Collectors.toList());


        Marshaller marshaller = jc.createMarshaller();
        File f = new File("marshaled.xml");
        SimpleCalculator marshall = factory.createSimpleCalculator();
        SimpleCalculator.ExpressionResults expressionResults = factory.createSimpleCalculatorExpressionResults();
        List<SimpleCalculator.ExpressionResults.ExpressionResult> expressionResultList = expressionResults.getExpressionResult();
        expressionResultList.addAll(collect);
        marshall.setExpressionResults(expressionResults);
        marshaller.marshal(marshall, f);
    }

}
