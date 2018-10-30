package top.youlanqiang.alphajson;

import top.youlanqiang.alphajson.deserialize.JSONDeserialize;
import top.youlanqiang.alphajson.deserialize.JSONObjectDeserialize;
import top.youlanqiang.alphajson.serialize.JSONSerialize;
import top.youlanqiang.alphajson.serialize.MapContainer;
import top.youlanqiang.alphajson.serialize.ObjectSerializable;
import top.youlanqiang.alphajson.serialize.StringSerialize;
import top.youlanqiang.alphajson.utils.TransitionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author youlanqiang
 * @version 1.0
 * @date 2018/10/5
 * @since 1.8
 */
public class JSONObject implements JSONSerialize, MapContainer{

    private static final JSONDeserialize parser = new JSONObjectDeserialize();

    private Map<String,Object> map;


    public JSONObject(){
        this.map = new HashMap<>(20);
    }

    public void put(String key, Object value){
        map.put(key, value);
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }

    public Set<String>  keys(){
        return map.keySet();
    }

    public int size(){
        return map.size();
    }

    @Override
    public Map<String, Object> getContainer(){
        return map;
    }

    @Override
    public void putAll(Map<String, Object> map) {
        this.map.putAll(map);
    }

    public Object getObjectValue(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }

    public Float getFloatValue(String key){
        Object result = getObjectValue(key);
        if (result != null){
            return TransitionUtil.parseFloat(result);
        }
        return 0.0F;
    }

    public Double getDoubleValue(String key){
        Object result = getObjectValue(key);
        if (result != null){
            return TransitionUtil.parseDouble(result);
        }
        return 0.0;
    }

    public Long getLongValue(String key){
        Object result = getObjectValue(key);
        if (result != null){
            return TransitionUtil.parseLong(result);
        }
        return 0L;
    }

    public Integer getIntegerValue(String key){
        Object result = getObjectValue(key);
        if (result != null){
            return TransitionUtil.parseInteger(result);
        }
        return 0;
    }

    public Boolean getBooleanValue(String key){
        Object result = getObjectValue(key);
        if (result != null){
            return TransitionUtil.parseBoolean(result);
        }
        return false;
    }

    public String  getStringValue(String key){
        Object result = getObjectValue(key);
        if (result != null){
            return result.toString();
        }
        return null;
    }

    public <T> T getObjectValue(String key, Class<T> clazz){
        Object result = getObjectValue(key);
        if (result != null){
            if(result instanceof JSONObject){
                //TODO result 转换为 对应的Class类
            }
            if(result instanceof JSONArray){
                //TODO result 转换为 对应的Class类
            }
            return (T)result;
        }
        return null;
    }


    /**
     * json字符串解析为JSONObject对象
     * @param json
     * @return
     */
    public static JSONObject parse(String json){
        return (JSONObject) parser.parse(json);
    }

    /**
     * 将json字符串解析为对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> clazz){
        return parse(json).parseObject(clazz);
    }

    /**
     * 将该JSONObject对象转化为对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T parseObject(Class<T> clazz){
        //TODO 将该对象转化为对象
        return null;
    }

    @Override
    public StringSerialize getSerialize() {
        return new ObjectSerializable(this);
    }

    @Override
    public String toString(){
        return getSerialize().operator();
    }
}
