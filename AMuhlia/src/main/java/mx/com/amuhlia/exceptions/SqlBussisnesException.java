/**
*   @Objetivo           : Objeto de Exeption para implementacion JDBC
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : DataAccessException.java
*   @Fecha Creacion     : 3 abril 2070
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.exceptions;

public class SqlBussisnesException extends Exception
{

   	private static final long serialVersionUID = 6671718052693741436L;

		public SqlBussisnesException()
		{
		}

		public SqlBussisnesException(String message)
		{
			super(message);
		}

		public SqlBussisnesException(Throwable cause)
		{
			super(cause);
		}

		public SqlBussisnesException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public SqlBussisnesException(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}