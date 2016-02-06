package Calculator;

import java.util.HashMap;
import java.util.Stack;

public class ExpressionTree {
	
	private ExpressionNode _head=null;
	private static HashMap<String,Integer> _variables=new HashMap<String,Integer>();
	
	ExpressionTree(String expression){
		ExpressionBuild(expression);
	}
	
	public int getExpressionResult(){
		return InOrderTraversalExpTree(_head);
	}
		
	private int InOrderTraversalExpTree(ExpressionNode node){
		
		if(node==null)
			return 0;
		
		if(node.isLeaf)
		{

			if(node.name==null)
				return node.value;
			else 
				return _variables.get(node.name);
			
		}
		
		int var1=0;
		int var2=0;
		
		if(node.left!=null)
			var1=InOrderTraversalExpTree(node.left);
		

		if(node.operator!=null && node.operator.equals("let"))
			_variables.put(node.name, var1);

	
		if(node.right!=null)
			var2=InOrderTraversalExpTree(node.right);
		
		if(!node.operator.equals("let"))
			var2=new Calculator().BasicArithmatic(var1, var2, node.operator);
		
		return var2;
	}
	
	private ExpressionNode ExpressionNodeBuilder(String input){
		
		ExpressionNode node=new ExpressionNode();
		
		if(input.equals("let"))
		{
			node.operator="let";
			node.value=-1;
			return node;
		}
		else if(input.startsWith("add"))
		{
			node.operator="add";
			node.value=-1;
			return node;
		}
		else if(input.startsWith("sub"))
		{
			node.operator="sub";
			node.value=-1;
			return node;
		}
		else if(input.startsWith("mult")){
			
			node.operator="mult";
			node.value=-1;
			return node;
		}
		else if(input.startsWith("div"))
		{
			node.operator="div";
			node.value=-1;
			return node;
		}
		
		else if(input.matches("[0-9]+")){

			node.value=Integer.parseInt(input);
			node.isLeaf=true;
			return node;
		}
		else if(input.matches("[a-zA-Z]+")){
			node.value=-1;
			node.name=input;
			node.isLeaf=true;
			return node;
		}
		return null;
	}
	
	private ExpressionNode ExpressionBuild(String input){
		
		if(input==null)
			return null;
		
		if(input.isEmpty())
			return null;

		Stack<ExpressionNode> backtrack=new Stack<ExpressionNode>();
		ExpressionNode node=new ExpressionNode();

		int i=0;
		
		while(i<input.length())
		{
			if(input.charAt(i)=='('){
				
				String prefix=input.substring(0,i);

				node=ExpressionNodeBuilder(prefix);
				
				if(backtrack.isEmpty())
				{
					backtrack.push(node);
					_head=node;
				}
				
				else if(backtrack.peek().left==null)
				{
					backtrack.peek().left=node;
					if(!node.isLeaf)
						backtrack.push(node);					
				}
				else
				{
					backtrack.peek().right=node;
					if(!node.isLeaf)
						backtrack.push(node);
				}
				
				input=input.substring(i+1);
				i=0;
								
			}
			else if(input.charAt(i)==','){
				
				String prefix=input.substring(0,i);
				
				if(!prefix.equals(""))
				{
					node=ExpressionNodeBuilder(prefix);	
					if(backtrack.peek().operator!=null && backtrack.peek().operator.equals("let") && node.name!=null)
							backtrack.peek().name=node.name;
					else {
						backtrack.peek().left=node;
					
						if(!node.isLeaf){
						
							backtrack.push(node);	
						}
					}
					
				}
				else {
					backtrack.pop();
				}
				input=input.substring(i+1);
				i=0;
			}
			else if(input.charAt(i)==')'){
				
				String prefix=input.substring(0,i);	
								
				if(!prefix.equals(""))
				{
					node=ExpressionNodeBuilder(prefix);	
					
					backtrack.peek().right=node;

					if(!node.isLeaf)
						backtrack.push(node);
				}
				else {
					backtrack.pop();
				}
				input=input.substring(i+1);
				i=0;
				
			}
			else i++;
		}
		
		return _head;
	}
	

	 public ExpressionNode getHead() {
		return _head;
	}


	private class ExpressionNode {
		 
		String name=null;
		int value;
		String operator=null;
		boolean isLeaf=false;
		
		ExpressionNode left=null;
		ExpressionNode right=null;
			
	}
	
}
