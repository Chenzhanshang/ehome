package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Apply;
import com.zyct.ehome.entity.File;
import com.zyct.ehome.service.ApplyManageService;
import com.zyct.ehome.service.FileManageService;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/11 14:51
 * Email 1945282561@qq.com
 */
@Controller
@RequestMapping("/admin")
public class ApplyManageController {

    @Autowired
    private ApplyManageService applyManageService;

    @Autowired
    private FileManageService fileManageService;

    @RequestMapping(value = "/getAdminExamineList", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage getAdminExamineList() {
        //获取登录的用户对象
        Subject subject = SecurityUtils.getSubject();
        //转换成为admin对象
        Admin admin = (Admin) subject.getPrincipal();
        //获取当前管理员能够看到的申请信息
        try {
            List<Apply> list = applyManageService.getCurrentAdminExamineList(admin);
            ResponseMessage responseMessage = new ResponseMessage("0", "获取待审批列表成功");
            responseMessage.getData().put("applyList", list);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage("-1", "获取待审批列表失败");
        }
    }

    /**
     * 通过id获取审批信息
     *
     * @param applyId
     * @return
     */
    @RequestMapping(value = "getApply/{applyId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getApply(@PathVariable("applyId") String applyId, HttpServletRequest request) {
        try {
            Apply apply = applyManageService.getApply(applyId);
            //设置文件访问路径
            List<File> files = apply.getFiles();
            for (File file : files) {
                file.setFilePath("http://localhost:8081" + request.getContextPath() + "/file/" + file.getFileName());
            }
            ResponseMessage responseMessage = new ResponseMessage("0", "获取审批信息成功");
            responseMessage.getData().put("applyInfo", apply);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage("-1", "获取审批信息失败");
        }

    }

    /**
     * 文件下载方法
     * @param file
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage downloadFile(@RequestBody File file, HttpServletResponse response) {
        //获取文件的信息
        try {
            File dbFile = fileManageService.getFileById(file.getFileId());
            //拿到文件路径
            String filePath = dbFile.getFilePath();
            java.io.File f = new java.io.File(filePath);
            if (f.exists()) {
                // 设置强制下载不打开
//                response.setContentType("application/force-download");
                // 设置文件名
//                response.addHeader("Content-Disposition", "attachment;fileName=" + file.getFileName());
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(f);
                    bis = new BufferedInputStream(fis);
                    //从响应中拿到输出流对象
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return new ResponseMessage("0", "下载文件成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseMessage("-1", "下载文件失败");
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return new ResponseMessage("-1", "下载文件不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage("-1", "下载文件失败");
        }
    }

    /**
     * 申请处理表文件下载方法
     * @param filePath
     * @param response
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage download(@RequestBody File file, HttpServletResponse response) {
        String filePath = file.getFilePath();
        //获取文件的信息
        try {
            java.io.File f = new java.io.File(filePath);
            if (f.exists()) {
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(f);
                    bis = new BufferedInputStream(fis);
                    //从响应中拿到输出流对象
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return new ResponseMessage("0", "下载文件成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseMessage("-1", "下载文件失败");
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return new ResponseMessage("-1", "下载文件不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage("-1", "下载文件失败");
        }
    }

}
