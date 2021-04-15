package utils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*Exception per for status 404*/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends Exception {
	
	public ApiNotFound(String message) {
		super(message);
	}

}
