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
import net.asam.openscenario.common.IParserMessageLogger;
import net.asam.openscenario.common.Textmarker;
import net.asam.openscenario.parser.ParserContext;
import net.asam.openscenario.parser.modelgroup.XmlSequenceParser;
import net.asam.openscenario.parser.type.XmlComplexTypeParser;
import net.asam.openscenario.simple.struct.IndexedElement;
import net.asam.openscenario.v1_0.api.IPhase;
import net.asam.openscenario.v1_0.common.OscConstants;
import net.asam.openscenario.v1_0.impl.PhaseImpl;
import net.asam.openscenario.v1_0.impl.TrafficSignalControllerImpl;
import net.asam.xml.indexer.Position;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Filling a TrafficSignalControllerImpl instance from an xml tree.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class TrafficSignalControllerXmlParser
    extends XmlComplexTypeParser<TrafficSignalControllerImpl> {

  /**
   * Constructor
   *
   * @param messageLogger to log messages during parsing
   * @param filename to locate the messages in a file
   */
  public TrafficSignalControllerXmlParser(IParserMessageLogger messageLogger, String filename) {
    super(messageLogger, filename);
    this.subElementParser = new SubElementParser(messageLogger, filename);
  }

  @Override
  protected Map<String, IAttributeParser<TrafficSignalControllerImpl>>
      getAttributeNameToAttributeParserMap() {
    Map<String, IAttributeParser<TrafficSignalControllerImpl>> result = new Hashtable<>();
    result.put(
        OscConstants.ATTRIBUTE__NAME,
        new IAttributeParser<TrafficSignalControllerImpl>() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void parse(
              Position startPosition,
              Position endPosition,
              String attributeName,
              String attributeValue,
              TrafficSignalControllerImpl object) {

            Textmarker startMarker =
                new Textmarker(
                    startPosition.getLine(),
                    startPosition.getColumn(),
                    TrafficSignalControllerXmlParser.this.filename);
            Textmarker endMarker =
                new Textmarker(
                    endPosition.getLine(),
                    endPosition.getColumn(),
                    TrafficSignalControllerXmlParser.this.filename);
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
    result.put(
        OscConstants.ATTRIBUTE__DELAY,
        new IAttributeParser<TrafficSignalControllerImpl>() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void parse(
              Position startPosition,
              Position endPosition,
              String attributeName,
              String attributeValue,
              TrafficSignalControllerImpl object) {

            Textmarker startMarker =
                new Textmarker(
                    startPosition.getLine(),
                    startPosition.getColumn(),
                    TrafficSignalControllerXmlParser.this.filename);
            Textmarker endMarker =
                new Textmarker(
                    endPosition.getLine(),
                    endPosition.getColumn(),
                    TrafficSignalControllerXmlParser.this.filename);
            if (isParametrized(attributeValue)) {
              object.setAttributeParameter(
                  OscConstants.ATTRIBUTE__DELAY, stripDollarSign(attributeValue), startMarker);
            } else {
              // Parse value
              // Simple type
              object.setDelay(parseDouble(attributeValue, startMarker));
            }
            object.putPropertyStartMarker(OscConstants.ATTRIBUTE__DELAY, startMarker);
            object.putPropertyEndMarker(OscConstants.ATTRIBUTE__DELAY, endMarker);
          }

          @Override
          public int getMinOccur() {
            return 0;
          }
        });
    result.put(
        OscConstants.ATTRIBUTE__REFERENCE,
        new IAttributeParser<TrafficSignalControllerImpl>() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void parse(
              Position startPosition,
              Position endPosition,
              String attributeName,
              String attributeValue,
              TrafficSignalControllerImpl object) {

            Textmarker startMarker =
                new Textmarker(
                    startPosition.getLine(),
                    startPosition.getColumn(),
                    TrafficSignalControllerXmlParser.this.filename);
            Textmarker endMarker =
                new Textmarker(
                    endPosition.getLine(),
                    endPosition.getColumn(),
                    TrafficSignalControllerXmlParser.this.filename);
            if (isParametrized(attributeValue)) {
              object.setAttributeParameter(
                  OscConstants.ATTRIBUTE__REFERENCE, stripDollarSign(attributeValue), startMarker);
            } else {
              // Parse value
              // Simple type
              object.setReference(parseString(attributeValue, startMarker));
            }
            object.putPropertyStartMarker(OscConstants.ATTRIBUTE__REFERENCE, startMarker);
            object.putPropertyEndMarker(OscConstants.ATTRIBUTE__REFERENCE, endMarker);
          }

          @Override
          public int getMinOccur() {
            return 0;
          }
        });
    return result;
  }

  /** Parser for all subelements */
  private class SubElementParser extends XmlSequenceParser<TrafficSignalControllerImpl> {
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
    protected List<IElementParser<TrafficSignalControllerImpl>> createParserList() {
      List<IElementParser<TrafficSignalControllerImpl>> result = new ArrayList<>();
      result.add(new SubElementPhasesParser());
      return result;
    }
  }
  /** A parser for subelement phases */
  @SuppressWarnings("synthetic-access")
  private class SubElementPhasesParser implements IElementParser<TrafficSignalControllerImpl> {

    /** Constructor */
    public SubElementPhasesParser() {
      super();
      this.phaseXmlParser =
          new PhaseXmlParser(
              TrafficSignalControllerXmlParser.this.messageLogger,
              TrafficSignalControllerXmlParser.this.filename);
    }

    private PhaseXmlParser phaseXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement,
        ParserContext parserContext,
        TrafficSignalControllerImpl object) {
      PhaseImpl phases = new PhaseImpl();
      // Setting the parent
      phases.setParent(object);
      this.phaseXmlParser.parseElement(indexedElement, parserContext, phases);
      List<IPhase> phasesList = object.getPhases();
      if (phasesList == null) {
        phasesList = new ArrayList<>();
        object.setPhases(phasesList);
      }
      phasesList.add(phases);
    }

    @Override
    public int getMinOccur() {
      return 0;
    }

    @Override
    public int getMaxOccur() {
      return -1;
    }

    @Override
    public boolean doesMatch(String elementName) {
      return elementName.equals(OscConstants.ELEMENT__PHASE);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__PHASE};
    }
  }
}
