package g38.tpi.bda2023.Estaciones.application;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());

        if (isNotBlank(message)) {
            response.put("message", message);
        }
        if (responseObj != null) {
            response.put("data", responseObj);
        }
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> success() {
        return generateResponse(null, HttpStatus.OK, null);
    }

    public static ResponseEntity<Object> notFound() {
        return generateResponse(null, HttpStatus.NOT_FOUND, null);
    }

    public static ResponseEntity<Object> success(Object responseObj) {
        return generateResponse(null, HttpStatus.OK, responseObj);
    }

    public static ResponseEntity<Object> created(Object responseObj) {
        return generateResponse(null, HttpStatus.CREATED, responseObj);
    }

    public static ResponseEntity<Object> badRequest(String message) {
        return generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }

    public static ResponseEntity<Object> noContent() {
        return generateResponse(null, HttpStatus.NO_CONTENT, null);
    }
    public static ResponseEntity<Object> internalError() {
        return generateResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public static ResponseEntity<Object> unauthorized() {
        return generateResponse(null, HttpStatus.UNAUTHORIZED, null);
    }
}
