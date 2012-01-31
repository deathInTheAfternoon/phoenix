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

package org.thakurn.phoenix.experiments.bpm;

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

import org.thakurn.phoenix.experiments.bpm.*;

import org.junit.Ignore;

/**
 * @author Naveen Thakur
 */
@Ignore
 @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org.thakurn.phoenix.experiments/bpm/beans.xml")
public class BpmServiceTest {
  @Autowired
  private MemberBpmService bpm;

  @Autowired
  private ProcessEngine processEngine;

  @Autowired 
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

  @After
  public void closeProcessEngine() {
    // Required, since all the other tests seem to do a specific drop on the end 
    processEngine.close();
  }

  @Test
  //@Deployment(resources = {"org.thakurn.phoenix.experiments/bpm/BpmServiceTest.testSimpleProcess.bpmn20.xml"})
  public void testSimpleProcess() {
    //runtimeService.startProcessInstanceByKey("simpleProcess");
    bpm.startBusinessProcess("simpleProcess", null);
    Task task = taskService.createTaskQuery().singleResult();
    assertEquals("My Task", task.getName());
  
    taskService.complete(task.getId());
    assertEquals(0, runtimeService.createProcessInstanceQuery().count());
   
  }

}
