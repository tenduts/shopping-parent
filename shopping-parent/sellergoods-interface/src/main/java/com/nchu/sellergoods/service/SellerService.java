package com.nchu.sellergoods.service;
import java.util.List;
import com.nchu.model.Seller;

import com.nchu.model.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SellerService {

	/**
	 * 登录方法
	 */
	public Seller login(Seller seller);
	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Seller> findAll();

	/**
	 * 商家审核
	 * @param sellerId
	 * @param status
	 */
	public void updateStatus(String sellerId,String status);
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Seller seller);
	
	
	/**
	 * 修改
	 */
	public void update(Seller seller);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Seller findOne(String id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(Seller seller, int pageNum, int pageSize);
	
}
