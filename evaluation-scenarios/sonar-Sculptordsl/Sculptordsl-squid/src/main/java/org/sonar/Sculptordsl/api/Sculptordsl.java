package org.sonar.Sculptordsl.api;

import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.api.Rule;

public class Sculptordsl extends Grammar {
	public Rule DSLMODEL;
	public Rule DSLIMPORT;
	public Rule DSLAPPLICATION;
	public Rule DSLMODULE;
	public Rule DSLSERVICE;
	public Rule DSLRESOURCE;
	public Rule DSLCONSUMER;
	public Rule DSLSUBSCRIBE;
	public Rule DSLPUBLISH;
	public Rule DSLEVENT;
	public Rule DSLDOMAINOBJECTTYPEDELEMENT;
	public Rule DSLSERVICEOPERATION;
	public Rule DSLSERVICEOPERATIONDELEGATE;
	public Rule DSLSERVICEREPOSITORYOPTION;
	public Rule DSLSERVICEREPOSITORYOPERATIONOPTION;
	public Rule DSLRESOURCEOPERATION;
	public Rule DSLRESOURCEOPERATIONDELEGATE;
	public Rule DSLREPOSITORYOPERATION;
	public Rule DSLPARAMETER;
	public Rule DSLCOMPLEXTYPE;
	public Rule DSLSIMPLEDOMAINOBJECT;
	public Rule DSLDOMAINOBJECT;
	public Rule DSLENTITY;
	public Rule DSLVALUEOBJECT;
	public Rule DSLDOMAINEVENT;
	public Rule DSLCOMMANDEVENT;
	public Rule DSLTRAIT;
	public Rule DSLDOMAINOBJECTOPERATION;
	public Rule DSLDATATRANSFEROBJECT;
	public Rule DSLBASICTYPE;
	public Rule DSLATTRIBUTE;
	public Rule DSLREFERENCE;
	public Rule DSLDTOATTRIBUTE;
	public Rule DSLDTOREFERENCE;
	public Rule DSLOPPOSITEHOLDER;
	public Rule DSLREPOSITORY;
	public Rule DSLSERVICEDEPENDENCY;
	public Rule DSLDEPENDENCY;
	public Rule DSLENUM;
	public Rule DSLENUMATTRIBUTE;
	public Rule DSLENUMVALUE;
	public Rule DSLENUMPARAMETER;
	public Rule DSLANYPROPERTY;
	public Rule DSLPROPERTY;
	public Rule DSLDTOPROPERTY;
	public Rule DSLTYPE;
	public Rule DSLJAVAIDENTIFIER;
	public Rule DSLCHANNELIDENTIFIER;
	public Rule DSLTHROWSIDENTIFIER;

	@Override
	public Rule getRootRule() {
		// TODO Auto-generated method stub
		return DSLMODEL;
	}
}
