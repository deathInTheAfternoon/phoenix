package com.aimia.endeavour.member.repository;

import com.aimia.endeavour.member.Member;
import java.util.List;

public interface MemberMapper {
	Member getMember(String name);
	List<Member> getMembers();
}