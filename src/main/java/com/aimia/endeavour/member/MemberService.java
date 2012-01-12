package com.aimia.endeavour.member;

import java.util.List;

/**
* Provides services for managing program Members. This prototype uses the Member name as a unique key.
*/
public interface MemberService 
{
  // Create a member using 'name' as a key.
  public boolean create(MemberDTO prospect);
  // Find the member with 'name' as its key.
  public MemberDTO find(String name);
  // Get a list of Members. todo: evolve to paging result set.
  public List<MemberDTO> getMembers();
  
}