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
import net.asam.openscenario.v1_0.api.FollowingMode;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>A Writer for the type 'TrajectoryFollowingMode' From OpenSCENARIO class model specification:
 * Used to define a steering trajectory.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public interface ITrajectoryFollowingModeWriter extends IOpenScenarioModelElement {

  // Getters and setter for all attributes
  /**
   * From OpenSCENARIO class model specification: Defines (lateral) trajectory following behavior of
   * the actor: Mode 'position' forces the actor to strictly adhere to the trajectory. In contrast,
   * mode 'follow' hands over control to the actor. In this mode, the actor tries to follow the
   * trajectory as best as he can. This may be restricted by dynamics constraints and/or control
   * loop implementation.
   *
   * @return value of model property followingMode
   */
  public FollowingMode getFollowingMode();

  /**
   * From OpenSCENARIO class model specification: Defines (lateral) trajectory following behavior of
   * the actor: Mode 'position' forces the actor to strictly adhere to the trajectory. In contrast,
   * mode 'follow' hands over control to the actor. In this mode, the actor tries to follow the
   * trajectory as best as he can. This may be restricted by dynamics constraints and/or control
   * loop implementation.
   *
   * @param followingMode value of model property followingMode
   */
  public void writeToFollowingMode(FollowingMode followingMode);

  /**
   * Set a parameter for the attribute followingMode
   *
   * @param parameterName the name of the parameter (without $)
   */
  public void writeParameterToFollowingMode(String parameterName);

  /**
   * Get the parameter for the attribute followingMode
   *
   * @return the name of the parameter (without $). Null if not parameter set or if attribute is
   *     empty.
   */
  public String getParameterFromFollowingMode();

  /**
   * Retrieves whether the attribute followingMode is parametrized.
   *
   * @return true if ${property.name.toMemberName()} is paramterized.
   */
  public boolean isFollowingModeParameterized();

  // children

}
