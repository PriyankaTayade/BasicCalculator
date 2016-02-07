package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Calculator.Calculator;

/**
 * 
 * @author priyanka
 *
 */
public class TestCalculator {
	
  @Test
  public void TestBasicArithmaticFunction() {
	  Calculator calci=new Calculator();
	  String[] op={"add","sub","mult","div"};
	  
	  int var1=4;
	  int var2=2;
	  
	  int[] result={6,2,8,2};
	  
	  for(int i=0;i<op.length;i++){

		 assertEquals(result[i],calci.BasicArithmatic(var1, var2, op[i])); 
	 }
  }
}
