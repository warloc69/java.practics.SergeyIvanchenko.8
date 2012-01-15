package practic;
import java.sql.*;
import oracle.jdbc.*;
import java.io.*;
import java.util.*;
import exception.*;
public class DBWizard implements DBWizardInterface {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DBConnector.class);
	private Connection con = null;
	public DBWizard(Connection con) {
		this.con = con;
	}
	/**
	* add data to Data Base.
	* @throws ConnectException if we can't connect to Data Base. 
	*/
	public void add()  throws ConnectException {
		PreparedStatement pstadd = null;
		try {
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(System.in, "Cp866"));  
			String st = "INSERT INTO emp VALUES (?,?,?,?,?,?,?,?)";
			if (pstadd == null) {
				pstadd = con.prepareStatement(st);
			}
			log.info("Write emploer number: ");
			Integer v1 = Integer.parseInt(br.readLine());
			if (v1 >= 0) {
				pstadd.setInt(1,v1);
			} else {
				throw new NumberFormatException();
			}
			log.info("Write emploer name: ");
			pstadd.setString(2,br.readLine().toUpperCase());
			log.info("Write emploer job: ");
			pstadd.setString(3,br.readLine().toUpperCase());
			log.info("Write emploer mannager number: ");
			Integer v2 = Integer.parseInt(br.readLine());
			if (v2 >= 0) {
				pstadd.setInt(4,v2);
			} else {
				throw new NumberFormatException();
			}
			pstadd.setDate(5,new java.sql.Date(new java.util.Date().getTime()));
			log.info("Write emploer sales: ");
			Float v3 = Float.parseFloat(br.readLine());
			if (v3 >= 0) {
				pstadd.setFloat(6,v3);
			} else {
				throw new NumberFormatException();
			}
			log.info("Write emploer comission: ");
			Float v4 = Float.parseFloat(br.readLine());
			if (v4 >= 0) {
				pstadd.setFloat(7,v4);
			} else {
				throw new NumberFormatException();
			}
			log.info("Write distric: ");
			Integer v5 = Integer.parseInt(br.readLine());
			if (v5 >= 0) {
				pstadd.setInt(8,v5);
			} else {
				throw new NumberFormatException();
			}
			pstadd.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectException("Data base error"+ e.getMessage(),e); 
		} catch (IOException e1) {
			log.error(e1);
			throw new ConnectException("IO error"+ e1.getMessage(),e1);
		} catch (NumberFormatException e2) {
			log.warn("incorrect data wrote");
		} finally  {
			try {
				if (pstadd != null) {
					pstadd.close();
				}
			} catch (SQLException e) {
				log.error(e);
				throw new ConnectException("Data base error"+ e.getMessage(),e); 
			}
		}
	}
	/**
	* remove data from Data Base.
	* @throws ConnectException if we can't connect to Data Base. 
	*/
	public void remove() throws ConnectException{		
		PreparedStatement pstdel = null;
		try {
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(System.in, "Cp866"));  
			log.info("for removing write emp number: ");
			String empno = br.readLine();
			String st = "delete from emp where empno=?";
			if(pstdel == null) {
				pstdel = con.prepareStatement(st);
			}
			Integer v1 = Integer.parseInt(br.readLine());
			if (v1 >= 0) {
				pstdel.setInt(1,v1);
			} else {
				throw new NumberFormatException();
			}
			pstdel.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			throw new ConnectException("Data base error"+ e.getMessage(),e); 
		} catch (IOException e1) {
			log.error(e1);
			throw new ConnectException("IO error"+ e1.getMessage(),e1);
		} catch (NumberFormatException e2) {
			log.warn("incorrect data wrote");
		} finally  {
			try {
				if (pstdel != null) {
					pstdel.close();
				}
			} catch (SQLException e) {
				log.error(e);
				throw new ConnectException("Data base error"+ e.getMessage(),e); 
			}
		}
	}
	/**
	* show data from Data Base.
	* @throws ConnectException if we can't connect to Data Base. 
	*/
	public void view() throws ConnectException {
		PreparedStatement pstview = null;
		try {
			BufferedReader br = new BufferedReader(
						new InputStreamReader(System.in, "Cp866"));  
						log.info("for view write emp number: ");
			StringBuilder sb = new StringBuilder();
			sb.append("select emp.empno,emp.ename,emp.job,emp.mgr,emp.hiredate,emp.sal,");
			sb.append("emp.comm,emp.deptno,dept.dname,dept.loc,salgrade.grade from emp, dept,");
			sb.append("salgrade where emp.deptno=dept.deptno and sal between salgrade.minsal and salgrade.hisal and empno=?");
			if (pstview == null) {
				pstview = con.prepareStatement(sb.toString());
			}
			Integer v1 = Integer.parseInt(br.readLine());
			if (v1 >= 0) {
				pstview.setInt(1,v1);
			} else {
				throw new NumberFormatException();
			}
			ResultSet rset = pstview.executeQuery();
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
			throw new ConnectException("IO error"+ e1.getMessage(),e1);
		} catch (NumberFormatException e2) {
			log.warn("incorrect data wrote");
		} finally  {
			try {
				if (pstview != null) {
					pstview.close();
				}
			} catch (SQLException e) {
				log.error(e);
				throw new ConnectException("Data base error"+ e.getMessage(),e); 
			}
		}
	}
}