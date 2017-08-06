/**
*   @Objetivo           : Bean de encapsulamiento de datos XML  
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : ResultCTO.java
*   @Fecha Creacion     : 7 abril 2017
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.jdbc.xml;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class ResultCTO implements Serializable{
	
	private static final long serialVersionUID = 5806072227064444001L;
	private String strProperty;
	private String strColumn;
	
	public String getStrProperty() {
		return strProperty;
	}

	public void setStrProperty(String strProperty) {
		this.strProperty = "set" + StringUtils.capitalize(strProperty);
	}

	public String getStrColumn() {
		return strColumn;
	}

	public void setStrColumn(String strColumn) {
		this.strColumn = strColumn;
	}

	public ResultCTO() {
		
	}

	@Override
	public String toString() {
		return "ResultCTO [strProperty=" + strProperty + ", strColumn=" + strColumn + ", toString()=" + super.toString()
				+ "]\n";
	}
	
	

}
