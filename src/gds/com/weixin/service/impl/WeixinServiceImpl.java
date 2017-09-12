package gds.com.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import gds.com.weixin.dao.WeixinDao;
import gds.com.weixin.service.WeixinService;

/**
 * 文件名：微信WeixinService接口实现类<br>
 * 版权：Copyright (c) 2017 刘云鹏<br>
 * 描述：微信测系统，主要包括微信的access_token、管理通讯录、发送微信消息、微信JS-SDK等操作。<br>
 * 修改人：Author: liuyunpeng<br>
 * 版本：Revision: 1.0
 */
@Service
public class WeixinServiceImpl implements WeixinService{

	/** 微信DAO */
	@Autowired
	@Qualifier("weixinDaoImpl")
	private  WeixinDao weixinDaoImpl;
	
	
}
