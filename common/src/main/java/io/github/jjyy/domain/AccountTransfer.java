package io.github.jjyy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.jjyy.domain.enumeration.TransferStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author jy
 */
@Data
@TableName("account_transfer")
public class AccountTransfer implements Serializable {

    @Serial
    private static final long serialVersionUID = -4993455581697135324L;

    @TableId(type = IdType.INPUT)
    private Long id;

    private Long accountId;

    private Long cny;

    private Long dollar;

    private TransferStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
