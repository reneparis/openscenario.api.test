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
import net.asam.openscenario.v1_0.api.IPosition;
import net.asam.openscenario.v1_0.api.ITrafficDefinition;
import net.asam.openscenario.v1_0.api.ITrafficSinkAction;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>Value class that implements ITrafficSinkAction. With setter methods to fill the properties.
 *
 * <ul>
 *   <li>getter methods for properties (implemented methods of ITrafficSinkAction)
 *   <li>setter methods for properties
 *   <li>getChildren method to collect all children
 *   <li>clone function to make a deep copy
 *   <li>overrides from BaseImpl
 * </ul>
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class TrafficSinkActionImpl extends BaseImpl implements ITrafficSinkAction {
  protected static Hashtable<String, SimpleType> propertyToType = new Hashtable<>();

  /** Filling the property to type map */
  static {
    propertyToType.put(OscConstants.ATTRIBUTE__RATE, SimpleType.DOUBLE);
    propertyToType.put(OscConstants.ATTRIBUTE__RADIUS, SimpleType.DOUBLE);
  }

  private Double rate;
  private Double radius;
  private IPosition position;
  private ITrafficDefinition trafficDefinition;
  /** Default constructor */
  public TrafficSinkActionImpl() {
    super();
    addAdapter(TrafficSinkActionImpl.class, this);
    addAdapter(ITrafficSinkAction.class, this);
  }

  @Override
  public IOpenScenarioFlexElement getOpenScenarioFlexElement() {
    return this;
  }

  @Override
  public Double getRate() {
    return this.rate;
  }

  @Override
  public Double getRadius() {
    return this.radius;
  }

  @Override
  public IPosition getPosition() {
    return this.position;
  }

  @Override
  public ITrafficDefinition getTrafficDefinition() {
    return this.trafficDefinition;
  }
  /**
   * Sets the value of model property rate
   *
   * @param rate from OpenSCENARIO class model specification: [Defines the rate on which vehicles
   *     disappear at the sinks location. Unit: vehicles/s Range: [0..inf[.]
   */
  public void setRate(Double rate) {
    this.rate = rate;
  }
  /**
   * Sets the value of model property radius
   *
   * @param radius from OpenSCENARIO class model specification: [Defines the radius of the traffic
   *     sink where vehicles disappear around the specified position. Unit: m; Range: [0..inf[.]
   */
  public void setRadius(Double radius) {
    this.radius = radius;
  }
  /**
   * Sets the value of model property position
   *
   * @param position from OpenSCENARIO class model specification: [Defines the position of the
   *     traffic sink.]
   */
  public void setPosition(IPosition position) {
    this.position = position;
  }
  /**
   * Sets the value of model property trafficDefinition
   *
   * @param trafficDefinition from OpenSCENARIO class model specification: [Defines the vehicle and
   *     controller distribution for the sink.]
   */
  public void setTrafficDefinition(ITrafficDefinition trafficDefinition) {
    this.trafficDefinition = trafficDefinition;
  }

  @Override
  public void resolveParameterInternal(
      IParserMessageLogger logger, String attributeKey, String parameterLiteralValue) {
    if (attributeKey.equals(OscConstants.ATTRIBUTE__RATE)) {
      // Simple type
      this.rate =
          ParserHelper.parseDouble(logger, parameterLiteralValue, getTextmarker(attributeKey));
      addResolvedParameter(attributeKey);

    } else if (attributeKey.equals(OscConstants.ATTRIBUTE__RADIUS)) {
      // Simple type
      this.radius =
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
    ITrafficDefinition trafficDefinition = null;
    trafficDefinition = getTrafficDefinition();
    if (trafficDefinition != null) {
      result.add((BaseImpl) trafficDefinition);
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
  public TrafficSinkActionImpl clone() {
    TrafficSinkActionImpl clonedObject = new TrafficSinkActionImpl();
    cloneStartMarker(clonedObject);
    cloneEndMarker(clonedObject);
    cloneAttributeKeyToStartMarker(clonedObject);
    cloneAttributeKeyToEndMarker(clonedObject);
    cloneAttributeKeyToParameterNameMap(clonedObject);
    // clone attributes;
    // Simple type
    clonedObject.setRate(getRate());
    // Simple type
    clonedObject.setRadius(getRadius());
    // clone children
    IPosition position = null;
    position = getPosition();
    if (position != null) {
      PositionImpl clonedChild = ((PositionImpl) position).clone();
      clonedObject.setPosition(clonedChild);
      clonedChild.setParent(clonedObject);
    }
    ITrafficDefinition trafficDefinition = null;
    trafficDefinition = getTrafficDefinition();
    if (trafficDefinition != null) {
      TrafficDefinitionImpl clonedChild = ((TrafficDefinitionImpl) trafficDefinition).clone();
      clonedObject.setTrafficDefinition(clonedChild);
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
    if (key.equals(OscConstants.ATTRIBUTE__RATE)) {
      return getRate();
    } else if (key.equals(OscConstants.ATTRIBUTE__RADIUS)) {
      return getRadius();
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
    if (key.equals(OscConstants.ELEMENT__TRAFFIC_DEFINITION)) {
      return (IOpenScenarioFlexElement) getTrafficDefinition();
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
    return "TrafficSinkAction";
  }
}
