package com.jfeat.am.module.payment.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.payment.services.crud.service.CRUDPaymentAppService;
import com.jfeat.am.module.payment.services.persistence.dao.PaymentAppMapper;
import com.jfeat.am.module.payment.services.persistence.model.PaymentApp;
import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.crud.plus.model.IdVersions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 * CRUDPaymentAppService
 *
 * @author Code Generator
 * @since 2018-10-18
 */

@Service
public class CRUDPaymentAppServiceImpl extends CRUDServiceOnlyImpl<PaymentApp> implements CRUDPaymentAppService {


    @Resource
    protected PaymentAppMapper paymentAppMapper;

    @Override
    protected BaseMapper<PaymentApp> getMasterMapper() {
        return paymentAppMapper;
    }


}


