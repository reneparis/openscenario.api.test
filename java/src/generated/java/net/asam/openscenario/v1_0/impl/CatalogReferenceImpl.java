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
package net.asam.openscenario.v1_0.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import net.asam.openscenario.api.IOpenScenarioFlexElement;
import net.asam.openscenario.api.KeyNotSupportedException;
import net.asam.openscenario.api.SimpleType;
import net.asam.openscenario.common.IParserMessageLogger;
import net.asam.openscenario.impl.BaseImpl;
import net.asam.openscenario.parser.ParserHelper;
import net.asam.openscenario.v1_0.api.ICatalogElement;
import net.asam.openscenario.v1_0.api.ICatalogReference;
import net.asam.openscenario.v1_0.api.IParameterAssignment;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>Value class that implements ICatalogReference. With setter methods to fill the properties.
 *
 * <ul>
 *   <li>getter methods for properties (implemented methods of ICatalogReference)
 *   <li>setter methods for properties
 *   <li>getChildren method to collect all children
 *   <li>clone function to make a deep copy
 *   <li>overrides from BaseImpl
 * </ul>
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class CatalogReferenceImpl extends BaseImpl implements ICatalogReference {
  protected static Hashtable<String, SimpleType> propertyToType = new Hashtable<>();

  /** Filling the property to type map */
  static {
    propertyToType.put(OscConstants.ATTRIBUTE__CATALOG_NAME, SimpleType.STRING);
    propertyToType.put(OscConstants.ATTRIBUTE__ENTRY_NAME, SimpleType.STRING);
  }

  private String catalogName;
  private String entryName;
  private List<IParameterAssignment> parameterAssignments;
  private ICatalogElement ref;
  /** Default constructor */
  public CatalogReferenceImpl() {
    super();
    addAdapter(CatalogReferenceImpl.class, this);
    addAdapter(ICatalogReference.class, this);
  }

  @Override
  public IOpenScenarioFlexElement getOpenScenarioFlexElement() {
    return this;
  }

  @Override
  public String getCatalogName() {
    return this.catalogName;
  }

  @Override
  public String getEntryName() {
    return this.entryName;
  }

  @Override
  public List<IParameterAssignment> getParameterAssignments() {
    return this.parameterAssignments;
  }

  @Override
  public ICatalogElement getRef() {
    return this.ref;
  }
  /**
   * Sets the value of model property catalogName
   *
   * @param catalogName from OpenSCENARIO class model specification: [Name of the catalog.]
   */
  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
  }
  /**
   * Sets the value of model property entryName
   *
   * @param entryName from OpenSCENARIO class model specification: [Name of catalog entry.]
   */
  public void setEntryName(String entryName) {
    this.entryName = entryName;
  }
  /**
   * Sets the value of model property parameterAssignments
   *
   * @param parameterAssignments from OpenSCENARIO class model specification: [List of parameter
   *     assignments for instantiation.]
   */
  public void setParameterAssignments(List<IParameterAssignment> parameterAssignments) {
    this.parameterAssignments = parameterAssignments;
  }
  /**
   * Sets the value of model property ref
   *
   * @param ref from OpenSCENARIO class model specification: [The resolved reference to a catalog
   *     element (out of the catalogName and entryName). Transient means, that it is not , mapped to
   *     the schema.]
   */
  public void setRef(ICatalogElement ref) {
    this.ref = ref;
  }

  @Override
  public void resolveParameterInternal(
      IParserMessageLogger logger, String attributeKey, String parameterLiteralValue) {
    if (attributeKey.equals(OscConstants.ATTRIBUTE__CATALOG_NAME)) {
      // Simple type
      this.catalogName =
          ParserHelper.parseString(logger, parameterLiteralValue, getTextmarker(attributeKey));
      addResolvedParameter(attributeKey);

    } else if (attributeKey.equals(OscConstants.ATTRIBUTE__ENTRY_NAME)) {
      // Simple type
      this.entryName =
          ParserHelper.parseString(logger, parameterLiteralValue, getTextmarker(attributeKey));
      addResolvedParameter(attributeKey);
    }
  }

  @Override
  public SimpleType getTypeFromAttributeName(String attributeKey) {
    return propertyToType.get(attributeKey);
  }

  /**
   * A list of all aggregated children (lists are flattened). This may be used for applying a
   * specific method for any child.
   *
   * @return a list with all children (as BaseImpl)
   */
  @Override
  public List<BaseImpl> getChildren() {
    List<BaseImpl> result = new ArrayList<>();

    List<IParameterAssignment> parameterAssignments = null;
    parameterAssignments = getParameterAssignments();
    if (parameterAssignments != null) {
      for (IParameterAssignment item : parameterAssignments) {
        result.add((BaseImpl) item);
      }
    }
    return result;
  }

  /**
   * Making a (deep) clone this object. This is useful and used for importing elements from
   * catalogs.
   *
   * @return a deep copy of the object.
   */
  @Override
  public CatalogReferenceImpl clone() {
    CatalogReferenceImpl clonedObject = new CatalogReferenceImpl();
    cloneStartMarker(clonedObject);
    cloneEndMarker(clonedObject);
    cloneAttributeKeyToStartMarker(clonedObject);
    cloneAttributeKeyToEndMarker(clonedObject);
    cloneAttributeKeyToParameterNameMap(clonedObject);
    // clone attributes;
    // Simple type
    clonedObject.setCatalogName(getCatalogName());
    // Simple type
    clonedObject.setEntryName(getEntryName());
    // clone children
    List<IParameterAssignment> parameterAssignments = null;
    parameterAssignments = getParameterAssignments();
    if (parameterAssignments != null) {
      List<IParameterAssignment> clonedList = new ArrayList<>();
      for (IParameterAssignment item : parameterAssignments) {
        ParameterAssignmentImpl clonedChild = ((ParameterAssignmentImpl) item).clone();
        clonedList.add(clonedChild);
        clonedChild.setParent(clonedObject);
      }
      clonedObject.setParameterAssignments(clonedList);
    }
    return clonedObject;
  }

  // Implement the IOpenScenarioFlexElement interface

  @Override
  public String getStringProperty(String key) throws KeyNotSupportedException {
    // proxies and string attributes
    if (key == null) {
      throw new KeyNotSupportedException();
    }
    if (key.equals(OscConstants.ATTRIBUTE__CATALOG_NAME)) {
      return getCatalogName();
    } else if (key.equals(OscConstants.ATTRIBUTE__ENTRY_NAME)) {
      return getEntryName();
    }
    throw new KeyNotSupportedException();
  }

  @Override
  public Long getUnsignedIntProperty(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public Integer getIntProperty(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public Double getDoubleProperty(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public Integer getUnsignedShortProperty(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public Boolean getBooleanProperty(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public Date getDateTimeProperty(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public IOpenScenarioFlexElement getChildElement(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<IOpenScenarioFlexElement> getListChildElement(String key)
      throws KeyNotSupportedException {

    if (key == null) {
      throw new KeyNotSupportedException();
    }
    if (key.equals(OscConstants.ELEMENT__PARAMETER_ASSIGNMENT)) {
      return (List<IOpenScenarioFlexElement>) (List<?>) getParameterAssignments();
    }
    throw new KeyNotSupportedException();
  }

  @Override
  public IOpenScenarioFlexElement getParentFlexElement() {
    return (IOpenScenarioFlexElement) getParent();
  }

  @Override
  public IOpenScenarioFlexElement getReferencedElement(String key, String name)
      throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public String getEnumerationLiteral(String key) throws KeyNotSupportedException {
    throw new KeyNotSupportedException();
  }

  @Override
  public String getModelType() {
    return "CatalogReference";
  }
}
