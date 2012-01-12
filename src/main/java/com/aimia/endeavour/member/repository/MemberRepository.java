package com.aimia.endeavour.member.repository;

import com.aimia.endeavour.member.Member;
import java.util.List;

public interface MemberRepository {
	public Member getMember(String userId);
	public List<Member> getMembers();
}