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

package org.thakurn.phoenix.experiments.email;

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

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import javax.mail.internet.MimeMessage;

/**
 * @author Naveen Thakur
 */
 @Ignore
 @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org.thakurn.phoenix.experiments/email/beans.xml")
public class EmailTest {
  /*@Autowired
  private MemberBpmService bpm;*/
  final Logger logger = LoggerFactory.getLogger(EmailTest.class);

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
  /*@Deployment(resources = {"org.thakurn.phoenix.experiments/drools_expert/drools_expert_process.bpmn20.xml",
                            "org.thakurn.phoenix.experiments/drools_expert/creditCheck.drl"})*/
  public void testRestProcess() {

    // Initialise the embedded email engine
    Wiser wiser = new Wiser();
    wiser.setPort(1025);
    wiser.start();

    Map<String, Object> vars = new HashMap<String, Object>();
    vars.put("logger", logger);
    runtimeService.startProcessInstanceByKey("email_process", vars);
    //bpm.startBusinessProcess("simpleProcess", null);
   try
   {
     List<WiserMessage> messages = wiser.getMessages();
     assertEquals(1, messages.size());
     WiserMessage message = messages.get(0);
     MimeMessage mimeMessage = message.getMimeMessage();
     assertEquals("Hello Naveen", mimeMessage.getHeader("Subject", null));
     wiser.stop();
  }catch(javax.mail.MessagingException e){;}
  }

}
