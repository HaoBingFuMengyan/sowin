package com.hbf.persistence;

import com.google.common.collect.Lists;
import com.hbf.jpa.PageSumRequest;
import com.hbf.utils.B;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PageUtils {

    static public final int MaxLimit=100;
    static public final int DefaultLimit=20;
    static public final String Limit="20";
    static public PageRequest sumPage(HttpServletRequest request, int page, int size, String[] sumcols, String... sort){
        Object obj=request.getParameter("cmd_autoexport");
        if(obj!=null&&obj.toString().equals("1"))
            size=10000;
        PageSumRequest p= new PageSumRequest(page,size,toSort(sort),sumcols);
        return p;
    }
    static public PageRequest sumPage(HttpServletRequest request, int page, int size, Sort sort, String...sumcols){
        Object obj=request.getParameter("cmd_autoexport");
        if(obj!=null&&obj.toString().equals("1"))
            size=10000;
        PageSumRequest p= new PageSumRequest(page,size,sort,sumcols);
        return p;

    }
    static public PageRequest sumPage(int page, int size,String[] sumcols, String... sort){

        PageSumRequest p= new PageSumRequest(page,size,toSort(sort),sumcols);
        return p;
    }
    static public PageRequest sumPage(int page, int size,Sort sort,String...sumcols){

        PageSumRequest p= new PageSumRequest(page,size,sort,sumcols);
        return p;

    }
    private static int startToPage(int start,int limit)
    {
        return start/(limit<=0?DefaultLimit:(limit>MaxLimit?MaxLimit:limit));
    }
    static public PageRequest extpage(int start,int limit){

        PageRequest page= new PageRequest(startToPage(start,limit), limit);

        return page;

    }
    static public PageRequest page(HttpServletRequest request,int page, int size){
        Object obj=request.getParameter("cmd_autoexport");
        PageRequest p;
        if(obj!=null&&obj.toString().equals("1"))
            p= new PageRequest(0,100000);
        else
            p= new PageRequest(page,size);

        return p;

    }
    static public PageRequest page(HttpServletRequest request,int page, int size, String... sort){
        PageRequest p;
        Object obj=request.getParameter("cmd_autoexport");
        if(obj!=null&&obj.toString().equals("1"))
            p= new PageRequest(0,100000,toSort(sort));
        else
            p= new PageRequest(page,size,toSort(sort));
        return p;
    }

    /**
     * @param sorts 例子: desc_time
     * @return
     */
    static public Sort toSort(String... sorts){
        Sort ss=null;
        if(sorts != null && sorts.length>0)
        {
            List<Sort.Order> orders= Lists.newArrayList();
            for(String sort:sorts){
                if(B.Y(sort))
                    continue;
                String [] keys=sort.split("_");
                Sort.Direction direct= Sort.Direction.DESC;
                if("asc".equalsIgnoreCase(keys[0]))
                    direct= Sort.Direction.ASC;
                orders.add(new Sort.Order(direct, keys[1]));
            }
            if(orders.size() != 0)
                ss= new Sort(orders);
        }
        return ss;

    }
    static public PageRequest extpage(int start, int limit, Sort.Direction direction, String... properties){

        PageRequest page= new PageRequest( startToPage(start,limit),  limit,  direction,  properties);
        return page;

    }
    static public PageRequest page(int page, int size){

        PageRequest p= new PageRequest(page,size);

        return p;

    }
    static public PageRequest page(int page, int size, String... sort){
        PageRequest p= new PageRequest(page,size,toSort(sort));
        return p;
    }
    static public PageRequest page(int page, int size,Sort sort){

        PageRequest p= new PageRequest(page,size,sort);
        return p;

    }
}
