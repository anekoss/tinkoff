package edu.hw10.Task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    private final Object target;
    private final Map<String, Object> cacheMap;

    private CacheProxy(Object target, Map<String, Object> cache) {
        this.target = target;
        this.cacheMap = cache;
    }

    public static <T> T create(T target, Class<?> interfaces, Map<String, Object> cache) {
        return (T) Proxy.newProxyInstance(
            interfaces.getClassLoader(),
            new Class<?>[] {interfaces},
            new CacheProxy(target, cache)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException {
        if (!method.isAnnotationPresent(Cache.class)) {
            return method.invoke(target, args);
        } else {
            Cache cache = method.getAnnotation(Cache.class);
            String key = method.getName() + ":" + Arrays.hashCode(args);
            if (this.cacheMap.containsKey(key)) {
                return this.cacheMap.get(key);
            } else {
                Object result = method.invoke(target, args);
                if (cache.persist()) {
                    this.cacheMap.put(key, result);
                }
                return result;
            }
        }
    }
}
