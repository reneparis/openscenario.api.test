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
import net.asam.openscenario.v1_0.api.IConditionGroup;
import net.asam.openscenario.v1_0.common.OscConstants;
import net.asam.openscenario.v1_0.impl.ConditionGroupImpl;
import net.asam.openscenario.v1_0.impl.TriggerImpl;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Filling a TriggerImpl instance from an xml tree.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class TriggerXmlParser extends XmlComplexTypeParser<TriggerImpl> {

  /**
   * Constructor
   *
   * @param messageLogger to log messages during parsing
   * @param filename to locate the messages in a file
   */
  public TriggerXmlParser(IParserMessageLogger messageLogger, String filename) {
    super(messageLogger, filename);
    this.subElementParser = new SubElementParser(messageLogger, filename);
  }

  @Override
  protected Map<String, IAttributeParser<TriggerImpl>> getAttributeNameToAttributeParserMap() {
    Map<String, IAttributeParser<TriggerImpl>> result = new Hashtable<>();
    return result;
  }

  /** Parser for all subelements */
  private class SubElementParser extends XmlSequenceParser<TriggerImpl> {
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
    protected List<IElementParser<TriggerImpl>> createParserList() {
      List<IElementParser<TriggerImpl>> result = new ArrayList<>();
      result.add(new SubElementConditionGroupsParser());
      return result;
    }
  }
  /** A parser for subelement conditionGroups */
  @SuppressWarnings("synthetic-access")
  private class SubElementConditionGroupsParser implements IElementParser<TriggerImpl> {

    /** Constructor */
    public SubElementConditionGroupsParser() {
      super();
      this.conditionGroupXmlParser =
          new ConditionGroupXmlParser(
              TriggerXmlParser.this.messageLogger, TriggerXmlParser.this.filename);
    }

    private ConditionGroupXmlParser conditionGroupXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, TriggerImpl object) {
      ConditionGroupImpl conditionGroups = new ConditionGroupImpl();
      // Setting the parent
      conditionGroups.setParent(object);
      this.conditionGroupXmlParser.parseElement(indexedElement, parserContext, conditionGroups);
      List<IConditionGroup> conditionGroupsList = object.getConditionGroups();
      if (conditionGroupsList == null) {
        conditionGroupsList = new ArrayList<>();
        object.setConditionGroups(conditionGroupsList);
      }
      conditionGroupsList.add(conditionGroups);
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
      return elementName.equals(OscConstants.ELEMENT__CONDITION_GROUP);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__CONDITION_GROUP};
    }
  }
}
