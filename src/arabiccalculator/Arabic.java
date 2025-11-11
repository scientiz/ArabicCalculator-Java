// Arabic.java
// Author: ScienTiz, COSC 211 F25
// Custom Arabic numeral class that converts between symbols and integers.

package arabiccalculator;

import java.util.HashMap;
import java.util.Map;

public class Arabic {

    private String arabicNumber;   // Current Arabic numeral
    private int value;             // Integer value

    // Maps for symbol ↔ value conversion
    private static final Map<Character, Integer> arabicToValue = new HashMap<>();
    private static final Map<Integer, Character> valueToArabic = new HashMap<>();

    // Load symbol mappings
    static {
        arabicToValue.put('غ', 1000); // Ghayn
        arabicToValue.put('ث', 500); // Tha
        arabicToValue.put('ق', 100); // Qaf
        arabicToValue.put('ن', 50); // Noon
        arabicToValue.put('ي', 10); // Ya
        arabicToValue.put('ه', 5); // Ha
        arabicToValue.put('أ', 1); //Alif

        for (Map.Entry<Character, Integer> entry : arabicToValue.entrySet()) {
            valueToArabic.put(entry.getValue(), entry.getKey());
        }
    }

    public Arabic() {
        arabicNumber = "";
        value = 0;
    }

    public Arabic(String arabicNumber) {
        setArabic(arabicNumber);
    }

    // Set the numeral and update its value
    public void setArabic(String arabicNumber) {
        this.arabicNumber = arabicNumber.replaceAll("\\s+", "");
        this.value = convert_Arabic_To_Integer();
    }

    // Convert Arabic numeral to integer (additive)
    public int convert_Arabic_To_Integer() {
        int total = 0;
        for (int i = 0; i < arabicNumber.length(); i++) {
            Integer val = arabicToValue.get(arabicNumber.charAt(i));
            if (val != null) total += val;
        }
        return total;
    }

    // Then convert integer back to Arabic numeral
    public String convert_Integer_To_Arabic(int number) {
        StringBuilder result = new StringBuilder();
        int[] values = {1000, 500, 100, 50, 10, 5, 1};

        for (int val : values) {
            while (number >= val) {
                result.append(valueToArabic.get(val));
                number -= val;
            }
        }
        return result.toString();
    }

    public int getValue() { return value; }

    public String getArabic() { return arabicNumber; }
}