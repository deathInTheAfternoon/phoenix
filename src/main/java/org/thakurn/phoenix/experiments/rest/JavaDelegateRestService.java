package org.thakurn.phoenix.experiments.rest;

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
public class JavaDelegateRestService implements JavaDelegate {

  final Logger logger = LoggerFactory.getLogger(JavaDelegateRestService.class);
  private String url;

  @Override
  public void execute(DelegateExecution execution)
  {
    logger.debug("Calling JavaDelegateRestService::execute");
    HttpClient client = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet("http://www.thomas-bayer.com/sqlrest/CUSTOMER/18");
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
