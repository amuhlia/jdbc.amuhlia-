/**
*   @Objetivo           : Bean de encapsulamiento de datos XML  
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : ProcedureCTO.java
*   @Fecha Creacion     : 7 abril 2017
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.jdbc.xml;

import mx.com.amuhlia.jdbc.xml.helper.QueryHelper;

import java.io.Serializable;
import java.util.HashMap;

public class ProcedureCTO implements Serializable {
	
	private static final long serialVersionUID = -2067294740646896845L;
	private String strId;
	private String strParameterClass;
	private String strResultMap;
	private String strTextProcedure;
	private QueryHelper helpQuery = new QueryHelper();

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		this.strId = strId;
	}

	public String getStrParameterClass() {
		return strParameterClass;
	}

	public void setStrParameterClass(String strParameterClass) {
		this.strParameterClass = strParameterClass;
	}

	public String getStrResultMap() {
		return strResultMap;
	}

	public void setStrResultMap(String strResultMap) {
		this.strResultMap = strResultMap;
	}

	public String getStrTextProcedure() {
		return strTextProcedure;
	}

	public void setStrTextProcedure(String strTextProcedure) {
		this.strTextProcedure = strTextProcedure;
	}


	public ProcedureCTO() {
		// TODO Auto-generated constructor stub
	}


	public QueryHelper getCtoQuery(HashMap<String, String> hQParams) {
		helpQuery = new QueryHelper();
		helpQuery.setStrProcTxt(getStrTextProcedure());
		helpQuery.sethParametros(hQParams);
	
		return helpQuery;
	}
	
	public QueryHelper getCtoQuery() {
		return helpQuery;
	}	

	public void setCtoQuery(QueryHelper ctoQuery) {
		this.helpQuery = ctoQuery;
	}
	
	@Override
	public String toString() {
		return "ProcedureCTO [strId=" + strId + ", strParameterClass=" + strParameterClass + ", strResultMap="
				+ strResultMap + ", strTextProcedure=" + strTextProcedure + ", toString()=" + super.toString() + "]\n";
	}


}
