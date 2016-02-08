package Calculator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class to perform basic arithmetic function such as , addition, division , subtraction and multiplication
 * @author priyanka
 *
 */
public class Calculator {
	private static final Logger logger = LogManager.getLogger(Calculator.class);
	
	public Calculator(){
		logger.trace("Calculator Initialized");
	}
	
	/**
	 * 
	 * @param var1 takes first operand as integer parameter
	 * @param var2 takes second operand as integer parameter
	 * @param op takes operator as string parameter
	 * @return returns the result operation
	 * @throws ArithmeticException throws basic arithmetic exception for output out of range or divide by zero error.
	 */
	public int BasicArithmatic(int var1, int var2, String op) throws ArithmeticException{
		
		logger.trace("BasicArithmatic method called");

		int result;
	
				if(op.equals("add")){
					
					logger.info("Add operation found");
					
					result= Math.addExact(var1, var2);
					
					logger.debug("BasicArithmatic method for add operation"+var1+" and "+var2+" result"+result);
														
				}
				
				else if(op.equals("sub")){
					
					logger.info("Subtract operation found");

					result= Math.subtractExact(var1, var2);
					
					logger.debug("BasicArithmatic method for sub operation"+var1+" and "+var2+" result"+result);

				}
				
				else if(op.equals("div")){
					logger.info("div operation found");

					if(var2==0)
						throw new ArithmeticException("You can\'t divide by zero!");
					
					result= Math.floorDiv(var1, var2);
					
					logger.debug("BasicArithmatic method for div operation"+var1+" and "+var2+" result"+result);
					
				}
				
				else if(op.equals("mult")){
					logger.info("mult operation found");

					result= Math.multiplyExact(var1, var2);
					logger.debug("BasicArithmatic method for mult operation"+var1+" and "+var2+" result"+result);
					
				}

				else{
					logger.error("operation type do not match add, sub, div, mult ");
					throw new ArithmeticException("Invalid operation type");
				}
				return result;
		
	}
	
	/*public boolean ValidateInput(String input){
		int index=0;
		Stack<String> validate=new Stack<String>();		
		while(index<input.length())
		{
			
			char c=input.charAt(index);
			if(c=='(')
			{
				validate.push("(");
			}			
			if(c==')')
			{
				if(validate.isEmpty() || !validate.peek().equals("("))
				{
					System.out.println("Invalid Input");
					return false;
				}					
				else 
				{
					validate.pop();
				}
			}
			index++;				
		}
		if(!validate.isEmpty())
		{
			System.out.println("Invalid Input");
			return false;
			
		}
		return true;
	}*/
}
