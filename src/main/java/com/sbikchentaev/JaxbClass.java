package com.sbikchentaev;

import com.sbikchentaev.generated.SimpleCalculator;
import com.sbikchentaev.generated.SimpleCalculatorObjectFactory;
import com.sbikchentaev.generated.Term;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс обработчик xml структуры SimpleCalculator
 */
public class JaxbClass {
    /**
     * Чтение xml и заполнение модели с помощью JAXB
     *
     * @param reader поток чтения
     * @return модель
     * @throws JAXBException - если xml не прочитана, либо неправильно размечена структура xml
     */
    private static SimpleCalculator unmarshall(Reader reader) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(SimpleCalculator.class, Term.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (SimpleCalculator) unmarshaller.unmarshal(reader);
    }

    /**
     * Запись xml из модели с помощью JAXB
     *
     * @param marshall модель
     * @param writer   поток запись
     * @throws JAXBException ошибка в записи из-за неправильно инстанциированного {@link JAXBContext}
     */
    private static void marshall(SimpleCalculator marshall, Writer writer) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(SimpleCalculator.class, Term.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.marshal(marshall, writer);
    }

    /**
     * Точка входа в класс <br>
     * Осуществляется обработка входящего потока и запись в выходящий
     * Достается из модели структура математических операций и решается через рекурсивный алгоритм {@link TreeHelper}
     *
     * @param reader поток чтения
     * @param writer поток записи
     * @throws JAXBException
     */
    static void prod(Reader reader, Writer writer) throws JAXBException {
        SimpleCalculatorObjectFactory factory = new SimpleCalculatorObjectFactory();
        SimpleCalculator expression = unmarshall(reader);
        List<SimpleCalculator.Expressions.Expression> expressionList = expression.getExpressions().getExpression();
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
        SimpleCalculator results = factory.createSimpleCalculator();
        SimpleCalculator.ExpressionResults expressionResults = factory.createSimpleCalculatorExpressionResults();
        List<SimpleCalculator.ExpressionResults.ExpressionResult> expressionResultList = expressionResults.getExpressionResult();
        expressionResultList.addAll(collect);
        results.setExpressionResults(expressionResults);
        marshall(results, writer);
    }
}
