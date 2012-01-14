package com.aimia.endeavour.bpm;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.drools.runtime.StatefulKnowledgeSession;

public class MemberBpmServiceImpl implements  MemberBpmService {
  
  final Logger logger = LoggerFactory.getLogger(MemberBpmServiceImpl.class);
  private StatefulKnowledgeSession jbpmSession;
    
  public void setJbpmSession(StatefulKnowledgeSession jbpmSession)
  {
    this.jbpmSession = jbpmSession;
  }

  public void startBusinessProcess(String processId, Map<String, Object> jbpmVariables)
  {
    logger.debug("Calling BPM Process " +  processId);
    jbpmVariables.put("logger", logger);
    jbpmSession.startProcess(processId, jbpmVariables); //todo: does dispose need to be called?
  }
  
}
