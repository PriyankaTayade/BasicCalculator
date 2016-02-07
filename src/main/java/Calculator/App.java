package Calculator;


import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

/**
 * This is a Calculator application
 *@author priyanka
 *@version 1.0
 */
public class App 
{
	
	private static final Logger logger = LogManager.getLogger(App.class);
	
	/**
	 * This method sets the log level log log4j
	 * @param level takes level as parameter
	 */
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
	/**
	 * This is the main method to run the calculator application
	 * @param args takes 2 arguments , 1 as input expression and 2nd argument is optional to set log level
	 */
    public static void main( String[] args )
    {
    	try{
			logger.info("Started the Calulator Application");
			 //PropertyConfigurator.configure("C:/Users/priyanka/Documents/Algorithms/BasicCalculator/src/log4j.properties");
			
			if(args.length<0){
				
				logger.error("Insufficent Arguments");
				logger.info("Aborting");
			}
			else{
				
				if(args.length>1){
					setLogger(args[1]);
					logger.trace("Setting logging level to "+args[1]);
				}
		
		
				 ExpressionTree exp=new ExpressionTree(args[0]);
				 logger.info("Generated Expression tree");
			
				 int result=exp.getExpressionResult();
				 System.out.println("The result of the Expression is : "+result);
				 logger.info("Expression tree result for string "+args[0]+" is : "+result);
			}
    	}
    	catch (Exception e){
    		logger.error(e);
    	}
		 logger.info("Exiting Calulator Application");
		}
    	
}
