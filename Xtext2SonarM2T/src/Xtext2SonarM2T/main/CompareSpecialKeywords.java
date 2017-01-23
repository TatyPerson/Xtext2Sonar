package Xtext2SonarM2T.main;

import java.util.regex.Pattern;

public class CompareSpecialKeywords {
	
	private final static String patternAccents = "[ñÑáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙ]";
	private final static String patternXtextExpressions = "[AaZze]";
	private final static String otherPunctuatorsCharacters = "[¡|ￜ]";
	
	/**
	 * Function to compare if the input keyword is a punctuation character.
	 * @param keyword
	 * @return true if the keyword is a punctuation character.
	 */
	public static boolean compareSpecialKeyword(String keyword) {		
		if(keyword.length() == 1) {
			return Pattern.matches("\\p{Punct}", keyword) || Pattern.matches("\\p{Digit}", keyword) || Pattern.matches(otherPunctuatorsCharacters, keyword);
		} else {
			boolean specialKeyword = true;
			
			for(int i=0; i<keyword.length(); i++) {
				specialKeyword = specialKeyword && (Pattern.matches("\\p{Punct}", String.valueOf(keyword.charAt(i))) || 
						Pattern.matches("\\p{Space}", String.valueOf(keyword.charAt(i))) || Pattern.matches("\\p{Digit}", String.valueOf(keyword.charAt(i)))
						|| Pattern.matches(otherPunctuatorsCharacters, String.valueOf(keyword.charAt(i))));
			}
			return specialKeyword;
		}
	}
	
	public static boolean noValidCharacters(String keyword) {
		if(keyword.length() == 1) {
			return Pattern.matches(patternXtextExpressions, keyword) || Pattern.matches(patternAccents, keyword) 
					|| Pattern.matches("\\p{Space}", keyword) || Pattern.matches("\\p{Digit}", keyword);
		} else {
			return false;
		}
	}	
}