package com.nchu.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nchu.search.service.ItemSearchService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins  = {"http://10.10.84.102:8040"},allowCredentials = "true")
public class ItemSearchController {
    @Reference
    private ItemSearchService itemSearchService;
    @RequestMapping("search")
    public Map<String,Object> search(@RequestBody Map searchMap, HttpServletResponse response){
//        response.setHeader("Access-Control-Allow-Origin","http://10.10.84.102:8040");
//        response.setHeader("Access-Control-Allow-Credentials","true");

        Map<String,Object> resultMap=new HashMap<>();
        System.out.println(searchMap);
        resultMap=itemSearchService.search(searchMap);
        return resultMap;
    }
}
