package com.hbf.sys;

import com.hbf.jpa.BaseDao;
import com.hbf.jpa.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorService extends BaseService<Operator> {

	@Autowired
	private OperatorDao operatorDao;

	@Override
	protected BaseDao<Operator, String> getBaseDao() {
		return operatorDao;
	}

	@Override
	protected void BaseSaveCheck(Operator obj) {

	}
	
	public Operator findBySusername(String susername){
		return this.operatorDao.findBySusername(susername);
	}

}
