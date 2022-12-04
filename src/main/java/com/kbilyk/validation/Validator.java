package com.kbilyk.validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.kbilyk.constant.maxSizes.*;

public class Validator {

    // regular expressions
    private static final String regexRealIntegerNumber = "([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)";

    private static final String regexIntegerNumber = "[1-9]\\d{0," +  (maxDigitInAddField - 1) + "}$";
    private static final String regexWordsLessThanMaxSymbColour = "^[a-zA-Z\\s\\-&]{1," + maxSizeOfColourField + "}$";


    /**
     * Method check if there is only integer number in text field
     * Also it changes error label: visible label if it`s not integer, invisible if is
     * @param label The error label
     * @param text The text field
     * @return true - if integer, false - if not
     */
    public static boolean isTextFieldIsInteger(TextField text, Label label){
        label.setVisible(!text.getText().matches(regexIntegerNumber));
        return text.getText().matches(regexIntegerNumber);
    }

    /**
     * Method check if there are only words (letters, ' ', &, -) in text field and has max size
     * Also it changes error label: visible label if it`s not only words, invisible if is.
     * @param label The error label
     * @param text The text field
     * @return true - if words, false - if not
     */
    public static boolean isTextFieldIsWords(TextField text, Label label){
        label.setVisible(!text.getText().matches(regexWordsLessThanMaxSymbColour));
        return text.getText().matches(regexWordsLessThanMaxSymbColour);
    }

    /**
     * Method check if there are only one real number in text field
     * Also it changes error label: visible label if it`s not real number, invisible if is
     * @param label The error label
     * @param text The text field
     * @return true - if words, false - if not
     */
    public static boolean isTextFieldIsRealNumber(TextField text, Label label){
        label.setVisible(!text.getText().matches(regexRealIntegerNumber) || text.getText().length() > maxDigitsInNumber);
        return text.getText().matches(regexRealIntegerNumber) && text.getText().length() <= maxDigitsInNumber;
    }

    /**
     * Method check if text has length less than some value
     * Also it changes error label: visible label if it`s bigger than value, invisible if it`s less
     * @param text The text area
     * @param label The error label
     * @return true - if text has length less than some value, false - if not
     */
    public static boolean isTextAreaLessThanMax(TextArea text, Label label){
        label.setVisible(text.getText().length() > maxSizeOfInfoField);
        return text.getText().length() <= maxSizeOfInfoField;
    }

    /**
     * Check if there is a real/integer number in some range
     * @param text The text field
     * @param label The error label
     * @param min The min value in range
     * @param max The max value in range
     * @return true - if entered number in range, false - if not
     */
    public static boolean isNumberInRange(TextField text, Label label, double min, double max){
        boolean isNumberInRange = text.getText().matches(regexRealIntegerNumber);

        if(isNumberInRange){
            double number = Double.parseDouble(text.getText());
            if(number < min || number > max){
                isNumberInRange = false;
            }
        }

        label.setVisible(!isNumberInRange);
        return isNumberInRange;
    }

    /**
     * Check if there is a real/integer number in some range and if it`s bigger/equals than some value
     * @param text The text field
     * @param label The error label
     * @param min The min value in range
     * @param max The max value in range
     * @param enteredMin The min limit that user entered
     * @return true - if entered number in range, false - if not
     */

    public static boolean isNumberInRangeBiggerThan(TextField text, Label label, double min, double max, double enteredMin){
        boolean isNumberInRange = text.getText().matches(regexRealIntegerNumber);

        if(isNumberInRange){
            double number = Double.parseDouble(text.getText());
            if(number < min || number > max || number < enteredMin){
                isNumberInRange = false;
            }
        }

        label.setVisible(!isNumberInRange);
        return isNumberInRange;
    }
}
