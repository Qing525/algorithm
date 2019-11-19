package com.wld.config.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体映射的工具类
 *
 * @author 王增光
 */
@Component
public class MapperUtil {
    private static DozerBeanMapper mapper;

    @Autowired
    public MapperUtil(DozerBeanMapper mapper) {
        MapperUtil.mapper = mapper;
    }

    /**
     * 对page的转换
     *
     * @param page
     * @param tClass
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> Page<T> map(Page<S> page, Class<T> tClass) throws MappingException {
        List<T> targets = new ArrayList<T>(page.getContent().size());
        for (S s : page.getContent()) {
            targets.add(MapperUtil.mapper.map(s, tClass));
        }
        Page<T> result = new PageImpl<T>(targets, page.getPageable(), page.getTotalElements());
        return result;
    }

    /**
     * 对List<T>转换
     *
     * @param source
     * @param tClass
     * @param <S>
     * @param <T>
     * @return
     * @throws MappingException
     */
    public static <S, T> List<T> map(List<S> source, Class<T> tClass) throws MappingException {
        List<T> targets = new ArrayList<T>(source.size());
        for (S s : source) {
            targets.add(MapperUtil.mapper.map(s, tClass));
        }
        return targets;
    }

    public static void map(Object source, Object destination, String mapId) throws MappingException {
        MapperUtil.mapper.map(source, destination, mapId);
    }

    public static <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException {
        return MapperUtil.mapper.map(source, destinationClass, mapId);
    }

    public static <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        return MapperUtil.mapper.map(source, destinationClass);
    }

    public static void map(Object source, Object destination) throws MappingException {
        MapperUtil.mapper.map(source, destination);
    }

}
