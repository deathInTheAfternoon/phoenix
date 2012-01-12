package com.aimia.endeavour.member;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "member")
public class MemberDTO {
	private String name = "No One";

	@XmlElement
	public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
}