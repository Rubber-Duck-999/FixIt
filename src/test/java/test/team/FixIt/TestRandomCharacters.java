package test.team.FixIt;

import static org.junit.Assert.*;

import org.junit.Test;

import team.FixIt.*;

public class TestRandomCharacters
{

	@Test
	public void testgetRandomCharacters()
	{
	      String value = randomCharacters.createRandomPassword(10);
	      assertEquals(10, value.length(), 0.0);
	}

}
