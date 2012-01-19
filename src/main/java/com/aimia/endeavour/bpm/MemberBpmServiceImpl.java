package com.aimia.endeavour.bpm;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class MemberBpmServiceImpl implements MemberBpmService {
  
  final Logger logger = LoggerFactory.getLogger(MemberBpmServiceImpl.class);
  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;
     
  public void startBusinessProcess(String processId, Map<String, Object> bpmEngineVariables)
  {
    runtimeService.startProcessInstanceByKey(processId, bpmEngineVariables);

    Task task = taskService.createTaskQuery().singleResult();
    logger.debug("Completed Order" + task.getName());
    taskService.complete(task.getId());
    logger.debug("Process instance count = " + runtimeService.createProcessInstanceQuery().count());

  }
  
}
