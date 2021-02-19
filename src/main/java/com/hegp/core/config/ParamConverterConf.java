package com.hegp.core.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class ParamConverterConf {
    // 配置 @RequestParam 字段取值 转 Date
    @Bean
    public Converter<String, Date> toDate() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                if (NumberUtil.isLong(source)) {
                    return new Date(Convert.toLong(source));
                } else if (StringUtils.hasText(source)) {
                    Date date = Convert.convert(Date.class, source);
                    if (date==null) {
                        throw new RuntimeException("[ "+source+" ] can't convert to Date.class ");
                    }
                    return date;
                } else return null;
            }
        };
    }

    // 配置 @RequestParam 字段取值 转 Timestamp
    @Bean
    public Converter<String, Timestamp> toTimestamp() {
        return new Converter<String, Timestamp>() {
            @Override
            public Timestamp convert(String source) {
                if (NumberUtil.isLong(source)) {
                    return new Timestamp(Convert.toLong(source));
                } else {
                    return Convert.convert(Timestamp.class, source);
                }
            }
        };
    }

    // 配置 @RequestParam 的 defaultValue 转 Date和Timestamp，defaultValue可能是now，currentYear，today，yesterday
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final CustomDateEditor dateEditor = new CustomDateEditor(df, true) {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if ("now".equals(text)) {
                    setValue(new Date());
                } else if (NumberUtil.isLong(text)) {
                    super.setValue(new Date(Convert.toLong(text)));
                } else {
//                    super.setAsText(text);
                    super.setValue(Convert.convert(Date.class, text));
                }
            }
        };
        binder.registerCustomEditor(Date.class, dateEditor);      // 绑定Date类型
        binder.registerCustomEditor(Timestamp.class, dateEditor); // 绑定Timestamp类型
    }

}
