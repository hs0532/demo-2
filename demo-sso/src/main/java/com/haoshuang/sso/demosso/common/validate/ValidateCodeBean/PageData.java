package com.haoshuang.sso.demosso.common.validate.ValidateCodeBean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.haoshuang.sso.demosso.common.validate.DigestUtilEZ;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PageData extends JSONObject {
    private static final long serialVersionUID = 1L;

    private JSONObject map = null;

    private HttpServletRequest request;

    @SuppressWarnings({})
    public PageData(HttpServletRequest request) {
        this.request = request;
        Map<?, ?> properties = request.getParameterMap();
        JSONObject returnMap = new JSONObject();
        Iterator<?> entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            if ("_".equals(name)) {    //解决一些特殊字符冲突，用了base64解密
                returnMap.put(name, DigestUtilEZ.digestString(value, DigestUtilEZ.ALGORITHM_NAME.Decode_BASE_64));
            } else {
                returnMap.put(name, value);    //无字符冲突 且明文传输情况
            }

        }
        map = returnMap;
    }

    public PageData(String jsonStr) {
        map = JSONObject.parseObject(jsonStr);
    }

    public PageData() {
        map = new JSONObject();
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    public String getString(String key) {
        return map.getString(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object putIfAbsent(String key, Object value) {
        return map.putIfAbsent(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public void putAll(JSONObject t) {
        map.putAll(t);
    }

    public int size() {
        return map.size();
    }

    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(map, SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames, SerializerFeature.WriteNullListAsEmpty);
    }

//    public static void main(String[] args) {
//        PageData pd = new PageData();
//        pd.put("123",null);
//        System.out.println(pd);
//    }
}