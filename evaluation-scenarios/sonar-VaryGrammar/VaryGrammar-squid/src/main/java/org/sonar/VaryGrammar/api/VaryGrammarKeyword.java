package org.sonar.VaryGrammar.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum VaryGrammarKeyword implements TokenType, GrammarRuleKey {
		
		HASTA_QUE_654421257("hasta_que"),
		ENTERO_1298279273("entero"),
		ALGORITMO_2065509140("Algoritmo"),
		MIENTRAS_1611895819("mientras"),
		CARACTER_10556053("caracter"),
		E_69("E"),
		FIN_FUNCION_1462417776("fin_funcion"),
		LOGICO_1097335599("logico"),
		ESCRIBIR__1722686697("escribir("),
		MATRIZ_1081239613("matriz"),
		S_83("S"),
		SI_3670("si"),
		FIN_SEGUN_304972922("fin_segun"),
		VECTOR_820387517("vector"),
		COPIAR__953046162("copiar("),
		LECTURA_52694394("lectura"),
		SEGUN_SEA__168470102("segun_sea("),
		ESCRITURA_1723234524("escritura"),
		EN_OTRO_CASO__1651095047("en_otro_caso:"),
		O_111("o"),
		Y_121("y"),
		DE_3201("de"),
		DIV_99473("div"),
		CONCATENA__2047516828("concatena("),
		SI__113810("si("),
		CONST_94844771("const"),
		REGISTRO__78239335("registro:"),
		SI_NO_109427786("si_no"),
		EXPORTA_1309056211("exporta"),
		FIN_MIENTRAS_1082823255("fin_mientras"),
		FIN_ALGORITMO_553129512("fin_algoritmo"),
		FALSO_97196333("falso"),
		LN__107238("ln("),
		FIN_MODULO_701596970("fin_modulo"),
		ENTONCES_2096757753("entonces"),
		TIPO_3560244("tipo"),
		FIN_SI_1274452214("fin_si"),
		MOD_108290("mod"),
		E_S_67849("E/S"),
		INICIO_1184092571("inicio"),
		REPETIR_1094306869("repetir"),
		HASTA_QUE__1187777473("hasta_que("),
		CASO_3046202("caso"),
		LEER__102849794("leer("),
		FIN_IMPLEMENTACION_288611351("fin_implementacion"),
		VAR_116519("var"),
		MODULO_1984916842("Modulo"),
		SEGUN_SEA_1390907842("segun_sea"),
		CADENA_1368020142("cadena"),
		ARCHIVO_DE__1061399147("archivo de "),
		FIN_IMPORTA_973273384("fin_importa"),
		IMPLEMENTACION_1682829667("implementacion"),
		ABRIR__1423933938("abrir("),
		LONGITUD__137365874("longitud("),
		PROCEDIMIENTO_1398395412("procedimiento"),
		HACER_99034295("hacer"),
		LOG__3327332("log("),
		FUNCION_509653308("funcion"),
		HASTA_99050119("hasta"),
		CERRAR__668869813("cerrar("),
		FIN_PRINCIPAL_1704305914("fin_principal"),
		MIENTRAS__1570837203("mientras("),
		SEN__3526476("sen("),
		SQRT__109684488("sqrt("),
		FIN_REGISTRO_161140249("fin_registro"),
		EXP__3127723("exp("),
		FIN_DESDE_291131103("fin_desde"),
		IMPORTA_1926037852("importa"),
		FIN_PROCEDIMIENTO_1769062624("fin_procedimiento"),
		DESDE_95474707("desde"),
		REAL_3496350("real"),
		COS__3059585("cos("),
		NO_3521("no"),
		DEVOLVER_1115017649("devolver"),
		CUADRADO__1934256775("cuadrado("),
		VERDADERO_1844263458("verdadero"),
		PRINCIPAL_1812041682("principal"),
		FIN_EXPORTA_2033146617("fin_exporta"),
;

  private final String value;

  private VaryGrammarKeyword(String value) {
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
    VaryGrammarKeyword[] keywordsEnum = VaryGrammarKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
