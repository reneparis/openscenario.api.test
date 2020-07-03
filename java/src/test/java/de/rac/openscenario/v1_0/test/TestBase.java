package de.rac.openscenario.v1_0.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import de.rac.openscenario.v1_0.api.IOpenScenario;
import de.rac.openscenario.v1_0.common.ErrorLevel;
import de.rac.openscenario.v1_0.common.FileContentMessage;
import de.rac.openscenario.v1_0.common.IParserMessageLogger;
import de.rac.openscenario.v1_0.impl.OpenScenarioImpl;
import de.rac.openscenario.v1_0.loader.FileResourceLocator;
import de.rac.openscenario.v1_0.loader.IScenarioLoader;
import de.rac.openscenario.v1_0.loader.IScenarioLoaderFactory;
import de.rac.openscenario.v1_0.loader.ScenarioLoaderException;
import de.rac.openscenario.v1_0.loader.XmlScenarioImportLoaderFactory;
import de.rac.openscenario.v1_0.loader.XmlScenarioLoaderFactory;

public class TestBase {

	protected MessageLogger messageLogger = new MessageLogger();
	//protected static String inputDir = "./src/test/Resources/";


	public OpenScenarioImpl executeParsing(String filename)
			throws ScenarioLoaderException {
		IScenarioLoaderFactory loaderFactory = new XmlScenarioLoaderFactory(
				filename);

		IScenarioLoader loader = loaderFactory.createLoader(new FileResourceLocator());
		IOpenScenario openScenario = loader.load(messageLogger);
		return (OpenScenarioImpl) openScenario;

	}
	
	public OpenScenarioImpl executeImportParsing(String filename, IParserMessageLogger catalogMessageLogger)
			throws ScenarioLoaderException {
		
		IScenarioLoaderFactory loaderFactory = new XmlScenarioImportLoaderFactory(
				catalogMessageLogger,filename);

		IScenarioLoader loader = loaderFactory.createLoader(new FileResourceLocator());
		IOpenScenario openScenario = loader.load(messageLogger);
		return (OpenScenarioImpl) openScenario;

	}

	@BeforeEach
	public void clearMessageLogger() {
		messageLogger.clear();
	}
	protected boolean hasErrors(MessageLogger messageLogger) {
		for (FileContentMessage message : messageLogger.getMessages()) {
			if (message.getErrorLevel() == ErrorLevel.ERROR
					|| message.getErrorLevel() == ErrorLevel.FATAL) {
				return true;
			}
		}
		return false;
	}

	protected File getResourceFile(String resourceName)
	{
	  ClassLoader classLoader = getClass().getClassLoader();
	  return new File(classLoader.getResource(resourceName).getFile());
	}
	
	protected boolean assertMessages(List<FileContentMessage> messages,
			ErrorLevel errorLevel, MessageLogger logger) {
		List<FileContentMessage> filterByErrorLevelMessages = filterByErrorLevel(
				messages, errorLevel);
		List<FileContentMessage> filterByErrorLevelLogger = filterByErrorLevel(
				logger.getMessages(), errorLevel);
		return filterByErrorLevelMessages.equals(filterByErrorLevelLogger)
				&& filterByErrorLevelMessages.size() == filterByErrorLevelLogger
						.size();

	}

	protected List<FileContentMessage> filterByErrorLevel(
			List<FileContentMessage> messages, ErrorLevel errorLevel) {
		List<FileContentMessage> result = new ArrayList<FileContentMessage>();
		for (FileContentMessage message : messages) {
			if (errorLevel == message.getErrorLevel()) {
				result.add(message);
			}
		}
		Collections.sort(result);
		return result;
	}


}
