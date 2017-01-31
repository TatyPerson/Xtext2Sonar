package org.sonar.Persistence;

import com.sonar.sslr.api.AstNode;
import org.sonar.squidbridge.AstScanner;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.SquidAstVisitorContextImpl;
import org.sonar.sslr.parser.LexerlessGrammar;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class ProgressAstScanner extends AstScanner<LexerlessGrammar> {

  private final ProgressReport progressReport;

  protected ProgressAstScanner(Builder builder) {
    super(builder);
    this.progressReport = builder.progressReport;
  }

  @Override
  public void scanFiles(Collection<File> files) {
    progressReport.start(files.size());
    super.scanFiles(files);
    progressReport.stop();
  }

  public static class Builder extends AstScanner.Builder<LexerlessGrammar> {

    private final ProgressReport progressReport = new ProgressReport("Report about progress of ActionScript analyzer", TimeUnit.SECONDS.toMillis(10));

    public Builder(SquidAstVisitorContextImpl<LexerlessGrammar> context) {
      super(context);
    }

    @Override
    public AstScanner<LexerlessGrammar> build() {
      super.withSquidAstVisitor(new SquidAstVisitor<LexerlessGrammar>() {
        @Override
        public void visitFile(@Nullable AstNode astNode) {
          progressReport.nextFile(getContext().getFile());
        }
      });
      return new ProgressAstScanner(this);
    }

  }

}
