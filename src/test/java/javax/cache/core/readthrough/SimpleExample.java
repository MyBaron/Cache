package javax.cache.core.readthrough;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;


/**
 * @author lanruqi
 * @date 2019/8/22
 */
public class SimpleExample {

    public static void main(String[] args) throws InterruptedException {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //创建缓存配置类
        MutableConfiguration mutableConfiguration = new MutableConfiguration();
        mutableConfiguration.setTypes(String.class, String.class);
        //开启readThrough模式
        mutableConfiguration.setReadThrough(true);
        mutableConfiguration.setCacheLoaderFactory(FactoryBuilder.factoryOf( ReadThroughLoader.class));

        //创建缓存
        cacheManager.createCache("readThroughDemo", mutableConfiguration);

        //获取缓存
        Cache<String, String> loaderDemo = cacheManager.getCache("readThroughDemo");

        String value = loaderDemo.get("key1");
        if (null != value) {
            System.out.println("命中缓存");
            System.out.println("缓存值:"+value);
            return;
        }
    }
}