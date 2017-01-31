package org.sonar.Sculptordsl.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.Sculptordsl.SculptordslConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.NUMBER;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.STRING;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.CHARACTER;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.DSL_MAP_COLLECTION_TYPE;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.NOT;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.DELEGATE;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.OPPOSITE;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.REF;
import static org.sonar.Sculptordsl.api.SculptordslTokenType.OP;

public enum SculptordslImpl implements GrammarRuleKey {
	DSLHTTPMETHOD,
	DSLINHERITANCETYPE,
	DSLDISCRIMINATORTYPE,
	DSLCOLLECTIONTYPE,
	DSLVISIBILITY,
	DSLMODEL,
	DSLIMPORT,
	DSLAPPLICATION,
	DSLMODULE,
	DSLSERVICE,
	DSLRESOURCE,
	DSLCONSUMER,
	DSLSUBSCRIBE,
	DSLPUBLISH,
	DSLEVENT,
	DSLDOMAINOBJECTTYPEDELEMENT,
	DSLSERVICEOPERATION,
	DSLSERVICEOPERATIONDELEGATE,
	DSLSERVICEREPOSITORYOPTION,
	DSLSERVICEREPOSITORYOPERATIONOPTION,
	DSLRESOURCEOPERATION,
	DSLRESOURCEOPERATIONDELEGATE,
	DSLREPOSITORYOPERATION,
	DSLPARAMETER,
	DSLCOMPLEXTYPE,
	DSLSIMPLEDOMAINOBJECT,
	DSLDOMAINOBJECT,
	DSLENTITY,
	DSLVALUEOBJECT,
	DSLDOMAINEVENT,
	DSLCOMMANDEVENT,
	DSLTRAIT,
	DSLDOMAINOBJECTOPERATION,
	DSLDATATRANSFEROBJECT,
	DSLBASICTYPE,
	DSLATTRIBUTE,
	DSLREFERENCE,
	DSLDTOATTRIBUTE,
	DSLDTOREFERENCE,
	DSLOPPOSITEHOLDER,
	DSLREPOSITORY,
	DSLSERVICEDEPENDENCY,
	DSLDEPENDENCY,
	DSLENUM,
	DSLENUMATTRIBUTE,
	DSLENUMVALUE,
	DSLENUMPARAMETER,
	DSLANYPROPERTY,
	DSLPROPERTY,
	DSLDTOPROPERTY,
	DSLTYPE,
	DSLJAVAIDENTIFIER,
	DSLCHANNELIDENTIFIER,
	DSLTHROWSIDENTIFIER;

	public static final Logger LOG = LoggerFactory.getLogger("SculptordslImpl");

	public static Grammar create(SculptordslConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(DSLMODEL);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {
		b.rule(DSL_MAP_COLLECTION_TYPE).is(b.isOneOfThem(STRING, STRING));
		b.rule(NOT).is(b.isOneOfThem(STRING, STRING));
		b.rule(DELEGATE).is(b.isOneOfThem(STRING, STRING));
		b.rule(OPPOSITE).is(b.isOneOfThem(STRING, STRING));
		b.rule(REF).is(b.isOneOfThem(STRING, STRING));
		b.rule(OP).is(b.isOneOfThem(STRING, STRING));
		b.rule(DSLHTTPMETHOD).is(
			b.firstOf(b.isOneOfThem(SculptordslKeyword.	NONE_2433880, SculptordslKeyword.	NONE_2433880),
			b.isOneOfThem(SculptordslKeyword.	GET_70454, SculptordslKeyword.	GET_70454),
			b.isOneOfThem(SculptordslKeyword.	POST_2461856, SculptordslKeyword.	POST_2461856),
			b.isOneOfThem(SculptordslKeyword.	PUT_79599, SculptordslKeyword.	PUT_79599),
			b.isOneOfThem(SculptordslKeyword.	DELETE_2012838315, SculptordslKeyword.	DELETE_2012838315)
		));
		b.rule(DSLINHERITANCETYPE).is(
			b.firstOf(b.isOneOfThem(SculptordslKeyword.	JOINED_2101200055, SculptordslKeyword.	JOINED_2101200055),
			b.isOneOfThem(SculptordslKeyword.	SINGLE_TABLE_1089259767, SculptordslKeyword.	SINGLE_TABLE_1089259767)
		));
		b.rule(DSLDISCRIMINATORTYPE).is(
			b.firstOf(b.isOneOfThem(SculptordslKeyword.	STRING_1838656495, SculptordslKeyword.	STRING_1838656495),
			b.isOneOfThem(SculptordslKeyword.	CHAR_2067286, SculptordslKeyword.	CHAR_2067286),
			b.isOneOfThem(SculptordslKeyword.	INTEGER_1618932450, SculptordslKeyword.	INTEGER_1618932450)
		));
		b.rule(DSLCOLLECTIONTYPE).is(
			b.firstOf(b.isOneOfThem(SculptordslKeyword.	NONE_2433880, SculptordslKeyword.	NONE_2433880),
			b.isOneOfThem(SculptordslKeyword.	SET_83010, SculptordslKeyword.	SET_83010),
			b.isOneOfThem(SculptordslKeyword.	LIST_2368702, SculptordslKeyword.	LIST_2368702),
			b.isOneOfThem(SculptordslKeyword.	BAG_66536, SculptordslKeyword.	BAG_66536),
			b.isOneOfThem(SculptordslKeyword.	COLLECTION_252152510, SculptordslKeyword.	COLLECTION_252152510)
		));
		b.rule(DSLVISIBILITY).is(
			b.firstOf(b.isOneOfThem(SculptordslKeyword.	PUBLIC_977423767, SculptordslKeyword.	PUBLIC_977423767),
			b.isOneOfThem(SculptordslKeyword.	PROTECTED_608539730, SculptordslKeyword.	PROTECTED_608539730),
			b.isOneOfThem(SculptordslKeyword.	PRIVATE_314497661, SculptordslKeyword.	PRIVATE_314497661),
			b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458)
		));

		b.rule(DSLMODEL).is(
		b.sequence(		b.zeroOrMore(DSLIMPORT),
		DSLAPPLICATION
		));
		


		b.rule(DSLIMPORT).is(
		b.sequence(		b.isOneOfThem(SculptordslKeyword.	IMPORT_1184795739, SculptordslKeyword.	IMPORT_1184795739),
		STRING
		));
		


		b.rule(DSLAPPLICATION).is(
		b.sequence(		b.optional(
		STRING),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	APPLICATION_1072845520, SculptordslKeyword.	APPLICATION_1072845520),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.isOneOfThem(SculptordslKeyword.	BASEPACKAGE_1494394933, SculptordslKeyword.	BASEPACKAGE_1494394933),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER,
		b.isOneOfThem(SculptordslKeyword.	APPLICATIONPART_2050583875, SculptordslKeyword.	APPLICATIONPART_2050583875),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123)),
		b.zeroOrMore(DSLMODULE),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLMODULE).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	MODULE_1984916852, SculptordslKeyword.	MODULE_1984916852),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	EXTERNAL_1820761141, SculptordslKeyword.	EXTERNAL_1820761141)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	BASEPACKAGE_1494394933, SculptordslKeyword.	BASEPACKAGE_1494394933),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.firstOf(
		b.zeroOrMore(DSLSERVICE),
		b.zeroOrMore(DSLRESOURCE),
		b.zeroOrMore(DSLCONSUMER),
		b.zeroOrMore(DSLSIMPLEDOMAINOBJECT)),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLSERVICE).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	SERVICE_646160747, SculptordslKeyword.	SERVICE_646160747),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	WEBSERVICE_1201296609, SculptordslKeyword.	WEBSERVICE_1201296609)),
		b.optional(
		DSLSUBSCRIBE),
		b.zeroOrMore(DSLDEPENDENCY),
		b.zeroOrMore(DSLSERVICEOPERATION),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLRESOURCE).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	RESOURCE_276420562, SculptordslKeyword.	RESOURCE_276420562),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SCAFFOLD_897347594, SculptordslKeyword.	SCAFFOLD_897347594)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PATH_3433509, SculptordslKeyword.	PATH_3433509),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.zeroOrMore(DSLSERVICEDEPENDENCY),
		b.zeroOrMore(DSLRESOURCEOPERATION),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLCONSUMER).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	CONSUMER_503125994, SculptordslKeyword.	CONSUMER_503125994),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.zeroOrMore(DSLDEPENDENCY),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	UNMARSHALL_TO_524109588, SculptordslKeyword.	UNMARSHALL_TO_524109588),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.optional(
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	QUEUENAME_1739647172, SculptordslKeyword.	QUEUENAME_1739647172),
		b.isOneOfThem(SculptordslKeyword.	TOPICNAME_388205658, SculptordslKeyword.	TOPICNAME_388205658)),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLCHANNELIDENTIFIER),
		b.optional(
		DSLSUBSCRIBE),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLSUBSCRIBE).is(
		b.sequence(		b.isOneOfThem(SculptordslKeyword.	SUBSCRIBE_514841930, SculptordslKeyword.	SUBSCRIBE_514841930),
		b.isOneOfThem(SculptordslKeyword.	TO_3707, SculptordslKeyword.	TO_3707),
		DSLCHANNELIDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	EVENTBUS_278091142, SculptordslKeyword.	EVENTBUS_278091142),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER)
		));
		


		b.rule(DSLPUBLISH).is(
		b.sequence(		b.isOneOfThem(SculptordslKeyword.	PUBLISH_235365105, SculptordslKeyword.	PUBLISH_235365105),
		b.optional(
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.isOneOfThem(SculptordslKeyword.	TO_3707, SculptordslKeyword.	TO_3707),
		DSLCHANNELIDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	EVENTBUS_278091142, SculptordslKeyword.	EVENTBUS_278091142),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER)
		));
		


		b.rule(DSLEVENT).is(
		b.firstOf(
		DSLDOMAINEVENT,
		DSLCOMMANDEVENT
		)).skipIfOneChild();
		
		


		b.rule(DSLDOMAINOBJECTTYPEDELEMENT).is(
		b.firstOf(
		DSLSERVICEOPERATION,
		DSLREPOSITORYOPERATION,
		DSLDOMAINOBJECTOPERATION,
		DSLPARAMETER
		)).skipIfOneChild();
		
		


		b.rule(DSLSERVICEOPERATION).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOMPLEXTYPE),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslPunctuator.	SK40, SculptordslPunctuator.	SK40),
		b.optional(
		DSLPARAMETER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		DSLPARAMETER),
		b.isOneOfThem(SculptordslPunctuator.	SK41, SculptordslPunctuator.	SK41)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	THROWS_874432947, SculptordslKeyword.	THROWS_874432947),
		DSLTHROWSIDENTIFIER),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		DSLPUBLISH),
		b.optional(
		DSLSERVICEOPERATIONDELEGATE),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLSERVICEOPERATIONDELEGATE).is(
		b.sequence(		DELEGATE,
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK46, SculptordslPunctuator.	SK46),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(DSLSERVICEREPOSITORYOPTION).is(
		b.firstOf(
		DSLREPOSITORY,
		DSLSERVICE
		)).skipIfOneChild();
		
		


		b.rule(DSLSERVICEREPOSITORYOPERATIONOPTION).is(
		b.firstOf(
		DSLREPOSITORYOPERATION,
		DSLSERVICEOPERATION
		)).skipIfOneChild();
		
		


		b.rule(DSLRESOURCEOPERATION).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOMPLEXTYPE),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslPunctuator.	SK40, SculptordslPunctuator.	SK40),
		b.optional(
		DSLPARAMETER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		DSLPARAMETER),
		b.isOneOfThem(SculptordslPunctuator.	SK41, SculptordslPunctuator.	SK41)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	THROWS_874432947, SculptordslKeyword.	THROWS_874432947),
		DSLTHROWSIDENTIFIER),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		DSLHTTPMETHOD),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PATH_3433509, SculptordslKeyword.	PATH_3433509),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	RETURN_934396624, SculptordslKeyword.	RETURN_934396624),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		DSLRESOURCEOPERATIONDELEGATE),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLRESOURCEOPERATIONDELEGATE).is(
		b.sequence(		DELEGATE,
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK46, SculptordslPunctuator.	SK46),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(DSLREPOSITORYOPERATION).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOMPLEXTYPE),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslPunctuator.	SK40, SculptordslPunctuator.	SK40),
		b.optional(
		DSLPARAMETER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		DSLPARAMETER),
		b.isOneOfThem(SculptordslPunctuator.	SK41, SculptordslPunctuator.	SK41)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	THROWS_874432947, SculptordslKeyword.	THROWS_874432947),
		DSLTHROWSIDENTIFIER),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	QUERY_107944136, SculptordslKeyword.	QUERY_107944136),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	CONDITION_861311717, SculptordslKeyword.	CONDITION_861311717),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SELECT_906021636, SculptordslKeyword.	SELECT_906021636),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	GROUPBY_293428022, SculptordslKeyword.	GROUPBY_293428022),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ORDERBY_1207110587, SculptordslKeyword.	ORDERBY_1207110587),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	CONSTRUCT_421764137, SculptordslKeyword.	CONSTRUCT_421764137)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	BUILD_94094958, SculptordslKeyword.	BUILD_94094958)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	MAP_107868, SculptordslKeyword.	MAP_107868)),
		b.optional(
		DSLPUBLISH),
		b.optional(
		DELEGATE,
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	ACCESSOBJECT_2045509091, SculptordslKeyword.	ACCESSOBJECT_2045509091),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER)),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLPARAMETER).is(
		b.sequence(		b.optional(
		STRING),
		DSLCOMPLEXTYPE,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(DSLCOMPLEXTYPE).is(
		b.firstOf(
		DSLCOLLECTIONTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLTYPE,
		DSLTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		DSL_MAP_COLLECTION_TYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60),
		DSLTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLTYPE,
		DSLTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLTYPE,
		DSLTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62)
		)).skipIfOneChild();
		
		


		b.rule(DSLSIMPLEDOMAINOBJECT).is(
		b.firstOf(
		DSLBASICTYPE,
		DSLENUM,
		DSLDOMAINOBJECT,
		DSLDATATRANSFEROBJECT,
		DSLTRAIT
		)).skipIfOneChild();
		
		


		b.rule(DSLDOMAINOBJECT).is(
		b.firstOf(
		DSLENTITY,
		DSLVALUEOBJECT,
		DSLEVENT
		)).skipIfOneChild();
		
		


		b.rule(DSLENTITY).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ABSTRACT_1732898850, SculptordslKeyword.	ABSTRACT_1732898850)),
		b.isOneOfThem(SculptordslKeyword.	ENTITY_2080559107, SculptordslKeyword.	ENTITY_2080559107),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslKeyword.	EXTENDS_1305664359, SculptordslKeyword.	EXTENDS_1305664359),
		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLJAVAIDENTIFIER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslKeyword.	WITH_3649734, SculptordslKeyword.	WITH_3649734),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	OPTIMISTICLOCKING_1038808150, SculptordslKeyword.	OPTIMISTICLOCKING_1038808150),
		b.isOneOfThem(SculptordslKeyword.	OPTIMISTICLOCKING_1038808150, SculptordslKeyword.	OPTIMISTICLOCKING_1038808150)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	AUDITABLE_193186741, SculptordslKeyword.	AUDITABLE_193186741),
		b.isOneOfThem(SculptordslKeyword.	AUDITABLE_193186741, SculptordslKeyword.	AUDITABLE_193186741)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SCAFFOLD_897347594, SculptordslKeyword.	SCAFFOLD_897347594)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASETABLE_1341460717, SculptordslKeyword.	DATABASETABLE_1341460717),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORVALUE_1564074955, SculptordslKeyword.	DISCRIMINATORVALUE_1564074955),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566, SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORTYPE_2128700426, SculptordslKeyword.	DISCRIMINATORTYPE_2128700426),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLDISCRIMINATORTYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078, SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	INHERITANCETYPE_1266412324, SculptordslKeyword.	INHERITANCETYPE_1266412324),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLINHERITANCETYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329),
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329)),
		b.isOneOfThem(SculptordslKeyword.	BELONGSTO_757939025, SculptordslKeyword.	BELONGSTO_757939025),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.firstOf(
		b.zeroOrMore(DSLATTRIBUTE),
		b.zeroOrMore(DSLREFERENCE),
		b.zeroOrMore(DSLDOMAINOBJECTOPERATION)),
		b.optional(
		DSLREPOSITORY),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLVALUEOBJECT).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ABSTRACT_1732898850, SculptordslKeyword.	ABSTRACT_1732898850)),
		b.isOneOfThem(SculptordslKeyword.	VALUEOBJECT_111882224, SculptordslKeyword.	VALUEOBJECT_111882224),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslKeyword.	EXTENDS_1305664359, SculptordslKeyword.	EXTENDS_1305664359),
		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLJAVAIDENTIFIER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslKeyword.	WITH_3649734, SculptordslKeyword.	WITH_3649734),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	OPTIMISTICLOCKING_1038808150, SculptordslKeyword.	OPTIMISTICLOCKING_1038808150),
		b.isOneOfThem(SculptordslKeyword.	OPTIMISTICLOCKING_1038808150, SculptordslKeyword.	OPTIMISTICLOCKING_1038808150)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	IMMUTABLE_1596987778, SculptordslKeyword.	IMMUTABLE_1596987778),
		b.isOneOfThem(SculptordslKeyword.	IMMUTABLE_1596987778, SculptordslKeyword.	IMMUTABLE_1596987778)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SCAFFOLD_897347594, SculptordslKeyword.	SCAFFOLD_897347594)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASETABLE_1341460717, SculptordslKeyword.	DATABASETABLE_1341460717),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORVALUE_1564074955, SculptordslKeyword.	DISCRIMINATORVALUE_1564074955),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566, SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORTYPE_2128700426, SculptordslKeyword.	DISCRIMINATORTYPE_2128700426),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLDISCRIMINATORTYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078, SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	INHERITANCETYPE_1266412324, SculptordslKeyword.	INHERITANCETYPE_1266412324),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLINHERITANCETYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	PERSISTENT_512462487, SculptordslKeyword.	PERSISTENT_512462487),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	PERSISTENT_512462487, SculptordslKeyword.	PERSISTENT_512462487)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329),
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329)),
		b.isOneOfThem(SculptordslKeyword.	BELONGSTO_757939025, SculptordslKeyword.	BELONGSTO_757939025),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.firstOf(
		b.zeroOrMore(DSLATTRIBUTE),
		b.zeroOrMore(DSLREFERENCE),
		b.zeroOrMore(DSLDOMAINOBJECTOPERATION)),
		b.optional(
		DSLREPOSITORY),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLDOMAINEVENT).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ABSTRACT_1732898850, SculptordslKeyword.	ABSTRACT_1732898850)),
		b.isOneOfThem(SculptordslKeyword.	DOMAINEVENT_1748217878, SculptordslKeyword.	DOMAINEVENT_1748217878),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslKeyword.	EXTENDS_1305664359, SculptordslKeyword.	EXTENDS_1305664359),
		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLJAVAIDENTIFIER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslKeyword.	WITH_3649734, SculptordslKeyword.	WITH_3649734),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SCAFFOLD_897347594, SculptordslKeyword.	SCAFFOLD_897347594)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASETABLE_1341460717, SculptordslKeyword.	DATABASETABLE_1341460717),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORVALUE_1564074955, SculptordslKeyword.	DISCRIMINATORVALUE_1564074955),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566, SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORTYPE_2128700426, SculptordslKeyword.	DISCRIMINATORTYPE_2128700426),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLDISCRIMINATORTYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078, SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	INHERITANCETYPE_1266412324, SculptordslKeyword.	INHERITANCETYPE_1266412324),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLINHERITANCETYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PERSISTENT_512462487, SculptordslKeyword.	PERSISTENT_512462487)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329),
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329)),
		b.isOneOfThem(SculptordslKeyword.	BELONGSTO_757939025, SculptordslKeyword.	BELONGSTO_757939025),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.firstOf(
		b.zeroOrMore(DSLATTRIBUTE),
		b.zeroOrMore(DSLREFERENCE),
		b.zeroOrMore(DSLDOMAINOBJECTOPERATION)),
		b.optional(
		DSLREPOSITORY),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLCOMMANDEVENT).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ABSTRACT_1732898850, SculptordslKeyword.	ABSTRACT_1732898850)),
		b.isOneOfThem(SculptordslKeyword.	COMMANDEVENT_2096614959, SculptordslKeyword.	COMMANDEVENT_2096614959),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslKeyword.	EXTENDS_1305664359, SculptordslKeyword.	EXTENDS_1305664359),
		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLJAVAIDENTIFIER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslKeyword.	WITH_3649734, SculptordslKeyword.	WITH_3649734),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SCAFFOLD_897347594, SculptordslKeyword.	SCAFFOLD_897347594)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASETABLE_1341460717, SculptordslKeyword.	DATABASETABLE_1341460717),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORVALUE_1564074955, SculptordslKeyword.	DISCRIMINATORVALUE_1564074955),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566, SculptordslKeyword.	DISCRIMINATORCOLUMN_1772707566),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORTYPE_2128700426, SculptordslKeyword.	DISCRIMINATORTYPE_2128700426),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLDISCRIMINATORTYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078, SculptordslKeyword.	DISCRIMINATORLENGTH_1524234078),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	INHERITANCETYPE_1266412324, SculptordslKeyword.	INHERITANCETYPE_1266412324),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLINHERITANCETYPE),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PERSISTENT_512462487, SculptordslKeyword.	PERSISTENT_512462487)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329),
		b.isOneOfThem(SculptordslKeyword.	AGGREGATEROOT_1247083329, SculptordslKeyword.	AGGREGATEROOT_1247083329)),
		b.isOneOfThem(SculptordslKeyword.	BELONGSTO_757939025, SculptordslKeyword.	BELONGSTO_757939025),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.firstOf(
		b.zeroOrMore(DSLATTRIBUTE),
		b.zeroOrMore(DSLREFERENCE),
		b.zeroOrMore(DSLDOMAINOBJECTOPERATION)),
		b.optional(
		DSLREPOSITORY),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLTRAIT).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	TRAIT_81068526, SculptordslKeyword.	TRAIT_81068526),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.firstOf(
		b.zeroOrMore(DSLATTRIBUTE),
		b.zeroOrMore(DSLREFERENCE),
		b.zeroOrMore(DSLDOMAINOBJECTOPERATION)),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLDOMAINOBJECTOPERATION).is(
		b.sequence(		b.optional(
		STRING),
		OP,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ABSTRACT_1732898850, SculptordslKeyword.	ABSTRACT_1732898850)),
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOMPLEXTYPE),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslPunctuator.	SK40, SculptordslPunctuator.	SK40),
		b.optional(
		DSLPARAMETER),
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		DSLPARAMETER),
		b.isOneOfThem(SculptordslPunctuator.	SK41, SculptordslPunctuator.	SK41)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	THROWS_874432947, SculptordslKeyword.	THROWS_874432947),
		DSLTHROWSIDENTIFIER),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLDATATRANSFEROBJECT).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ABSTRACT_1732898850, SculptordslKeyword.	ABSTRACT_1732898850)),
		b.isOneOfThem(SculptordslKeyword.	DATATRANSFEROBJECT_672215412, SculptordslKeyword.	DATATRANSFEROBJECT_672215412),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslKeyword.	EXTENDS_1305664359, SculptordslKeyword.	EXTENDS_1305664359),
		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		DSLJAVAIDENTIFIER),
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.firstOf(
		b.zeroOrMore(DSLDTOATTRIBUTE),
		b.zeroOrMore(DSLDTOREFERENCE)),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLBASICTYPE).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	BASICTYPE_304820328, SculptordslKeyword.	BASICTYPE_304820328),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.zeroOrMore(
		b.isOneOfThem(SculptordslKeyword.	WITH_3649734, SculptordslKeyword.	WITH_3649734),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	IMMUTABLE_1596987778, SculptordslKeyword.	IMMUTABLE_1596987778),
		b.isOneOfThem(SculptordslKeyword.	IMMUTABLE_1596987778, SculptordslKeyword.	IMMUTABLE_1596987778)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.firstOf(
		b.zeroOrMore(DSLATTRIBUTE),
		b.zeroOrMore(DSLREFERENCE),
		b.zeroOrMore(DSLDOMAINOBJECTOPERATION)),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLATTRIBUTE).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOLLECTIONTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60)),
		DSLTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	KEY_106079, SculptordslKeyword.	KEY_106079)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654),
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263)),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	INDEX_100346066, SculptordslKeyword.	INDEX_100346066)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ASSERTFALSE_381342077, SculptordslKeyword.	ASSERTFALSE_381342077),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ASSERTTRUE_2090945012, SculptordslKeyword.	ASSERTTRUE_2090945012),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	CREDITCARDNUMBER_1151034798, SculptordslKeyword.	CREDITCARDNUMBER_1151034798),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DIGITS_1331909402, SculptordslKeyword.	DIGITS_1331909402),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	EMAIL_96619420, SculptordslKeyword.	EMAIL_96619420),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	FUTURE_1263170109, SculptordslKeyword.	FUTURE_1263170109),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PAST_3433490, SculptordslKeyword.	PAST_3433490),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	MAX_107876, SculptordslKeyword.	MAX_107876),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	MIN_108114, SculptordslKeyword.	MIN_108114),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DECIMALMAX_1984164781, SculptordslKeyword.	DECIMALMAX_1984164781),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DECIMALMIN_1984164543, SculptordslKeyword.	DECIMALMIN_1984164543),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	NOTEMPTY_1552332346, SculptordslKeyword.	NOTEMPTY_1552332346),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	NOTBLANK_1549517377, SculptordslKeyword.	NOTBLANK_1549517377),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PATTERN_791090288, SculptordslKeyword.	PATTERN_791090288),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	RANGE_108280125, SculptordslKeyword.	RANGE_108280125),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SIZE_3530753, SculptordslKeyword.	SIZE_3530753),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	LENGTH_1106363674, SculptordslKeyword.	LENGTH_1106363674),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SCRIPTASSERT_2108675087, SculptordslKeyword.	SCRIPTASSERT_2108675087),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	URL_116079, SculptordslKeyword.	URL_116079),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	TRANSIENT_1052746378, SculptordslKeyword.	TRANSIENT_1052746378)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASECOLUMN_890931377, SculptordslKeyword.	DATABASECOLUMN_890931377),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASETYPE_458891435, SculptordslKeyword.	DATABASETYPE_458891435),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLREFERENCE).is(
		b.sequence(		b.optional(
		STRING),
		REF,
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOLLECTIONTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60)),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	KEY_106079, SculptordslKeyword.	KEY_106079)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654),
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263)),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	CASCADE_554829492, SculptordslKeyword.	CASCADE_554829492),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	FETCH_97322682, SculptordslKeyword.	FETCH_97322682),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CACHE_94416770, SculptordslKeyword.	CACHE_94416770)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	INVERSE_1959910192, SculptordslKeyword.	INVERSE_1959910192),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	INVERSE_1959910192, SculptordslKeyword.	INVERSE_1959910192)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASECOLUMN_890931377, SculptordslKeyword.	DATABASECOLUMN_890931377),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASEJOINTABLE_480158089, SculptordslKeyword.	DATABASEJOINTABLE_480158089),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DATABASEJOINCOLUMN_1526539515, SculptordslKeyword.	DATABASEJOINCOLUMN_1526539515),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	NOTEMPTY_1552332346, SculptordslKeyword.	NOTEMPTY_1552332346),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SIZE_3530753, SculptordslKeyword.	SIZE_3530753),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALID_111972348, SculptordslKeyword.	VALID_111972348),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	TRANSIENT_1052746378, SculptordslKeyword.	TRANSIENT_1052746378)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ORDERBY_1207109595, SculptordslKeyword.	ORDERBY_1207109595),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ORDERCOLUMN_1167725636, SculptordslKeyword.	ORDERCOLUMN_1167725636),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		DSLOPPOSITEHOLDER),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLDTOATTRIBUTE).is(
		b.sequence(		b.optional(
		STRING),
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOLLECTIONTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60)),
		DSLTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	KEY_106079, SculptordslKeyword.	KEY_106079)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654),
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263)),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	TRANSIENT_1052746378, SculptordslKeyword.	TRANSIENT_1052746378)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ASSERTFALSE_381342077, SculptordslKeyword.	ASSERTFALSE_381342077),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ASSERTTRUE_2090945012, SculptordslKeyword.	ASSERTTRUE_2090945012),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	CREDITCARDNUMBER_1151034798, SculptordslKeyword.	CREDITCARDNUMBER_1151034798),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DIGITS_1331909402, SculptordslKeyword.	DIGITS_1331909402),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	EMAIL_96619420, SculptordslKeyword.	EMAIL_96619420),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	FUTURE_1263170109, SculptordslKeyword.	FUTURE_1263170109),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PAST_3433490, SculptordslKeyword.	PAST_3433490),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	MAX_107876, SculptordslKeyword.	MAX_107876),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	MIN_108114, SculptordslKeyword.	MIN_108114),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DECIMALMAX_1984164781, SculptordslKeyword.	DECIMALMAX_1984164781),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	DECIMALMIN_1984164543, SculptordslKeyword.	DECIMALMIN_1984164543),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	NOTEMPTY_1552332346, SculptordslKeyword.	NOTEMPTY_1552332346),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	NOTBLANK_1549517377, SculptordslKeyword.	NOTBLANK_1549517377),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PATTERN_791090288, SculptordslKeyword.	PATTERN_791090288),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	RANGE_108280125, SculptordslKeyword.	RANGE_108280125),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SIZE_3530753, SculptordslKeyword.	SIZE_3530753),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	LENGTH_1106363674, SculptordslKeyword.	LENGTH_1106363674),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SCRIPTASSERT_2108675087, SculptordslKeyword.	SCRIPTASSERT_2108675087),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	URL_116079, SculptordslKeyword.	URL_116079),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLDTOREFERENCE).is(
		b.sequence(		b.optional(
		STRING),
		REF,
		b.optional(
		DSLVISIBILITY),
		b.optional(
		DSLCOLLECTIONTYPE,
		b.isOneOfThem(SculptordslPunctuator.	SK60, SculptordslPunctuator.	SK60)),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	KEY_106079, SculptordslKeyword.	KEY_106079)),
		b.firstOf(
		NOT,
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654),
		b.isOneOfThem(SculptordslKeyword.	CHANGEABLE_2131537654, SculptordslKeyword.	CHANGEABLE_2131537654)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	REQUIRED_393139297, SculptordslKeyword.	REQUIRED_393139297)),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263),
		NOT,
		b.isOneOfThem(SculptordslKeyword.	NULLABLE_1905967263, SculptordslKeyword.	NULLABLE_1905967263)),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	TRANSIENT_1052746378, SculptordslKeyword.	TRANSIENT_1052746378)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	NOTEMPTY_1552332346, SculptordslKeyword.	NOTEMPTY_1552332346),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	SIZE_3530753, SculptordslKeyword.	SIZE_3530753),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALID_111972348, SculptordslKeyword.	VALID_111972348),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	VALIDATE_1421272810, SculptordslKeyword.	VALIDATE_1421272810),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLOPPOSITEHOLDER).is(
		b.sequence(		OPPOSITE,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(DSLREPOSITORY).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	REPOSITORY_350701718, SculptordslKeyword.	REPOSITORY_350701718),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	GAP_102102, SculptordslKeyword.	GAP_102102),
		b.isOneOfThem(SculptordslKeyword.	NOGAP_104996213, SculptordslKeyword.	NOGAP_104996213)),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		DSLSUBSCRIBE),
		b.zeroOrMore(DSLDEPENDENCY),
		b.zeroOrMore(DSLREPOSITORYOPERATION),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLSERVICEDEPENDENCY).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		b.isOneOfThem(SculptordslKeyword.	INJECT_1184061039, SculptordslKeyword.	INJECT_1184061039)),
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		));
		


		b.rule(DSLDEPENDENCY).is(
		b.sequence(		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK62, SculptordslPunctuator.	SK62),
		b.isOneOfThem(SculptordslKeyword.	INJECT_1184061039, SculptordslKeyword.	INJECT_1184061039)),
		b.firstOf(
		b.isOneOfThem(SculptordslPunctuator.	SK64, SculptordslPunctuator.	SK64),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		))).skipIfOneChild();
		
		


		b.rule(DSLENUM).is(
		b.sequence(		b.optional(
		STRING),
		b.isOneOfThem(SculptordslKeyword.	ENUM_3118337, SculptordslKeyword.	ENUM_3118337),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SculptordslPunctuator.	SK123, SculptordslPunctuator.	SK123),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	PACKAGE_807062458, SculptordslKeyword.	PACKAGE_807062458),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		DSLJAVAIDENTIFIER),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	HINT_3202695, SculptordslKeyword.	HINT_3202695),
		b.isOneOfThem(SculptordslPunctuator.	SK61, SculptordslPunctuator.	SK61),
		STRING),
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	ORDINAL_1206994319, SculptordslKeyword.	ORDINAL_1206994319)),
		b.zeroOrMore(DSLENUMATTRIBUTE),
		DSLENUMVALUE,
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		DSLENUMVALUE),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59),
		b.isOneOfThem(SculptordslPunctuator.	SK125, SculptordslPunctuator.	SK125)
		));
		


		b.rule(DSLENUMATTRIBUTE).is(
		b.sequence(		b.optional(
		STRING),
		DSLTYPE,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslKeyword.	KEY_106079, SculptordslKeyword.	KEY_106079)),
		b.isOneOfThem(SculptordslPunctuator.	SK59, SculptordslPunctuator.	SK59)
		));
		


		b.rule(DSLENUMVALUE).is(
		b.sequence(		b.optional(
		STRING),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SculptordslPunctuator.	SK40, SculptordslPunctuator.	SK40),
		DSLENUMPARAMETER,
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		DSLENUMPARAMETER),
		b.isOneOfThem(SculptordslPunctuator.	SK41, SculptordslPunctuator.	SK41))
		));
		


		b.rule(DSLENUMPARAMETER).is(
		b.firstOf(
		STRING,
		b.isOneOfThem(NUMBER, NUMBER)
		)).skipIfOneChild();
		
		


		b.rule(DSLANYPROPERTY).is(
		b.firstOf(
		DSLPROPERTY,
		DSLDTOPROPERTY
		)).skipIfOneChild();
		
		


		b.rule(DSLPROPERTY).is(
		b.firstOf(
		DSLATTRIBUTE,
		DSLREFERENCE
		)).skipIfOneChild();
		
		


		b.rule(DSLDTOPROPERTY).is(
		b.firstOf(
		DSLDTOATTRIBUTE,
		DSLDTOREFERENCE
		)).skipIfOneChild();
		
		


		b.rule(DSLTYPE).is(
		b.firstOf(
		b.isOneOfThem(SculptordslKeyword.	STRING_1808118735, SculptordslKeyword.	STRING_1808118735),
		b.isOneOfThem(SculptordslKeyword.	INT_104431, SculptordslKeyword.	INT_104431),
		b.isOneOfThem(SculptordslKeyword.	INTEGER_672261858, SculptordslKeyword.	INTEGER_672261858),
		b.isOneOfThem(SculptordslKeyword.	LONG_3327612, SculptordslKeyword.	LONG_3327612),
		b.isOneOfThem(SculptordslKeyword.	LONG_2374300, SculptordslKeyword.	LONG_2374300),
		b.isOneOfThem(SculptordslKeyword.	BOOLEAN_64711720, SculptordslKeyword.	BOOLEAN_64711720),
		b.isOneOfThem(SculptordslKeyword.	BOOLEAN_1729365000, SculptordslKeyword.	BOOLEAN_1729365000),
		b.isOneOfThem(SculptordslKeyword.	DATE_2122702, SculptordslKeyword.	DATE_2122702),
		b.isOneOfThem(SculptordslKeyword.	DATETIME_1857393595, SculptordslKeyword.	DATETIME_1857393595),
		b.isOneOfThem(SculptordslKeyword.	TIMESTAMP_2059094262, SculptordslKeyword.	TIMESTAMP_2059094262),
		b.isOneOfThem(SculptordslKeyword.	BIGDECIMAL_1438607953, SculptordslKeyword.	BIGDECIMAL_1438607953),
		b.isOneOfThem(SculptordslKeyword.	BIGINTEGER_1854396478, SculptordslKeyword.	BIGINTEGER_1854396478),
		b.isOneOfThem(SculptordslKeyword.	DOUBLE_1325958191, SculptordslKeyword.	DOUBLE_1325958191),
		b.isOneOfThem(SculptordslKeyword.	DOUBLE_2052876273, SculptordslKeyword.	DOUBLE_2052876273),
		b.isOneOfThem(SculptordslKeyword.	FLOAT_97526364, SculptordslKeyword.	FLOAT_97526364),
		b.isOneOfThem(SculptordslKeyword.	FLOAT_67973692, SculptordslKeyword.	FLOAT_67973692),
		b.isOneOfThem(SculptordslKeyword.	KEY_75327, SculptordslKeyword.	KEY_75327),
		b.isOneOfThem(SculptordslKeyword.	PAGINGPARAMETER_135188131, SculptordslKeyword.	PAGINGPARAMETER_135188131),
		b.isOneOfThem(SculptordslKeyword.	PAGEDRESULT_162729966, SculptordslKeyword.	PAGEDRESULT_162729966),
		b.isOneOfThem(SculptordslKeyword.	BLOB_2073533, SculptordslKeyword.	BLOB_2073533),
		b.isOneOfThem(SculptordslKeyword.	CLOB_2103324, SculptordslKeyword.	CLOB_2103324),
		b.isOneOfThem(SculptordslKeyword.	OBJECT___155139841, SculptordslKeyword.	OBJECT___155139841),
		DSLJAVAIDENTIFIER
		)).skipIfOneChild();
		
		


		b.rule(DSLJAVAIDENTIFIER).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK46, SculptordslPunctuator.	SK46),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		)
		));
		


		b.rule(DSLCHANNELIDENTIFIER).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK46, SculptordslPunctuator.	SK46),
		b.isOneOfThem(SculptordslPunctuator.	SK47, SculptordslPunctuator.	SK47),
		b.isOneOfThem(SculptordslPunctuator.	SK58, SculptordslPunctuator.	SK58),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		)
		));
		


		b.rule(DSLTHROWSIDENTIFIER).is(
		b.sequence(		DSLJAVAIDENTIFIER,
		b.zeroOrMore(
		b.isOneOfThem(SculptordslPunctuator.	SK44, SculptordslPunctuator.	SK44),
		DSLJAVAIDENTIFIER
		)
		));
		

	}
}
