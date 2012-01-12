/**
 * 
 */
package com.aimia.endeavour.validation;

import java.util.Date;

import org.drools.runtime.KnowledgeRuntime;
import org.drools.runtime.rule.RuleContext;
import org.joda.time.DateMidnight;
import org.joda.time.Years;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author miba
 * 
 */
// @extract-start 03 23
public class ValidationHelper {
  static final Logger logger = LoggerFactory.getLogger(ValidationHelper.class);

  /**
   * adds an error message to the global validation report
   * @param drools RuleContext that is accessible from
   *  rule condition
   * @param context for the message
   */
  public static void error(RuleContext drools,
      Object... context) {

    logger.info("ValidationHelper::error in rule '" + drools.getRule().getName() + "'");
    KnowledgeRuntime knowledgeRuntime = drools
        .getKnowledgeRuntime();
    ValidationReport validationReport = (ValidationReport) 
        knowledgeRuntime.getGlobal("validationReport");
    ReportFactory reportFactory = (ReportFactory) 
        knowledgeRuntime.getGlobal("reportFactory");

    validationReport.addMessage(reportFactory.createMessage(
        Message.Type.ERROR, drools.getRule().getName(),
        context));
  }
  // @extract-end
  
  public static void warning(RuleContext drools,
      Object... context) {
    KnowledgeRuntime knowledgeRuntime = drools.getKnowledgeRuntime();
    ValidationReport validationReport = (ValidationReport) knowledgeRuntime
        .getGlobal("validationReport");
    ReportFactory reportFactory = (ReportFactory) knowledgeRuntime
        .getGlobal("reportFactory");

    validationReport.addMessage(reportFactory.createMessage(
        Message.Type.WARNING, drools.getRule().getName(),
        context));
  }

  // @extract-start 03 64
  /**
   * @return number of years between today and specified date 
   */
  public static int yearsPassedSince(Date date) {
    return Years.yearsBetween(new DateMidnight(date),
        new DateMidnight()).getYears();
  }
  // @extract-end

}
