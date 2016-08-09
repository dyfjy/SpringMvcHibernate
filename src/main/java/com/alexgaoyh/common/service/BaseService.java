package com.alexgaoyh.common.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.alexgaoyh.common.dao.BaseDao;
import com.alexgaoyh.common.entity.BaseEntity;
import com.alexgaoyh.common.util.Pagination;

public interface BaseService<E extends BaseEntity> {
	
	
	BaseDao<E> getBaseDao();
	
	void setBaseDao(BaseDao<E> dao);

	/**
	 * 
	 * @param entity
	 * @throws ValidateException 
	 */
	void saveOrUpdate(E entity) throws Exception;
	
	/**
	 * 根据orderBy 排序查询所有实体信息
	 * @param orderBy
	 * @return
	 */
	List<E> getAll(String orderBy);
	
	/**
	 * 查询所有实体信息
	 * @return
	 */
	List<E> getAll();
	
	/**
	 * 根据主键获取对应实体信息
	 * @param pid
	 * @return
	 */
	E get(Integer pid);
	
	void evict(Object entity);

	/**
	 * 后台easyui-datagrid 数据查询
	 * @param condition
	 * @param page
	 * @param rows
	 * @return
	 */
	Pagination<E> getPageData(DetachedCriteria condition, int page, int rows);
	
	/**
	 * 保存 entity
	 * 
	 * @param entity
	 * @throws ValidateException 
	 */
	void save(E entity) throws Exception;
	
	/**
	 * 实体更新
	 * @param entity
	 * @throws Exception
	 */
	void update(E entity) throws Exception;
	
	/**
	 * 逻辑删除，入参为pid主键数组
	 * @param pidArray
	 * @throws Exception
	 */
	void deleteLogicByIds(String[] pidArray) throws Exception;
}
