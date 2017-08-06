/**
*   @Objetivo           : Objeto RecordSet/Reflexion para implementacion JDBC
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : AcciJDBC.java
*   @Fecha de Creaci�n  : 7 abril 2017
*   @Versi�n            : 1.0.0
*/

package mx.com.amuhlia.jdbc;

import mx.com.amuhlia.exceptions.DataAccessException;
import mx.com.amuhlia.jdbc.xml.*;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class AcciRS {
	
	AcciJDBC conJDBC;
	Logger log= Logger.getLogger("AcciRS");
	
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		
		AcciRS RStest = new AcciRS();
		ResultSet trs;
		
		try {
			trs = RStest.result("select max(table_catalog) as x from information_schema.tables");
			
		} catch (DataAccessException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	public AcciRS() {
		try {
			
			xmlLeeEspecial xmlEsp = new xmlLeeEspecial();
			conJDBC = AcciJDBC.getInstance();
		} catch (Throwable e) {
			log.fatal("Error:", e);
		} 
	}
	
	public ResultSet result(String strQuery) throws DataAccessException{
		
		ResultSet rs = null;
		Statement stmt =null;
		
		try{
		
		stmt = conJDBC.getConnection().createStatement();

		log.debug("Query " + strQuery);
		
		if ( stmt.execute(strQuery) ){
			rs = stmt.getResultSet();
		} else {
			stmt.close();
		}
		
		if (rs != null) {
		log.debug("Respuesta de BD OK");
		} else {
		log.debug("No Encotre Datos ;(");
		}
	
		} catch (Exception e) {
			log.fatal("stmt.SQLException()", e);
			throw new DataAccessException("Metodo result ", e);
		} 
		return rs;
	}
	
	public boolean resultInsert(String strQuery) throws DataAccessException{
		
		Statement stmt = null;
		boolean blOK = false;
		
		try{
		
		stmt = conJDBC.getConnection().createStatement();

		log.debug("Query " + strQuery);
		blOK = stmt.execute(strQuery);
		stmt.close();

		} catch (Exception e) {
			throw new DataAccessException("Metodo resultInsert ", e);
		}
		return blOK;

	}
	
	public HashMap<String,String> resultUpdate(String strQuery) throws DataAccessException{
		
		CallableStatement cstmt = null;
		Connection conn = null ;
		ResultSet rsUP = null;

		HashMap<String,String> hResultado = new HashMap<String,String> ();
		
		try{
		
			conn = conJDBC.getConnection();
			conn.setAutoCommit(false);
			cstmt = conn.prepareCall(strQuery);

			log.debug("Query " + strQuery);
			rsUP = cstmt.executeQuery();
			rsUP.next();
			hResultado.put("resultado", String.valueOf(rsUP.getObject("resultado")));
			log.debug("resultado:" + hResultado.toString());
			cstmt.getConnection().commit();
			cstmt.close();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			try {
				log.fatal("Metodo resultUpdate pre-rollback ", e);
				cstmt.getConnection().rollback();
			} catch (SQLException e1) {
				log.fatal("Metodo resultUpdate rollback ", e1);
				hResultado.put("resultado", "-7");
				throw new DataAccessException("Metodo resultUpdate rollback ", e1);
			}
			throw new DataAccessException("Metodo resultUpdate ", e);
		}
		return (hResultado);

	
	}	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryForList(String strIDProcedure, HashMap<String, String> hmParametros ) throws DataAccessException{
		
		HashMap <String,Object> hProcMapRelac = new HashMap <String,Object>();
		List lList = new ArrayList();
		
		//busca las deficiones de los XMLs.
		hProcMapRelac = buscaProcedureYMap(strIDProcedure, hmParametros);
		ResultSet lRs = result((String) hProcMapRelac.get("strQuery")); //query
			
		if (lRs==null && hProcMapRelac.get("RClass")==null) {
			hmParametros.put("Resultado", "-1");
			lList.add(hmParametros);
			log.debug("fake-hmParametros" + lList.toString());
			
		} else if (lRs==null && hProcMapRelac.get("RClass")!=null) {
			//TODO y que pasa cuando no regresa nada el RS?
			try {
				Class<?> clase = Class.forName((String) hProcMapRelac.get("RClass"));
				lList.add(clase.newInstance());
			} catch (ClassNotFoundException e) {
				throw new DataAccessException(":QueryForList ClassError:", e);
			} catch (InstantiationException e) {
				throw new DataAccessException(":QueryForList InitClassError:", e);
			} catch (IllegalAccessException e) {
				throw new DataAccessException(":QueryForList IlegalAccessClassError:", e);
			}
			
			
		} else if (hProcMapRelac.get("RClass")==null) {
			
				
				HashMap<String,Object> lhMap = new HashMap<String,Object>();
				ResultSetMetaData lrSMeta;
				
				try {
					
					lrSMeta = lRs.getMetaData();
					int iCount = lrSMeta.getColumnCount(); 
					log.debug("hashColumnCount: "+ iCount);
					
					while (lRs.next()) {
						
						for (int x=1; x<=iCount; x++){
							lhMap.put(lrSMeta.getColumnName(x),lRs.getObject(x));
							log.debug("lHap:" + lhMap.toString());
						}
						
						lList.add(lhMap);
						lhMap = new  HashMap<String,Object>();
					}
					
					log.debug("hlList:" + lList.toString());
					
				} catch (SQLException e) {
					log.debug("(error)lhMap:" + lhMap.toString());
					throw new DataAccessException(":hQueryForList SQLError:", e);
				} catch (Exception e) {
					log.debug("(error)lhMap:" + lhMap.toString());
					throw new DataAccessException(":hQueryForList Exception:", e);
				}
			
			
		} else {
		
				try {
				
					Class<?> clase = Class.forName((String) hProcMapRelac.get("RClass"));
					Object obj;
					Method metod;
					Object oValor;
					Type tTipo = Integer.class;
					
					Class[] paramStr = new Class[1];
					paramStr[0] = String.class;
					Class[] paramInt = new Class[1];
					paramInt[0] = Integer.TYPE;
					Class[] paramIntCls = new Class[1];
					paramIntCls[0] = Integer.class;
					Class[] paramLong = new Class[1];
					paramLong[0] = Long.TYPE;					
					
					Vector<ResultCTO> ctoResult = (Vector<ResultCTO>) hProcMapRelac.get("ResultCTO");
										
					log.debug("ColumnCount: "+lRs.getMetaData().getColumnCount());
					
					 while (lRs.next()) {
						 obj = clase.newInstance();
						
						for (ResultCTO item : ctoResult){
							String strTMP = new String();
							oValor = lRs.getObject(item.getStrColumn());
							log.debug("Columna: "+ item.getStrColumn() + "  RS Valor:"+oValor  );
							
							//TODO Magia del reflexion
							for (Method m : clase.getDeclaredMethods())
							{
							   if(m.getName().toLowerCase().trim().equals(item.getStrProperty().toLowerCase().trim())){
								   tTipo = m.getGenericParameterTypes()[0];
								   strTMP = m.getName();
								   break;
							   }
							} //toma el nobre del Methodo real......
							
							log.debug("realPropName: " + strTMP + " StrProperty: " + item.getStrProperty() + " Typo: "+ tTipo.toString()); 
							
							if (tTipo.equals(String.class)){
								metod = clase.getDeclaredMethod(strTMP, paramStr);
								metod.invoke(obj, String.valueOf(oValor));
							} else if (tTipo.equals(Long.TYPE)) {
								metod = clase.getDeclaredMethod(strTMP, paramLong);
								metod.invoke(obj, Long.parseLong(String.valueOf(oValor)));
							} else if (tTipo.equals(Integer.class)) {
								metod = clase.getDeclaredMethod(strTMP, paramIntCls);
								metod.invoke(obj, new Integer(Integer.parseInt(String.valueOf(oValor))));
							} else {
								
								if(oValor.toString().trim().equals("true") || oValor.toString().trim().equals("false")){
									
									oValor = (Boolean.parseBoolean(oValor.toString())  ) ? 1 : 0;
								}
								
								metod = clase.getDeclaredMethod(strTMP, paramInt);
								metod.invoke(obj, Integer.parseInt(String.valueOf(oValor)));
							} 
			
						}
						
						lList.add(obj);
					}
					
						
					log.debug(lList.toString());
					
				} catch (SQLException e) {
					throw new DataAccessException(":QueryForList SQLError:", e);
				} catch (ClassNotFoundException e) {
					throw new DataAccessException(":QueryForList ClassError:", e);
				} catch (InstantiationException e) {
					throw new DataAccessException(":QueryForList InitClassError:", e);
				} catch (IllegalAccessException e) {
					throw new DataAccessException(":QueryForList IlegalAccessClassError:", e);
				} catch (NoSuchMethodException e) {
					throw new DataAccessException(":QueryForList NoMethodClassError:", e);
				} catch (SecurityException e) {
					throw new DataAccessException(":QueryForList SecurityException:", e);
				} catch (IllegalArgumentException e) {
					throw new DataAccessException(":QueryForList IllegalArgumentException:", e);
				} catch (InvocationTargetException e) {
					throw new DataAccessException(":QueryForList InvocationTargetException:", e);
				} catch (Exception e) {
					throw new DataAccessException(":QueryForList ExceptionGeneral:", e);
				}
		
		}
		
		 return lList;
		
	}
	
	public HashMap <String,Object> buscaProcedureYMap(String strProcedure, HashMap<String, String> hQParams) {
		
		HashMap <String,Object> hReturn = new HashMap <String,Object>();
		
		for (SqlMapAcciSit vitem : xmlLeeEspecial.vAll) {
			
			for (ProcedureCTO item : vitem.getCtoProcedure()) {
				
				if (item.getStrId().equals(strProcedure)) {
					
					hReturn.put("RMap", item.getStrResultMap());
					hReturn.put("strQuery", item.getCtoQuery(hQParams).getStrCommandExe());
					
					for (ResultMapCTO itemMap : vitem.getCtoResultMAP()) {
						if (itemMap.getStrId().equals(hReturn.get("RMap"))) {
							hReturn.put("RClass", itemMap.getStrClass());
							hReturn.put("ResultCTO", itemMap.gethResult());
							return hReturn;
						}
						
					}
					log.debug("Rutina-buscaProcedureYMap-: "+ hReturn.toString());
					return hReturn;
					
					} //if(procedure)
				} //for (procedure)
			} //for (all)
		
		return null;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HashMap> insert(String strIDProcedure, HashMap<String,String> hmParametros) {
		
		List<HashMap> lList = new ArrayList();
		HashMap <String,Object> hProcMapRelac = new HashMap <String,Object>();
		
		//busca las deficiones de los XMLs.
		hProcMapRelac = buscaProcedureYMap(strIDProcedure, hmParametros);
		
		try {
			boolean lok = resultInsert((String) hProcMapRelac.get("strQuery"));
			
			log.debug("resultInsert() : " + lok);
			
			lList.add( (HashMap) (new HashMap()).put("resultado", String.valueOf(lok)) );
			
			} catch (DataAccessException e) {
				log.fatal("insert() DataAccessException: ", e);
			}
		
		log.debug("insert() lList :" + String.valueOf(lList.toArray()));
		return lList;	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HashMap> update(String strIDProcedure, HashMap<String,String> hmParametros) {
		
		List<HashMap> lList = new ArrayList();
		HashMap <String,Object> hProcMapRelac = new HashMap <String,Object>();
		
		//busca las deficiones de los XMLs.
		hProcMapRelac = buscaProcedureYMap(strIDProcedure, hmParametros);
		
		try {
			lList.add(resultUpdate((String) hProcMapRelac.get("strQuery")));
			
			log.debug("resultUpdate() : " + String.valueOf(lList.toArray()));
	
			} catch (DataAccessException e) {
				log.fatal("update() DataAccessException: ", e);
			}
		
		return lList;	
	}
	
	
}


