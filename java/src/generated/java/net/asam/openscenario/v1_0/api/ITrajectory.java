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

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>From OpenSCENARIO class model specification: Definition of a trajectory type in terms of shape
 * and optional time domain.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public interface ITrajectory extends ICatalogElement {
  /**
   * From OpenSCENARIO class model specification: Name of the trajectory type. Required if used in
   * catalog.
   *
   * @return value of model property name
   */
  public String getName();
  /**
   * From OpenSCENARIO class model specification: True if trajectory is closed.
   *
   * @return value of model property closed
   */
  public Boolean getClosed();
  /**
   * From OpenSCENARIO class model specification: Definition of additional parameters.
   *
   * @return value of model property parameterDeclarations
   */
  public List<IParameterDeclaration> getParameterDeclarations();
  /**
   * From OpenSCENARIO class model specification: The shape of a trajectory (Polyline, Clothoid or
   * Nurbs)
   *
   * @return value of model property shape
   */
  public IShape getShape();
}
