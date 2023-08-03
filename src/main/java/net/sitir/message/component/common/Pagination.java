package net.sitir.message.component.common;

import lombok.Data;

import java.util.List;

/**
 * @author linweikang
 * @since 2023/8/3
 */
@Data
public class Pagination<T> {

    private Long totalCount;
    private List<T> recordList;

    public Pagination(Long totalCount, List<T> recordList) {
        this.recordList = recordList;
        this.totalCount = totalCount;
    }
}
