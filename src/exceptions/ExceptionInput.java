package exceptions;

import models.products.ReadableItems;

public class ExceptionInput extends RuntimeException {
    public ExceptionInput(String message) {
        super(message);
    }
}
