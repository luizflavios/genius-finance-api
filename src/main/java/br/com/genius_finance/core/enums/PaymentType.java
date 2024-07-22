package br.com.genius_finance.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {

    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD("Cartão de Débito"),
    MONEY("Dinheiro"),
    TRANSFER("Transferência ou Pix");

    private final String portugueseText;

}
