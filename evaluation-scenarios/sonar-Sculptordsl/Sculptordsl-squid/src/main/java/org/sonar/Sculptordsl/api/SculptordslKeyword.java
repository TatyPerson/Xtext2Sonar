package org.sonar.Sculptordsl.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum SculptordslKeyword implements TokenType, GrammarRuleKey {
		
		ENTITY_2080559107("Entity"),
		DEF_99333("def"),
		ACCESSOBJECT_2045509091("AccessObject"),
		STRING_1808118735("String"),
		PATH_3433509("path"),
		PROTECTED_608539730("protected"),
		SINGLE_TABLE_1089259767("SINGLE_TABLE"),
		PERSISTENT_512462487("persistent"),
		DOMAINEVENT_1748217878("DomainEvent"),
		PAST_3433490("past"),
		UNMARSHALL_TO_524109588("unmarshall to"),
		ORDERBY_1207109595("orderby"),
		SIZE_3530753("size"),
		TRANSIENT_1052746378("transient"),
		AUDITABLE_193186741("auditable"),
		CLOB_2103324("Clob"),
		SET_83010("Set"),
		ORDERBY_1207110587("orderBy"),
		INHERITANCETYPE_1266412324("inheritanceType"),
		DELEGATES_TO_265920435("delegates to"),
		REPOSITORY_350701718("Repository"),
		DIGITS_1331909402("digits"),
		POST_2461856("POST"),
		LIST_2368702("List"),
		EXTERNAL_1820761141("external"),
		PRIVATE_314497661("private"),
		LENGTH_1106363674("length"),
		WITH_3649734("with"),
		PUBLISH_235365105("publish"),
		DECIMALMIN_1984164543("decimalMin"),
		INJECT_1184061039("inject"),
		SELECT_906021636("select"),
		NULLABLE_1905967263("nullable"),
		CONSTRUCT_421764137("construct"),
		DATETIME_1857393595("DateTime"),
		EVENTBUS_278091142("eventBus"),
		AGGREGATEROOT_1247083329("aggregateRoot"),
		DATABASEJOINTABLE_480158089("databaseJoinTable"),
		BASEPACKAGE_1494394933("basePackage"),
		INDEX_100346066("index"),
		ENUM_3118337("enum"),
		CREDITCARDNUMBER_1151034798("creditCardNumber"),
		TRAIT_81068526("Trait"),
		APPLICATION_1072845520("Application"),
		MAP_77116("Map"),
		VALID_111972348("valid"),
		ASSERTTRUE_2090945012("assertTrue"),
		VALUEOBJECT_111882224("ValueObject"),
		MAP_107868("map"),
		DATATRANSFEROBJECT_672215412("DataTransferObject"),
		MAX_107876("max"),
		URL_116079("url"),
		SCAFFOLD_897347594("scaffold"),
		FUTURE_1263170109("future"),
		RETURN_934396624("return"),
		DISCRIMINATORLENGTH_1524234078("discriminatorLength"),
		JOINED_2101200055("JOINED"),
		SCRIPTASSERT_2108675087("scriptAssert"),
		DATABASECOLUMN_890931377("databaseColumn"),
		THROWS_874432947("throws"),
		DOUBLE_2052876273("Double"),
		CONDITION_861311717("condition"),
		COLLECTION_252152510("Collection"),
		OPTIMISTICLOCKING_1038808150("optimisticLocking"),
		ORDERCOLUMN_1167725636("orderColumn"),
		DECIMALMAX_1984164781("decimalMax"),
		CHANGEABLE_2131537654("changeable"),
		DATABASEJOINCOLUMN_1526539515("databaseJoinColumn"),
		CONSUMER_503125994("Consumer"),
		NOTBLANK_1549517377("notBlank"),
		CACHE_94416770("cache"),
		OPPOSITE_187877657("opposite"),
		FLOAT_67973692("Float"),
		KEY_75327("Key"),
		OBJECT___155139841("Object[]"),
		BASICTYPE_304820328("BasicType"),
		GROUPBY_293428022("groupBy"),
		TIMESTAMP_2059094262("Timestamp"),
		LONG_3327612("long"),
		DELETE_2012838315("DELETE"),
		MIN_108114("min"),
		KEY_106079("key"),
		EMAIL_96619420("email"),
		PAGEDRESULT_162729966("PagedResult"),
		VALIDATE_1421272810("validate"),
		STRING_1838656495("STRING"),
		TO_3707("to"),
		BELONGSTO_757939025("belongsTo"),
		ORDINAL_1206994319("ordinal"),
		INTEGER_1618932450("INTEGER"),
		QUERY_107944136("query"),
		PAGINGPARAMETER_135188131("PagingParameter"),
		IMMUTABLE_1596987778("immutable"),
		EXTENDS_1305664359("extends"),
		HINT_3202695("hint"),
		LONG_2374300("Long"),
		BIGINTEGER_1854396478("BigInteger"),
		NOTEMPTY_1552332346("notEmpty"),
		IMPORT_1184795739("import"),
		NOT_109267("not"),
		GAP_102102("gap"),
		COMMANDEVENT_2096614959("CommandEvent"),
		DISCRIMINATORTYPE_2128700426("discriminatorType"),
		PUT_79599("PUT"),
		INTEGER_672261858("Integer"),
		DISCRIMINATORVALUE_1564074955("discriminatorValue"),
		REQUIRED_393139297("required"),
		DATABASETABLE_1341460717("databaseTable"),
		BOOLEAN_1729365000("Boolean"),
		DOUBLE_1325958191("double"),
		NOGAP_104996213("nogap"),
		GET_70454("GET"),
		MODULE_1984916852("Module"),
		DISCRIMINATORCOLUMN_1772707566("discriminatorColumn"),
		PATTERN_791090288("pattern"),
		RANGE_108280125("range"),
		CHAR_2067286("CHAR"),
		ABSTRACT_1732898850("abstract"),
		QUEUENAME_1739647172("queueName"),
		FETCH_97322682("fetch"),
		TOPICNAME_388205658("topicName"),
		APPLICATIONPART_2050583875("ApplicationPart"),
		WEBSERVICE_1201296609("webservice"),
		NONE_2433880("None"),
		FLOAT_97526364("float"),
		REFERENCE_925155509("reference"),
		BLOB_2073533("Blob"),
		PACKAGE_807062458("package"),
		CASCADE_554829492("cascade"),
		RESOURCE_276420562("Resource"),
		ASSERTFALSE_381342077("assertFalse"),
		PUBLIC_977423767("public"),
		INVERSE_1959910192("inverse"),
		SUBSCRIBE_514841930("subscribe"),
		SERVICE_646160747("Service"),
		BAG_66536("Bag"),
		INT_104431("int"),
		DATE_2122702("Date"),
		BIGDECIMAL_1438607953("BigDecimal"),
		DATABASETYPE_458891435("databaseType"),
		BOOLEAN_64711720("boolean"),
		BUILD_94094958("build");
;

  private final String value;

  private SculptordslKeyword(String value) {
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
    SculptordslKeyword[] keywordsEnum = SculptordslKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
