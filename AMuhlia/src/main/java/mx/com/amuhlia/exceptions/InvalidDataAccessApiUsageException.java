/**
*   @Objetivo           : Objeto de Exeption para implementacion JDBC
*   @Autor              : Jose Agustin Muhlia Montero
*   @Objeto             : DataAccessException.java
*   @Fecha Creacion     : 3 abril 2070
*   @Versi�n            : 1.0.0
*/
package mx.com.amuhlia.exceptions;

public class InvalidDataAccessApiUsageException extends Exception
{

   
	private static final long serialVersionUID = -2752039960305530247L;

		public InvalidDataAccessApiUsageException()
		{
		}

		public InvalidDataAccessApiUsageException(String message)
		{
			super(message);
		}

		public InvalidDataAccessApiUsageException(Throwable cause)
		{
			super(cause);
		}

		public InvalidDataAccessApiUsageException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public InvalidDataAccessApiUsageException(String message, Throwable cause, 
                                           boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}