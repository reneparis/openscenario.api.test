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
package net.asam.openscenario.v1_0.api.writer;

import net.asam.openscenario.api.IOpenScenarioModelElement;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>A Writer for the type 'SpeedAction' From OpenSCENARIO class model specification: This action
 * describes the transition of an entity's longitudinal speed to a target longitudinal speed.
 * SpeedActionDynamics specifies the transition with respects to time or distance combined with a
 * shape.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public interface ISpeedActionWriter extends IOpenScenarioModelElement {

  // children
  /**
   * From OpenSCENARIO class model specification: Defines how the target speed is reached.
   *
   * @return a writer for model property speedActionDynamics
   */
  public ITransitionDynamicsWriter getSpeedActionDynamicsWriter();
  /**
   * From OpenSCENARIO class model specification: Defines the target speed which should be reached.
   *
   * @return a writer for model property speedActionTarget
   */
  public ISpeedActionTargetWriter getSpeedActionTargetWriter();

  /**
   * From OpenSCENARIO class model specification: Defines how the target speed is reached.
   *
   * @param speedActionDynamicsWriter writer for the model property speedActionDynamics
   */
  public void writeToSpeedActionDynamicsWriter(ITransitionDynamicsWriter speedActionDynamicsWriter);
  /**
   * From OpenSCENARIO class model specification: Defines the target speed which should be reached.
   *
   * @param speedActionTargetWriter writer for the model property speedActionTarget
   */
  public void writeToSpeedActionTargetWriter(ISpeedActionTargetWriter speedActionTargetWriter);
}
