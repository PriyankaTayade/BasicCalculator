package Calculator;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Calculator {
	private static final Logger logger = LogManager.getLogger(Calculator.class);
	
	public Calculator(){
		logger.trace("Calculator Initialized");
	}

	public int BasicArithmatic(int var1, int var2, String op) throws ArithmeticException{
		
		logger.trace("BasicArithmatic method called");

		int result;
	
				if(op.equals("add")){
					
					logger.info("Add operation found");
					
					result= var1 + var2;
					
					logger.debug("BasicArithmatic method for add operation"+var1+" and "+var2+" result"+result);
					
					if(Integer.MAX_VALUE>result && Integer.MIN_VALUE<result)
						return result;
					else{
						
						logger.error("Result out of range "+result);
						throw new ArithmeticException("Result out of range");
					}

				}
				
				else if(op.equals("sub")){
					
					logger.info("Subtract operation found");

					result= var1 - var2;
					
					logger.debug("BasicArithmatic method for sub operation"+var1+" and "+var2+" result"+result);

					if(Integer.MAX_VALUE>result && Integer.MIN_VALUE<result)
						return result;
					else{
						
						logger.error("Result out of range "+result);
						throw new ArithmeticException("Result out of range");
					}
				}
				
				else if(op.equals("div")){
					logger.info("div operation found");

					if(var2==0)
						throw new ArithmeticException("You can\'t divide by zero!");
					
					result= var1 / var2;
					
					logger.debug("BasicArithmatic method for div operation"+var1+" and "+var2+" result"+result);

					if(Integer.MAX_VALUE>result && Integer.MIN_VALUE<result)
						return result;
					else{
						logger.error("Result out of range "+result);
						throw new ArithmeticException("Result out of range");
					}
				}
				
				else if(op.equals("mult")){
					logger.info("mult operation found");

					result= var1 * var2;
					logger.debug("BasicArithmatic method for mult operation"+var1+" and "+var2+" result"+result);

					if(Integer.MAX_VALUE>result && Integer.MIN_VALUE<result)
						return result;
					else {
						logger.error("Result out of range "+result);
						throw new ArithmeticException("Result out of range");
					}
				}

				else{
					logger.error("operation type do not match add, sub, div, mult ");
					throw new ArithmeticException("Invalid operation type");
				}
		
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
