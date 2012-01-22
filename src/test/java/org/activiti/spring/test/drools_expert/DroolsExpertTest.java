/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.activiti.spring.test.drools_expert;

import static org.junit.Assert.assertEquals;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.activiti.spring.impl.test.SpringActivitiTestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

import org.junit.Ignore;

/**
 * @author Naveen Thakur
 */
 @Ignore
 @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org/activiti/spring/test/drools_expert/beans.xml")
public class DroolsExpertTest {
  /*@Autowired
  private MemberBpmService bpm;*/
  final Logger logger = LoggerFactory.getLogger(DroolsExpertTest.class);

  @Autowired
  private ProcessEngine processEngine;

  @Autowired 
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

  @Autowired
  @Rule
  public ActivitiRule activitiRule;

  @After
  public void closeProcessEngine() {
    // Required, since all the other tests seem to do a specific drop on the end 
    processEngine.close();
  }

  @Test
  /*@Deployment(resources = {"org/activiti/spring/test/drools_expert/drools_expert_process.bpmn20.xml",
                            "org/activiti/spring/test/drools_expert/creditCheck.drl"})*/
  public void testSimpleProcess() {
    Map<String, Object> vars = new HashMap<String, Object>();
    vars.put("logger", logger);
    vars.put("applicant", new LoanApplicant(50));
    runtimeService.startProcessInstanceByKey("drools_expert_process", vars);
    //bpm.startBusinessProcess("simpleProcess", null);
   
  }

}
