package org.sonar.PogoDsl.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum PogoDslKeyword implements TokenType, GrammarRuleKey {
		
		CPP_67971("Cpp"),
		DEVVARLONG64ARRAY_1444597267("DevVarLong64Array"),
		USHORT_835943129("ushort"),
		IMAGE_70760763("Image"),
		FLOAT_97526364("float"),
		READ_WRITE_1247349718("READ_WRITE"),
		CLASSES__692440276("classes:"),
		EXPERT_2059133482("EXPERT"),
		CONSTDEVSTRING_1097727869("ConstDevString"),
		FORWARDEDATTRIBUTES__1578292993("forwardedAttributes:"),
		STATES__1897139592("states:"),
		WRITEEXCLUDEDSTATES__2138848111("writeExcludedStates:"),
		READEXCLUDEDSTATES__489900840("readExcludedStates:"),
		COMMANDS__1498724686("commands:"),
		VECTOR_SHORT__79254565("vector<short>"),
		PYTHON_1889329924("Python"),
		DEVVARDOUBLESTRINGARRAY_1358582629("DevVarDoubleStringArray"),
		DEVICEPROPERTIES__1978568399("deviceProperties:"),
		VOID_3625364("void"),
		DYNAMICCOMMANDS__241771213("dynamicCommands:"),
		DOUBLE_1325958191("double"),
		DEFAULTPROPVALUE__1058706611("defaultPropValue:"),
		PARENTCLASSES__1347096674("parentClasses:"),
		DEVVARFLOATARRAY_189043183("DevVarFloatArray"),
		DEVLONG64_1210288783("DevLong64"),
		MULTICLASSES_1600372941("multiclasses"),
		INHERITANCES__1172267121("inheritances:"),
		READ_2511254("READ"),
		EXTENDS_1305664359("extends"),
		VECTOR_FLOAT__447755781("vector<float>"),
		CLASSPROPERTIES__117046479("classProperties:"),
		VECTOR_INT__549345544("vector<int>"),
		DEVENUM_1071574378("DevEnum"),
		TRUE_3569038("true"),
		DEVUCHAR_1154415222("DevUChar"),
		EXCLUDEDSTATES__1299477838("excludedStates:"),
		DEVVARSTRINGARRAY_1949467018("DevVarStringArray"),
		ENUMLABELS__325332954("enumLabels:"),
		VECTOR_LONG__147361399("vector<long>"),
		DEVVARUSHORTARRAY_964562112("DevVarUShortArray"),
		IMPORT_1184795739("import"),
		DESCRIPTION__1921320002("description:"),
		ATTRIBUTES__309886525("attributes:"),
		READ_WITH_WRITE_1854638415("READ_WITH_WRITE"),
		STRING_891985903("string"),
		VECTOR_DOUBLE__1615884564("vector<double>"),
		PIPES__988095403("pipes:"),
		SCALAR_1824322548("Scalar"),
		SPECTRUM_2067891215("Spectrum"),
		WRITE_82862015("WRITE"),
		DEVVARLONGARRAY_832894571("DevVarLongArray"),
		OVERLODEDPOLLPERIODOBJECT__155207569("overlodedPollPeriodObject:"),
		DEVVARDOUBLEARRAY_2056865098("DevVarDoubleArray"),
		DEVSTATE_1154021788("DevState"),
		DEVICECLASS_1539436318("deviceclass"),
		DEVVARULONGARRAY_1820742874("DevVarULongArray"),
		JAVA_2301506("Java"),
		DYNAMICATTRIBUTES__736590852("dynamicAttributes:"),
		DEVULONG64_1555971418("DevULong64"),
		FALSE_97196323("false"),
		ABSTRACT_1732898850("abstract"),
		UINT_3589978("uint"),
		ADDITIONALFILES__87588950("additionalFiles:"),
		INT_104431("int"),
		VECTOR_ULONG__18306426("vector<ulong>"),
		DEVENCODED_1203151705("DevEncoded"),
		DEVVARLONGSTRINGARRAY_853003430("DevVarLongStringArray"),
		BOOLEAN_64711720("boolean"),
		OPERATOR_282073252("OPERATOR"),
		DEVVARBOOLEANARRAY_933778653("DevVarBooleanArray"),
		DEVVARULONG64ARRAY_1310150500("DevVarULong64Array"),
		DEVINT_2043646906("DevInt"),
		DEVVARCHARARRAY_2027347503("DevVarCharArray"),
		DEVVARSHORTARRAY_1523088433("DevVarShortArray"),
		SHORT_109413500("short"),
		KEYWORDS__1878375952("keyWords:"),
		VECTOR_STRING__2110843692("vector<string>"),
;

  private final String value;

  private PogoDslKeyword(String value) {
    this.value = value;
  }

  public String getName() {
    return name();
  }

  public String getValue() {
    return value;
  }

  public boolean hasToBeSkippedFromAst(AstNode node) {
    return false;
  }

  public static String[] keywordValues() {
    PogoDslKeyword[] keywordsEnum = PogoDslKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
