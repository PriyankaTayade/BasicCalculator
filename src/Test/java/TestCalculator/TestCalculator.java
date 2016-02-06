package TestCalculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import Calculator.Calculator;

public class TestCalculator {
	
  @Test
  @Parameters({"var1","var2","op","expectedResult"})
  public void TestBasicArithmaticFunction(int var1, int var2, String op, int expectedResult) {
	  Calculator calci=new Calculator();
	  int result=calci.BasicArithmatic(var1, var2, op);
	  if(expectedResult==result)
		  Assert.assertTrue(true,"Actual result match the exprected result ");
  }
}
