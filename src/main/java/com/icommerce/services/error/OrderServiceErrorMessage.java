package com.icommerce.services.error;

/**
 *
 * @author kaiser
 */
public class OrderServiceErrorMessage {
    public static final String MISSING_FULL_NAME_OF_USER = "Full name of user must not be null";
    public static final String MISSING_ADDRESS_OF_USER = "Address of user must not be null";
    public static final String MISSING_PHONE_OF_USER = "Phone of user must not be null";
    public static final String MISSING_EMAIL_OF_USER = "Email of user must not be null";
    public static final String MISSING_NAME_OF_PRODUCT_IN_ORDER = "Name of product must not be null";
    public static final String MISSING_QUANTITY_OF_PRODUCT_IN_ORDER = "Quantity must not be null";
    public static final String NUMBER_PRODUCT_IN_ORDER_MUST_GREATER_THAN_ZERO = "The number of product in order must be greater than 0";
    public static final String NUMBER_PRODUCT_IN_ORDER_NOT_VALID = "The number of product in order must not exceed remaining quantity of product exist product";
}
