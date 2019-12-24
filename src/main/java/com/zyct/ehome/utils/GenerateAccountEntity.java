package com.zyct.ehome.utils;/**
 * @author CZS
 * CreateTime 2019/12/10 11:20
 * Email 642125256@qq.com
 */
public class GenerateAccountEntity {
    private String roleId;
    private Long regionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "GenerateAccountEntity{" +
                "roleId='" + roleId + '\'' +
                ", regionId=" + regionId +
                '}';
    }
}
