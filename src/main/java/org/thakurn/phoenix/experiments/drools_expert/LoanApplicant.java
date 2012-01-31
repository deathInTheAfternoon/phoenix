package org.thakurn.phoenix.experiments.drools_expert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


public class LoanApplicant implements Serializable {
  
  final Logger logger = LoggerFactory.getLogger(LoanApplicant.class);
  private Integer income;
  private Boolean creditOk;

  public LoanApplicant(Integer income){
    this.income = income;
  }

  public void setIncome(Integer income)
  {
    this.income = income;
  }

  public Integer getIncome()
  {
    return income;
  }

  public void setCreditOk(Boolean ok)
  {
    logger.debug("Xxxxxxxxxxxxxxxxxxxxxxxxxxxx SETTING CREDIT RATING TO: " + ok);
    this.creditOk = ok;
  }

  public Boolean getCreditOk()
  {
    return creditOk;
  }
  
}
