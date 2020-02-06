import DataDealer.DataDealer;
import Math.BasicMath;
import Math.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class MathTest {

    private double[] testEnergyArray = {1.0,2.0};
    private double[] testValueArray = {5.0,5.0};
    private double[] testMinuendArray = {6.0,6.0};
    private double[] testNewValueArray = {10.0,10.0};
    private double[] onesArray = {1.0,1.0};
    private double[] testErrorsArray = {0.0,0.0};


    @Test
    public void testQuickRound(){
        Locale.setDefault(Locale.US);
        double testValue = 123.4567778;
        Assert.assertEquals(123.46, BasicMath.quickRound(testValue),0.01);
    }

    @Test
    public void testQuickRoundWithTwoNumbersAfterPoint(){
        double testValue = 123.0;
        String stringValue = String.valueOf(BasicMath.quickRound(testValue));
        Assert.assertEquals("123.0", stringValue);

    }

    @Test
    public void testSum(){
        DataDealer answerData = new DataDealer(testEnergyArray,testNewValueArray,testErrorsArray,"AnswerArray");
        DataDealer testData1 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Answer");
        DataDealer testData2 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Array");
        SumCalculator calculator = new SumCalculator();
        Assert.assertTrue(answerData.equals(calculator.calculate(testData1, testData2)));
    }

    @Test
    public void testDelta() {
        DataDealer subtract = new DataDealer(testEnergyArray, testValueArray, testErrorsArray, "subtract");
        DataDealer minuend = new DataDealer(testEnergyArray, testMinuendArray,testErrorsArray, "minuend");
        DataDealer answer = new DataDealer(testEnergyArray, onesArray,testErrorsArray, "answer");
        DeltaCalculator calculator = new DeltaCalculator();
        Assert.assertTrue(answer.equalsIgnoreLabel(calculator.calculate(minuend,subtract)));
    }




}
