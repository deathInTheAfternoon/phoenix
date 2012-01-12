package com.aimia.endeavour.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Member{
	final Logger logger = LoggerFactory.getLogger(Member.class);
	private Long id;
	private String name = "No One";

	public Long getId(){return this.id;}
	public void setId(Long id){this.id = id;}
	public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
}