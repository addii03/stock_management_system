package stocks_management_system.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(CustomNewException.class)
	
	public ResponseEntity<?> customExceptionHandler(CustomNewException exception, WebRequest webRequest)
	{
		ErrorDetails errorDetails= new ErrorDetails(webRequest .getDescription(false),exception.getMessage(), new Date());
	
	return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception2, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(webRequest.getDescription(false), exception2.getMessage(),
				new Date(3));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
