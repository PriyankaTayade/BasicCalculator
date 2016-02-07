package Test;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Calculator.ExpressionTree;

public class TestExpTree {
	 @Test
	 @Parameters({"exp","result"})
	  public void TestExp(String exp, int result) {
		  ExpressionTree expression=new ExpressionTree(exp);
		  if(result==expression.getExpressionResult())
			  Assert.assertTrue(true,"Actual result match the exprected result "+result);
		  else 
			  Assert.assertTrue(false,"Actual result do not match the exprected result "+result);
	  }
}
