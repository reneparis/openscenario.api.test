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
import net.asam.openscenario.v1_0.api.ILaneChangeAction;
import net.asam.openscenario.v1_0.api.ILaneChangeTarget;
import net.asam.openscenario.v1_0.api.ITransitionDynamics;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>Value class that implements ILaneChangeAction. With setter methods to fill the properties.
 *
 * <ul>
 *   <li>getter methods for properties (implemented methods of ILaneChangeAction)
 *   <li>setter methods for properties
 *   <li>getChildren method to collect all children
 *   <li>clone function to make a deep copy
 *   <li>overrides from BaseImpl
 * </ul>
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class LaneChangeActionImpl extends BaseImpl implements ILaneChangeAction {
  protected static Hashtable<String, SimpleType> propertyToType = new Hashtable<>();

  /** Filling the property to type map */
  static {
    propertyToType.put(OscConstants.ATTRIBUTE__TARGET_LANE_OFFSET, SimpleType.DOUBLE);
  }

  private Double targetLaneOffset;
  private ITransitionDynamics laneChangeActionDynamics;
  private ILaneChangeTarget laneChangeTarget;
  /** Default constructor */
  public LaneChangeActionImpl() {
    super();
    addAdapter(LaneChangeActionImpl.class, this);
    addAdapter(ILaneChangeAction.class, this);
  }

  @Override
  public IOpenScenarioFlexElement getOpenScenarioFlexElement() {
    return this;
  }

  @Override
  public Double getTargetLaneOffset() {
    return this.targetLaneOffset;
  }

  @Override
  public ITransitionDynamics getLaneChangeActionDynamics() {
    return this.laneChangeActionDynamics;
  }

  @Override
  public ILaneChangeTarget getLaneChangeTarget() {
    return this.laneChangeTarget;
  }
  /**
   * Sets the value of model property targetLaneOffset
   *
   * @param targetLaneOffset from OpenSCENARIO class model specification: [Lane offset to be reached
   *     at the target lane; the action will end there. Unit: m.]
   */
  public void setTargetLaneOffset(Double targetLaneOffset) {
    this.targetLaneOffset = targetLaneOffset;
  }
  /**
   * Sets the value of model property laneChangeActionDynamics
   *
   * @param laneChangeActionDynamics from OpenSCENARIO class model specification: [Shape/time of
   *     lane change action.]
   */
  public void setLaneChangeActionDynamics(ITransitionDynamics laneChangeActionDynamics) {
    this.laneChangeActionDynamics = laneChangeActionDynamics;
  }
  /**
   * Sets the value of model property laneChangeTarget
   *
   * @param laneChangeTarget from OpenSCENARIO class model specification: [Direction of lane change
   *     action.]
   */
  public void setLaneChangeTarget(ILaneChangeTarget laneChangeTarget) {
    this.laneChangeTarget = laneChangeTarget;
  }

  @Override
  public void resolveParameterInternal(
      IParserMessageLogger logger, String attributeKey, String parameterLiteralValue) {
    if (attributeKey.equals(OscConstants.ATTRIBUTE__TARGET_LANE_OFFSET)) {
      // Simple type
      this.targetLaneOffset =
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

    ITransitionDynamics laneChangeActionDynamics = null;
    laneChangeActionDynamics = getLaneChangeActionDynamics();
    if (laneChangeActionDynamics != null) {
      result.add((BaseImpl) laneChangeActionDynamics);
    }
    ILaneChangeTarget laneChangeTarget = null;
    laneChangeTarget = getLaneChangeTarget();
    if (laneChangeTarget != null) {
      result.add((BaseImpl) laneChangeTarget);
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
  public LaneChangeActionImpl clone() {
    LaneChangeActionImpl clonedObject = new LaneChangeActionImpl();
    cloneStartMarker(clonedObject);
    cloneEndMarker(clonedObject);
    cloneAttributeKeyToStartMarker(clonedObject);
    cloneAttributeKeyToEndMarker(clonedObject);
    cloneAttributeKeyToParameterNameMap(clonedObject);
    // clone attributes;
    // Simple type
    clonedObject.setTargetLaneOffset(getTargetLaneOffset());
    // clone children
    ITransitionDynamics laneChangeActionDynamics = null;
    laneChangeActionDynamics = getLaneChangeActionDynamics();
    if (laneChangeActionDynamics != null) {
      TransitionDynamicsImpl clonedChild =
          ((TransitionDynamicsImpl) laneChangeActionDynamics).clone();
      clonedObject.setLaneChangeActionDynamics(clonedChild);
      clonedChild.setParent(clonedObject);
    }
    ILaneChangeTarget laneChangeTarget = null;
    laneChangeTarget = getLaneChangeTarget();
    if (laneChangeTarget != null) {
      LaneChangeTargetImpl clonedChild = ((LaneChangeTargetImpl) laneChangeTarget).clone();
      clonedObject.setLaneChangeTarget(clonedChild);
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
    if (key.equals(OscConstants.ATTRIBUTE__TARGET_LANE_OFFSET)) {
      return getTargetLaneOffset();
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
    if (key.equals(OscConstants.ELEMENT__LANE_CHANGE_ACTION_DYNAMICS)) {
      return (IOpenScenarioFlexElement) getLaneChangeActionDynamics();
    }
    if (key.equals(OscConstants.ELEMENT__LANE_CHANGE_TARGET)) {
      return (IOpenScenarioFlexElement) getLaneChangeTarget();
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
    return "LaneChangeAction";
  }
}
