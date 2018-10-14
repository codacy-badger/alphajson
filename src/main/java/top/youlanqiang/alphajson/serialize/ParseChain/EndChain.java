package top.youlanqiang.alphajson.serialize.ParseChain;

import top.youlanqiang.alphajson.bean.SimpleObjectBean;
import top.youlanqiang.alphajson.serialize.ObjectSerializable;

/**
 * @author youlanqiang
 * @version 1.0
 * @date 2018/10/12
 * @since 1.8
 * 工作链的尾链 直接将Object转化为JSON字符串
 */
public class EndChain  extends ObjectToStringChain{


    @Override
    public String execute(Object object) {
        return new ObjectSerializable(new SimpleObjectBean(object)).operator();
    }
}