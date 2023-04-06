package net.codejava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.codejava.payload.APIResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String webGlobalException(Exception e)
	{
		e.printStackTrace();
		return "errorPage";
	}
	
	@ExceptionHandler(CustomResourceNotFoundException.class)
	public ResponseEntity<APIResponse> endpointGlobalException(CustomResourceNotFoundException e)
	{
		APIResponse apiResponse= new APIResponse();
		apiResponse.setMessage(e.getMessage());
		apiResponse.setStatus(false);
		apiResponse.setHttpStatus(HttpStatus.BAD_GATEWAY);
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.BAD_GATEWAY);
	}
}
