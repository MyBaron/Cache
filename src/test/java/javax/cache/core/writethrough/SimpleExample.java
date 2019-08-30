package javax.cache.core.writethrough;

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

        MutableConfiguration mutableConfiguration = new MutableConfiguration();
        mutableConfiguration.setWriteThrough(true);
        mutableConfiguration.setCacheWriterFactory(FactoryBuilder.factoryOf(SimpleExample.class));

        cacheManager.createCache("writeThrough", mutableConfiguration);

        Cache<Object, Object> writeThrough = cacheManager.getCache("writeThrough");

        writeThrough.put("key1", "value1");

    }
}