package javax.cache.core.listen.expirelistener;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author lanruqi
 * @date 2019/8/27
 */
public class SimpleExample {

    public static void main(String[] args) throws InterruptedException {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        //创建缓存配置类
        MutableConfiguration mutableConfiguration = new MutableConfiguration();
        mutableConfiguration.setTypes(String.class, String.class);
        //设置1分钟过期时间
        mutableConfiguration.setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(new Duration(SECONDS, 10)));

        MutableCacheEntryListenerConfiguration mutableCacheEntryListenerConfiguration = new MutableCacheEntryListenerConfiguration(
                FactoryBuilder.factoryOf(SimpleExpireAndCreateListener.class), null, false, false
        );
        //创建缓存操作类
        Cache<String, String> cache = cacheManager.createCache("simpleExpireListenerDemo", mutableConfiguration);

        cache.registerCacheEntryListener(mutableCacheEntryListenerConfiguration);

        cache.put("过期key", "过期value");

        //睡眠1分钟
        Thread.sleep(10000);

        String value = cache.get("过期key");

        System.out.println("????");
        System.out.println("获取到value:"+value);
    }
}