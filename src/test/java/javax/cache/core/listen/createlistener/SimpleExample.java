package javax.cache.core.listen.createlistener;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * @author lanruqi
 * @date 2019/8/27
 */
public class SimpleExample {

    public static void main(String[] args) {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //创建缓存配置类
        MutableConfiguration mutableConfiguration = new MutableConfiguration();
        mutableConfiguration.setTypes(String.class, String.class);

        Cache<Object, Object> cache = cacheManager.createCache("simpleCreateListener", mutableConfiguration);

        MutableCacheEntryListenerConfiguration mutableCacheEntryListenerConfiguration = new MutableCacheEntryListenerConfiguration(
                FactoryBuilder.factoryOf(SimpleCacheEntryCreatedListener.class), null,
                false, false
        );

        //添加监听器
        cache.registerCacheEntryListener(mutableCacheEntryListenerConfiguration);

        //添加新的值
        cache.put("创建key", "创建value");

    }
}