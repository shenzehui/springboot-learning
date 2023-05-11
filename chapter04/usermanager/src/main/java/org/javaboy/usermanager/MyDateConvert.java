package org.javaboy.usermanager;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 两个泛型说明 ：S:source 将什么转化  T:target 转化成的引用类型
 *
 * @author szh
 */
@Component
public class MyDateConvert implements Converter<String, Date> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
