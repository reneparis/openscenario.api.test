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
import net.asam.openscenario.v1_0.api.IAbsoluteTargetLane;
import net.asam.openscenario.v1_0.api.ILaneChangeTarget;
import net.asam.openscenario.v1_0.api.IRelativeTargetLane;
import net.asam.openscenario.v1_0.api.writer.IAbsoluteTargetLaneWriter;
import net.asam.openscenario.v1_0.api.writer.ILaneChangeTargetWriter;
import net.asam.openscenario.v1_0.api.writer.IRelativeTargetLaneWriter;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>Value class that implements ILaneChangeTarget. With setter methods to fill the properties.
 *
 * <ul>
 *   <li>getter methods for properties (implemented methods of ILaneChangeTarget)
 *   <li>setter methods for properties
 *   <li>getChildren method to collect all children
 *   <li>clone function to make a deep copy
 *   <li>overrides from BaseImpl
 * </ul>
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class LaneChangeTargetImpl extends BaseImpl implements ILaneChangeTargetWriter {
  protected static Hashtable<String, SimpleType> propertyToType = new Hashtable<>();

  private IRelativeTargetLaneWriter relativeTargetLane;
  private IAbsoluteTargetLaneWriter absoluteTargetLane;

  /** Default constructor */
  public LaneChangeTargetImpl() {
    super();
    addAdapter(LaneChangeTargetImpl.class, this);
    addAdapter(ILaneChangeTarget.class, this);
    addAdapter(ILaneChangeTargetWriter.class, this);
  }

  @Override
  public IOpenScenarioFlexElement getOpenScenarioFlexElement() {
    return this;
  }

  @Override
  public IRelativeTargetLane getRelativeTargetLane() {
    return this.relativeTargetLane;
  }

  @Override
  public IAbsoluteTargetLane getAbsoluteTargetLane() {
    return this.absoluteTargetLane;
  }

  @Override
  public void setRelativeTargetLane(IRelativeTargetLaneWriter relativeTargetLane) {
    this.relativeTargetLane = relativeTargetLane;
  }

  @Override
  public void setAbsoluteTargetLane(IAbsoluteTargetLaneWriter absoluteTargetLane) {
    this.absoluteTargetLane = absoluteTargetLane;
  }

  @Override
  public void resolveParameterInternal(
      IParserMessageLogger logger, String attributeKey, String parameterLiteralValue) {
    // Empty
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

    IRelativeTargetLaneWriter relativeTargetLane = null;
    relativeTargetLane = getWriterRelativeTargetLane();
    if (relativeTargetLane != null) {
      result.add((BaseImpl) relativeTargetLane);
    }
    IAbsoluteTargetLaneWriter absoluteTargetLane = null;
    absoluteTargetLane = getWriterAbsoluteTargetLane();
    if (absoluteTargetLane != null) {
      result.add((BaseImpl) absoluteTargetLane);
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
  public LaneChangeTargetImpl clone() {
    LaneChangeTargetImpl clonedObject = new LaneChangeTargetImpl();
    cloneStartMarker(clonedObject);
    cloneEndMarker(clonedObject);
    cloneAttributeKeyToStartMarker(clonedObject);
    cloneAttributeKeyToEndMarker(clonedObject);
    cloneAttributeKeyToParameterNameMap(clonedObject);
    // clone attributes;
    // clone children
    IRelativeTargetLaneWriter relativeTargetLane = null;
    relativeTargetLane = getWriterRelativeTargetLane();
    if (relativeTargetLane != null) {
      IRelativeTargetLaneWriter clonedChild = ((RelativeTargetLaneImpl) relativeTargetLane).clone();
      clonedObject.setRelativeTargetLane(clonedChild);
      clonedChild.setParent(clonedObject);
    }
    IAbsoluteTargetLaneWriter absoluteTargetLane = null;
    absoluteTargetLane = getWriterAbsoluteTargetLane();
    if (absoluteTargetLane != null) {
      IAbsoluteTargetLaneWriter clonedChild = ((AbsoluteTargetLaneImpl) absoluteTargetLane).clone();
      clonedObject.setAbsoluteTargetLane(clonedChild);
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
    if (key.equals(OscConstants.ELEMENT__RELATIVE_TARGET_LANE)) {
      return (IOpenScenarioFlexElement) getRelativeTargetLane();
    }
    if (key.equals(OscConstants.ELEMENT__ABSOLUTE_TARGET_LANE)) {
      return (IOpenScenarioFlexElement) getAbsoluteTargetLane();
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
    return "LaneChangeTarget";
  }

  // children
  @Override
  public IRelativeTargetLaneWriter getWriterRelativeTargetLane() {
    return this.relativeTargetLane;
  }

  @Override
  public IAbsoluteTargetLaneWriter getWriterAbsoluteTargetLane() {
    return this.absoluteTargetLane;
  }
}
