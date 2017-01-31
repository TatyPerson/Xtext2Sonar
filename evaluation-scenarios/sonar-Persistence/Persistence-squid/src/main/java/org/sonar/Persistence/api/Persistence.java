package org.sonar.Persistence.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public class Persistence extends Grammar {
	public Rule PERSISTENCEMODEL;
	public Rule STRATEGY;
	public Rule CRONSTRATEGY;
	public Rule FILTER;
	public Rule FILTERDETAILS;
	public Rule THRESHOLDFILTER;
	public Rule TIMEFILTER;
	public Rule PERSISTENCECONFIGURATION;
	public Rule ALLCONFIG;
	public Rule ITEMCONFIG;
	public Rule GROUPCONFIG;
	public Rule DECIMAL;

	@Override
	public Rule getRootRule() {
		// TODO Auto-generated method stub
		return PERSISTENCEMODEL;
	}
}
