package org.sonar.Items.plugin.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.resources.Project;

/**
 * Utility class holding various, well, utilities
 */
public final class ItemsUtils {

  /**
   * Default logger.
   */
  public static final Logger LOG = LoggerFactory.getLogger("ItemsPlugin");

  private ItemsUtils() {
    // only static methods
  }

  /**
   * @param file
   * @return Returns file path of provided file, or "null" if file == null
   */
  public static String fileToAbsolutePath(File file) {
    if (file == null) {
      return "null";
    }
    return file.getAbsolutePath();
  }

  /**
   * Normalize the given path to pass it to sonar. Return null if normalization
   * has failed.
   */
  public static String normalizePath(String filename) {
    try {
      return new File(filename).getCanonicalPath();
    } catch (java.io.IOException e) {
      LOG.error("path normalizing of '{}' failed: '{}'", filename, e.toString());
      return null;
    }
  }

  /**
   * @return returns case sensitive full path
   */
  public static String normalizePathFull(String filename, String baseDir) {
    String filePath = filename;   
    File targetfile = new java.io.File(filename);
    if (targetfile.exists()) {
      filePath = normalizePath(filename);
    } else {
      // RATS, CppCheck and Vera++ provide names like './file.cpp' - add source folder for index check
      filePath = normalizePath(baseDir + File.separator + filename);
    }
    return filePath;
  }

  public static boolean isReactorProject(Project project) {
    return project.isRoot() && !project.getModules().isEmpty();
  }
}

