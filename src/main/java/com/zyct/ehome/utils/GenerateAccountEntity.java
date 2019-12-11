package com.zyct.ehome.utils;/**
 * @author CZS
 * CreateTime 2019/12/10 11:20
 * Email 642125256@qq.com
 */
public class GenerateAccountEntity {
    private String accountType;
    private Long regionId;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "GenerateAccountUtil{" +
                "accountType='" + accountType + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
