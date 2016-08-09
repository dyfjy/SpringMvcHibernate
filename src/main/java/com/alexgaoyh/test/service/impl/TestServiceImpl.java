package com.alexgaoyh.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alexgaoyh.test.dao.TestDao;
import com.alexgaoyh.test.entity.Test;
import com.alexgaoyh.test.service.TestService;
import com.alexgaoyh.common.dao.BaseDao;
import com.alexgaoyh.common.service.impl.BaseServiceImpl;

/**
 * 
 * @desc 测试service接口实现类
 *
 * @author dyf
 * @Tue Aug 09 13:18:14 CST 2016
 */
@Service
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {

	@Override
	@Resource(name ="testDaoImpl")
	public void setBaseDao(BaseDao<Test> dao) {
		this.baseDao =  dao;
	}
	
	private TestDao getDao(){
		return (TestDao) this.baseDao ;
	}
	


}
