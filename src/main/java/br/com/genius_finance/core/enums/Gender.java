package br.com.genius_finance.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    INDIFFERENT("Indifferent");

    private final String description;
    
}
