package Calculator;
import java.util.Stack;

public class Calculator {

	public int BasicArithmatic(int var1, int var2, String op){

		switch(op){
		case "add" : var1 = var1  + var2;
					break;
		case "sub" : var1= var1 - var2;
					break;
		case "div" : var1= var1 / var2;
					break;
		case "mult" : var1= var1 * var2;
					break;	
		}
		return var1;
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
