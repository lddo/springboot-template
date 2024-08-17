package cn.uppp.springboot.template.spring.helper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.BooleanUtil;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis助手
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Component
public class RedisHelper {
    @Autowired
    @Getter
    private StringRedisTemplate redisTemplate;

    /**
     * 扫描Redis Key
     *
     * @param pattern
     * @return
     */
    public Set<String> scan(String pattern) {
        ScanOptions options = ScanOptions.scanOptions()
                .count(1000) // 步进值，过小效率会低，尽量与数据级别匹配
                .match(pattern) // 表达式
                .build();
        Cursor<String> keys = (Cursor<String>) redisTemplate.executeWithStickyConnection(conn -> new ConvertingCursor<>(conn.scan(options), redisTemplate.getKeySerializer()::deserialize));
        if (CollUtil.isEmpty(keys)) {
            return Collections.emptySet();
        }
        return Sets.newHashSet(keys);
    }

    /**
     * 查询指定Key是否存在
     *
     * @param key 键
     * @return
     */
    public boolean hasKey(String key) {
        return BooleanUtil.isTrue(redisTemplate.hasKey(key));
    }

    /**
     * 为指定Key设置失效时间
     *
     * @param key     键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return BooleanUtil.isTrue(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获取指定Key过期时间
     * 1.当 key 不存在时，返回 -2
     * 2.当 key 存在但没有设置剩余生存时间时，返回 -1
     * 3.否则，以秒为单位，返回 key 的剩余生存时间
     *
     * @param key 键
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 重命名Key
     *
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey) {
        redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除指定Key
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除Key
     *
     * @param keys
     */
    public void del(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    //--------------------------------String--------------------------------

    /**
     * 获取指定 key 的值
     *
     * @param key
     * @return
     */
    public String vGet(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取指定 key 的值
     *
     * @param key
     * @return
     */
    public int vGetInt(String key) {
        return Convert.convert(Integer.class, redisTemplate.opsForValue().get(key), 0);
    }

    /**
     * 批量获取
     *
     * @param keys
     * @return
     */
    public List<String> vGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 设置指定 key 的值
     *
     * @param key
     * @param value
     */
    public void vSet(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key
     * @param value
     * @param timeout 过期时间
     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    public void vSet(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 只有在 key 不存在时设置 value
     *
     * @param key
     * @param value
     * @return 之前已经存在返回false, 不存在返回true
     */
    public boolean vSetIfAbsent(String key, String value) {
        return BooleanUtil.isTrue(redisTemplate.opsForValue().setIfAbsent(key, value));
    }

    public boolean vSetIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        return BooleanUtil.isTrue(redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit));
    }

    /**
     * 只有在 key 存在时设置 value
     *
     * @param key
     * @param value
     * @return 之前已经存在返回false, 不存在返回true
     */
    public boolean vSetIfPresent(String key, String value) {
        return BooleanUtil.isTrue(redisTemplate.opsForValue().setIfPresent(key, value));
    }

    public boolean vSetIfPresent(String key, String value, long timeout, TimeUnit unit) {
        return BooleanUtil.isTrue(redisTemplate.opsForValue().setIfPresent(key, value, timeout, unit));
    }

    /**
     * 指定Key自增长
     *
     * @param key
     * @return
     */
    public long vIncr(String key) {
        Long value = redisTemplate.opsForValue().increment(key);
        return value != null ? value : 0;
    }

    /**
     * 指定Key自减
     *
     * @param key
     * @return
     */
    public long vDecr(String key) {
        Long value = redisTemplate.opsForValue().decrement(key);
        return value != null ? value : 0;
    }

    //--------------------------------Hash--------------------------------

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param hashKey
     * @return
     */
    public String hGet(String key, String hashKey) {
        return redisTemplate.<String, String>opsForHash().get(key, hashKey);
    }

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param hashKey
     * @return
     */
    public int hGetInt(String key, String hashKey) {
        return Convert.convert(Integer.class, redisTemplate.<String, String>opsForHash().get(key, hashKey), 0);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @param hashKeys
     * @return
     */
    public List<String> hMGet(String key, Collection<String> hashKeys) {
        return redisTemplate.<String, String>opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 获取所有的key
     */
    public Set<String> hKeys(String key) {
        return redisTemplate.<String, String>opsForHash().keys(key);
    }

    /**
     * 获取所有的value
     */
    public List<String> hValues(String key) {
        return redisTemplate.<String, String>opsForHash().values(key);
    }

    /**
     * 批量获取指定Key的元素
     *
     * @param key
     * @return
     */
    public Map<String, String> hEntries(String key) {
        return redisTemplate.<String, String>opsForHash().entries(key);
    }

    /**
     * 获取哈希表中字段的数量
     *
     * @param key
     * @return
     */
    public long hSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 为指定HashKey元素，更新Value
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 为指定Key，批量更新Value
     *
     * @param key
     * @param entries
     */
    public void hPutAll(String key, Map<String, String> entries) {
        redisTemplate.opsForHash().putAll(key, entries);
    }

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean hPutIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @return
     */
    public long hIncr(String key, String hashKey) {
        return redisTemplate.opsForHash().increment(key, hashKey, 1);
    }

    /**
     * 为指定HashKey自减
     *
     * @param key
     * @param hashKey
     * @return
     */
    public long hDecr(String key, String hashKey) {
        return redisTemplate.opsForHash().increment(key, hashKey, -1);
    }

    /**
     * 删除指定key
     *
     * @param key
     * @param hashKey
     * @return 返回1 成功 ,返回0失败(不存在这个hashKey)
     */
    public long hDel(String key, String hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }


    //--------------------------------Set--------------------------------

    /**
     * 获取集合所有元素
     *
     * @param key
     * @return
     */
    public Set<String> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * set添加元素
     *
     * @param key
     * @param values
     * @return 是否添加成功
     */
    public boolean sAdd(String key, String... values) {
        Long result = redisTemplate.opsForSet().add(key, values);
        return BooleanUtil.toBoolean(String.valueOf(result));
    }

    /**
     * 为指定Key，批量添加元素
     *
     * @param key
     * @param values
     * @return
     */
    public long sMAdd(String key, Collection<String> values) {
        Long size = redisTemplate.opsForSet().add(key, values.toArray(new String[0]));
        return size != null ? size : 0;
    }

    /**
     * 判断集合是否包含value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean sHasValue(String key, String value) {
        return BooleanUtil.isTrue(redisTemplate.opsForSet().isMember(key, value));
    }

    /**
     * 获取集合的大小
     *
     * @param key
     * @return
     */
    public long sSize(String key) {
        Long size = redisTemplate.opsForSet().size(key);
        return size != null ? size : 0;
    }

    /**
     * set移除元素
     *
     * @param key
     * @param values
     * @return
     */
    public void sDel(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

    //--------------------------------zSet--------------------------------

    public Set<String> zGet(String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }

    /**
     * 获取集合的元素, 从小到大排序
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return
     */
    public Set<String> zGet(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public void zAdd(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 指定Key的数量
     * 当 key 存在且是有序集类型时，返回有序集的基数
     * 当 key 不存在时，返回 0
     *
     * @param key
     * @return
     */
    public long zSize(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 指定Key自增长
     *
     * @param key
     * @param value
     * @return
     */
    public double zIncr(String key, String value) {
        return redisTemplate.opsForZSet().incrementScore(key, value, 1);
    }

    /**
     * 指定Key自减
     *
     * @param key
     * @param value
     * @return
     */
    public double zDecr(String key, String value) {
        return redisTemplate.opsForZSet().incrementScore(key, value, -1);
    }


    /**
     * 批量移除元素根据元素值
     *
     * @param key
     * @param values
     * @return
     */
    public void zDel(String key, Object... values) {
        redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 根据score值获取集合元素数量
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public long zSize(String key, long min, long max) {
        Long size = redisTemplate.opsForZSet().count(key, min, max);
        return size != null ? size : 0;
    }

    //--------------------------------List--------------------------------

    /**
     * 获取指定Key所有元素
     *
     * @param key
     * @return
     */
    public List<String> lGet(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param key
     * @param start 开始位置, 0是开始位置
     * @param end   结束位置, -1返回所有
     * @return
     */
    public List<String> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 通过索引获取列表中的元素
     *
     * @param key
     * @param index
     * @return
     */
    public String lGet(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 通过索引设置列表元素的值
     *
     * @param key
     * @param index 位置
     * @param value
     */
    public void lSet(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 存储在list头部
     *
     * @param key
     * @param value
     * @return
     */
    public void lLeftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     * @return 删除的元素
     */
    public void lLeftPop(String key) {
        redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 在集合右边添加元素
     *
     * @param key
     * @param value
     * @return
     */
    public void lRightPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key
     * @return 删除的元素
     */
    public void lRightPop(String key) {
        redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param
     * @param
     * @return
     */
    public void lRightPopAndLeftPush(String key, String value) {
        redisTemplate.opsForList().rightPopAndLeftPush(key, value);
    }

    /**
     * 获取列表长度
     *
     * @param key
     * @return
     */
    public long lSize(String key) {
        Long size = redisTemplate.opsForList().size(key);
        return size != null ? size : 0;
    }

}
