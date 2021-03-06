package org.sonar.VaryGrammar.plugin.xunit;

import org.sonar.api.config.Settings;
import org.sonar.plugins.dotnet.tests.UnitTestConfiguration;
import org.sonar.plugins.dotnet.tests.UnitTestResultsAggregator;
import org.sonar.plugins.dotnet.tests.UnitTestResultsImportSensor;

public class MSTestResultsProvider {

  public static final String VISUAL_STUDIO_TEST_RESULTS_PROPERTY_KEY = "sonar.varygrammar.vstest.reportsPaths";
  private static final UnitTestConfiguration UNIT_TEST_CONF = new UnitTestConfiguration(VISUAL_STUDIO_TEST_RESULTS_PROPERTY_KEY);
  
  private MSTestResultsProvider() {
  }

  public static class MSTestResultsAggregator extends UnitTestResultsAggregator {

    public MSTestResultsAggregator(Settings settings) {
      super(UNIT_TEST_CONF, settings);
    }

  }

  public static class MSTestResultsImportSensor extends UnitTestResultsImportSensor {
    
    public MSTestResultsImportSensor(MSTestResultsAggregator unitTestResultsAggregator) {
      super(unitTestResultsAggregator);
    }

  }
}
