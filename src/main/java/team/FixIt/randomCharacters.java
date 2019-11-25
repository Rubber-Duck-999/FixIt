package team.FixIt;

import java.util.Random;

public class randomCharacters
{
    private static final String ALPHANUMERIC_CHARACTERS = "0123456789abcdefghijklmnopq"
    		                                           + "rstuvwxyz!£$%^&*()_-+= {}{}"
    		                                           + ":@#~<>,.?/¬`|";

    private static void main(String[] args)
    {
        System.out.println("Random character:"+ getRandomPassword(16));
    }

    private static String getRandomPassword(int lengthPassword)
    {
    	String password = "";
    	for(int loop = 0; loop < lengthPassword; loop++)
    	{
    		Random r = new Random();
    	    int offset = r.nextInt(ALPHANUMERIC_CHARACTERS.length());
    	    //System.out.println(offset);
            password += ALPHANUMERIC_CHARACTERS.substring(offset, offset+1);
            //System.out.println(password);
    	}
    	return password;
    }

	public static String createRandomPassword(int length)
	{
		String password = getRandomPassword(length);
		return password;
	}
}
