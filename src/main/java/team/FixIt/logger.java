package team.FixIt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class logger 
{
	private boolean appendMode = true;
	  /**
     * Use BufferedWriter when number of write operations are more
     * It uses internal buffer to reduce real IO operations and saves time
     * @param data
     * @param noOfLines
	 * @throws IOException 
     */
    private static void writeUsingBufferedWriter(String data, int numOfLines, boolean appendMode)
    {
		FileWriter fW = null;
		BufferedWriter bW = null;
    	try
    	{
    		String time = common.getDateAndTime();
            File file = new File(common.LOGS + "/Log" + time + ".txt");
            if (!file.exists()) 
            {
        	   file.createNewFile();
   	        }
            String dataWithNewLine = data + System.getProperty("line.separator");
            fW = new FileWriter(file, appendMode);
            bW = new BufferedWriter(fW);
            for(int i = numOfLines; i > 0; i--)
            {
                bW.write(dataWithNewLine);
            }
            bW.write(data);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("Logging has failures in writing to the file");
        }
        finally
        {
        	try 
        	{
                bW.close();
                fW.close();
            } 
        	catch (IOException e) 
        	{
                e.printStackTrace();
            }
        }
    }
    
    public void logMessageAdd(String data, int numOfLines)
    {
    	writeUsingBufferedWriter(data, numOfLines, appendMode);
    }
}
