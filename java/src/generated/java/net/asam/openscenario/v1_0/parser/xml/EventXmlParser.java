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
package net.asam.openscenario.v1_0.parser.xml;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import net.asam.openscenario.common.ErrorLevel;
import net.asam.openscenario.common.FileContentMessage;
import net.asam.openscenario.common.IParserMessageLogger;
import net.asam.openscenario.common.Textmarker;
import net.asam.openscenario.parser.ParserContext;
import net.asam.openscenario.parser.modelgroup.XmlSequenceParser;
import net.asam.openscenario.parser.type.XmlComplexTypeParser;
import net.asam.openscenario.simple.struct.IndexedElement;
import net.asam.openscenario.v1_0.api.IAction;
import net.asam.openscenario.v1_0.api.Priority;
import net.asam.openscenario.v1_0.common.OscConstants;
import net.asam.openscenario.v1_0.impl.ActionImpl;
import net.asam.openscenario.v1_0.impl.EventImpl;
import net.asam.openscenario.v1_0.impl.TriggerImpl;
import net.asam.xml.indexer.Position;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Filling a EventImpl instance from an xml tree.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class EventXmlParser extends XmlComplexTypeParser<EventImpl> {

  /**
   * Constructor
   *
   * @param messageLogger to log messages during parsing
   * @param filename to locate the messages in a file
   */
  public EventXmlParser(IParserMessageLogger messageLogger, String filename) {
    super(messageLogger, filename);
    this.subElementParser = new SubElementParser(messageLogger, filename);
  }

  @Override
  protected Map<String, IAttributeParser<EventImpl>> getAttributeNameToAttributeParserMap() {
    Map<String, IAttributeParser<EventImpl>> result = new Hashtable<>();
    result.put(
        OscConstants.ATTRIBUTE__PRIORITY,
        new IAttributeParser<EventImpl>() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void parse(
              Position startPosition,
              Position endPosition,
              String attributeName,
              String attributeValue,
              EventImpl object) {

            Textmarker startMarker =
                new Textmarker(
                    startPosition.getLine(),
                    startPosition.getColumn(),
                    EventXmlParser.this.filename);
            Textmarker endMarker =
                new Textmarker(
                    endPosition.getLine(), endPosition.getColumn(), EventXmlParser.this.filename);
            if (isParametrized(attributeValue)) {
              object.setAttributeParameter(
                  OscConstants.ATTRIBUTE__PRIORITY, stripDollarSign(attributeValue), startMarker);
            } else {
              // Parse value
              // Enumeration Type
              Priority result = Priority.getFromLiteral(attributeValue);
              if (result != null) {
                object.setPriority(result);
              } else {
                EventXmlParser.this.messageLogger.logMessage(
                    new FileContentMessage(
                        "Value '" + attributeValue + "' is not allowed.",
                        ErrorLevel.ERROR,
                        startMarker));
              }
            }
            object.putPropertyStartMarker(OscConstants.ATTRIBUTE__PRIORITY, startMarker);
            object.putPropertyEndMarker(OscConstants.ATTRIBUTE__PRIORITY, endMarker);
          }

          @Override
          public int getMinOccur() {
            return 1;
          }
        });
    result.put(
        OscConstants.ATTRIBUTE__MAXIMUM_EXECUTION_COUNT,
        new IAttributeParser<EventImpl>() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void parse(
              Position startPosition,
              Position endPosition,
              String attributeName,
              String attributeValue,
              EventImpl object) {

            Textmarker startMarker =
                new Textmarker(
                    startPosition.getLine(),
                    startPosition.getColumn(),
                    EventXmlParser.this.filename);
            Textmarker endMarker =
                new Textmarker(
                    endPosition.getLine(), endPosition.getColumn(), EventXmlParser.this.filename);
            if (isParametrized(attributeValue)) {
              object.setAttributeParameter(
                  OscConstants.ATTRIBUTE__MAXIMUM_EXECUTION_COUNT,
                  stripDollarSign(attributeValue),
                  startMarker);
            } else {
              // Parse value
              // Simple type
              object.setMaximumExecutionCount(parseUnsignedInt(attributeValue, startMarker));
            }
            object.putPropertyStartMarker(
                OscConstants.ATTRIBUTE__MAXIMUM_EXECUTION_COUNT, startMarker);
            object.putPropertyEndMarker(OscConstants.ATTRIBUTE__MAXIMUM_EXECUTION_COUNT, endMarker);
          }

          @Override
          public int getMinOccur() {
            return 0;
          }
        });
    result.put(
        OscConstants.ATTRIBUTE__NAME,
        new IAttributeParser<EventImpl>() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void parse(
              Position startPosition,
              Position endPosition,
              String attributeName,
              String attributeValue,
              EventImpl object) {

            Textmarker startMarker =
                new Textmarker(
                    startPosition.getLine(),
                    startPosition.getColumn(),
                    EventXmlParser.this.filename);
            Textmarker endMarker =
                new Textmarker(
                    endPosition.getLine(), endPosition.getColumn(), EventXmlParser.this.filename);
            if (isParametrized(attributeValue)) {
              object.setAttributeParameter(
                  OscConstants.ATTRIBUTE__NAME, stripDollarSign(attributeValue), startMarker);
            } else {
              // Parse value
              // Simple type
              object.setName(parseString(attributeValue, startMarker));
            }
            object.putPropertyStartMarker(OscConstants.ATTRIBUTE__NAME, startMarker);
            object.putPropertyEndMarker(OscConstants.ATTRIBUTE__NAME, endMarker);
          }

          @Override
          public int getMinOccur() {
            return 1;
          }
        });
    return result;
  }

  /** Parser for all subelements */
  private class SubElementParser extends XmlSequenceParser<EventImpl> {
    /**
     * Constructor
     *
     * @param messageLogger to log messages during parsing
     * @param filename to locate the messages in a file
     */
    public SubElementParser(IParserMessageLogger messageLogger, String filename) {
      super(messageLogger, filename);
    }
    /*
     * Creates a list of parser
     */
    @Override
    protected List<IElementParser<EventImpl>> createParserList() {
      List<IElementParser<EventImpl>> result = new ArrayList<>();
      result.add(new SubElementActionsParser());
      result.add(new SubElementStartTriggerParser());
      return result;
    }
  }
  /** A parser for subelement actions */
  @SuppressWarnings("synthetic-access")
  private class SubElementActionsParser implements IElementParser<EventImpl> {

    /** Constructor */
    public SubElementActionsParser() {
      super();
      this.actionXmlParser =
          new ActionXmlParser(EventXmlParser.this.messageLogger, EventXmlParser.this.filename);
    }

    private ActionXmlParser actionXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, EventImpl object) {
      ActionImpl actions = new ActionImpl();
      // Setting the parent
      actions.setParent(object);
      this.actionXmlParser.parseElement(indexedElement, parserContext, actions);
      List<IAction> actionsList = object.getActions();
      if (actionsList == null) {
        actionsList = new ArrayList<>();
        object.setActions(actionsList);
      }
      actionsList.add(actions);
    }

    @Override
    public int getMinOccur() {
      return 1;
    }

    @Override
    public int getMaxOccur() {
      return -1;
    }

    @Override
    public boolean doesMatch(String elementName) {
      return elementName.equals(OscConstants.ELEMENT__ACTION);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__ACTION};
    }
  }
  /** A parser for subelement startTrigger */
  @SuppressWarnings("synthetic-access")
  private class SubElementStartTriggerParser implements IElementParser<EventImpl> {

    /** Constructor */
    public SubElementStartTriggerParser() {
      super();
      this.triggerXmlParser =
          new TriggerXmlParser(EventXmlParser.this.messageLogger, EventXmlParser.this.filename);
    }

    private TriggerXmlParser triggerXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, EventImpl object) {
      TriggerImpl startTrigger = new TriggerImpl();
      // Setting the parent
      startTrigger.setParent(object);
      this.triggerXmlParser.parseElement(indexedElement, parserContext, startTrigger);

      object.setStartTrigger(startTrigger);
    }

    @Override
    public int getMinOccur() {
      return 1;
    }

    @Override
    public int getMaxOccur() {
      return 1;
    }

    @Override
    public boolean doesMatch(String elementName) {
      return elementName.equals(OscConstants.ELEMENT__START_TRIGGER);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__START_TRIGGER};
    }
  }
}
