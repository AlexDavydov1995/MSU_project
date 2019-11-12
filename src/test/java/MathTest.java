import DataDealer.DataDealer;
import junit.framework.TestCase;
import Math.QuickMath;
import Math.MathDealer;

public class MathTest extends TestCase {



    public void testQuickRound(){
        double testValue = 123.4567778;
        assertEquals(123.46, QuickMath.quickRound(testValue));
    }

    public void testSum(){
        double[] testEnergyArray = {1.0,2.0};
        double[] testValueArray = {5.0,5.0};
        double[] testNewValueArray = {10.0,10.0};
        double[] testErrorsArray = {0.0,0.0};

        DataDealer answerData = new DataDealer(testEnergyArray,testNewValueArray,testErrorsArray,"AnswerArray");
        DataDealer testData1 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Answer");
        DataDealer testData2 = new DataDealer(testEnergyArray,testValueArray,testErrorsArray,"Array");
        assertTrue(answerData.equals(MathDealer.sum(testData1,testData2)));
    }
}
