/**
*   @Objetivo           : Objeto de XML Exeption para lectura de XML iBatisOLD
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : DataAccessException.java
*   @Fecha Creacion     : 3 abril 2070
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.exceptions;

public class CanNotGetXMLDefinition extends Throwable
{
   
	private static final long serialVersionUID = 1L;

		public CanNotGetXMLDefinition()
		{
		}

		public CanNotGetXMLDefinition(String message)
		{
			super(message);
		}

		public CanNotGetXMLDefinition(Throwable cause)
		{
			super(cause);
		}

		public CanNotGetXMLDefinition(String message, Throwable cause)
		{
			super(message, cause);
		}

		public CanNotGetXMLDefinition(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}