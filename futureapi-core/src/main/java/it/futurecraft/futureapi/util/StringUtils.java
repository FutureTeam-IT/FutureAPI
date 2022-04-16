package it.futurecraft.futureapi.util;

/**
 * String utility class.
 */
public class StringUtils {
    private StringUtils() {
    }

    /**
     * Convert a string into snake case.
     *
     * @param string The string to convert.
     * @return The converted string.
     */
    public static String toSnakeCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isUpperCase(c) && i != 0) {
                stringBuilder.append('_');
            }

            stringBuilder.append(Character.toLowerCase(c));
        }

        return stringBuilder.toString();
    }
}
