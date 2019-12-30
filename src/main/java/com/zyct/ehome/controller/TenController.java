package com.zyct.ehome.controller;

import com.zyct.ehome.dto.AdviseDto;
import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.dto.NoticeDto;
import com.zyct.ehome.entity.Advise;
import com.zyct.ehome.entity.Fix;
import com.zyct.ehome.entity.Notice;
import com.zyct.ehome.entity.Ten;
import com.zyct.ehome.service.TenService;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 09:55
 */
@RequestMapping("/ten")
@Controller
public class TenController {


    @Autowired
    private TenService tenService;

    /**
     * 用户进行维修
     *
     * @param fix
     * @return
     */
    @RequestMapping("/fix")
    @ResponseBody
    public ResponseMessage fix(@RequestBody Fix fix) {
        String uuid = UUID.randomUUID().toString();
        fix.setFixId(uuid);
        fix.setFlag(0);
        System.out.println(fix.toString());
        tenService.insertFix(fix);
        return new ResponseMessage("0", "报修成功");
    }

    /**
     * 获取待维修列表
     *
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/fixList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage fixList(@RequestParam("communityId") String communityId) {
        //通过小区id查询待维修列表
        List<FixDto> fixDtoList = tenService.fixList(communityId);
        ResponseMessage responseMessage = new ResponseMessage("0", "请求成功");
        Map<String, Object> map = new HashMap<>();
        map.put("fixDtoList", fixDtoList);
        responseMessage.setData(map);
        return responseMessage;
    }

    /**
     * 获取已维修列表，历史维修列表
     *
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/fixedList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage fixedList(@RequestParam("communityId") String communityId) {
        //通过小区id查询待维修列表
        List<FixDto> fixedDtoList = tenService.fixedList(communityId);
        ResponseMessage responseMessage = new ResponseMessage("0", "请求成功");
        Map<String, Object> map = new HashMap<>();
        map.put("fixedDtoList", fixedDtoList);
        responseMessage.setData(map);
        return responseMessage;
    }

    /**
     * 已通知维修人员上门维修
     *
     * @param fix
     * @return
     */
    @RequestMapping("/doJob")
    @ResponseBody
    public ResponseMessage doJob(@RequestBody Fix fix) {
        //将维修状态更新
        fix.setFlag(1);
        tenService.updateFix(fix);
        return new ResponseMessage("0", "操纵成功");
    }

    /**
     * 发布公告
     *
     * @param noticeDto
     * @return
     */
    @RequestMapping("/putNotice")
    @ResponseBody
    public ResponseMessage putNotice(@RequestBody NoticeDto noticeDto) {
        if (noticeDto.getTitle() == null || noticeDto.getTitle().equals("")) {
            return new ResponseMessage("-1", "发布失败,标题不能为空");
        } else if (noticeDto.getContent() == null || noticeDto.getContent().equals("")) {
            return new ResponseMessage("-1", "发布失败,内容不能为空");
        } else {
            //封装notice
            Notice notice = new Notice();
            notice.setTitle(noticeDto.getTitle());
            notice.setContent(noticeDto.getContent());
            notice.setCommunityId(noticeDto.getManagerDto().getCommunityId());
            notice.setType(noticeDto.getManagerDto().getType());
            //插入数据库
            tenService.putNotice(notice);
            return new ResponseMessage("0", "发布成功");
        }


    }

    /**
     * 通过小区id和通知类型查询通知
     * @param communityId
     * @param type
     * @return
     */
    @RequestMapping("/getNoticeListByType")
    @ResponseBody
    public ResponseMessage getNoticeListByType(@RequestParam("communityId")String communityId,
                                               @RequestParam("type")Integer type){
        List<Notice> noticeList = tenService.getNoticeListByType(communityId,type);
        if (noticeList == null){
            return new ResponseMessage("-1","查不到数据");
        }else {
            ResponseMessage responseMessage = new ResponseMessage("0","操纵成功");
            Map<String,Object> map = new HashMap<>();
            map.put("noticeList",noticeList);
            responseMessage.setData(map);
            return responseMessage;
        }

    }

    /**
     * 通过小区id和通知类型查询通知
     * @param communityId
     * @return
     */
    @RequestMapping("/getNoticeList")
    @ResponseBody
    public ResponseMessage getNoticeList(@RequestParam("communityId")String communityId){
        List<Notice> noticeList = tenService.getNoticeList(communityId);
        if (noticeList == null){
            return new ResponseMessage("-1","查不到数据");
        }else {
            ResponseMessage responseMessage = new ResponseMessage("0","操纵成功");
            Map<String,Object> map = new HashMap<>();
            map.put("noticeList",noticeList);
            responseMessage.setData(map);
            return responseMessage;
        }

    }

    /**
     * 删除公告
     * @param notice
     * @return
     */
    @RequestMapping("/deleteNotice")
    @ResponseBody
    public ResponseMessage deleteNotice(@RequestBody Notice notice){

        System.out.println(notice.toString());
        tenService.deleteNotice(notice);
        return new ResponseMessage("0","操作成功");
    }

    @RequestMapping("/updateNotice")
    @ResponseBody
    public ResponseMessage updateNotice(@RequestBody NoticeDto noticeDto){
        System.out.println(noticeDto.toString());
        if (noticeDto.getTitle() == null || noticeDto.getTitle().equals("")) {
            return new ResponseMessage("-1", "修改失败,标题不能为空");
        } else if (noticeDto.getContent() == null || noticeDto.getContent().equals("")) {
            return new ResponseMessage("-1", "修改失败,内容不能为空");
        } else {
            //封装notice
            Notice notice = new Notice();
            notice.setNoticeId(noticeDto.getNoticeId());
            notice.setTitle(noticeDto.getTitle());
            notice.setContent(noticeDto.getContent());
            notice.setCommunityId(noticeDto.getManagerDto().getCommunityId());
            notice.setType(noticeDto.getManagerDto().getType());
            //插入数据库
            tenService.updateNotice(notice);
            return new ResponseMessage("0", "发布成功");
        }

    }

    /**
     * 投诉建议接口
     * @param advise
     * @return
     */
    @RequestMapping("/advise")
    @ResponseBody
    public ResponseMessage advise(@RequestBody Advise advise){
        System.out.println(advise.toString());
        tenService.insertAdvise(advise);
        return new ResponseMessage("0","操纵成功");
    }

    @RequestMapping("/adviseList")
    @ResponseBody
    public ResponseMessage adviseList(@RequestParam("communityId")String communityId){
        System.out.println(communityId);
        List<AdviseDto> adviseList = tenService.getAdviseList(communityId);
        ResponseMessage responseMessage = new ResponseMessage("0","操纵成功");
        Map<String,Object> map = new HashMap<>();
        map.put("adviseList",adviseList);
        responseMessage.setData(map);
        return responseMessage;
    }

}
