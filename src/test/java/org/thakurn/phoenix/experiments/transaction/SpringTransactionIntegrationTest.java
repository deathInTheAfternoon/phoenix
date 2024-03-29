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

package org.thakurn.phoenix.experiments.transaction;

import javax.sql.DataSource;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Ignore;

/**
 * @author Tom Baeyens
 */
 @Ignore
@ContextConfiguration("classpath:org/thakurn/phoenix/experiments/transaction/SpringTransactionIntegrationTest-context.xml")
public class SpringTransactionIntegrationTest extends SpringActivitiTestCase {
  
  @Autowired
  protected UserBean userBean;
  
  @Autowired
  protected DataSource dataSource;

  @Deployment
  public void testBasicActivitiSpringIntegration() {
    userBean.hello();
    
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().singleResult();
    assertEquals("Hello from Printer!", runtimeService.getVariable(processInstance.getId(), "myVar"));
  }
  
  @Deployment
  public void testRollbackTransactionOnActivitiException() {
    
    // Create a table that the userBean is supposed to fill with some data
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.execute("create table MY_TABLE (MY_TEXT varchar);");
    
    // The hello() method will start the process. The process will wait in a user task
    userBean.hello();
    assertEquals(0, jdbcTemplate.queryForLong("select count(*) from MY_TABLE"));
    
    // The completeTask() method will write a record to the 'MY_TABLE' table and complete the user task
    try {
      userBean.completeTask(taskService.createTaskQuery().singleResult().getId());
      fail();
    } catch (Exception e) { }
    
    // Since the service task after the user tasks throws an exception, both 
    // the record and the process must be rolled back !
    assertEquals("My Task", taskService.createTaskQuery().singleResult().getName());
    assertEquals(0, jdbcTemplate.queryForLong("select count(*) from MY_TABLE"));
    
    // Cleanup
    jdbcTemplate.execute("drop table MY_TABLE if exists;");
  }
  
  
}
