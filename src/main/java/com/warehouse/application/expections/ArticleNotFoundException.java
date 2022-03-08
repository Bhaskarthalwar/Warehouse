package com.warehouse.application.expections;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleNotFoundException(Throwable cause) {
        super(cause);
    }
}
