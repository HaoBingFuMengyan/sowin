package com.hbf.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorService {

	@Autowired
	private OperatorDao operatorDao;
	
	
	public Operator findBySusername(String susername){
		return this.operatorDao.findBySusername(susername);
	}
}
