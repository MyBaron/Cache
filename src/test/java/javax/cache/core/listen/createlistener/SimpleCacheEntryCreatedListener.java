package javax.cache.core.listen.createlistener;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.EventType;

/**
 * @author lanruqi
 * @date 2019/8/27
 */
public class SimpleCacheEntryCreatedListener implements CacheEntryCreatedListener<String,String> {

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents) throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends String, ? extends String> entryEvent : cacheEntryEvents) {
            System.out.println("Created的Listener方法: " + entryEvent.getKey() + " with value: " + entryEvent.getValue());
        }
    }



}