package org.sslr.NAME.toolkit;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.sonar.sslr.api.Grammar;
import com.sonar.sslr.impl.Parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.colorizer.KeywordsTokenizer;
import org.sonar.colorizer.StringTokenizer;
import org.sonar.colorizer.Tokenizer;
import org.sonar.NAME.NAMEConfiguration;
import org.sonar.NAME.api.NAMEKeyword;
import org.sonar.NAME.parser.NAMEParser;
import org.sonar.squidbridge.SquidAstVisitorContext;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.squidbridge.api.SourceProject;
import org.sonar.sslr.toolkit.AbstractConfigurationModel;
import org.sonar.sslr.toolkit.ConfigurationProperty;
import org.sonar.sslr.toolkit.Validators;

import java.nio.charset.Charset;
import java.util.List;

public class NAMEConfigurationModel extends AbstractConfigurationModel {

  private static final Logger LOG = LoggerFactory.getLogger(NAMEConfigurationModel.class);

  private static final String CHARSET_PROPERTY_KEY = "sonar.sourceEncoding";

  @VisibleForTesting
  ConfigurationProperty charsetProperty = new ConfigurationProperty("Charset", CHARSET_PROPERTY_KEY,
    getPropertyOrDefaultValue(CHARSET_PROPERTY_KEY, "UTF-8"),
    Validators.charsetValidator());

  @Override
  public List<ConfigurationProperty> getProperties() {
    return ImmutableList.of(charsetProperty);
  }

  @Override
  public Charset getCharset() {
    return Charset.forName(charsetProperty.getValue());
  }

  @Override
  public Parser<? extends Grammar> doGetParser() {
    SquidAstVisitorContext<Grammar> context
      = new SquidAstVisitorContextImpl<>(new SourceProject(""));
    return NAMEParser.create(context, getConfiguration());
  }

  @Override
  public List<Tokenizer> doGetTokenizers() {
    return ImmutableList.of(
      new StringTokenizer("<span class=\"s\">", "</span>"),
      new KeywordsTokenizer("<span class=\"k\">", "</span>", NAMEKeyword.keywordValues()));
  }

  @VisibleForTesting
  NAMEConfiguration getConfiguration() {
    NAMEConfiguration config = new NAMEConfiguration(getCharset());
    config.setErrorRecoveryEnabled(false);
    return config;
  }

  @VisibleForTesting
  static String getPropertyOrDefaultValue(String propertyKey, String defaultValue) {
    String propertyValue = System.getProperty(propertyKey);

    if (propertyValue == null) {
      LOG.info("The property \"" + propertyKey + "\" is not set, using the default value \"" + defaultValue + "\".");
      return defaultValue;
    } else {
      LOG.info("The property \"" + propertyKey + "\" is set, using its value \"" + propertyValue + "\".");
      return propertyValue;
    }
  }

}
