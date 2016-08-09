package com.alexgaoyh.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alexgaoyh.common.entity.BaseEntity;

/**
 * 
 * @desc 测试实体
 *
 * @author dyf
 * @Tue Aug 09 13:18:14 CST 2016
 */
@Entity
@Table(name="test_entity")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Test extends BaseEntity{
	@Column(nullable=false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}