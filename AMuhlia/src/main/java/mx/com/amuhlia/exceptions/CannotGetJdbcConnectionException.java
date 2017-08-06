/**
*   @Objetivo           : Objeto de Exeption para implementacion JDBC
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : DataAccessException.java
*   @Fecha Creacion     : 3 abril 2070
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.exceptions;

public class CannotGetJdbcConnectionException extends Throwable
{
   
	private static final long serialVersionUID = 1L;

		public CannotGetJdbcConnectionException()
		{
		}

		public CannotGetJdbcConnectionException(String message)
		{
			super(message);
		}

		public CannotGetJdbcConnectionException(Throwable cause)
		{
			super(cause);
		}

		public CannotGetJdbcConnectionException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public CannotGetJdbcConnectionException(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}