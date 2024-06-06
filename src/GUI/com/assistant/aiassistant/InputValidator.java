package com.assistant.aiassistant;

import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    public static int maxInputLength = 40;

    public static boolean isInvalidEmail(String emailToCheck) {
        // checkt of een email adres valid is, met een regex / pattern
        // HIJ CHECKT:
        //   1 het e-mailadres heeft alleen valid characters  voor de @ (alfanumerieke karakters, ! # & ' + = ? -  , zijn toegestaan)
        //   2 het kan (ook voor de @) een . hebben met nog meer tekst, die moet voldoen aan 1
        //   3 dan moet er een @ in zitten
        //   4 daarna moet het domeinnaam alleen bestaan uit letters en nummers, dan een . en daarna minimaal 2 letters en max 6.
        String regex = "^[\\w!#&'+=?-]+(?:\\.[\\w!#&'+=?-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailToCheck);
        return !matcher.matches();
    }
    public static TextField findFirstTextFieldWithTooManyCharacters(ArrayList<TextField> fields) {
        for(TextField field : fields) {
            if(doesTextFieldHaveTooManyCharacters(field)) {
                return field;
            }
        }
        return null;
    }
    public static TextField findFirstEmptyTextField(ArrayList<TextField> fields) {
        for(TextField field : fields) {
            if(isTextfieldEmpty(field)) {
                return field;
            }
        }
        return null;
    }
    public static boolean isTextfieldEmpty(TextField fieldToCheck) {
        return fieldToCheck.getText().equalsIgnoreCase("");
    }
    public static boolean doesTextFieldHaveTooManyCharacters(TextField fieldToCheck) {
        return fieldToCheck.getText().length() > InputValidator.maxInputLength;
    }
}
