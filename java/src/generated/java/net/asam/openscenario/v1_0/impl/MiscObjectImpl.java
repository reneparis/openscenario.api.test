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
import net.asam.openscenario.common.ErrorLevel;
import net.asam.openscenario.common.FileContentMessage;
import net.asam.openscenario.common.IParserMessageLogger;
import net.asam.openscenario.impl.BaseImpl;
import net.asam.openscenario.parameter.ParameterValue;
import net.asam.openscenario.parser.ParserHelper;
import net.asam.openscenario.v1_0.api.IBoundingBox;
import net.asam.openscenario.v1_0.api.IMiscObject;
import net.asam.openscenario.v1_0.api.IParameterDeclaration;
import net.asam.openscenario.v1_0.api.IProperties;
import net.asam.openscenario.v1_0.api.MiscObjectCategory;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>Value class that implements IMiscObject. With setter methods to fill the properties.
 *
 * <ul>
 *   <li>getter methods for properties (implemented methods of IMiscObject)
 *   <li>setter methods for properties
 *   <li>getChildren method to collect all children
 *   <li>clone function to make a deep copy
 *   <li>overrides from BaseImpl
 * </ul>
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class MiscObjectImpl extends BaseImpl implements IMiscObject {
  protected static Hashtable<String, SimpleType> propertyToType = new Hashtable<>();

  /** Filling the property to type map */
  static {
    propertyToType.put(OscConstants.ATTRIBUTE__MISC_OBJECT_CATEGORY, SimpleType.ENUM_TYPE);
    propertyToType.put(OscConstants.ATTRIBUTE__MASS, SimpleType.DOUBLE);
    propertyToType.put(OscConstants.ATTRIBUTE__NAME, SimpleType.STRING);
  }

  private MiscObjectCategory miscObjectCategory;
  private Double mass;
  private String name;
  private List<IParameterDeclaration> parameterDeclarations;
  private IBoundingBox boundingBox;
  private IProperties properties;
  /** Default constructor */
  public MiscObjectImpl() {
    super();
    addAdapter(MiscObjectImpl.class, this);
    addAdapter(IMiscObject.class, this);
  }

  @Override
  public IOpenScenarioFlexElement getOpenScenarioFlexElement() {
    return this;
  }

  @Override
  public MiscObjectCategory getMiscObjectCategory() {
    return this.miscObjectCategory;
  }

  @Override
  public Double getMass() {
    return this.mass;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<IParameterDeclaration> getParameterDeclarations() {
    return this.parameterDeclarations;
  }

  @Override
  public IBoundingBox getBoundingBox() {
    return this.boundingBox;
  }

  @Override
  public IProperties getProperties() {
    return this.properties;
  }
  /**
   * Sets the value of model property miscObjectCategory
   *
   * @param miscObjectCategory from OpenSCENARIO class model specification: [Categorization of the
   *     miscellaneous object.]
   */
  public void setMiscObjectCategory(MiscObjectCategory miscObjectCategory) {
    this.miscObjectCategory = miscObjectCategory;
  }
  /**
   * Sets the value of model property mass
   *
   * @param mass from OpenSCENARIO class model specification: [Mass of the miscellaneous object.
   *     Unit: kg; Range: [0..inf[.]
   */
  public void setMass(Double mass) {
    this.mass = mass;
  }
  /**
   * Sets the value of model property name
   *
   * @param name from OpenSCENARIO class model specification: [Name of the miscellaneous object
   *     type.]
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * Sets the value of model property parameterDeclarations
   *
   * @param parameterDeclarations from OpenSCENARIO class model specification: [Definition of
   *     additional parameters.]
   */
  public void setParameterDeclarations(List<IParameterDeclaration> parameterDeclarations) {
    this.parameterDeclarations = parameterDeclarations;
  }
  /**
   * Sets the value of model property boundingBox
   *
   * @param boundingBox from OpenSCENARIO class model specification: [Bounding box definition for
   *     the miscellaneous object.]
   */
  public void setBoundingBox(IBoundingBox boundingBox) {
    this.boundingBox = boundingBox;
  }
  /**
   * Sets the value of model property properties
   *
   * @param properties from OpenSCENARIO class model specification: [Property definitions for the
   *     miscellaneous object.]
   */
  public void setProperties(IProperties properties) {
    this.properties = properties;
  }

  @Override
  public void resolveParameterInternal(
      IParserMessageLogger logger, String attributeKey, String parameterLiteralValue) {
    if (attributeKey.equals(OscConstants.ATTRIBUTE__MISC_OBJECT_CATEGORY)) {
      // Enumeration Type
      MiscObjectCategory result = MiscObjectCategory.getFromLiteral(parameterLiteralValue);
      if (result != null) {
        this.miscObjectCategory = result;
        addResolvedParameter(attributeKey);
      } else {
        logger.logMessage(
            new FileContentMessage(
                "Value '" + parameterLiteralValue + "' is not allowed.",
                ErrorLevel.ERROR,
                getTextmarker(attributeKey)));
      }

    } else if (attributeKey.equals(OscConstants.ATTRIBUTE__MASS)) {
      // Simple type
      this.mass =
          ParserHelper.parseDouble(logger, parameterLiteralValue, getTextmarker(attributeKey));
      addResolvedParameter(attributeKey);

    } else if (attributeKey.equals(OscConstants.ATTRIBUTE__NAME)) {
      // Simple type
      this.name =
          ParserHelper.parseString(logger, parameterLiteralValue, getTextmarker(attributeKey));
      addResolvedParameter(attributeKey);
    }
  }

  @Override
  public SimpleType getTypeFromAttributeName(String attributeKey) {
    return propertyToType.get(attributeKey);
  }

  @Override
  public boolean hasParameterDefinitions() {
    return true;
  }

  @Override
  public List<ParameterValue> getParameterDefinitions() {
    List<ParameterValue> result = new java.util.ArrayList<>();
    if (this.parameterDeclarations != null) {
      for (IParameterDeclaration parameterDeclaration : this.parameterDeclarations) {
        ParameterValue parameterValue =
            new ParameterValue(
                parameterDeclaration.getName(),
                getParameterType(parameterDeclaration.getParameterType().getLiteral()),
                parameterDeclaration.getValue());
        result.add(parameterValue);
      }
    }
    return result;
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

    List<IParameterDeclaration> parameterDeclarations = null;
    parameterDeclarations = getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration item : parameterDeclarations) {
        result.add((BaseImpl) item);
      }
    }
    IBoundingBox boundingBox = null;
    boundingBox = getBoundingBox();
    if (boundingBox != null) {
      result.add((BaseImpl) boundingBox);
    }
    IProperties properties = null;
    properties = getProperties();
    if (properties != null) {
      result.add((BaseImpl) properties);
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
  public MiscObjectImpl clone() {
    MiscObjectImpl clonedObject = new MiscObjectImpl();
    cloneStartMarker(clonedObject);
    cloneEndMarker(clonedObject);
    cloneAttributeKeyToStartMarker(clonedObject);
    cloneAttributeKeyToEndMarker(clonedObject);
    cloneAttributeKeyToParameterNameMap(clonedObject);
    // clone attributes;
    // Enumeration Type
    MiscObjectCategory miscObjectCategory = getMiscObjectCategory();
    if (miscObjectCategory != null) {
      clonedObject.setMiscObjectCategory(
          MiscObjectCategory.getFromLiteral(miscObjectCategory.getLiteral()));
    }
    // Simple type
    clonedObject.setMass(getMass());
    // Simple type
    clonedObject.setName(getName());
    // clone children
    List<IParameterDeclaration> parameterDeclarations = null;
    parameterDeclarations = getParameterDeclarations();
    if (parameterDeclarations != null) {
      List<IParameterDeclaration> clonedList = new ArrayList<>();
      for (IParameterDeclaration item : parameterDeclarations) {
        ParameterDeclarationImpl clonedChild = ((ParameterDeclarationImpl) item).clone();
        clonedList.add(clonedChild);
        clonedChild.setParent(clonedObject);
      }
      clonedObject.setParameterDeclarations(clonedList);
    }
    IBoundingBox boundingBox = null;
    boundingBox = getBoundingBox();
    if (boundingBox != null) {
      BoundingBoxImpl clonedChild = ((BoundingBoxImpl) boundingBox).clone();
      clonedObject.setBoundingBox(clonedChild);
      clonedChild.setParent(clonedObject);
    }
    IProperties properties = null;
    properties = getProperties();
    if (properties != null) {
      PropertiesImpl clonedChild = ((PropertiesImpl) properties).clone();
      clonedObject.setProperties(clonedChild);
      clonedChild.setParent(clonedObject);
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
    if (key.equals(OscConstants.ATTRIBUTE__NAME)) {
      return getName();
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
    if (key == null) {
      throw new KeyNotSupportedException();
    }
    if (key.equals(OscConstants.ATTRIBUTE__MASS)) {
      return getMass();
    }
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
    if (key == null) {
      throw new KeyNotSupportedException();
    }
    if (key.equals(OscConstants.ELEMENT__BOUNDING_BOX)) {
      return (IOpenScenarioFlexElement) getBoundingBox();
    }
    if (key.equals(OscConstants.ELEMENT__PROPERTIES)) {
      return (IOpenScenarioFlexElement) getProperties();
    }
    throw new KeyNotSupportedException();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<IOpenScenarioFlexElement> getListChildElement(String key)
      throws KeyNotSupportedException {

    if (key == null) {
      throw new KeyNotSupportedException();
    }
    if (key.equals(OscConstants.ELEMENT__PARAMETER_DECLARATION)) {
      return (List<IOpenScenarioFlexElement>) (List<?>) getParameterDeclarations();
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
    if (key == null) {
      throw new KeyNotSupportedException();
    }
    if (key.equals(OscConstants.ATTRIBUTE__MISC_OBJECT_CATEGORY)) {
      MiscObjectCategory miscObjectCategory = getMiscObjectCategory();
      return miscObjectCategory != null ? miscObjectCategory.getLiteral() : null;
    }
    throw new KeyNotSupportedException();
  }

  @Override
  public String getModelType() {
    return "MiscObject";
  }
}
