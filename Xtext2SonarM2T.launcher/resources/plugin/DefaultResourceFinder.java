package org.sonar.NAME.plugin.xunit;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;
import org.sonar.api.batch.fs.FileSystem;

public class DefaultResourceFinder implements ResourceFinder {

  public org.sonar.api.resources.File findInSonar(File file, SensorContext context, FileSystem fs, Project project) {
    List<File> files = Lists.newArrayList(fs.files(fs.predicates().is(file)));
    assert (files.size() <= 1);
    return files.size() == 1
      ? org.sonar.api.resources.File.fromIOFile(files.get(0), project) //@todo fromIOFile: deprecated, see http://javadocs.sonarsource.org/4.5.2/apidocs/deprecated-list.html
      : null;
  }
}
