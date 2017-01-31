package org.sonar.PogoDsl.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public class PogoDsl extends Grammar {
	public Rule POGOSYSTEM;
	public Rule IMPORT;
	public Rule POGOMULTICLASSES;
	public Rule ONECLASSSIMPLEDEF;
	public Rule POGODEVICECLASS;
	public Rule LANGUAGE;
	public Rule DISPLAYLEVEL;
	public Rule ATTRTYPE;
	public Rule RW_TYPE;
	public Rule BOOLEAN;
	public Rule CLASSDESCRIPTION;
	public Rule INHERITANCE;
	public Rule CLASSIDENTIFICATION;
	public Rule COMMENTS;
	public Rule PREFERENCES;
	public Rule STATE;
	public Rule PROPERTY;
	public Rule PROPTYPE;
	public Rule SIMPLETYPE;
	public Rule VECTORTYPE;
	public Rule INHERITANCESTATUS;
	public Rule COMMAND;
	public Rule ARGUMENT;
	public Rule ATTRIBUTE;
	public Rule FORWARDEDATTRIBUTE;
	public Rule FIREEVENTS;
	public Rule EVENTCRITERIA;
	public Rule ATTRPROPERTIES;
	public Rule ADDITIONALFILE;
	public Rule OVERLODEDPOLLPERIODOBJECT;
	public Rule PIPE;
	public Rule RW_PIPETYPE;
	public Rule TYPE;
	public Rule VOIDTYPE;
	public Rule BOOLEANTYPE;
	public Rule SHORTTYPE;
	public Rule USHORTTYPE;
	public Rule INTTYPE;
	public Rule UINTTYPE;
	public Rule FLOATTYPE;
	public Rule DOUBLETYPE;
	public Rule STRINGTYPE;
	public Rule CHARARRAYTYPE;
	public Rule SHORTARRAYTYPE;
	public Rule USHORTARRAYTYPE;
	public Rule INTARRAYTYPE;
	public Rule UINTARRAYTYPE;
	public Rule FLOATARRAYTYPE;
	public Rule DOUBLEARRAYTYPE;
	public Rule STRINGARRAYTYPE;
	public Rule LONGSTRINGARRAYTYPE;
	public Rule DOUBLESTRINGARRAYTYPE;
	public Rule STATETYPE;
	public Rule CONSTSTRINGTYPE;
	public Rule BOOLEANARRAYTYPE;
	public Rule UCHARTYPE;
	public Rule LONGTYPE;
	public Rule ULONGTYPE;
	public Rule LONGARRAYTYPE;
	public Rule ULONGARRAYTYPE;
	public Rule DEVINTTYPE;
	public Rule ENCODEDTYPE;
	public Rule ENUMTYPE;
	public Rule SHORTVECTORTYPE;
	public Rule INTVECTORTYPE;
	public Rule LONGVECTORTYPE;
	public Rule ULONGVECTORTYPE;
	public Rule FLOATVECTORTYPE;
	public Rule DOUBLEVECTORTYPE;
	public Rule STRINGVECTORTYPE;

	@Override
	public Rule getRootRule() {
		// TODO Auto-generated method stub
		return POGOSYSTEM;
	}
}
