# Basic Calculator
Perform Basic Arithmatic Operation Using CLI, Implemented using Expression Tree

PURPOSE

Write a calculator program in Java that evaluates expressions in a very simple integer expression language. The program takes an input on the command line, computes the result, and prints it to the console

COMMAND LINE ARGUMENT

1. first argument will be the input string, for expression

2. second arguemnt is optional, it will set the log level

Following are the example of valid expression

  "add(2,5)"
  
  "add(1,mult(2,3))"
  
  "mult(add(2,2),div(9,3))"
  
  "let(a,5,add(a,10))"
  
  "let(a,5,let(b,mult(a,10),add(b,a)))"
  

An expression is one of the of the following:

•	Numbers: integers between Integer.MIN_VALUE and Integer.MAX_VALUE

•	Variables: strings of characters, where each character is one of a-z, A-Z

•	Arithmetic functions: add, sub, mult, div, each taking two arbitrary expressions as arguments.  In other words, each argument may be any of the expressions on this list.

•	A “let” operator for assigning values to variables:

	let(<variable name>, <value expression>, <expression where variable is used>)

Default log level is set to ALL

following are the options, as valid second argument (all lowercase)

1. all

2. debug

3. info

4. erro

5. warn

6. trace

7. fatal

# Requirements 

Java 7 or higher
Maven 3.3
TestNG 6.9

NOTE: If you do not java installed

Here is the link to download

jdk : http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

jre: http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html

Also here is the link to how to setup java home

Tested on windows 8

IDE: eclipse mars

#ALGORITHM

TO PROCESS INPUT STRING:

We process input string and build a expression tree of the form

        add
        /  \
      2     3
      
      where left child: first operand
            right child: second operand
            parent: operator
            
  Example: let(a,5,let(b,mult(a,10),add(b,a)))
  
  The tree will be as follows
  
              let(a)
              /    \
             5     let(b)
                  /    \
                mult    add
              /   \     /   \
            a     10   b     a
            

The following algorithm build the tree in pre oder, root is added first, the left child and then the right child.

First we will look ta the node of the tree, it is as follows

ExpressionNode has fields
  name
  value
  operator
  left child
  right child

The following algorithm is used to generate expression tree
--------------------------------------------------------------------------------------------------------------------

  
				
		ExpressionNode head=null;
			
		ExpressionBuild(string) : returns head 
			
		if string =null or empty
         return null;
      
        stack
    
        while(i<length)
        {
    	  if  char at index  = (
        {
    	  	prefix = cut string 0 to i
    		  node = generatenode(prefix)
    
    		  if node.operator =null
    			  invalid arguments, return null value
    
    		  if stack is empty
    			  stack push node;
    			  node is head
    		
    		  else if stack top has left child
    			  stack top left child is node
    			 if node is not leaf -> stack push node
    
    		  else 
    			 stack top right child is node;
    			 if node is not leaf -> stack push node
    			
          string = remove prefix of string
    	      i=0 
    	   }
    
          if  char at index = “,”
          {
      		  prefix = cut string 0 to i
      
      		  if prefix is !=“”
      			  node = generatenode(prefix)
      
      			  if stack top has left child
      				  already left child is there so exception occurred, invalid string, return null
      				  
      			  if stack top node is let and node.name is not null
      				  stack top name value= node name
      				  
      			  else 
                  stack top left child is node
                  
      				    if node is not leaf -> stack push node
      				    
      	    else
      			   stack pop top element
           string =cut string by removing prefix of string
           i=0
           }
           
           if  char at index = )
           {
            prefix = cut string 0 to i
      
      		    if prefix is !=“”
      			    node = generatenode(prefix)
      			    
      			    stack top .right=node
      			    if node is not leaf -> stack push node
      			    
      		    else
      			  stack pop top element
      			  
      		string =cut string by remove prefix of string
      		i=0
      		}
      	i++
      	}
    return head
    
-------------------------------------------------------------------------------------------------------------------

Algorithm to construct node from prefix string is

       ExpressionNodeBuilder(string): returns ExpressionNode

      if string equals add
          ExpressionNode has operator value set to add
          node value is -1
          return ExpressionNode
          
      if string equals sub
          ExpressionNode has operator value set to sub
          node value is -1
          return ExpressionNode
          
      if string equals mult
          ExpressionNode has operator value set to mult
          node value is -1
          return ExpressionNode
          
      if string equals div
          ExpressionNode has operator value set to div
          node value is -1
          return ExpressionNode
          
      if string matches numbers
          node value is integer value of number, if number is very large to convert to string, error return null value
          set node to left
          return ExpressionNode
          
      if string matches alphabets
          node name is string
          set node to lef
          return ExpressionNode
          
      else nothing matches , invalid input, erro, return null value
    
---------------------------------------------------------------------------------------------------------------------------

Evaluating the expression tree can be done in post order traversal, but let makes it tricky, whenever we encounter let we will evaluate it first . let is basically assignment operator. This is a recursive alorithm, we can implement a iterative traversal for better meomry management. 

The following algorithm is used to comupte the result by traversal

		hashMap variables;
		
		TraverseExpTree(ExpressionNode node): return result

		node==null
			Invalid expression tree, error;
			return 0;
		
		
		ifnode is leaf
		
			if node name is null
				return node value;
			
			else 
	
  			if variable name is not already found and value is not set
  					Invalid input, cannot evaluate, error;				
  					return 0;
  				
  				return return value of the variable
  			
		int var1=0;
		int var2=0;
		
		if left child is not null
			var1=TraverseExpTree(left child);

		if node.operator is not null && node.operator equals to "let"
			store value of varibale in hashMap with key as variable name and value as vaibale value;
		
		if node.right is not null
			var2=TraverseExpTree(right child);

		if node operator is not let
				var2= BasicArithmatic(var1, var2, operator);

		return var2 as result

----------------------------------------------------------------------------------------		
Algorithm for basic arithmatic operation is very simple and straigt forward


		BasicArithmatic(var1, var2, operator) : return result
  
    if operator is add
      return var1 + var 2 , if the resut is out of range of int type then, error, stop
      
    if operator is sub
      return var1 - var2,  if the resut is out of range of int type then, error, stop
      
    if opeartor if div
      if var2 is 0, cannot divide by 0, error, stop
      return var1 / var2,  if the resut is out of range of int type then, error, stop
      
    if operator is mul
      return var1 * var2,  if the resut is out of range of int type then, error, stop
    
  
#INSTALLATION GUIDE FOR WINDOWS


STEP 1. DOWNLOAD THE SOURCE CODE

Method 1 1. Clone the project to your local Git repository

Method 2 1. Download the compressed project file from github 2. Extract the file in local project folder



STEP 2: CREATE MAVEN PROJECT

Open eclipse

Click on "File" in the top menu bar > Click on "Import"

Go to "Maven", and expand > under Maven click on "Existing Maven Project" > Click "Next"

Click on "Browse" > Select the download source code directory as "Root Directory".

It will detect the pom.xml > Click "Finish".

This will load the project in project explorer. > Expand the project

Right click on App class -> go to run -> run configuartion -> provide commandline arguments -> click run



STEP 3: RUN THE TEST AS TESTNG SUITE

Right Click on testng.xml or testingCalculator.xml

Go to Run > Click on TestNG Suite.

Monitor the Suite and check for number of test case passed, failed and skipped.

Ideally all the test case should pass.

 
