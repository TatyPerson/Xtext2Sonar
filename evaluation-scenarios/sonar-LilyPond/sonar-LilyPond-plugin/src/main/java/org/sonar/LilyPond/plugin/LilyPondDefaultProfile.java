package org.sonar.LilyPond.plugin;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.utils.ValidationMessages;
import org.sonar.squidbridge.annotations.AnnotationBasedProfileBuilder;
import org.sonar.LilyPond.checks.CheckList;

/**
 * {@inheritDoc}
 */
public class LilyPondDefaultProfile extends ProfileDefinition {
	  private final RuleFinder ruleFinder;

	  public LilyPondDefaultProfile(RuleFinder ruleFinder) {
	    this.ruleFinder = ruleFinder;
	  }

	  @Override
	  public RulesProfile createProfile(ValidationMessages messages) {
	    AnnotationBasedProfileBuilder annotationBasedProfileBuilder = new AnnotationBasedProfileBuilder(ruleFinder);
	    return annotationBasedProfileBuilder.build(CheckList.REPOSITORY_KEY, "Sonar way", LilyPondLanguage.KEY, CheckList.getChecks(), messages);
	  }
}
