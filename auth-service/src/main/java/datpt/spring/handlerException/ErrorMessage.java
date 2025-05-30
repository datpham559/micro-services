package datpt.spring.handlerException;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private String message;
    private HttpStatus statusCode;

    public ErrorMessage(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
