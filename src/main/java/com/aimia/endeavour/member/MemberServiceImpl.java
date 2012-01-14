package com.aimia.endeavour.member;

import java.util.*;
import com.aimia.endeavour.validation.*;
import com.aimia.endeavour.member.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dozer.DozerBeanMapper;
/**
* Provides services for managing program Members. This prototype uses the Member name as a unique key.
*/
public class MemberServiceImpl implements MemberService
{
	final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	private DozerBeanMapper mapper;
	private Map<String, Member> members = new HashMap<String, Member>();
	private MemberValidationService memberValidationService;
	private MemberRepository memberRepository;

	public MemberServiceImpl()
	{
		//todo: dummy data for testing
		Member test = new Member();
		test.setName("Kulio");
		members.put(test.getName(), test);
	}
	// Create a member using 'name' as a key. return is false if member wasn't created.
	public boolean create(MemberDTO prospect){
		//mapping layer
		Member newbie = mapper.map(prospect, Member.class);

		ValidationReport report = memberValidationService.validate(newbie);
		Set<Message> messages = report.getMessages();
		if (messages.size() > 0)
		{
			logger.debug("validation returned the following " + messages.size() + " errors:");
			for (Message m : messages)
			{
				logger.debug("==> " + m);
			}
			return false;
		}
		
		members.put(newbie.getName(), newbie);
		return true;
	}

	// Find the member with 'name' as its key.
	public MemberDTO find(String name)
	{
		// todo: adding myBatis persistence
		Member m = memberRepository.getMember("Kulio");
		MemberDTO ret = mapper.map(m, MemberDTO.class);
		return ret;
	}

	public List<MemberDTO> getMembers()
	{
		List<Member> memberList = memberRepository.getMembers();
		// todo: have to use a loop? Would modelmapper better?
		ArrayList<MemberDTO> ret = new ArrayList<MemberDTO>();
		for (Member m : memberList)
		{
			ret.add(mapper.map(m, MemberDTO.class));
		}
		return ret;
	}
	public void setBeanMapper(DozerBeanMapper mapper){this.mapper = mapper;}
	public void setMemberValidationService(MemberValidationService mvs){this.memberValidationService = mvs;}
	public void setMemberRepository(MemberRepository mr){this.memberRepository = mr;}
  
}
