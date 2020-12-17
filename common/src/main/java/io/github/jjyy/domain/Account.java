package io.github.jjyy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author jy
 */
@Data
@TableName("account")
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 8066644076760595740L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long cny;

    private Long dollar;

    private Instant createdAt;

    private Instant updatedAt;
}
