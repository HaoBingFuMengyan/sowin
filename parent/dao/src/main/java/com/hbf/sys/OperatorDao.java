package com.hbf.sys;

import com.hbf.jpa.BaseDao;

public interface OperatorDao extends BaseDao<Operator, String>{

	Operator findBySusername(String susername);
	
}
