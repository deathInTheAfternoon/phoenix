package org.thakurn.phoenix.experiments.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.activiti.engine.delegate.*;

import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.*;
import org.apache.http.util.*;
import java.io.*;
import java.lang.Exception;

/**
* This class is dependent upon the Activiti framework. Eventually it should be encapsulated so 
* we can switch between JBPM and Activiti. Adiitionally, it doesn't belong in the domain model anyhow.
**/
public class JavaDelegateSmsService implements JavaDelegate {
  
  final Logger logger = LoggerFactory.getLogger(JavaDelegateSmsService.class);
  private String url;

  @Override
  public void execute(DelegateExecution execution)
  {
    logger.debug("Calling JavaDelegateSmsService::execute");
    HttpClient client = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet("http://api.clickatell.com/http/sendmsg?user=naveen.thakur&password=Endeavour123&api_id=3356098&to=447807577370&text=Message");
    try
    {
      HttpResponse response = client.execute(httpGet);
      HttpEntity entity = response.getEntity();
      if(entity != null)
      {
        InputStream input = entity.getContent();
        long l = entity.getContentLength();
        if (l != -1 && l < 2048)
          logger.debug(EntityUtils.toString(entity));
      }
    }catch (Exception e){logger.debug(e.toString());}
    return;
  }
  
}
