package com.aimia.endeavour.member.repository;

import com.aimia.endeavour.member.Member;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberRepositoryImpl implements MemberRepository {
	private MemberMapper memberMapper;
	final Logger logger = LoggerFactory.getLogger(MemberRepositoryImpl.class);

	public Member getMember(String userId){
		return memberMapper.getMember(userId);	
	}

	public List<Member> getMembers(){
		return memberMapper.getMembers();
	}

	public void setMemberMapper(MemberMapper memberMapper){
		this.memberMapper = memberMapper;
	}
}