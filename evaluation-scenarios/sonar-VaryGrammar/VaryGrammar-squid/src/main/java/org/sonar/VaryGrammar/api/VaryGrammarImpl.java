package org.sonar.VaryGrammar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.VaryGrammar.VaryGrammarConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.NUMBER;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.STRING;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.CHARACTER;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.SL_COMMENT;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.NOMBRE_FUN;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.CAD;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.CAR;
import static org.sonar.VaryGrammar.api.VaryGrammarTokenType.COMENT;

public enum VaryGrammarImpl implements GrammarRuleKey {
	CODIGO,
	ALGORITMO,
	MODULO,
	IMPLEMENTACION,
	CABECERASUBPROCESO,
	CABECERAPROCEDIMIENTO,
	CABECERAFUNCION,
	TIPOCOMPLEJO,
	SUBPROCESO,
	COMENTARIO,
	SENTENCIAS,
	BLOQUE,
	DECLARACION,
	FUNCIONFICHEROABRIR,
	FUNCIONFICHEROCERRAR,
	MODOAPERTURA,
	NOMBREINTERNA,
	EINT,
	EFLOAT,
	TIPO,
	TIPODEFINIDO,
	TIPOEXISTENTE,
	CONSTANTES,
	VECTOR,
	MATRIZ,
	REGISTRO,
	ARCHIVO,
	ENUMERADO,
	SUBRANGO,
	SUBRANGONUMERICO,
	SUBRANGOENUMERADO,
	INICIO,
	ESTRING,
	DECLARACIONVARIABLE,
	DECLARACIONPROPIA,
	ASIGNACION,
	ASIGNACIONNORMAL,
	ASIGNACIONCOMPLEJA,
	ESCRIBIR,
	LEER,
	SI,
	MIENTRAS,
	REPETIR,
	DESDE,
	CASO,
	SEGUN,
	TIPOVARIABLE,
	VARIABLE,
	SIGNOOR,
	SIGNOAND,
	SIGNOIGUALDAD,
	SIGNOCOMPARACION,
	SIGNOSUMA,
	SIGNORESTA,
	SIGNOMULTIPLICACION,
	SIGNODIVISION,
	SIGNOMODULO,
	SIGNODIV,
	SIGNONO,
	OPERACION,
	OR,
	AND,
	MOD,
	IGUALDAD,
	COMPARACION,
	SUMARESTA,
	MULTIPLICACIONDIVISION,
	OPERACIONINDICE,
	ORINDICE,
	ANDINDICE,
	IGUALDADINDICE,
	COMPARACIONINDICE,
	SUMARESTAINDICE,
	MULTIPLICACIONDIVISIONINDICE,
	PRIMARIAINDICE,
	PRIMARIA,
	OPERACIONCOMPLETA,
	OPERACIONPARENTESIS,
	FUNCIONES,
	CAMPOREGISTRO,
	VARIABLESCOMPLEJAS,
	VARIABLESBASICAS,
	SINO,
	DEVOLVER,
	PARAMETROFUNCION,
	FUNCION,
	PROCEDIMIENTO,
	TIPOPASO,
	BOOLEANO;

	public static final Logger LOG = LoggerFactory.getLogger("VaryGrammarImpl");

	public static Grammar create(VaryGrammarConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(CODIGO);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {
		b.rule(SL_COMMENT).is(b.isOneOfThem(STRING, STRING));
		b.rule(NOMBRE_FUN).is(b.isOneOfThem(STRING, STRING));
		b.rule(CAD).is(b.isOneOfThem(STRING, STRING));
		b.rule(CAR).is(b.isOneOfThem(STRING, STRING));
		b.rule(COMENT).is(b.isOneOfThem(STRING, STRING));

		b.rule(CODIGO).is(
		b.firstOf(
		ALGORITMO,
		MODULO
		)).skipIfOneChild();
		
		


		b.rule(ALGORITMO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	ALGORITMO_2065509140, VaryGrammarKeyword.	ALGORITMO_2065509140),
		b.isOneOfThem(STRING, STRING),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	IMPORTA_1926037852, VaryGrammarKeyword.	IMPORTA_1926037852),
		b.zeroOrMore(com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_IMPORTA_973273384, VaryGrammarKeyword.	FIN_IMPORTA_973273384)),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	CONST_94844771, VaryGrammarKeyword.	CONST_94844771),
		b.zeroOrMore(CONSTANTES)),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	TIPO_3560244, VaryGrammarKeyword.	TIPO_3560244),
		b.zeroOrMore(TIPOCOMPLEJO)),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	VAR_116519, VaryGrammarKeyword.	VAR_116519),
		b.zeroOrMore(DECLARACION)),
		b.zeroOrMore(SUBPROCESO),
		INICIO,
		b.isOneOfThem(VaryGrammarKeyword.	FIN_ALGORITMO_553129512, VaryGrammarKeyword.	FIN_ALGORITMO_553129512)
		));
		


		b.rule(MODULO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	MODULO_1984916842, VaryGrammarKeyword.	MODULO_1984916842),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(VaryGrammarKeyword.	IMPORTA_1926037852, VaryGrammarKeyword.	IMPORTA_1926037852),
		b.zeroOrMore(com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_IMPORTA_973273384, VaryGrammarKeyword.	FIN_IMPORTA_973273384),
		b.isOneOfThem(VaryGrammarKeyword.	EXPORTA_1309056211, VaryGrammarKeyword.	EXPORTA_1309056211),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	CONST_94844771, VaryGrammarKeyword.	CONST_94844771),
		b.zeroOrMore(b.isOneOfThem(STRING, STRING))),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	TIPO_3560244, VaryGrammarKeyword.	TIPO_3560244),
		b.zeroOrMore(b.isOneOfThem(STRING, STRING))),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	VAR_116519, VaryGrammarKeyword.	VAR_116519),
		b.zeroOrMore(DECLARACION)),
		b.zeroOrMore(CABECERASUBPROCESO),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_EXPORTA_2033146617, VaryGrammarKeyword.	FIN_EXPORTA_2033146617),
		IMPLEMENTACION,
		b.isOneOfThem(VaryGrammarKeyword.	FIN_MODULO_701596970, VaryGrammarKeyword.	FIN_MODULO_701596970)
		));
		


		b.rule(IMPLEMENTACION).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	IMPLEMENTACION_1682829667, VaryGrammarKeyword.	IMPLEMENTACION_1682829667),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	CONST_94844771, VaryGrammarKeyword.	CONST_94844771),
		b.zeroOrMore(CONSTANTES)),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	TIPO_3560244, VaryGrammarKeyword.	TIPO_3560244),
		b.zeroOrMore(TIPOCOMPLEJO)),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	VAR_116519, VaryGrammarKeyword.	VAR_116519),
		b.zeroOrMore(DECLARACION)),
		b.zeroOrMore(SUBPROCESO),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_IMPLEMENTACION_288611351, VaryGrammarKeyword.	FIN_IMPLEMENTACION_288611351)
		));
		


		b.rule(CABECERASUBPROCESO).is(
		b.firstOf(
		CABECERAPROCEDIMIENTO,
		CABECERAFUNCION
		)).skipIfOneChild();
		
		


		b.rule(CABECERAPROCEDIMIENTO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	PROCEDIMIENTO_1398395412, VaryGrammarKeyword.	PROCEDIMIENTO_1398395412),
		NOMBRE_FUN,
		b.optional(
		PARAMETROFUNCION,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		PARAMETROFUNCION)),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(CABECERAFUNCION).is(
		b.sequence(		TIPOVARIABLE,
		b.isOneOfThem(VaryGrammarKeyword.	FUNCION_509653308, VaryGrammarKeyword.	FUNCION_509653308),
		NOMBRE_FUN,
		b.optional(
		PARAMETROFUNCION,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		PARAMETROFUNCION)),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(TIPOCOMPLEJO).is(
		b.firstOf(
		VECTOR,
		MATRIZ,
		REGISTRO,
		ARCHIVO,
		ENUMERADO,
		SUBRANGO
		)).skipIfOneChild();
		
		


		b.rule(SUBPROCESO).is(
		b.firstOf(
		FUNCION,
		PROCEDIMIENTO
		)).skipIfOneChild();
		
		


		b.rule(COMENTARIO).is(
		b.sequence(		SL_COMMENT,
		COMENT
		));
		


		b.rule(SENTENCIAS).is(
		b.firstOf(
		ESCRIBIR,
		LEER,
		BLOQUE,
		FUNCIONFICHEROABRIR,
		FUNCIONFICHEROCERRAR,
		ASIGNACION,
		FUNCIONES
		)).skipIfOneChild();
		
		


		b.rule(BLOQUE).is(
		b.firstOf(
		SI,
		MIENTRAS,
		REPETIR,
		DESDE,
		SEGUN
		)).skipIfOneChild();
		
		


		b.rule(DECLARACION).is(
		b.firstOf(
		DECLARACIONVARIABLE,
		DECLARACIONPROPIA
		)).skipIfOneChild();
		
		


		b.rule(FUNCIONFICHEROABRIR).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	ABRIR__1423933938, VaryGrammarKeyword.	ABRIR__1423933938),
		PRIMARIA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		MODOAPERTURA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		PRIMARIA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(FUNCIONFICHEROCERRAR).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	CERRAR__668869813, VaryGrammarKeyword.	CERRAR__668869813),
		PRIMARIA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(MODOAPERTURA).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	ESCRITURA_1723234524, VaryGrammarKeyword.	ESCRITURA_1723234524),
		b.isOneOfThem(VaryGrammarKeyword.	LECTURA_52694394, VaryGrammarKeyword.	LECTURA_52694394)
		)).skipIfOneChild();
		
		


		b.rule(NOMBREINTERNA).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	COS__3059585, VaryGrammarKeyword.	COS__3059585),
		b.isOneOfThem(VaryGrammarKeyword.	CUADRADO__1934256775, VaryGrammarKeyword.	CUADRADO__1934256775),
		b.isOneOfThem(VaryGrammarKeyword.	EXP__3127723, VaryGrammarKeyword.	EXP__3127723),
		b.isOneOfThem(VaryGrammarKeyword.	LN__107238, VaryGrammarKeyword.	LN__107238),
		b.isOneOfThem(VaryGrammarKeyword.	LOG__3327332, VaryGrammarKeyword.	LOG__3327332),
		b.isOneOfThem(VaryGrammarKeyword.	SEN__3526476, VaryGrammarKeyword.	SEN__3526476),
		b.isOneOfThem(VaryGrammarKeyword.	SQRT__109684488, VaryGrammarKeyword.	SQRT__109684488),
		b.isOneOfThem(VaryGrammarKeyword.	LONGITUD__137365874, VaryGrammarKeyword.	LONGITUD__137365874),
		b.isOneOfThem(VaryGrammarKeyword.	CONCATENA__2047516828, VaryGrammarKeyword.	CONCATENA__2047516828),
		b.isOneOfThem(VaryGrammarKeyword.	COPIAR__953046162, VaryGrammarKeyword.	COPIAR__953046162)
		)).skipIfOneChild();
		
		

		

		


		b.rule(TIPO).is(
		b.firstOf(
		TIPODEFINIDO,
		TIPOEXISTENTE
		)).skipIfOneChild();
		
		

		b.rule(TIPODEFINIDO).is(b.isOneOfThem(STRING, STRING));
		

		b.rule(TIPOEXISTENTE).is(TIPOVARIABLE);
		


		b.rule(CONSTANTES).is(
		b.sequence(		VARIABLE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK61, VaryGrammarPunctuator.	SK61),
		PRIMARIA
		));
		


		b.rule(VECTOR).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	VECTOR_820387517, VaryGrammarKeyword.	VECTOR_820387517),
		b.isOneOfThem(VaryGrammarPunctuator.	SK91, VaryGrammarPunctuator.	SK91),
		PRIMARIA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK93, VaryGrammarPunctuator.	SK93),
		b.isOneOfThem(VaryGrammarKeyword.	DE_3201, VaryGrammarKeyword.	DE_3201),
		TIPO,
		b.isOneOfThem(VaryGrammarPunctuator.	SK5832, VaryGrammarPunctuator.	SK5832),
		b.isOneOfThem(STRING, STRING)
		));
		


		b.rule(MATRIZ).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	MATRIZ_1081239613, VaryGrammarKeyword.	MATRIZ_1081239613),
		b.isOneOfThem(VaryGrammarPunctuator.	SK91, VaryGrammarPunctuator.	SK91),
		PRIMARIA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK9391, VaryGrammarPunctuator.	SK9391),
		PRIMARIA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK93, VaryGrammarPunctuator.	SK93),
		b.isOneOfThem(VaryGrammarKeyword.	DE_3201, VaryGrammarKeyword.	DE_3201),
		TIPO,
		b.isOneOfThem(VaryGrammarPunctuator.	SK5832, VaryGrammarPunctuator.	SK5832),
		b.isOneOfThem(STRING, STRING)
		));
		


		b.rule(REGISTRO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	REGISTRO__78239335, VaryGrammarKeyword.	REGISTRO__78239335),
		b.isOneOfThem(STRING, STRING),
		b.oneOrMore(DECLARACION),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_REGISTRO_161140249, VaryGrammarKeyword.	FIN_REGISTRO_161140249)
		));
		


		b.rule(ARCHIVO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	ARCHIVO_DE__1061399147, VaryGrammarKeyword.	ARCHIVO_DE__1061399147),
		TIPO,
		b.isOneOfThem(VaryGrammarPunctuator.	SK5832, VaryGrammarPunctuator.	SK5832),
		b.isOneOfThem(STRING, STRING)
		));
		


		b.rule(ENUMERADO).is(
		b.sequence(		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK61, VaryGrammarPunctuator.	SK61),
		b.isOneOfThem(VaryGrammarPunctuator.	SK123, VaryGrammarPunctuator.	SK123),
		PRIMARIA,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		PRIMARIA),
		b.isOneOfThem(VaryGrammarPunctuator.	SK125, VaryGrammarPunctuator.	SK125)
		));
		


		b.rule(SUBRANGO).is(
		b.firstOf(
		SUBRANGONUMERICO,
		SUBRANGOENUMERADO
		)).skipIfOneChild();
		
		


		b.rule(SUBRANGONUMERICO).is(
		b.sequence(		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK61, VaryGrammarPunctuator.	SK61),
		b.isOneOfThem(NUMBER, NUMBER),
		b.isOneOfThem(VaryGrammarPunctuator.	SK4646, VaryGrammarPunctuator.	SK4646),
		b.isOneOfThem(NUMBER, NUMBER)
		));
		


		b.rule(SUBRANGOENUMERADO).is(
		b.sequence(		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK61, VaryGrammarPunctuator.	SK61),
		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK4646, VaryGrammarPunctuator.	SK4646),
		b.isOneOfThem(STRING, STRING)
		));
		


		b.rule(INICIO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	PRINCIPAL_1812041682, VaryGrammarKeyword.	PRINCIPAL_1812041682),
		b.isOneOfThem(VaryGrammarKeyword.	VAR_116519, VaryGrammarKeyword.	VAR_116519),
		b.zeroOrMore(DECLARACION),
		b.isOneOfThem(VaryGrammarKeyword.	INICIO_1184092571, VaryGrammarKeyword.	INICIO_1184092571),
		b.zeroOrMore(SENTENCIAS),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_PRINCIPAL_1704305914, VaryGrammarKeyword.	FIN_PRINCIPAL_1704305914)
		));
		

		


		b.rule(DECLARACIONVARIABLE).is(
		b.sequence(		TIPOVARIABLE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK5832, VaryGrammarPunctuator.	SK5832),
		VARIABLE,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		VARIABLE
		)
		));
		


		b.rule(DECLARACIONPROPIA).is(
		b.sequence(		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK5832, VaryGrammarPunctuator.	SK5832),
		VARIABLE,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		VARIABLE
		)
		));
		


		b.rule(ASIGNACION).is(
		b.firstOf(
		ASIGNACIONNORMAL,
		ASIGNACIONCOMPLEJA
		)).skipIfOneChild();
		
		


		b.rule(ASIGNACIONNORMAL).is(
		b.sequence(		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK6045, VaryGrammarPunctuator.	SK6045),
		OPERACIONCOMPLETA
		));
		


		b.rule(ASIGNACIONCOMPLEJA).is(
		b.sequence(		VARIABLESCOMPLEJAS,
		b.isOneOfThem(VaryGrammarPunctuator.	SK6045, VaryGrammarPunctuator.	SK6045),
		OPERACIONCOMPLETA
		));
		


		b.rule(ESCRIBIR).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	ESCRIBIR__1722686697, VaryGrammarKeyword.	ESCRIBIR__1722686697),
		PRIMARIA,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		OPERACIONCOMPLETA),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(LEER).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	LEER__102849794, VaryGrammarKeyword.	LEER__102849794),
		PRIMARIA,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		PRIMARIA),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(SI).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	SI_3670, VaryGrammarKeyword.	SI_3670),
		b.isOneOfThem(VaryGrammarPunctuator.	SK40, VaryGrammarPunctuator.	SK40),
		b.isOneOfThem(VaryGrammarKeyword.	SI__113810, VaryGrammarKeyword.	SI__113810)),
		OPERACIONCOMPLETA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41),
		b.isOneOfThem(VaryGrammarKeyword.	ENTONCES_2096757753, VaryGrammarKeyword.	ENTONCES_2096757753),
		b.zeroOrMore(SENTENCIAS),
		b.optional(
		DEVOLVER),
		b.optional(
		SINO),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_SI_1274452214, VaryGrammarKeyword.	FIN_SI_1274452214)
		));
		


		b.rule(MIENTRAS).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	MIENTRAS_1611895819, VaryGrammarKeyword.	MIENTRAS_1611895819),
		b.isOneOfThem(VaryGrammarPunctuator.	SK40, VaryGrammarPunctuator.	SK40),
		b.isOneOfThem(VaryGrammarKeyword.	MIENTRAS__1570837203, VaryGrammarKeyword.	MIENTRAS__1570837203)),
		OPERACIONCOMPLETA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41),
		b.isOneOfThem(VaryGrammarKeyword.	HACER_99034295, VaryGrammarKeyword.	HACER_99034295),
		b.zeroOrMore(SENTENCIAS),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_MIENTRAS_1082823255, VaryGrammarKeyword.	FIN_MIENTRAS_1082823255)
		));
		


		b.rule(REPETIR).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	REPETIR_1094306869, VaryGrammarKeyword.	REPETIR_1094306869),
		b.zeroOrMore(SENTENCIAS),
		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	HASTA_QUE_654421257, VaryGrammarKeyword.	HASTA_QUE_654421257),
		b.isOneOfThem(VaryGrammarPunctuator.	SK40, VaryGrammarPunctuator.	SK40),
		b.isOneOfThem(VaryGrammarKeyword.	HASTA_QUE__1187777473, VaryGrammarKeyword.	HASTA_QUE__1187777473)),
		OPERACIONCOMPLETA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(DESDE).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	DESDE_95474707, VaryGrammarKeyword.	DESDE_95474707),
		ASIGNACIONNORMAL,
		b.isOneOfThem(VaryGrammarKeyword.	HASTA_99050119, VaryGrammarKeyword.	HASTA_99050119),
		OPERACIONCOMPLETA,
		b.isOneOfThem(VaryGrammarKeyword.	HACER_99034295, VaryGrammarKeyword.	HACER_99034295),
		b.zeroOrMore(SENTENCIAS),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_DESDE_291131103, VaryGrammarKeyword.	FIN_DESDE_291131103)
		));
		


		b.rule(CASO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	CASO_3046202, VaryGrammarKeyword.	CASO_3046202),
		PRIMARIA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK5832, VaryGrammarPunctuator.	SK5832),
		b.zeroOrMore(SENTENCIAS),
		b.optional(
		DEVOLVER)
		));
		


		b.rule(SEGUN).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	SEGUN_SEA_1390907842, VaryGrammarKeyword.	SEGUN_SEA_1390907842),
		b.isOneOfThem(VaryGrammarPunctuator.	SK40, VaryGrammarPunctuator.	SK40),
		b.isOneOfThem(VaryGrammarKeyword.	SEGUN_SEA__168470102, VaryGrammarKeyword.	SEGUN_SEA__168470102)),
		OPERACIONCOMPLETA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41),
		b.isOneOfThem(VaryGrammarKeyword.	HACER_99034295, VaryGrammarKeyword.	HACER_99034295),
		b.zeroOrMore(CASO),
		b.isOneOfThem(VaryGrammarKeyword.	EN_OTRO_CASO__1651095047, VaryGrammarKeyword.	EN_OTRO_CASO__1651095047),
		b.zeroOrMore(SENTENCIAS),
		b.optional(
		DEVOLVER),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_SEGUN_304972922, VaryGrammarKeyword.	FIN_SEGUN_304972922)
		));
		


		b.rule(TIPOVARIABLE).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	ENTERO_1298279273, VaryGrammarKeyword.	ENTERO_1298279273),
		b.isOneOfThem(VaryGrammarKeyword.	CARACTER_10556053, VaryGrammarKeyword.	CARACTER_10556053),
		b.isOneOfThem(VaryGrammarKeyword.	REAL_3496350, VaryGrammarKeyword.	REAL_3496350),
		b.isOneOfThem(VaryGrammarKeyword.	LOGICO_1097335599, VaryGrammarKeyword.	LOGICO_1097335599),
		b.isOneOfThem(VaryGrammarKeyword.	CADENA_1368020142, VaryGrammarKeyword.	CADENA_1368020142)
		)).skipIfOneChild();
		
		

		b.rule(VARIABLE).is(b.isOneOfThem(STRING, STRING));
		


		b.rule(SIGNOOR).is(
		b.isOneOfThem(VaryGrammarKeyword.	O_111, VaryGrammarKeyword.	O_111)
		);
		


		b.rule(SIGNOAND).is(
		b.isOneOfThem(VaryGrammarKeyword.	Y_121, VaryGrammarKeyword.	Y_121)
		);
		


		b.rule(SIGNOIGUALDAD).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarPunctuator.	SK61, VaryGrammarPunctuator.	SK61),
		b.isOneOfThem(VaryGrammarPunctuator.	SK3361, VaryGrammarPunctuator.	SK3361)
		)).skipIfOneChild();
		
		


		b.rule(SIGNOCOMPARACION).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarPunctuator.	SK60, VaryGrammarPunctuator.	SK60),
		b.isOneOfThem(VaryGrammarPunctuator.	SK62, VaryGrammarPunctuator.	SK62),
		b.isOneOfThem(VaryGrammarPunctuator.	SK6261, VaryGrammarPunctuator.	SK6261),
		b.isOneOfThem(VaryGrammarPunctuator.	SK6061, VaryGrammarPunctuator.	SK6061)
		)).skipIfOneChild();
		
		


		b.rule(SIGNOSUMA).is(
		b.isOneOfThem(VaryGrammarPunctuator.	SK43, VaryGrammarPunctuator.	SK43)
		);
		


		b.rule(SIGNORESTA).is(
		b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)
		);
		


		b.rule(SIGNOMULTIPLICACION).is(
		b.isOneOfThem(VaryGrammarPunctuator.	SK42, VaryGrammarPunctuator.	SK42)
		);
		


		b.rule(SIGNODIVISION).is(
		b.isOneOfThem(VaryGrammarPunctuator.	SK47, VaryGrammarPunctuator.	SK47)
		);
		


		b.rule(SIGNOMODULO).is(
		b.isOneOfThem(VaryGrammarKeyword.	MOD_108290, VaryGrammarKeyword.	MOD_108290)
		);
		


		b.rule(SIGNODIV).is(
		b.isOneOfThem(VaryGrammarKeyword.	DIV_99473, VaryGrammarKeyword.	DIV_99473)
		);
		


		b.rule(SIGNONO).is(
		b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)
		);
		

		b.rule(OPERACION).is(OR);
		


		b.rule(OR).is(
		b.sequence(		AND,
		b.zeroOrMore(
		SIGNOOR,
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		AND
		)
		));
		


		b.rule(AND).is(
		b.sequence(		MOD,
		b.zeroOrMore(
		SIGNOAND,
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		MOD
		)
		));
		


		b.rule(MOD).is(
		b.sequence(		IGUALDAD,
		b.zeroOrMore(
		SIGNOMODULO,
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		IGUALDAD
		)
		));
		


		b.rule(IGUALDAD).is(
		b.sequence(		COMPARACION,
		b.zeroOrMore(
		SIGNOIGUALDAD,
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		COMPARACION
		)
		));
		


		b.rule(COMPARACION).is(
		b.sequence(		SUMARESTA,
		b.zeroOrMore(
		SIGNOCOMPARACION,
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		SUMARESTA
		)
		));
		


		b.rule(SUMARESTA).is(
		b.sequence(		MULTIPLICACIONDIVISION,
		b.zeroOrMore(
		SIGNOSUMA,
		SIGNORESTA,
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		MULTIPLICACIONDIVISION
		)
		));
		


		b.rule(MULTIPLICACIONDIVISION).is(
		b.sequence(		PRIMARIA,
		b.zeroOrMore(
		SIGNOMULTIPLICACION,
		SIGNODIVISION,
		SIGNODIV,
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		PRIMARIA
		)
		));
		

		b.rule(OPERACIONINDICE).is(ORINDICE);
		


		b.rule(ORINDICE).is(
		b.sequence(		ANDINDICE,
		b.zeroOrMore(
		SIGNOOR,
		ANDINDICE
		)
		));
		


		b.rule(ANDINDICE).is(
		b.sequence(		IGUALDADINDICE,
		b.zeroOrMore(
		SIGNOAND,
		IGUALDADINDICE
		)
		));
		


		b.rule(IGUALDADINDICE).is(
		b.sequence(		COMPARACIONINDICE,
		b.zeroOrMore(
		SIGNOIGUALDAD,
		COMPARACIONINDICE
		)
		));
		


		b.rule(COMPARACIONINDICE).is(
		b.sequence(		SUMARESTAINDICE,
		b.zeroOrMore(
		SIGNOCOMPARACION,
		SUMARESTAINDICE
		)
		));
		


		b.rule(SUMARESTAINDICE).is(
		b.sequence(		MULTIPLICACIONDIVISIONINDICE,
		b.zeroOrMore(
		SIGNOSUMA,
		SIGNORESTA,
		MULTIPLICACIONDIVISIONINDICE
		)
		));
		


		b.rule(MULTIPLICACIONDIVISIONINDICE).is(
		b.sequence(		PRIMARIAINDICE,
		b.zeroOrMore(
		SIGNOMULTIPLICACION,
		SIGNODIVISION,
		SIGNODIV,
		PRIMARIAINDICE
		)
		));
		


		b.rule(PRIMARIAINDICE).is(
		b.firstOf(
		b.isOneOfThem(NUMBER, NUMBER),
		b.isOneOfThem(STRING, STRING)
		)).skipIfOneChild();
		
		


		b.rule(PRIMARIA).is(
		b.firstOf(
		VARIABLESBASICAS,
		VARIABLESCOMPLEJAS,
		FUNCIONES,
		OPERACIONPARENTESIS
		)).skipIfOneChild();
		
		


		b.rule(OPERACIONCOMPLETA).is(
		b.sequence(		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		OPERACION
		));
		


		b.rule(OPERACIONPARENTESIS).is(
		b.sequence(		b.isOneOfThem(VaryGrammarPunctuator.	SK40, VaryGrammarPunctuator.	SK40),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarPunctuator.	SK45, VaryGrammarPunctuator.	SK45)),
		b.zeroOrMore(b.isOneOfThem(VaryGrammarKeyword.	NO_3521, VaryGrammarKeyword.	NO_3521)),
		OPERACION,
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		));
		


		b.rule(FUNCIONES).is(
		b.firstOf(
		NOMBRE_FUN,
		b.optional(
		OPERACIONCOMPLETA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		OPERACIONCOMPLETA),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41),
		NOMBREINTERNA,
		b.optional(
		OPERACIONCOMPLETA,
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		OPERACIONCOMPLETA),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41)
		)).skipIfOneChild();
		
		


		b.rule(CAMPOREGISTRO).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarPunctuator.	SK46, VaryGrammarPunctuator.	SK46),
		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK46, VaryGrammarPunctuator.	SK46),
		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK91, VaryGrammarPunctuator.	SK91),
		OPERACIONINDICE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK93, VaryGrammarPunctuator.	SK93),
		b.isOneOfThem(VaryGrammarPunctuator.	SK46, VaryGrammarPunctuator.	SK46),
		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK91, VaryGrammarPunctuator.	SK91),
		OPERACIONINDICE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK9391, VaryGrammarPunctuator.	SK9391),
		OPERACIONINDICE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK93, VaryGrammarPunctuator.	SK93)
		)).skipIfOneChild();
		
		


		b.rule(VARIABLESCOMPLEJAS).is(
		b.firstOf(
		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK91, VaryGrammarPunctuator.	SK91),
		OPERACIONINDICE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK93, VaryGrammarPunctuator.	SK93),
		b.zeroOrMore(CAMPOREGISTRO),
		b.isOneOfThem(STRING, STRING),
		b.isOneOfThem(VaryGrammarPunctuator.	SK91, VaryGrammarPunctuator.	SK91),
		OPERACIONINDICE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK9391, VaryGrammarPunctuator.	SK9391),
		OPERACIONINDICE,
		b.isOneOfThem(VaryGrammarPunctuator.	SK93, VaryGrammarPunctuator.	SK93),
		b.zeroOrMore(CAMPOREGISTRO),
		b.isOneOfThem(STRING, STRING),
		b.oneOrMore(CAMPOREGISTRO)
		)).skipIfOneChild();
		
		


		b.rule(VARIABLESBASICAS).is(
		b.firstOf(
		b.isOneOfThem(NUMBER, NUMBER),
		b.isOneOfThem(NUMBER, NUMBER),
		CAD,
		CAR,
		BOOLEANO,
		b.isOneOfThem(STRING, STRING)
		)).skipIfOneChild();
		
		


		b.rule(SINO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	SI_NO_109427786, VaryGrammarKeyword.	SI_NO_109427786),
		b.optional(
		SENTENCIAS,
		b.zeroOrMore(SENTENCIAS)),
		b.optional(
		DEVOLVER)
		));
		


		b.rule(DEVOLVER).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	DEVOLVER_1115017649, VaryGrammarKeyword.	DEVOLVER_1115017649),
		OPERACIONCOMPLETA
		));
		


		b.rule(PARAMETROFUNCION).is(
		b.sequence(		TIPOPASO,
		TIPO,
		b.isOneOfThem(VaryGrammarPunctuator.	SK5832, VaryGrammarPunctuator.	SK5832),
		VARIABLE
		));
		


		b.rule(FUNCION).is(
		b.sequence(		TIPOVARIABLE,
		b.isOneOfThem(VaryGrammarKeyword.	FUNCION_509653308, VaryGrammarKeyword.	FUNCION_509653308),
		NOMBRE_FUN,
		b.optional(
		PARAMETROFUNCION,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		PARAMETROFUNCION)),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	VAR_116519, VaryGrammarKeyword.	VAR_116519),
		b.zeroOrMore(DECLARACION)),
		b.isOneOfThem(VaryGrammarKeyword.	INICIO_1184092571, VaryGrammarKeyword.	INICIO_1184092571),
		b.zeroOrMore(SENTENCIAS),
		b.optional(
		DEVOLVER),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_FUNCION_1462417776, VaryGrammarKeyword.	FIN_FUNCION_1462417776)
		));
		


		b.rule(PROCEDIMIENTO).is(
		b.sequence(		b.isOneOfThem(VaryGrammarKeyword.	PROCEDIMIENTO_1398395412, VaryGrammarKeyword.	PROCEDIMIENTO_1398395412),
		NOMBRE_FUN,
		b.optional(
		PARAMETROFUNCION,
		b.zeroOrMore(
		b.isOneOfThem(VaryGrammarPunctuator.	SK44, VaryGrammarPunctuator.	SK44),
		PARAMETROFUNCION)),
		b.isOneOfThem(VaryGrammarPunctuator.	SK41, VaryGrammarPunctuator.	SK41),
		b.optional(
		b.isOneOfThem(VaryGrammarKeyword.	VAR_116519, VaryGrammarKeyword.	VAR_116519),
		b.zeroOrMore(DECLARACION)),
		b.isOneOfThem(VaryGrammarKeyword.	INICIO_1184092571, VaryGrammarKeyword.	INICIO_1184092571),
		b.zeroOrMore(SENTENCIAS),
		b.isOneOfThem(VaryGrammarKeyword.	FIN_PROCEDIMIENTO_1769062624, VaryGrammarKeyword.	FIN_PROCEDIMIENTO_1769062624)
		));
		


		b.rule(TIPOPASO).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	E_69, VaryGrammarKeyword.	E_69),
		b.isOneOfThem(VaryGrammarKeyword.	E_S_67849, VaryGrammarKeyword.	E_S_67849),
		b.isOneOfThem(VaryGrammarKeyword.	S_83, VaryGrammarKeyword.	S_83)
		)).skipIfOneChild();
		
		


		b.rule(BOOLEANO).is(
		b.firstOf(
		b.isOneOfThem(VaryGrammarKeyword.	VERDADERO_1844263458, VaryGrammarKeyword.	VERDADERO_1844263458),
		b.isOneOfThem(VaryGrammarKeyword.	FALSO_97196333, VaryGrammarKeyword.	FALSO_97196333)
		)).skipIfOneChild();
		
		

	}
}
