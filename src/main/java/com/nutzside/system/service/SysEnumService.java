package com.nutzside.system.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.nutzside.common.domain.AjaxResData;
import com.nutzside.common.domain.jqgrid.AdvancedJqgridResData;
import com.nutzside.common.domain.jqgrid.JqgridReqData;
import com.nutzside.common.service.BaseService;
import com.nutzside.common.util.StrUtils;
import com.nutzside.system.domain.SysEnum;

@IocBean(args = { "refer:dao" })
public class SysEnumService extends BaseService<SysEnum> {

	public SysEnumService(Dao dao) {
		super(dao);
	}

	@Aop(value = "log")
	public AdvancedJqgridResData<SysEnum> getGridData(JqgridReqData jqReq, Boolean isSearch, SysEnum sysEnumSearch) {
		Cnd cnd = null;
		if (null != isSearch && isSearch && null != sysEnumSearch) {
			cnd = Cnd.where("1", "=", 1);
			String name = sysEnumSearch.getName();
			if (!Strings.isEmpty(name)) {
				cnd.and("NAME", "LIKE", StrUtils.quote(name, '%'));
			}
			String description = sysEnumSearch.getDescription();
			if (!Strings.isEmpty(description)) {
				cnd.and("DESCRIPTION", "LIKE", StrUtils.quote(description, '%'));
			}
		}
		AdvancedJqgridResData<SysEnum> jq = getAdvancedJqgridRespData(null, jqReq);
		return jq;
	}

	@Aop(value = "log")
	public AjaxResData CUDSysEnum(String oper, String ids, SysEnum sysEnum) {
		AjaxResData respData = new AjaxResData();
		if ("del".equals(oper)) {
			final Condition cnd = Cnd.where("ID", "IN", ids.split(","));
			final List<SysEnum> sysEnums = query(cnd, null);
			Trans.exec(new Atom() {
				public void run() {
					for (SysEnum sysEnum : sysEnums) {
						dao().clearLinks(sysEnum, "items");
					}
					clear(cnd);
				}
			});
			respData.setInfo("删除成功!");
		} else if ("add".equals(oper)) {
			dao().insert(sysEnum);
			respData.setInfo("添加成功!");
		} else if ("edit".equals(oper)) {
			dao().update(sysEnum);
			respData.setInfo("修改成功!");
		} else {
			respData.setError("未知操作!");
		}
		return respData;
	}
}