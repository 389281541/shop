package com.rainbow.portal.controller;

import com.rainbow.api.dto.CustomerAddressSaveDTO;
import com.rainbow.api.dto.CustomerAddressUpdateDTO;
import com.rainbow.api.vo.CustomerAddressVO;
import com.rainbow.common.annotation.NeedLogin;
import com.rainbow.common.dto.IdDTO;
import com.rainbow.common.dto.R;
import com.rainbow.common.dto.token.RB;
import com.rainbow.portal.service.IAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 顾客地址表 前端控制器
 *
 * @author lujinwei
 * @since 2020-02-29
 */
@RestController
@RequestMapping("/customerAddress")
@Api(value = "/customerAddress", tags = "收货地址")
public class AddressController {

    @Autowired
    private IAddressService addressService;


    @ApiOperation(value = "修改收货地址", notes = "修改收货地址", httpMethod = "POST")
    @PostMapping("/update")
    @NeedLogin
    public R<Boolean> updateAddress(@Valid @RequestBody CustomerAddressUpdateDTO param) {
        param.setCustomerId(RB.getUserId());
        Integer res = addressService.updateAddress(param);
        return new R<>(res > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "添加收货地址", notes = "添加收货地址", httpMethod = "POST")
    @PostMapping("/add")
    @NeedLogin
    public R<Boolean> addAddress(@Valid @RequestBody CustomerAddressSaveDTO param) {
        param.setCustomerId(RB.getUserId());
        Integer res = addressService.addAddress(param);
        return new R<>(res > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @ApiOperation(value = "查看收货地址", notes = "查看收货地址", httpMethod = "POST")
    @PostMapping("/get")
    @NeedLogin
    public R<CustomerAddressVO> get(@Valid @RequestBody IdDTO param) {
        return new R<>(addressService.getAddressDetail(param));
    }


    @ApiOperation(value = "删除收货地址", notes = "删除收货地址", httpMethod = "POST")
    @PostMapping("/remove")
    @NeedLogin
    public R<Boolean> remove(@Valid @RequestBody IdDTO param) {
        Integer res = addressService.removeAddress(param);
        return new R<>(res > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    @ApiOperation(value = "收货地址列表", notes = "收货地址列表", httpMethod = "POST")
    @PostMapping("/list")
    @NeedLogin
    public R<List<CustomerAddressVO>> list() {
        return new R<>(addressService.listAddress(RB.getUserId()));
    }

    @ApiOperation(value = "设为默认", notes = "设为默认", httpMethod = "POST")
    @PostMapping("/setDefault")
    @NeedLogin
    public R<Boolean> setDefault(@Valid @RequestBody IdDTO param) {
        return new R<>(addressService.setDefault(RB.getUserId(), param.getId()));
    }

}
