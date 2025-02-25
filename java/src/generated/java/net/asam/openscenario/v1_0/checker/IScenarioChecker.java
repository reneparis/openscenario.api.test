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
package net.asam.openscenario.v1_0.checker;

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

/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * Provides a method for every type of the osc model to add checker rules.
 *
 * @author RA Consulting OpenSCENARIO generation facility
 */
public interface IScenarioChecker {
  public void checkScenario(IParserMessageLogger messageLogger, IOpenScenario openScenario);

  public void addAbsoluteSpeedCheckerRule(ICheckerRule<IAbsoluteSpeed> checkerRule);

  public void addAbsoluteTargetLaneCheckerRule(ICheckerRule<IAbsoluteTargetLane> checkerRule);

  public void addAbsoluteTargetLaneOffsetCheckerRule(
      ICheckerRule<IAbsoluteTargetLaneOffset> checkerRule);

  public void addAbsoluteTargetSpeedCheckerRule(ICheckerRule<IAbsoluteTargetSpeed> checkerRule);

  public void addAccelerationConditionCheckerRule(ICheckerRule<IAccelerationCondition> checkerRule);

  public void addAcquirePositionActionCheckerRule(ICheckerRule<IAcquirePositionAction> checkerRule);

  public void addActCheckerRule(ICheckerRule<IAct> checkerRule);

  public void addActionCheckerRule(ICheckerRule<IAction> checkerRule);

  public void addActivateControllerActionCheckerRule(
      ICheckerRule<IActivateControllerAction> checkerRule);

  public void addActorsCheckerRule(ICheckerRule<IActors> checkerRule);

  public void addAddEntityActionCheckerRule(ICheckerRule<IAddEntityAction> checkerRule);

  public void addAssignControllerActionCheckerRule(
      ICheckerRule<IAssignControllerAction> checkerRule);

  public void addAssignRouteActionCheckerRule(ICheckerRule<IAssignRouteAction> checkerRule);

  public void addAxleCheckerRule(ICheckerRule<IAxle> checkerRule);

  public void addAxlesCheckerRule(ICheckerRule<IAxles> checkerRule);

  public void addBoundingBoxCheckerRule(ICheckerRule<IBoundingBox> checkerRule);

  public void addByEntityConditionCheckerRule(ICheckerRule<IByEntityCondition> checkerRule);

  public void addByObjectTypeCheckerRule(ICheckerRule<IByObjectType> checkerRule);

  public void addByTypeCheckerRule(ICheckerRule<IByType> checkerRule);

  public void addByValueConditionCheckerRule(ICheckerRule<IByValueCondition> checkerRule);

  public void addCatalogCheckerRule(ICheckerRule<ICatalog> checkerRule);

  public void addCatalogDefinitionCheckerRule(ICheckerRule<ICatalogDefinition> checkerRule);

  public void addCatalogLocationsCheckerRule(ICheckerRule<ICatalogLocations> checkerRule);

  public void addCatalogReferenceCheckerRule(ICheckerRule<ICatalogReference> checkerRule);

  public void addCenterCheckerRule(ICheckerRule<ICenter> checkerRule);

  public void addCentralSwarmObjectCheckerRule(ICheckerRule<ICentralSwarmObject> checkerRule);

  public void addClothoidCheckerRule(ICheckerRule<IClothoid> checkerRule);

  public void addCollisionConditionCheckerRule(ICheckerRule<ICollisionCondition> checkerRule);

  public void addConditionCheckerRule(ICheckerRule<ICondition> checkerRule);

  public void addConditionGroupCheckerRule(ICheckerRule<IConditionGroup> checkerRule);

  public void addControlPointCheckerRule(ICheckerRule<IControlPoint> checkerRule);

  public void addControllerCheckerRule(ICheckerRule<IController> checkerRule);

  public void addControllerActionCheckerRule(ICheckerRule<IControllerAction> checkerRule);

  public void addControllerCatalogLocationCheckerRule(
      ICheckerRule<IControllerCatalogLocation> checkerRule);

  public void addControllerDistributionCheckerRule(
      ICheckerRule<IControllerDistribution> checkerRule);

  public void addControllerDistributionEntryCheckerRule(
      ICheckerRule<IControllerDistributionEntry> checkerRule);

  public void addCustomCommandActionCheckerRule(ICheckerRule<ICustomCommandAction> checkerRule);

  public void addDeleteEntityActionCheckerRule(ICheckerRule<IDeleteEntityAction> checkerRule);

  public void addDimensionsCheckerRule(ICheckerRule<IDimensions> checkerRule);

  public void addDirectoryCheckerRule(ICheckerRule<IDirectory> checkerRule);

  public void addDistanceConditionCheckerRule(ICheckerRule<IDistanceCondition> checkerRule);

  public void addDynamicConstraintsCheckerRule(ICheckerRule<IDynamicConstraints> checkerRule);

  public void addEndOfRoadConditionCheckerRule(ICheckerRule<IEndOfRoadCondition> checkerRule);

  public void addEntitiesCheckerRule(ICheckerRule<IEntities> checkerRule);

  public void addEntityActionCheckerRule(ICheckerRule<IEntityAction> checkerRule);

  public void addEntityConditionCheckerRule(ICheckerRule<IEntityCondition> checkerRule);

  public void addEntityObjectCheckerRule(ICheckerRule<IEntityObject> checkerRule);

  public void addEntityRefCheckerRule(ICheckerRule<IEntityRef> checkerRule);

  public void addEntitySelectionCheckerRule(ICheckerRule<IEntitySelection> checkerRule);

  public void addEnvironmentCheckerRule(ICheckerRule<IEnvironment> checkerRule);

  public void addEnvironmentActionCheckerRule(ICheckerRule<IEnvironmentAction> checkerRule);

  public void addEnvironmentCatalogLocationCheckerRule(
      ICheckerRule<IEnvironmentCatalogLocation> checkerRule);

  public void addEventCheckerRule(ICheckerRule<IEvent> checkerRule);

  public void addFileCheckerRule(ICheckerRule<IFile> checkerRule);

  public void addFileHeaderCheckerRule(ICheckerRule<IFileHeader> checkerRule);

  public void addFinalSpeedCheckerRule(ICheckerRule<IFinalSpeed> checkerRule);

  public void addFogCheckerRule(ICheckerRule<IFog> checkerRule);

  public void addFollowTrajectoryActionCheckerRule(
      ICheckerRule<IFollowTrajectoryAction> checkerRule);

  public void addGlobalActionCheckerRule(ICheckerRule<IGlobalAction> checkerRule);

  public void addInRoutePositionCheckerRule(ICheckerRule<IInRoutePosition> checkerRule);

  public void addInfrastructureActionCheckerRule(ICheckerRule<IInfrastructureAction> checkerRule);

  public void addInitCheckerRule(ICheckerRule<IInit> checkerRule);

  public void addInitActionsCheckerRule(ICheckerRule<IInitActions> checkerRule);

  public void addKnotCheckerRule(ICheckerRule<IKnot> checkerRule);

  public void addLaneChangeActionCheckerRule(ICheckerRule<ILaneChangeAction> checkerRule);

  public void addLaneChangeTargetCheckerRule(ICheckerRule<ILaneChangeTarget> checkerRule);

  public void addLaneOffsetActionCheckerRule(ICheckerRule<ILaneOffsetAction> checkerRule);

  public void addLaneOffsetActionDynamicsCheckerRule(
      ICheckerRule<ILaneOffsetActionDynamics> checkerRule);

  public void addLaneOffsetTargetCheckerRule(ICheckerRule<ILaneOffsetTarget> checkerRule);

  public void addLanePositionCheckerRule(ICheckerRule<ILanePosition> checkerRule);

  public void addLateralActionCheckerRule(ICheckerRule<ILateralAction> checkerRule);

  public void addLateralDistanceActionCheckerRule(ICheckerRule<ILateralDistanceAction> checkerRule);

  public void addLongitudinalActionCheckerRule(ICheckerRule<ILongitudinalAction> checkerRule);

  public void addLongitudinalDistanceActionCheckerRule(
      ICheckerRule<ILongitudinalDistanceAction> checkerRule);

  public void addManeuverCheckerRule(ICheckerRule<IManeuver> checkerRule);

  public void addManeuverCatalogLocationCheckerRule(
      ICheckerRule<IManeuverCatalogLocation> checkerRule);

  public void addManeuverGroupCheckerRule(ICheckerRule<IManeuverGroup> checkerRule);

  public void addMiscObjectCheckerRule(ICheckerRule<IMiscObject> checkerRule);

  public void addMiscObjectCatalogLocationCheckerRule(
      ICheckerRule<IMiscObjectCatalogLocation> checkerRule);

  public void addModifyRuleCheckerRule(ICheckerRule<IModifyRule> checkerRule);

  public void addNoneCheckerRule(ICheckerRule<INone> checkerRule);

  public void addNurbsCheckerRule(ICheckerRule<INurbs> checkerRule);

  public void addObjectControllerCheckerRule(ICheckerRule<IObjectController> checkerRule);

  public void addOffroadConditionCheckerRule(ICheckerRule<IOffroadCondition> checkerRule);

  public void addOpenScenarioCheckerRule(ICheckerRule<IOpenScenario> checkerRule);

  public void addOpenScenarioCategoryCheckerRule(ICheckerRule<IOpenScenarioCategory> checkerRule);

  public void addOrientationCheckerRule(ICheckerRule<IOrientation> checkerRule);

  public void addOverrideBrakeActionCheckerRule(ICheckerRule<IOverrideBrakeAction> checkerRule);

  public void addOverrideClutchActionCheckerRule(ICheckerRule<IOverrideClutchAction> checkerRule);

  public void addOverrideControllerValueActionCheckerRule(
      ICheckerRule<IOverrideControllerValueAction> checkerRule);

  public void addOverrideGearActionCheckerRule(ICheckerRule<IOverrideGearAction> checkerRule);

  public void addOverrideParkingBrakeActionCheckerRule(
      ICheckerRule<IOverrideParkingBrakeAction> checkerRule);

  public void addOverrideSteeringWheelActionCheckerRule(
      ICheckerRule<IOverrideSteeringWheelAction> checkerRule);

  public void addOverrideThrottleActionCheckerRule(
      ICheckerRule<IOverrideThrottleAction> checkerRule);

  public void addParameterActionCheckerRule(ICheckerRule<IParameterAction> checkerRule);

  public void addParameterAddValueRuleCheckerRule(ICheckerRule<IParameterAddValueRule> checkerRule);

  public void addParameterAssignmentCheckerRule(ICheckerRule<IParameterAssignment> checkerRule);

  public void addParameterConditionCheckerRule(ICheckerRule<IParameterCondition> checkerRule);

  public void addParameterDeclarationCheckerRule(ICheckerRule<IParameterDeclaration> checkerRule);

  public void addParameterModifyActionCheckerRule(ICheckerRule<IParameterModifyAction> checkerRule);

  public void addParameterMultiplyByValueRuleCheckerRule(
      ICheckerRule<IParameterMultiplyByValueRule> checkerRule);

  public void addParameterSetActionCheckerRule(ICheckerRule<IParameterSetAction> checkerRule);

  public void addPedestrianCheckerRule(ICheckerRule<IPedestrian> checkerRule);

  public void addPedestrianCatalogLocationCheckerRule(
      ICheckerRule<IPedestrianCatalogLocation> checkerRule);

  public void addPerformanceCheckerRule(ICheckerRule<IPerformance> checkerRule);

  public void addPhaseCheckerRule(ICheckerRule<IPhase> checkerRule);

  public void addPolylineCheckerRule(ICheckerRule<IPolyline> checkerRule);

  public void addPositionCheckerRule(ICheckerRule<IPosition> checkerRule);

  public void addPositionInLaneCoordinatesCheckerRule(
      ICheckerRule<IPositionInLaneCoordinates> checkerRule);

  public void addPositionInRoadCoordinatesCheckerRule(
      ICheckerRule<IPositionInRoadCoordinates> checkerRule);

  public void addPositionOfCurrentEntityCheckerRule(
      ICheckerRule<IPositionOfCurrentEntity> checkerRule);

  public void addPrecipitationCheckerRule(ICheckerRule<IPrecipitation> checkerRule);

  public void addPrivateCheckerRule(ICheckerRule<IPrivate> checkerRule);

  public void addPrivateActionCheckerRule(ICheckerRule<IPrivateAction> checkerRule);

  public void addPropertiesCheckerRule(ICheckerRule<IProperties> checkerRule);

  public void addPropertyCheckerRule(ICheckerRule<IProperty> checkerRule);

  public void addReachPositionConditionCheckerRule(
      ICheckerRule<IReachPositionCondition> checkerRule);

  public void addRelativeDistanceConditionCheckerRule(
      ICheckerRule<IRelativeDistanceCondition> checkerRule);

  public void addRelativeLanePositionCheckerRule(ICheckerRule<IRelativeLanePosition> checkerRule);

  public void addRelativeObjectPositionCheckerRule(
      ICheckerRule<IRelativeObjectPosition> checkerRule);

  public void addRelativeRoadPositionCheckerRule(ICheckerRule<IRelativeRoadPosition> checkerRule);

  public void addRelativeSpeedConditionCheckerRule(
      ICheckerRule<IRelativeSpeedCondition> checkerRule);

  public void addRelativeSpeedToMasterCheckerRule(ICheckerRule<IRelativeSpeedToMaster> checkerRule);

  public void addRelativeTargetLaneCheckerRule(ICheckerRule<IRelativeTargetLane> checkerRule);

  public void addRelativeTargetLaneOffsetCheckerRule(
      ICheckerRule<IRelativeTargetLaneOffset> checkerRule);

  public void addRelativeTargetSpeedCheckerRule(ICheckerRule<IRelativeTargetSpeed> checkerRule);

  public void addRelativeWorldPositionCheckerRule(ICheckerRule<IRelativeWorldPosition> checkerRule);

  public void addRoadConditionCheckerRule(ICheckerRule<IRoadCondition> checkerRule);

  public void addRoadNetworkCheckerRule(ICheckerRule<IRoadNetwork> checkerRule);

  public void addRoadPositionCheckerRule(ICheckerRule<IRoadPosition> checkerRule);

  public void addRouteCheckerRule(ICheckerRule<IRoute> checkerRule);

  public void addRouteCatalogLocationCheckerRule(ICheckerRule<IRouteCatalogLocation> checkerRule);

  public void addRoutePositionCheckerRule(ICheckerRule<IRoutePosition> checkerRule);

  public void addRouteRefCheckerRule(ICheckerRule<IRouteRef> checkerRule);

  public void addRoutingActionCheckerRule(ICheckerRule<IRoutingAction> checkerRule);

  public void addScenarioDefinitionCheckerRule(ICheckerRule<IScenarioDefinition> checkerRule);

  public void addScenarioObjectCheckerRule(ICheckerRule<IScenarioObject> checkerRule);

  public void addSelectedEntitiesCheckerRule(ICheckerRule<ISelectedEntities> checkerRule);

  public void addShapeCheckerRule(ICheckerRule<IShape> checkerRule);

  public void addSimulationTimeConditionCheckerRule(
      ICheckerRule<ISimulationTimeCondition> checkerRule);

  public void addSpeedActionCheckerRule(ICheckerRule<ISpeedAction> checkerRule);

  public void addSpeedActionTargetCheckerRule(ICheckerRule<ISpeedActionTarget> checkerRule);

  public void addSpeedConditionCheckerRule(ICheckerRule<ISpeedCondition> checkerRule);

  public void addStandStillConditionCheckerRule(ICheckerRule<IStandStillCondition> checkerRule);

  public void addStoryCheckerRule(ICheckerRule<IStory> checkerRule);

  public void addStoryboardCheckerRule(ICheckerRule<IStoryboard> checkerRule);

  public void addStoryboardElementStateConditionCheckerRule(
      ICheckerRule<IStoryboardElementStateCondition> checkerRule);

  public void addSunCheckerRule(ICheckerRule<ISun> checkerRule);

  public void addSynchronizeActionCheckerRule(ICheckerRule<ISynchronizeAction> checkerRule);

  public void addTeleportActionCheckerRule(ICheckerRule<ITeleportAction> checkerRule);

  public void addTimeHeadwayConditionCheckerRule(ICheckerRule<ITimeHeadwayCondition> checkerRule);

  public void addTimeOfDayCheckerRule(ICheckerRule<ITimeOfDay> checkerRule);

  public void addTimeOfDayConditionCheckerRule(ICheckerRule<ITimeOfDayCondition> checkerRule);

  public void addTimeReferenceCheckerRule(ICheckerRule<ITimeReference> checkerRule);

  public void addTimeToCollisionConditionCheckerRule(
      ICheckerRule<ITimeToCollisionCondition> checkerRule);

  public void addTimeToCollisionConditionTargetCheckerRule(
      ICheckerRule<ITimeToCollisionConditionTarget> checkerRule);

  public void addTimingCheckerRule(ICheckerRule<ITiming> checkerRule);

  public void addTrafficActionCheckerRule(ICheckerRule<ITrafficAction> checkerRule);

  public void addTrafficDefinitionCheckerRule(ICheckerRule<ITrafficDefinition> checkerRule);

  public void addTrafficSignalActionCheckerRule(ICheckerRule<ITrafficSignalAction> checkerRule);

  public void addTrafficSignalConditionCheckerRule(
      ICheckerRule<ITrafficSignalCondition> checkerRule);

  public void addTrafficSignalControllerCheckerRule(
      ICheckerRule<ITrafficSignalController> checkerRule);

  public void addTrafficSignalControllerActionCheckerRule(
      ICheckerRule<ITrafficSignalControllerAction> checkerRule);

  public void addTrafficSignalControllerConditionCheckerRule(
      ICheckerRule<ITrafficSignalControllerCondition> checkerRule);

  public void addTrafficSignalStateCheckerRule(ICheckerRule<ITrafficSignalState> checkerRule);

  public void addTrafficSignalStateActionCheckerRule(
      ICheckerRule<ITrafficSignalStateAction> checkerRule);

  public void addTrafficSinkActionCheckerRule(ICheckerRule<ITrafficSinkAction> checkerRule);

  public void addTrafficSourceActionCheckerRule(ICheckerRule<ITrafficSourceAction> checkerRule);

  public void addTrafficSwarmActionCheckerRule(ICheckerRule<ITrafficSwarmAction> checkerRule);

  public void addTrajectoryCheckerRule(ICheckerRule<ITrajectory> checkerRule);

  public void addTrajectoryCatalogLocationCheckerRule(
      ICheckerRule<ITrajectoryCatalogLocation> checkerRule);

  public void addTrajectoryFollowingModeCheckerRule(
      ICheckerRule<ITrajectoryFollowingMode> checkerRule);

  public void addTransitionDynamicsCheckerRule(ICheckerRule<ITransitionDynamics> checkerRule);

  public void addTraveledDistanceConditionCheckerRule(
      ICheckerRule<ITraveledDistanceCondition> checkerRule);

  public void addTriggerCheckerRule(ICheckerRule<ITrigger> checkerRule);

  public void addTriggeringEntitiesCheckerRule(ICheckerRule<ITriggeringEntities> checkerRule);

  public void addUserDefinedActionCheckerRule(ICheckerRule<IUserDefinedAction> checkerRule);

  public void addUserDefinedValueConditionCheckerRule(
      ICheckerRule<IUserDefinedValueCondition> checkerRule);

  public void addVehicleCheckerRule(ICheckerRule<IVehicle> checkerRule);

  public void addVehicleCatalogLocationCheckerRule(
      ICheckerRule<IVehicleCatalogLocation> checkerRule);

  public void addVehicleCategoryDistributionCheckerRule(
      ICheckerRule<IVehicleCategoryDistribution> checkerRule);

  public void addVehicleCategoryDistributionEntryCheckerRule(
      ICheckerRule<IVehicleCategoryDistributionEntry> checkerRule);

  public void addVertexCheckerRule(ICheckerRule<IVertex> checkerRule);

  public void addVisibilityActionCheckerRule(ICheckerRule<IVisibilityAction> checkerRule);

  public void addWaypointCheckerRule(ICheckerRule<IWaypoint> checkerRule);

  public void addWeatherCheckerRule(ICheckerRule<IWeather> checkerRule);

  public void addWorldPositionCheckerRule(ICheckerRule<IWorldPosition> checkerRule);
}
