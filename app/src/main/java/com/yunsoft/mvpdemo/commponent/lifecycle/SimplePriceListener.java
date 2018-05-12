package com.yunsoft.mvpdemo.commponent.lifecycle;

import java.math.BigDecimal;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-11 18:27
 * Description:this is SimplePriceListener
 */


public interface SimplePriceListener  {

    public void onPriceChanged(BigDecimal price);
}
