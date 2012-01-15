package practic;
import java.sql.*;
import oracle.jdbc.*;
import java.io.*;
import java.util.*;
import exception.*;
public class SQLManager {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SQLManager.class);
    public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("log\\log4j.properties");
		try{
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(System.in, "Cp866"));  
				String com = "";
			DBWizardInterface db = new DBWizard(DBConnector.getConnection());
			while (!"exit".equals(com)) {
			log.info("write command (view, add, remove, exit):"); 
				com = br.readLine();
				if ("view".equals(com)) {
					db.view();
				} else if ("add".equals(com)) {
					db.add();
				} else if ("remove".equals(com)) {
					db.remove();
				}
			}
		} catch (ConnectException e) {
			log.error(e);
		} catch (IOException e1) {
			log.error(e1);
		}
    }
}