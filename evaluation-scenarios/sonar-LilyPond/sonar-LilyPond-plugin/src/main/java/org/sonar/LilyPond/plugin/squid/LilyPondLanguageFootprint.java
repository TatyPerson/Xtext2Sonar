package org.sonar.LilyPond.plugin.squid;

import java.util.HashSet;
import java.util.Set;

import org.sonar.squid.recognizer.ContainsDetector;
import org.sonar.squid.recognizer.Detector;
import org.sonar.squid.recognizer.EndWithDetector;
import org.sonar.squid.recognizer.KeywordsDetector;
import org.sonar.squid.recognizer.LanguageFootprint;

/**
 * {@inheritDoc}
 */
public final class LilyPondLanguageFootprint implements LanguageFootprint {

  /**
   * {@inheritDoc}
   */
  public Set<Detector> getDetectors() {
    final Set<Detector> detectors = new HashSet<Detector>();
    /*detectors.add(new EndWithDetector(0.95, '}', ';', '{'));
    detectors.add(new KeywordsDetector(0.7, "||", "&&"));
    detectors.add(new KeywordsDetector(0.95, "fin_algoritmo", "fin_modulo", "#endif", "#ifdef", "#ifndef", "#include"));
    detectors.add(new KeywordsDetector(0.3, "fin_algoritmo", "class", "do", "double", "float", "for", "int", "long", "mutable", "namespace",
        "operator", "private", "protected", "public", "return", "sizeof", "short", "static", "struct", "template", "throw", "typedef",
        "typename", "union", "void", "while"));
    detectors.add(new ContainsDetector(0.95, "++", "--"));*/
    return detectors;
  }

}
