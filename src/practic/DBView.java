package practic;
import java.sql.*;
import oracle.jdbc.*;
import java.io.*;
import java.util.*;
import exception.*;
/**
* class load information from data base.
*/
public class DBView {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DBView.class);
	private PreparedStatement pst = null;
	public DBView() throws ConnectException {
	    try {
			Connection con = DBConnector.getConnection();
			BufferedReader br = new BufferedReader(
						new InputStreamReader(System.in, "Cp866"));  
						log.info("for view write emp number: ");
					String empno = br.readLine();
			StringBuilder sb = new StringBuilder();
			sb.append("select emp.empno,emp.ename,emp.job,emp.mgr,emp.hiredate,emp.sal,");
			sb.append("emp.comm,emp.deptno,dept.dname,dept.loc,salgrade.grade from emp, dept,");
			sb.append("salgrade where emp.deptno=dept.deptno and sal between salgrade.minsal and salgrade.hisal and empno=?");
			pst = con.prepareStatement(sb.toString());
			pst.setInt(1,Integer.parseInt(empno));
			ResultSet rset = pst.executeQuery();
			boolean res = false;
			while (rset.next()) {
				StringBuilder result = new StringBuilder();
				result.append(rset.getString(1));
				result.append(" ");
				result.append(rset.getString(2));
				result.append(" ");
				result.append(rset.getString(3));
				result.append(" ");
				result.append(rset.getString(4));
				result.append(" ");
				result.append(rset.getString(5));
				result.append(" ");
				result.append(rset.getString(6));
				result.append(" ");
				result.append(rset.getString(7));
				result.append(" ");
				result.append(rset.getString(8));
				result.append(" ");
				result.append(rset.getString(9));
				result.append(" ");
				result.append(rset.getString(10));
				result.append(" ");
				result.append(rset.getString(11));
				System.out.println(result.toString());
				res = true;
		    }
			if(!res) { 
				log.info("data no found");
			}			
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