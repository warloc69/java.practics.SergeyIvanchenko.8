package practic;
import java.sql.*;
import oracle.jdbc.*;
import java.io.*;
import java.util.*;
import exception.*;
public interface DBWizardInterface {
	/**
	* add data to Data Base.
	* @throws ConnectException if we can't connect to Data Base. 
	*/
	public void add()  throws ConnectException;
	/**
	* remove data from Data Base.
	* @throws ConnectException if we can't connect to Data Base. 
	*/
	public void remove() throws ConnectException;
	/**
	* show data from Data Base.
	* @throws ConnectException if we can't connect to Data Base. 
	*/
	public void view() throws ConnectException;
}