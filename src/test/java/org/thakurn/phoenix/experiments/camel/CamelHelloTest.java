package org.thakurn.phoenix.experiments.camel;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.Ignore;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:org/thakurn/phoenix/experiments/camel/application-context.xml")
public class CamelHelloTest {

	@Autowired
	private CamelContext camelContext;

	@Autowired
  	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Test
	public void simpleProcessTest() {
    ProducerTemplate tpl = camelContext.createProducerTemplate();
    String instanceId = (String) tpl.requestBody("direct:start", Collections.singletonMap("var1", "hello"));
    assertEquals("world", runtimeService.getVariable(instanceId, "var2"));
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("HelloTask", task.getName());
		taskService.complete(task.getId());
	}
}
