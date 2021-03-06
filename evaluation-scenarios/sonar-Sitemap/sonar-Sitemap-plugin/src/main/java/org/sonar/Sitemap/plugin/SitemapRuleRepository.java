package org.sonar.Sitemap.plugin;

import java.util.List;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.Sitemap.checks.CheckList;
import org.sonar.squidbridge.annotations.AnnotationBasedRulesDefinition;

public class SitemapRuleRepository implements RulesDefinition {
  @Override
  public void define(Context context) {
	  final String languageKey = SitemapLanguage.KEY;
      final String repositoryKey = CheckList.REPOSITORY_KEY;

      @SuppressWarnings("rawtypes")
      final List<Class> list = CheckList.getChecks();

      final NewRepository repository
          = context.createRepository(repositoryKey, languageKey)
          .setName("SonarQube");

      AnnotationBasedRulesDefinition.load(repository, languageKey, list);
      
      for (NewRule rule : repository.rules()) {
          //FIXME: set internal key to key to ensure rule templates works properly : should be removed when SONAR-6162 is fixed.
          rule.setInternalKey(rule.key());
      }

      repository.done();
  }
  
  
}
