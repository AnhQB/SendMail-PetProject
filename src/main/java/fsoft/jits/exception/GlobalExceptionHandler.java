package fsoft.jits.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);
	
	@ExceptionHandler(BindException.class)
	//@ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
	public String handleBindException(BindException e) {
	  // Trả về message của lỗi đầu tiên
		String errorMessage = "Request không hợp lệ";
		if (e.getBindingResult().hasErrors()) {
			LOGGER.error(e.getMessage(), e);
			e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	    }
	    return errorMessage;
	}
	
	@ExceptionHandler(CustomException.class)
	public String handleCustomException(HttpServletResponse res, CustomException ex) 
			throws IOException {
		LOGGER.error(ex.getMessage(), ex);
		res.sendError(ex.getHttpStatus().value(), ex.getMessage());
		return ex.getMessage();
	}
}
