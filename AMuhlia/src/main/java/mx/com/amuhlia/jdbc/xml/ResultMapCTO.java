/**
*   @Objetivo           : Bean de encapsulamiento de datos XML  
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : ResultMapCTO.java
*   @Fecha Creacion     : 7 abril 2017
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.jdbc.xml;

import java.io.Serializable;
import java.util.Vector;

public class ResultMapCTO implements Serializable {

	private static final long serialVersionUID = 5103242941139911987L;
	private String strId;
	private String strClass;
	private Vector<ResultCTO> vCtoResult = new Vector<ResultCTO>();
	
	public String getStrId() {
		return strId;
	}


	public void setStrId(String strId) {
		this.strId = strId;
	}


	public String getStrClass() {
		return strClass;
	}


	public void setStrClass(String strClass) {
		this.strClass = strClass;
	}


	public Vector<ResultCTO> gethResult() {
		return vCtoResult;
	}


	public void sethResult(Vector<ResultCTO> lctoResult) {
		this.vCtoResult = lctoResult;
	}



	public ResultMapCTO() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "ResultMapCTO [strId=" + strId + ", strClass=" + strClass  + ", ctoResult="
				+ vCtoResult.toString() + ", toString()=" 
				+ super.toString() + "]\n";
	}
	
	

}
