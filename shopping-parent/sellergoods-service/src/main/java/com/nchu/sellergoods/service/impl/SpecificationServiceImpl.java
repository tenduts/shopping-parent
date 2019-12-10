package com.nchu.sellergoods.service.impl;
import java.util.List;

import com.nchu.mapper.SpecificationOptionMapper;
import com.nchu.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nchu.mapper.SpecificationMapper;
import com.nchu.model.SpecificationExample.Criteria;
import com.nchu.sellergoods.service.SpecificationService;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private SpecificationMapper specificationMapper;
	@Autowired
	private SpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Specification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Specification> page=   (Page<Specification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		specificationMapper.insert(specification);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		specificationMapper.updateByPrimaryKey(specification);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		//先从第一张表查
		Specification specification=specificationMapper.selectByPrimaryKey(id);
		//设置从表查询条件
		SpecificationOptionExample example=new SpecificationOptionExample();
		SpecificationOptionExample.Criteria criteria=example.createCriteria();
		criteria.andSpecIdEqualTo(id);
		//查询出数据
		List<SpecificationOption> options=specificationOptionMapper.selectByExample(example);
		//设置值
		if(options!=null){
			specification.setOptions(options);
		}
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Specification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		SpecificationExample example=new SpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<Specification> page= (Page<Specification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
