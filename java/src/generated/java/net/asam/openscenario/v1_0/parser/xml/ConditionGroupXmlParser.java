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
import net.asam.openscenario.parser.ParserContext;
import net.asam.openscenario.parser.modelgroup.XmlSequenceParser;
import net.asam.openscenario.parser.type.XmlComplexTypeParser;
import net.asam.openscenario.simple.struct.IndexedElement;
import net.asam.openscenario.v1_0.api.ICondition;
import net.asam.openscenario.v1_0.common.OscConstants;
import net.asam.openscenario.v1_0.impl.ConditionGroupImpl;
import net.asam.openscenario.v1_0.impl.ConditionImpl;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Filling a ConditionGroupImpl instance from an xml tree.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class ConditionGroupXmlParser extends XmlComplexTypeParser<ConditionGroupImpl> {

  /**
   * Constructor
   *
   * @param messageLogger to log messages during parsing
   * @param filename to locate the messages in a file
   */
  public ConditionGroupXmlParser(IParserMessageLogger messageLogger, String filename) {
    super(messageLogger, filename);
    this.subElementParser = new SubElementParser(messageLogger, filename);
  }

  @Override
  protected Map<String, IAttributeParser<ConditionGroupImpl>>
      getAttributeNameToAttributeParserMap() {
    Map<String, IAttributeParser<ConditionGroupImpl>> result = new Hashtable<>();
    return result;
  }

  /** Parser for all subelements */
  private class SubElementParser extends XmlSequenceParser<ConditionGroupImpl> {
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
    protected List<IElementParser<ConditionGroupImpl>> createParserList() {
      List<IElementParser<ConditionGroupImpl>> result = new ArrayList<>();
      result.add(new SubElementConditionsParser());
      return result;
    }
  }
  /** A parser for subelement conditions */
  @SuppressWarnings("synthetic-access")
  private class SubElementConditionsParser implements IElementParser<ConditionGroupImpl> {

    /** Constructor */
    public SubElementConditionsParser() {
      super();
      this.conditionXmlParser =
          new ConditionXmlParser(
              ConditionGroupXmlParser.this.messageLogger, ConditionGroupXmlParser.this.filename);
    }

    private ConditionXmlParser conditionXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, ConditionGroupImpl object) {
      ConditionImpl conditions = new ConditionImpl();
      // Setting the parent
      conditions.setParent(object);
      this.conditionXmlParser.parseElement(indexedElement, parserContext, conditions);
      List<ICondition> conditionsList = object.getConditions();
      if (conditionsList == null) {
        conditionsList = new ArrayList<>();
        object.setConditions(conditionsList);
      }
      conditionsList.add(conditions);
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
      return elementName.equals(OscConstants.ELEMENT__CONDITION);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__CONDITION};
    }
  }
}
