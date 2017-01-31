package org.sonar.Thing.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public class Thing extends Grammar {
	public Rule THINGMODEL;
	public Rule MODELPROPERTYCONTAINER;
	public Rule MODELBRIDGE;
	public Rule MODELTHING;
	public Rule MODELCHANNEL;
	public Rule MODELITEMTYPE;
	public Rule MODELPROPERTY;
	public Rule CHANNEL_ID;
	public Rule UID;
	public Rule UID_SEGMENT;
	public Rule VALUETYPE;
	public Rule BOOLEAN;
	public Rule NUMBER;

	@Override
	public Rule getRootRule() {
		// TODO Auto-generated method stub
		return THINGMODEL;
	}
}
