/**
*   @Objetivo           : Objeto de Exeption para implementacion JDBC
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : DataAccessException.java
*   @Fecha Creacion     : 3 abril 2070
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.exceptions;

public class DataAccessException extends Exception
{

   
	private static final long serialVersionUID = 1L;

		public DataAccessException()
		{
		}

		public DataAccessException(String message)
		{
			super(message);
		}

		public DataAccessException(Throwable cause)
		{
			super(cause);
		}

		public DataAccessException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public DataAccessException(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}