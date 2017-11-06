package com.wxs.tkmybatis.vo;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

/**
 * <p>Account</p>
 *
 * @author wuxinshui
 */
public class Account {
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal count;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal count1;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal count2;
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal count3;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCount1() {
        return count1;
    }

    public void setCount1(BigDecimal count1) {
        this.count1 = count1;
    }

    public BigDecimal getCount2() {
        return count2;
    }

    public void setCount2(BigDecimal count2) {
        this.count2 = count2;
    }

    public BigDecimal getCount3() {
        return count3;
    }

    public void setCount3(BigDecimal count3) {
        this.count3 = count3;
    }
}
