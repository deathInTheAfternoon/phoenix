package com.aimia.endeavour.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.drools.agent.KnowledgeAgent;
import org.drools.RuleBase;
import org.drools.StatelessSession;
import org.drools.runtime.StatefulKnowledgeSession;

import com.aimia.endeavour.member.*;

public interface MemberValidationService {

  /**
   * validates provided customer and returns validation report
   */
  public ValidationReport validate(Member member);
  public void setKnowledgeAgent(KnowledgeAgent kAgent);
  
}
