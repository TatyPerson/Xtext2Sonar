package org.sonar.Sitemap.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public class Sitemap extends Grammar {
	public Rule SITEMAPMODEL;
	public Rule SITEMAP;
	public Rule WIDGET;
	public Rule NONLINKABLEWIDGET;
	public Rule LINKABLEWIDGET;
	public Rule FRAME;
	public Rule TEXT;
	public Rule GROUP;
	public Rule IMAGE;
	public Rule VIDEO;
	public Rule CHART;
	public Rule WEBVIEW;
	public Rule SWITCH;
	public Rule MAPVIEW;
	public Rule SLIDER;
	public Rule SELECTION;
	public Rule LIST;
	public Rule SETPOINT;
	public Rule COLORPICKER;
	public Rule DEFAULT;
	public Rule MAPPING;
	public Rule VISIBILITYRULE;
	public Rule ITEMREF;
	public Rule GROUPITEMREF;
	public Rule ICON;
	public Rule COLORARRAY;
	public Rule COMMAND;
	public Rule NUMBER;
	public Rule XSTATE;

	@Override
	public Rule getRootRule() {
		// TODO Auto-generated method stub
		return SITEMAPMODEL;
	}
}
