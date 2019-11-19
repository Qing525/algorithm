package com.wld.msg.domain.base;

public interface RemindObject {


    /**
     * 对象分类；描述对象属于哪一类的。比如体育类，科技类
     */
    String getObjectType();

    /**
     * 目标对象;(通俗讲是某个表名（实体）)
     * 对象分类下具体的哪一对象。比如体育类下的篮球。
     */
    String getObject();

    /**
     * 目标对象Id(通俗讲是某个表的某条数据)
     */
    String getObjectId();

    /**
     * 目标对象简介，用以填充消息模板
     */
    String getObjectContent();
}
