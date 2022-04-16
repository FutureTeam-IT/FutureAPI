package it.futurecraft.futureapi.util;

/**
 * Validator checker
 */
public class Validator {
    /**
     * Check if a string can be parsed as an integer without errors
     *
     * @param string Presumed integer string
     * @return true is the string is a valid integer, otherwise false
     */
    public static boolean canBeInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if a string can be parsed as a double without errors
     *
     * @param string Presumed double string
     * @return true is the string is a valid double, otherwise false
     */
    public static boolean canBeDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
