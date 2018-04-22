package com.hbf.jpa;

import com.google.common.collect.Maps;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public class SumPageImpl<T> extends PageImpl<T> {

    private Map<String,Object> sum= Maps.newHashMap();


    public Map<String, Object> getSum() {
        return sum;
    }

    public void setSum(Map<String, Object> sum) {
        this.sum = sum;
    }

    public SumPageImpl(List<T> content) {
        super(content);
    }

    public SumPageImpl(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    /**
     *
     */
    private static final long serialVersionUID = -3047501096477184547L;

}
