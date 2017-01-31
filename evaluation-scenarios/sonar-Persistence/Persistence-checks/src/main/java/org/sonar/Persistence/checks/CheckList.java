package org.sonar.Persistence.checks;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class CheckList {

  public static final String REPOSITORY_KEY = "persistence";

  public static final String DEFAULT_PROFILE = "Sonar way";

  private CheckList() {
  }
  
  @SuppressWarnings("rawtypes")
  public static List<Class> getChecks() {
    return ImmutableList.<Class>of(
    	FileComplexityCheck.class,
    	TooLongLineCheck.class
    );
  }

}
