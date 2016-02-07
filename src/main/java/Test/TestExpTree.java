package Test;
//import static org.junit.Assert.*;

//import org.junit.Test;

//import org.junit.Test;

import Calculator.ExpressionTree;

/**
 * This is the unit class to test ExpressionTree
 * @author priyanka
 *
 */
public class TestExpTree {

	 //@Test
	  public void TestExp() {
		 
		 	String[] exp= {"add(2,5)"
		 			       , "add(1,mult(2,3))"
		 			       , "mult(add(2,2),div(9,3))"
		 			       , "let(a,5,add(a,10))"
		 	               ,"let(a,5,let(b,mult(a,10),add(b,a)))"};
		 int[] result={7,7,12,15,55};
		 
		 for(int i=0;i<exp.length;i++){
			 ExpressionTree expression=new ExpressionTree(exp[i]);
			 //assertEquals(result[i],expression.getExpressionResult()); 
			 if(result[i]!=expression.getExpressionResult()){
				 System.out.println("Test Case failed");
				 break;
			 }
			 else System.out.println("Test Case "+exp[i]+"passed");
		 }
	  }
	 
	 
}
