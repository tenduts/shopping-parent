package com.nchu.solr.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nchu.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemSolrService {
    public List<Item> getAllItems();
    public Page<Item> getPageItems(Integer pageNo, Integer pageSize);
}
