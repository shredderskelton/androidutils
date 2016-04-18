package com.shredder.utils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private static final int MAX_COUNTRYCODE_LENGTH = 5;
    private static final int MAX_DISTRICTCODE_LENGTH = 5;
    private static final int MAX_PHONENUMBER_LENGTH = 20;

    private static String INTERNATIONAL_PHONE_NUMBER_PATTERN = "^\\+(?:[0-9] ?){6,14}[0-9]$";
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	
	public static boolean isMailValid(String str) {
		String strLwr = str.toLowerCase();
		Pattern p = Pattern.compile("[0-9a-z._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}");
		Matcher m = p.matcher(strLwr);
		return m.matches();
	}
	
	
	public static boolean isCountryCodeValid(String str) {
		return str.length() <= MAX_COUNTRYCODE_LENGTH && str.trim().length() > 1;
	}
	
	public static boolean isDisrictCodeValid(String str) {
		return str.length() <= MAX_DISTRICTCODE_LENGTH && str.trim().length() > 1;
	}
	
	public static boolean isPhoneNumberValid(String str) {
		return str.length() <= MAX_PHONENUMBER_LENGTH && str.trim().length() > 2 && containsOnlyPhoneChars(str);
	}
	

	public static boolean isValidEmail(String email){
		   Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		   Matcher m = p.matcher(email);
		   boolean matchFound = m.matches();
		   StringTokenizer st = new StringTokenizer(email, ".");
		   String lastToken = null;
		   while (st.hasMoreTokens()) {
		      lastToken = st.nextToken();
		   }	
		   if (matchFound && lastToken.length() >= 2
		      && email.length() - 1 != lastToken.length()) {
		      return true;
		   }
		   else return false;
		}
	
	private static boolean containsOnlyPhoneChars(String str) {
        if (str == null || str.length() == 0)
            return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '+' )
                return false;
        }
        return true;
    }

    private static boolean isInternationalPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile(INTERNATIONAL_PHONE_NUMBER_PATTERN);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }
}