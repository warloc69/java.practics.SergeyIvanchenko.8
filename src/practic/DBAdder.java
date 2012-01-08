package practic;
import java.sql.*;
import oracle.jdbc.*;
import java.io.*;
import java.util.*;
import exception.*;
/**
* class adds information to data base.
*/
public class DBAdder {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DBAdder.class);
	private PreparedStatement pst = null;
	public DBAdder()  throws ConnectException {
		try {
			Connection con = DBConnector.getConnection();
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(System.in, "Cp866"));  
			String st = "INSERT INTO emp VALUES (?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(st);
			log.info("Write emploer number: ");
			Integer v1 = Integer.parseInt(br.readLine());
			if (v1 >= 0) {
				pst.setInt(1,v1);
			} else {
				throw new NumberFormatException();
			}
			log.info("Write emploer name: ");
			pst.setString(2,br.readLine().toUpperCase());
			log.info("Write emploer job: ");
			pst.setString(3,br.readLine().toUpperCase());
			log.info("Write emploer mannager number: ");
			Integer v2 = Integer.parseInt(br.readLine());
			if (v2 >= 0) {
				pst.setInt(4,v2);
			} else {
				throw new NumberFormatException();
			}
			pst.setDate(5,new java.sql.Date(new java.util.Date().getTime()));
			log.info("Write emploer sales: ");
			Float v3 = Float.parseFloat(br.readLine());
			if (v3 >= 0) {
				pst.setFloat(6,v3);
			} else {
				throw new NumberFormatException();
			}
			log.info("Write emploer comission: ");
			Float v4 = Float.parseFloat(br.readLine());
			if (v4 >= 0) {
				pst.setFloat(7,v4);
			} else {
				throw new NumberFormatException();
			}
			log.info("Write distric: ");
			Integer v5 = Integer.parseInt(br.readLine());
			if (v5 >= 0) {
				pst.setInt(8,v5);
			} else {
				throw new NumberFormatException();
			}
			pst.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectException("Data base error",e); 
		} catch (IOException e1) {
			log.error(e1);
			throw new ConnectException("IO error",e1);
		} catch (NumberFormatException e2) {
			log.warn("incorrect data wrote");
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