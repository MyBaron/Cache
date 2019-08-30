package javax.cache.core.readthrough;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lanruqi
 * @date 2019/8/22
 */
public class loadAllDemo {

    public static void main(String[] args) throws InterruptedException {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //创建缓存配置类
        MutableConfiguration mutableConfiguration = new MutableConfiguration();
        mutableConfiguration.setTypes(String.class, String.class);
//        mutableConfiguration.setCacheLoaderFactory(new ReadThroughLoaderFactory(new ReadThroughLoader()));
        mutableConfiguration.setCacheLoaderFactory(FactoryBuilder.factoryOf(ReadThroughLoader.class));

        //创建缓存
        cacheManager.createCache("loaderDemo", mutableConfiguration);

        //获取缓存
        Cache<String, String> loaderDemo = cacheManager.getCache("loaderDemo");

        String key1 = loaderDemo.get("key1");
        if (null != key1) {
            System.out.println("命中缓存");
            return;
        }

        System.out.println("未命中缓存，执行read through 策略");

        //创建keys集合
        Set<String> keySet = new HashSet<>();
        keySet.add("key1");
        keySet.add("key2");
        keySet.add("key3");
        //创建loaderListener
        ReadThroughLoaderListener readThroughLoaderListener = new ReadThroughLoaderListener();
        loaderDemo.loadAll(keySet, true, readThroughLoaderListener);

        while (readThroughLoaderListener.getState() != ReadThroughLoaderListener.COMPLETION && ReadThroughLoaderListener.EXCEPTION != readThroughLoaderListener.getState()) {
            System.out.println("睡眠等待1S");
            Thread.sleep(1000);
        }

        if (readThroughLoaderListener.getState() == ReadThroughLoaderListener.COMPLETION) {
            Map<String, String> allKeyMap = loaderDemo.getAll(keySet);
            for (Map.Entry<String, String> map : allKeyMap.entrySet()) {
                System.out.println("key:"+map.getKey()+"  value: "+map.getValue());
            }
        }
    }
}