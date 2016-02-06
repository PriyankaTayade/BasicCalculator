package Calculator;
import java.util.Stack;

public class Calculator {

	public int BasicArithmatic(int var1, int var2, String op){

		if(op.equals("add"))
			return var1  + var2;
		
		if(op.equals("sub"))
			return var1  - var2;
		
		if(op.equals("div"))
			return var1  / var2;
		
		if(op.equals("mult"))
			return var1  * var2;

		return Integer.MIN_VALUE;
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
