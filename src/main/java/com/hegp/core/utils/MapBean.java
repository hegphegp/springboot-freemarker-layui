package com.hegp.core.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MapBean {
    private static final Logger logger = LoggerFactory.getLogger(MapBean.class);
    public Map<String, Object> map = new HashMap();
    private Pattern longRegex = Pattern.compile("^\\d+$");

    public MapBean() { }

    public MapBean(Map<String,Object> map) {
        this.map = map;
    }

    public Short getShort(String key) {
        Object value = map.get(key);
        if (value instanceof Short) {
            return (Short) value;
        } else if (value!=null) {
            return Convert.toShort(value);
        }
        return null;
    }

    public Integer getInt(String key) {
        Object value = map.get(key);
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value!=null) {
            return Convert.toInt(value.toString());
        }
        return null;
    }

    public Long getLong(String key) {
        Object value = map.get(key);
        if (value instanceof Long) {
            return (Long) value;
        } else if (value!=null) {
            return Convert.toLong(value.toString());
        }
        return null;
    }

    public Double getDouble(String key) {
        Object value = map.get(key);
        if (value instanceof Double) {
            return (Double) value;
        } else if (value!=null) {
            return Convert.toDouble(value.toString());
        }
        return null;
    }

    public Float getFloat(String key) {
        Object value = map.get(key);
        if (value instanceof Float) {
            return (Float) value;
        } else if (value!=null) {
            return Convert.toFloat(value.toString());
        }
        return null;
    }

    public BigDecimal getBigDecimal(String key) {
        Object value = map.get(key);
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value!=null) {
            return Convert.toBigDecimal(value);
        }
        return null;
    }

    public Number getNumber(String key) {
        Object value = map.get(key);
        if (value instanceof Number) {
            return (Number) value;
        } else if (value!=null) {
            return Convert.toNumber(value);
        }
        return null;
    }

    public Boolean getBoolean(String key) {
        Object value = map.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value!=null) {
            return Boolean.parseBoolean(value.toString());
        }
        return null;
    }

    public String getString(String key) {
        Object value = map.get(key);
        if (value instanceof String) {
            return (String) value;
        } else if (value!=null) {
            return value.toString();
        }
        return null;
    }

    public Date getDate(String key) {
        Object value = map.get(key);
        if (value instanceof Date) {
            return (Date)value;
        } else if (value!=null) {
            if (value instanceof Long) {
                return new Date((Long)value);
            } else if (value instanceof String) {
                String valueStr = (String) value;
                valueStr = valueStr.trim();
                if (longRegex.matcher(valueStr).matches()) {
                    return new Date(Long.parseLong(valueStr));
                } else {
                    try {
                        return DateUtil.parse(valueStr);
                    } catch (Exception e) {
                        logger.error(key+" 字段的时间格式不正确；时间取值是"+valueStr);
                    }
                }
            }
        }
        return null;
    }

    public Timestamp getTimestamp(String key) {
        Date date = getDate(key);
        return date==null? null:new Timestamp(date.getTime());
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("del", "true");
        map.put("DEL", "TRUE");
        map.put("DEL1", "1");
        MapBean mapBean = new MapBean(map);
        System.out.println(mapBean.getShort("del"));
        System.out.println(mapBean.getInt("del"));
        System.out.println(mapBean.getLong("del"));
        System.out.println(mapBean.getDouble("del"));
        System.out.println(mapBean.getFloat("del"));
        System.out.println(mapBean.getBigDecimal("del"));
        System.out.println(mapBean.getNumber("del"));
        System.out.println(mapBean.getBoolean("del"));
        System.out.println(mapBean.getString("del"));
        System.out.println(mapBean.getDate("del"));
        System.out.println(mapBean.getTimestamp("del"));
    }

}
