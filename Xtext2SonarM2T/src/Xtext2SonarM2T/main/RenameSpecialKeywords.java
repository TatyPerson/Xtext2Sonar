/*Copyright (C) 2016  Tatiana Person Montero

This program is free software: you can redistribute it and/or modify
it under the terms of the Eclipse Public License as published by
the Eclipse Software Foundation, either version 1 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
Eclipse Public License for more details.

You should have received a copy of the Eclipse Public License
along with this program.  If not, see <https://www.eclipse.org/legal/epl-v10.html>*/

package Xtext2SonarM2T.main;

public class RenameSpecialKeywords {
	
	/**
	 * Function to convert a keyword in Ascii representation.
	 * @param keyword
	 * @return the renamed keyword
	 */
	public static String renameKeyword(final String keyword) {
		return "SK" + toAscii(keyword);
	}
	
	//Helper function of renameKeyword function.
	private static String toAscii(String s){
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i = 0; i < s.length(); i++){
	    	sb.append((int)s.charAt(i));
	    }
	    
	    return Long.toString(Long.parseLong(sb.toString()));
    }
}
