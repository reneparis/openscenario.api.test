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
package net.asam.openscenario.v1_0.api;

import java.util.List;
import net.asam.openscenario.api.IOpenScenarioModelElement;
import net.asam.openscenario.common.INamedReference;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>From OpenSCENARIO class model specification: Considered true if a given traffic signal
 * controller (which may be defined within OpenSCENARIO or externally) reaches a specific state.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public interface ITrafficSignalControllerCondition extends IOpenScenarioModelElement {
  /**
   * From OpenSCENARIO class model specification: ID of the referenced signal controller in a road
   * network.
   *
   * @return value of model property trafficSignalControllerRef
   */
  public INamedReference<ITrafficSignalController> getTrafficSignalControllerRef();
  /**
   * From OpenSCENARIO class model specification: Name of the phase of the signal controller to be
   * reached for the condition to become true. The available phases are defined in type RoadNetwork
   * under the property trafficSignalControllers.
   *
   * @return value of model property phase
   */
  public String getPhase();
  /**
   * From OpenSCENARIO class model specification: The reference to the phase (phase is the
   * referential key in the referenced TrafficSignalController).
   *
   * @return value of model property phaseRef
   */
  public List<IPhase> getPhaseRef();
}
