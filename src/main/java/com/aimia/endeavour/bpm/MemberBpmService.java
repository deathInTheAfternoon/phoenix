package com.aimia.endeavour.bpm;

import java.util.Map;
import org.drools.runtime.StatefulKnowledgeSession;

public interface MemberBpmService {
  public void startBusinessProcess(String processId, Map<String, Object> bpmEngineVariables);  
}
