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
import net.asam.openscenario.parser.modelgroup.XmlChoiceParser;
import net.asam.openscenario.parser.type.XmlComplexTypeParser;
import net.asam.openscenario.simple.struct.IndexedElement;
import net.asam.openscenario.v1_0.api.IByType;
import net.asam.openscenario.v1_0.api.IEntityRef;
import net.asam.openscenario.v1_0.common.OscConstants;
import net.asam.openscenario.v1_0.impl.ByTypeImpl;
import net.asam.openscenario.v1_0.impl.EntityRefImpl;
import net.asam.openscenario.v1_0.impl.SelectedEntitiesImpl;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Filling a SelectedEntitiesImpl instance from an xml tree.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class SelectedEntitiesXmlParser extends XmlComplexTypeParser<SelectedEntitiesImpl> {

  /**
   * Constructor
   *
   * @param messageLogger to log messages during parsing
   * @param filename to locate the messages in a file
   */
  public SelectedEntitiesXmlParser(IParserMessageLogger messageLogger, String filename) {
    super(messageLogger, filename);
    this.subElementParser = new SubElementParser(messageLogger, filename);
  }

  @Override
  protected Map<String, IAttributeParser<SelectedEntitiesImpl>>
      getAttributeNameToAttributeParserMap() {
    Map<String, IAttributeParser<SelectedEntitiesImpl>> result = new Hashtable<>();
    return result;
  }

  /** Parser for all subelements */
  private class SubElementParser extends XmlChoiceParser<SelectedEntitiesImpl> {
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
    protected List<IElementParser<SelectedEntitiesImpl>> createParserList() {
      List<IElementParser<SelectedEntitiesImpl>> result = new ArrayList<>();
      result.add(new SubElementEntityRefParser());
      result.add(new SubElementByTypeParser());
      return result;
    }
  }
  /** A parser for subelement entityRef */
  @SuppressWarnings("synthetic-access")
  private class SubElementEntityRefParser implements IElementParser<SelectedEntitiesImpl> {

    /** Constructor */
    public SubElementEntityRefParser() {
      super();
      this.entityRefXmlParser =
          new EntityRefXmlParser(
              SelectedEntitiesXmlParser.this.messageLogger,
              SelectedEntitiesXmlParser.this.filename);
    }

    private EntityRefXmlParser entityRefXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, SelectedEntitiesImpl object) {
      EntityRefImpl entityRef = new EntityRefImpl();
      // Setting the parent
      entityRef.setParent(object);
      this.entityRefXmlParser.parseElement(indexedElement, parserContext, entityRef);
      List<IEntityRef> entityRefList = object.getEntityRef();
      if (entityRefList == null) {
        entityRefList = new ArrayList<>();
        object.setEntityRef(entityRefList);
      }
      entityRefList.add(entityRef);
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
      return elementName.equals(OscConstants.ELEMENT__ENTITY_REF);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__ENTITY_REF};
    }
  }
  /** A parser for subelement byType */
  @SuppressWarnings("synthetic-access")
  private class SubElementByTypeParser implements IElementParser<SelectedEntitiesImpl> {

    /** Constructor */
    public SubElementByTypeParser() {
      super();
      this.byTypeXmlParser =
          new ByTypeXmlParser(
              SelectedEntitiesXmlParser.this.messageLogger,
              SelectedEntitiesXmlParser.this.filename);
    }

    private ByTypeXmlParser byTypeXmlParser;

    @Override
    public void parse(
        IndexedElement indexedElement, ParserContext parserContext, SelectedEntitiesImpl object) {
      ByTypeImpl byType = new ByTypeImpl();
      // Setting the parent
      byType.setParent(object);
      this.byTypeXmlParser.parseElement(indexedElement, parserContext, byType);
      List<IByType> byTypeList = object.getByType();
      if (byTypeList == null) {
        byTypeList = new ArrayList<>();
        object.setByType(byTypeList);
      }
      byTypeList.add(byType);
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
      return elementName.equals(OscConstants.ELEMENT__BY_TYPE);
    }

    @Override
    public String[] getExpectedTagNames() {
      return new String[] {OscConstants.ELEMENT__BY_TYPE};
    }
  }
}
