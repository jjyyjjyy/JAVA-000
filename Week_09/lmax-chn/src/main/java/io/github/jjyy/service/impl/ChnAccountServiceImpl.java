package io.github.jjyy.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jjyy.domain.Account;
import io.github.jjyy.domain.AccountTransfer;
import io.github.jjyy.domain.enumeration.TransferStatus;
import io.github.jjyy.mapper.AccountMapper;
import io.github.jjyy.service.AccountService;
import io.github.jjyy.service.AccountTransferService;
import io.github.jjyy.service.ChnAccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author jy
 */
@Service
public class ChnAccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements ChnAccountService {

    @DubboReference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private AccountService accountService;

    @Autowired
    private AccountTransferService accountTransferService;

    @Override
    @HmilyTCC(confirmMethod = "confirmTransfer", cancelMethod = "cancelTransfer")
    public void transfer(Long transferId, Long id, Long amount, Long targetAccountId) {
        accountTransferService.save(new AccountTransfer().setId(transferId).setAccountId(id).setCny(-amount).setCreatedAt(Instant.now()).setUpdatedAt(Instant.now()));
        accountService.addDollarBalance(new AccountTransfer().setId(transferId).setAccountId(targetAccountId).setDollar(amount / 7));
    }

    public void confirmTransfer(Long transferId, Long id, Long amount, Long targetAccountId) {
        AccountTransfer accountTransfer = accountTransferService.getById(transferId);
        if (accountTransfer != null && accountTransfer.getStatus() == TransferStatus.PENDING) {
            accountTransferService.updateById(new AccountTransfer().setId(transferId).setStatus(TransferStatus.SUCCESS).setUpdatedAt(Instant.now()));
            update(new UpdateWrapper<Account>().setSql("cny = cny + " + accountTransfer.getCny()).eq("id", id));
        }
    }

    public void cancelTransfer(Long transferId, Long id, Long amount, Long targetAccountId) {
        AccountTransfer accountTransfer = accountTransferService.getById(transferId);
        if (accountTransfer != null && accountTransfer.getStatus() == TransferStatus.PENDING) {
            accountTransferService.updateById(new AccountTransfer().setId(transferId).setStatus(TransferStatus.FAILED).setUpdatedAt(Instant.now()));
        }
    }
}
