package javax.cache.core.writethrough;

import javax.cache.Cache;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;
import java.util.Collection;

/**
 * @author lanruqi
 * @date 2019/8/23
 */
public class WriteThroughWriter implements CacheWriter<String,String> {

    public final static String PREFIX = "[writeThrough]";

    @Override
    public void write(Cache.Entry<? extends String, ? extends String> entry) throws CacheWriterException {
        System.out.println("[CacheWrite] write方法 key:"+entry.getKey()+"   value:"+entry.getValue());
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends String, ? extends String>> entries) throws CacheWriterException {
        System.out.println("[CacheWrite] writeAll方法 key:"+entries.toString());

    }

    @Override
    public void delete(Object key) throws CacheWriterException {
        System.out.println("[CacheWrite] delete方法 key:"+key);

    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {
        System.out.println("[CacheWrite] deleteAll方法 key:"+keys.toString());
    }
}