package org.sonar.Items.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public class Items extends Grammar {
	public Rule ITEMMODEL;
	public Rule MODELITEM;
	public Rule MODELGROUPITEM;
	public Rule MODELNORMALITEM;
	public Rule MODELITEMTYPE;
	public Rule MODELBINDING;

	@Override
	public Rule getRootRule() {
		// TODO Auto-generated method stub
		return ITEMMODEL;
	}
}
