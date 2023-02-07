package com.jeelp.platform.modules.bm02priv.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.jeelp.platform.common.exception.BadRequestException;
import com.jeelp.platform.common.mybatis.exception.MessageRuntimeException;
import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm01login.utils.UserinfoUtils;
import com.jeelp.platform.modules.bm02priv.entity.UserEntity;
import com.jeelp.platform.modules.bm02priv.mapper.PostRoleMapper;
import com.jeelp.platform.modules.bm02priv.mapper.PostUserMapper;
import com.jeelp.platform.modules.bm02priv.mapper.UserMapper;
import com.jeelp.platform.modules.bm02priv.service.FuncService;
import com.jeelp.platform.modules.bm02priv.service.UserService;
import com.jeelp.platform.modules.bm02priv.viewobject.EditPassword;

/**
* @Title:              UserServiceImpl
* @Description: TODO   用户管理管理
* @author              
* @date                2021-09-22
* @version             V1.0
*/
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity> implements UserService{

	private final UserMapper mapper;
	private final PostUserMapper postUsermapper;
	private final PostRoleMapper postRoleMapper;
	private final FuncService funcService;
  
	
    public UserServiceImpl(UserMapper mapper, PostUserMapper postUsermapper, PostRoleMapper postRoleMapper,FuncService funcService) {
		super();
		this.mapper = mapper;
		this.postUsermapper = postUsermapper;
		this.postRoleMapper = postRoleMapper;
		this.funcService = funcService;
	}

	@Override
	public BaseMapper<UserEntity> getMapper(){
		return mapper;
	}

	@Override
	public Integer insert(UserEntity entity) {
		entity.setUserPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
		return super.insert(entity);
	}

	@Override
	public UserEntity findByUserName(String username) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userName", username);
		UserEntity user = mapper.selectByPropertys(params);
		user.setRoles(mapper.getRolesByUserId(user.getUserId()));
		return user;
	}

	@Override
	public Map<String, Object> getUserinfo(String id) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		// 获取用户基本信息
		result.put("user", selectByPK(id));
		
		
		Map<String,Object> params= new HashMap<>();
		params.put("userId", id);
		
		// 获取用户岗位信息
		result.put("posts", postUsermapper.findAll(params));
		
		// 获取用户角色信息
		result.put("roles", postRoleMapper.findAll(params));
		
		// 获取功能树
		params.put("pid", "-1");
		result.put("funcs", funcService.tree(params));
		
		return result;
	}

	@Override
	public void editPassword(EditPassword editPassword) {
		
		UserEntity user = selectByPK(UserinfoUtils.getUserId());
		
		// 判断原密码是否正确
		if(!user.getUserPassword().equals(DigestUtils.md5DigestAsHex(editPassword.getPassword().getBytes()))){
			throw new MessageRuntimeException("原密码不正确！", null);
		}
		
		// 修改密码
		user.setUserPassword(DigestUtils.md5DigestAsHex(editPassword.getNewPassword().getBytes()));
		update(user);
		
	}

}
