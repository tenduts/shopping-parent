package com.nchu.solr.controller;

import com.github.pagehelper.Page;
import com.nchu.model.Item;
import com.nchu.model.Result;
import com.nchu.solr.service.ItemSolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class SolrInitialController {
    @Autowired
    private ItemSolrService itemSolrService;
    @Autowired
    private SolrTemplate solrTemplate;
    @RequestMapping("/initSolr")
    @ResponseBody
    public Result initSolr(){
        Result result=null;
        try {
            int i=0;
            Long total = 100L;
            Page<Item> items=null;
            do {
                items= itemSolrService.getPageItems(0, 100);
                i+=100;
                total=items.getTotal();
                List<Item> itemList = items.getResult();
                for (Item item : itemList) {
                    solrTemplate.saveBean("testcore", item);
                }
                solrTemplate.commit("testcore");
            }while (i<total);
            result=new Result(true,"solr初始化成功！");
        }catch (Exception e){
            e.printStackTrace();
            result=new Result(false,"solr初始化失败");
        }finally {
            return result;
        }
    }
}
