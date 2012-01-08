package practic;
import java.sql.*;
import oracle.jdbc.*;
import java.io.*;
import java.util.*;
import exception.*;
/**
* class remove information from data base
*/
public class DBRemover {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DBRemover.class);
	private PreparedStatement pst = null;
	public DBRemover() throws ConnectException{
		try {
			Connection con = DBConnector.getConnection();
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(System.in, "Cp866"));  
			log.info("for removing write emp number: ");
			String empno = br.readLine();
			String st = "delete from emp where empno=?";
			pst = con.prepareStatement(st);
			pst.setInt(1,Integer.parseInt(empno));
			pst.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectException("Data base error",e); 
		} catch (IOException e1) {
			log.error(e1);
			throw new ConnectException("IO error",e1);
		} finally  {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				log.error(e);
				throw new ConnectException("Data base error",e); 
			}
		}
	}
}