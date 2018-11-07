package com.huangph.service.impl;

import com.huangph.dao.UserinfoDao;
import com.huangph.entity.Userinfo;
import com.huangph.service.UserinfoService;
import com.softdev.system.generator.entity.ReturnT;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 用户信息
* @author huangph 2018-10-26
*/
@Service
public class UserinfoServiceImpl implements UserinfoService {

	@Resource
	private UserinfoDao userinfoDao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(Userinfo userinfo) {

		// valid
		if (userinfo == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		userinfoDao.insert(userinfo);
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = userinfoDao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(Userinfo userinfo) {
		int ret = userinfoDao.update(userinfo);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public Userinfo load(int id) {
		return userinfoDao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<Userinfo> pageList = userinfoDao.pageList(offset, pagesize);
		int totalCount = userinfoDao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
