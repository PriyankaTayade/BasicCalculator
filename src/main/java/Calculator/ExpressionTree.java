package Calculator;
/**
 * 
 */
import java.util.HashMap;
import java.util.Stack;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 
 * @author priyanka
 *
 */
public class ExpressionTree {

	private static final Logger logger = LogManager.getLogger(ExpressionTree.class);
	private ExpressionNode _head=null;
	private static HashMap<String,Integer> _variables=new HashMap<String,Integer>();
	
	/**
	 * 
	 * @param expression
	 * @throws Exception 
	 */
	public ExpressionTree(String expression){
		logger.trace("Contructor for Expression tree called");
		try{
			
			ExpressionBuild(expression);
			logger.info("Proccessed input");
		}
		catch(Exception e)
		{
			logger.error(e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getExpressionResult(){
		logger.trace("getExpressionResult method is called called");

		try{
		return TraverseExpTree(_head);
		}
		catch(Exception e){
			logger.error(e);
			return 0;
		}
	}
	
	/**
	 * This method is used to evaluate the expression tree by post order traversal and returns the result
	 * @param node Takes parameter as Expression Node
	 * @return
	 */
	
	/*
	 * Method can be further improved by using a 
	 * iterative method rather than calling the function recursively
	 * This can be done by maintaining a programmatic stack 
	 */
	private int TraverseExpTree(ExpressionNode node){
		
		logger.trace("TraverseExpTree is called");
		
		if(node==null)
		{
			logger.error("Invalid expression tree, cannot evaluate");
			return 0;
		}
		
		if(node.isLeaf)
		{
			if(node.name==null){
				logger.debug("Leaf node with value "+node.value +"");
				return node.value;
			}
			else 
			{	if(_variables.get(node.name)==null){
					logger.error("Invalid input, cannot evaluate");				
					throw new IllegalArgumentException("Invalid expression tree");
				}
				logger.debug("Leaf node with value "+_variables.get(node.name) +" varibale name "+node.name);
				return _variables.get(node.name);
			}
			
		}
		
		int var1=0;
		int var2=0;
		
		if(node.left!=null){
			logger.trace("Calling TraverseExpTree on left node");
			var1=TraverseExpTree(node.left);
			logger.debug("Output of left node traversal is "+var1);
		}
		

		if(node.operator!=null && node.operator.equals("let")){
			logger.debug("let operator found storing variable with name"+node.name+" and value "+var1);
			_variables.put(node.name, var1);
		}

	
		if(node.right!=null){
			logger.trace("Calling TraverseExpTree on right node");
			var2=TraverseExpTree(node.right);
			logger.debug("Output of right node traversal is "+var2);
		}
		
		if(!node.operator.equals("let")){
			try{
				logger.trace("Evaluating operator "+node.operator+ " for values "+var1+" and "+var2);
				var2=new Calculator().BasicArithmatic(var1, var2, node.operator);
				logger.debug("Result of "+node.operator+" is "+var2);
			}
			catch(Exception e){
				logger.error(e);
				return 0;
			}
		}
		logger.trace("Returning result "+var2);
		return var2;
	}

	/**
	 *  This methods is used to build an expression tree node 
	 * @param input takes string as input parameter 
	 * @return return the expression node
	 * @throws NumberFormatException if the input string exceed the max rang of integer then a number expression is thrown
	 */
	private ExpressionNode ExpressionNodeBuilder(String input) throws NumberFormatException{
		
		ExpressionNode node=new ExpressionNode();
		logger.trace("New Node is initialized");
		//check of the string matches valid operator or number or variable name
		
		if(input.equals("let"))   
		{
			node.operator="let";
			node.value=-1;
			logger.trace("Let operator is set with value -1");			
			return node;
		}
		else if(input.startsWith("add"))
		{
			node.operator="add";
			node.value=-1;
			logger.trace("add operator is set with value -1");			
			return node;
		}
		else if(input.startsWith("sub"))
		{
			node.operator="sub";
			node.value=-1;
			logger.trace("sub operator is set with value -1");			
			return node;
		}
		else if(input.startsWith("mult")){
			
			node.operator="mult";
			node.value=-1;
			logger.trace("mult operator is set with value -1");			
			return node;
		}
		else if(input.startsWith("div"))  
		{		
			node.operator="div";
			node.value=-1;
			logger.trace("div operator is set with value -1");
			return node;
		}
		
		else if(input.matches("[0-9]+")){ 
			//regular expression matches numbers so set value of node
			node.value=Integer.parseInt(input);   //will throw number format exception of large number string is given grater than range of string
			node.isLeaf=true;   //value will be leaf
			logger.trace("value of the node is set to "+node.value+ " and is set to leaf node");
			return node;
		}
		else if(input.matches("[a-zA-Z]+")){  //regular expression matches alphabets the set name of the node
			node.value=-1;
			node.name=input;
			node.isLeaf=true;     //variable with no value will be leaf
			logger.trace("node of the node is set to "+node.name+ " with value -1 and is set to leaf node");

			return node;
		}
		return null;  //if the input is invalid then return null
	}
	
	/**
	 * This method is used to generate expression tree 
	 * @param input takes string as input parameter to generate expression
	 * @return return expression tree head
	 */
	private ExpressionNode ExpressionBuild(String input){
		logger.trace("ExpressionBuild method called");
		
		if(input==null || input.isEmpty()){		//if the input string is null or empty return null
			logger.error("Empty or null input");
			return null;
		}
	try{	

		Stack<ExpressionNode> backtrack=new Stack<ExpressionNode>();  // Maintain a stack generate data in pre-order 
		ExpressionNode node=new ExpressionNode();   

		int index=0;
		
		while(index<input.length())
		{
			if(input.charAt(index)=='('){   // "(" will have operator as prefix and will have left and right child
				
				String prefix=input.substring(0,index); // get prefix string till index and generate node out of it

				node=ExpressionNodeBuilder(prefix);  
				if(node.operator==null)  //check if the operator  was not found
				{
					_head=null;
					logger.error("invalid input string");
					throw new IllegalArgumentException("invalid input string");
				}
				
				if(backtrack.isEmpty())      //if the stack is empty then the node is head 
				{
					logger.debug("head is found "+node.operator);
					backtrack.push(node);    //push the node to stack
					_head=node;				 //set head
				}
				
				else if(backtrack.peek().left==null)  //check if previous node has left by check top of stack
				{
					logger.debug("Left node is found "+node.operator);
					backtrack.peek().left=node;   //assign node as left node to previous node
					if(!node.isLeaf)             //if left is not found and if the node is not a leaf node then push on stack
						backtrack.push(node);					
				}
				else
				{
					logger.debug("Right node is found "+node.operator);

					backtrack.peek().right=node;    //else assign node as right node
					
					//if node is node not leaf push on stack so that we can add more
					if(!node.isLeaf)               
						backtrack.push(node);
				}
				
				input=input.substring(index+1); //since the prefix is processed remove the prefix from input string
				index=0;   //set index to 0 to process the cut string from start
								
			}
			else if(input.charAt(index)==','){   //if comma is found the element will now only can be left node
				
				String prefix=input.substring(0,index);
				
				if(!prefix.equals(""))   //check of prefix is not empty to avoid ")," such strings
				{
					node=ExpressionNodeBuilder(prefix);	
					
					if(backtrack.peek().operator!=null && backtrack.peek().operator.equals("let") && node.name!=null)
					{
						logger.debug("Left node is found with varibale name "+node.name);
						backtrack.peek().name=node.name;
					}
					else {
						
						if(backtrack.peek().left!=null){  //This will be thrown when invalid string is provided
							logger.error("invalid input string, possibly too many arguments "+node.value);
							throw new IllegalArgumentException("invalid input string");
						}
						
						logger.debug("Left node is found with value "+node.value);
						backtrack.peek().left=node;
					
						if(!node.isLeaf){
						
							backtrack.push(node);	
						}
					}
					
				}
				else {
					/*
					 * if prefix was empty that means we have process 
					 * all the child node for the previous node 
					 * we can now remove it from the stack
					 */
					backtrack.pop();
				}
				input=input.substring(index+1); //since the prefix is processed remove the prefix from input string
				index=0; //set index to 0 to process the cut string from start
			}
			else if(input.charAt(index)==')'){   //closing brackets are found therefore the node will be right node
				
				String prefix=input.substring(0,index);	
								
				if(!prefix.equals(""))  //check if the prefix is not empty to avoid ")))" such strings
				{
					node=ExpressionNodeBuilder(prefix);	
					
					if(node.name==null)
						logger.debug("Right node is found with value "+node.value);
					else 
						logger.debug("Right node is found with varibale name "+node.name);
					
					backtrack.peek().right=node;

					if(!node.isLeaf)
						backtrack.push(node);
				}
				else {
					/*
					 * if prefix was empty that means we have process 
					 * all the child node for the previous node 
					 * we can now remove it from the stack
					 */
					backtrack.pop();   
				}
				input=input.substring(index+1); //since the prefix is processed remove the prefix from input string
				index=0; //set index to 0 to process the cut string from start
				
			}
			else index++;  
		}
		
	}
	catch(Exception e){  //if the input string is in invalid format null pointer exception will be thrown 
		logger.error(e);
		_head=null;  //set the head to null
	}
		
		return _head; 
	}
	
	/**
	 * This method is used to get the head of the expression tree
	 * @return Returns the head of the expression tree
	 */
	 public ExpressionNode getHead() {
		return _head;
	}

	/**
	 * 
	 * @author priyanka
	 *
	 *Inner class to expression tree
	 * 
	 * Represents Expression tree node objects
	 */
	private class ExpressionNode {
		
		//used to store name of the node that represents
		//variable name for let or variable name in general in the expression
		String name=null;
			
		int value;				//stores the value of the node               
		String operator=null;    //stored if the node is an operator
		
		boolean isLeaf=false;      
		
		ExpressionNode left=null;     //Reference to left node of expression tree
		ExpressionNode right=null;		//Reference to left node of expression tree
			
	}
	
}
