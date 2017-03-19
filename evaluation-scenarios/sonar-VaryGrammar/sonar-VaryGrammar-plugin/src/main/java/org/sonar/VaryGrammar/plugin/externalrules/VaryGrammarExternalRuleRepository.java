package org.sonar.VaryGrammar.plugin.externalrules;

import java.io.StringReader;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.VaryGrammar.plugin.VaryGrammarLanguage;
import org.sonar.VaryGrammar.plugin.utils.VaryGrammarUtils;

/**
 * Loads the external rules configuration file.
 */
public class VaryGrammarExternalRuleRepository implements RulesDefinition {

  public static final String KEY = "varygrammar other";
  public static final String RULES_KEY = "sonar.varygrammar.other.rules";
  private final Settings settings;
  private final RulesDefinitionXmlLoader xmlRuleLoader;
  private static final String VaryGrammar = "VaryGrammar Other";

  public VaryGrammarExternalRuleRepository(RulesDefinitionXmlLoader xmlRuleLoader, Settings settings) {
    this.xmlRuleLoader = xmlRuleLoader;
    this.settings = settings;
  }

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(KEY, VaryGrammarLanguage.KEY).setName(VaryGrammar);
    xmlRuleLoader.load(repository, getClass().getResourceAsStream("/resources/external-rule.xml"), "UTF-8");

    for (String ruleDefs : settings.getStringArray(RULES_KEY)) {
      if (StringUtils.isNotBlank(ruleDefs)) {
        try {
          xmlRuleLoader.load(repository, new StringReader(ruleDefs));
        } catch (Exception ex) {
          VaryGrammarUtils.LOG.info("Cannot Load XML '{}'", ex.getMessage());
        }
      }
    }

    //i18nLoader.load(repository); //@todo?
    repository.done();
  }

}
