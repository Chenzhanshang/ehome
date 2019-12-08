package com.zyct.ehome.utils;

import com.zyct.ehome.dao.AdminMapper;
import com.zyct.ehome.entity.Admin;
import org.junit.jupiter.api.Test;
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
    private int accountSize = 8;
    private Random random = new Random();
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 生成管理员账号
     * @return Admin
     */
    public Admin generateAccount(){
        String uuid = null;
        //生成uuid主键
        uuid = UUID.randomUUID().toString().replaceAll("-","");
        Admin admin = new Admin();
        admin.setAdminId(uuid);
        //账号前缀
        String prefix = "admin";
        while(true) {
            String account = prefix + random.nextInt(100000000);
            admin.setAdminAccount(account);
            //账户已存在
            if(adminMapper.getAdminByAccount(admin.getAdminAccount()) != null){
                continue;
            }
            else {
                System.out.println(admin);
                adminMapper.insertAdmin(admin);
                return admin;
            }
        }
    }

}



