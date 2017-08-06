/**
*   @Objetivo           : Objeto de Lectura y encapsulamiento de datos XML  
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : xmlLeeEspecial.java
*   @Fecha Creacion     : 7 abril 2017
*   @Versi�n            : 1.0.0
*/

package mx.com.amuhlia.jdbc.xml;


import mx.com.amuhlia.exceptions.CanNotGetXMLDefinition;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Vector;


public class xmlLeeEspecial implements Serializable{
	
	private static final long serialVersionUID = 5356920371581675326L;
	Logger log= Logger.getLogger("xmlLeeEspecial");
	static Logger staticlog= Logger.getLogger("(static)xmlLeeEspecial");
	static public Vector<SqlMapAcciSit> vAll ;	
	


	public static void main(String args[]) {
		
		SqlMapAcciSit allXmlInCTO = new SqlMapAcciSit();
			  
		
		  
		try {
			
			xmlLeeEspecial xmlLee = new xmlLeeEspecial();
			
			//allXmlInCTO = xmlLee.leeXML("MapExample.xml");
			allXmlInCTO = xmlLee.leeXML("MapExample.xml");

						
			for (ProcedureCTO item : allXmlInCTO.getCtoProcedure()) {
				staticlog.debug("ProcedureCTO (iterator): " + item);
			}
			
			for (ResultMapCTO item : allXmlInCTO.getCtoResultMAP()) {
				staticlog.debug("ResultMapCTO (iterator): " + item);
			}			
			
/*			for (Iterator<ProcedureCTO> i = allXmlIn.getCtoProcedure().iterator(); i.hasNext();) {
			    ProcedureCTO item = i.next();
			    staticlog.debug("ProcedureCTO (iterator): " + item);
			}*/
			
			
		} catch (CanNotGetXMLDefinition e) {
			e.printStackTrace();
		} catch (Throwable e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		 
		

		
	}
	
	public xmlLeeEspecial() throws Throwable {
		
		log.debug("inicia clase");

		if (vAll == null) {
			
			log.debug("inicia carga de XML en Memoria");
		
			vAll = new Vector<SqlMapAcciSit>();
			
			try {
				
				vAll.add(leeXML("MapExample.xml"));
/*
				vAll.add(leeXML("MapExample1.xml"));
				vAll.add(leeXML("MapExample2.xml"));
				vAll.add(leeXML("MapExample3.xml"));
*/

							
				for (SqlMapAcciSit vitem : vAll) {
					
					for (ResultMapCTO item : vitem.getCtoResultMAP()) {
						staticlog.debug("ResultMapCTO (iterator): " + item);
					}
					
					for (ProcedureCTO item : vitem.getCtoProcedure()) {
						staticlog.debug("ProcedureCTO (iterator): " + item);
					}
					
				}
				
			} catch (CanNotGetXMLDefinition e) {
				log.fatal("Error al Cargar XMLs: ", e);
				e.printStackTrace();
				throw new CanNotGetXMLDefinition("Error al Cargar XMLs: ",e);
			}
		} else {
			log.debug("XML en Memoria!");
		}
		
	}
	
	protected Node getNode(String tagName, NodeList nodes) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            return node;
	        }
	    }
	 
	    return null;
	}
	
	protected Vector<ResultCTO> getResult(NodeList nodes) {
		Vector<ResultCTO> vResult = new Vector<ResultCTO>();
		ResultCTO ctoResult = new ResultCTO();
		
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase("result")) {
	        	ctoResult.setStrProperty(getNodeAttr("property", node));
	        	ctoResult.setStrColumn(getNodeAttr("column", node));
	        	
		        vResult.add(ctoResult);
		        ctoResult = new ResultCTO();
	        }
	    }
	    return vResult;
	}	
	
	protected Vector<ResultMapCTO> getResultMap(NodeList nodes) {
		Vector<ResultMapCTO> vResultMap = new Vector<ResultMapCTO>();
		ResultMapCTO ctoResultMap = new ResultMapCTO();
		
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase("resultMap")) {
	        	ctoResultMap.setStrId(getNodeAttr("id", node));
	        	ctoResultMap.setStrClass(getNodeAttr("class", node));
	        	ctoResultMap.sethResult(getResult(node.getChildNodes()));
	        	
		        vResultMap.add(ctoResultMap);
		        ctoResultMap = new ResultMapCTO();
	        }
	    }
	 
	    return vResultMap;
	}	
	
	protected Vector<ProcedureCTO> getProcedure(NodeList nodes) {
		Vector<ProcedureCTO> vProc = new Vector<ProcedureCTO>();
		ProcedureCTO ctoProc = new ProcedureCTO();
		
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase("procedure")) {
	        	ctoProc.setStrTextProcedure(node.getFirstChild().getNodeValue().trim()); ;
	        	ctoProc.setStrId(getNodeAttr("id", node)); 
	        	ctoProc.setStrParameterClass(getNodeAttr("parameterClass", node));
	        	ctoProc.setStrResultMap(getNodeAttr("resultMap", node));
	        	
	        	vProc.add(ctoProc);
	        	ctoProc = new ProcedureCTO();
	        }
	    }
	    
	    return vProc;
	}	
	 
	protected String getNodeValue( Node node ) {
	    NodeList childNodes = node.getChildNodes();
	    for (int x = 0; x < childNodes.getLength(); x++ ) {
	        Node data = childNodes.item(x);
	        if ( data.getNodeType() == Node.TEXT_NODE )
	            return data.getNodeValue();
	    }
	    return "";
	}
	 
	protected String getNodeValue(String tagName, NodeList nodes ) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            NodeList childNodes = node.getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++ ) {
	                Node data = childNodes.item(y);
	                if ( data.getNodeType() == Node.TEXT_NODE )
	                    return data.getNodeValue();
	            }
	        }
	    }
	    return "";
	}
	 
	protected String getNodeAttr(String attrName, Node node ) {
	    NamedNodeMap attrs = node.getAttributes();
	    for (int y = 0; y < attrs.getLength(); y++ ) {
	        Node attr = attrs.item(y);
	        if (attr.getNodeName().equalsIgnoreCase(attrName)) {
	            return attr.getNodeValue();
	        }
	    }
	    return "";
	}
	 
	protected String getNodeAttr(String tagName, String attrName, NodeList nodes ) {
	    for ( int x = 0; x < nodes.getLength(); x++ ) {
	        Node node = nodes.item(x);
	        if (node.getNodeName().equalsIgnoreCase(tagName)) {
	            NodeList childNodes = node.getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++ ) {
	                Node data = childNodes.item(y);
	                if ( data.getNodeType() == Node.ATTRIBUTE_NODE ) {
	                    if ( data.getNodeName().equalsIgnoreCase(attrName) )
	                        return data.getNodeValue();
	                }
	            }
	        }
	    }
	 
	    return "";
	}
	
	public SqlMapAcciSit leeXML(String strXML) throws CanNotGetXMLDefinition{
		
	    SqlMapAcciSit ctoSqlMap = new SqlMapAcciSit();
		
		try {
			
			InputStream stream = this.getClass().getResourceAsStream("/"+strXML);
			InputSource sourceXML = new InputSource(stream);
		    DOMParser parser = new DOMParser();
		    parser.parse(sourceXML);
		    Document doc = parser.getDocument();
		 
		    // Get the document's root XML node
		    NodeList root = doc.getChildNodes();
		 
		    // Navigate down the hierarchy to get to the sqlMap node
		    Node comp = getNode("sqlMap", root);
		    
		    ctoSqlMap.setStrNameSpace(getNodeAttr("namespace", comp));
		    ctoSqlMap.setCtoProcedure(getProcedure(comp.getChildNodes()));
		    ctoSqlMap.setCtoResultMAP(getResultMap(comp.getChildNodes()));
		    
		    log.debug("SqlMapAcciSit:" + ctoSqlMap.toString());
		    
		    
		}
		catch ( Exception e ) {
			e.printStackTrace();
			throw new CanNotGetXMLDefinition("Error: " + e.getMessage());
		}
		return ctoSqlMap;
	}

}
