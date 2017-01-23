/*Copyright (C) 2016  Tatiana Person Montero

This program is free software: you can redistribute it and/or modify
it under the terms of the Eclipse Public License as published by
the Eclipse Software Foundation, either version 1 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
Eclipse Public License for more details.

You should have received a copy of the Eclipse Public License
along with this program.  If not, see <https://www.eclipse.org/legal/epl-v10.html>*/
package Xtext2SonarM2T.launcher.popupMenus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.eclipse.acceleo.common.preference.AcceleoPreferences;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import Xtext2SonarM2T.launcher.Activator;
import Xtext2SonarM2T.launcher.common.GenerateAll;

import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

/**
 * Generate SonarQube Plugin  code generation.
 */
public class AcceleoGenerateGenerateSonarQubePluginAction extends ActionDelegate implements IActionDelegate {
	
	/**
	 * Selected model files.
	 */
	protected List<IFile> files;
	String name;
	
	//protected static final Logger logger = Logger.getLogger(AcceleoGenerateGenerateSonarQubePluginAction.class);

	/**{@inheritDoc}
	 *
	 * @see org.eclipse.ui.actions.ActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			files = ((IStructuredSelection) selection).toList();
		}
	}
	
	/**
	 * @author Tatiana Person Montero
	 * @param project
	 * @param pathFileLoad
	 * @param pathFileTarget
	 * @param monitor
	 * @param name
	 * @throws CoreException
	 * @throws IOException
	 */
	private void addFileToProject(IProject project, String pathFileLoad, String pathFileTarget, IProgressMonitor monitor, String name, boolean pom) throws CoreException, IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream(pathFileLoad);
		System.out.println("input = "+ input.available());
		IFile target = project.getFile(pathFileTarget);
		target.create(input, true, monitor);
		updateNaming(target, name, pom);
		System.out.println("Update naming Done!");
		input.close();
	}
	
	private void addSourceFolder(IProject project, IPath[] excludePatterns, IFolder sourcefolder, IProgressMonitor monitor) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(project);
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourcefolder);
		IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
		IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
		System.out.println("Root path: "+root.getPath());
		System.out.println("Exclude paths: "+excludePatterns[0].toString() + " - " +excludePatterns[1].toString());
		newEntries[oldEntries.length] = JavaCore.newSourceEntry(root.getPath(), excludePatterns);
		javaProject.setRawClasspath(newEntries, monitor);
	}
	
	/**
	 * @author Tatiana Person Montero	
	 * @param project
	 * @param monitor
	 * @throws CoreException 
	 */
	private void setJRE(IProject project, IProgressMonitor monitor) throws CoreException {
		
		project.setDefaultCharset("UTF-8", null);
		IJavaProject javaProject = JavaCore.create(project);
		Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
		//entries.addAll(Arrays.asList(javaProject.getRawClasspath()));
		IVMInstall vmInstall= JavaRuntime.getDefaultVMInstall();
		LibraryLocation[] locations= JavaRuntime.getLibraryLocations(vmInstall);

		for (LibraryLocation element : locations) {
			entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
		}
		
		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), monitor);
	}
	
	private Model pomToModel(String pathToPom) throws Exception {
	    BufferedReader in = new BufferedReader(new FileReader(pathToPom));
	    MavenXpp3Reader reader = new MavenXpp3Reader();
	    Model model = reader.read(in);
	    return model;
	}
	
	/**
	 * @author Tatiana Person Montero
	 * @param name : model name
	 * @throws Exception 
	 */
	private void createMavenProjects(String name, IProgressMonitor monitor) throws Exception {
		//Delete .xmi of the name
		name = name.replaceAll(".xmi", "");
		this.name = name;
		
		//1) sonar-name project
		String projectMainName = "sonar-" + name;
		IProject projectMain = ResourcesPlugin.getWorkspace().getRoot().getProject(projectMainName);
		createMavenProject(projectMain, monitor, true);
		
		//------ Copy the pom.xml file
		addFileToProject(projectMain,"resources/pom/pomMain.xml", "pom.xml", monitor, name, true);
		
		//2) sonar-name-plugin project
		String projectPluginName = "sonar-" + name + "-plugin";
		IProject projectPlugin = ResourcesPlugin.getWorkspace().getRoot().getProject(projectPluginName);
		createMavenProject(projectPlugin, monitor, false);
		IFolder sourcefolderPlugin = projectPlugin.getFolder("src/");
		IFolder sourcefoldermainPlugin = projectPlugin.getFolder("src/main/");
		IFolder sourcefolderjavaPlugin = projectPlugin.getFolder("src/main/java/");
		sourcefolderPlugin.create(false, true, monitor);
		sourcefoldermainPlugin.create(false, true, monitor);
		sourcefolderjavaPlugin.create(false, true, monitor);
		setJRE(projectPlugin, monitor);
		addSourceFolder(projectPlugin, new IPath[] {sourcefolderPlugin.getProjectRelativePath(), sourcefoldermainPlugin.getProjectRelativePath()}, sourcefolderjavaPlugin, monitor);
		
		//------ Copy the pom.xml file
		addFileToProject(projectPlugin,"resources/pom/pomPlugin.xml", "pom.xml", monitor, name, true);
		
		//========== Copy the basic Java files to the Plugin project
		IJavaProject javaProjectPlugin = JavaCore.create(projectPlugin);
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("org.sonar." + name +".plugin", false, null);
		addFileToProject(projectPlugin,"resources/plugin/XCommonRulesDecorator.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "CommonRulesDecorator.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XCommonRulesEngine.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "CommonRulesEngine.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XCpdMapping.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "CpdMapping.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XDefaultProfile.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "DefaultProfile.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XLanguage.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "Language.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XPlugin.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "Plugin.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XRuleRepository.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "RuleRepository.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XTokenizer.java", "src/main/java/" + "org/sonar/" + name + "/plugin/" + name + "Tokenizer.java", monitor, name, false);
		
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("org.sonar." + name +".plugin.coverage", false, null);
		addFileToProject(projectPlugin,"resources/plugin/BullseyeParser.java", "src/main/java/" + "org/sonar/" + name + "/plugin/coverage/BullseyeParser.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/CoberturaParser.java", "src/main/java/" + "org/sonar/" + name + "/plugin/coverage/CoberturaParser.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/CoverageParser.java", "src/main/java/" + "org/sonar/" + name + "/plugin/coverage/CoverageParser.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XCoverageSensor.java", "src/main/java/" + "org/sonar/" + name + "/plugin/coverage/" + name + "CoverageSensor.java", monitor, name, false);
		
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("org.sonar." + name +".plugin.externalrules", false, null);
		addFileToProject(projectPlugin,"resources/plugin/XExternalRuleRepository.java", "src/main/java/" + "org/sonar/" + name + "/plugin/externalrules/"+ name + "ExternalRuleRepository.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XExternalRulesSensor.java", "src/main/java/" + "org/sonar/" + name + "/plugin/externalrules/"+ name + "ExternalRulesSensor.java", monitor, name, false);
		
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("org.sonar." + name +".plugin.highlighter", false, null);
		addFileToProject(projectPlugin,"resources/plugin/SourceFileOffsets.java", "src/main/java/" + "org/sonar/" + name + "/plugin/highlighter/SourceFileOffsets.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XHighlighter.java", "src/main/java/" + "org/sonar/" + name + "/plugin/highlighter/"+ name + "Highlighter.java", monitor, name, false);
		
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("org.sonar." + name +".plugin.squid", false, null);
		addFileToProject(projectPlugin,"resources/plugin/DependencyAnalyzer.java", "src/main/java/" + "org/sonar/" + name + "/plugin/squid/DependencyAnalyzer.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/DirectoryEdge.java", "src/main/java/" + "org/sonar/" + name + "/plugin/squid/DirectoryEdge.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/DsmSerializer.java", "src/main/java/" + "org/sonar/" + name + "/plugin/squid/DsmSerializer.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/FileEdge.java", "src/main/java/" + "org/sonar/" + name + "/plugin/squid/FileEdge.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XLanguageFootprint.java", "src/main/java/" + "org/sonar/" + name + "/plugin/squid/" + name + "LanguageFootprint.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XSquidSensor.java", "src/main/java/" + "org/sonar/" + name + "/plugin/squid/" + name + "SquidSensor.java", monitor, name, false);
		
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("org.sonar." + name +".plugin.utils", false, null);
		addFileToProject(projectPlugin,"resources/plugin/EmptyReportException.java", "src/main/java/" + "org/sonar/" + name + "/plugin/utils/EmptyReportException.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XAbstractRuleRepository.java", "src/main/java/" + "org/sonar/" + name + "/plugin/utils/" + name + "AbstractRuleRepository.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XMetrics.java", "src/main/java/" + "org/sonar/" + name + "/plugin/utils/" + name + "Metrics.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XReportSensor.java", "src/main/java/" + "org/sonar/" + name + "/plugin/utils/" + name + "ReportSensor.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XUtils.java", "src/main/java/" + "org/sonar/" + name + "/plugin/utils/" + name + "Utils.java", monitor, name, false);
		
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("org.sonar." + name +".plugin.xunit", false, null);
		addFileToProject(projectPlugin,"resources/plugin/DefaultResourceFinder.java", "src/main/java/" + "org/sonar/" + name + "/plugin/xunit/DefaultResourceFinder.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/MSTestResultsProvider.java", "src/main/java/" + "org/sonar/" + name + "/plugin/xunit/MSTestResultsProvider.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/ResourceFinder.java", "src/main/java/" + "org/sonar/" + name + "/plugin/xunit/ResourceFinder.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/TestCase.java", "src/main/java/" + "org/sonar/" + name + "/plugin/xunit/TestCase.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/TestResource.java", "src/main/java/" + "org/sonar/" + name + "/plugin/xunit/TestResource.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XXunitSensor.java", "src/main/java/" + "org/sonar/" + name + "/plugin/xunit/"+ name + "XunitSensor.java", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/XunitReportParser.java", "src/main/java/" + "org/sonar/" + name + "/plugin/xunit/XunitReportParser.java", monitor, name, false);
		
		javaProjectPlugin.getPackageFragmentRoot(sourcefolderjavaPlugin).createPackageFragment("resources", false, null);
		addFileToProject(projectPlugin,"resources/plugin/default-profile.xml", "src/main/java/" + "resources/default-profile.xml", monitor, name, false);
		addFileToProject(projectPlugin,"resources/plugin/external-rule.xml", "src/main/java/" + "resources/external-rule.xml", monitor, name, false);
		
		//3) sslr-name-toolkit project
		String projectToolkitName = "sslr-" + name + "-toolkit";
		IProject projectToolkit = ResourcesPlugin.getWorkspace().getRoot().getProject(projectToolkitName);
		createMavenProject(projectToolkit, monitor, false);
		IFolder sourcefolderToolkit = projectToolkit.getFolder("src");
		IFolder sourcefoldermainToolkit = projectToolkit.getFolder("src/main");
		IFolder sourcefolderjavaToolkit = projectToolkit.getFolder("src/main/java");
		sourcefolderToolkit.create(false, true, monitor);
		sourcefoldermainToolkit.create(false, true, monitor);
		sourcefolderjavaToolkit.create(false, true, monitor);
		setJRE(projectToolkit, monitor);
		addSourceFolder(projectToolkit, new IPath[] {sourcefolderToolkit.getProjectRelativePath(), sourcefoldermainToolkit.getProjectRelativePath()}, sourcefolderjavaToolkit, monitor);
		
		//------ Copy the pom.xml file
		addFileToProject(projectToolkit,"resources/pom/pomToolkit.xml", "pom.xml", monitor, name, true);
		
		//========== Copy the basic Java files to the Toolkit project
		IJavaProject javaProjectToolkit = JavaCore.create(projectToolkit);
		javaProjectToolkit.getPackageFragmentRoot(sourcefolderjavaToolkit).createPackageFragment("org.sslr." + name +".toolkit", false, null);
		addFileToProject(projectToolkit,"resources/toolkit/XConfigurationModel.java", "src/main/java/" + "org/sslr/" + name + "/toolkit/" + name + "ConfigurationModel" + ".java", monitor, name, false);
		addFileToProject(projectToolkit,"resources/toolkit/XToolkit.java", "src/main/java/" + "org/sslr/" + name + "/toolkit/" + name + "Toolkit" + ".java", monitor, name, false);
		
		//MavenProjectBuilder mavenProjectBuilder = new MavenProjectBuilder(pomToModel(projectToolkit.getFullPath() + "/pom.xml"));
		//MavenProject mavenProject = new MavenProject(pomToModel(projectToolkit.getFullPath() + "/pom.xml"));
		
		//4) name-checks project
		String projectChecksName = name + "-checks";
		IProject projectChecks = ResourcesPlugin.getWorkspace().getRoot().getProject(projectChecksName);
		createMavenProject(projectChecks, monitor, false);
		IFolder sourcefolderChecks = projectChecks.getFolder("src/");
		IFolder sourcefoldermainChecks = projectChecks.getFolder("src/main/");
		IFolder sourcefolderjavaChecks = projectChecks.getFolder("src/main/java/");
		IFolder sourcefolderesourcesChecks = projectChecks.getFolder("src/main/resources/");
		sourcefolderChecks.create(false, true, monitor);
		sourcefoldermainChecks.create(false, true, monitor);
		sourcefolderjavaChecks.create(false, true, monitor);
		sourcefolderesourcesChecks.create(false, true, monitor);
		setJRE(projectChecks, monitor);
		addSourceFolder(projectChecks, new IPath[] {sourcefolderChecks.getProjectRelativePath(), sourcefoldermainChecks.getProjectRelativePath()}, sourcefolderjavaChecks, monitor);
		addSourceFolder(projectChecks, new IPath[] {sourcefolderChecks.getProjectRelativePath(), sourcefoldermainChecks.getProjectRelativePath()}, sourcefolderesourcesChecks, monitor);
		
		//------ Copy the pom.xml file
		addFileToProject(projectChecks,"resources/pom/pomChecks.xml", "pom.xml", monitor, name, true);
		
		//========== Copy the basic Java files to the Checks project
		IJavaProject javaProjectChecks = JavaCore.create(projectChecks);
		javaProjectChecks.getPackageFragmentRoot(sourcefolderjavaChecks).createPackageFragment("org.sonar." + name +".checks", false, null);
		addFileToProject(projectChecks,"resources/checks/CheckList.java", "src/main/java/" + "org/sonar/" + name + "/checks/CheckList.java", monitor, name, false);
		addFileToProject(projectChecks,"resources/checks/FileComplexityCheck.java", "src/main/java/" + "org/sonar/" + name + "/checks/FileComplexityCheck.java", monitor, name, false);
		addFileToProject(projectChecks,"resources/checks/TooLongLineCheck.java", "src/main/java/" + "org/sonar/" + name + "/checks/TooLongLineCheck.java", monitor, name, false);
		addFileToProject(projectChecks,"resources/checks/CompileIncludePathNotFoundOrInvalid.java", "src/main/java/" + "org/sonar/" + name + "/checks/CompileIncludePathNotFoundOrInvalid.java", monitor, name, false);
		
		//javaProjectChecks.getPackageFragmentRoot(sourcefolderesourcesChecks).createPackageFragment("org.sonar.l10n." + name +".rules." + name, false, null);
		//addFileToProject(projectChecks,"resources/checks/TooLongLine.html", "src/main/resources/" + "org/sonar/l10n/" + name + "/rules/" + name + "/TooLongLine.html", monitor, name, false);
		//addFileToProject(projectChecks,"resources/checks/FileComplexity.html", "src/main/resources/" + "org/sonar/l10n/" + name + "/rules/" + name + "/FileComplexity.html", monitor, name, false);
		//addFileToProject(projectChecks,"resources/checks/CompileIncludePathNotFoundOrInvalid.html", "src/main/resources/" + "org/sonar/l10n/" + name + "/rules/" + name + "/CompileIncludePathNotFoundOrInvalid.html", monitor, name, false);
		
		//4) name-squid project
		String projectSquidName = name + "-squid";
		IProject projectSquid = ResourcesPlugin.getWorkspace().getRoot().getProject(projectSquidName);
		createMavenProject(projectSquid, monitor, false);
		IFolder sourcefolderSquid = projectSquid.getFolder("src/");
		IFolder sourcefoldermainSquid = projectSquid.getFolder("src/main/");
		IFolder sourcefolderjavaSquid = projectSquid.getFolder("src/main/java/");
		sourcefolderSquid.create(false, true, monitor);
		sourcefoldermainSquid.create(false, true, monitor);
		sourcefolderjavaSquid.create(false, true, monitor);
		setJRE(projectSquid, monitor);
		addSourceFolder(projectSquid, new IPath[] {sourcefolderSquid.getProjectRelativePath(), sourcefoldermainSquid.getProjectRelativePath()}, sourcefolderjavaSquid, monitor);
		
		//------ Copy the pom.xml file
		addFileToProject(projectSquid,"resources/pom/pomSquid.xml", "pom.xml", monitor, name, true);
		
		//========== Copy the basic Java files to the Squid project
		IJavaProject javaProjectSquid = JavaCore.create(projectSquid);
		javaProjectSquid.getPackageFragmentRoot(sourcefolderjavaSquid).createPackageFragment("org.sonar." + name, false, null);
		addFileToProject(projectSquid,"resources/squid/ProgressAstScanner.java", "src/main/java/" + "org/sonar/" + name + "/ProgressAstScanner.java", monitor, name, false);
		addFileToProject(projectSquid,"resources/squid/ProgressReport.java", "src/main/java/" + "org/sonar/" + name + "/ProgressReport.java", monitor, name, false);
		addFileToProject(projectSquid,"resources/squid/XCommentAnalyser.java", "src/main/java/" + "org/sonar/" + name + "/" + name + "CommentAnalyser.java", monitor, name, false);
		addFileToProject(projectSquid,"resources/squid/XConfiguration.java", "src/main/java/" + "org/sonar/" + name + "/" + name + "Configuration.java", monitor, name, false);
		
		javaProjectSquid.getPackageFragmentRoot(sourcefolderjavaSquid).createPackageFragment("org.sonar." + name + ".api", false, null);
		addFileToProject(projectSquid,"resources/squid/XMetric.java", "src/main/java/" + "org/sonar/" + name + "/api/" + name + "Metric.java", monitor, name, false);
		
		javaProjectSquid.getPackageFragmentRoot(sourcefolderjavaSquid).createPackageFragment("org.sonar." + name + ".channels", false, null);
		addFileToProject(projectSquid,"resources/squid/CharacterLiteralsChannel.java", "src/main/java/" + "org/sonar/" + name + "/channels/CharacterLiteralsChannel.java", monitor, name, false);
		addFileToProject(projectSquid,"resources/squid/StringLiteralsChannel.java", "src/main/java/" + "org/sonar/" + name + "/channels/StringLiteralsChannel.java", monitor, name, false);
		
		javaProjectSquid.getPackageFragmentRoot(sourcefolderjavaSquid).createPackageFragment("org.sonar." + name + ".lexer", false, null);
		addFileToProject(projectSquid,"resources/squid/BackslashChannel.java", "src/main/java/" + "org/sonar/" + name + "/lexer/BackslashChannel.java", monitor, name, false);
		addFileToProject(projectSquid,"resources/squid/XLexer.java", "src/main/java/" + "org/sonar/" + name + "/lexer/" + name + "Lexer.java", monitor, name, false);
		
		javaProjectSquid.getPackageFragmentRoot(sourcefolderjavaSquid).createPackageFragment("org.sonar." + name + ".parser", false, null);
		addFileToProject(projectSquid,"resources/squid/XParser.java", "src/main/java/" + "org/sonar/" + name + "/parser/" + name + "Parser.java", monitor, name, false);
		
		javaProjectSquid.getPackageFragmentRoot(sourcefolderjavaSquid).createPackageFragment("org.sonar." + name + ".visitors", false, null);
		addFileToProject(projectSquid,"resources/squid/XCharsetAwareVisitor.java", "src/main/java/" + "org/sonar/" + name + "/visitors/" + name + "CharsetAwareVisitor.java", monitor, name, false);
		addFileToProject(projectSquid,"resources/squid/XFileVisitor.java", "src/main/java/" + "org/sonar/" + name + "/visitors/" + name + "FileVisitor.java", monitor, name, false);
		addFileToProject(projectSquid,"resources/squid/XLinesOfCodeVisitor.java", "src/main/java/" + "org/sonar/" + name + "/visitors/" + name + "LinesOfCodeVisitor.java", monitor, name, false);
		
		//----- Copy all projects in the main project : Maven multi module
		
		//projectMain.copy(projectPlugin.getFullPath(), true, monitor);
	    System.out.println("Path usada = " +projectMain.getName() + "/");
		projectPlugin.copy(new Path(projectMain.getName() + "/"), true, monitor);
		System.out.println("Copy plugin project done.");
		
	}
	
	private void createMavenProject(IProject project, IProgressMonitor monitor, boolean mainProject) throws CoreException {
		//Project creation:
		if(!project.exists()) {
			project.create(monitor);
			project.open(monitor);
		}
		
		//Project nature configuration:
		IProjectDescription description = project.getDescription();
		if(mainProject) {
			description.setNatureIds(new String[] {"org.eclipse.m2e.core.maven2Nature" });
		} else {
			description.setNatureIds(new String[] {JavaCore.NATURE_ID, "org.eclipse.m2e.core.maven2Nature" });
		}
		project.setDescription(description, monitor);
	}
	
	
	private void copyFile(InputStream input, File output, String name) throws IOException {
		OutputStream outputStream = new FileOutputStream(output);
		
		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = input.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		System.out.println("outputStream = " + outputStream.toString());
		outputStream.close();

		System.out.println("Copy Done!");
		//updateNaming(output, name);
		//System.out.println("Update naming Done!");
		
	}
	
	
	@SuppressWarnings("resource")
	private void copyFile(File source, File target, String name) throws IOException {
		if(!target.exists()) {
			target.createNewFile();
		}
		
		//updateNaming(source, name);
		
		FileChannel sourceChannel;
		FileChannel targetChannel;
		
		sourceChannel = new FileInputStream(source).getChannel();
        targetChannel = new FileOutputStream(target).getChannel();
        sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
        
        if(sourceChannel != null) {
            sourceChannel.close();
        }
        
        if(targetChannel != null) {
            targetChannel.close();        	
        }
	}
	
	private void updateNaming(IFile source, String name, boolean pom) throws IOException {
		if(pom) {
			File sourceFile = new File(source.getLocation().toString());
	        String fileContext = FileUtils.readFileToString(sourceFile);
	        fileContext = fileContext.replaceAll("UPPER", name);
	        FileUtils.write(sourceFile, fileContext);
	        name = name.toLowerCase();
	        fileContext = FileUtils.readFileToString(sourceFile);
	        fileContext = fileContext.replaceAll("NAME", name);
	        FileUtils.write(sourceFile, fileContext);
		} else {
			File sourceFile = new File(source.getLocation().toString());
	        String fileContext = FileUtils.readFileToString(sourceFile);
	        fileContext = fileContext.replaceAll("NAME", name);
	        FileUtils.write(sourceFile, fileContext);
	        name = name.toLowerCase();
	        sourceFile = new File(source.getLocation().toString());
	        fileContext = FileUtils.readFileToString(sourceFile);
	        fileContext = fileContext.replaceAll("LOWER", name);
	        FileUtils.write(sourceFile, fileContext);
		}
	}

	/**{@inheritDoc}
	 *
	 * @see org.eclipse.ui.actions.ActionDelegate#run(org.eclipse.jface.action.IAction)
	 * @generated
	 */
	public void run(IAction action) {
		if (files != null) {
			//AcceleoPreferences.switchQueryCache(false);
			IRunnableWithProgress operation = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					try {
						Iterator<IFile> filesIt = files.iterator();
						while (filesIt.hasNext()) {
							IFile model = (IFile)filesIt.next();
							URI modelURI = URI.createPlatformResourceURI(model.getFullPath().toString(), true);
							try {
								//Construir proyecto Maven y pasarle la URL.
								try {
									createMavenProjects(model.getName(), monitor);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String projectSquidName = name + "-squid";
								IProject projectSquid = ResourcesPlugin.getWorkspace().getRoot().getProject(projectSquidName);
								IContainer target = projectSquid.getFolder("src/main/java/org/sonar/" + name + "/api");
								//IContainer target = model.getProject().getFolder("src-gen");
								GenerateAll generator = new GenerateAll(modelURI, target, getArguments());
								generator.doGenerate(monitor);
							} catch (IOException e) {
								IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
								Activator.getDefault().getLog().log(status);
							} finally {
								model.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
							}
						}
					} catch (CoreException e) {
						IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
						Activator.getDefault().getLog().log(status);
					}
				}
			};
			try {
				PlatformUI.getWorkbench().getProgressService().run(true, true, operation);
			} catch (InvocationTargetException e) {
				IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
				Activator.getDefault().getLog().log(status);
			} catch (InterruptedException e) {
				IStatus status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
				Activator.getDefault().getLog().log(status);
			}
		}
	}

	/**
	 * Computes the arguments of the generator.
	 * 
	 * @return the arguments
	 * @generated
	 */
	protected List<? extends Object> getArguments() {
		return new ArrayList<String>();
	}

}