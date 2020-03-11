package com.crowdfunding.ajax;

import com.crowdfunding.domain.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AjaxResult {

	//是否成功
	private boolean success ;
	//提示返回信息
	private String message ;

	//分页数据   PageInfo对象 由pageHelper提供
	private PageInfo pageInfo;

	//回显数据
	private Object Data;

	//用于封装根据id查询出来的角色信息
	private List<Role> list;

	public AjaxResult() {
	}

	public AjaxResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}
}
