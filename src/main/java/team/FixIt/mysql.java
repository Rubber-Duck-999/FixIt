package team.FixIt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class mysql 
{
	Connection con;
	Statement st;
	
	public mysql()
	{

	}
	
	public boolean StartConnection(String username, String password)
	{
		boolean databaseAccessed = false;
		try
		{  
			Connection con = DriverManager.getConnection(common.all, username, password);
			Statement st = con.createStatement();
			this.st = st;
			this.con = con;
			System.out.println("Starting Connection");
		}
		catch (SQLException ex)
		{
			System.out.println("SQL cannot be accessed, try again");
		}
		if(this.con != null && this.st != null)
		{
			databaseAccessed = true;
		}
		System.out.println("St is = " + this.st);
		System.out.println("Con is = " + this.con);
		return databaseAccessed;
	}
	
	public boolean CheckConnection()
	{
		boolean connectionEstablished = false;
		if(this.st != null)
		{
			connectionEstablished = true;
			System.out.println("This connection can be accessed");
		}
		else
		{
			connectionEstablished = false;
			System.out.println("This connection cannot be accessed");
		}
		return connectionEstablished;
	}
	
	private void CreateTable(String table) throws SQLException
	{
		if(CheckConnection())
		{
			String sql = "CREATE TABLE " + table +
	                     "(id int," + 
	                     " account varchar(30)," + 
	                     " username varchar(30)," + 
	                     " password varchar(30)," + 
	                     " primary key (id));"; 
	        this.st.executeUpdate(sql);
	        System.out.println("Created table in given database...");
		}
		else
		{
			System.out.println("Error - Can't create table, connection fail!");
		}	
	}
	
	private void DeleteTable(String table) throws SQLException
	{
		if(CheckConnection())
		{
			String sql = "drop table " + table;
		    this.st.executeUpdate(sql);
		    System.out.println("Table deleted in given database...");
		}
		else
		{
			System.out.println("Error - Can't delete table, connection fail!");
		}
	}
	
	private boolean checkIfIDExists(String table, int ID) throws SQLException
	{
		String strSelect = common.SELECT_VALUES + "from " + table + " where id = " + ID;
        System.out.println("The SQL query is: " + strSelect);  // Echo for debugging
        ResultSet rset = this.st.executeQuery(strSelect);
        int idLocal = 0;
        while(rset.next()) 
        {
        	String idString = rset.getString("id");
        	idLocal = Integer.parseInt(idString);
        }
        if(idLocal == ID)
        {
        	return true;
        }
        else
        {
        	return false;
        }
	}
	
	public boolean checkIfAccountExists(String table, String Account) throws SQLException
	{
		String AccountName = "'" + Account + "'";
		String strSelect = common.SELECT_VALUES + "from " + table + " where account = " + AccountName;
        System.out.println("The SQL query is: " + strSelect);  // Echo for debugging
        ResultSet rset = this.st.executeQuery(strSelect);
        String accountString = "";
        while(rset.next()) 
        {
        	accountString = rset.getString("account");
        	//System.out.println("Printing record Account name = " + accountString);
        }
        System.out.println(accountString);
		if(accountString.equals(Account))
        {
			System.out.println("True");
        	return true;
        }
        else
        {
        	return false;
        }
	}
	
	public String getUsername(String table, String Account) throws SQLException
	{
		String Input = "'" + Account + "'";
		String strSelect = common.SELECT_VALUES + "from " + table + " where account = " + Input;
        System.out.println("The SQL query is: " + strSelect);  // Echo for debugging
        ResultSet rset = this.st.executeQuery(strSelect);
        int idLocal = 0;
        String username = "";
        while(rset.next()) 
        {
        	username = rset.getString("username");
        }
		return username;
	}
	
	public String getPassword(String table, String Account) throws SQLException
	{
		String Input = "'" + Account + "'";
		String strSelect = common.SELECT_VALUES + "from " + table + " where account = " + Input;
        System.out.println("The SQL query is: " + strSelect);  // Echo for debugging
        ResultSet rset = this.st.executeQuery(strSelect);
        int idLocal = 0;
        String password = "";
        while(rset.next()) 
        {
        	password = rset.getString("password");
        }
		return password;
	}
	
	public void CreateNewLogin(String table, String account, String username, String password) throws SQLException
	{
		if(CheckConnection())
		{
			int ID = 0;
			boolean idTrue = true;
			int idLoop = 101;
			boolean exit = false;
			while(idLoop <= common.MAX_ID_NUMBER && exit == false)
			{
				idTrue = checkIfIDExists(table, idLoop);
				if(!idTrue)
				{
					ID = idLoop;
					exit = true;
				}
				idLoop++;
			}
			if(ID != 0)
			{
				String id = String.valueOf(ID);
				System.out.println("Available ID" + id);
				String sql = common.INSERT_VALUES + table + " values ('" + id + "','" + account + "','" + username + "','" + password + "');";
				System.out.println(sql);
		        this.st.executeUpdate(sql);
			}
		}
		else
		{
			System.out.println("Error - Can't make a new login, connection fail!");
		}
	}
	
	public boolean DeleteLogin(String table, String account) throws SQLException
	{
		boolean deleted = false;
		if(CheckConnection())
		{
			String Input = "'" + account + "'";
		    String sql = common.REMOVE_VALUES + table + " where account = " + Input;
		    System.out.println(sql);
		    this.st.executeUpdate(sql);
		    if(!checkIfAccountExists(table, account))
		    {
		    	deleted = true;
		    };
		}
		else
		{
			System.out.println("Error - Can't delete login, connection fail!");
		}
		return deleted;
	}
	
	
	public LinkedList<String> PrintDetails(String table) throws SQLException
	{
		List<String> TypeList = new LinkedList<String>();
		if(CheckConnection())
		{
			String strSelect = common.SELECT_VALUES + "from " + table;
	        System.out.println("The SQL query is: " + strSelect);  // Echo for debugging
	        ResultSet rset = this.st.executeQuery(strSelect);
	        while(rset.next()) 
	        {
	        	String typeString = rset.getString("account");
	            TypeList.add(typeString);
	        }
		}
		return (LinkedList<String>) TypeList;
	}
	public LinkedList<String> PrintDetailsOne(String table, String Type, String TypeName) throws SQLException
	{
		List<String> TypeList = new LinkedList<String>();
		if(CheckConnection())
		{
			String strSelect = common.SELECT_VALUES + "from " + table + " where " + Type + " = " + TypeName;
	        System.out.println("The SQL query is: " + strSelect);  // Echo for debugging
	        ResultSet rset = this.st.executeQuery(strSelect);
	        while(rset.next()) 
	        {
	        	String typeString = rset.getString(Type);
	            TypeList.add(typeString);
	        }
		}
		return (LinkedList<String>) TypeList;
	}
	
	public void UpdateUsername(String table, String account, String username) throws SQLException
	{
		if(CheckConnection())
		{
			PrintDetailsOne(table, account, username);
			String strUpdate = common.UPDATE_VALUES + table + " set username = " 
	                         + username + " where account = " + account;
	        System.out.println("The SQL query is: " + strUpdate);  // Echo for debugging
	        this.st.executeUpdate(strUpdate);
		}
		else
		{
			System.out.println("Error - Can't update account username, connection fail!");
		}
	}
	
	public void UpdatePassword(String table, String account, String password) throws SQLException
	{
		if(CheckConnection())
		{
			PrintDetailsOne(table, account, password);
			String strUpdate = common.UPDATE_VALUES + table + " set username = " 
	                         + password + " where account = " + account;
	        System.out.println("The SQL query is: " + strUpdate);  // Echo for debugging
	        this.st.executeUpdate(strUpdate);
		}
		else
		{
			System.out.println("Error - Can't update account password, connection fail!");
		}
	}
	
}
