package com.warehouse.application.expections;

/**
 * Exception thrown when an article is not present in the inventory
 * for whom we are looking for
 */
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
