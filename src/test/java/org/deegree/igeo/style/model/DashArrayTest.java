/*----------------    FILE HEADER  ------------------------------------------
 This file is part of deegree.
 Copyright (C) 2001-20012 by:
 Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/deegree/
 lat/lon GmbH
 http://www.lat-lon.de

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 Contact:

 lat/lon GmbH
 Aennchenstr. 19
 53177 Bonn
 Germany
 E-Mail: info@lat-lon.de

 Prof. Dr. Klaus Greve
 Department of Geography
 University of Bonn
 Meckenheimer Allee 166
 53115 Bonn
 Germany
 E-Mail: greve@giub.uni-bonn.de
 ---------------------------------------------------------------------------*/

package org.deegree.igeo.style.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test Class for <code>DashArray</code>.
 * 
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author$
 * 
 * @version $Revision$, $Date$
 * 
 */
public class DashArrayTest {

	@Test
	public void test_equals_with_same_DashArray() {
		//arrange
		String daName = "testDashArray";
		float[] daArray = {1f, 2f};
		DashArray da = new DashArray(daName, daArray);
		//act
		//assert
		assertTrue (da.equals(da));
	}
	
	@Test
	public void test_equals_with_same_values () {
		//arrange
		String daName = "testDashArray";
		float[] daArray = {1f, 2f};
		DashArray da1 = new DashArray(daName, daArray.clone());
		DashArray da2 = new DashArray(daName, daArray.clone());
		//act
		//assert
		assertTrue (da1.equals(da2));
	}

	@Test
	public void test_equals_with_same_name_and_diffenent_arrays () {
		//arrange
		String daName = "testDashArray";
		float[] daArray1 = {1f, 2f};
		float[] daArray2 = {2f, 3f};
		DashArray da1 = new DashArray(daName, daArray1);
		DashArray da2 = new DashArray(daName, daArray2);
		//act
		//assert
		assertFalse(da1.equals(da2));
	}

	@Test
	public void test_equals_with_different_name_and_same_array () {
		//arrange
		String daName1 = "testDashArray1";
		String daName2 = "testDashArray2";
		float[] daArray = {1f, 2f};
		DashArray da1 = new DashArray(daName1, daArray);
		DashArray da2 = new DashArray(daName2, daArray);
		//act
		//assert
		assertFalse(da1.equals(da2));
	}

	@Test
	public void test_equals_with_different_values () {
		//arrange
		String daName1 = "testDashArray1";
		String daName2 = "testDashArray2";
		float[] daArray1 = {1f, 2f};
		float[] daArray2 = {1f, 2f};
		DashArray da1 = new DashArray(daName1, daArray1);
		DashArray da2 = new DashArray(daName2, daArray2);
		//act
		//assert
		assertFalse(da1.equals(da2));
	}
	
	@Test
	public void test_hashCode_with_same_DashArray() {
		//arrange
		String daName = "testDashArray";
		float[] daArray = {1f, 2f};
		DashArray da = new DashArray(daName, daArray);
		//act
		int hashCode1 = da.hashCode();
		int hashCode2 = da.hashCode();
		//assert
		assertTrue (hashCode1==hashCode2);
	}

	@Test
	public void test_hashCode_with_same_values() {
		//arrange
		String daName = "testDashArray";
		float[] daArray = {1f, 2f};
		DashArray da1 = new DashArray(daName, daArray.clone());
		DashArray da2 = new DashArray(daName, daArray.clone());
		//act
		int hashCode1 = da1.hashCode();
		int hashCode2 = da2.hashCode();
		//assert
		assertTrue (hashCode1==hashCode2);
	}

	@Test
	public void test_hashCode_with_same_name_and_different_array() {
		//arrange
		String daName = "testDashArray";
		float[] daArray1 = {1f, 2f};
		float[] daArray2 = {2f, 3f};
		DashArray da1 = new DashArray(daName, daArray1);
		DashArray da2 = new DashArray(daName, daArray2);
		//act
		int hashCode1 = da1.hashCode();
		int hashCode2 = da2.hashCode();
		//assert
		assertFalse (hashCode1==hashCode2);
	}

	@Test
	public void test_hashCode_with_different_name_and_same_array() {
		//arrange
		String daName1 = "testDashArray1";
		String daName2 = "testDashArray2";		
		float[] daArray = {1f, 2f};
		DashArray da1 = new DashArray(daName1, daArray.clone());
		DashArray da2 = new DashArray(daName2, daArray.clone());
		//act
		int hashCode1 = da1.hashCode();
		int hashCode2 = da2.hashCode();
		//assert
		assertFalse (hashCode1==hashCode2);
	}

	@Test
	public void test_hashCode_with_different_values() {
		//arrange
		String daName1 = "testDashArray1";
		String daName2 = "testDashArray2";		
		float[] daArray1 = {1f, 2f};
		float[] daArray2 = {2f, 3f};
		DashArray da1 = new DashArray(daName1, daArray1);
		DashArray da2 = new DashArray(daName2, daArray2);
		//act
		int hashCode1 = da1.hashCode();
		int hashCode2 = da2.hashCode();
		//assert
		assertFalse (hashCode1==hashCode2);
	}

}
