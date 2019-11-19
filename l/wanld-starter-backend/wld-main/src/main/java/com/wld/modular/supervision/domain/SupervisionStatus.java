package com.wld.modular.supervision.domain;

/**
 * @author LJQ
 * @date 2019/9/25 10:13
 **/
public class SupervisionStatus {
    /**
     * 接收状态  0未接收   1接收     2录入(新建任务后的状态)
     */
    public static final int NOT_RECEIVE = 0;

    public static final int RECEIVED = 1;

    public static final int INPUT = 2;

    /**
     * 完成状态   0未完成   1完成    2逾期完成
     */
    public static final int NOT_COMPLETE = 0;

    public static final int COMPLETED = 1;

    public static final int DELAY_COMPLETED = 2;

    /**
     * 审核状态   0 未审核 1通过   2驳回
     */
    public static final int NOT_REVIEW = 0;

    public static final int REVIEW_SUCC = 1;

    public static final int REVIEW_FAIL = 2;
}
