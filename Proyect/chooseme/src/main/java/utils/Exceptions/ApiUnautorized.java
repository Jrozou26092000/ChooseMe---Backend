package utils.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*Exception per for status 401*/

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnautorized extends Exception{
	public ApiUnautorized(String message) {
		super(message);
	}
}
