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

import de.rac.openscenario.v1_0.api.IPositionInLaneCoordinates;
import de.rac.openscenario.v1_0.impl.BaseImpl;
import de.rac.openscenario.v1_0.common.ILocator;
import de.rac.openscenario.v1_0.common.IParserMessageLogger;
import de.rac.openscenario.v1_0.common.OscConstants;
import de.rac.openscenario.v1_0.common.FileContentMessage;
import de.rac.openscenario.v1_0.api.IOpenScenarioModelElement;
import java.lang.Cloneable;
import de.rac.openscenario.v1_0.parser.ParserHelper;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;



/**
 * This is a automatic generated file according to the OpenSCENARIO specification version 1.0
 * <p>
 * Value class that implements IPositionInLaneCoordinates. With setter methods to fill the properties.
 * <ul>
 * <li> getter methods for properties (implemented methods of IPositionInLaneCoordinates)
 * <li> setter methods for properties
 * <li> getChildren method to collect all children
 * <li> clone function to make a deep copy
 * <li> overrides from BaseImpl
 * </ul>
 * 
 * @author RA Consulting OpenSCENARIO generation facility
*/
public class PositionInLaneCoordinatesImpl extends BaseImpl implements IPositionInLaneCoordinates, Cloneable{
	
	/**
	 * Filling the property to type map
	 */
	 static{
		propertyToType.put(OscConstants.ATTRIBUTE__PATH_S, Double.class);
		propertyToType.put(OscConstants.ATTRIBUTE__LANE_ID, String.class);
		propertyToType.put(OscConstants.ATTRIBUTE__LANE_OFFSET, Double.class);
	}
	
	private Double pathS;
	private String laneId;
	private Double laneOffset;

	@Override
	public Double getPathS()
	{
		return pathS;
	}
	@Override
	public String getLaneId()
	{
		return laneId;
	}
	@Override
	public Double getLaneOffset()
	{
		return laneOffset;
	}
	/**
	 * Sets the value of model property pathS
	 * @param pathS from OpenSCENARIO class model specification: [s-coordinate of the actual position. Unit: m; Range: 
	 * [0..inf[.]
	 * 
	*/
	public  void setPathS (Double pathS )
	{
		this.pathS = pathS;
	}
	/**
	 * Sets the value of model property laneId
	 * @param laneId from OpenSCENARIO class model specification: [Lane ID of the actual position.]
	 * 
	*/
	public  void setLaneId (String laneId )
	{
		this.laneId = laneId;
	}
	/**
	 * Sets the value of model property laneOffset
	 * @param laneOffset from OpenSCENARIO class model specification: [Lateral offset (relative to the lane centerline) of the 
	 * actual position. Unit: m.]
	 * 
	*/
	public  void setLaneOffset (Double laneOffset )
	{
		this.laneOffset = laneOffset;
	}

	@Override
	public  void resolveParameterInternal(IParserMessageLogger logger,String attributeKey, String parameterLiteralValue)
	{
		if (attributeKey.equals(OscConstants.ATTRIBUTE__PATH_S))
		{
			// Simple type
			pathS = ParserHelper.parseDouble(logger,parameterLiteralValue,getTextmarker(attributeKey));
			removeResolvedParameter(attributeKey);
				
	 	}
		else if (attributeKey.equals(OscConstants.ATTRIBUTE__LANE_ID))
		{
			// Simple type
			laneId = ParserHelper.parseString(logger,parameterLiteralValue,getTextmarker(attributeKey));
			removeResolvedParameter(attributeKey);
				
	 	}
		else if (attributeKey.equals(OscConstants.ATTRIBUTE__LANE_OFFSET))
		{
			// Simple type
			laneOffset = ParserHelper.parseDouble(logger,parameterLiteralValue,getTextmarker(attributeKey));
			removeResolvedParameter(attributeKey);
				
	 	}
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
		
		return result;
	}
	
	/**
	 * Making a (deep) clone this object. This is useful and used for importing elements from catalogs.
	 * @return a deep copy of the object.
	 */
	public PositionInLaneCoordinatesImpl clone()
	{
		PositionInLaneCoordinatesImpl clonedObject = new PositionInLaneCoordinatesImpl();
		cloneStartMarker(clonedObject);
		cloneEndMarker(clonedObject);
		cloneAttributeKeyToStartMarker(clonedObject);
		cloneAttributeKeyToEndMarker(clonedObject);
		cloneAttributeKeyToParameterNameMap(clonedObject);
		// clone attributes;
		// Simple type
		clonedObject.setPathS(getPathS());		
		// Simple type
		clonedObject.setLaneId(getLaneId());		
		// Simple type
		clonedObject.setLaneOffset(getLaneOffset());		
		// clone children
		return clonedObject;
	}
	
	
	
}

