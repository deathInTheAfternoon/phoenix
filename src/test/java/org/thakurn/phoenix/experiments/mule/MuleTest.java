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

package org.thakurn.phoenix.experiments.mule;

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
import java.util.List;

import org.junit.Ignore;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.transport.PropertyScope;
import org.mule.client.DefaultLocalMuleClient;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.module.activiti.action.model.ProcessInstance;
import org.mule.config.spring.SpringXmlConfigurationBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Naveen Thakur
 */

 //@RunWith(SpringJUnit4ClassRunner.class)
 // Not required as we're loading via Mule libraries
 //@ContextConfiguration("classpath:org.thakurn.phoenix.experiments/mule/mule-configuration.xml")
 @Ignore
public class MuleTest {
  /*@Autowired
  private MemberBpmService bpm;*/
  final Logger logger = LoggerFactory.getLogger(MuleTest.class);

  @Autowired
  private ProcessEngine processEngine;
/*
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
*/
  @Test
  /*@Deployment(resources = {"org.thakurn.phoenix.experiments/drools_expert/drools_expert_process.bpmn20.xml",
                            "org.thakurn.phoenix.experiments/drools_expert/creditCheck.drl"})*/
  public void testMuleProcess() {
    try
    {
      MuleContext muleContext = new DefaultMuleContextFactory().createMuleContext("org.thakurn.phoenix.experiments/mule/mule-configuration.xml");
      muleContext.start();

      MuleClient muleClient = new DefaultLocalMuleClient(muleContext);
      DefaultMuleMessage message = new DefaultMuleMessage("", muleContext);

      Map<String, Object> variableMap = new HashMap<String, Object>();
      variableMap.put("var1", "hello");
      variableMap.put("processDefinitionKey", "mule_process");
      message.setProperty("createProcessParameters", variableMap , PropertyScope.OUTBOUND);

      MuleMessage responseMessage = muleClient.send("vm://create", message);

      ProcessInstance processInstance = (ProcessInstance) responseMessage.getPayload();
      assertFalse(processInstance.isEnded());
      
      RuntimeService runtimeService = (RuntimeService) muleContext.getRegistry().get("runtimeService");
      Object result = runtimeService.getVariable(processInstance.getId(), "var2");
      assertEquals("world", result);//These exceptions are swallowed at present and not visible in the log file.
      
      muleContext.stop();
      muleContext.dispose();
    }catch (Exception e){logger.debug(e.toString());}
  }

}
