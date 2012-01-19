package com.aimia.endeavour.member;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.aimia.endeavour.bpm.MemberBpmService;
import com.aimia.endeavour.member.MemberDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.jboss.resteasy.annotations.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.drools.agent.KnowledgeAgent;
import org.drools.runtime.StatelessKnowledgeSession;

/**
* This class is used to map incoming REST requests to service calls.
**/
@Path("/members")
public class MemberREST{
	final Logger logger = LoggerFactory.getLogger(MemberREST.class);
  	private MemberService memberService;
  	private MemberBpmService memberBpmService;

  	@GET
  	public List<MemberDTO> getMembers()
  	{
  		return memberService.getMembers();
  	}

	@GET
	@Path("/member/{id}")
	public MemberDTO getMember(@PathParam("id") String name)
	{
		return memberService.find(name);//todo: for now we return the name. And id is the 'name'.
	}

	@POST
	@Path("/member/{id}")
	@Consumes("text/plain")
	public void createMember(@PathParam("id") String id, String body){
		Map<String, Object> jbpmVariables = new HashMap<String, Object>();

		MemberDTO prospect = new MemberDTO();
		prospect.setName(body);
    	jbpmVariables.put("memberDTO", prospect);  
    	  
		memberBpmService.startBusinessProcess("member", jbpmVariables);
		// Push the following into a BPM task.
		
		if (memberService.create(prospect))
			logger.info("Created member:" + id + " name: " + body);
		else
			logger.info("Create Member failed for name " + body);
	}

	public void setMemberService(MemberService memberService){this.memberService = memberService;}
	public void setMemberBpmService(MemberBpmService bpmService){this.memberBpmService = bpmService;}
}