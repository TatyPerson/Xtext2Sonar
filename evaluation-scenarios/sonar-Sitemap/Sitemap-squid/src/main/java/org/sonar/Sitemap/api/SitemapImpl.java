package org.sonar.Sitemap.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sonar.sslr.api.Grammar;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.Sitemap.SitemapConfiguration;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;
import static com.sonar.sslr.api.GenericTokenType.IDENTIFIER;
import static org.sonar.Sitemap.api.SitemapTokenType.NUMBER;
import static org.sonar.Sitemap.api.SitemapTokenType.STRING;
import static org.sonar.Sitemap.api.SitemapTokenType.CHARACTER;
import static org.sonar.Sitemap.api.SitemapTokenType.ID;
import static org.sonar.Sitemap.api.SitemapTokenType.FLOAT;

public enum SitemapImpl implements GrammarRuleKey {
	SITEMAPMODEL,
	SITEMAP,
	WIDGET,
	NONLINKABLEWIDGET,
	LINKABLEWIDGET,
	FRAME,
	TEXT,
	GROUP,
	IMAGE,
	VIDEO,
	CHART,
	WEBVIEW,
	SWITCH,
	MAPVIEW,
	SLIDER,
	SELECTION,
	LIST,
	SETPOINT,
	COLORPICKER,
	DEFAULT,
	MAPPING,
	VISIBILITYRULE,
	ITEMREF,
	GROUPITEMREF,
	ICON,
	COLORARRAY,
	COMMAND,
	XSTATE;

	public static final Logger LOG = LoggerFactory.getLogger("SitemapImpl");

	public static Grammar create(SitemapConfiguration conf) {
		 LexerfulGrammarBuilder b = LexerfulGrammarBuilder.create();

		 generate(b);
		 b.setRootRule(SITEMAPMODEL);

		 return b.buildWithMemoizationOfMatchesForAllRules();
	}

	private static void generate(LexerfulGrammarBuilder b) {
		b.rule(ID).is(b.firstOf(b.isOneOfThem(com.sonar.sslr.api.GenericTokenType.IDENTIFIER, com.sonar.sslr.api.GenericTokenType.IDENTIFIER),"|", "."));
		b.rule(FLOAT).is(b.isOneOfThem(NUMBER, NUMBER));

		b.rule(SITEMAPMODEL).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	SITEMAP_2100014261, SitemapKeyword.	SITEMAP_2100014261),
		SITEMAP
		));
		


		b.rule(SITEMAP).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		STRING),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		STRING),
		b.isOneOfThem(SitemapPunctuator.	SK123, SitemapPunctuator.	SK123),
		b.oneOrMore(WIDGET),
		b.isOneOfThem(SitemapPunctuator.	SK125, SitemapPunctuator.	SK125)
		));
		


		b.rule(WIDGET).is(
		b.firstOf(
		LINKABLEWIDGET,
		NONLINKABLEWIDGET
		)).skipIfOneChild();
		
		


		b.rule(NONLINKABLEWIDGET).is(
		b.firstOf(
		SWITCH,
		SELECTION,
		SLIDER,
		LIST,
		SETPOINT,
		VIDEO,
		CHART,
		WEBVIEW,
		COLORPICKER,
		MAPVIEW,
		DEFAULT
		)).skipIfOneChild();
		
		


		b.rule(LINKABLEWIDGET).is(
		b.sequence(		b.firstOf(
		TEXT,
		GROUP,
		IMAGE,
		FRAME),
		b.optional(
		b.isOneOfThem(SitemapPunctuator.	SK123, SitemapPunctuator.	SK123),
		b.oneOrMore(WIDGET),
		b.isOneOfThem(SitemapPunctuator.	SK125, SitemapPunctuator.	SK125))
		));
		


		b.rule(FRAME).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	FRAME_68139341, SitemapKeyword.	FRAME_68139341),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(TEXT).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	TEXT_2603341, SitemapKeyword.	TEXT_2603341),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(GROUP).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	GROUP_69076575, SitemapKeyword.	GROUP_69076575),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		GROUPITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(IMAGE).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	IMAGE_70760763, SitemapKeyword.	IMAGE_70760763),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.isOneOfThem(SitemapKeyword.	URL__3598510, SitemapKeyword.	URL__3598510),
		STRING,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	REFRESH__710948670, SitemapKeyword.	REFRESH__710948670),
		b.isOneOfThem(NUMBER, NUMBER)),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	ICONCOLOR___1355127864, SitemapKeyword.	ICONCOLOR___1355127864),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(VIDEO).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	VIDEO_82650203, SitemapKeyword.	VIDEO_82650203),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.isOneOfThem(SitemapKeyword.	URL__3598510, SitemapKeyword.	URL__3598510),
		STRING,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ENCODING__1508277578, SitemapKeyword.	ENCODING__1508277578),
		STRING),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(CHART).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	CHART_65071038, SitemapKeyword.	CHART_65071038),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	SERVICE__1379209256, SitemapKeyword.	SERVICE__1379209256),
		STRING),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	REFRESH__710948670, SitemapKeyword.	REFRESH__710948670),
		b.isOneOfThem(NUMBER, NUMBER)),
		b.isOneOfThem(SitemapKeyword.	PERIOD__678739300, SitemapKeyword.	PERIOD__678739300),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(WEBVIEW).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	WEBVIEW_1405889575, SitemapKeyword.	WEBVIEW_1405889575),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	HEIGHT__802788342, SitemapKeyword.	HEIGHT__802788342),
		b.isOneOfThem(NUMBER, NUMBER)),
		b.isOneOfThem(SitemapKeyword.	URL__3598510, SitemapKeyword.	URL__3598510),
		STRING,
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(SWITCH).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	SWITCH_1805606060, SitemapKeyword.	SWITCH_1805606060),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	MAPPINGS___2116271133, SitemapKeyword.	MAPPINGS___2116271133),
		MAPPING,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		MAPPING),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(MAPVIEW).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	MAPVIEW_1792579103, SitemapKeyword.	MAPVIEW_1792579103),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	HEIGHT__802788342, SitemapKeyword.	HEIGHT__802788342),
		b.isOneOfThem(NUMBER, NUMBER)),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(SLIDER).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	SLIDER_1815780095, SitemapKeyword.	SLIDER_1815780095),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	SENDFREQUENCY__43186167, SitemapKeyword.	SENDFREQUENCY__43186167),
		b.isOneOfThem(NUMBER, NUMBER)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	SWITCHSUPPORT_98525701, SitemapKeyword.	SWITCHSUPPORT_98525701)),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(SELECTION).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	SELECTION_288002412, SitemapKeyword.	SELECTION_288002412),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	MAPPINGS___2116271133, SitemapKeyword.	MAPPINGS___2116271133),
		MAPPING,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		MAPPING),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(LIST).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	LIST_2368702, SitemapKeyword.	LIST_2368702),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.isOneOfThem(SitemapKeyword.	SEPARATOR__2116847112, SitemapKeyword.	SEPARATOR__2116847112),
		STRING,
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(SETPOINT).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	SETPOINT_1495755406, SitemapKeyword.	SETPOINT_1495755406),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	MINVALUE__263629278, SitemapKeyword.	MINVALUE__263629278),
		NUMBER),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	MAXVALUE__508849296, SitemapKeyword.	MAXVALUE__508849296),
		NUMBER),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	STEP__109761265, SitemapKeyword.	STEP__109761265),
		NUMBER),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(COLORPICKER).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	COLORPICKER_825259631, SitemapKeyword.	COLORPICKER_825259631),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	SENDFREQUENCY__43186167, SitemapKeyword.	SENDFREQUENCY__43186167),
		b.isOneOfThem(NUMBER, NUMBER)),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(DEFAULT).is(
		b.sequence(		b.isOneOfThem(SitemapKeyword.	DEFAULT_1085510111, SitemapKeyword.	DEFAULT_1085510111),
		b.isOneOfThem(SitemapKeyword.	ITEM__100525962, SitemapKeyword.	ITEM__100525962),
		ITEMREF,
		b.optional(
		b.isOneOfThem(SitemapKeyword.	LABEL__1110417463, SitemapKeyword.	LABEL__1110417463),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING)),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	ICON__100029156, SitemapKeyword.	ICON__100029156),
		ICON),
		b.optional(
		b.isOneOfThem(SitemapKeyword.	HEIGHT__802788342, SitemapKeyword.	HEIGHT__802788342),
		b.isOneOfThem(NUMBER, NUMBER)),
		b.isOneOfThem(SitemapKeyword.	LABELCOLOR___1202818835, SitemapKeyword.	LABELCOLOR___1202818835),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VALUECOLOR___46397488, SitemapKeyword.	VALUECOLOR___46397488),
		COLORARRAY,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		COLORARRAY),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93),
		b.isOneOfThem(SitemapKeyword.	VISIBILITY___1604972112, SitemapKeyword.	VISIBILITY___1604972112),
		VISIBILITYRULE,
		b.zeroOrMore(
		b.isOneOfThem(SitemapPunctuator.	SK44, SitemapPunctuator.	SK44),
		VISIBILITYRULE),
		b.isOneOfThem(SitemapPunctuator.	SK93, SitemapPunctuator.	SK93)
		));
		


		b.rule(MAPPING).is(
		b.sequence(		COMMAND,
		b.isOneOfThem(SitemapPunctuator.	SK61, SitemapPunctuator.	SK61),
		b.firstOf(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING
		))).skipIfOneChild();
		
		


		b.rule(VISIBILITYRULE).is(
		b.sequence(		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		b.firstOf(
		b.isOneOfThem(SitemapPunctuator.	SK6161, SitemapPunctuator.	SK6161),
		b.isOneOfThem(SitemapPunctuator.	SK62, SitemapPunctuator.	SK62),
		b.isOneOfThem(SitemapPunctuator.	SK60, SitemapPunctuator.	SK60),
		b.isOneOfThem(SitemapPunctuator.	SK6261, SitemapPunctuator.	SK6261),
		b.isOneOfThem(SitemapPunctuator.	SK6061, SitemapPunctuator.	SK6061),
		b.isOneOfThem(SitemapPunctuator.	SK3361, SitemapPunctuator.	SK3361)),
		b.firstOf(
		b.isOneOfThem(SitemapPunctuator.	SK45, SitemapPunctuator.	SK45),
		b.isOneOfThem(SitemapPunctuator.	SK43, SitemapPunctuator.	SK43)),
		XSTATE
		));
		

		b.rule(ITEMREF).is(b.isOneOfThem(IDENTIFIER, IDENTIFIER));
		

		b.rule(GROUPITEMREF).is(b.isOneOfThem(IDENTIFIER, IDENTIFIER));
		


		b.rule(ICON).is(
		b.firstOf(
		STRING,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER
		)).skipIfOneChild();
		
		


		b.rule(COLORARRAY).is(
		b.sequence(		b.optional(
		b.optional(
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER),
		b.firstOf(
		b.isOneOfThem(SitemapPunctuator.	SK6161, SitemapPunctuator.	SK6161),
		b.isOneOfThem(SitemapPunctuator.	SK62, SitemapPunctuator.	SK62),
		b.isOneOfThem(SitemapPunctuator.	SK60, SitemapPunctuator.	SK60),
		b.isOneOfThem(SitemapPunctuator.	SK6261, SitemapPunctuator.	SK6261),
		b.isOneOfThem(SitemapPunctuator.	SK6061, SitemapPunctuator.	SK6061),
		b.isOneOfThem(SitemapPunctuator.	SK3361, SitemapPunctuator.	SK3361)),
		b.firstOf(
		b.isOneOfThem(SitemapPunctuator.	SK45, SitemapPunctuator.	SK45),
		b.isOneOfThem(SitemapPunctuator.	SK43, SitemapPunctuator.	SK43)),
		XSTATE,
		b.isOneOfThem(SitemapPunctuator.	SK61, SitemapPunctuator.	SK61)),
		STRING
		));
		


		b.rule(COMMAND).is(
		b.firstOf(
		NUMBER,
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING
		)).skipIfOneChild();
		
		

		


		b.rule(XSTATE).is(
		b.firstOf(
		b.isOneOfThem(NUMBER, NUMBER),
		com.sonar.sslr.api.GenericTokenType.IDENTIFIER,
		STRING,
		b.isOneOfThem(NUMBER, NUMBER)
		)).skipIfOneChild();
		
		

	}
}
