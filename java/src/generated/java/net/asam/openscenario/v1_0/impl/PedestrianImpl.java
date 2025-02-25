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
import net.asam.openscenario.v1_0.api.IParameterDeclaration;
import net.asam.openscenario.v1_0.api.IPedestrian;
import net.asam.openscenario.v1_0.api.IProperties;
import net.asam.openscenario.v1_0.api.PedestrianCategory;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>Value class that implements IPedestrian. With setter methods to fill the properties.
 *
 * <ul>
 *   <li>getter methods for properties (implemented methods of IPedestrian)
 *   <li>setter methods for properties
 *   <li>getChildren method to collect all children
 *   <li>clone function to make a deep copy
 *   <li>overrides from BaseImpl
 * </ul>
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class PedestrianImpl extends BaseImpl implements IPedestrian {
  protected static Hashtable<String, SimpleType> propertyToType = new Hashtable<>();

  /** Filling the property to type map */
  static {
    propertyToType.put(OscConstants.ATTRIBUTE__MODEL, SimpleType.STRING);
    propertyToType.put(OscConstants.ATTRIBUTE__MASS, SimpleType.DOUBLE);
    propertyToType.put(OscConstants.ATTRIBUTE__NAME, SimpleType.STRING);
    propertyToType.put(OscConstants.ATTRIBUTE__PEDESTRIAN_CATEGORY, SimpleType.ENUM_TYPE);
  }

  private String model;
  private Double mass;
  private String name;
  private PedestrianCategory pedestrianCategory;
  private List<IParameterDeclaration> parameterDeclarations;
  private IBoundingBox boundingBox;
  private IProperties properties;
  /** Default constructor */
  public PedestrianImpl() {
    super();
    addAdapter(PedestrianImpl.class, this);
    addAdapter(IPedestrian.class, this);
  }

  @Override
  public IOpenScenarioFlexElement getOpenScenarioFlexElement() {
    return this;
  }

  @Override
  public String getModel() {
    return this.model;
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
  public PedestrianCategory getPedestrianCategory() {
    return this.pedestrianCategory;
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
   * Sets the value of model property model
   *
   * @param model from OpenSCENARIO class model specification: [Definition of the model of the
   *     pedestrian.]
   */
  public void setModel(String model) {
    this.model = model;
  }
  /**
   * Sets the value of model property mass
   *
   * @param mass from OpenSCENARIO class model specification: [The mass of a pedestrian in kg.]
   */
  public void setMass(Double mass) {
    this.mass = mass;
  }
  /**
   * Sets the value of model property name
   *
   * @param name from OpenSCENARIO class model specification: [Name of the pedestrian type. Required
   *     when used in catalog.]
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * Sets the value of model property pedestrianCategory
   *
   * @param pedestrianCategory from OpenSCENARIO class model specification: [Category type of
   *     pedestrian.]
   */
  public void setPedestrianCategory(PedestrianCategory pedestrianCategory) {
    this.pedestrianCategory = pedestrianCategory;
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
   * @param boundingBox from OpenSCENARIO class model specification: [Bounding box of the
   *     pedestrian.]
   */
  public void setBoundingBox(IBoundingBox boundingBox) {
    this.boundingBox = boundingBox;
  }
  /**
   * Sets the value of model property properties
   *
   * @param properties from OpenSCENARIO class model specification: [Properties (values/files) of
   *     the pedestrian.]
   */
  public void setProperties(IProperties properties) {
    this.properties = properties;
  }

  @Override
  public void resolveParameterInternal(
      IParserMessageLogger logger, String attributeKey, String parameterLiteralValue) {
    if (attributeKey.equals(OscConstants.ATTRIBUTE__MODEL)) {
      // Simple type
      this.model =
          ParserHelper.parseString(logger, parameterLiteralValue, getTextmarker(attributeKey));
      addResolvedParameter(attributeKey);

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

    } else if (attributeKey.equals(OscConstants.ATTRIBUTE__PEDESTRIAN_CATEGORY)) {
      // Enumeration Type
      PedestrianCategory result = PedestrianCategory.getFromLiteral(parameterLiteralValue);
      if (result != null) {
        this.pedestrianCategory = result;
        addResolvedParameter(attributeKey);
      } else {
        logger.logMessage(
            new FileContentMessage(
                "Value '" + parameterLiteralValue + "' is not allowed.",
                ErrorLevel.ERROR,
                getTextmarker(attributeKey)));
      }
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
  public PedestrianImpl clone() {
    PedestrianImpl clonedObject = new PedestrianImpl();
    cloneStartMarker(clonedObject);
    cloneEndMarker(clonedObject);
    cloneAttributeKeyToStartMarker(clonedObject);
    cloneAttributeKeyToEndMarker(clonedObject);
    cloneAttributeKeyToParameterNameMap(clonedObject);
    // clone attributes;
    // Simple type
    clonedObject.setModel(getModel());
    // Simple type
    clonedObject.setMass(getMass());
    // Simple type
    clonedObject.setName(getName());
    // Enumeration Type
    PedestrianCategory pedestrianCategory = getPedestrianCategory();
    if (pedestrianCategory != null) {
      clonedObject.setPedestrianCategory(
          PedestrianCategory.getFromLiteral(pedestrianCategory.getLiteral()));
    }
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
    if (key.equals(OscConstants.ATTRIBUTE__MODEL)) {
      return getModel();
    } else if (key.equals(OscConstants.ATTRIBUTE__NAME)) {
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
    if (key.equals(OscConstants.ATTRIBUTE__PEDESTRIAN_CATEGORY)) {
      PedestrianCategory pedestrianCategory = getPedestrianCategory();
      return pedestrianCategory != null ? pedestrianCategory.getLiteral() : null;
    }
    throw new KeyNotSupportedException();
  }

  @Override
  public String getModelType() {
    return "Pedestrian";
  }
}
