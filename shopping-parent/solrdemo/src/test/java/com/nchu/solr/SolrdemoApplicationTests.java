package com.nchu.solr;

import com.nchu.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrdemoApplicationTests {
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void addItem() {
        Item item=new Item();
        item.setId(536565L);
        item.setTitle("new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待");
        item.setSellPoint("下单送12000毫安移动电源！双3.5英寸魔焕炫屏，以非凡纵观天下时局，尊崇翻盖设计，张弛中，尽显从容气度！");
        item.setCategory("手机");
        item.setSpec("{\"机身内存\":\"16G\",\"网络\":\"联通3G\"}");
        item.setSeller("阿尔卡特");
        solrTemplate.saveBean("testcore",item);
        solrTemplate.commit("testcore");
    }
    @Test
    public void addItems10000() {
        List<Item> items=new ArrayList<>();
        Item item=null;
        for (int i=0;i<20001;i++) {
            item = new Item();
            item.setId(2000+i+536565L);
            item.setTitle("夏普（SHARP）LCD-46DS40A 46英寸 日本原装液晶面板 智能全高清液晶电视");
            item.setSellPoint("要好屏，选夏普！日本原装面板，智能电视，高画质高音质！<a  target=\"blank\"  href=\"http://item.jd.com/1278686.html\">还有升级版安卓智能新机46DS52供您选择！</a>");
            item.setCategory("平板电视");
            item.setBrand("夏普");
            item.setSpec("{\"电视屏幕尺寸\":\"46英寸\"}");
            item.setSeller("阿尔卡特");

            //items.add(item);
            solrTemplate.saveBean("testcore", item);
        }
        solrTemplate.commit("testcore");
    }

    @Test
    public void deleteItemById(){

        solrTemplate.deleteByIds("testcore","537565");
        solrTemplate.commit("testcore");
    }

    @Test
    public void query(){
        List<Item> itemList=null;
        Query query=new SimpleQuery();
        Criteria criteria = new Criteria("title").contains("联通");
        query.addCriteria(criteria);
        query.setOffset(20L);
        query.setRows(1);
        ScoredPage<Item> page=solrTemplate.query("testcore",query,Item.class);
         System.out.println(page.getContent().toString());
    }
    @Test
    public void queryHighLight(){
        HighlightQuery query=new SimpleHighlightQuery();
        HighlightOptions options = new HighlightOptions().addField("title");
        // 设置高亮前缀,后缀
        options.setSimplePrefix("<em style='color:red'>");
        options.setSimplePostfix("</em>");
        // 绑定高亮域跟高亮查询
        query.setHighlightOptions(options);

        Criteria criteria = new Criteria("title").contains("联通");
        query.addCriteria(criteria);
        query.setOffset(20L);
        query.setRows(1);
        HighlightPage<Item> page = solrTemplate.queryForHighlightPage("testcore",query, Item.class);
        List<HighlightEntry<Item>> highlighted = page.getHighlighted(); //
        for (HighlightEntry<Item> highlightEntry : highlighted) {
            // 遍历高亮入口集合,得到高亮入口对象
            Item entity = highlightEntry.getEntity(); // 获取原实体对象,需要往原实体对象的title中设置高亮数据
            List<HighlightEntry.Highlight> highlights = highlightEntry.getHighlights();
            // 获取单个document中的所有高亮数据集合,因为有可能HighlightOptions().addField("item_title")不只addFiled一个域
            if (highlights != null && highlights.size() > 0) {
                // 默认选中第一个域,即item.title
                HighlightEntry.Highlight highlight = highlights.get(0);// 终于得到了一个高亮对象,但是有可能这条数据多次匹配了高亮数据,比如搜索手机,苹果手机 防水手机
                if (highlight.getSnipplets() != null && highlight.getSnipplets().size() > 0) {
                    String title = highlight.getSnipplets().get(0);
                    System.out.println(title);
                    // 设置高亮结果
                    entity.setTitle(title); // 由于这是引用数据类型,在查询结果内部进行修改,那么原参数自然会被修改
                }
            }
        }
        System.out.println(page.getContent());
    }
    @Test
    public void testDeleteAll(){
        Query query=new SimpleQuery("*:*");
        solrTemplate.delete("testcore",query);
        solrTemplate.commit("testcore");
    }


}
