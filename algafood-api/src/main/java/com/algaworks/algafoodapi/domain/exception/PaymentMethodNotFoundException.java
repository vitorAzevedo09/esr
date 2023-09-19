package com.algaworks.algafoodapi.domain.exception;

/**
 * PaymentMethodNotFoundException
 */
public class PaymentMethodNotFoundException extends NotFoundException{
    public PaymentMethodNotFoundException(String message){
        super(message);
    }

    public PaymentMethodNotFoundException(Long id){
        super(String.format("There is no payment method with id equals to %d", id));
    }
}
