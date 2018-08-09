/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author Michael Isvy
 * <p/>
 * Also see how a view that resolves to "error" has been added ("error.html").
 */
@Controller
class CrashController {

	@RequestMapping(value = "/oups", method = RequestMethod.GET)
	public String triggerException() {
		throw new RuntimeException("Expected: controller used to showcase what "
				+ "happens when an exception is thrown");
	}





	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public String stopMachine() {
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r
					.exec("sudo kill -9 $(ps -eaf | grep spring-petclinic-1.5.1.jar | grep -v grep | awk '{print $2}') | true");
			p.waitFor();
			BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while ((line = b.readLine()) != null) {
				System.out.println(line);
			}
			b.close();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

}
