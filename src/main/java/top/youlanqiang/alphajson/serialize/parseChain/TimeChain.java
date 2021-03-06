package top.youlanqiang.alphajson.serialize.parseChain;

import top.youlanqiang.alphajson.utils.RailUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author youlanqiang
 * @version 1.0
 * @date 2018/11/24
 * @since 1.8
 * 处理时间类型
 */
public class TimeChain extends ObjectToStringChain{

    private String parseStr;

    private SimpleDateFormat format;

    public TimeChain(ObjectToStringChain chain){
        this.next = chain;
        this.parseStr = "yyyy-MM-dd HH:mm:ss";
        this.format = new SimpleDateFormat(parseStr);
    }

    public TimeChain(ObjectToStringChain chain, String parseStr) {
        this.next = chain;
        this.parseStr = parseStr;
        this.format = new SimpleDateFormat(parseStr);
    }



    @Override
    public String execute(Object object) {
        if(object instanceof LocalDateTime){
            return RailUtil.string(((LocalDateTime) object).format(DateTimeFormatter.ofPattern(parseStr)));
        }
        if(object instanceof LocalDate){
            return RailUtil.string(((LocalDate) object).format(DateTimeFormatter.ofPattern(parseStr)));
        }
        if(object instanceof LocalTime){
            return RailUtil.string(((LocalTime) object).format(DateTimeFormatter.ofPattern(parseStr)));
        }
        if(object instanceof Date){
            return RailUtil.string(format.format((Date)object));
        }
        if(object instanceof Calendar){
            return RailUtil.string(format.format(((Calendar)object).getTime()));
        }
        return next.execute(object);
    }

    @Override
    public void setNext(ObjectToStringChain chain) {
        this.next = chain;
    }
}
