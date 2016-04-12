package Xtext2SonarM2T.main;

public class RenameSpecialKeywords {
	//Setting the ASCII value of characters like a name.
	
	public static String toAscii(String s){
	    StringBuilder sb = new StringBuilder();
	    String ascString = null;
	    long asciiInt;
	    
	    for (int i = 0; i < s.length(); i++){
	    	sb.append((int)s.charAt(i));
	    }
	    
	    ascString = sb.toString();
	    asciiInt = Long.parseLong(ascString);
	    
	    return Long.toString(asciiInt);
    }
	
	public String renameKeyword(final String keyword) {
		
		String newKeyword = keyword;
		
		if(newKeyword.equals("+")) {
			return "SK" + toAscii("+");
		} else if(newKeyword.equals("-")) {
			return "SK" + toAscii("-");
		} else if(newKeyword.equals("*")) {
			return "SK" + toAscii("*");
		} else if(newKeyword.equals("/")) {
			return "SK" + toAscii("/");
		} else if(newKeyword.equals("<")) {
			return "SK" + toAscii("<");
		} else if(newKeyword.equals(">")) {
			return "SK" + toAscii(">");
		} else if(newKeyword.equals("<=")) {
			return "SK" + toAscii("<=");
		} else if(newKeyword.equals(">=")) {
			return "SK" + toAscii(">=");
		} else if(newKeyword.equals("=")) {
			return "SK" + toAscii("=");
		} else if(newKeyword.equals("<-")) {
			return "SK" + toAscii("<-");
		} else if(newKeyword.equals("!=")) {
			return "SK" + toAscii("!=");
		} else if(newKeyword.equals(")")) {
			return "SK" + toAscii(")");
		} else if(newKeyword.equals("(")) {
			return "SK" + toAscii("(");
		} else if(newKeyword.equals("..")) {
			return "SK" + toAscii("..");
		} else if(newKeyword.equals(":")) {
			return "SK" + toAscii(":");
		} else if(newKeyword.equals(".")) {
			return "SK" + toAscii(".");
		} else if(newKeyword.equals(",")) {
			return "SK" + toAscii(",");
		} else if(newKeyword.equals("}")) {
			return "SK" + toAscii("}");
		} else if(newKeyword.equals("{")) {
			return "SK" + toAscii("{");
		} else if(newKeyword.equals("]")) {
			return "SK" + toAscii("]");
		} else if(newKeyword.equals("[")) {
			return "SK" + toAscii("[");
		} else if(newKeyword.equals("][")) {
			return "SK" + toAscii("][");
		} else if(newKeyword.equals("y")) {
			return "SK" + toAscii("y");
		} else if(newKeyword.equals("o")) {
			return "SK" + toAscii("o");
		} else if(newKeyword.contains(" ")) {
			newKeyword = newKeyword.replaceAll(" ", "_");
			return "SK" + toAscii(newKeyword);
		} else if(newKeyword.contains(":")) {
			newKeyword = newKeyword.replaceAll(":", "");
			return "SK" + toAscii(newKeyword);
		} else {
			return "SKOther";
		}
	}
}
