/**
 * 
 */
package com.aimia.endeavour.validation;

import java.util.Arrays;

import com.aimia.endeavour.validation.Message;
import com.aimia.endeavour.validation.ReportFactory;
import com.aimia.endeavour.validation.ValidationReport;
import com.aimia.endeavour.validation.Message.Type;

/**
 * @author miba
 * 
 */
// @extract-start 03 55
public class DefaultReportFactory implements ReportFactory {
  public Message createMessage(Message.Type type, 
      String messageKey, Object... context) {
    return new DefaultMessage(type, messageKey, Arrays
        .asList(context));
  }
  
  public ValidationReport createValidationReport() {
    return new DefaultValidationReport();
  }
}
// @extract-end
