package Xtext2SonarM2T.main;

import org.eclipse.xtext.Keyword;

public class RenameSpecialKeywords {
	
	public Keyword rename(Keyword keyword) {
		
		if(keyword.getValue().equals('+')) {
			keyword.setValue("SUMA");
		} else if(keyword.getValue().equals('-')) {
			keyword.setValue("RESTA");
		} else if(keyword.getValue().equals('*')) {
			keyword.setValue("MULTIPLICACION");
		} else if(keyword.getValue().equals('/')) {
			keyword.setValue("DIVISION");
		} else if(keyword.getValue().equals('<')) {
			keyword.setValue("MENOR");
		} else if(keyword.getValue().equals('>')) {
			keyword.setValue("MAYOR");
		} else if(keyword.getValue().equals("<=")) {
			keyword.setValue("MENOR_IGUAL");
		} else if(keyword.getValue().equals(">=")) {
			keyword.setValue("MAYOR_IGUAL");
		} else if(keyword.getValue().equals('=')) {
			keyword.setValue("IGUAL");
		} else if(keyword.getValue().equals("<-")) {
			keyword.setValue("ASIGNACION");
		} else if(keyword.getValue().equals("!=")) {
			keyword.setValue("DISTINTO");
		} else if(keyword.getValue().equals(')')) {
			keyword.setValue("PARENTESIS_DER");
		} else if(keyword.getValue().equals('(')) {
			keyword.setValue("PARENTESIS_IZQ");
		} else if(keyword.getValue().equals("..")) {
			keyword.setValue("PUNTOS_SUSPENSIVOS");
		} else if(keyword.getValue().equals(':')) {
			keyword.setValue("DOS_PUNTOS");
		} else if(keyword.getValue().equals('.')) {
			keyword.setValue("PUNTO");
		} else if(keyword.getValue().equals(',')) {
			keyword.setValue("COMA");
		} else if(keyword.getValue().equals('}')) {
			keyword.setValue("LLAVE_DER");
		} else if(keyword.getValue().equals('{')) {
			keyword.setValue("LLAVE_IZQ");
		} else if(keyword.getValue().equals(']')) {
			keyword.setValue("CORCHETE_DER");
		} else if(keyword.getValue().equals('[')) {
			keyword.setValue("CORCHETE_IZQ");
		} else if(keyword.getValue().equals("][")) {
			keyword.setValue("CORCHETES");
		} else if(keyword.getValue().equals('y')) {
			keyword.setValue("Y");
		} else if(keyword.getValue().equals('o')) {
			keyword.setValue("O");
		} else if(keyword.getValue().contains(" ")) {
			keyword.setValue(keyword.getValue().replaceAll(" ", "_"));
			keyword.setValue(keyword.getValue());
		} else if(keyword.getValue().contains(":")) {
			keyword.setValue(keyword.getValue().replaceAll(":", ""));
			keyword.setValue(keyword.getValue());
		}
		
		return keyword;
	}

}
