package org.sonar.Persistence;

import java.nio.charset.Charset;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.rule.ActiveRule;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.squidbridge.api.SquidConfiguration;

public class PersistenceConfiguration extends SquidConfiguration {

	  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger("PersistenceConfiguration");
	  private String baseDir;
	  private boolean errorRecoveryEnabled = true;
	  private ResourcePerspectives perspectives;
	  private FileSystem fs;
	  private ActiveRule activeRule;
	  
	  public PersistenceConfiguration() {
	  }

	  public PersistenceConfiguration(Charset encoding) {
	    super(encoding);   
	  }
	  
	  public PersistenceConfiguration(FileSystem fs) {
	    super(fs.encoding());   
	    this.fs = fs;
	  }
	  
	  public PersistenceConfiguration(FileSystem fs,
	          ResourcePerspectives perspectivesIn,
	          ActiveRule activeRule) {
	    super(fs.encoding());   
	    this.fs = fs;
	    perspectives = perspectivesIn;
	    this.activeRule = activeRule;
	  }

	  public void setBaseDir(String baseDir) {
	    this.baseDir = baseDir;
	  }

	  public String getBaseDir() {
	    return baseDir;
	  }

	  public void setErrorRecoveryEnabled(boolean errorRecoveryEnabled){
	    this.errorRecoveryEnabled = errorRecoveryEnabled;
	  }

	  public boolean getErrorRecoveryEnabled(){
	    return this.errorRecoveryEnabled;
	  }
	}