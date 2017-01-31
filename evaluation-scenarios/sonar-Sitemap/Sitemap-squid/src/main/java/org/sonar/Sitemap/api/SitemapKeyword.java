package org.sonar.Sitemap.api;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.TokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;

public enum SitemapKeyword implements TokenType, GrammarRuleKey {
		
		LABEL__1110417463("label="),
		SERVICE__1379209256("service="),
		SEPARATOR__2116847112("separator="),
		HEIGHT__802788342("height="),
		MAPPINGS___2116271133("mappings=["),
		GROUP_69076575("Group"),
		SELECTION_288002412("Selection"),
		CHART_65071038("Chart"),
		MINVALUE__263629278("minValue="),
		ITEM__100525962("item="),
		STEP__109761265("step="),
		URL__3598510("url="),
		LIST_2368702("List"),
		LABELCOLOR___1202818835("labelcolor=["),
		ENCODING__1508277578("encoding="),
		VIDEO_82650203("Video"),
		SLIDER_1815780095("Slider"),
		MAPVIEW_1792579103("Mapview"),
		MAXVALUE__508849296("maxValue="),
		VALUECOLOR___46397488("valuecolor=["),
		ICONCOLOR___1355127864("iconcolor=["),
		IMAGE_70760763("Image"),
		ICON__100029156("icon="),
		PERIOD__678739300("period="),
		SWITCH_1805606060("Switch"),
		FRAME_68139341("Frame"),
		REFRESH__710948670("refresh="),
		COLORPICKER_825259631("Colorpicker"),
		SENDFREQUENCY__43186167("sendFrequency="),
		TEXT_2603341("Text"),
		DEFAULT_1085510111("Default"),
		SITEMAP_2100014261("sitemap"),
		SETPOINT_1495755406("Setpoint"),
		SWITCHSUPPORT_98525701("switchSupport"),
		WEBVIEW_1405889575("Webview"),
		VISIBILITY___1604972112("visibility=[");
;

  private final String value;

  private SitemapKeyword(String value) {
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
    SitemapKeyword[] keywordsEnum = SitemapKeyword.values();
    String[] keywords = new String[keywordsEnum.length];
    for (int i = 0; i < keywords.length; i++) {
      keywords[i] = keywordsEnum[i].getValue();
    }
    return keywords;
  }
}
