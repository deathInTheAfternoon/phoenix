package com.aimia.endeavour.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.drools.agent.KnowledgeAgent;
import org.drools.RuleBase;
import org.drools.StatelessSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.event.rule.DebugWorkingMemoryEventListener;//note: used for debugging only.

import com.aimia.endeavour.member.*;

// @extract-start 03 66
public class MemberValidationServiceImpl implements  MemberValidationService {
  final Logger logger = LoggerFactory.getLogger(MemberValidationServiceImpl.class);
  private KnowledgeAgent kAgent;
  private StatefulKnowledgeSession jbpmSession;
  private ReportFactory reportFactory;
  //private ReferenceDataService refDataService;
  
  /**
   * validates provided customer and returns validation report
   */
  public ValidationReport validate(Member member) {
    logger.debug("ValidationReport::validate(" + member.getName() + ")");
    ValidationReport report = reportFactory.createValidationReport();
    StatelessKnowledgeSession session = kAgent.getKnowledgeBase().newStatelessKnowledgeSession();
    session.addEventListener( new DebugWorkingMemoryEventListener() );
    session.setGlobal("validationReport", report);
    session.setGlobal("reportFactory", reportFactory);
    //session.setGlobal("refData", refDataService);
    logger.debug("ValidationReport::validate calling session.execute(member)");
    session.execute(member);
    return report;
  }

  /**
   * @return facts that the rules will reason upon
   */
  /*private Collection<Object> getFacts(Customer customer) {
    ArrayList<Object> facts = new ArrayList<Object>();
    facts.add(customer);
    facts.add(customer.getAddress());
    facts.addAll(customer.getAccounts());
    return facts;
  }*/
  
  public void setKnowledgeAgent(KnowledgeAgent kAgent) {
    this.kAgent = kAgent;
  }
  
  public void setReportFactory(ReportFactory reportFactory) {
    this.reportFactory = reportFactory;
  }
  
  /*public void setBankingInquiryService(
      BankingInquiryService bankingInquiryService) {
    this.bankingInquiryService = bankingInquiryService;
  }*/
}
