package org.sonar.Sitemap.plugin.xunit;

import java.io.File;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.resources.Project;

public interface ResourceFinder {

  public org.sonar.api.resources.File findInSonar(File file, SensorContext context, FileSystem fs, Project project);
}
