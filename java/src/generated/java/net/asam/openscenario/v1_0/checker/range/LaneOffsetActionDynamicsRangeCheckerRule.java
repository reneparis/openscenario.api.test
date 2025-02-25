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
package net.asam.openscenario.v1_0.checker.range;

import net.asam.openscenario.checker.RangeCheckerRule;
import net.asam.openscenario.common.IParserMessageLogger;
import net.asam.openscenario.v1_0.api.ILaneOffsetActionDynamics;
import net.asam.openscenario.v1_0.common.OscConstants;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0 A
 * range checker rule class for the type ILaneOffsetActionDynamics according to OpenSCENARIO
 * standard
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class LaneOffsetActionDynamicsRangeCheckerRule
    extends RangeCheckerRule<ILaneOffsetActionDynamics> {

  /** Default constructor */
  public LaneOffsetActionDynamicsRangeCheckerRule() {
    super();
  }

  @Override
  public void applyRule(IParserMessageLogger messageLogger, ILaneOffsetActionDynamics object) {
    Double maxLateralAcc = object.getMaxLateralAcc();
    if (maxLateralAcc != null) {
      if (!(maxLateralAcc >= 0)) {
        logMessage(
            object,
            messageLogger,
            OscConstants.ATTRIBUTE__MAX_LATERAL_ACC,
            object.getMaxLateralAcc().toString(),
            ">=",
            "0",
            OscConstants.ATTRIBUTE__MAX_LATERAL_ACC);
      }
    }
  }
}
