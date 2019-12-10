package com.nchu.sellergoods.service;

import com.nchu.model.Brand;
import com.nchu.model.PageResult;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有的brand
     * @return
     */
    public List<Brand> findAllBrand();

    /**
     * 分页查询brand
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult<Brand> findPage(int pageNum, int pageSize);

    /**
     * 条件查询的方法
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    public PageResult<Brand> findPage(int pageNum, int pageSize, Brand brand);

    /**
     * 新增brand方法
     * @param brand
     */
    public Integer add(Brand brand);

    /**
     * 更改brand方法
     * @param brand
     * @return
     */
    public Integer update(Brand brand);

    /**
     * 根据Id查询一个brand
     * @param id
     * @return
     */
    public Brand selectOne(Long id);

    /**
     * 根据Id删除一个brand
     * @param id
     * @return
     */
    public Integer delete(Long id);

    /**
     * 删除多id的brand
     * @param ids
     * @return
     */
    public Integer delete(Long[] ids);

}
