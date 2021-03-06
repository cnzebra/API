package cn.localhost01.controller;

import cn.localhost01.annotation.RequestFormBody;
import cn.localhost01.constant.APICode;
import cn.localhost01.constant.APIMsg;
import cn.localhost01.constant.APISubCode;
import cn.localhost01.constant.APISubMsg;
import cn.localhost01.domain.JsonVO;
import cn.localhost01.domain.MailDO;
import cn.localhost01.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:邮件控制层
 * @Author Ran.chunlin
 * @Date: Created in 19:29 2017/12/16
 */
@RestController public class MailController {

    @Autowired private MailService mailService;

    @RequestMapping(value = "/mail") public JsonVO sendMail(@RequestFormBody MailDO mailDO) throws Exception {
        JsonVO jsonVO = new JsonVO();

        //1.进行校验
        if (mailDO == null || mailDO.getReceiver() == null)
            return jsonVO.withBase(APICode.BAD_REQUEST, APIMsg.BAD_REQUEST);

        //2.业务处理
        boolean isSendOk = mailService.send(mailDO);
        if (isSendOk)
            return jsonVO.withSub(APISubCode.SUCCESS, APISubMsg.SUCCESS);
        else
            return jsonVO.withSub(APISubCode.FAILED, APISubMsg.FAILED);
    }
}
