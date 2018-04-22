package com.hbf.hy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;

	public List<Member> sys(){
		System.out.println("service");
		return this.memberDao.findAll();
	}
}
