package practic;
import java.sql.*;
import oracle.jdbc.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import exception.*;
/**
*    class creates connection with data base
*/
public class DBConnector {
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DBConnector.class);
	private static Connection conn = null;
	private DBConnector() {};
	public static Connection getConnection() throws ConnectException {
		if (conn == null) {
			try {
				DocumentBuilderFactory fact = 
					DocumentBuilderFactory.newInstance();
				DocumentBuilder build = 
					fact.newDocumentBuilder();
				File f  = new File("connect.xml");
				Document doc = build.parse(f);
				Element root = doc.getDocumentElement();
				String source_name = null;
				String connection_url = null;
				String driver_class = null;
				String password = null;
				String user_name = null;
				NodeList children = root.getChildNodes();
				for(int i = 0; i<children.getLength();i++) {
					Node child = children.item(i);
					if (child instanceof Element) {
						Element childElement = (Element) child;
						Text textNode = (Text)childElement.getFirstChild();
						if (childElement.getTagName().equals("source-name")) {
							source_name = textNode.getData().trim();
						} else if (childElement.getTagName().equals("connection-url")) {
							connection_url = textNode.getData().trim();
						} else if (childElement.getTagName().equals("driver-class")) {
							driver_class = textNode.getData().trim();
						} else if (childElement.getTagName().equals("password")) {
							password = textNode.getData().trim();
						} else if (childElement.getTagName().equals("user-name")) {
							user_name = textNode.getData().trim();
						}
							
					}
				}
				Class.forName(driver_class);
				log.info("Oracle JDBC driver loaded ok.");
				conn = DriverManager.getConnection(source_name+user_name +"/"+password+ "@" +connection_url);
				log.info("connect ok.");
			} catch (SQLException e) {
				log.error(e);
				throw new ConnectException("Data base error",e); 
			} catch (ParserConfigurationException e1) {
				log.error(e1);
				throw new ConnectException("XML error",e1);
			} catch (org.xml.sax.SAXException e2) {
				log.error(e2);
				throw new ConnectException("XML error",e2);
			} catch (ClassNotFoundException e3) {
				log.error(e3);
				throw new ConnectException("Data base class no found error",e3);
			} catch (IOException e4) {
				log.error(e4);
				throw new ConnectException("IO error",e4);
			}
		}
		return conn;
	}
}