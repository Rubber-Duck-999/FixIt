package team.FixIt_Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import team.FixIt.randomCharacters;

public class TestrandomCharacters 
{
	
	@Test
	public void testgetRandomCharacters() 
	{
	      String value = randomCharacters.createRandomPassword(10);
	      assertEquals(10, value.length(), 0.0);
	}

}
