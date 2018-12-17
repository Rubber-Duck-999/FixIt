package team.FixIt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;

public interface common 
{
	public static final String REMOVE_VALUES = "delete from ";
	
	public static final String INSERT_VALUES = "insert into ";
	
	public static final String SELECT_VALUES = "select * ";
	
	public static final String UPDATE_VALUES = "update ";
	
	public static final String URL = "jdbc:mysql://localhost:3306/";
			
	public static final String DATABASE = "logins";
	
	public static final String TABLE = "accounts";
	
	public static final String TIME_ZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&SSL=false";
	
	public static final String all = URL + DATABASE + TIME_ZONE;
	
	public static final Integer HEIGHT = 640;
	
	public static final Integer WIDTH  = 480;
	
	public static final Integer MAX_ID_NUMBER = 150;
	
	public static final String EXE_WIN = "C:/Users/simon/Documents/Projects/Java/FixIt";
	
	public static final String LOGS = EXE_WIN + "/Logs";
	
	public static final String EXE_LINUX = null;
	//Yet to be decided
	public static final int STRING_LENGTH = 14;
	
	public static String replaceLetter(String word, char letterToReplace, char replacingLetter)
	{
	    String replacedWord = word.replace(letterToReplace, replacingLetter);
	    return replacedWord;    
	}
	
	public static String getDateAndTime()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now)); //2016/11/16 12:08:43
		String time = dtf.format(now);
		return time;
	}
	
	public static boolean checkStringLength(String string)
	{
		int length = string.length();
		if(length > common.STRING_LENGTH && length > 1)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}

}
