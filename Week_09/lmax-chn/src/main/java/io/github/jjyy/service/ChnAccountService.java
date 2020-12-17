package io.github.jjyy.service;

/**
 * @author jy
 */
public interface ChnAccountService {

    void transfer(Long transferId, Long id, Long amount, Long targetAccountId);

}
