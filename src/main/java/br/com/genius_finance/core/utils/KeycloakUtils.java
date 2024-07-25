package br.com.genius_finance.core.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KeycloakUtils {

    public static String extractUserIdFromLocation(String location) {
        return location.replaceAll(".*/(.*)$", "$1");
    }

}
