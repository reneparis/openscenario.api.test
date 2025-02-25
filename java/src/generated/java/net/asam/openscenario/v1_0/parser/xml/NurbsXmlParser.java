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
import net.asam.openscenario.v1_0.api.IControlPoint;
import net.asam.openscenario.v1_0.api.IKnot;
import net.asam.openscenario.v1_0.common.OscConstants;
import net.asam.openscenario.v1_0.impl.ControlPointImpl;
import net.asam.openscenario.v1_0.impl.KnotImpl;
import net.asam.openscenario.v1_0.impl.NurbsImpl;
import net.asam.xml.indexer.Position;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Filling a NurbsImpl instance from an xml tree.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class NurbsXmlParser extends XmlComplexTypeParser<NurbsImpl> {

  /**
   * Constructor
   *
   * @param messageLogger to log messages during parsing
   * @param filename to locate the messages in a file
   */
  public NurbsXmlParser(IParserMessageLogger messageLogger, String filename) {
    super(messageLogger, filename);
    this.subElementParser = new SubElementParser(messageLogger, filename);
  }

  @Override
  protected Map<String, IAttributeParser<NurbsImpl>> getAttributeNameToAttributeParserMap() {
    Map<String, IAttributeParser<NurbsImpl>> result = new Hashtable<>();
    result.put(
        OscConstants.ATTRIBUTE__ORDER,
        new IAttributeParser<NurbsImpl>() {
          @SuppressWarnings("synthetic-access")
          @Override
          public void parse(
              Position startPosition,
              Position endPosition,
              String attributeName,
              String attributeValue,
              NurbsImpl object) {

            Textmarker startMarker =
                new Textmarker(
                    startPosition.getLine(),
                    startPosition.getColumn(),
                    NurbsXmlParser.this.filename);
            Textmarker endMarker =
                new Textmarker(
                    endPosition.getLine(), endPosition.getColumn(), NurbsXmlParser.this.filename);
            if (isParametrized(attributeValue)) {
              object.setAttributeParameter(
                  OscConstants.ATTRIBUTE__ORDER, stripDollarSign(attributeValue), startMarker);
            } else {
              // Parse value
              // Simple type
              object.setOrder(parseUnsignedInt(attributeValue, startMarker));
            }
            object.putPropertyStartMarker(OscConstants.ATTRIBUTE__ORDER, startMarker);
            object.putPropertyEndMarker(OscConstants.ATTRIBUTE__ORDER, endMarker);
          }

          @Override
          public int getMinOccur() {
            return 1;
          }
        });
    return result;
  }

  /** Parser for all subelements */
  private class SubElementParser extends XmlSequenceParser<NurbsImpl> {
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
    protected List<IElementParser<NurbsImpl>> createParserList() {
      List<IElementParser<NurbsImpl>> result = new ArrayList<>();
      result.add(new SubElementControlPointsParser());
      result.add(new SubElementKnotsParser());
      return result;
    }
  }
  /** A parser for subelement controlPoints */
  @SuppressWarnings("synthetic-access")
  private class SubElementControlPointsParser implements IElementParser<NurbsImpl> {

    /** Constructor */
    public SubElementControlPointsParser() {
      super();
      this.controlPointXmlParser =
          new ControlPointXmlParser(
              NurbsXmlParser.this.messageLogger, NurbsXmlParser.this.filename);
    }

    private ControlPointXmlParser controlPointXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, NurbsImpl object) {
      ControlPointImpl controlPoints = new ControlPointImpl();
      // Setting the parent
      controlPoints.setParent(object);
      this.controlPointXmlParser.parseElement(indexedElement, parserContext, controlPoints);
      List<IControlPoint> controlPointsList = object.getControlPoints();
      if (controlPointsList == null) {
        controlPointsList = new ArrayList<>();
        object.setControlPoints(controlPointsList);
      }
      controlPointsList.add(controlPoints);
    }

    @Override
    public int getMinOccur() {
      return 2;
    }

    @Override
    public int getMaxOccur() {
      return -1;
    }

    @Override
    public boolean doesMatch(String elementName) {
      return elementName.equals(OscConstants.ELEMENT__CONTROL_POINT);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__CONTROL_POINT};
    }
  }
  /** A parser for subelement knots */
  @SuppressWarnings("synthetic-access")
  private class SubElementKnotsParser implements IElementParser<NurbsImpl> {

    /** Constructor */
    public SubElementKnotsParser() {
      super();
      this.knotXmlParser =
          new KnotXmlParser(NurbsXmlParser.this.messageLogger, NurbsXmlParser.this.filename);
    }

    private KnotXmlParser knotXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, NurbsImpl object) {
      KnotImpl knots = new KnotImpl();
      // Setting the parent
      knots.setParent(object);
      this.knotXmlParser.parseElement(indexedElement, parserContext, knots);
      List<IKnot> knotsList = object.getKnots();
      if (knotsList == null) {
        knotsList = new ArrayList<>();
        object.setKnots(knotsList);
      }
      knotsList.add(knots);
    }

    @Override
    public int getMinOccur() {
      return 2;
    }

    @Override
    public int getMaxOccur() {
      return -1;
    }

    @Override
    public boolean doesMatch(String elementName) {
      return elementName.equals(OscConstants.ELEMENT__KNOT);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__KNOT};
    }
  }
}
