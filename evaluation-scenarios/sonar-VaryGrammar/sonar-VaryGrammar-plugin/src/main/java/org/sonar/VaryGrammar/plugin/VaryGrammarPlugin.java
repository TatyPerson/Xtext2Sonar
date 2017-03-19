package org.sonar.VaryGrammar.plugin;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.PropertyType;
import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;
import org.sonar.VaryGrammar.plugin.coverage.VaryGrammarCoverageSensor;
import org.sonar.VaryGrammar.plugin.externalrules.VaryGrammarExternalRuleRepository;
import org.sonar.VaryGrammar.plugin.externalrules.VaryGrammarExternalRulesSensor;
import org.sonar.VaryGrammar.plugin.squid.VaryGrammarSquidSensor;
import org.sonar.VaryGrammar.plugin.utils.VaryGrammarMetrics;
import org.sonar.VaryGrammar.plugin.xunit.VaryGrammarXunitSensor;

import com.google.common.collect.ImmutableList;

/**
 * {@inheritDoc}
 */
public final class VaryGrammarPlugin extends SonarPlugin {
  static final String SOURCE_FILE_SUFFIXES_KEY = "sonar.varygrammar.suffixes.sources";
  public static final String FILE_SUFFIXES_DEFVALUE = ".varygrammar";
  public static final String HEADER_FILE_SUFFIXES_KEY = "sonar.varygrammar.suffixes.headers";
  public static final String DEFINES_KEY = "sonar.varygrammar.defines";  
  public static final String INCLUDE_DIRECTORIES_KEY = "sonar.varygrammar.includeDirectories";
  public static final String ERROR_RECOVERY_KEY = "sonar.varygrammar.errorRecoveryEnabled";
  public static final String FORCE_INCLUDE_FILES_KEY = "sonar.varygrammar.forceIncludes";
  public static final String MISSING_INCLUDE_WARN = "sonar.varygrammar.missingIncludeWarnings";

  private static List<PropertyDefinition> generalProperties() {
    String subcateg = "(1) General";
    return ImmutableList.of(
    
      PropertyDefinition.builder(SOURCE_FILE_SUFFIXES_KEY)
      .defaultValue(VaryGrammarLanguage.DEFAULT_FILE_SUFFIXES)
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

      PropertyDefinition.builder(VaryGrammarPlugin.ERROR_RECOVERY_KEY)
      .defaultValue("False")
      .name("Parse error recovery")
      .description("Enables/disables the parse error recovery (experimental).")
      .subCategory(subcateg)
      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
      .type(PropertyType.BOOLEAN)
      .index(7)              
      .build(),
      PropertyDefinition.builder(VaryGrammarPlugin.MISSING_INCLUDE_WARN)
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
	      PropertyDefinition.builder(VaryGrammarXunitSensor.REPORT_PATH_KEY)
	      .name("Unit test execution report(s)")
	      .description("Path to unit test execution report(s), relative to projects root."
	                   + " See <a href='https://github.com/wenns/sonar-cxx/wiki/Get-test-execution-metrics' for supported formats."
	                   + " Use <a href='https://ant.apache.org/manual/dirtasks.html'>Ant-style wildcards</a> if neccessary.")
	      .subCategory(subcateg)
	      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	      .index(5)
	      .build(),

	      PropertyDefinition.builder(VaryGrammarXunitSensor.XSLT_URL_KEY)
	      .name("XSLT transformer")
	      .description("By default, the unit test execution reports are expected to be in the JUnitReport format."
	                   + " To import a report in an other format, set this property to an URL to a XSLT stylesheet which is able to perform the according transformation.")
	      .subCategory(subcateg)
	      .onQualifiers(Qualifiers.PROJECT, Qualifiers.MODULE)
	      .index(6)
	      .build(),

	      PropertyDefinition.builder(VaryGrammarXunitSensor.PROVIDE_DETAILS_KEY)
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
    l.add(VaryGrammarLanguage.class);
    l.add(VaryGrammarMetrics.class);
    l.add(VaryGrammarSquidSensor.class);
    l.add(VaryGrammarCpdMapping.class);
    l.add(VaryGrammarXunitSensor.class);
    l.add(VaryGrammarCoverageSensor.class);
    l.add(VaryGrammarDefaultProfile.class);
    l.add(VaryGrammarCommonRulesEngine.class);
    l.add(VaryGrammarCommonRulesDecorator.class);
    l.add(VaryGrammarRuleRepository.class);
    l.add(VaryGrammarExternalRulesSensor.class);
    l.add(VaryGrammarExternalRuleRepository.class);
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
