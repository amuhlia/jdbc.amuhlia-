/**
*   @Objetivo           : Objeto de Exeption para implementacion JDBC
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : DataAccessException.java
*   @Fecha Creacion     : 3 abril 2070
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.exceptions;

public class JdbcUpdateAffectedIncorrectNumberOfRowsException extends Exception
{

   	private static final long serialVersionUID = -3629038092454110046L;

		public JdbcUpdateAffectedIncorrectNumberOfRowsException()
		{
		}

		public JdbcUpdateAffectedIncorrectNumberOfRowsException(String message)
		{
			super(message);
		}

		public JdbcUpdateAffectedIncorrectNumberOfRowsException(Throwable cause)
		{
			super(cause);
		}

		public JdbcUpdateAffectedIncorrectNumberOfRowsException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public JdbcUpdateAffectedIncorrectNumberOfRowsException(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}