package org.sonar.PogoDsl.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.PogoDsl.PogoDslConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.PogoDsl.api.PogoDslTokenType.NUMBER;
import static org.sonar.PogoDsl.api.PogoDslTokenType.STRING;
import static org.sonar.PogoDsl.api.PogoDslTokenType.CHARACTER;

public enum PogoDslImpl implements GrammarRuleKey {
	POGOSYSTEM,
	IMPORT,
	POGOMULTICLASSES,
	ONECLASSSIMPLEDEF,
	POGODEVICECLASS,
	LANGUAGE,
	DISPLAYLEVEL,
	ATTRTYPE,
	RW_TYPE,
	BOOLEAN,
	CLASSDESCRIPTION,
	INHERITANCE,
	CLASSIDENTIFICATION,
	COMMENTS,
	PREFERENCES,
	STATE,
	PROPERTY,
	PROPTYPE,
	SIMPLETYPE,
	VECTORTYPE,
	INHERITANCESTATUS,
	COMMAND,
	ARGUMENT,
	ATTRIBUTE,
	FORWARDEDATTRIBUTE,
	FIREEVENTS,
	EVENTCRITERIA,
	ATTRPROPERTIES,
	ADDITIONALFILE,
	OVERLODEDPOLLPERIODOBJECT,
	PIPE,
	RW_PIPETYPE,
	TYPE,
	VOIDTYPE,
	BOOLEANTYPE,
	SHORTTYPE,
	USHORTTYPE,
	INTTYPE,
	UINTTYPE,
	FLOATTYPE,
	DOUBLETYPE,
	STRINGTYPE,
	CHARARRAYTYPE,
	SHORTARRAYTYPE,
	USHORTARRAYTYPE,
	INTARRAYTYPE,
	UINTARRAYTYPE,
	FLOATARRAYTYPE,
	DOUBLEARRAYTYPE,
	STRINGARRAYTYPE,
	LONGSTRINGARRAYTYPE,
	DOUBLESTRINGARRAYTYPE,
	STATETYPE,
	CONSTSTRINGTYPE,
	BOOLEANARRAYTYPE,
	UCHARTYPE,
	LONGTYPE,
	ULONGTYPE,
	LONGARRAYTYPE,
	ULONGARRAYTYPE,
	DEVINTTYPE,
	ENCODEDTYPE,
	ENUMTYPE,
	SHORTVECTORTYPE,
	INTVECTORTYPE,
	LONGVECTORTYPE,
	ULONGVECTORTYPE,
	FLOATVECTORTYPE,
	DOUBLEVECTORTYPE,
	STRINGVECTORTYPE;

	public static final Logger LOG = LoggerFactory.getLogger("PogoDslImpl");

	public static Grammar create(PogoDslConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(POGOSYSTEM);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {

		b.rule(POGOSYSTEM).is(
		b.sequence(		b.zeroOrMore(IMPORT),
		b.zeroOrMore(POGODEVICECLASS),
		b.zeroOrMore(POGOMULTICLASSES)
		));
		


		b.rule(IMPORT).is(
		b.sequence(		b.isOneOfThem(PogoDslKeyword.	IMPORT_1184795739, PogoDslKeyword.	IMPORT_1184795739),
		STRING
		));
		


		b.rule(POGOMULTICLASSES).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PogoDslKeyword.	MULTICLASSES_1600372941, PogoDslKeyword.	MULTICLASSES_1600372941),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PogoDslPunctuator.	SK123, PogoDslPunctuator.	SK123),
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		b.isOneOfThem(PogoDslKeyword.	CLASSES__692440276, PogoDslKeyword.	CLASSES__692440276),
		ONECLASSSIMPLEDEF,
		STRING,
		PREFERENCES,
		b.isOneOfThem(PogoDslPunctuator.	SK125, PogoDslPunctuator.	SK125)
		));
		


		b.rule(ONECLASSSIMPLEDEF).is(
		b.sequence(		STRING,
		STRING,
		BOOLEAN,
		BOOLEAN,
		b.isOneOfThem(PogoDslKeyword.	INHERITANCES__1172267121, PogoDslKeyword.	INHERITANCES__1172267121),
		INHERITANCE,
		b.isOneOfThem(PogoDslKeyword.	PARENTCLASSES__1347096674, PogoDslKeyword.	PARENTCLASSES__1347096674),
		STRING,
		b.isOneOfThem(PogoDslKeyword.	ADDITIONALFILES__87588950, PogoDslKeyword.	ADDITIONALFILES__87588950),
		b.zeroOrMore(ADDITIONALFILE)
		));
		


		b.rule(POGODEVICECLASS).is(
		b.sequence(		b.isOneOfThem(PogoDslKeyword.	DEVICECLASS_1539436318, PogoDslKeyword.	DEVICECLASS_1539436318),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(PogoDslKeyword.	ABSTRACT_1732898850, PogoDslKeyword.	ABSTRACT_1732898850)),
		b.optional(
		b.isOneOfThem(PogoDslKeyword.	EXTENDS_1305664359, PogoDslKeyword.	EXTENDS_1305664359),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.isOneOfThem(PogoDslPunctuator.	SK123, PogoDslPunctuator.	SK123),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(PogoDslKeyword.	DESCRIPTION__1921320002, PogoDslKeyword.	DESCRIPTION__1921320002),
		CLASSDESCRIPTION,
		b.isOneOfThem(PogoDslKeyword.	CLASSPROPERTIES__117046479, PogoDslKeyword.	CLASSPROPERTIES__117046479),
		b.zeroOrMore(PROPERTY),
		b.isOneOfThem(PogoDslKeyword.	DEVICEPROPERTIES__1978568399, PogoDslKeyword.	DEVICEPROPERTIES__1978568399),
		b.zeroOrMore(PROPERTY),
		b.isOneOfThem(PogoDslKeyword.	COMMANDS__1498724686, PogoDslKeyword.	COMMANDS__1498724686),
		b.zeroOrMore(COMMAND),
		b.isOneOfThem(PogoDslKeyword.	DYNAMICCOMMANDS__241771213, PogoDslKeyword.	DYNAMICCOMMANDS__241771213),
		b.zeroOrMore(COMMAND),
		b.isOneOfThem(PogoDslKeyword.	ATTRIBUTES__309886525, PogoDslKeyword.	ATTRIBUTES__309886525),
		b.zeroOrMore(ATTRIBUTE),
		b.isOneOfThem(PogoDslKeyword.	DYNAMICATTRIBUTES__736590852, PogoDslKeyword.	DYNAMICATTRIBUTES__736590852),
		b.zeroOrMore(ATTRIBUTE),
		b.isOneOfThem(PogoDslKeyword.	FORWARDEDATTRIBUTES__1578292993, PogoDslKeyword.	FORWARDEDATTRIBUTES__1578292993),
		b.zeroOrMore(FORWARDEDATTRIBUTE),
		b.isOneOfThem(PogoDslKeyword.	PIPES__988095403, PogoDslKeyword.	PIPES__988095403),
		b.zeroOrMore(PIPE),
		b.isOneOfThem(PogoDslKeyword.	STATES__1897139592, PogoDslKeyword.	STATES__1897139592),
		b.zeroOrMore(STATE),
		PREFERENCES,
		b.isOneOfThem(PogoDslKeyword.	ADDITIONALFILES__87588950, PogoDslKeyword.	ADDITIONALFILES__87588950),
		b.zeroOrMore(ADDITIONALFILE),
		b.isOneOfThem(PogoDslKeyword.	OVERLODEDPOLLPERIODOBJECT__155207569, PogoDslKeyword.	OVERLODEDPOLLPERIODOBJECT__155207569),
		b.zeroOrMore(OVERLODEDPOLLPERIODOBJECT),
		b.isOneOfThem(PogoDslPunctuator.	SK125, PogoDslPunctuator.	SK125)
		));
		


		b.rule(LANGUAGE).is(
		b.firstOf(
		b.isOneOfThem(PogoDslKeyword.	CPP_67971, PogoDslKeyword.	CPP_67971),
		b.isOneOfThem(PogoDslKeyword.	JAVA_2301506, PogoDslKeyword.	JAVA_2301506),
		b.isOneOfThem(PogoDslKeyword.	PYTHON_1889329924, PogoDslKeyword.	PYTHON_1889329924)
		)).skipIfOneChild();
		
		


		b.rule(DISPLAYLEVEL).is(
		b.firstOf(
		b.isOneOfThem(PogoDslKeyword.	OPERATOR_282073252, PogoDslKeyword.	OPERATOR_282073252),
		b.isOneOfThem(PogoDslKeyword.	EXPERT_2059133482, PogoDslKeyword.	EXPERT_2059133482)
		)).skipIfOneChild();
		
		


		b.rule(ATTRTYPE).is(
		b.firstOf(
		b.isOneOfThem(PogoDslKeyword.	SCALAR_1824322548, PogoDslKeyword.	SCALAR_1824322548),
		b.isOneOfThem(PogoDslKeyword.	SPECTRUM_2067891215, PogoDslKeyword.	SPECTRUM_2067891215),
		b.isOneOfThem(PogoDslKeyword.	IMAGE_70760763, PogoDslKeyword.	IMAGE_70760763)
		)).skipIfOneChild();
		
		


		b.rule(RW_TYPE).is(
		b.firstOf(
		b.isOneOfThem(PogoDslKeyword.	READ_2511254, PogoDslKeyword.	READ_2511254),
		b.isOneOfThem(PogoDslKeyword.	WRITE_82862015, PogoDslKeyword.	WRITE_82862015),
		b.isOneOfThem(PogoDslKeyword.	READ_WRITE_1247349718, PogoDslKeyword.	READ_WRITE_1247349718),
		b.isOneOfThem(PogoDslKeyword.	READ_WITH_WRITE_1854638415, PogoDslKeyword.	READ_WITH_WRITE_1854638415)
		)).skipIfOneChild();
		
		


		b.rule(BOOLEAN).is(
		b.firstOf(
		b.isOneOfThem(PogoDslKeyword.	TRUE_3569038, PogoDslKeyword.	TRUE_3569038),
		b.isOneOfThem(PogoDslKeyword.	FALSE_97196323, PogoDslKeyword.	FALSE_97196323)
		)).skipIfOneChild();
		
		


		b.rule(CLASSDESCRIPTION).is(
		b.sequence(		STRING,
		STRING,
		STRING,
		b.isOneOfThem(PogoDslKeyword.	INHERITANCES__1172267121, PogoDslKeyword.	INHERITANCES__1172267121),
		INHERITANCE,
		LANGUAGE,
		STRING,
		CLASSIDENTIFICATION,
		COMMENTS,
		STRING,
		STRING,
		BOOLEAN,
		BOOLEAN,
		BOOLEAN,
		BOOLEAN,
		BOOLEAN
		));
		


		b.rule(INHERITANCE).is(
		b.sequence(		STRING,
		STRING
		));
		


		b.rule(CLASSIDENTIFICATION).is(
		b.sequence(		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		b.isOneOfThem(PogoDslKeyword.	KEYWORDS__1878375952, PogoDslKeyword.	KEYWORDS__1878375952),
		b.zeroOrMore(STRING)
		));
		

		b.rule(COMMENTS).is(b.isOneOfThem(STRING, STRING));
		


		b.rule(PREFERENCES).is(
		b.sequence(		STRING,
		STRING,
		STRING,
		BOOLEAN
		));
		


		b.rule(STATE).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING,
		INHERITANCESTATUS
		));
		


		b.rule(PROPERTY).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		PROPTYPE,
		INHERITANCESTATUS,
		BOOLEAN,
		STRING,
		b.isOneOfThem(PogoDslKeyword.	DEFAULTPROPVALUE__1058706611, PogoDslKeyword.	DEFAULTPROPVALUE__1058706611),
		b.zeroOrMore(STRING)
		));
		


		b.rule(PROPTYPE).is(
		b.firstOf(
		SIMPLETYPE,
		VECTORTYPE
		)).skipIfOneChild();
		
		


		b.rule(SIMPLETYPE).is(
		b.firstOf(
		BOOLEANTYPE,
		SHORTTYPE,
		USHORTTYPE,
		INTTYPE,
		UINTTYPE,
		LONGTYPE,
		ULONGTYPE,
		FLOATTYPE,
		DOUBLETYPE,
		STRINGTYPE
		)).skipIfOneChild();
		
		


		b.rule(VECTORTYPE).is(
		b.firstOf(
		SHORTVECTORTYPE,
		INTVECTORTYPE,
		LONGVECTORTYPE,
		ULONGVECTORTYPE,
		FLOATVECTORTYPE,
		DOUBLEVECTORTYPE,
		STRINGVECTORTYPE
		)).skipIfOneChild();
		
		


		b.rule(INHERITANCESTATUS).is(
		b.sequence(		BOOLEAN,
		BOOLEAN,
		BOOLEAN,
		BOOLEAN,
		STRING
		));
		


		b.rule(COMMAND).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		ARGUMENT,
		ARGUMENT,
		STRING,
		INHERITANCESTATUS,
		STRING,
		DISPLAYLEVEL,
		STRING,
		BOOLEAN,
		b.isOneOfThem(PogoDslKeyword.	EXCLUDEDSTATES__1299477838, PogoDslKeyword.	EXCLUDEDSTATES__1299477838),
		b.zeroOrMore(STRING)
		));
		


		b.rule(ARGUMENT).is(
		b.sequence(		TYPE,
		STRING
		));
		


		b.rule(ATTRIBUTE).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		ATTRTYPE,
		TYPE,
		RW_TYPE,
		DISPLAYLEVEL,
		STRING,
		STRING,
		STRING,
		STRING,
		BOOLEAN,
		BOOLEAN,
		FIREEVENTS,
		FIREEVENTS,
		FIREEVENTS,
		INHERITANCESTATUS,
		ATTRPROPERTIES,
		BOOLEAN,
		BOOLEAN,
		EVENTCRITERIA,
		EVENTCRITERIA,
		b.isOneOfThem(PogoDslKeyword.	ENUMLABELS__325332954, PogoDslKeyword.	ENUMLABELS__325332954),
		b.zeroOrMore(STRING),
		b.isOneOfThem(PogoDslKeyword.	READEXCLUDEDSTATES__489900840, PogoDslKeyword.	READEXCLUDEDSTATES__489900840),
		b.zeroOrMore(STRING),
		b.isOneOfThem(PogoDslKeyword.	WRITEEXCLUDEDSTATES__2138848111, PogoDslKeyword.	WRITEEXCLUDEDSTATES__2138848111),
		b.zeroOrMore(STRING)
		));
		


		b.rule(FORWARDEDATTRIBUTE).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING,
		INHERITANCESTATUS
		));
		


		b.rule(FIREEVENTS).is(
		b.sequence(		BOOLEAN,
		BOOLEAN
		));
		


		b.rule(EVENTCRITERIA).is(
		b.sequence(		STRING,
		STRING,
		STRING
		));
		


		b.rule(ATTRPROPERTIES).is(
		b.sequence(		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING,
		STRING
		));
		


		b.rule(ADDITIONALFILE).is(
		b.sequence(		STRING,
		STRING
		));
		


		b.rule(OVERLODEDPOLLPERIODOBJECT).is(
		b.sequence(		STRING,
		STRING,
		STRING
		));
		


		b.rule(PIPE).is(
		b.sequence(		STRING,
		STRING,
		STRING,
		RW_PIPETYPE,
		DISPLAYLEVEL,
		b.isOneOfThem(PogoDslKeyword.	READEXCLUDEDSTATES__489900840, PogoDslKeyword.	READEXCLUDEDSTATES__489900840),
		b.zeroOrMore(STRING),
		b.isOneOfThem(PogoDslKeyword.	WRITEEXCLUDEDSTATES__2138848111, PogoDslKeyword.	WRITEEXCLUDEDSTATES__2138848111),
		b.zeroOrMore(STRING)
		));
		


		b.rule(RW_PIPETYPE).is(
		b.firstOf(
		b.isOneOfThem(PogoDslKeyword.	READ_2511254, PogoDslKeyword.	READ_2511254),
		b.isOneOfThem(PogoDslKeyword.	READ_WRITE_1247349718, PogoDslKeyword.	READ_WRITE_1247349718)
		)).skipIfOneChild();
		
		


		b.rule(TYPE).is(
		b.firstOf(
		VOIDTYPE,
		BOOLEANTYPE,
		SHORTTYPE,
		USHORTTYPE,
		INTTYPE,
		UINTTYPE,
		FLOATTYPE,
		DOUBLETYPE,
		STRINGTYPE,
		CHARARRAYTYPE,
		SHORTARRAYTYPE,
		USHORTARRAYTYPE,
		INTARRAYTYPE,
		UINTARRAYTYPE,
		FLOATARRAYTYPE,
		DOUBLEARRAYTYPE,
		STRINGARRAYTYPE,
		LONGSTRINGARRAYTYPE,
		DOUBLESTRINGARRAYTYPE,
		STATETYPE,
		CONSTSTRINGTYPE,
		BOOLEANARRAYTYPE,
		LONGTYPE,
		ULONGTYPE,
		UCHARTYPE,
		LONGARRAYTYPE,
		ULONGARRAYTYPE,
		DEVINTTYPE,
		ENCODEDTYPE,
		ENUMTYPE
		)).skipIfOneChild();
		
		


		b.rule(VOIDTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VOID_3625364, PogoDslKeyword.	VOID_3625364)
		);
		


		b.rule(BOOLEANTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	BOOLEAN_64711720, PogoDslKeyword.	BOOLEAN_64711720)
		);
		


		b.rule(SHORTTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	SHORT_109413500, PogoDslKeyword.	SHORT_109413500)
		);
		


		b.rule(USHORTTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	USHORT_835943129, PogoDslKeyword.	USHORT_835943129)
		);
		


		b.rule(INTTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	INT_104431, PogoDslKeyword.	INT_104431)
		);
		


		b.rule(UINTTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	UINT_3589978, PogoDslKeyword.	UINT_3589978)
		);
		


		b.rule(FLOATTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	FLOAT_97526364, PogoDslKeyword.	FLOAT_97526364)
		);
		


		b.rule(DOUBLETYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DOUBLE_1325958191, PogoDslKeyword.	DOUBLE_1325958191)
		);
		


		b.rule(STRINGTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	STRING_891985903, PogoDslKeyword.	STRING_891985903)
		);
		


		b.rule(CHARARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARCHARARRAY_2027347503, PogoDslKeyword.	DEVVARCHARARRAY_2027347503)
		);
		


		b.rule(SHORTARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARSHORTARRAY_1523088433, PogoDslKeyword.	DEVVARSHORTARRAY_1523088433)
		);
		


		b.rule(USHORTARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARUSHORTARRAY_964562112, PogoDslKeyword.	DEVVARUSHORTARRAY_964562112)
		);
		


		b.rule(INTARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARLONGARRAY_832894571, PogoDslKeyword.	DEVVARLONGARRAY_832894571)
		);
		


		b.rule(UINTARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARULONGARRAY_1820742874, PogoDslKeyword.	DEVVARULONGARRAY_1820742874)
		);
		


		b.rule(FLOATARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARFLOATARRAY_189043183, PogoDslKeyword.	DEVVARFLOATARRAY_189043183)
		);
		


		b.rule(DOUBLEARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARDOUBLEARRAY_2056865098, PogoDslKeyword.	DEVVARDOUBLEARRAY_2056865098)
		);
		


		b.rule(STRINGARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARSTRINGARRAY_1949467018, PogoDslKeyword.	DEVVARSTRINGARRAY_1949467018)
		);
		


		b.rule(LONGSTRINGARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARLONGSTRINGARRAY_853003430, PogoDslKeyword.	DEVVARLONGSTRINGARRAY_853003430)
		);
		


		b.rule(DOUBLESTRINGARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARDOUBLESTRINGARRAY_1358582629, PogoDslKeyword.	DEVVARDOUBLESTRINGARRAY_1358582629)
		);
		


		b.rule(STATETYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVSTATE_1154021788, PogoDslKeyword.	DEVSTATE_1154021788)
		);
		


		b.rule(CONSTSTRINGTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	CONSTDEVSTRING_1097727869, PogoDslKeyword.	CONSTDEVSTRING_1097727869)
		);
		


		b.rule(BOOLEANARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARBOOLEANARRAY_933778653, PogoDslKeyword.	DEVVARBOOLEANARRAY_933778653)
		);
		


		b.rule(UCHARTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVUCHAR_1154415222, PogoDslKeyword.	DEVUCHAR_1154415222)
		);
		


		b.rule(LONGTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVLONG64_1210288783, PogoDslKeyword.	DEVLONG64_1210288783)
		);
		


		b.rule(ULONGTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVULONG64_1555971418, PogoDslKeyword.	DEVULONG64_1555971418)
		);
		


		b.rule(LONGARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARLONG64ARRAY_1444597267, PogoDslKeyword.	DEVVARLONG64ARRAY_1444597267)
		);
		


		b.rule(ULONGARRAYTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVVARULONG64ARRAY_1310150500, PogoDslKeyword.	DEVVARULONG64ARRAY_1310150500)
		);
		


		b.rule(DEVINTTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVINT_2043646906, PogoDslKeyword.	DEVINT_2043646906)
		);
		


		b.rule(ENCODEDTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVENCODED_1203151705, PogoDslKeyword.	DEVENCODED_1203151705)
		);
		


		b.rule(ENUMTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	DEVENUM_1071574378, PogoDslKeyword.	DEVENUM_1071574378)
		);
		


		b.rule(SHORTVECTORTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VECTOR_SHORT__79254565, PogoDslKeyword.	VECTOR_SHORT__79254565)
		);
		


		b.rule(INTVECTORTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VECTOR_INT__549345544, PogoDslKeyword.	VECTOR_INT__549345544)
		);
		


		b.rule(LONGVECTORTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VECTOR_LONG__147361399, PogoDslKeyword.	VECTOR_LONG__147361399)
		);
		


		b.rule(ULONGVECTORTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VECTOR_ULONG__18306426, PogoDslKeyword.	VECTOR_ULONG__18306426)
		);
		


		b.rule(FLOATVECTORTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VECTOR_FLOAT__447755781, PogoDslKeyword.	VECTOR_FLOAT__447755781)
		);
		


		b.rule(DOUBLEVECTORTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VECTOR_DOUBLE__1615884564, PogoDslKeyword.	VECTOR_DOUBLE__1615884564)
		);
		


		b.rule(STRINGVECTORTYPE).is(
		b.isOneOfThem(PogoDslKeyword.	VECTOR_STRING__2110843692, PogoDslKeyword.	VECTOR_STRING__2110843692)
		);
		

	}
}
