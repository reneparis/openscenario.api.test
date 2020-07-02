/*
 * Copyright 2020 RA Consulting
 *
 * RA Consulting GmbH licenses this file under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except 
 * in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package de.rac.openscenario.v1_0.parser.xml;

import de.rac.openscenario.v1_0.common.IParserMessageLogger;
import de.rac.openscenario.v1_0.common.OscConstants;
import de.rac.openscenario.v1_0.simple.struct.IndexedElement;
import de.rac.openscenario.v1_0.parser.ParserContext;
import de.rac.openscenario.v1_0.common.FileContentMessage;
import de.rac.xml.indexer.Position;
import de.rac.openscenario.v1_0.common.Textmarker;
import de.rac.openscenario.v1_0.common.ErrorLevel;
import java.util.List;
import de.rac.openscenario.v1_0.api.IPrivate;
import de.rac.openscenario.v1_0.api.IUserDefinedAction;
import de.rac.openscenario.v1_0.api.IGlobalAction;

import de.rac.openscenario.v1_0.impl.GlobalActionImpl;
import de.rac.openscenario.v1_0.impl.UserDefinedActionImpl;
import de.rac.openscenario.v1_0.impl.InitActionsImpl;
import de.rac.openscenario.v1_0.impl.PrivateImpl;


import de.rac.openscenario.v1_0.parser.modelgroup.XmlSequenceParser;
import java.util.Map;
import java.util.Hashtable;
import java.util.ArrayList;
import de.rac.openscenario.v1_0.parser.type.XmlComplexTypeParser;


/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Filling a InitActionsImpl instance from an xml tree.
 * 
 * @author RA Consulting OpenSCENARIO generation facility
*/
public class InitActionsXmlParser extends XmlComplexTypeParser<InitActionsImpl> {

	/**
	 * Constructor
	 * @param messageLogger to log messages during parsing
	 * @param filename to locate the messages in a file
	 */
	public InitActionsXmlParser(IParserMessageLogger messageLogger, String filename) {
		super(messageLogger, filename);
		subElementParser = new SubElementParser(messageLogger, filename);
	}
	@Override
	public void parseElement(IndexedElement indexedElement, ParserContext parserContext,InitActionsImpl object) {
		messageLogger.logMessage(new FileContentMessage("Start Parsing InitActions", ErrorLevel.DEBUG, new Textmarker(indexedElement.getStartElementLocation().getLine(), indexedElement.getStartElementLocation().getColumn(), filename))); 
		super.parseElement(indexedElement,  parserContext, object);
		messageLogger.logMessage(new FileContentMessage("End Parsing InitActions", ErrorLevel.DEBUG, new Textmarker(indexedElement.getEndElementLocation().getLine(), indexedElement.getEndElementLocation().getColumn(), filename))); 
	
	}			
	
	@Override
	protected  Map<String, IAttributeParser<InitActionsImpl>> getAttributeNameToAttributeParserMap()
	{
		Map<String, IAttributeParser<InitActionsImpl>> result  = new Hashtable<String, IAttributeParser<InitActionsImpl>>();
		return result;
	}

	/**
	 * Parser for all subelements
	 */
	private class SubElementParser extends XmlSequenceParser<InitActionsImpl>{
		/**
		 * Constructor
		 * @param messageLogger to log messages during parsing
		 * @param filename to locate the messages in a file
		 */
		public SubElementParser (IParserMessageLogger messageLogger, String filename) {
			super( messageLogger, filename);	
		}
		/*
		 * Creates a list of parser
		 */
		protected  List<IElementParser<InitActionsImpl>> createParserList()
		{
			List<IElementParser<InitActionsImpl>> result = new ArrayList<IElementParser<InitActionsImpl>>();
			result.add(new SubElementGlobalActionsParser());
			result.add(new SubElementUserDefinedActionsParser());
			result.add(new SubElementPrivatesParser());
			return result;
		
		}	
	}
	/**
	 * A parser for subelement globalActions
	 */
	private class SubElementGlobalActionsParser implements IElementParser<InitActionsImpl> {
	
		/**
		 * Constructor
		 */
		public SubElementGlobalActionsParser()
		{
			super();
			globalActionXmlParser = new GlobalActionXmlParser(messageLogger, filename);
		}
		private GlobalActionXmlParser globalActionXmlParser;
		
		@Override
		public void parse(IndexedElement indexedElement, ParserContext parserContext,InitActionsImpl object)
		{
			GlobalActionImpl globalActions = new GlobalActionImpl();
			// Setting the parent
			globalActions.setParent(object);
			globalActionXmlParser.parseElement(indexedElement,parserContext, globalActions);
			List<IGlobalAction> globalActionsList = object.getGlobalActions();
			if (globalActionsList == null)
			{
				globalActionsList = new ArrayList<IGlobalAction>();
				object.setGlobalActions( globalActionsList);
			}
			globalActionsList.add(globalActions);
			
		}

		@Override
		public int getMinOccur()
		{
			return 0;
		}
		
		@Override
		public int getMaxOccur()
		{
			return -1;
		}
		
		@Override
		public boolean doesMatch(String elementName)
		{
			return
				elementName.equals(OscConstants.ELEMENT__GLOBAL_ACTION) ;
		}
		
		@Override
		public String[] getExpectedTagNames()
		{		 	return new String[]{
				OscConstants.ELEMENT__GLOBAL_ACTION
					};
		}
	}
	/**
	 * A parser for subelement userDefinedActions
	 */
	private class SubElementUserDefinedActionsParser implements IElementParser<InitActionsImpl> {
	
		/**
		 * Constructor
		 */
		public SubElementUserDefinedActionsParser()
		{
			super();
			userDefinedActionXmlParser = new UserDefinedActionXmlParser(messageLogger, filename);
		}
		private UserDefinedActionXmlParser userDefinedActionXmlParser;
		
		@Override
		public void parse(IndexedElement indexedElement, ParserContext parserContext,InitActionsImpl object)
		{
			UserDefinedActionImpl userDefinedActions = new UserDefinedActionImpl();
			// Setting the parent
			userDefinedActions.setParent(object);
			userDefinedActionXmlParser.parseElement(indexedElement,parserContext, userDefinedActions);
			List<IUserDefinedAction> userDefinedActionsList = object.getUserDefinedActions();
			if (userDefinedActionsList == null)
			{
				userDefinedActionsList = new ArrayList<IUserDefinedAction>();
				object.setUserDefinedActions( userDefinedActionsList);
			}
			userDefinedActionsList.add(userDefinedActions);
			
		}

		@Override
		public int getMinOccur()
		{
			return 0;
		}
		
		@Override
		public int getMaxOccur()
		{
			return -1;
		}
		
		@Override
		public boolean doesMatch(String elementName)
		{
			return
				elementName.equals(OscConstants.ELEMENT__USER_DEFINED_ACTION) ;
		}
		
		@Override
		public String[] getExpectedTagNames()
		{		 	return new String[]{
				OscConstants.ELEMENT__USER_DEFINED_ACTION
					};
		}
	}
	/**
	 * A parser for subelement privates
	 */
	private class SubElementPrivatesParser implements IElementParser<InitActionsImpl> {
	
		/**
		 * Constructor
		 */
		public SubElementPrivatesParser()
		{
			super();
			privateXmlParser = new PrivateXmlParser(messageLogger, filename);
		}
		private PrivateXmlParser privateXmlParser;
		
		@Override
		public void parse(IndexedElement indexedElement, ParserContext parserContext,InitActionsImpl object)
		{
			PrivateImpl privates = new PrivateImpl();
			// Setting the parent
			privates.setParent(object);
			privateXmlParser.parseElement(indexedElement,parserContext, privates);
			List<IPrivate> privatesList = object.getPrivates();
			if (privatesList == null)
			{
				privatesList = new ArrayList<IPrivate>();
				object.setPrivates( privatesList);
			}
			privatesList.add(privates);
			
		}

		@Override
		public int getMinOccur()
		{
			return 0;
		}
		
		@Override
		public int getMaxOccur()
		{
			return -1;
		}
		
		@Override
		public boolean doesMatch(String elementName)
		{
			return
				elementName.equals(OscConstants.ELEMENT__PRIVATE) ;
		}
		
		@Override
		public String[] getExpectedTagNames()
		{		 	return new String[]{
				OscConstants.ELEMENT__PRIVATE
					};
		}
	}
}

