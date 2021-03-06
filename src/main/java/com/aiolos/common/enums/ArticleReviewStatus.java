package com.aiolos.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Desc: 文章审核状态 枚举
 * 文章状态：
 *      1：审核中（用户已提交），
 *      2：机审结束，等待人工审核，
 *      3：审核通过（已发布），
 *      4：审核未通过
 *      5：文章撤回
 *
 *      1/2 可以统一归类为 [正在审核]状态
 * @author Aiolos
 * @date 2020/11/26 6:01 下午
 */
@Getter
@AllArgsConstructor
public enum ArticleReviewStatus {
    REVIEWING(1, "审核中（用户已提交）"),
    WAITING_MANUAL(2, "机审结束，等待人工审核"),
    SUCCESS(3, "审核通过（已发布）"),
    FAILED(4, "审核未通过"),
    WITHDRAW(5, "文章撤回");

    private final Integer type;
    private final String value;

    /**
     * 判断传入的审核状态是不是有效的值
     * @param tempStatus
     * @return
     */
    public static boolean isArticleStatusValid(Integer tempStatus) {
        if (tempStatus != null) {
            if (tempStatus.equals(REVIEWING.type)
                    || tempStatus.equals(WAITING_MANUAL.type)
                    || tempStatus.equals(SUCCESS.type)
                    || tempStatus.equals(FAILED.type)
                    || tempStatus.equals(WITHDRAW.type)) {
                return true;
            }
        }
        return false;
    }
}
