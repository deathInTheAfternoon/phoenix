package org.thakurn.phoenix.experiments.bpm;

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
     
  public void startBusinessProcess(String processId, Map<String, Object> bpmEngineVariables)
  {
  	bpmEngineVariables = new HashMap<String, Object>();
  	bpmEngineVariables.put("logger", logger);
    runtimeService.startProcessInstanceByKey(processId, bpmEngineVariables);
    //runtimeService.startProcessInstanceByKey(processId);
  }
  
}
