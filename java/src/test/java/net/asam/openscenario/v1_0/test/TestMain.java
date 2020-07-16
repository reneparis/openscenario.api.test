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
 
package net.asam.openscenario.v1_0.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.asam.openscenario.v1_0.main.OpenScenarioChecker;

public class TestMain extends TestBase {


	public void testImportSuccess() {
		OpenScenarioChecker.main(new String[] { getResourceFile(
				 "simpleImport/simpleImport.xosc").getAbsolutePath() });

	}
	
	@Test
	public void testFileNoteFound() {
      OpenScenarioChecker.main(new String[] { "testFileNoteFound"});
      Assertions.assertEquals("Scenario file not found 'testFileNoteFound'", getLine(testOut.toString(), 4));
	}
	@Test
	public void testWithErrors()
	{
		String filename = getResourceFile( "DoubleLaneChangerParamsError.xosc").getAbsolutePath();
		OpenScenarioChecker.main(new String[] {filename}
		);
		Assertions.assertEquals("Validation failed with 2 errors and 0 warnings.", getLine(testOut.toString(), 10));
	}
	
	
	
	@Test
    public void testWrongCommandLine()
    {
        String filename = getResourceFile( "DoubleLaneChangerParamsError.xosc").getAbsolutePath();
        OpenScenarioChecker.main(new String[] {filename, "Test"}
        );
        Assertions.assertEquals("Usage: [inputfile] [-paramsfile injectedParameterFile]|[-v]", getLine(testOut.toString(), 4));
        
    }
	
	@Test
    public void testWithParamFile()
    {
        String filename = getResourceFile("DoubleLaneChangerInjectedParams.xosc").getAbsolutePath();
        String paramFileName = getResourceFile("params.conf").getAbsolutePath();
        OpenScenarioChecker.main(new String[] {filename, "-paramsfile", paramFileName}
        );
        Assertions.assertTrue(getLine(testOut.toString(), 4).startsWith("Checking"));
        

    }
	
	@Test
    public void testWithParamFileSyntaxError()
    {
	  // test stuff here...

        String filename = getResourceFile("DoubleLaneChangerInjectedParams.xosc").getAbsolutePath();
        String paramFileName = getResourceFile("paramsSyntaxError.conf").getAbsolutePath();
        OpenScenarioChecker.main(new String[] {filename, "-paramsfile", paramFileName}
        );
      Assertions.assertEquals("Syntax error in parameter file: line 8", getLine(testOut.toString(), 4));
      
    }
	
	@Test
    public void testWithParamFileSyntaxError2()
    {
      // test stuff here...

        String filename = getResourceFile("DoubleLaneChangerInjectedParams.xosc").getAbsolutePath();
        String paramFileName = getResourceFile("paramsSyntaxError2.conf").getAbsolutePath();
        OpenScenarioChecker.main(new String[] {filename, "-paramsfile", paramFileName}
        );
      Assertions.assertEquals("Syntax error in parameter file: line 5", getLine(testOut.toString(), 4));
      
    }
	
	@Test
    public void testWithParamFileNotFound()
    {
      // test stuff here...
      String filename = getResourceFile("DoubleLaneChangerInjectedParams.xosc").getAbsolutePath();
      String paramFileName = "paramsNotFound.conf";
      OpenScenarioChecker.main(new String[] {filename, "-paramsfile", paramFileName});

      Assertions.assertEquals("paramsfile not found", getLine(testOut.toString(), 4));
      
    }
	
	// Use these lines to make stout visible
    //System.setOut(stdout);
    //System.out.println(testOut.toString());

	
	
}
