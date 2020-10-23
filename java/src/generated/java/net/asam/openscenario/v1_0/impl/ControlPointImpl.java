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
import net.asam.openscenario.v1_0.api.IControlPoint;
import net.asam.openscenario.v1_0.api.IPosition;
import net.asam.openscenario.v1_0.api.writer.IControlPointWriter;
import net.asam.openscenario.v1_0.api.writer.IPositionWriter;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>Value class that implements IControlPoint. With setter methods to fill the properties.
 *
 * <ul>
 *   <li>getter methods for properties (implemented methods of IControlPoint)
 *   <li>setter methods for properties
 *   <li>getChildren method to collect all children
 *   <li>clone function to make a deep copy
 *   <li>overrides from BaseImpl
 * </ul>
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class ControlPointImpl extends BaseImpl implements IControlPoint, IControlPointWriter {
  protected static Hashtable<String, SimpleType> propertyToType = new Hashtable<>();

  /** Filling the property to type map */
  static {
    propertyToType.put(OscConstants.ATTRIBUTE__TIME, SimpleType.DOUBLE);
    propertyToType.put(OscConstants.ATTRIBUTE__WEIGHT, SimpleType.DOUBLE);
  }

  private Double time;
  private Double weight;
  private IPosition position;
  /** Default constructor */
  public ControlPointImpl() {
    super();
    addAdapter(ControlPointImpl.class, this);
    addAdapter(IControlPoint.class, this);
    addAdapter(IControlPointWriter.class, this);
  }

  @Override
  public IOpenScenarioFlexElement getOpenScenarioFlexElement() {
    return this;
  }

  @Override
  public Double getTime() {
    return this.time;
  }

  @Override
  public Double getWeight() {
    return this.weight;
  }

  @Override
  public IPosition getPosition() {
    return this.position;
  }
  /**
   * Sets the value of model property time
   *
   * @param time from OpenSCENARIO class model specification: [Optional specification of the time
   *     dimension of the control point. Unit: s;Range [0..inf[.]
   */
  public void setTime(Double time) {
    this.time = time;
  }
  /**
   * Sets the value of model property weight
   *
   * @param weight from OpenSCENARIO class model specification: [Optional weight specification of
   *     the control point. If unspecified, all control points will be equal weighted. Range ,
   *     ]-inf..inf[.]
   */
  public void setWeight(Double weight) {
    this.weight = weight;
  }
  /**
   * Sets the value of model property position
   *
   * @param position from OpenSCENARIO class model specification: [Position of the control point.]
   */
  public void setPosition(IPosition position) {
    this.position = position;
  }

  @Override
  public void resolveParameterInternal(
      IParserMessageLogger logger, String attributeKey, String parameterLiteralValue) {
    if (attributeKey.equals(OscConstants.ATTRIBUTE__TIME)) {
      // Simple type
      this.time =
          ParserHelper.parseDouble(logger, parameterLiteralValue, getTextmarker(attributeKey));
      addResolvedParameter(attributeKey);

    } else if (attributeKey.equals(OscConstants.ATTRIBUTE__WEIGHT)) {
      // Simple type
      this.weight =
          ParserHelper.parseDouble(logger, parameterLiteralValue, getTextmarker(attributeKey));
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

    IPosition position = null;
    position = getPosition();
    if (position != null) {
      result.add((BaseImpl) position);
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
  public ControlPointImpl clone() {
    ControlPointImpl clonedObject = new ControlPointImpl();
    cloneStartMarker(clonedObject);
    cloneEndMarker(clonedObject);
    cloneAttributeKeyToStartMarker(clonedObject);
    cloneAttributeKeyToEndMarker(clonedObject);
    cloneAttributeKeyToParameterNameMap(clonedObject);
    // clone attributes;
    // Simple type
    clonedObject.setTime(getTime());
    // Simple type
    clonedObject.setWeight(getWeight());
    // clone children
    IPosition position = null;
    position = getPosition();
    if (position != null) {
      PositionImpl clonedChild = ((PositionImpl) position).clone();
      clonedObject.setPosition(clonedChild);
      clonedChild.setParent(clonedObject);
    }
    return clonedObject;
  }

  // Implement the IOpenScenarioFlexElement interface

  @Override
  public String getStringProperty(String key) throws KeyNotSupportedException {
    // proxies and string attributes
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
    if (key.equals(OscConstants.ATTRIBUTE__TIME)) {
      return getTime();
    } else if (key.equals(OscConstants.ATTRIBUTE__WEIGHT)) {
      return getWeight();
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
    if (key.equals(OscConstants.ELEMENT__POSITION)) {
      return (IOpenScenarioFlexElement) getPosition();
    }
    throw new KeyNotSupportedException();
  }

  @Override
  public List<IOpenScenarioFlexElement> getListChildElement(String key)
      throws KeyNotSupportedException {

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
    return "ControlPoint";
  }

  @Override
  public void writeToTime(Double time) {
    // empty
  }

  @Override
  public void writeToWeight(Double weight) {
    // empty
  }

  @Override
  public void writeParameterToTime(String parameterName) {
    // empty
  }

  @Override
  public void writeParameterToWeight(String parameterName) {
    // empty
  }

  @Override
  public String getParameterFromTime() {
    return null;
  }

  @Override
  public String getParameterFromWeight() {
    return null;
  }

  @Override
  public boolean isTimeParameterized() {
    return false;
  }

  @Override
  public boolean isWeightParameterized() {
    return false;
  }

  // children
  @Override
  public IPositionWriter getPositionWriter() {
    return null;
  }

  @Override
  public void writeToPositionWriter(IPositionWriter positionWriter) {
    // empty
  }
}
