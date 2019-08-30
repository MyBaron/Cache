package javax.cache.core.listen.expirelistener;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListenerException;

/**
 * @author lanruqi
 * @date 2019/8/27
 * 缓存创建和过期监听器
 */
public class SimpleExpireAndCreateListener implements CacheEntryCreatedListener<String,String> , CacheEntryExpiredListener<String,String> {
    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends String, ? extends String> entryEvent : cacheEntryEvents) {
            System.out.println("Created的Listener方法: " + entryEvent.getKey() + " with value: " + entryEvent.getValue());
        }
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends String, ? extends String> entryEvent : cacheEntryEvents) {
            System.out.println("Expired的Listener方法: " + entryEvent.getKey() + " with value: " + entryEvent.getValue()+" oldValue: "+entryEvent.getOldValue());
        }
    }
}