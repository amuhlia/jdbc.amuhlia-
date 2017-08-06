/**
*   @Objetivo           : Objeto de Lectura y encapsulamiento del QUERY 
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : QueryHelper.java
*   @Fecha Creacion     : 7 abril 2017
*   @Versi�n            : 1.0.0
*/

package mx.com.amuhlia.jdbc.xml.helper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;


public class QueryHelper implements Serializable {
	
	private static final long serialVersionUID = 7084551988873444815L;
	private String strProcTxt = new String();
	private String strCommandExe = new String();
	private HashMap<String,String> hParametros = new HashMap<String, String>();
	private Vector<String> viParams = new Vector<String>();
	private Logger log= Logger.getLogger("QueryHelper");
	
	public String getStrProcTxt() {
		return strProcTxt;
	}

	public void setStrProcTxt(String strProcTxt) {
		this.strProcTxt = strProcTxt;
		
		log.debug("strProcTxt ("+ strProcTxt +")");
		
		int x=1;
		String strTMP = new String();
		StringTokenizer stkParams = new StringTokenizer(strProcTxt,"#");
		
		if (stkParams.hasMoreTokens()) { setStrCommandExe(stkParams.nextToken()); }
		
	     while (stkParams.hasMoreTokens()) {
	    	 x++;
	    	 strTMP = stkParams.nextToken().trim();
	    	 if (x % 2 == 0 &&  !strTMP.isEmpty()) viParams.add(strTMP);
	    	 strTMP = "";
	     }
	    
	    
		log.debug("setStrProcTxt (strCommandExe:"+ strCommandExe + ") viParams:" + viParams.toString() );
	}

	public String getStrCommandExe() {
		
		String strValor = new String();
		int iCont = 0;
		
		for (String item : viParams) {
			
			strValor = hParametros.get(item).trim();
			
			if (StringUtils.isNumeric(strValor) && !strValor.isEmpty() ){
				strCommandExe = strCommandExe + " " + strValor + " ,";
			}else {
				strCommandExe = strCommandExe + " '" + strValor + "',";
			}
			
			iCont=1;
			
			log.debug(" (iterator) viParams: " + item + " strValor:" + strValor);
		}
		
		return strCommandExe.substring(0, strCommandExe.length()-iCont);
	}

	private void setStrCommandExe(String strCommandExe) {
		this.strCommandExe = strCommandExe;
	}

	public HashMap<String, String> gethParametros() {
		return hParametros;
	}

	public void sethParametros(HashMap<String, String> hParametros) {
		this.hParametros = hParametros;
	}


	public QueryHelper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "QueryCTO [strProcTxt=" + strProcTxt + ", strCommandExe=" + strCommandExe + ", hParametros="
				+ hParametros + ", viParams=" + viParams + ", toString()=" + super.toString() + "]";
	}
	
	

}
