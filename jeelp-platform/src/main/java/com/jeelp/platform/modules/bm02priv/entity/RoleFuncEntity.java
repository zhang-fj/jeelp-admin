package com.jeelp.platform.modules.bm02priv.entity;

public class RoleFuncEntity {
	
	private String roleId;
	
	private String funcId;

	public RoleFuncEntity(String roleId, String funcId) {
		this.roleId = roleId;
		this.funcId = funcId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

}
