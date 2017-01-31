package org.sonar.Items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ProgressReport implements Runnable {

  private final long period;
  private final Logger logger;
  private int files;
  private int count;
  private File currentFile;
  private final Thread thread;

  public ProgressReport(String threadName, long period, Logger logger) {
    this.period = period;
    this.logger = logger;
    thread = new Thread(this);
    thread.setName(threadName);
  }

  public ProgressReport(String threadName, long period) {
    this(threadName, period, LoggerFactory.getLogger(ProgressReport.class));
  }

  public void run() {
    while (!Thread.interrupted()) {
      try {
        Thread.sleep(period);
        synchronized (ProgressReport.this) {
          logger.info(count + "/" + files + " files analyzed, current is " + currentFile.getAbsolutePath());
        }
      } catch (InterruptedException e) {
        thread.interrupt();
      }
    }
    synchronized (ProgressReport.this) {
      logger.info(files + "/" + files + " source files analyzed");
    }
  }

  public synchronized void start(int files) {
    this.files = files;
    logger.info(files + " source files to be analyzed");
    thread.start();
  }

  public synchronized void nextFile(File currentFile) {
    count++;
    this.currentFile = currentFile;
  }

  public synchronized void stop() {
    thread.interrupt();
  }

}
