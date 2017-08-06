/**
*   @Objetivo           : Bean de encapsulamiento de datos XML  
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : SqlMapAcciSit.java
*   @Fecha Creacion     : 7 abril 2017
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.jdbc.xml;

import java.io.Serializable;
import java.util.Vector;

public class SqlMapAcciSit implements Serializable {
	
	private static final long serialVersionUID = 635688189140720238L;
	private Vector<ResultMapCTO> vCtoResultMAP;
	private Vector<ProcedureCTO> vCtoProcedure;
	private String strNameSpace;

	public SqlMapAcciSit() {
		
	}
	
	public Vector<ResultMapCTO> getCtoResultMAP() {
		return vCtoResultMAP;
	}
	public void setCtoResultMAP(Vector<ResultMapCTO> ctoResultMAP) {
		this.vCtoResultMAP = ctoResultMAP;
	}
	public Vector<ProcedureCTO> getCtoProcedure() {
		return vCtoProcedure;
	}
	public void setCtoProcedure(Vector<ProcedureCTO> ctoProcedure) {
		this.vCtoProcedure = ctoProcedure;
	}
	public String getStrNameSpace() {
		return strNameSpace;
	}
	public void setStrNameSpace(String strNameSpace) {
		this.strNameSpace = strNameSpace;
	}


	@Override
	public String toString() {
		return "ctoResultMAP=" + vCtoResultMAP.toString() + ", ctoProcedure=" + vCtoProcedure.toString()
				+ ", strNameSpace=" + strNameSpace + ", toString()=" + super.toString() + "]\n";
	}
	
	

}

