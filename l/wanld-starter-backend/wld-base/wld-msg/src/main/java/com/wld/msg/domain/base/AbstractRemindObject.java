package com.wld.msg.domain.base;


/**
 * 消息对象
 *
 * @author 王增光
 * @date 2019/9/16
 */

public abstract class AbstractRemindObject implements RemindObject {
    /**
     * 对象分类；描述对象属于哪一类的。比如体育类，科技类
     */
    private String objectType;

    /**
     * 目标对象;(通俗讲是某个表名（实体）)
     * 对象分类下具体的哪一对象。比如体育类下的篮球。
     */
    private String object;

    /**
     * 目标对象Id(通俗讲是某个表的某条数据)
     */
    private String objectId;

    /**
     * 目标对象简介，用以填充消息模板
     */
    private String objectContent;

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setObjectContent(String objectContent) {
        this.objectContent = objectContent;
    }

    @Override
    public String getObjectType() {
        return objectType;
    }

    @Override
    public String getObject() {
        return object;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    @Override
    public String getObjectContent() {
        return objectContent;
    }


}
