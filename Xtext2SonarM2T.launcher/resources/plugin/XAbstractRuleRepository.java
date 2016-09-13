package org.sonar.NAME.plugin.utils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.platform.ServerFileSystem;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.NAME.plugin.NAMELanguage;

/**
 * {@inheritDoc}
 */
public abstract class NAMEAbstractRuleRepository implements RulesDefinition {

  private final ServerFileSystem fileSystem;
  private final Settings settings;
  private final RulesDefinitionXmlLoader xmlRuleLoader;
  protected final String repositoryKey;
  protected final String repositoryName;
  protected final String customRepositoryKey;

  /**
   * {@inheritDoc}
   */
  public NAMEAbstractRuleRepository(ServerFileSystem fileSystem, RulesDefinitionXmlLoader xmlRuleLoader, Settings settings, String key, String name, String customKey) {
    this.fileSystem = fileSystem;
    this.xmlRuleLoader = xmlRuleLoader;
    this.repositoryKey = key;
    this.repositoryName = name;
    this.customRepositoryKey = customKey;
    this.settings = settings;
  }

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(repositoryKey, NAMELanguage.KEY).setName(repositoryName);

    RulesDefinitionXmlLoader xmlLoader = new RulesDefinitionXmlLoader();
    if (!"".equals(fileName())) {
      InputStream xmlStream = getClass().getResourceAsStream(fileName());
      xmlLoader.load(repository, xmlStream, "UTF-8");

      for (File userExtensionXml : fileSystem.getExtensions(repositoryKey, "xml")) { //@todo getExtensions: deprecated, see http://javadocs.sonarsource.org/4.5.2/apidocs/deprecated-list.html
        try {
          FileReader reader = new FileReader(userExtensionXml);
          xmlRuleLoader.load(repository, reader);
        } catch (Exception ex) {
          NAMEUtils.LOG.info("Cannot Load XML '{}'", ex.getMessage());
        }
      }
    }

    String customRules = settings.getString(this.customRepositoryKey);
    if (StringUtils.isNotBlank(customRules)) {
      xmlRuleLoader.load(repository, new StringReader(customRules));
    }
    repository.done();
  }

  protected abstract String fileName();
}
