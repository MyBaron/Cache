package javax.cache.core.enrtyprocessor;


import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;

/**
 * @author lanruqi
 * @date 2019/8/29
 */
public class ReadThroughEntryProcessor implements EntryProcessor<String,String,String> {

    @Override
    public String process(MutableEntry<String,String> entry, Object... arguments) throws EntryProcessorException {
        //判断entry是否存在
        if (entry.exists()) {
            System.out.println("value存在,实现writeThrough");
            entry.setValue("主动修改value");
        }else {
            System.out.println("value不存在,实现readThrough");
        }
        return entry.getValue();
    }
}