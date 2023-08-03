package net.sitir.message.component.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 新增修改是默认修改字段设置
 * @author linweikang
 * @since 2023/8/3
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时对元对象字段填充（用于更新时对公共字段的填充）
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "dr", Integer.class, 0);
        this.strictInsertFill(metaObject, "modifiedTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "creationTime", LocalDateTime.class, LocalDateTime.now());
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime.class, LocalDateTime.now());
    }
}
