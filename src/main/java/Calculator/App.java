package Calculator;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;


//String input="add(2,5)";
//String input="add(1,mult(2,3))";
//String input="mult(add(2,2),div(9,3))";
//String input="mult(add(2,2),div(9,3))";
//String input="let(a,5,add(a,10))";
//String input="let(a,5,let(b,mult(a,10),add(b,a)))";

/**
 * Hello world!
 *
 */
public class App 
{

	private static final Logger logger = LogManager.getRootLogger();
	
	private static void setLogger(String level){
		
			if(level.equals("all"))
				logger.setLevel(Level.ALL);
			
			if(level.equals("info"))
				logger.setLevel(Level.INFO);
			
			if(level.equals("error"))
				logger.setLevel(Level.ERROR);
			
			if(level.equals("debug"))
				logger.setLevel(Level.DEBUG);
			
			if(level.equals("fatal"))
				logger.setLevel(Level.FATAL);
			
			if(level.equals("trace"))
				logger.setLevel(Level.TRACE);
			
			if(level.equals("warn"))
				logger.setLevel(Level.WARN);
			 					
	}
    public static void main( String[] args )
    {
		logger.trace("In the Main Function");
		 //PropertyConfigurator.configure("C:/Users/priyanka/Documents/Algorithms/BasicCalculator/src/log4j.properties");
		
		if(args.length<0){
			logger.error("Insufficent Arguments");
			logger.info("Aborting");
			return;
		}
		
		if(args.length>1){
			setLogger(args[1]);
			logger.trace("Setting logging level to "+args[1]);
		}


		 ExpressionTree exp=new ExpressionTree(args[0]);
		 logger.trace("Successfully Generated Expression tree");
	
		 System.out.println("Expression tree output is: "+exp.getExpressionResult());
		 
		 logger.trace("Exiting Main Function");
		}
}
