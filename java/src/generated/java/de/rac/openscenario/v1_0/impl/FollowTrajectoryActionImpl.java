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
 
package de.rac.openscenario.v1_0.impl;

import de.rac.openscenario.v1_0.api.IFollowTrajectoryAction;
import de.rac.openscenario.v1_0.impl.BaseImpl;
import de.rac.openscenario.v1_0.common.ILocator;
import de.rac.openscenario.v1_0.common.IParserMessageLogger;
import de.rac.openscenario.v1_0.common.OscConstants;
import de.rac.openscenario.v1_0.common.FileContentMessage;
import de.rac.openscenario.v1_0.api.IOpenScenarioModelElement;
import java.lang.Cloneable;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

import de.rac.openscenario.v1_0.api.ITimeReference;
import de.rac.openscenario.v1_0.api.ICatalogReference;
import de.rac.openscenario.v1_0.api.ITrajectory;
import de.rac.openscenario.v1_0.api.ITrajectoryFollowingMode;


/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * <p>
 * Value class that implements IFollowTrajectoryAction. With setter methods to fill the properties.
 * <ul>
 * <li> getter methods for properties (implemented methods of IFollowTrajectoryAction)
 * <li> setter methods for properties
 * <li> getChildren method to collect all children
 * <li> clone function to make a deep copy
 * <li> overrides from BaseImpl
 * </ul>
 * 
 * @author RA Consulting OpenSCENARIO generation facility
*/
public class FollowTrajectoryActionImpl extends BaseImpl implements IFollowTrajectoryAction, Cloneable{
	
	/**
	 * Filling the property to type map
	 */
	 static{
	}
	
	private ITrajectory trajectory;
	private ICatalogReference catalogReference;
	private ITimeReference timeReference;
	private ITrajectoryFollowingMode trajectoryFollowingMode;

	@Override
	public ITrajectory getTrajectory()
	{
		return trajectory;
	}
	@Override
	public ICatalogReference getCatalogReference()
	{
		return catalogReference;
	}
	@Override
	public ITimeReference getTimeReference()
	{
		return timeReference;
	}
	@Override
	public ITrajectoryFollowingMode getTrajectoryFollowingMode()
	{
		return trajectoryFollowingMode;
	}
	/**
	 * Sets the value of model property trajectory
	 * @param trajectory from OpenSCENARIO class model specification: [Trajectory definition.]
	 * 
	*/
	public  void setTrajectory (ITrajectory trajectory )
	{
		this.trajectory = trajectory;
	}
	/**
	 * Sets the value of model property catalogReference
	 * @param catalogReference from OpenSCENARIO class model specification: [A reference to the trajectory type in a catalog.]
	 * 
	*/
	public  void setCatalogReference (ICatalogReference catalogReference )
	{
		this.catalogReference = catalogReference;
	}
	/**
	 * Sets the value of model property timeReference
	 * @param timeReference from OpenSCENARIO class model specification: [Defines if time information provided within the 
	 * trajectory should be considered. If so, it may be used as either , absolute or relative time along the trajectory in 
	 * order to define longitudinal velocity of the actor. Moreover, a time , offset or time scaling may be applied.]
	 * 
	*/
	public  void setTimeReference (ITimeReference timeReference )
	{
		this.timeReference = timeReference;
	}
	/**
	 * Sets the value of model property trajectoryFollowingMode
	 * @param trajectoryFollowingMode from OpenSCENARIO class model specification: [The mode how to follow the given 
	 * trajectory.]
	 * 
	*/
	public  void setTrajectoryFollowingMode (ITrajectoryFollowingMode trajectoryFollowingMode )
	{
		this.trajectoryFollowingMode = trajectoryFollowingMode;
	}

	@Override
	public  void resolveParameterInternal(IParserMessageLogger logger,String attributeKey, String parameterLiteralValue)
	{
	}
	
	@Override
	public  Class<?> getTypeFromAttributeName(String attributeKey)
	{
		return propertyToType.get(attributeKey);
	}

	/**
	 * A list of all aggregated children (lists are flattened). This may be used for applying a specific 
	 * method for any child.
	 * @return a list with all children (as BaseImpl)
	 */
	public List<BaseImpl> getChildren()
	{
		List<BaseImpl> result = new ArrayList<BaseImpl>();
		
			ITrajectory trajectory = null;
			trajectory =  getTrajectory();
			if (trajectory != null)
			{
				result.add((BaseImpl) trajectory);
			}	
			ICatalogReference catalogReference = null;
			catalogReference =  getCatalogReference();
			if (catalogReference != null)
			{
				result.add((BaseImpl) catalogReference);
			}	
			ITimeReference timeReference = null;
			timeReference =  getTimeReference();
			if (timeReference != null)
			{
				result.add((BaseImpl) timeReference);
			}	
			ITrajectoryFollowingMode trajectoryFollowingMode = null;
			trajectoryFollowingMode =  getTrajectoryFollowingMode();
			if (trajectoryFollowingMode != null)
			{
				result.add((BaseImpl) trajectoryFollowingMode);
			}	
		return result;
	}
	
	/**
	 * Making a (deep) clone this object. This is useful and used for importing elements from catalogs.
	 * @return a deep copy of the object.
	 */
	public FollowTrajectoryActionImpl clone()
	{
		FollowTrajectoryActionImpl clonedObject = new FollowTrajectoryActionImpl();
		cloneStartMarker(clonedObject);
		cloneEndMarker(clonedObject);
		cloneAttributeKeyToStartMarker(clonedObject);
		cloneAttributeKeyToEndMarker(clonedObject);
		cloneAttributeKeyToParameterNameMap(clonedObject);
		// clone attributes;
		// clone children
		ITrajectory trajectory = null;
		trajectory =  getTrajectory();
		if (trajectory != null)
		{
			TrajectoryImpl clonedChild = ((TrajectoryImpl) trajectory).clone();
			clonedObject.setTrajectory(clonedChild);
			clonedChild.setParent(clonedObject);
		}	
		ICatalogReference catalogReference = null;
		catalogReference =  getCatalogReference();
		if (catalogReference != null)
		{
			CatalogReferenceImpl clonedChild = ((CatalogReferenceImpl) catalogReference).clone();
			clonedObject.setCatalogReference(clonedChild);
			clonedChild.setParent(clonedObject);
		}	
		ITimeReference timeReference = null;
		timeReference =  getTimeReference();
		if (timeReference != null)
		{
			TimeReferenceImpl clonedChild = ((TimeReferenceImpl) timeReference).clone();
			clonedObject.setTimeReference(clonedChild);
			clonedChild.setParent(clonedObject);
		}	
		ITrajectoryFollowingMode trajectoryFollowingMode = null;
		trajectoryFollowingMode =  getTrajectoryFollowingMode();
		if (trajectoryFollowingMode != null)
		{
			TrajectoryFollowingModeImpl clonedChild = ((TrajectoryFollowingModeImpl) trajectoryFollowingMode).clone();
			clonedObject.setTrajectoryFollowingMode(clonedChild);
			clonedChild.setParent(clonedObject);
		}	
		return clonedObject;
	}
	
	
	
}

