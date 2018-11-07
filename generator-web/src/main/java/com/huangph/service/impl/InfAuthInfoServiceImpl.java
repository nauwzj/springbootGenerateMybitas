package com.huangph.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 第三方接口鉴权表
* @author huangph 2018-11-06
*/
@Service
public class InfAuthInfoServiceImpl implements InfAuthInfoService {

	@Resource
	private InfAuthInfoDao infAuthInfoDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(InfAuthInfo infAuthInfo) {

		// valid
		if (infAuthInfo == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		infAuthInfoDao.insert(infAuthInfo);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = infAuthInfoDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(InfAuthInfo infAuthInfo) {
		int ret = infAuthInfoDao.update(infAuthInfo);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public InfAuthInfo load(int id) {
		return infAuthInfoDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<InfAuthInfo> pageList = infAuthInfoDao.pageList(offset, pagesize);
		int totalCount = infAuthInfoDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
