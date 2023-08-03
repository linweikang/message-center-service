package net.sitir.message.component.common;

/**
 * 分页参数的类
 *
 * @author linweikang
 * @date 2023/8/3
 */
public class PageBO {

    /**
     * 页码
     */
    private Long current = 1L;

    /**
     * 每页数量
     */
    private Long pageSize = 10L;


    public PageBO() {
    }

    public PageBO(Long current, Long pageSize) {
        this.current = current;
        this.pageSize = pageSize;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
