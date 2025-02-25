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
package net.asam.openscenario.v1_0.checker.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import net.asam.openscenario.checker.ICheckerRule;
import net.asam.openscenario.common.IParserMessageLogger;
import net.asam.openscenario.v1_0.api.IAbsoluteSpeed;
import net.asam.openscenario.v1_0.api.IAbsoluteTargetLane;
import net.asam.openscenario.v1_0.api.IAbsoluteTargetLaneOffset;
import net.asam.openscenario.v1_0.api.IAbsoluteTargetSpeed;
import net.asam.openscenario.v1_0.api.IAccelerationCondition;
import net.asam.openscenario.v1_0.api.IAcquirePositionAction;
import net.asam.openscenario.v1_0.api.IAct;
import net.asam.openscenario.v1_0.api.IAction;
import net.asam.openscenario.v1_0.api.IActivateControllerAction;
import net.asam.openscenario.v1_0.api.IActors;
import net.asam.openscenario.v1_0.api.IAddEntityAction;
import net.asam.openscenario.v1_0.api.IAssignControllerAction;
import net.asam.openscenario.v1_0.api.IAssignRouteAction;
import net.asam.openscenario.v1_0.api.IAxle;
import net.asam.openscenario.v1_0.api.IAxles;
import net.asam.openscenario.v1_0.api.IBoundingBox;
import net.asam.openscenario.v1_0.api.IByEntityCondition;
import net.asam.openscenario.v1_0.api.IByObjectType;
import net.asam.openscenario.v1_0.api.IByType;
import net.asam.openscenario.v1_0.api.IByValueCondition;
import net.asam.openscenario.v1_0.api.ICatalog;
import net.asam.openscenario.v1_0.api.ICatalogDefinition;
import net.asam.openscenario.v1_0.api.ICatalogLocations;
import net.asam.openscenario.v1_0.api.ICatalogReference;
import net.asam.openscenario.v1_0.api.ICenter;
import net.asam.openscenario.v1_0.api.ICentralSwarmObject;
import net.asam.openscenario.v1_0.api.IClothoid;
import net.asam.openscenario.v1_0.api.ICollisionCondition;
import net.asam.openscenario.v1_0.api.ICondition;
import net.asam.openscenario.v1_0.api.IConditionGroup;
import net.asam.openscenario.v1_0.api.IControlPoint;
import net.asam.openscenario.v1_0.api.IController;
import net.asam.openscenario.v1_0.api.IControllerAction;
import net.asam.openscenario.v1_0.api.IControllerCatalogLocation;
import net.asam.openscenario.v1_0.api.IControllerDistribution;
import net.asam.openscenario.v1_0.api.IControllerDistributionEntry;
import net.asam.openscenario.v1_0.api.ICustomCommandAction;
import net.asam.openscenario.v1_0.api.IDeleteEntityAction;
import net.asam.openscenario.v1_0.api.IDimensions;
import net.asam.openscenario.v1_0.api.IDirectory;
import net.asam.openscenario.v1_0.api.IDistanceCondition;
import net.asam.openscenario.v1_0.api.IDynamicConstraints;
import net.asam.openscenario.v1_0.api.IEndOfRoadCondition;
import net.asam.openscenario.v1_0.api.IEntities;
import net.asam.openscenario.v1_0.api.IEntityAction;
import net.asam.openscenario.v1_0.api.IEntityCondition;
import net.asam.openscenario.v1_0.api.IEntityObject;
import net.asam.openscenario.v1_0.api.IEntityRef;
import net.asam.openscenario.v1_0.api.IEntitySelection;
import net.asam.openscenario.v1_0.api.IEnvironment;
import net.asam.openscenario.v1_0.api.IEnvironmentAction;
import net.asam.openscenario.v1_0.api.IEnvironmentCatalogLocation;
import net.asam.openscenario.v1_0.api.IEvent;
import net.asam.openscenario.v1_0.api.IFile;
import net.asam.openscenario.v1_0.api.IFileHeader;
import net.asam.openscenario.v1_0.api.IFinalSpeed;
import net.asam.openscenario.v1_0.api.IFog;
import net.asam.openscenario.v1_0.api.IFollowTrajectoryAction;
import net.asam.openscenario.v1_0.api.IGlobalAction;
import net.asam.openscenario.v1_0.api.IInRoutePosition;
import net.asam.openscenario.v1_0.api.IInfrastructureAction;
import net.asam.openscenario.v1_0.api.IInit;
import net.asam.openscenario.v1_0.api.IInitActions;
import net.asam.openscenario.v1_0.api.IKnot;
import net.asam.openscenario.v1_0.api.ILaneChangeAction;
import net.asam.openscenario.v1_0.api.ILaneChangeTarget;
import net.asam.openscenario.v1_0.api.ILaneOffsetAction;
import net.asam.openscenario.v1_0.api.ILaneOffsetActionDynamics;
import net.asam.openscenario.v1_0.api.ILaneOffsetTarget;
import net.asam.openscenario.v1_0.api.ILanePosition;
import net.asam.openscenario.v1_0.api.ILateralAction;
import net.asam.openscenario.v1_0.api.ILateralDistanceAction;
import net.asam.openscenario.v1_0.api.ILongitudinalAction;
import net.asam.openscenario.v1_0.api.ILongitudinalDistanceAction;
import net.asam.openscenario.v1_0.api.IManeuver;
import net.asam.openscenario.v1_0.api.IManeuverCatalogLocation;
import net.asam.openscenario.v1_0.api.IManeuverGroup;
import net.asam.openscenario.v1_0.api.IMiscObject;
import net.asam.openscenario.v1_0.api.IMiscObjectCatalogLocation;
import net.asam.openscenario.v1_0.api.IModifyRule;
import net.asam.openscenario.v1_0.api.INone;
import net.asam.openscenario.v1_0.api.INurbs;
import net.asam.openscenario.v1_0.api.IObjectController;
import net.asam.openscenario.v1_0.api.IOffroadCondition;
import net.asam.openscenario.v1_0.api.IOpenScenario;
import net.asam.openscenario.v1_0.api.IOpenScenarioCategory;
import net.asam.openscenario.v1_0.api.IOrientation;
import net.asam.openscenario.v1_0.api.IOverrideBrakeAction;
import net.asam.openscenario.v1_0.api.IOverrideClutchAction;
import net.asam.openscenario.v1_0.api.IOverrideControllerValueAction;
import net.asam.openscenario.v1_0.api.IOverrideGearAction;
import net.asam.openscenario.v1_0.api.IOverrideParkingBrakeAction;
import net.asam.openscenario.v1_0.api.IOverrideSteeringWheelAction;
import net.asam.openscenario.v1_0.api.IOverrideThrottleAction;
import net.asam.openscenario.v1_0.api.IParameterAction;
import net.asam.openscenario.v1_0.api.IParameterAddValueRule;
import net.asam.openscenario.v1_0.api.IParameterAssignment;
import net.asam.openscenario.v1_0.api.IParameterCondition;
import net.asam.openscenario.v1_0.api.IParameterDeclaration;
import net.asam.openscenario.v1_0.api.IParameterModifyAction;
import net.asam.openscenario.v1_0.api.IParameterMultiplyByValueRule;
import net.asam.openscenario.v1_0.api.IParameterSetAction;
import net.asam.openscenario.v1_0.api.IPedestrian;
import net.asam.openscenario.v1_0.api.IPedestrianCatalogLocation;
import net.asam.openscenario.v1_0.api.IPerformance;
import net.asam.openscenario.v1_0.api.IPhase;
import net.asam.openscenario.v1_0.api.IPolyline;
import net.asam.openscenario.v1_0.api.IPosition;
import net.asam.openscenario.v1_0.api.IPositionInLaneCoordinates;
import net.asam.openscenario.v1_0.api.IPositionInRoadCoordinates;
import net.asam.openscenario.v1_0.api.IPositionOfCurrentEntity;
import net.asam.openscenario.v1_0.api.IPrecipitation;
import net.asam.openscenario.v1_0.api.IPrivate;
import net.asam.openscenario.v1_0.api.IPrivateAction;
import net.asam.openscenario.v1_0.api.IProperties;
import net.asam.openscenario.v1_0.api.IProperty;
import net.asam.openscenario.v1_0.api.IReachPositionCondition;
import net.asam.openscenario.v1_0.api.IRelativeDistanceCondition;
import net.asam.openscenario.v1_0.api.IRelativeLanePosition;
import net.asam.openscenario.v1_0.api.IRelativeObjectPosition;
import net.asam.openscenario.v1_0.api.IRelativeRoadPosition;
import net.asam.openscenario.v1_0.api.IRelativeSpeedCondition;
import net.asam.openscenario.v1_0.api.IRelativeSpeedToMaster;
import net.asam.openscenario.v1_0.api.IRelativeTargetLane;
import net.asam.openscenario.v1_0.api.IRelativeTargetLaneOffset;
import net.asam.openscenario.v1_0.api.IRelativeTargetSpeed;
import net.asam.openscenario.v1_0.api.IRelativeWorldPosition;
import net.asam.openscenario.v1_0.api.IRoadCondition;
import net.asam.openscenario.v1_0.api.IRoadNetwork;
import net.asam.openscenario.v1_0.api.IRoadPosition;
import net.asam.openscenario.v1_0.api.IRoute;
import net.asam.openscenario.v1_0.api.IRouteCatalogLocation;
import net.asam.openscenario.v1_0.api.IRoutePosition;
import net.asam.openscenario.v1_0.api.IRouteRef;
import net.asam.openscenario.v1_0.api.IRoutingAction;
import net.asam.openscenario.v1_0.api.IScenarioDefinition;
import net.asam.openscenario.v1_0.api.IScenarioObject;
import net.asam.openscenario.v1_0.api.ISelectedEntities;
import net.asam.openscenario.v1_0.api.IShape;
import net.asam.openscenario.v1_0.api.ISimulationTimeCondition;
import net.asam.openscenario.v1_0.api.ISpeedAction;
import net.asam.openscenario.v1_0.api.ISpeedActionTarget;
import net.asam.openscenario.v1_0.api.ISpeedCondition;
import net.asam.openscenario.v1_0.api.IStandStillCondition;
import net.asam.openscenario.v1_0.api.IStory;
import net.asam.openscenario.v1_0.api.IStoryboard;
import net.asam.openscenario.v1_0.api.IStoryboardElementStateCondition;
import net.asam.openscenario.v1_0.api.ISun;
import net.asam.openscenario.v1_0.api.ISynchronizeAction;
import net.asam.openscenario.v1_0.api.ITeleportAction;
import net.asam.openscenario.v1_0.api.ITimeHeadwayCondition;
import net.asam.openscenario.v1_0.api.ITimeOfDay;
import net.asam.openscenario.v1_0.api.ITimeOfDayCondition;
import net.asam.openscenario.v1_0.api.ITimeReference;
import net.asam.openscenario.v1_0.api.ITimeToCollisionCondition;
import net.asam.openscenario.v1_0.api.ITimeToCollisionConditionTarget;
import net.asam.openscenario.v1_0.api.ITiming;
import net.asam.openscenario.v1_0.api.ITrafficAction;
import net.asam.openscenario.v1_0.api.ITrafficDefinition;
import net.asam.openscenario.v1_0.api.ITrafficSignalAction;
import net.asam.openscenario.v1_0.api.ITrafficSignalCondition;
import net.asam.openscenario.v1_0.api.ITrafficSignalController;
import net.asam.openscenario.v1_0.api.ITrafficSignalControllerAction;
import net.asam.openscenario.v1_0.api.ITrafficSignalControllerCondition;
import net.asam.openscenario.v1_0.api.ITrafficSignalState;
import net.asam.openscenario.v1_0.api.ITrafficSignalStateAction;
import net.asam.openscenario.v1_0.api.ITrafficSinkAction;
import net.asam.openscenario.v1_0.api.ITrafficSourceAction;
import net.asam.openscenario.v1_0.api.ITrafficSwarmAction;
import net.asam.openscenario.v1_0.api.ITrajectory;
import net.asam.openscenario.v1_0.api.ITrajectoryCatalogLocation;
import net.asam.openscenario.v1_0.api.ITrajectoryFollowingMode;
import net.asam.openscenario.v1_0.api.ITransitionDynamics;
import net.asam.openscenario.v1_0.api.ITraveledDistanceCondition;
import net.asam.openscenario.v1_0.api.ITrigger;
import net.asam.openscenario.v1_0.api.ITriggeringEntities;
import net.asam.openscenario.v1_0.api.IUserDefinedAction;
import net.asam.openscenario.v1_0.api.IUserDefinedValueCondition;
import net.asam.openscenario.v1_0.api.IVehicle;
import net.asam.openscenario.v1_0.api.IVehicleCatalogLocation;
import net.asam.openscenario.v1_0.api.IVehicleCategoryDistribution;
import net.asam.openscenario.v1_0.api.IVehicleCategoryDistributionEntry;
import net.asam.openscenario.v1_0.api.IVertex;
import net.asam.openscenario.v1_0.api.IVisibilityAction;
import net.asam.openscenario.v1_0.api.IWaypoint;
import net.asam.openscenario.v1_0.api.IWeather;
import net.asam.openscenario.v1_0.api.IWorldPosition;
import net.asam.openscenario.v1_0.checker.IScenarioChecker;

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0 A
 * range checker rule class for the type
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public class ScenarioCheckerImpl implements IScenarioChecker {
  private Hashtable<Class<?>, List<ICheckerRule<?>>> typeToCheckerRuleList = new Hashtable<>();

  @Override
  public void checkScenario(IParserMessageLogger messageLogger, IOpenScenario openScenario) {
    applyOpenScenarioCheckerRules(messageLogger, openScenario);
  }

  @Override
  public void addAbsoluteSpeedCheckerRule(ICheckerRule<IAbsoluteSpeed> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteSpeed.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAbsoluteSpeed.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAbsoluteTargetLaneCheckerRule(ICheckerRule<IAbsoluteTargetLane> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteTargetLane.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAbsoluteTargetLane.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAbsoluteTargetLaneOffsetCheckerRule(
      ICheckerRule<IAbsoluteTargetLaneOffset> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteTargetLaneOffset.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAbsoluteTargetLaneOffset.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAbsoluteTargetSpeedCheckerRule(ICheckerRule<IAbsoluteTargetSpeed> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteTargetSpeed.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAbsoluteTargetSpeed.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAccelerationConditionCheckerRule(
      ICheckerRule<IAccelerationCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAccelerationCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAccelerationCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAcquirePositionActionCheckerRule(
      ICheckerRule<IAcquirePositionAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAcquirePositionAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAcquirePositionAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addActCheckerRule(ICheckerRule<IAct> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAct.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAct.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addActionCheckerRule(ICheckerRule<IAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addActivateControllerActionCheckerRule(
      ICheckerRule<IActivateControllerAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IActivateControllerAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IActivateControllerAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addActorsCheckerRule(ICheckerRule<IActors> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IActors.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IActors.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAddEntityActionCheckerRule(ICheckerRule<IAddEntityAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAddEntityAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAddEntityAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAssignControllerActionCheckerRule(
      ICheckerRule<IAssignControllerAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAssignControllerAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAssignControllerAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAssignRouteActionCheckerRule(ICheckerRule<IAssignRouteAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAssignRouteAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAssignRouteAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAxleCheckerRule(ICheckerRule<IAxle> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAxle.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAxle.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addAxlesCheckerRule(ICheckerRule<IAxles> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAxles.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IAxles.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addBoundingBoxCheckerRule(ICheckerRule<IBoundingBox> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IBoundingBox.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IBoundingBox.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addByEntityConditionCheckerRule(ICheckerRule<IByEntityCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IByEntityCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IByEntityCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addByObjectTypeCheckerRule(ICheckerRule<IByObjectType> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IByObjectType.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IByObjectType.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addByTypeCheckerRule(ICheckerRule<IByType> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IByType.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IByType.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addByValueConditionCheckerRule(ICheckerRule<IByValueCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IByValueCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IByValueCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCatalogCheckerRule(ICheckerRule<ICatalog> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ICatalog.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICatalog.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCatalogDefinitionCheckerRule(ICheckerRule<ICatalogDefinition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICatalogDefinition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICatalogDefinition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCatalogLocationsCheckerRule(ICheckerRule<ICatalogLocations> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICatalogLocations.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICatalogLocations.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCatalogReferenceCheckerRule(ICheckerRule<ICatalogReference> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICatalogReference.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICatalogReference.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCenterCheckerRule(ICheckerRule<ICenter> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ICenter.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICenter.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCentralSwarmObjectCheckerRule(ICheckerRule<ICentralSwarmObject> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICentralSwarmObject.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICentralSwarmObject.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addClothoidCheckerRule(ICheckerRule<IClothoid> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IClothoid.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IClothoid.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCollisionConditionCheckerRule(ICheckerRule<ICollisionCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICollisionCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICollisionCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addConditionCheckerRule(ICheckerRule<ICondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ICondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addConditionGroupCheckerRule(ICheckerRule<IConditionGroup> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IConditionGroup.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IConditionGroup.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addControlPointCheckerRule(ICheckerRule<IControlPoint> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IControlPoint.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IControlPoint.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addControllerCheckerRule(ICheckerRule<IController> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IController.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IController.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addControllerActionCheckerRule(ICheckerRule<IControllerAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IControllerAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addControllerCatalogLocationCheckerRule(
      ICheckerRule<IControllerCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IControllerCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addControllerDistributionCheckerRule(
      ICheckerRule<IControllerDistribution> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerDistribution.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IControllerDistribution.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addControllerDistributionEntryCheckerRule(
      ICheckerRule<IControllerDistributionEntry> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerDistributionEntry.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IControllerDistributionEntry.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addCustomCommandActionCheckerRule(ICheckerRule<ICustomCommandAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICustomCommandAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ICustomCommandAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addDeleteEntityActionCheckerRule(ICheckerRule<IDeleteEntityAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IDeleteEntityAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IDeleteEntityAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addDimensionsCheckerRule(ICheckerRule<IDimensions> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IDimensions.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IDimensions.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addDirectoryCheckerRule(ICheckerRule<IDirectory> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IDirectory.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IDirectory.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addDistanceConditionCheckerRule(ICheckerRule<IDistanceCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IDistanceCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IDistanceCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addDynamicConstraintsCheckerRule(ICheckerRule<IDynamicConstraints> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IDynamicConstraints.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IDynamicConstraints.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEndOfRoadConditionCheckerRule(ICheckerRule<IEndOfRoadCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEndOfRoadCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEndOfRoadCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEntitiesCheckerRule(ICheckerRule<IEntities> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntities.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEntities.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEntityActionCheckerRule(ICheckerRule<IEntityAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntityAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEntityAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEntityConditionCheckerRule(ICheckerRule<IEntityCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEntityCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEntityCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEntityObjectCheckerRule(ICheckerRule<IEntityObject> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntityObject.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEntityObject.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEntityRefCheckerRule(ICheckerRule<IEntityRef> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntityRef.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEntityRef.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEntitySelectionCheckerRule(ICheckerRule<IEntitySelection> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEntitySelection.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEntitySelection.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEnvironmentCheckerRule(ICheckerRule<IEnvironment> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEnvironment.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEnvironment.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEnvironmentActionCheckerRule(ICheckerRule<IEnvironmentAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEnvironmentAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEnvironmentAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEnvironmentCatalogLocationCheckerRule(
      ICheckerRule<IEnvironmentCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEnvironmentCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEnvironmentCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addEventCheckerRule(ICheckerRule<IEvent> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEvent.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IEvent.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addFileCheckerRule(ICheckerRule<IFile> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFile.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IFile.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addFileHeaderCheckerRule(ICheckerRule<IFileHeader> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFileHeader.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IFileHeader.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addFinalSpeedCheckerRule(ICheckerRule<IFinalSpeed> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFinalSpeed.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IFinalSpeed.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addFogCheckerRule(ICheckerRule<IFog> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFog.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IFog.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addFollowTrajectoryActionCheckerRule(
      ICheckerRule<IFollowTrajectoryAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IFollowTrajectoryAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IFollowTrajectoryAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addGlobalActionCheckerRule(ICheckerRule<IGlobalAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IGlobalAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IGlobalAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addInRoutePositionCheckerRule(ICheckerRule<IInRoutePosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IInRoutePosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IInRoutePosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addInfrastructureActionCheckerRule(ICheckerRule<IInfrastructureAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IInfrastructureAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IInfrastructureAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addInitCheckerRule(ICheckerRule<IInit> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IInit.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IInit.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addInitActionsCheckerRule(ICheckerRule<IInitActions> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IInitActions.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IInitActions.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addKnotCheckerRule(ICheckerRule<IKnot> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IKnot.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IKnot.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLaneChangeActionCheckerRule(ICheckerRule<ILaneChangeAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneChangeAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILaneChangeAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLaneChangeTargetCheckerRule(ICheckerRule<ILaneChangeTarget> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneChangeTarget.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILaneChangeTarget.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLaneOffsetActionCheckerRule(ICheckerRule<ILaneOffsetAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneOffsetAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILaneOffsetAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLaneOffsetActionDynamicsCheckerRule(
      ICheckerRule<ILaneOffsetActionDynamics> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneOffsetActionDynamics.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILaneOffsetActionDynamics.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLaneOffsetTargetCheckerRule(ICheckerRule<ILaneOffsetTarget> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneOffsetTarget.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILaneOffsetTarget.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLanePositionCheckerRule(ICheckerRule<ILanePosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ILanePosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILanePosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLateralActionCheckerRule(ICheckerRule<ILateralAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILateralAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILateralAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLateralDistanceActionCheckerRule(
      ICheckerRule<ILateralDistanceAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILateralDistanceAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILateralDistanceAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLongitudinalActionCheckerRule(ICheckerRule<ILongitudinalAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILongitudinalAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILongitudinalAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addLongitudinalDistanceActionCheckerRule(
      ICheckerRule<ILongitudinalDistanceAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILongitudinalDistanceAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ILongitudinalDistanceAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addManeuverCheckerRule(ICheckerRule<IManeuver> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IManeuver.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IManeuver.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addManeuverCatalogLocationCheckerRule(
      ICheckerRule<IManeuverCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IManeuverCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IManeuverCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addManeuverGroupCheckerRule(ICheckerRule<IManeuverGroup> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IManeuverGroup.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IManeuverGroup.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addMiscObjectCheckerRule(ICheckerRule<IMiscObject> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IMiscObject.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IMiscObject.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addMiscObjectCatalogLocationCheckerRule(
      ICheckerRule<IMiscObjectCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IMiscObjectCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IMiscObjectCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addModifyRuleCheckerRule(ICheckerRule<IModifyRule> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IModifyRule.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IModifyRule.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addNoneCheckerRule(ICheckerRule<INone> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(INone.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(INone.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addNurbsCheckerRule(ICheckerRule<INurbs> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(INurbs.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(INurbs.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addObjectControllerCheckerRule(ICheckerRule<IObjectController> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IObjectController.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IObjectController.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOffroadConditionCheckerRule(ICheckerRule<IOffroadCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOffroadCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOffroadCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOpenScenarioCheckerRule(ICheckerRule<IOpenScenario> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IOpenScenario.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOpenScenario.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOpenScenarioCategoryCheckerRule(ICheckerRule<IOpenScenarioCategory> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOpenScenarioCategory.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOpenScenarioCategory.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOrientationCheckerRule(ICheckerRule<IOrientation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IOrientation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOrientation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOverrideBrakeActionCheckerRule(ICheckerRule<IOverrideBrakeAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideBrakeAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOverrideBrakeAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOverrideClutchActionCheckerRule(ICheckerRule<IOverrideClutchAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideClutchAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOverrideClutchAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOverrideControllerValueActionCheckerRule(
      ICheckerRule<IOverrideControllerValueAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideControllerValueAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOverrideControllerValueAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOverrideGearActionCheckerRule(ICheckerRule<IOverrideGearAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideGearAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOverrideGearAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOverrideParkingBrakeActionCheckerRule(
      ICheckerRule<IOverrideParkingBrakeAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideParkingBrakeAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOverrideParkingBrakeAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOverrideSteeringWheelActionCheckerRule(
      ICheckerRule<IOverrideSteeringWheelAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideSteeringWheelAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOverrideSteeringWheelAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addOverrideThrottleActionCheckerRule(
      ICheckerRule<IOverrideThrottleAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideThrottleAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IOverrideThrottleAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterActionCheckerRule(ICheckerRule<IParameterAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterAddValueRuleCheckerRule(
      ICheckerRule<IParameterAddValueRule> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterAddValueRule.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterAddValueRule.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterAssignmentCheckerRule(ICheckerRule<IParameterAssignment> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterAssignment.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterAssignment.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterConditionCheckerRule(ICheckerRule<IParameterCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterDeclarationCheckerRule(ICheckerRule<IParameterDeclaration> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterDeclaration.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterDeclaration.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterModifyActionCheckerRule(
      ICheckerRule<IParameterModifyAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterModifyAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterModifyAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterMultiplyByValueRuleCheckerRule(
      ICheckerRule<IParameterMultiplyByValueRule> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterMultiplyByValueRule.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterMultiplyByValueRule.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addParameterSetActionCheckerRule(ICheckerRule<IParameterSetAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterSetAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IParameterSetAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPedestrianCheckerRule(ICheckerRule<IPedestrian> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPedestrian.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPedestrian.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPedestrianCatalogLocationCheckerRule(
      ICheckerRule<IPedestrianCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPedestrianCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPedestrianCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPerformanceCheckerRule(ICheckerRule<IPerformance> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPerformance.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPerformance.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPhaseCheckerRule(ICheckerRule<IPhase> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPhase.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPhase.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPolylineCheckerRule(ICheckerRule<IPolyline> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPolyline.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPolyline.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPositionCheckerRule(ICheckerRule<IPosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPositionInLaneCoordinatesCheckerRule(
      ICheckerRule<IPositionInLaneCoordinates> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPositionInLaneCoordinates.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPositionInLaneCoordinates.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPositionInRoadCoordinatesCheckerRule(
      ICheckerRule<IPositionInRoadCoordinates> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPositionInRoadCoordinates.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPositionInRoadCoordinates.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPositionOfCurrentEntityCheckerRule(
      ICheckerRule<IPositionOfCurrentEntity> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPositionOfCurrentEntity.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPositionOfCurrentEntity.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPrecipitationCheckerRule(ICheckerRule<IPrecipitation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPrecipitation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPrecipitation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPrivateCheckerRule(ICheckerRule<IPrivate> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPrivate.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPrivate.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPrivateActionCheckerRule(ICheckerRule<IPrivateAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPrivateAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IPrivateAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPropertiesCheckerRule(ICheckerRule<IProperties> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IProperties.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IProperties.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addPropertyCheckerRule(ICheckerRule<IProperty> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IProperty.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IProperty.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addReachPositionConditionCheckerRule(
      ICheckerRule<IReachPositionCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IReachPositionCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IReachPositionCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeDistanceConditionCheckerRule(
      ICheckerRule<IRelativeDistanceCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeDistanceCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeDistanceCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeLanePositionCheckerRule(ICheckerRule<IRelativeLanePosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeLanePosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeLanePosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeObjectPositionCheckerRule(
      ICheckerRule<IRelativeObjectPosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeObjectPosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeObjectPosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeRoadPositionCheckerRule(ICheckerRule<IRelativeRoadPosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeRoadPosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeRoadPosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeSpeedConditionCheckerRule(
      ICheckerRule<IRelativeSpeedCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeSpeedCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeSpeedCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeSpeedToMasterCheckerRule(
      ICheckerRule<IRelativeSpeedToMaster> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeSpeedToMaster.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeSpeedToMaster.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeTargetLaneCheckerRule(ICheckerRule<IRelativeTargetLane> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeTargetLane.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeTargetLane.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeTargetLaneOffsetCheckerRule(
      ICheckerRule<IRelativeTargetLaneOffset> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeTargetLaneOffset.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeTargetLaneOffset.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeTargetSpeedCheckerRule(ICheckerRule<IRelativeTargetSpeed> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeTargetSpeed.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeTargetSpeed.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRelativeWorldPositionCheckerRule(
      ICheckerRule<IRelativeWorldPosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeWorldPosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRelativeWorldPosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRoadConditionCheckerRule(ICheckerRule<IRoadCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRoadCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRoadCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRoadNetworkCheckerRule(ICheckerRule<IRoadNetwork> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRoadNetwork.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRoadNetwork.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRoadPositionCheckerRule(ICheckerRule<IRoadPosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRoadPosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRoadPosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRouteCheckerRule(ICheckerRule<IRoute> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRoute.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRoute.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRouteCatalogLocationCheckerRule(ICheckerRule<IRouteCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRouteCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRouteCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRoutePositionCheckerRule(ICheckerRule<IRoutePosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRoutePosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRoutePosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRouteRefCheckerRule(ICheckerRule<IRouteRef> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRouteRef.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRouteRef.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addRoutingActionCheckerRule(ICheckerRule<IRoutingAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRoutingAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IRoutingAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addScenarioDefinitionCheckerRule(ICheckerRule<IScenarioDefinition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IScenarioDefinition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IScenarioDefinition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addScenarioObjectCheckerRule(ICheckerRule<IScenarioObject> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IScenarioObject.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IScenarioObject.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addSelectedEntitiesCheckerRule(ICheckerRule<ISelectedEntities> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISelectedEntities.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ISelectedEntities.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addShapeCheckerRule(ICheckerRule<IShape> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IShape.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IShape.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addSimulationTimeConditionCheckerRule(
      ICheckerRule<ISimulationTimeCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISimulationTimeCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ISimulationTimeCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addSpeedActionCheckerRule(ICheckerRule<ISpeedAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ISpeedAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ISpeedAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addSpeedActionTargetCheckerRule(ICheckerRule<ISpeedActionTarget> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISpeedActionTarget.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ISpeedActionTarget.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addSpeedConditionCheckerRule(ICheckerRule<ISpeedCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISpeedCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ISpeedCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addStandStillConditionCheckerRule(ICheckerRule<IStandStillCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IStandStillCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IStandStillCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addStoryCheckerRule(ICheckerRule<IStory> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IStory.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IStory.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addStoryboardCheckerRule(ICheckerRule<IStoryboard> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IStoryboard.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IStoryboard.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addStoryboardElementStateConditionCheckerRule(
      ICheckerRule<IStoryboardElementStateCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IStoryboardElementStateCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IStoryboardElementStateCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addSunCheckerRule(ICheckerRule<ISun> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ISun.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ISun.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addSynchronizeActionCheckerRule(ICheckerRule<ISynchronizeAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISynchronizeAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ISynchronizeAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTeleportActionCheckerRule(ICheckerRule<ITeleportAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITeleportAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITeleportAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTimeHeadwayConditionCheckerRule(ICheckerRule<ITimeHeadwayCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeHeadwayCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITimeHeadwayCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTimeOfDayCheckerRule(ICheckerRule<ITimeOfDay> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITimeOfDay.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITimeOfDay.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTimeOfDayConditionCheckerRule(ICheckerRule<ITimeOfDayCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeOfDayCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITimeOfDayCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTimeReferenceCheckerRule(ICheckerRule<ITimeReference> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeReference.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITimeReference.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTimeToCollisionConditionCheckerRule(
      ICheckerRule<ITimeToCollisionCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeToCollisionCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITimeToCollisionCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTimeToCollisionConditionTargetCheckerRule(
      ICheckerRule<ITimeToCollisionConditionTarget> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeToCollisionConditionTarget.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITimeToCollisionConditionTarget.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTimingCheckerRule(ICheckerRule<ITiming> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITiming.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITiming.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficActionCheckerRule(ICheckerRule<ITrafficAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficDefinitionCheckerRule(ICheckerRule<ITrafficDefinition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficDefinition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficDefinition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSignalActionCheckerRule(ICheckerRule<ITrafficSignalAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSignalAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSignalConditionCheckerRule(
      ICheckerRule<ITrafficSignalCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSignalCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSignalControllerCheckerRule(
      ICheckerRule<ITrafficSignalController> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalController.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSignalController.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSignalControllerActionCheckerRule(
      ICheckerRule<ITrafficSignalControllerAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalControllerAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSignalControllerAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSignalControllerConditionCheckerRule(
      ICheckerRule<ITrafficSignalControllerCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalControllerCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSignalControllerCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSignalStateCheckerRule(ICheckerRule<ITrafficSignalState> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalState.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSignalState.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSignalStateActionCheckerRule(
      ICheckerRule<ITrafficSignalStateAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalStateAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSignalStateAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSinkActionCheckerRule(ICheckerRule<ITrafficSinkAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSinkAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSinkAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSourceActionCheckerRule(ICheckerRule<ITrafficSourceAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSourceAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSourceAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrafficSwarmActionCheckerRule(ICheckerRule<ITrafficSwarmAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSwarmAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrafficSwarmAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrajectoryCheckerRule(ICheckerRule<ITrajectory> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITrajectory.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrajectory.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrajectoryCatalogLocationCheckerRule(
      ICheckerRule<ITrajectoryCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrajectoryCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrajectoryCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTrajectoryFollowingModeCheckerRule(
      ICheckerRule<ITrajectoryFollowingMode> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrajectoryFollowingMode.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrajectoryFollowingMode.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTransitionDynamicsCheckerRule(ICheckerRule<ITransitionDynamics> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITransitionDynamics.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITransitionDynamics.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTraveledDistanceConditionCheckerRule(
      ICheckerRule<ITraveledDistanceCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITraveledDistanceCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITraveledDistanceCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTriggerCheckerRule(ICheckerRule<ITrigger> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITrigger.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITrigger.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addTriggeringEntitiesCheckerRule(ICheckerRule<ITriggeringEntities> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITriggeringEntities.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(ITriggeringEntities.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addUserDefinedActionCheckerRule(ICheckerRule<IUserDefinedAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IUserDefinedAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IUserDefinedAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addUserDefinedValueConditionCheckerRule(
      ICheckerRule<IUserDefinedValueCondition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IUserDefinedValueCondition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IUserDefinedValueCondition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addVehicleCheckerRule(ICheckerRule<IVehicle> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IVehicle.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IVehicle.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addVehicleCatalogLocationCheckerRule(
      ICheckerRule<IVehicleCatalogLocation> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVehicleCatalogLocation.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IVehicleCatalogLocation.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addVehicleCategoryDistributionCheckerRule(
      ICheckerRule<IVehicleCategoryDistribution> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVehicleCategoryDistribution.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IVehicleCategoryDistribution.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addVehicleCategoryDistributionEntryCheckerRule(
      ICheckerRule<IVehicleCategoryDistributionEntry> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVehicleCategoryDistributionEntry.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IVehicleCategoryDistributionEntry.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addVertexCheckerRule(ICheckerRule<IVertex> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IVertex.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IVertex.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addVisibilityActionCheckerRule(ICheckerRule<IVisibilityAction> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVisibilityAction.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IVisibilityAction.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addWaypointCheckerRule(ICheckerRule<IWaypoint> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IWaypoint.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IWaypoint.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addWeatherCheckerRule(ICheckerRule<IWeather> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IWeather.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IWeather.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  @Override
  public void addWorldPositionCheckerRule(ICheckerRule<IWorldPosition> checkerRule) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IWorldPosition.class);
    if (checkerRulesForType == null) {
      checkerRulesForType = new ArrayList<>();
      this.typeToCheckerRuleList.put(IWorldPosition.class, checkerRulesForType);
    }
    checkerRulesForType.add(checkerRule);
  }

  private void applyAbsoluteSpeedCheckerRules(
      IParserMessageLogger messageLogger, IAbsoluteSpeed object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteSpeed.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAbsoluteSpeed> typedCheckerRule = (ICheckerRule<IAbsoluteSpeed>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyAbsoluteTargetLaneCheckerRules(
      IParserMessageLogger messageLogger, IAbsoluteTargetLane object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteTargetLane.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAbsoluteTargetLane> typedCheckerRule =
            (ICheckerRule<IAbsoluteTargetLane>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyAbsoluteTargetLaneOffsetCheckerRules(
      IParserMessageLogger messageLogger, IAbsoluteTargetLaneOffset object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteTargetLaneOffset.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAbsoluteTargetLaneOffset> typedCheckerRule =
            (ICheckerRule<IAbsoluteTargetLaneOffset>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyAbsoluteTargetSpeedCheckerRules(
      IParserMessageLogger messageLogger, IAbsoluteTargetSpeed object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAbsoluteTargetSpeed.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAbsoluteTargetSpeed> typedCheckerRule =
            (ICheckerRule<IAbsoluteTargetSpeed>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyAccelerationConditionCheckerRules(
      IParserMessageLogger messageLogger, IAccelerationCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAccelerationCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAccelerationCondition> typedCheckerRule =
            (ICheckerRule<IAccelerationCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyAcquirePositionActionCheckerRules(
      IParserMessageLogger messageLogger, IAcquirePositionAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAcquirePositionAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAcquirePositionAction> typedCheckerRule =
            (ICheckerRule<IAcquirePositionAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyActCheckerRules(IParserMessageLogger messageLogger, IAct object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAct.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAct> typedCheckerRule = (ICheckerRule<IAct>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IManeuverGroup> maneuverGroups = object.getManeuverGroups();
    if (maneuverGroups != null) {
      for (IManeuverGroup listItem : maneuverGroups) {
        applyManeuverGroupCheckerRules(messageLogger, listItem);
      }
    }
    ITrigger startTrigger = object.getStartTrigger();
    if (startTrigger != null) {
      applyTriggerCheckerRules(messageLogger, startTrigger);
    }
    ITrigger stopTrigger = object.getStopTrigger();
    if (stopTrigger != null) {
      applyTriggerCheckerRules(messageLogger, stopTrigger);
    }
  }

  private void applyActionCheckerRules(IParserMessageLogger messageLogger, IAction object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAction> typedCheckerRule = (ICheckerRule<IAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IGlobalAction globalAction = object.getGlobalAction();
    if (globalAction != null) {
      applyGlobalActionCheckerRules(messageLogger, globalAction);
    }
    IUserDefinedAction userDefinedAction = object.getUserDefinedAction();
    if (userDefinedAction != null) {
      applyUserDefinedActionCheckerRules(messageLogger, userDefinedAction);
    }
    IPrivateAction privateAction = object.getPrivateAction();
    if (privateAction != null) {
      applyPrivateActionCheckerRules(messageLogger, privateAction);
    }
  }

  private void applyActivateControllerActionCheckerRules(
      IParserMessageLogger messageLogger, IActivateControllerAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IActivateControllerAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IActivateControllerAction> typedCheckerRule =
            (ICheckerRule<IActivateControllerAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyActorsCheckerRules(IParserMessageLogger messageLogger, IActors object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IActors.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IActors> typedCheckerRule = (ICheckerRule<IActors>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IEntityRef> entityRefs = object.getEntityRefs();
    if (entityRefs != null) {
      for (IEntityRef listItem : entityRefs) {
        applyEntityRefCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyAddEntityActionCheckerRules(
      IParserMessageLogger messageLogger, IAddEntityAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAddEntityAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAddEntityAction> typedCheckerRule =
            (ICheckerRule<IAddEntityAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyAssignControllerActionCheckerRules(
      IParserMessageLogger messageLogger, IAssignControllerAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAssignControllerAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAssignControllerAction> typedCheckerRule =
            (ICheckerRule<IAssignControllerAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IController controller = object.getController();
    if (controller != null) {
      applyControllerCheckerRules(messageLogger, controller);
    }
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
  }

  private void applyAssignRouteActionCheckerRules(
      IParserMessageLogger messageLogger, IAssignRouteAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IAssignRouteAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAssignRouteAction> typedCheckerRule =
            (ICheckerRule<IAssignRouteAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IRoute route = object.getRoute();
    if (route != null) {
      applyRouteCheckerRules(messageLogger, route);
    }
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
  }

  private void applyAxleCheckerRules(IParserMessageLogger messageLogger, IAxle object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAxle.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAxle> typedCheckerRule = (ICheckerRule<IAxle>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyAxlesCheckerRules(IParserMessageLogger messageLogger, IAxles object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IAxles.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IAxles> typedCheckerRule = (ICheckerRule<IAxles>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IAxle frontAxle = object.getFrontAxle();
    if (frontAxle != null) {
      applyAxleCheckerRules(messageLogger, frontAxle);
    }
    IAxle rearAxle = object.getRearAxle();
    if (rearAxle != null) {
      applyAxleCheckerRules(messageLogger, rearAxle);
    }
    List<IAxle> additionalAxles = object.getAdditionalAxles();
    if (additionalAxles != null) {
      for (IAxle listItem : additionalAxles) {
        applyAxleCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyBoundingBoxCheckerRules(
      IParserMessageLogger messageLogger, IBoundingBox object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IBoundingBox.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IBoundingBox> typedCheckerRule = (ICheckerRule<IBoundingBox>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ICenter center = object.getCenter();
    if (center != null) {
      applyCenterCheckerRules(messageLogger, center);
    }
    IDimensions dimensions = object.getDimensions();
    if (dimensions != null) {
      applyDimensionsCheckerRules(messageLogger, dimensions);
    }
  }

  private void applyByEntityConditionCheckerRules(
      IParserMessageLogger messageLogger, IByEntityCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IByEntityCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IByEntityCondition> typedCheckerRule =
            (ICheckerRule<IByEntityCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITriggeringEntities triggeringEntities = object.getTriggeringEntities();
    if (triggeringEntities != null) {
      applyTriggeringEntitiesCheckerRules(messageLogger, triggeringEntities);
    }
    IEntityCondition entityCondition = object.getEntityCondition();
    if (entityCondition != null) {
      applyEntityConditionCheckerRules(messageLogger, entityCondition);
    }
  }

  private void applyByObjectTypeCheckerRules(
      IParserMessageLogger messageLogger, IByObjectType object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IByObjectType.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IByObjectType> typedCheckerRule = (ICheckerRule<IByObjectType>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyByTypeCheckerRules(IParserMessageLogger messageLogger, IByType object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IByType.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IByType> typedCheckerRule = (ICheckerRule<IByType>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyByValueConditionCheckerRules(
      IParserMessageLogger messageLogger, IByValueCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IByValueCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IByValueCondition> typedCheckerRule =
            (ICheckerRule<IByValueCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IParameterCondition parameterCondition = object.getParameterCondition();
    if (parameterCondition != null) {
      applyParameterConditionCheckerRules(messageLogger, parameterCondition);
    }
    ITimeOfDayCondition timeOfDayCondition = object.getTimeOfDayCondition();
    if (timeOfDayCondition != null) {
      applyTimeOfDayConditionCheckerRules(messageLogger, timeOfDayCondition);
    }
    ISimulationTimeCondition simulationTimeCondition = object.getSimulationTimeCondition();
    if (simulationTimeCondition != null) {
      applySimulationTimeConditionCheckerRules(messageLogger, simulationTimeCondition);
    }
    IStoryboardElementStateCondition storyboardElementStateCondition =
        object.getStoryboardElementStateCondition();
    if (storyboardElementStateCondition != null) {
      applyStoryboardElementStateConditionCheckerRules(
          messageLogger, storyboardElementStateCondition);
    }
    IUserDefinedValueCondition userDefinedValueCondition = object.getUserDefinedValueCondition();
    if (userDefinedValueCondition != null) {
      applyUserDefinedValueConditionCheckerRules(messageLogger, userDefinedValueCondition);
    }
    ITrafficSignalCondition trafficSignalCondition = object.getTrafficSignalCondition();
    if (trafficSignalCondition != null) {
      applyTrafficSignalConditionCheckerRules(messageLogger, trafficSignalCondition);
    }
    ITrafficSignalControllerCondition trafficSignalControllerCondition =
        object.getTrafficSignalControllerCondition();
    if (trafficSignalControllerCondition != null) {
      applyTrafficSignalControllerConditionCheckerRules(
          messageLogger, trafficSignalControllerCondition);
    }
  }

  private void applyCatalogCheckerRules(IParserMessageLogger messageLogger, ICatalog object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ICatalog.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICatalog> typedCheckerRule = (ICheckerRule<ICatalog>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IVehicle> vehicles = object.getVehicles();
    if (vehicles != null) {
      for (IVehicle listItem : vehicles) {
        applyVehicleCheckerRules(messageLogger, listItem);
      }
    }
    List<IController> controllers = object.getControllers();
    if (controllers != null) {
      for (IController listItem : controllers) {
        applyControllerCheckerRules(messageLogger, listItem);
      }
    }
    List<IPedestrian> pedestrians = object.getPedestrians();
    if (pedestrians != null) {
      for (IPedestrian listItem : pedestrians) {
        applyPedestrianCheckerRules(messageLogger, listItem);
      }
    }
    List<IMiscObject> miscObjects = object.getMiscObjects();
    if (miscObjects != null) {
      for (IMiscObject listItem : miscObjects) {
        applyMiscObjectCheckerRules(messageLogger, listItem);
      }
    }
    List<IEnvironment> environments = object.getEnvironments();
    if (environments != null) {
      for (IEnvironment listItem : environments) {
        applyEnvironmentCheckerRules(messageLogger, listItem);
      }
    }
    List<IManeuver> maneuvers = object.getManeuvers();
    if (maneuvers != null) {
      for (IManeuver listItem : maneuvers) {
        applyManeuverCheckerRules(messageLogger, listItem);
      }
    }
    List<ITrajectory> trajectories = object.getTrajectories();
    if (trajectories != null) {
      for (ITrajectory listItem : trajectories) {
        applyTrajectoryCheckerRules(messageLogger, listItem);
      }
    }
    List<IRoute> routes = object.getRoutes();
    if (routes != null) {
      for (IRoute listItem : routes) {
        applyRouteCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyCatalogDefinitionCheckerRules(
      IParserMessageLogger messageLogger, ICatalogDefinition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICatalogDefinition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICatalogDefinition> typedCheckerRule =
            (ICheckerRule<ICatalogDefinition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ICatalog catalog = object.getCatalog();
    if (catalog != null) {
      applyCatalogCheckerRules(messageLogger, catalog);
    }
  }

  private void applyCatalogLocationsCheckerRules(
      IParserMessageLogger messageLogger, ICatalogLocations object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICatalogLocations.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICatalogLocations> typedCheckerRule =
            (ICheckerRule<ICatalogLocations>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IVehicleCatalogLocation vehicleCatalog = object.getVehicleCatalog();
    if (vehicleCatalog != null) {
      applyVehicleCatalogLocationCheckerRules(messageLogger, vehicleCatalog);
    }
    IControllerCatalogLocation controllerCatalog = object.getControllerCatalog();
    if (controllerCatalog != null) {
      applyControllerCatalogLocationCheckerRules(messageLogger, controllerCatalog);
    }
    IPedestrianCatalogLocation pedestrianCatalog = object.getPedestrianCatalog();
    if (pedestrianCatalog != null) {
      applyPedestrianCatalogLocationCheckerRules(messageLogger, pedestrianCatalog);
    }
    IMiscObjectCatalogLocation miscObjectCatalog = object.getMiscObjectCatalog();
    if (miscObjectCatalog != null) {
      applyMiscObjectCatalogLocationCheckerRules(messageLogger, miscObjectCatalog);
    }
    IEnvironmentCatalogLocation environmentCatalog = object.getEnvironmentCatalog();
    if (environmentCatalog != null) {
      applyEnvironmentCatalogLocationCheckerRules(messageLogger, environmentCatalog);
    }
    IManeuverCatalogLocation maneuverCatalog = object.getManeuverCatalog();
    if (maneuverCatalog != null) {
      applyManeuverCatalogLocationCheckerRules(messageLogger, maneuverCatalog);
    }
    ITrajectoryCatalogLocation trajectoryCatalog = object.getTrajectoryCatalog();
    if (trajectoryCatalog != null) {
      applyTrajectoryCatalogLocationCheckerRules(messageLogger, trajectoryCatalog);
    }
    IRouteCatalogLocation routeCatalog = object.getRouteCatalog();
    if (routeCatalog != null) {
      applyRouteCatalogLocationCheckerRules(messageLogger, routeCatalog);
    }
  }

  private void applyCatalogReferenceCheckerRules(
      IParserMessageLogger messageLogger, ICatalogReference object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICatalogReference.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICatalogReference> typedCheckerRule =
            (ICheckerRule<ICatalogReference>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterAssignment> parameterAssignments = object.getParameterAssignments();
    if (parameterAssignments != null) {
      for (IParameterAssignment listItem : parameterAssignments) {
        applyParameterAssignmentCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyCenterCheckerRules(IParserMessageLogger messageLogger, ICenter object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ICenter.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICenter> typedCheckerRule = (ICheckerRule<ICenter>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyCentralSwarmObjectCheckerRules(
      IParserMessageLogger messageLogger, ICentralSwarmObject object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICentralSwarmObject.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICentralSwarmObject> typedCheckerRule =
            (ICheckerRule<ICentralSwarmObject>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyClothoidCheckerRules(IParserMessageLogger messageLogger, IClothoid object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IClothoid.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IClothoid> typedCheckerRule = (ICheckerRule<IClothoid>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyCollisionConditionCheckerRules(
      IParserMessageLogger messageLogger, ICollisionCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICollisionCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICollisionCondition> typedCheckerRule =
            (ICheckerRule<ICollisionCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IEntityRef entityRef = object.getEntityRef();
    if (entityRef != null) {
      applyEntityRefCheckerRules(messageLogger, entityRef);
    }
    IByObjectType byType = object.getByType();
    if (byType != null) {
      applyByObjectTypeCheckerRules(messageLogger, byType);
    }
  }

  private void applyConditionCheckerRules(IParserMessageLogger messageLogger, ICondition object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ICondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICondition> typedCheckerRule = (ICheckerRule<ICondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IByEntityCondition byEntityCondition = object.getByEntityCondition();
    if (byEntityCondition != null) {
      applyByEntityConditionCheckerRules(messageLogger, byEntityCondition);
    }
    IByValueCondition byValueCondition = object.getByValueCondition();
    if (byValueCondition != null) {
      applyByValueConditionCheckerRules(messageLogger, byValueCondition);
    }
  }

  private void applyConditionGroupCheckerRules(
      IParserMessageLogger messageLogger, IConditionGroup object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IConditionGroup.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IConditionGroup> typedCheckerRule =
            (ICheckerRule<IConditionGroup>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<ICondition> conditions = object.getConditions();
    if (conditions != null) {
      for (ICondition listItem : conditions) {
        applyConditionCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyControlPointCheckerRules(
      IParserMessageLogger messageLogger, IControlPoint object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IControlPoint.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IControlPoint> typedCheckerRule = (ICheckerRule<IControlPoint>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyControllerCheckerRules(IParserMessageLogger messageLogger, IController object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IController.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IController> typedCheckerRule = (ICheckerRule<IController>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    IProperties properties = object.getProperties();
    if (properties != null) {
      applyPropertiesCheckerRules(messageLogger, properties);
    }
  }

  private void applyControllerActionCheckerRules(
      IParserMessageLogger messageLogger, IControllerAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IControllerAction> typedCheckerRule =
            (ICheckerRule<IControllerAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IAssignControllerAction assignControllerAction = object.getAssignControllerAction();
    if (assignControllerAction != null) {
      applyAssignControllerActionCheckerRules(messageLogger, assignControllerAction);
    }
    IOverrideControllerValueAction overrideControllerValueAction =
        object.getOverrideControllerValueAction();
    if (overrideControllerValueAction != null) {
      applyOverrideControllerValueActionCheckerRules(messageLogger, overrideControllerValueAction);
    }
  }

  private void applyControllerCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, IControllerCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IControllerCatalogLocation> typedCheckerRule =
            (ICheckerRule<IControllerCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyControllerDistributionCheckerRules(
      IParserMessageLogger messageLogger, IControllerDistribution object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerDistribution.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IControllerDistribution> typedCheckerRule =
            (ICheckerRule<IControllerDistribution>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IControllerDistributionEntry> controllerDistributionEntries =
        object.getControllerDistributionEntries();
    if (controllerDistributionEntries != null) {
      for (IControllerDistributionEntry listItem : controllerDistributionEntries) {
        applyControllerDistributionEntryCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyControllerDistributionEntryCheckerRules(
      IParserMessageLogger messageLogger, IControllerDistributionEntry object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IControllerDistributionEntry.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IControllerDistributionEntry> typedCheckerRule =
            (ICheckerRule<IControllerDistributionEntry>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IController controller = object.getController();
    if (controller != null) {
      applyControllerCheckerRules(messageLogger, controller);
    }
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
  }

  private void applyCustomCommandActionCheckerRules(
      IParserMessageLogger messageLogger, ICustomCommandAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ICustomCommandAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ICustomCommandAction> typedCheckerRule =
            (ICheckerRule<ICustomCommandAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyDeleteEntityActionCheckerRules(
      IParserMessageLogger messageLogger, IDeleteEntityAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IDeleteEntityAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IDeleteEntityAction> typedCheckerRule =
            (ICheckerRule<IDeleteEntityAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyDimensionsCheckerRules(IParserMessageLogger messageLogger, IDimensions object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IDimensions.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IDimensions> typedCheckerRule = (ICheckerRule<IDimensions>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyDirectoryCheckerRules(IParserMessageLogger messageLogger, IDirectory object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IDirectory.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IDirectory> typedCheckerRule = (ICheckerRule<IDirectory>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyDistanceConditionCheckerRules(
      IParserMessageLogger messageLogger, IDistanceCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IDistanceCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IDistanceCondition> typedCheckerRule =
            (ICheckerRule<IDistanceCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyDynamicConstraintsCheckerRules(
      IParserMessageLogger messageLogger, IDynamicConstraints object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IDynamicConstraints.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IDynamicConstraints> typedCheckerRule =
            (ICheckerRule<IDynamicConstraints>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyEndOfRoadConditionCheckerRules(
      IParserMessageLogger messageLogger, IEndOfRoadCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEndOfRoadCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEndOfRoadCondition> typedCheckerRule =
            (ICheckerRule<IEndOfRoadCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyEntitiesCheckerRules(IParserMessageLogger messageLogger, IEntities object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntities.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEntities> typedCheckerRule = (ICheckerRule<IEntities>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IScenarioObject> scenarioObjects = object.getScenarioObjects();
    if (scenarioObjects != null) {
      for (IScenarioObject listItem : scenarioObjects) {
        applyScenarioObjectCheckerRules(messageLogger, listItem);
      }
    }
    List<IEntitySelection> entitySelections = object.getEntitySelections();
    if (entitySelections != null) {
      for (IEntitySelection listItem : entitySelections) {
        applyEntitySelectionCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyEntityActionCheckerRules(
      IParserMessageLogger messageLogger, IEntityAction object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntityAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEntityAction> typedCheckerRule = (ICheckerRule<IEntityAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IAddEntityAction addEntityAction = object.getAddEntityAction();
    if (addEntityAction != null) {
      applyAddEntityActionCheckerRules(messageLogger, addEntityAction);
    }
    IDeleteEntityAction deleteEntityAction = object.getDeleteEntityAction();
    if (deleteEntityAction != null) {
      applyDeleteEntityActionCheckerRules(messageLogger, deleteEntityAction);
    }
  }

  private void applyEntityConditionCheckerRules(
      IParserMessageLogger messageLogger, IEntityCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEntityCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEntityCondition> typedCheckerRule =
            (ICheckerRule<IEntityCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IEndOfRoadCondition endOfRoadCondition = object.getEndOfRoadCondition();
    if (endOfRoadCondition != null) {
      applyEndOfRoadConditionCheckerRules(messageLogger, endOfRoadCondition);
    }
    ICollisionCondition collisionCondition = object.getCollisionCondition();
    if (collisionCondition != null) {
      applyCollisionConditionCheckerRules(messageLogger, collisionCondition);
    }
    IOffroadCondition offroadCondition = object.getOffroadCondition();
    if (offroadCondition != null) {
      applyOffroadConditionCheckerRules(messageLogger, offroadCondition);
    }
    ITimeHeadwayCondition timeHeadwayCondition = object.getTimeHeadwayCondition();
    if (timeHeadwayCondition != null) {
      applyTimeHeadwayConditionCheckerRules(messageLogger, timeHeadwayCondition);
    }
    ITimeToCollisionCondition timeToCollisionCondition = object.getTimeToCollisionCondition();
    if (timeToCollisionCondition != null) {
      applyTimeToCollisionConditionCheckerRules(messageLogger, timeToCollisionCondition);
    }
    IAccelerationCondition accelerationCondition = object.getAccelerationCondition();
    if (accelerationCondition != null) {
      applyAccelerationConditionCheckerRules(messageLogger, accelerationCondition);
    }
    IStandStillCondition standStillCondition = object.getStandStillCondition();
    if (standStillCondition != null) {
      applyStandStillConditionCheckerRules(messageLogger, standStillCondition);
    }
    ISpeedCondition speedCondition = object.getSpeedCondition();
    if (speedCondition != null) {
      applySpeedConditionCheckerRules(messageLogger, speedCondition);
    }
    IRelativeSpeedCondition relativeSpeedCondition = object.getRelativeSpeedCondition();
    if (relativeSpeedCondition != null) {
      applyRelativeSpeedConditionCheckerRules(messageLogger, relativeSpeedCondition);
    }
    ITraveledDistanceCondition traveledDistanceCondition = object.getTraveledDistanceCondition();
    if (traveledDistanceCondition != null) {
      applyTraveledDistanceConditionCheckerRules(messageLogger, traveledDistanceCondition);
    }
    IReachPositionCondition reachPositionCondition = object.getReachPositionCondition();
    if (reachPositionCondition != null) {
      applyReachPositionConditionCheckerRules(messageLogger, reachPositionCondition);
    }
    IDistanceCondition distanceCondition = object.getDistanceCondition();
    if (distanceCondition != null) {
      applyDistanceConditionCheckerRules(messageLogger, distanceCondition);
    }
    IRelativeDistanceCondition relativeDistanceCondition = object.getRelativeDistanceCondition();
    if (relativeDistanceCondition != null) {
      applyRelativeDistanceConditionCheckerRules(messageLogger, relativeDistanceCondition);
    }
  }

  private void applyEntityObjectCheckerRules(
      IParserMessageLogger messageLogger, IEntityObject object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntityObject.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEntityObject> typedCheckerRule = (ICheckerRule<IEntityObject>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
    IVehicle vehicle = object.getVehicle();
    if (vehicle != null) {
      applyVehicleCheckerRules(messageLogger, vehicle);
    }
    IPedestrian pedestrian = object.getPedestrian();
    if (pedestrian != null) {
      applyPedestrianCheckerRules(messageLogger, pedestrian);
    }
    IMiscObject miscObject = object.getMiscObject();
    if (miscObject != null) {
      applyMiscObjectCheckerRules(messageLogger, miscObject);
    }
  }

  private void applyEntityRefCheckerRules(IParserMessageLogger messageLogger, IEntityRef object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEntityRef.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEntityRef> typedCheckerRule = (ICheckerRule<IEntityRef>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyEntitySelectionCheckerRules(
      IParserMessageLogger messageLogger, IEntitySelection object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEntitySelection.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEntitySelection> typedCheckerRule =
            (ICheckerRule<IEntitySelection>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ISelectedEntities members = object.getMembers();
    if (members != null) {
      applySelectedEntitiesCheckerRules(messageLogger, members);
    }
  }

  private void applyEnvironmentCheckerRules(
      IParserMessageLogger messageLogger, IEnvironment object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEnvironment.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEnvironment> typedCheckerRule = (ICheckerRule<IEnvironment>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    ITimeOfDay timeOfDay = object.getTimeOfDay();
    if (timeOfDay != null) {
      applyTimeOfDayCheckerRules(messageLogger, timeOfDay);
    }
    IWeather weather = object.getWeather();
    if (weather != null) {
      applyWeatherCheckerRules(messageLogger, weather);
    }
    IRoadCondition roadCondition = object.getRoadCondition();
    if (roadCondition != null) {
      applyRoadConditionCheckerRules(messageLogger, roadCondition);
    }
  }

  private void applyEnvironmentActionCheckerRules(
      IParserMessageLogger messageLogger, IEnvironmentAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEnvironmentAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEnvironmentAction> typedCheckerRule =
            (ICheckerRule<IEnvironmentAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IEnvironment environment = object.getEnvironment();
    if (environment != null) {
      applyEnvironmentCheckerRules(messageLogger, environment);
    }
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
  }

  private void applyEnvironmentCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, IEnvironmentCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IEnvironmentCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEnvironmentCatalogLocation> typedCheckerRule =
            (ICheckerRule<IEnvironmentCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyEventCheckerRules(IParserMessageLogger messageLogger, IEvent object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IEvent.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IEvent> typedCheckerRule = (ICheckerRule<IEvent>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IAction> actions = object.getActions();
    if (actions != null) {
      for (IAction listItem : actions) {
        applyActionCheckerRules(messageLogger, listItem);
      }
    }
    ITrigger startTrigger = object.getStartTrigger();
    if (startTrigger != null) {
      applyTriggerCheckerRules(messageLogger, startTrigger);
    }
  }

  private void applyFileCheckerRules(IParserMessageLogger messageLogger, IFile object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFile.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IFile> typedCheckerRule = (ICheckerRule<IFile>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyFileHeaderCheckerRules(IParserMessageLogger messageLogger, IFileHeader object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFileHeader.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IFileHeader> typedCheckerRule = (ICheckerRule<IFileHeader>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyFinalSpeedCheckerRules(IParserMessageLogger messageLogger, IFinalSpeed object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFinalSpeed.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IFinalSpeed> typedCheckerRule = (ICheckerRule<IFinalSpeed>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IAbsoluteSpeed absoluteSpeed = object.getAbsoluteSpeed();
    if (absoluteSpeed != null) {
      applyAbsoluteSpeedCheckerRules(messageLogger, absoluteSpeed);
    }
    IRelativeSpeedToMaster relativeSpeedToMaster = object.getRelativeSpeedToMaster();
    if (relativeSpeedToMaster != null) {
      applyRelativeSpeedToMasterCheckerRules(messageLogger, relativeSpeedToMaster);
    }
  }

  private void applyFogCheckerRules(IParserMessageLogger messageLogger, IFog object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IFog.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IFog> typedCheckerRule = (ICheckerRule<IFog>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IBoundingBox boundingBox = object.getBoundingBox();
    if (boundingBox != null) {
      applyBoundingBoxCheckerRules(messageLogger, boundingBox);
    }
  }

  private void applyFollowTrajectoryActionCheckerRules(
      IParserMessageLogger messageLogger, IFollowTrajectoryAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IFollowTrajectoryAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IFollowTrajectoryAction> typedCheckerRule =
            (ICheckerRule<IFollowTrajectoryAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITrajectory trajectory = object.getTrajectory();
    if (trajectory != null) {
      applyTrajectoryCheckerRules(messageLogger, trajectory);
    }
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
    ITimeReference timeReference = object.getTimeReference();
    if (timeReference != null) {
      applyTimeReferenceCheckerRules(messageLogger, timeReference);
    }
    ITrajectoryFollowingMode trajectoryFollowingMode = object.getTrajectoryFollowingMode();
    if (trajectoryFollowingMode != null) {
      applyTrajectoryFollowingModeCheckerRules(messageLogger, trajectoryFollowingMode);
    }
  }

  private void applyGlobalActionCheckerRules(
      IParserMessageLogger messageLogger, IGlobalAction object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IGlobalAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IGlobalAction> typedCheckerRule = (ICheckerRule<IGlobalAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IEnvironmentAction environmentAction = object.getEnvironmentAction();
    if (environmentAction != null) {
      applyEnvironmentActionCheckerRules(messageLogger, environmentAction);
    }
    IEntityAction entityAction = object.getEntityAction();
    if (entityAction != null) {
      applyEntityActionCheckerRules(messageLogger, entityAction);
    }
    IParameterAction parameterAction = object.getParameterAction();
    if (parameterAction != null) {
      applyParameterActionCheckerRules(messageLogger, parameterAction);
    }
    IInfrastructureAction infrastructureAction = object.getInfrastructureAction();
    if (infrastructureAction != null) {
      applyInfrastructureActionCheckerRules(messageLogger, infrastructureAction);
    }
    ITrafficAction trafficAction = object.getTrafficAction();
    if (trafficAction != null) {
      applyTrafficActionCheckerRules(messageLogger, trafficAction);
    }
  }

  private void applyInRoutePositionCheckerRules(
      IParserMessageLogger messageLogger, IInRoutePosition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IInRoutePosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IInRoutePosition> typedCheckerRule =
            (ICheckerRule<IInRoutePosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPositionOfCurrentEntity fromCurrentEntity = object.getFromCurrentEntity();
    if (fromCurrentEntity != null) {
      applyPositionOfCurrentEntityCheckerRules(messageLogger, fromCurrentEntity);
    }
    IPositionInRoadCoordinates fromRoadCoordinates = object.getFromRoadCoordinates();
    if (fromRoadCoordinates != null) {
      applyPositionInRoadCoordinatesCheckerRules(messageLogger, fromRoadCoordinates);
    }
    IPositionInLaneCoordinates fromLaneCoordinates = object.getFromLaneCoordinates();
    if (fromLaneCoordinates != null) {
      applyPositionInLaneCoordinatesCheckerRules(messageLogger, fromLaneCoordinates);
    }
  }

  private void applyInfrastructureActionCheckerRules(
      IParserMessageLogger messageLogger, IInfrastructureAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IInfrastructureAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IInfrastructureAction> typedCheckerRule =
            (ICheckerRule<IInfrastructureAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITrafficSignalAction trafficSignalAction = object.getTrafficSignalAction();
    if (trafficSignalAction != null) {
      applyTrafficSignalActionCheckerRules(messageLogger, trafficSignalAction);
    }
  }

  private void applyInitCheckerRules(IParserMessageLogger messageLogger, IInit object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IInit.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IInit> typedCheckerRule = (ICheckerRule<IInit>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IInitActions actions = object.getActions();
    if (actions != null) {
      applyInitActionsCheckerRules(messageLogger, actions);
    }
  }

  private void applyInitActionsCheckerRules(
      IParserMessageLogger messageLogger, IInitActions object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IInitActions.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IInitActions> typedCheckerRule = (ICheckerRule<IInitActions>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IGlobalAction> globalActions = object.getGlobalActions();
    if (globalActions != null) {
      for (IGlobalAction listItem : globalActions) {
        applyGlobalActionCheckerRules(messageLogger, listItem);
      }
    }
    List<IUserDefinedAction> userDefinedActions = object.getUserDefinedActions();
    if (userDefinedActions != null) {
      for (IUserDefinedAction listItem : userDefinedActions) {
        applyUserDefinedActionCheckerRules(messageLogger, listItem);
      }
    }
    List<IPrivate> privates = object.getPrivates();
    if (privates != null) {
      for (IPrivate listItem : privates) {
        applyPrivateCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyKnotCheckerRules(IParserMessageLogger messageLogger, IKnot object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IKnot.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IKnot> typedCheckerRule = (ICheckerRule<IKnot>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyLaneChangeActionCheckerRules(
      IParserMessageLogger messageLogger, ILaneChangeAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneChangeAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILaneChangeAction> typedCheckerRule =
            (ICheckerRule<ILaneChangeAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITransitionDynamics laneChangeActionDynamics = object.getLaneChangeActionDynamics();
    if (laneChangeActionDynamics != null) {
      applyTransitionDynamicsCheckerRules(messageLogger, laneChangeActionDynamics);
    }
    ILaneChangeTarget laneChangeTarget = object.getLaneChangeTarget();
    if (laneChangeTarget != null) {
      applyLaneChangeTargetCheckerRules(messageLogger, laneChangeTarget);
    }
  }

  private void applyLaneChangeTargetCheckerRules(
      IParserMessageLogger messageLogger, ILaneChangeTarget object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneChangeTarget.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILaneChangeTarget> typedCheckerRule =
            (ICheckerRule<ILaneChangeTarget>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IRelativeTargetLane relativeTargetLane = object.getRelativeTargetLane();
    if (relativeTargetLane != null) {
      applyRelativeTargetLaneCheckerRules(messageLogger, relativeTargetLane);
    }
    IAbsoluteTargetLane absoluteTargetLane = object.getAbsoluteTargetLane();
    if (absoluteTargetLane != null) {
      applyAbsoluteTargetLaneCheckerRules(messageLogger, absoluteTargetLane);
    }
  }

  private void applyLaneOffsetActionCheckerRules(
      IParserMessageLogger messageLogger, ILaneOffsetAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneOffsetAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILaneOffsetAction> typedCheckerRule =
            (ICheckerRule<ILaneOffsetAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ILaneOffsetActionDynamics laneOffsetActionDynamics = object.getLaneOffsetActionDynamics();
    if (laneOffsetActionDynamics != null) {
      applyLaneOffsetActionDynamicsCheckerRules(messageLogger, laneOffsetActionDynamics);
    }
    ILaneOffsetTarget laneOffsetTarget = object.getLaneOffsetTarget();
    if (laneOffsetTarget != null) {
      applyLaneOffsetTargetCheckerRules(messageLogger, laneOffsetTarget);
    }
  }

  private void applyLaneOffsetActionDynamicsCheckerRules(
      IParserMessageLogger messageLogger, ILaneOffsetActionDynamics object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneOffsetActionDynamics.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILaneOffsetActionDynamics> typedCheckerRule =
            (ICheckerRule<ILaneOffsetActionDynamics>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyLaneOffsetTargetCheckerRules(
      IParserMessageLogger messageLogger, ILaneOffsetTarget object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILaneOffsetTarget.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILaneOffsetTarget> typedCheckerRule =
            (ICheckerRule<ILaneOffsetTarget>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IRelativeTargetLaneOffset relativeTargetLaneOffset = object.getRelativeTargetLaneOffset();
    if (relativeTargetLaneOffset != null) {
      applyRelativeTargetLaneOffsetCheckerRules(messageLogger, relativeTargetLaneOffset);
    }
    IAbsoluteTargetLaneOffset absoluteTargetLaneOffset = object.getAbsoluteTargetLaneOffset();
    if (absoluteTargetLaneOffset != null) {
      applyAbsoluteTargetLaneOffsetCheckerRules(messageLogger, absoluteTargetLaneOffset);
    }
  }

  private void applyLanePositionCheckerRules(
      IParserMessageLogger messageLogger, ILanePosition object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ILanePosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILanePosition> typedCheckerRule = (ICheckerRule<ILanePosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IOrientation orientation = object.getOrientation();
    if (orientation != null) {
      applyOrientationCheckerRules(messageLogger, orientation);
    }
  }

  private void applyLateralActionCheckerRules(
      IParserMessageLogger messageLogger, ILateralAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILateralAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILateralAction> typedCheckerRule = (ICheckerRule<ILateralAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ILaneChangeAction laneChangeAction = object.getLaneChangeAction();
    if (laneChangeAction != null) {
      applyLaneChangeActionCheckerRules(messageLogger, laneChangeAction);
    }
    ILaneOffsetAction laneOffsetAction = object.getLaneOffsetAction();
    if (laneOffsetAction != null) {
      applyLaneOffsetActionCheckerRules(messageLogger, laneOffsetAction);
    }
    ILateralDistanceAction lateralDistanceAction = object.getLateralDistanceAction();
    if (lateralDistanceAction != null) {
      applyLateralDistanceActionCheckerRules(messageLogger, lateralDistanceAction);
    }
  }

  private void applyLateralDistanceActionCheckerRules(
      IParserMessageLogger messageLogger, ILateralDistanceAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILateralDistanceAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILateralDistanceAction> typedCheckerRule =
            (ICheckerRule<ILateralDistanceAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDynamicConstraints dynamicConstraints = object.getDynamicConstraints();
    if (dynamicConstraints != null) {
      applyDynamicConstraintsCheckerRules(messageLogger, dynamicConstraints);
    }
  }

  private void applyLongitudinalActionCheckerRules(
      IParserMessageLogger messageLogger, ILongitudinalAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILongitudinalAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILongitudinalAction> typedCheckerRule =
            (ICheckerRule<ILongitudinalAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ISpeedAction speedAction = object.getSpeedAction();
    if (speedAction != null) {
      applySpeedActionCheckerRules(messageLogger, speedAction);
    }
    ILongitudinalDistanceAction longitudinalDistanceAction = object.getLongitudinalDistanceAction();
    if (longitudinalDistanceAction != null) {
      applyLongitudinalDistanceActionCheckerRules(messageLogger, longitudinalDistanceAction);
    }
  }

  private void applyLongitudinalDistanceActionCheckerRules(
      IParserMessageLogger messageLogger, ILongitudinalDistanceAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ILongitudinalDistanceAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ILongitudinalDistanceAction> typedCheckerRule =
            (ICheckerRule<ILongitudinalDistanceAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDynamicConstraints dynamicConstraints = object.getDynamicConstraints();
    if (dynamicConstraints != null) {
      applyDynamicConstraintsCheckerRules(messageLogger, dynamicConstraints);
    }
  }

  private void applyManeuverCheckerRules(IParserMessageLogger messageLogger, IManeuver object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IManeuver.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IManeuver> typedCheckerRule = (ICheckerRule<IManeuver>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    List<IEvent> events = object.getEvents();
    if (events != null) {
      for (IEvent listItem : events) {
        applyEventCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyManeuverCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, IManeuverCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IManeuverCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IManeuverCatalogLocation> typedCheckerRule =
            (ICheckerRule<IManeuverCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyManeuverGroupCheckerRules(
      IParserMessageLogger messageLogger, IManeuverGroup object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IManeuverGroup.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IManeuverGroup> typedCheckerRule = (ICheckerRule<IManeuverGroup>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IActors actors = object.getActors();
    if (actors != null) {
      applyActorsCheckerRules(messageLogger, actors);
    }
    List<ICatalogReference> catalogReferences = object.getCatalogReferences();
    if (catalogReferences != null) {
      for (ICatalogReference listItem : catalogReferences) {
        applyCatalogReferenceCheckerRules(messageLogger, listItem);
      }
    }
    List<IManeuver> maneuvers = object.getManeuvers();
    if (maneuvers != null) {
      for (IManeuver listItem : maneuvers) {
        applyManeuverCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyMiscObjectCheckerRules(IParserMessageLogger messageLogger, IMiscObject object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IMiscObject.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IMiscObject> typedCheckerRule = (ICheckerRule<IMiscObject>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    IBoundingBox boundingBox = object.getBoundingBox();
    if (boundingBox != null) {
      applyBoundingBoxCheckerRules(messageLogger, boundingBox);
    }
    IProperties properties = object.getProperties();
    if (properties != null) {
      applyPropertiesCheckerRules(messageLogger, properties);
    }
  }

  private void applyMiscObjectCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, IMiscObjectCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IMiscObjectCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IMiscObjectCatalogLocation> typedCheckerRule =
            (ICheckerRule<IMiscObjectCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyModifyRuleCheckerRules(IParserMessageLogger messageLogger, IModifyRule object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IModifyRule.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IModifyRule> typedCheckerRule = (ICheckerRule<IModifyRule>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IParameterAddValueRule addValue = object.getAddValue();
    if (addValue != null) {
      applyParameterAddValueRuleCheckerRules(messageLogger, addValue);
    }
    IParameterMultiplyByValueRule multiplyByValue = object.getMultiplyByValue();
    if (multiplyByValue != null) {
      applyParameterMultiplyByValueRuleCheckerRules(messageLogger, multiplyByValue);
    }
  }

  private void applyNoneCheckerRules(IParserMessageLogger messageLogger, INone object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(INone.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<INone> typedCheckerRule = (ICheckerRule<INone>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyNurbsCheckerRules(IParserMessageLogger messageLogger, INurbs object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(INurbs.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<INurbs> typedCheckerRule = (ICheckerRule<INurbs>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IControlPoint> controlPoints = object.getControlPoints();
    if (controlPoints != null) {
      for (IControlPoint listItem : controlPoints) {
        applyControlPointCheckerRules(messageLogger, listItem);
      }
    }
    List<IKnot> knots = object.getKnots();
    if (knots != null) {
      for (IKnot listItem : knots) {
        applyKnotCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyObjectControllerCheckerRules(
      IParserMessageLogger messageLogger, IObjectController object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IObjectController.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IObjectController> typedCheckerRule =
            (ICheckerRule<IObjectController>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
    IController controller = object.getController();
    if (controller != null) {
      applyControllerCheckerRules(messageLogger, controller);
    }
  }

  private void applyOffroadConditionCheckerRules(
      IParserMessageLogger messageLogger, IOffroadCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOffroadCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOffroadCondition> typedCheckerRule =
            (ICheckerRule<IOffroadCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyOpenScenarioCheckerRules(
      IParserMessageLogger messageLogger, IOpenScenario object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IOpenScenario.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOpenScenario> typedCheckerRule = (ICheckerRule<IOpenScenario>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IFileHeader fileHeader = object.getFileHeader();
    if (fileHeader != null) {
      applyFileHeaderCheckerRules(messageLogger, fileHeader);
    }
    IOpenScenarioCategory openScenarioCategory = object.getOpenScenarioCategory();
    if (openScenarioCategory != null) {
      applyOpenScenarioCategoryCheckerRules(messageLogger, openScenarioCategory);
    }
  }

  private void applyOpenScenarioCategoryCheckerRules(
      IParserMessageLogger messageLogger, IOpenScenarioCategory object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOpenScenarioCategory.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOpenScenarioCategory> typedCheckerRule =
            (ICheckerRule<IOpenScenarioCategory>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IScenarioDefinition scenarioDefinition = object.getScenarioDefinition();
    if (scenarioDefinition != null) {
      applyScenarioDefinitionCheckerRules(messageLogger, scenarioDefinition);
    }
    ICatalogDefinition catalogDefinition = object.getCatalogDefinition();
    if (catalogDefinition != null) {
      applyCatalogDefinitionCheckerRules(messageLogger, catalogDefinition);
    }
  }

  private void applyOrientationCheckerRules(
      IParserMessageLogger messageLogger, IOrientation object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IOrientation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOrientation> typedCheckerRule = (ICheckerRule<IOrientation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyOverrideBrakeActionCheckerRules(
      IParserMessageLogger messageLogger, IOverrideBrakeAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideBrakeAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOverrideBrakeAction> typedCheckerRule =
            (ICheckerRule<IOverrideBrakeAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyOverrideClutchActionCheckerRules(
      IParserMessageLogger messageLogger, IOverrideClutchAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideClutchAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOverrideClutchAction> typedCheckerRule =
            (ICheckerRule<IOverrideClutchAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyOverrideControllerValueActionCheckerRules(
      IParserMessageLogger messageLogger, IOverrideControllerValueAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideControllerValueAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOverrideControllerValueAction> typedCheckerRule =
            (ICheckerRule<IOverrideControllerValueAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IOverrideThrottleAction throttle = object.getThrottle();
    if (throttle != null) {
      applyOverrideThrottleActionCheckerRules(messageLogger, throttle);
    }
    IOverrideBrakeAction brake = object.getBrake();
    if (brake != null) {
      applyOverrideBrakeActionCheckerRules(messageLogger, brake);
    }
    IOverrideClutchAction clutch = object.getClutch();
    if (clutch != null) {
      applyOverrideClutchActionCheckerRules(messageLogger, clutch);
    }
    IOverrideParkingBrakeAction parkingBrake = object.getParkingBrake();
    if (parkingBrake != null) {
      applyOverrideParkingBrakeActionCheckerRules(messageLogger, parkingBrake);
    }
    IOverrideSteeringWheelAction steeringWheel = object.getSteeringWheel();
    if (steeringWheel != null) {
      applyOverrideSteeringWheelActionCheckerRules(messageLogger, steeringWheel);
    }
    IOverrideGearAction gear = object.getGear();
    if (gear != null) {
      applyOverrideGearActionCheckerRules(messageLogger, gear);
    }
  }

  private void applyOverrideGearActionCheckerRules(
      IParserMessageLogger messageLogger, IOverrideGearAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideGearAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOverrideGearAction> typedCheckerRule =
            (ICheckerRule<IOverrideGearAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyOverrideParkingBrakeActionCheckerRules(
      IParserMessageLogger messageLogger, IOverrideParkingBrakeAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideParkingBrakeAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOverrideParkingBrakeAction> typedCheckerRule =
            (ICheckerRule<IOverrideParkingBrakeAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyOverrideSteeringWheelActionCheckerRules(
      IParserMessageLogger messageLogger, IOverrideSteeringWheelAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideSteeringWheelAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOverrideSteeringWheelAction> typedCheckerRule =
            (ICheckerRule<IOverrideSteeringWheelAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyOverrideThrottleActionCheckerRules(
      IParserMessageLogger messageLogger, IOverrideThrottleAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IOverrideThrottleAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IOverrideThrottleAction> typedCheckerRule =
            (ICheckerRule<IOverrideThrottleAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyParameterActionCheckerRules(
      IParserMessageLogger messageLogger, IParameterAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterAction> typedCheckerRule =
            (ICheckerRule<IParameterAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IParameterSetAction setAction = object.getSetAction();
    if (setAction != null) {
      applyParameterSetActionCheckerRules(messageLogger, setAction);
    }
    IParameterModifyAction modifyAction = object.getModifyAction();
    if (modifyAction != null) {
      applyParameterModifyActionCheckerRules(messageLogger, modifyAction);
    }
  }

  private void applyParameterAddValueRuleCheckerRules(
      IParserMessageLogger messageLogger, IParameterAddValueRule object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterAddValueRule.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterAddValueRule> typedCheckerRule =
            (ICheckerRule<IParameterAddValueRule>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyParameterAssignmentCheckerRules(
      IParserMessageLogger messageLogger, IParameterAssignment object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterAssignment.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterAssignment> typedCheckerRule =
            (ICheckerRule<IParameterAssignment>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyParameterConditionCheckerRules(
      IParserMessageLogger messageLogger, IParameterCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterCondition> typedCheckerRule =
            (ICheckerRule<IParameterCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyParameterDeclarationCheckerRules(
      IParserMessageLogger messageLogger, IParameterDeclaration object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterDeclaration.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterDeclaration> typedCheckerRule =
            (ICheckerRule<IParameterDeclaration>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyParameterModifyActionCheckerRules(
      IParserMessageLogger messageLogger, IParameterModifyAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterModifyAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterModifyAction> typedCheckerRule =
            (ICheckerRule<IParameterModifyAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IModifyRule rule = object.getRule();
    if (rule != null) {
      applyModifyRuleCheckerRules(messageLogger, rule);
    }
  }

  private void applyParameterMultiplyByValueRuleCheckerRules(
      IParserMessageLogger messageLogger, IParameterMultiplyByValueRule object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterMultiplyByValueRule.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterMultiplyByValueRule> typedCheckerRule =
            (ICheckerRule<IParameterMultiplyByValueRule>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyParameterSetActionCheckerRules(
      IParserMessageLogger messageLogger, IParameterSetAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IParameterSetAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IParameterSetAction> typedCheckerRule =
            (ICheckerRule<IParameterSetAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyPedestrianCheckerRules(IParserMessageLogger messageLogger, IPedestrian object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPedestrian.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPedestrian> typedCheckerRule = (ICheckerRule<IPedestrian>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    IBoundingBox boundingBox = object.getBoundingBox();
    if (boundingBox != null) {
      applyBoundingBoxCheckerRules(messageLogger, boundingBox);
    }
    IProperties properties = object.getProperties();
    if (properties != null) {
      applyPropertiesCheckerRules(messageLogger, properties);
    }
  }

  private void applyPedestrianCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, IPedestrianCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPedestrianCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPedestrianCatalogLocation> typedCheckerRule =
            (ICheckerRule<IPedestrianCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyPerformanceCheckerRules(
      IParserMessageLogger messageLogger, IPerformance object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPerformance.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPerformance> typedCheckerRule = (ICheckerRule<IPerformance>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyPhaseCheckerRules(IParserMessageLogger messageLogger, IPhase object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPhase.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPhase> typedCheckerRule = (ICheckerRule<IPhase>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<ITrafficSignalState> trafficSignalStates = object.getTrafficSignalStates();
    if (trafficSignalStates != null) {
      for (ITrafficSignalState listItem : trafficSignalStates) {
        applyTrafficSignalStateCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyPolylineCheckerRules(IParserMessageLogger messageLogger, IPolyline object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPolyline.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPolyline> typedCheckerRule = (ICheckerRule<IPolyline>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IVertex> vertices = object.getVertices();
    if (vertices != null) {
      for (IVertex listItem : vertices) {
        applyVertexCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyPositionCheckerRules(IParserMessageLogger messageLogger, IPosition object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPosition> typedCheckerRule = (ICheckerRule<IPosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IWorldPosition worldPosition = object.getWorldPosition();
    if (worldPosition != null) {
      applyWorldPositionCheckerRules(messageLogger, worldPosition);
    }
    IRelativeWorldPosition relativeWorldPosition = object.getRelativeWorldPosition();
    if (relativeWorldPosition != null) {
      applyRelativeWorldPositionCheckerRules(messageLogger, relativeWorldPosition);
    }
    IRelativeObjectPosition relativeObjectPosition = object.getRelativeObjectPosition();
    if (relativeObjectPosition != null) {
      applyRelativeObjectPositionCheckerRules(messageLogger, relativeObjectPosition);
    }
    IRoadPosition roadPosition = object.getRoadPosition();
    if (roadPosition != null) {
      applyRoadPositionCheckerRules(messageLogger, roadPosition);
    }
    IRelativeRoadPosition relativeRoadPosition = object.getRelativeRoadPosition();
    if (relativeRoadPosition != null) {
      applyRelativeRoadPositionCheckerRules(messageLogger, relativeRoadPosition);
    }
    ILanePosition lanePosition = object.getLanePosition();
    if (lanePosition != null) {
      applyLanePositionCheckerRules(messageLogger, lanePosition);
    }
    IRelativeLanePosition relativeLanePosition = object.getRelativeLanePosition();
    if (relativeLanePosition != null) {
      applyRelativeLanePositionCheckerRules(messageLogger, relativeLanePosition);
    }
    IRoutePosition routePosition = object.getRoutePosition();
    if (routePosition != null) {
      applyRoutePositionCheckerRules(messageLogger, routePosition);
    }
  }

  private void applyPositionInLaneCoordinatesCheckerRules(
      IParserMessageLogger messageLogger, IPositionInLaneCoordinates object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPositionInLaneCoordinates.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPositionInLaneCoordinates> typedCheckerRule =
            (ICheckerRule<IPositionInLaneCoordinates>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyPositionInRoadCoordinatesCheckerRules(
      IParserMessageLogger messageLogger, IPositionInRoadCoordinates object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPositionInRoadCoordinates.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPositionInRoadCoordinates> typedCheckerRule =
            (ICheckerRule<IPositionInRoadCoordinates>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyPositionOfCurrentEntityCheckerRules(
      IParserMessageLogger messageLogger, IPositionOfCurrentEntity object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPositionOfCurrentEntity.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPositionOfCurrentEntity> typedCheckerRule =
            (ICheckerRule<IPositionOfCurrentEntity>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyPrecipitationCheckerRules(
      IParserMessageLogger messageLogger, IPrecipitation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPrecipitation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPrecipitation> typedCheckerRule = (ICheckerRule<IPrecipitation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyPrivateCheckerRules(IParserMessageLogger messageLogger, IPrivate object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IPrivate.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPrivate> typedCheckerRule = (ICheckerRule<IPrivate>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IPrivateAction> privateActions = object.getPrivateActions();
    if (privateActions != null) {
      for (IPrivateAction listItem : privateActions) {
        applyPrivateActionCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyPrivateActionCheckerRules(
      IParserMessageLogger messageLogger, IPrivateAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IPrivateAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IPrivateAction> typedCheckerRule = (ICheckerRule<IPrivateAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ILongitudinalAction longitudinalAction = object.getLongitudinalAction();
    if (longitudinalAction != null) {
      applyLongitudinalActionCheckerRules(messageLogger, longitudinalAction);
    }
    ILateralAction lateralAction = object.getLateralAction();
    if (lateralAction != null) {
      applyLateralActionCheckerRules(messageLogger, lateralAction);
    }
    IVisibilityAction visibilityAction = object.getVisibilityAction();
    if (visibilityAction != null) {
      applyVisibilityActionCheckerRules(messageLogger, visibilityAction);
    }
    ISynchronizeAction synchronizeAction = object.getSynchronizeAction();
    if (synchronizeAction != null) {
      applySynchronizeActionCheckerRules(messageLogger, synchronizeAction);
    }
    IActivateControllerAction activateControllerAction = object.getActivateControllerAction();
    if (activateControllerAction != null) {
      applyActivateControllerActionCheckerRules(messageLogger, activateControllerAction);
    }
    IControllerAction controllerAction = object.getControllerAction();
    if (controllerAction != null) {
      applyControllerActionCheckerRules(messageLogger, controllerAction);
    }
    ITeleportAction teleportAction = object.getTeleportAction();
    if (teleportAction != null) {
      applyTeleportActionCheckerRules(messageLogger, teleportAction);
    }
    IRoutingAction routingAction = object.getRoutingAction();
    if (routingAction != null) {
      applyRoutingActionCheckerRules(messageLogger, routingAction);
    }
  }

  private void applyPropertiesCheckerRules(IParserMessageLogger messageLogger, IProperties object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IProperties.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IProperties> typedCheckerRule = (ICheckerRule<IProperties>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IProperty> properties = object.getProperties();
    if (properties != null) {
      for (IProperty listItem : properties) {
        applyPropertyCheckerRules(messageLogger, listItem);
      }
    }
    List<IFile> files = object.getFiles();
    if (files != null) {
      for (IFile listItem : files) {
        applyFileCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyPropertyCheckerRules(IParserMessageLogger messageLogger, IProperty object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IProperty.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IProperty> typedCheckerRule = (ICheckerRule<IProperty>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyReachPositionConditionCheckerRules(
      IParserMessageLogger messageLogger, IReachPositionCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IReachPositionCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IReachPositionCondition> typedCheckerRule =
            (ICheckerRule<IReachPositionCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyRelativeDistanceConditionCheckerRules(
      IParserMessageLogger messageLogger, IRelativeDistanceCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeDistanceCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeDistanceCondition> typedCheckerRule =
            (ICheckerRule<IRelativeDistanceCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyRelativeLanePositionCheckerRules(
      IParserMessageLogger messageLogger, IRelativeLanePosition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeLanePosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeLanePosition> typedCheckerRule =
            (ICheckerRule<IRelativeLanePosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IOrientation orientation = object.getOrientation();
    if (orientation != null) {
      applyOrientationCheckerRules(messageLogger, orientation);
    }
  }

  private void applyRelativeObjectPositionCheckerRules(
      IParserMessageLogger messageLogger, IRelativeObjectPosition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeObjectPosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeObjectPosition> typedCheckerRule =
            (ICheckerRule<IRelativeObjectPosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IOrientation orientation = object.getOrientation();
    if (orientation != null) {
      applyOrientationCheckerRules(messageLogger, orientation);
    }
  }

  private void applyRelativeRoadPositionCheckerRules(
      IParserMessageLogger messageLogger, IRelativeRoadPosition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeRoadPosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeRoadPosition> typedCheckerRule =
            (ICheckerRule<IRelativeRoadPosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IOrientation orientation = object.getOrientation();
    if (orientation != null) {
      applyOrientationCheckerRules(messageLogger, orientation);
    }
  }

  private void applyRelativeSpeedConditionCheckerRules(
      IParserMessageLogger messageLogger, IRelativeSpeedCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeSpeedCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeSpeedCondition> typedCheckerRule =
            (ICheckerRule<IRelativeSpeedCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyRelativeSpeedToMasterCheckerRules(
      IParserMessageLogger messageLogger, IRelativeSpeedToMaster object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeSpeedToMaster.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeSpeedToMaster> typedCheckerRule =
            (ICheckerRule<IRelativeSpeedToMaster>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyRelativeTargetLaneCheckerRules(
      IParserMessageLogger messageLogger, IRelativeTargetLane object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeTargetLane.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeTargetLane> typedCheckerRule =
            (ICheckerRule<IRelativeTargetLane>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyRelativeTargetLaneOffsetCheckerRules(
      IParserMessageLogger messageLogger, IRelativeTargetLaneOffset object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeTargetLaneOffset.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeTargetLaneOffset> typedCheckerRule =
            (ICheckerRule<IRelativeTargetLaneOffset>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyRelativeTargetSpeedCheckerRules(
      IParserMessageLogger messageLogger, IRelativeTargetSpeed object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeTargetSpeed.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeTargetSpeed> typedCheckerRule =
            (ICheckerRule<IRelativeTargetSpeed>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyRelativeWorldPositionCheckerRules(
      IParserMessageLogger messageLogger, IRelativeWorldPosition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRelativeWorldPosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRelativeWorldPosition> typedCheckerRule =
            (ICheckerRule<IRelativeWorldPosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IOrientation orientation = object.getOrientation();
    if (orientation != null) {
      applyOrientationCheckerRules(messageLogger, orientation);
    }
  }

  private void applyRoadConditionCheckerRules(
      IParserMessageLogger messageLogger, IRoadCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRoadCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRoadCondition> typedCheckerRule = (ICheckerRule<IRoadCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IProperties properties = object.getProperties();
    if (properties != null) {
      applyPropertiesCheckerRules(messageLogger, properties);
    }
  }

  private void applyRoadNetworkCheckerRules(
      IParserMessageLogger messageLogger, IRoadNetwork object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRoadNetwork.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRoadNetwork> typedCheckerRule = (ICheckerRule<IRoadNetwork>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IFile logicFile = object.getLogicFile();
    if (logicFile != null) {
      applyFileCheckerRules(messageLogger, logicFile);
    }
    IFile sceneGraphFile = object.getSceneGraphFile();
    if (sceneGraphFile != null) {
      applyFileCheckerRules(messageLogger, sceneGraphFile);
    }
    List<ITrafficSignalController> trafficSignals = object.getTrafficSignals();
    if (trafficSignals != null) {
      for (ITrafficSignalController listItem : trafficSignals) {
        applyTrafficSignalControllerCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyRoadPositionCheckerRules(
      IParserMessageLogger messageLogger, IRoadPosition object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRoadPosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRoadPosition> typedCheckerRule = (ICheckerRule<IRoadPosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IOrientation orientation = object.getOrientation();
    if (orientation != null) {
      applyOrientationCheckerRules(messageLogger, orientation);
    }
  }

  private void applyRouteCheckerRules(IParserMessageLogger messageLogger, IRoute object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRoute.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRoute> typedCheckerRule = (ICheckerRule<IRoute>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    List<IWaypoint> waypoints = object.getWaypoints();
    if (waypoints != null) {
      for (IWaypoint listItem : waypoints) {
        applyWaypointCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyRouteCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, IRouteCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRouteCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRouteCatalogLocation> typedCheckerRule =
            (ICheckerRule<IRouteCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyRoutePositionCheckerRules(
      IParserMessageLogger messageLogger, IRoutePosition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRoutePosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRoutePosition> typedCheckerRule = (ICheckerRule<IRoutePosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IRouteRef routeRef = object.getRouteRef();
    if (routeRef != null) {
      applyRouteRefCheckerRules(messageLogger, routeRef);
    }
    IOrientation orientation = object.getOrientation();
    if (orientation != null) {
      applyOrientationCheckerRules(messageLogger, orientation);
    }
    IInRoutePosition inRoutePosition = object.getInRoutePosition();
    if (inRoutePosition != null) {
      applyInRoutePositionCheckerRules(messageLogger, inRoutePosition);
    }
  }

  private void applyRouteRefCheckerRules(IParserMessageLogger messageLogger, IRouteRef object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IRouteRef.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRouteRef> typedCheckerRule = (ICheckerRule<IRouteRef>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IRoute route = object.getRoute();
    if (route != null) {
      applyRouteCheckerRules(messageLogger, route);
    }
    ICatalogReference catalogReference = object.getCatalogReference();
    if (catalogReference != null) {
      applyCatalogReferenceCheckerRules(messageLogger, catalogReference);
    }
  }

  private void applyRoutingActionCheckerRules(
      IParserMessageLogger messageLogger, IRoutingAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IRoutingAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IRoutingAction> typedCheckerRule = (ICheckerRule<IRoutingAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IAssignRouteAction assignRouteAction = object.getAssignRouteAction();
    if (assignRouteAction != null) {
      applyAssignRouteActionCheckerRules(messageLogger, assignRouteAction);
    }
    IFollowTrajectoryAction followTrajectoryAction = object.getFollowTrajectoryAction();
    if (followTrajectoryAction != null) {
      applyFollowTrajectoryActionCheckerRules(messageLogger, followTrajectoryAction);
    }
    IAcquirePositionAction acquirePositionAction = object.getAcquirePositionAction();
    if (acquirePositionAction != null) {
      applyAcquirePositionActionCheckerRules(messageLogger, acquirePositionAction);
    }
  }

  private void applyScenarioDefinitionCheckerRules(
      IParserMessageLogger messageLogger, IScenarioDefinition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IScenarioDefinition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IScenarioDefinition> typedCheckerRule =
            (ICheckerRule<IScenarioDefinition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    ICatalogLocations catalogLocations = object.getCatalogLocations();
    if (catalogLocations != null) {
      applyCatalogLocationsCheckerRules(messageLogger, catalogLocations);
    }
    IRoadNetwork roadNetwork = object.getRoadNetwork();
    if (roadNetwork != null) {
      applyRoadNetworkCheckerRules(messageLogger, roadNetwork);
    }
    IEntities entities = object.getEntities();
    if (entities != null) {
      applyEntitiesCheckerRules(messageLogger, entities);
    }
    IStoryboard storyboard = object.getStoryboard();
    if (storyboard != null) {
      applyStoryboardCheckerRules(messageLogger, storyboard);
    }
  }

  private void applyScenarioObjectCheckerRules(
      IParserMessageLogger messageLogger, IScenarioObject object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IScenarioObject.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IScenarioObject> typedCheckerRule =
            (ICheckerRule<IScenarioObject>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IEntityObject entityObject = object.getEntityObject();
    if (entityObject != null) {
      applyEntityObjectCheckerRules(messageLogger, entityObject);
    }
    IObjectController objectController = object.getObjectController();
    if (objectController != null) {
      applyObjectControllerCheckerRules(messageLogger, objectController);
    }
  }

  private void applySelectedEntitiesCheckerRules(
      IParserMessageLogger messageLogger, ISelectedEntities object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISelectedEntities.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ISelectedEntities> typedCheckerRule =
            (ICheckerRule<ISelectedEntities>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IEntityRef> entityRef = object.getEntityRef();
    if (entityRef != null) {
      for (IEntityRef listItem : entityRef) {
        applyEntityRefCheckerRules(messageLogger, listItem);
      }
    }
    List<IByType> byType = object.getByType();
    if (byType != null) {
      for (IByType listItem : byType) {
        applyByTypeCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyShapeCheckerRules(IParserMessageLogger messageLogger, IShape object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IShape.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IShape> typedCheckerRule = (ICheckerRule<IShape>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPolyline polyline = object.getPolyline();
    if (polyline != null) {
      applyPolylineCheckerRules(messageLogger, polyline);
    }
    IClothoid clothoid = object.getClothoid();
    if (clothoid != null) {
      applyClothoidCheckerRules(messageLogger, clothoid);
    }
    INurbs nurbs = object.getNurbs();
    if (nurbs != null) {
      applyNurbsCheckerRules(messageLogger, nurbs);
    }
  }

  private void applySimulationTimeConditionCheckerRules(
      IParserMessageLogger messageLogger, ISimulationTimeCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISimulationTimeCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ISimulationTimeCondition> typedCheckerRule =
            (ICheckerRule<ISimulationTimeCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applySpeedActionCheckerRules(
      IParserMessageLogger messageLogger, ISpeedAction object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ISpeedAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ISpeedAction> typedCheckerRule = (ICheckerRule<ISpeedAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITransitionDynamics speedActionDynamics = object.getSpeedActionDynamics();
    if (speedActionDynamics != null) {
      applyTransitionDynamicsCheckerRules(messageLogger, speedActionDynamics);
    }
    ISpeedActionTarget speedActionTarget = object.getSpeedActionTarget();
    if (speedActionTarget != null) {
      applySpeedActionTargetCheckerRules(messageLogger, speedActionTarget);
    }
  }

  private void applySpeedActionTargetCheckerRules(
      IParserMessageLogger messageLogger, ISpeedActionTarget object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISpeedActionTarget.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ISpeedActionTarget> typedCheckerRule =
            (ICheckerRule<ISpeedActionTarget>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IRelativeTargetSpeed relativeTargetSpeed = object.getRelativeTargetSpeed();
    if (relativeTargetSpeed != null) {
      applyRelativeTargetSpeedCheckerRules(messageLogger, relativeTargetSpeed);
    }
    IAbsoluteTargetSpeed absoluteTargetSpeed = object.getAbsoluteTargetSpeed();
    if (absoluteTargetSpeed != null) {
      applyAbsoluteTargetSpeedCheckerRules(messageLogger, absoluteTargetSpeed);
    }
  }

  private void applySpeedConditionCheckerRules(
      IParserMessageLogger messageLogger, ISpeedCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISpeedCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ISpeedCondition> typedCheckerRule =
            (ICheckerRule<ISpeedCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyStandStillConditionCheckerRules(
      IParserMessageLogger messageLogger, IStandStillCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IStandStillCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IStandStillCondition> typedCheckerRule =
            (ICheckerRule<IStandStillCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyStoryCheckerRules(IParserMessageLogger messageLogger, IStory object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IStory.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IStory> typedCheckerRule = (ICheckerRule<IStory>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    List<IAct> acts = object.getActs();
    if (acts != null) {
      for (IAct listItem : acts) {
        applyActCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyStoryboardCheckerRules(IParserMessageLogger messageLogger, IStoryboard object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IStoryboard.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IStoryboard> typedCheckerRule = (ICheckerRule<IStoryboard>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IInit init = object.getInit();
    if (init != null) {
      applyInitCheckerRules(messageLogger, init);
    }
    List<IStory> stories = object.getStories();
    if (stories != null) {
      for (IStory listItem : stories) {
        applyStoryCheckerRules(messageLogger, listItem);
      }
    }
    ITrigger stopTrigger = object.getStopTrigger();
    if (stopTrigger != null) {
      applyTriggerCheckerRules(messageLogger, stopTrigger);
    }
  }

  private void applyStoryboardElementStateConditionCheckerRules(
      IParserMessageLogger messageLogger, IStoryboardElementStateCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IStoryboardElementStateCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IStoryboardElementStateCondition> typedCheckerRule =
            (ICheckerRule<IStoryboardElementStateCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applySunCheckerRules(IParserMessageLogger messageLogger, ISun object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ISun.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ISun> typedCheckerRule = (ICheckerRule<ISun>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applySynchronizeActionCheckerRules(
      IParserMessageLogger messageLogger, ISynchronizeAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ISynchronizeAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ISynchronizeAction> typedCheckerRule =
            (ICheckerRule<ISynchronizeAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition targetPositionMaster = object.getTargetPositionMaster();
    if (targetPositionMaster != null) {
      applyPositionCheckerRules(messageLogger, targetPositionMaster);
    }
    IPosition targetPosition = object.getTargetPosition();
    if (targetPosition != null) {
      applyPositionCheckerRules(messageLogger, targetPosition);
    }
    IFinalSpeed finalSpeed = object.getFinalSpeed();
    if (finalSpeed != null) {
      applyFinalSpeedCheckerRules(messageLogger, finalSpeed);
    }
  }

  private void applyTeleportActionCheckerRules(
      IParserMessageLogger messageLogger, ITeleportAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITeleportAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITeleportAction> typedCheckerRule =
            (ICheckerRule<ITeleportAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyTimeHeadwayConditionCheckerRules(
      IParserMessageLogger messageLogger, ITimeHeadwayCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeHeadwayCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITimeHeadwayCondition> typedCheckerRule =
            (ICheckerRule<ITimeHeadwayCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTimeOfDayCheckerRules(IParserMessageLogger messageLogger, ITimeOfDay object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITimeOfDay.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITimeOfDay> typedCheckerRule = (ICheckerRule<ITimeOfDay>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTimeOfDayConditionCheckerRules(
      IParserMessageLogger messageLogger, ITimeOfDayCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeOfDayCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITimeOfDayCondition> typedCheckerRule =
            (ICheckerRule<ITimeOfDayCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTimeReferenceCheckerRules(
      IParserMessageLogger messageLogger, ITimeReference object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeReference.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITimeReference> typedCheckerRule = (ICheckerRule<ITimeReference>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    INone none = object.getNone();
    if (none != null) {
      applyNoneCheckerRules(messageLogger, none);
    }
    ITiming timing = object.getTiming();
    if (timing != null) {
      applyTimingCheckerRules(messageLogger, timing);
    }
  }

  private void applyTimeToCollisionConditionCheckerRules(
      IParserMessageLogger messageLogger, ITimeToCollisionCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeToCollisionCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITimeToCollisionCondition> typedCheckerRule =
            (ICheckerRule<ITimeToCollisionCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITimeToCollisionConditionTarget timeToCollisionConditionTarget =
        object.getTimeToCollisionConditionTarget();
    if (timeToCollisionConditionTarget != null) {
      applyTimeToCollisionConditionTargetCheckerRules(
          messageLogger, timeToCollisionConditionTarget);
    }
  }

  private void applyTimeToCollisionConditionTargetCheckerRules(
      IParserMessageLogger messageLogger, ITimeToCollisionConditionTarget object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITimeToCollisionConditionTarget.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITimeToCollisionConditionTarget> typedCheckerRule =
            (ICheckerRule<ITimeToCollisionConditionTarget>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
    IEntityRef entityRef = object.getEntityRef();
    if (entityRef != null) {
      applyEntityRefCheckerRules(messageLogger, entityRef);
    }
  }

  private void applyTimingCheckerRules(IParserMessageLogger messageLogger, ITiming object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITiming.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITiming> typedCheckerRule = (ICheckerRule<ITiming>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTrafficActionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficAction> typedCheckerRule = (ICheckerRule<ITrafficAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITrafficSourceAction trafficSourceAction = object.getTrafficSourceAction();
    if (trafficSourceAction != null) {
      applyTrafficSourceActionCheckerRules(messageLogger, trafficSourceAction);
    }
    ITrafficSinkAction trafficSinkAction = object.getTrafficSinkAction();
    if (trafficSinkAction != null) {
      applyTrafficSinkActionCheckerRules(messageLogger, trafficSinkAction);
    }
    ITrafficSwarmAction trafficSwarmAction = object.getTrafficSwarmAction();
    if (trafficSwarmAction != null) {
      applyTrafficSwarmActionCheckerRules(messageLogger, trafficSwarmAction);
    }
  }

  private void applyTrafficDefinitionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficDefinition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficDefinition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficDefinition> typedCheckerRule =
            (ICheckerRule<ITrafficDefinition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IVehicleCategoryDistribution vehicleCategoryDistribution =
        object.getVehicleCategoryDistribution();
    if (vehicleCategoryDistribution != null) {
      applyVehicleCategoryDistributionCheckerRules(messageLogger, vehicleCategoryDistribution);
    }
    IControllerDistribution controllerDistribution = object.getControllerDistribution();
    if (controllerDistribution != null) {
      applyControllerDistributionCheckerRules(messageLogger, controllerDistribution);
    }
  }

  private void applyTrafficSignalActionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSignalAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSignalAction> typedCheckerRule =
            (ICheckerRule<ITrafficSignalAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ITrafficSignalControllerAction trafficSignalControllerAction =
        object.getTrafficSignalControllerAction();
    if (trafficSignalControllerAction != null) {
      applyTrafficSignalControllerActionCheckerRules(messageLogger, trafficSignalControllerAction);
    }
    ITrafficSignalStateAction trafficSignalStateAction = object.getTrafficSignalStateAction();
    if (trafficSignalStateAction != null) {
      applyTrafficSignalStateActionCheckerRules(messageLogger, trafficSignalStateAction);
    }
  }

  private void applyTrafficSignalConditionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSignalCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSignalCondition> typedCheckerRule =
            (ICheckerRule<ITrafficSignalCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTrafficSignalControllerCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSignalController object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalController.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSignalController> typedCheckerRule =
            (ICheckerRule<ITrafficSignalController>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IPhase> phases = object.getPhases();
    if (phases != null) {
      for (IPhase listItem : phases) {
        applyPhaseCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyTrafficSignalControllerActionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSignalControllerAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalControllerAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSignalControllerAction> typedCheckerRule =
            (ICheckerRule<ITrafficSignalControllerAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTrafficSignalControllerConditionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSignalControllerCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalControllerCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSignalControllerCondition> typedCheckerRule =
            (ICheckerRule<ITrafficSignalControllerCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTrafficSignalStateCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSignalState object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalState.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSignalState> typedCheckerRule =
            (ICheckerRule<ITrafficSignalState>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTrafficSignalStateActionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSignalStateAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSignalStateAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSignalStateAction> typedCheckerRule =
            (ICheckerRule<ITrafficSignalStateAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTrafficSinkActionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSinkAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSinkAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSinkAction> typedCheckerRule =
            (ICheckerRule<ITrafficSinkAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
    ITrafficDefinition trafficDefinition = object.getTrafficDefinition();
    if (trafficDefinition != null) {
      applyTrafficDefinitionCheckerRules(messageLogger, trafficDefinition);
    }
  }

  private void applyTrafficSourceActionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSourceAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSourceAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSourceAction> typedCheckerRule =
            (ICheckerRule<ITrafficSourceAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
    ITrafficDefinition trafficDefinition = object.getTrafficDefinition();
    if (trafficDefinition != null) {
      applyTrafficDefinitionCheckerRules(messageLogger, trafficDefinition);
    }
  }

  private void applyTrafficSwarmActionCheckerRules(
      IParserMessageLogger messageLogger, ITrafficSwarmAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrafficSwarmAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrafficSwarmAction> typedCheckerRule =
            (ICheckerRule<ITrafficSwarmAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ICentralSwarmObject centralObject = object.getCentralObject();
    if (centralObject != null) {
      applyCentralSwarmObjectCheckerRules(messageLogger, centralObject);
    }
    ITrafficDefinition trafficDefinition = object.getTrafficDefinition();
    if (trafficDefinition != null) {
      applyTrafficDefinitionCheckerRules(messageLogger, trafficDefinition);
    }
  }

  private void applyTrajectoryCheckerRules(IParserMessageLogger messageLogger, ITrajectory object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITrajectory.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrajectory> typedCheckerRule = (ICheckerRule<ITrajectory>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    IShape shape = object.getShape();
    if (shape != null) {
      applyShapeCheckerRules(messageLogger, shape);
    }
  }

  private void applyTrajectoryCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, ITrajectoryCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrajectoryCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrajectoryCatalogLocation> typedCheckerRule =
            (ICheckerRule<ITrajectoryCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyTrajectoryFollowingModeCheckerRules(
      IParserMessageLogger messageLogger, ITrajectoryFollowingMode object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITrajectoryFollowingMode.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrajectoryFollowingMode> typedCheckerRule =
            (ICheckerRule<ITrajectoryFollowingMode>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTransitionDynamicsCheckerRules(
      IParserMessageLogger messageLogger, ITransitionDynamics object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITransitionDynamics.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITransitionDynamics> typedCheckerRule =
            (ICheckerRule<ITransitionDynamics>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTraveledDistanceConditionCheckerRules(
      IParserMessageLogger messageLogger, ITraveledDistanceCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITraveledDistanceCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITraveledDistanceCondition> typedCheckerRule =
            (ICheckerRule<ITraveledDistanceCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyTriggerCheckerRules(IParserMessageLogger messageLogger, ITrigger object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(ITrigger.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITrigger> typedCheckerRule = (ICheckerRule<ITrigger>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IConditionGroup> conditionGroups = object.getConditionGroups();
    if (conditionGroups != null) {
      for (IConditionGroup listItem : conditionGroups) {
        applyConditionGroupCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyTriggeringEntitiesCheckerRules(
      IParserMessageLogger messageLogger, ITriggeringEntities object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(ITriggeringEntities.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<ITriggeringEntities> typedCheckerRule =
            (ICheckerRule<ITriggeringEntities>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IEntityRef> entityRefs = object.getEntityRefs();
    if (entityRefs != null) {
      for (IEntityRef listItem : entityRefs) {
        applyEntityRefCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyUserDefinedActionCheckerRules(
      IParserMessageLogger messageLogger, IUserDefinedAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IUserDefinedAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IUserDefinedAction> typedCheckerRule =
            (ICheckerRule<IUserDefinedAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ICustomCommandAction customCommandAction = object.getCustomCommandAction();
    if (customCommandAction != null) {
      applyCustomCommandActionCheckerRules(messageLogger, customCommandAction);
    }
  }

  private void applyUserDefinedValueConditionCheckerRules(
      IParserMessageLogger messageLogger, IUserDefinedValueCondition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IUserDefinedValueCondition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IUserDefinedValueCondition> typedCheckerRule =
            (ICheckerRule<IUserDefinedValueCondition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyVehicleCheckerRules(IParserMessageLogger messageLogger, IVehicle object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IVehicle.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IVehicle> typedCheckerRule = (ICheckerRule<IVehicle>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IParameterDeclaration> parameterDeclarations = object.getParameterDeclarations();
    if (parameterDeclarations != null) {
      for (IParameterDeclaration listItem : parameterDeclarations) {
        applyParameterDeclarationCheckerRules(messageLogger, listItem);
      }
    }
    IBoundingBox boundingBox = object.getBoundingBox();
    if (boundingBox != null) {
      applyBoundingBoxCheckerRules(messageLogger, boundingBox);
    }
    IPerformance performance = object.getPerformance();
    if (performance != null) {
      applyPerformanceCheckerRules(messageLogger, performance);
    }
    IAxles axles = object.getAxles();
    if (axles != null) {
      applyAxlesCheckerRules(messageLogger, axles);
    }
    IProperties properties = object.getProperties();
    if (properties != null) {
      applyPropertiesCheckerRules(messageLogger, properties);
    }
  }

  private void applyVehicleCatalogLocationCheckerRules(
      IParserMessageLogger messageLogger, IVehicleCatalogLocation object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVehicleCatalogLocation.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IVehicleCatalogLocation> typedCheckerRule =
            (ICheckerRule<IVehicleCatalogLocation>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IDirectory directory = object.getDirectory();
    if (directory != null) {
      applyDirectoryCheckerRules(messageLogger, directory);
    }
  }

  private void applyVehicleCategoryDistributionCheckerRules(
      IParserMessageLogger messageLogger, IVehicleCategoryDistribution object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVehicleCategoryDistribution.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IVehicleCategoryDistribution> typedCheckerRule =
            (ICheckerRule<IVehicleCategoryDistribution>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    List<IVehicleCategoryDistributionEntry> vehicleCategoryDistributionEntries =
        object.getVehicleCategoryDistributionEntries();
    if (vehicleCategoryDistributionEntries != null) {
      for (IVehicleCategoryDistributionEntry listItem : vehicleCategoryDistributionEntries) {
        applyVehicleCategoryDistributionEntryCheckerRules(messageLogger, listItem);
      }
    }
  }

  private void applyVehicleCategoryDistributionEntryCheckerRules(
      IParserMessageLogger messageLogger, IVehicleCategoryDistributionEntry object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVehicleCategoryDistributionEntry.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IVehicleCategoryDistributionEntry> typedCheckerRule =
            (ICheckerRule<IVehicleCategoryDistributionEntry>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyVertexCheckerRules(IParserMessageLogger messageLogger, IVertex object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IVertex.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IVertex> typedCheckerRule = (ICheckerRule<IVertex>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyVisibilityActionCheckerRules(
      IParserMessageLogger messageLogger, IVisibilityAction object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IVisibilityAction.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IVisibilityAction> typedCheckerRule =
            (ICheckerRule<IVisibilityAction>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }

  private void applyWaypointCheckerRules(IParserMessageLogger messageLogger, IWaypoint object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IWaypoint.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IWaypoint> typedCheckerRule = (ICheckerRule<IWaypoint>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    IPosition position = object.getPosition();
    if (position != null) {
      applyPositionCheckerRules(messageLogger, position);
    }
  }

  private void applyWeatherCheckerRules(IParserMessageLogger messageLogger, IWeather object) {
    List<ICheckerRule<?>> checkerRulesForType = this.typeToCheckerRuleList.get(IWeather.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IWeather> typedCheckerRule = (ICheckerRule<IWeather>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
    ISun sun = object.getSun();
    if (sun != null) {
      applySunCheckerRules(messageLogger, sun);
    }
    IFog fog = object.getFog();
    if (fog != null) {
      applyFogCheckerRules(messageLogger, fog);
    }
    IPrecipitation precipitation = object.getPrecipitation();
    if (precipitation != null) {
      applyPrecipitationCheckerRules(messageLogger, precipitation);
    }
  }

  private void applyWorldPositionCheckerRules(
      IParserMessageLogger messageLogger, IWorldPosition object) {
    List<ICheckerRule<?>> checkerRulesForType =
        this.typeToCheckerRuleList.get(IWorldPosition.class);
    if (checkerRulesForType != null) {
      for (ICheckerRule<?> checkerRule : checkerRulesForType) {
        @SuppressWarnings("unchecked")
        ICheckerRule<IWorldPosition> typedCheckerRule = (ICheckerRule<IWorldPosition>) checkerRule;
        typedCheckerRule.applyRule(messageLogger, object);
      }
    }
    // getChildren
  }
}
