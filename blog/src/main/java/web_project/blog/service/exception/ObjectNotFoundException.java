package web_project.blog.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException{

    private final Object id;
    public ObjectNotFoundException(String message, Object id) {
        super(message);
        this.id = id;
    }

    public Object getId() {
        return id;
    }
}
