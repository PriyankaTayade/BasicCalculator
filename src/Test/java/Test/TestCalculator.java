package Test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import Calculator.Calculator;

/**
 * 
 * @author priyanka
 *
 */
public class TestCalculator {
	
  @Test
  @Parameters({"var1","var2","op","expectedResult"})
  public void TestBasicArithmaticFunction(int var1, int var2, String op, int expectedResult) {
	  Calculator calci=new Calculator();
	  if(expectedResult==calci.BasicArithmatic(var1, var2, op))
		  Assert.assertTrue(true,"Actual result match the exprected result "+expectedResult);
	  else
		  Assert.assertTrue(false,"Actual result do not match the exprected result "+expectedResult);
  }
}
