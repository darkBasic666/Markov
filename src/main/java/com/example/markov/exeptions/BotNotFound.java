package wolox.chargebee.bravo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BotNotFound extends RuntimeException  {

    public BotNotFound(String message) {
        super(message);
    }

}