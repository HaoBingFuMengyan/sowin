package com.hbf.jpa;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageSumRequest extends PageRequest {
    private String[] sumcols;

    public String[] getSumcols() {
        return sumcols;
    }

    public void setSumcols(String[] sumcols) {
        this.sumcols = sumcols;
    }

    /**
     *
     */
    private static final long serialVersionUID = 4650178705623319980L;

    public PageSumRequest(int page, int size,String[] sumcols) {
        super(page,size);
        this.sumcols=sumcols;
    }

    /**
     * Creates a new {@link PageSumRequest} with sort parameters applied.
     *
     * @param page
     * @param size
     * @param direction
     * @param properties
     */
    public PageSumRequest(int page, int size, Sort.Direction direction, String[] sumcols, String... properties) {

        super(page,size,direction,properties);
        this.sumcols=sumcols;
    }

    /**
     * Creates a new {@link PageSumRequest} with sort parameters applied.
     *
     * @param page
     * @param size
     * @param sort
     */
    public PageSumRequest(int page, int size, Sort sort,String[] sumcols) {

        super(page,size,sort);
        this.sumcols=sumcols;

    }
}
