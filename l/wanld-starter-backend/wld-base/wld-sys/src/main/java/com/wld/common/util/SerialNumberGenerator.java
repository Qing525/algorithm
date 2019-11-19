package com.wld.common.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务编号：yyyyMMdd+四位流水号
 *
 * @author LJQ
 * @date 2019/9/16 10:34
 **/
@Component
public class SerialNumberGenerator {
    private static AtomicInteger count = new AtomicInteger(1);

    public String getSerialNumber() {

        if (count.get() == 1000) {
            count.set(0);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String currentTime = df.format(new Date());
        String currentIndex = String.format("%04d", count.get());
        String serialNumber = currentTime + currentIndex;
        count.incrementAndGet();
        return serialNumber;
    }
}
