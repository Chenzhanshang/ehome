package com.zyct.ehome.config.exception;

import com.zyct.ehome.utils.ErrorEnum;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author JGZ
 * CreateTime 2019/12/5 9:36
 * Email 1945282561@qq.com
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    /**
     * 权限不足返回信息
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseMessage unauthorizedExceptionHandler(){
        return new ResponseMessage(ErrorEnum.E_UNAUTHORIZED.getErrorCode(),
                ErrorEnum.E_UNAUTHORIZED.getErrorMsg());
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseMessage unauthenticatedException() {
        return new ResponseMessage(ErrorEnum.E_UNAUTHENTICATED.getErrorCode(),
                ErrorEnum.E_UNAUTHENTICATED.getErrorMsg());
    }

}
