package io.github.jjyy.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jjyy.domain.Account;
import io.github.jjyy.domain.AccountTransfer;
import io.github.jjyy.domain.enumeration.TransferStatus;
import io.github.jjyy.mapper.AccountMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

/**
 * @author jy
 */
@DubboService(interfaceName = "io.github.jjyy.service.AccountService", version = "1.0.0")
public class CnAccountService extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountTransferService accountTransferService;

    @Override
    @HmilyTCC(confirmMethod = "confirmTransfer", cancelMethod = "cancelTransfer")
    public void addDollarBalance(AccountTransfer transfer) {
        accountTransferService.save(transfer.setCreatedAt(Instant.now()).setUpdatedAt(Instant.now()));
    }

    public void confirmTransfer(AccountTransfer transfer) {
        AccountTransfer accountTransfer = accountTransferService.getById(transfer.getId());
        if (accountTransfer != null && accountTransfer.getStatus() == TransferStatus.PENDING) {
            accountTransferService.updateById(new AccountTransfer().setId(transfer.getId()).setStatus(TransferStatus.SUCCESS).setUpdatedAt(Instant.now()));
            update(new UpdateWrapper<Account>().setSql("dollar = dollar + " + transfer.getDollar()).eq("id", transfer.getAccountId()));
        }
    }

    public void cancelTransfer(AccountTransfer transfer) {
        AccountTransfer accountTransfer = accountTransferService.getById(transfer.getId());
        if (accountTransfer != null && accountTransfer.getStatus() == TransferStatus.PENDING) {
            accountTransferService.updateById(new AccountTransfer().setId(transfer.getId()).setStatus(TransferStatus.FAILED).setUpdatedAt(Instant.now()));

        }
    }
}
