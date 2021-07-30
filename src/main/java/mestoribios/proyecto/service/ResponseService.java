package mestoribios.proyecto.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.json.simple.JSONObject;

public class ResponseService {
    public static ResponseEntity<Object> genError(String message, HttpStatus httpStatus) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", message);
        return new ResponseEntity<>(jsonObject, httpStatus);
    }

    public static ResponseEntity<Object> genSuccess(Object message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
