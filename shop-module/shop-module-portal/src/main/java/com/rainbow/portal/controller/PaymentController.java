package com.rainbow.portal.controller;

import com.rainbow.api.dto.ParentOrderNoDTO;
import com.rainbow.api.vo.UploadImageVO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.IPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 支付接口
 *
 * @author lujinwei
 * @since 2020/4/7
 */
@RestController
@RequestMapping("/payment")
@Api(value = "/payment", tags = "支付服务")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @ApiOperation(value = "创建支付二维码", notes = "创建支付二维码", httpMethod = "POST")
    @PostMapping("/createQrCodeImage")
    public R<UploadImageVO> createQrCodeImage(@Valid @RequestBody ParentOrderNoDTO param) {
        param.setCustomerId(RB.getUserId());
        return new R<>(paymentService.createPayQrCodeImage(param));
    }


    @ApiOperation(value = "支付宝支付成功回调", notes = "支付宝支付成功回调", httpMethod = "POST")
    @PostMapping("/alipayCallback")
    public R<Boolean> alipayCallback(HttpServletRequest request) {
        return new R<>(paymentService.paySucessCallback(RB.getUserId(), request));
    }

}
