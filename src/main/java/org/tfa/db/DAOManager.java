package org.tfa.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.tfa.dto.InputDTO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * http://stackoverflow.com/questions/12812256/how-do-i-implement-a-dao-manager-using-jdbc-and-connection-pools
 *
 * To install ojdbc6.jar
 * 		mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=ojdbc6.jar -DgeneratePom=true
 */
public class DAOManager {

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }  
    
    public static String createInCondition(String column, List<InputDTO> inputList, boolean isChar){
    	
    	StringBuilder sql = new StringBuilder();
    	sql.append(column);
    	sql.append(" in (");
    	
    	boolean hasSelected = false;
    	for(InputDTO input : inputList){
    		if(input.getSelected()){
    			if(hasSelected){
        			sql.append(", ");
    			}
    			if(isChar){
    				sql.append(String.format("'%s'", input.getValue()));
    			}
    			else{
    				sql.append(input.getValue());	
    			}
    			hasSelected = true;
    		}
    	}
    	if(!hasSelected){
    		sql.append("null");
    	}
    	
    	sql.append(")");
    	return sql.toString();
    }
    
    public ResultSet executeQuery(String query){
    	
    	System.out.println(query);
    	
    	Statement createStatement;
    	ResultSet rs = null;
		try {
			createStatement = this.con.createStatement();
			rs = createStatement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
    }

    public void open() throws SQLException {
        try
        {
            if(this.con == null)
                this.con = src.getConnection();
        }
        catch(SQLException e) { throw e; }
    }

    public void close() throws SQLException {
        try
        {
            if(this.con != null)
                this.con.close();
        }
        catch(SQLException e) { throw e; }
    }

    //Private
    private DataSource src;
    private Connection con;

    private DAOManager() throws Exception {
        try
        {
        	HikariConfig config = new HikariConfig("/opt/projectConf/spark.qa.properties");
        	this.src = new HikariDataSource(config);
        	this.open();
        }
        catch(Exception e) { throw e; }
    }

    private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;
        static
        {
            ThreadLocal<DAOManager> dm;
            try
            {
                dm = new ThreadLocal<DAOManager>(){
                    @Override
                    protected DAOManager initialValue() {
                        try
                        {
                            return new DAOManager();
                        }
                        catch(Exception e)
                        {
                            return null;
                        }
                    }
                };
            }
            catch(Exception e){
                dm = null;
            }
            INSTANCE = dm;
        }        

    }

}