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

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>From OpenSCENARIO class model specification: The road network which provides the base layer
 * (and road-relative coordinate systems) for all scenario elements is defined as a reference to a
 * road network file. This reference is optional; in case it is not specified, an infinite
 * non-inclined plane on level z=0 shall be assumed as base layer. Additionally and optionally, a
 * visual 3D model file can be referenced and any number of signals can be specified.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public interface IRoadNetwork extends IOpenScenarioModelElement {
  /**
   * From OpenSCENARIO class model specification: File path of the road network file (e.g. an ASAM
   * OpenDRIVE file).
   *
   * @return value of model property logicFile
   */
  public IFile getLogicFile();
  /**
   * From OpenSCENARIO class model specification: File path of a 3D model representing the virtual
   * environment. This may be used for visual representation (rendering).
   *
   * @return value of model property sceneGraphFile
   */
  public IFile getSceneGraphFile();
  /**
   * From OpenSCENARIO class model specification: Name references and description of dynamic
   * behavior for traffic signals defined in the road network file.
   *
   * @return value of model property trafficSignals
   */
  public List<ITrafficSignalController> getTrafficSignals();
}
