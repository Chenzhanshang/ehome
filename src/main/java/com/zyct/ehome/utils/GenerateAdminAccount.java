package com.zyct.ehome.utils;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Region;
import com.zyct.ehome.service.AdminService;
import com.zyct.ehome.service.GenerateAdminAccountService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * @author CZS
 * CreateTime 2019/12/6 11:41
 * Email 642125256@qq.com
 */

@Component
public class GenerateAdminAccount {
    //账号长度为admin加8位
    private Random random = new Random();
    @Autowired
    private GenerateAdminAccountService generateAdminAccountService;

    @Autowired
    private AdminService adminService;

    /**
     * 生成管理员账号
     * @return Admin
     */
    public Admin generateAccount(String roleId, Long regionId){
        String uuid = null;
        //生成uuid主键
        uuid = UUID.randomUUID().toString().replaceAll("-","");
        Admin admin = new Admin();
        admin.setAdminId(uuid);
        Region region = new Region();
        region.setRegionId(regionId);
        admin.setRegion(region);
        //账号前缀
        String prefix = "admin";
        while(true) {
            String account = prefix + random.nextInt(100000000);
            admin.setAdminAccount(account);

            //账户已存在
            if(adminService.getAdminByAccount(admin.getAdminAccount()) != null){
                continue;
            }
            else {
                //加密密码
                ByteSource bytes = ByteSource.Util.bytes(account);
                SimpleHash simpleHash = new SimpleHash("MD5","123456",bytes,1024);
                admin.setAdminPassword(simpleHash.toString());
                System.out.println(admin);
                generateAdminAccountService.insertAdmin(admin);
                generateAdminAccountService.insertAdminAndRole(admin.getAdminId(),roleId);

                return admin;
            }
        }
    }

}



