package javax.cache.core.enrtyprocessor;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.core.readthrough.ReadThroughLoader;
import javax.cache.core.writethrough.WriteThroughWriter;
import javax.cache.spi.CachingProvider;

/**
 * @author lanruqi
 * @date 2019/8/29
 */
public class SimpleExample {


    public static void main(String[] args) {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //创建缓存配置类
        MutableConfiguration mutableConfiguration = new MutableConfiguration();
        mutableConfiguration.setTypes(String.class, String.class);

        //配置readThrough
        mutableConfiguration.setReadThrough(true);
        mutableConfiguration.setCacheLoaderFactory(FactoryBuilder.factoryOf(ReadThroughLoader.class));

        //配置readThrough
        mutableConfiguration.setReadThrough(true);
        mutableConfiguration.setCacheWriterFactory(FactoryBuilder.factoryOf(WriteThroughWriter.class));

        //创建缓存
        Cache<String, String> cache = cacheManager.createCache("simpleCache", mutableConfiguration);

        cache.put("key1","value1");

        String value = cache.invoke("key", new ReadThroughEntryProcessor());

        String value1 = cache.invoke("key1", new ReadThroughEntryProcessor());

        System.out.println("获取的值:"+value);
        System.out.println("获取的值:"+value1);

    }
}