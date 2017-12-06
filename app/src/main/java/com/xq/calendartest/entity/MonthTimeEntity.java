package com.xq.calendartest.entity;

import zzbcar.cckj.com.nzzb.bean.MonthPriceBean;

/**
 * Created by Administrator on 2017/7/17.
 */

public class MonthTimeEntity {

    private int year;         //该月份 属于哪一年
    private int month;        // 该月 是哪一个月份
    private MonthPriceBean.DataBean priceBean;

    public MonthTimeEntity() {
    }

    public MonthTimeEntity(int year, int month, MonthPriceBean.DataBean priceBean) {
        this.year = year;
        this.month = month;
        this.priceBean = priceBean;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setPriceBean(MonthPriceBean.DataBean priceBean) {
        this.priceBean = priceBean;
    }

    public MonthPriceBean.DataBean getPriceBean() {
        return priceBean;
    }
}
