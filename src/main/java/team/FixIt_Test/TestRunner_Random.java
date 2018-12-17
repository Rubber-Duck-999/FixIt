package team.FixIt_Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner_Random 
{
   public static void main(String[] args) 
   {
	  Result result = JUnitCore.runClasses(TestrandomCharacters.class);
		
      for (Failure failure : result.getFailures()) 
      {
         System.out.println(failure.toString());
      }
		
      System.out.println(result.wasSuccessful());
   }
} 