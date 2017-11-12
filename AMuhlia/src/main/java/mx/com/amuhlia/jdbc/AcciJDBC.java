/**
*   @Objetivo           : Objeto de Conexion Singleton para implementacion JDBC
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : AcciJDBC.java
*   @Fecha Creacion     : 7 abril 2017
*   @Versi�n            : 1.0.0
*/

package mx.com.amuhlia.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mx.com.amuhlia.exceptions.CannotGetJdbcConnectionException;
import org.apache.log4j.Logger;


public class AcciJDBC {
	
	    private static AcciJDBC myObj;
	    private DataSource ds;
	    Logger log = Logger.getLogger("AcciJDBC");

	    /**
	     * Create private constructor
	     * @throws CannotGetJdbcConnectionException 
	     * @throws IOException 
	     */
	    private AcciJDBC() throws CannotGetJdbcConnectionException, IOException{
	    	
			Properties prop = new Properties();
			Properties propLoad = new Properties();
			//InputStream input = new FileInputStream("config.properties");
			InputStream input =this.getClass().getResourceAsStream("/config.properties");
			propLoad.load(input);
			
			try{
			    log.debug("Properties" + propLoad.toString());
			    log.debug("getHostName() " + java.net.InetAddress.getLocalHost().getHostName() );
			    
				prop.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
				prop.put(Context.PROVIDER_URL, "t3://"+ java.net.InetAddress.getLocalHost().getHostName() +":"+propLoad.getProperty("PORT_NUMBER")+"");
				Context ctx = new InitialContext(prop);
				Object obj = ctx.lookup("PoolProdc1"); // java:comp/env/CPDS
				ds = (DataSource) obj;
				//conn = (Connection) ds.getConnection();
				log.debug("//Se ha establecido la JDBC.....//");
				//ctx.close();
			} catch  (Exception e) {
				log.fatal("AcciJDBC(1).getHostName() " + java.net.InetAddress.getLocalHost().getHostName());
				log.fatal("AcciJDBC(1) Properties" + propLoad.toString(),e);
				throw new CannotGetJdbcConnectionException();
			} finally {
				
				if (input != null) {
						input.close();
			    }
		    }  
	    }
	    /**
	     * Create a static method to get instance.
	     * @throws CannotGetJdbcConnectionException 
	     * @throws IOException 
	     */
	    public static AcciJDBC getInstance() throws CannotGetJdbcConnectionException, IOException{
	        if(myObj == null){
	            myObj = new AcciJDBC();
	        }
	        return myObj;
	    }
	    
	    public void reConnectOn() throws IOException, CannotGetJdbcConnectionException, SQLException{
	    	//TODO no esta probado
	    	//myObj.getConnection().close();
	    	log.debug(":Conn=Null:");
	    	myObj = new AcciJDBC();
	    	log.debug(":Conn=New:");
	    }
	     
	    
	    public DataSource getDataSource(){
	        return ds;
	    }

}
