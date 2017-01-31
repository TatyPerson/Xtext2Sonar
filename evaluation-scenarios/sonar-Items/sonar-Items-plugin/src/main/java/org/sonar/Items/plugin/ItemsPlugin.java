package org.sonar.Items.plugin;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.PropertyType;
import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;
import org.sonar.Items.plugin.coverage.ItemsCoverageSensor;
import org.sonar.Items.plugin.externalrules.ItemsExternalRuleRepository;
import org.sonar.Items.plugin.externalrules.ItemsExternalRulesSensor;
import org.sonar.Items.plugin.squid.ItemsSquidSensor;
import org.sonar.Items.plugin.utils.ItemsMetrics;
import org.sonar.Items.plugin.xunit.ItemsXunitSensor;

import com.google.common.collect.ImmutableList;

/**
 * {@inheritDoc}
 */
public final class ItemsPlugin extends SonarPlugin {
  static final String SOURCE_FILE_SUFFIXES_KEY = "sonar.items.suffixes.sources";
  public static final String FILE_SUFFIXES_DEFVALUE = ".items";
  public static final String HEADER_FILE_SUFFIXES_KEY = "sonar.items.suffixes.headers";
  public static final String DEFINES_KEY = "sonar.items.defines";  
  public static final String INCLUDE_DIRECTORIES_KEY = "sonar.items.includeDirectories";
  public static final String ERROR_RECOVERY_KEY = "sonar.items.errorRecoveryEnabled";
  public static final String FORCE_INCLUDE_FILES_KEY = "sonar.items.forceIncludes";
  public static final String MISSING_INCLUDE_WARN = "sonar.items.missingIncludeWarnings";

  private static List<PropertyDefinition> generalProperties() {
    String subcateg = "(1) General";
    return ImmutableList.of(
    
      PropertyDefinition.builder(SOURCE_FILE_SUFFIXES_KEY)
      .defaultValue(ItemsLanguage.DEFAULT_FILE_SUFFIXES)
      .name("Source files suffixes")
      .description("Comma-separated list of suffixes for source files to analyze. Leave empty to use the default.")
      .subCategory(subcateg)
      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
      .index(1)
      .build(),

      PropertyDefinition.builder(DEFINES_KEY)
      .name("Default macros")
      .description("Additional macro definitions (one per line) to use when analysing the source code. Use to provide macros which cannot be resolved by other means."
                   + " Use the 'force includes' setting to inject more complex, multi-line macros.")
      .subCategory(subcateg)
      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
      .type(PropertyType.TEXT)
      .index(5)
      .build(),     

      PropertyDefinition.builder(ItemsPlugin.ERROR_RECOVERY_KEY)
      .defaultValue("False")
      .name("Parse error recovery")
      .description("Enables/disables the parse error recovery (experimental).")
      .subCategory(subcateg)
      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
      .type(PropertyType.BOOLEAN)
      .index(7)              
      .build(),
      PropertyDefinition.builder(ItemsPlugin.MISSING_INCLUDE_WARN)
      .defaultValue("True")
      .name("Missing include warnings")
      .description("Enables/disables the warnings when included files could not be found.")
      .subCategory(subcateg)
      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
      .type(PropertyType.BOOLEAN)
      .index(8)   
      .build()
      );
  }

	  private static List<PropertyDefinition> compilerWarningsProperties() {
	    return ImmutableList.of();
	  }

	  private static List<PropertyDefinition> testingAndCoverageProperties() {
	    String subcateg = "(3) Testing & Coverage";
	    return ImmutableList.of(
	      PropertyDefinition.builder(ItemsXunitSensor.REPORT_PATH_KEY)
	      .name("Unit test execution report(s)")
	      .description("Path to unit test execution report(s), relative to projects root."
	                   + " See <a href='https://github.com/wenns/sonar-cxx/wiki/Get-test-execution-metrics' for supported formats."
	                   + " Use <a href='https://ant.apache.org/manual/dirtasks.html'>Ant-style wildcards</a> if neccessary.")
	      .subCategory(subcateg)
	      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	      .index(5)
	      .build(),

	      PropertyDefinition.builder(ItemsXunitSensor.XSLT_URL_KEY)
	      .name("XSLT transformer")
	      .description("By default, the unit test execution reports are expected to be in the JUnitReport format."
	                   + " To import a report in an other format, set this property to an URL to a XSLT stylesheet which is able to perform the according transformation.")
	      .subCategory(subcateg)
	      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	      .index(6)
	      .build(),

	      PropertyDefinition.builder(ItemsXunitSensor.PROVIDE_DETAILS_KEY)
	      .name("Provide test execution details")
	      .description("If 'True', tries to assign testcases in report to test resources in SonarQube, "
	                   + "thus making the drillown to details possible")
	      .defaultValue("False")
	      .subCategory(subcateg)
	      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	      .type(PropertyType.BOOLEAN)
	      .index(7)
	      .build());
	  }

  /**
   * {@inheritDoc}
   */
  public List<Object> getExtensions() {
    List<Object> l = new ArrayList<Object>();
    l.add(ItemsLanguage.class);
    l.add(ItemsMetrics.class);
    l.add(ItemsSquidSensor.class);
    l.add(ItemsCpdMapping.class);
    l.add(ItemsXunitSensor.class);
    l.add(ItemsCoverageSensor.class);
    l.add(ItemsDefaultProfile.class);
    l.add(ItemsCommonRulesEngine.class);
    l.add(ItemsCommonRulesDecorator.class);
    l.add(ItemsRuleRepository.class);
    l.add(ItemsExternalRulesSensor.class);
    l.add(ItemsExternalRuleRepository.class);
    l.addAll(generalProperties());
    l.addAll(testingAndCoverageProperties());
    l.addAll(compilerWarningsProperties());

    return l;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
