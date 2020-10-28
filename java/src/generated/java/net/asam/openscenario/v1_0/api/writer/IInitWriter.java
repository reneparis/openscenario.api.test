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

import net.asam.openscenario.api.writer.IOpenScenarioElementWriter;
import net.asam.openscenario.v1_0.api.IInit;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 *
 * <p>A Writer for the type 'Init' From OpenSCENARIO class model specification: Defines the actions
 * which are executed during the initialization phase of a scenario.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public interface IInitWriter extends IInit, IOpenScenarioElementWriter {

  // Setters for all attributes

  /**
   * From OpenSCENARIO class model specification: A list of actions initially executed when the
   * enclosing storyboard starts.
   *
   * @param actions value of model property actions
   */
  public void setActions(IInitActionsWriter actions);

  // children
  /**
   * From OpenSCENARIO class model specification: A list of actions initially executed when the
   * enclosing storyboard starts.
   *
   * @return a writer for model property actions
   */
  public IInitActionsWriter getWriterActions();
}
