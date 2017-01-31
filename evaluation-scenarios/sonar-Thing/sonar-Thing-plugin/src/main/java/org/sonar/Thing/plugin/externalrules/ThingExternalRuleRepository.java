package org.sonar.Thing.plugin.externalrules;

import java.io.StringReader;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.Thing.plugin.ThingLanguage;
import org.sonar.Thing.plugin.utils.ThingUtils;

/**
 * Loads the external rules configuration file.
 */
public class ThingExternalRuleRepository implements RulesDefinition {

  public static final String KEY = "thing other";
  public static final String RULES_KEY = "sonar.thing.other.rules";
  private final Settings settings;
  private final RulesDefinitionXmlLoader xmlRuleLoader;
  private static final String Thing = "Thing Other";

  public ThingExternalRuleRepository(RulesDefinitionXmlLoader xmlRuleLoader, Settings settings) {
    this.xmlRuleLoader = xmlRuleLoader;
    this.settings = settings;
  }

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(KEY, ThingLanguage.KEY).setName(Thing);
    xmlRuleLoader.load(repository, getClass().getResourceAsStream("/resources/external-rule.xml"), "UTF-8");

    for (String ruleDefs : settings.getStringArray(RULES_KEY)) {
      if (StringUtils.isNotBlank(ruleDefs)) {
        try {
          xmlRuleLoader.load(repository, new StringReader(ruleDefs));
        } catch (Exception ex) {
          ThingUtils.LOG.info("Cannot Load XML '{}'", ex.getMessage());
        }
      }
    }

    //i18nLoader.load(repository); //@todo?
    repository.done();
  }

}
