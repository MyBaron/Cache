package javax.cache.core.readthrough;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import java.util.*;

/**
 * @author lanruqi
 * @date 2019/8/22
 */
public class ReadThroughLoader implements CacheLoader {

    /**
     * 模拟数据库
     */
    private static final List<String> DATASOURCE ;
    private static final Random RANDOMINT;

    static {
        DATASOURCE = new ArrayList<>();
        DATASOURCE.add("datasource1");
        DATASOURCE.add("datasource2");
        DATASOURCE.add("datasource3");
        DATASOURCE.add("datasource4");
        RANDOMINT = new Random();
    }


    @Override
    public Object load(Object key) throws CacheLoaderException {
        System.out.println("[loader加载] 传入key："+key);
        //模擬查询数据库的数据
        String value = DATASOURCE.get(RANDOMINT.nextInt(DATASOURCE.size()));
        return value;
    }

    @Override
    public Map loadAll(Iterable keys) throws CacheLoaderException {
        Map<String, String> map = new HashMap<>();
        keys.forEach(key->{
            //模擬查询数据库的数据
            String value = DATASOURCE.get(RANDOMINT.nextInt(DATASOURCE.size()));
            map.put(String.valueOf(key), value);
        });
        return map;
    }

    public static void main(String[] args) {
        int i = new Random().nextInt(4);
        System.out.println(i);
    }
}