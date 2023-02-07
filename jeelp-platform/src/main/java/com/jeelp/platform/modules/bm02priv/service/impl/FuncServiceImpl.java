package com.jeelp.platform.modules.bm02priv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.common.mybatis.service.impl.BaseServiceImpl;
import com.jeelp.platform.modules.bm02priv.entity.FuncEntity;
import com.jeelp.platform.modules.bm02priv.mapper.FuncMapper;
import com.jeelp.platform.modules.bm02priv.service.FuncService;
import com.jeelp.platform.modules.bm02priv.viewobject.MenuMetaVo;
import com.jeelp.platform.modules.bm02priv.viewobject.MenuVo;

import cn.hutool.core.collection.CollectionUtil;

/**
* @Title:              FuncServiceImpl
* @Description: TODO   菜单管理
* @author              
* @date                2021-09-22
* @version             V1.0
*/
@Service
public class FuncServiceImpl extends BaseServiceImpl<FuncEntity> implements FuncService{

	private final FuncMapper mapper;

    public FuncServiceImpl(FuncMapper mapper) {
        this.mapper = mapper;
    }

    @Override
	public BaseMapper<FuncEntity> getMapper(){
		return mapper;
	}
	
	
	@Override
    public List<FuncEntity> tree(Map<String, Object> params){
	    List<FuncEntity> funcs = mapper.findAll(params);
        if(funcs != null){
            for(FuncEntity func: funcs){
                params.put("pid",func.getId());
                func.setChildren(tree(params));
            }
        }
        return funcs;
    }
	
	
	@Override
    public List<MenuVo> getMenus(Map<String, Object> params){
	    List<FuncEntity> funcs = tree(params);
        return buildMenus(funcs);
    }
	
	
	private List<MenuVo> buildMenus(List<FuncEntity> funcs) {
		List<MenuVo> vos = new ArrayList<MenuVo>();
        if(funcs != null){
            for(FuncEntity func:funcs){
                MenuVo menuVo = new MenuVo();
                menuVo.setName(func.getFuncName());
                menuVo.setPath(("-1".equals(func.getUpFuncUuid())?"/":"")+func.getFuncAddr());
                menuVo.setMeta(new MenuMetaVo(func.getFuncShortName(), func.getIcon()));
                if("-1".equals(func.getUpFuncUuid())){
                    menuVo.setComponent(StringUtils.isEmpty(func.getComponent()) ? "Layout" : func.getComponent());
                }else if("0".equals(func.getIsLeaf())){
                    menuVo.setComponent(StringUtils.isEmpty(func.getComponent()) ? "ParentView" : func.getComponent());
                }else if (StringUtils.isNoneBlank(func.getComponent())) {
                    menuVo.setComponent(func.getComponent());
                }
                List<FuncEntity> chlidren = func.getChildren();
                if(CollectionUtil.isNotEmpty(chlidren)){
                    menuVo.setAlwaysShow(true);
                    menuVo.setRedirect("noredirect");
                    menuVo.setChildren(buildMenus(chlidren));
                }else if ("-1".equals(func.getUpFuncUuid())) {
                    MenuVo menuVo1 = new MenuVo();
                    menuVo1.setMeta(menuVo.getMeta());
                    menuVo1.setPath(func.getFuncAddr());
                    menuVo.setName(null);
                    menuVo.setMeta(null);
                    menuVo.setComponent("Layout");
                    List<MenuVo> list1 = new ArrayList<>();
                    list1.add(menuVo1);
                    menuVo.setChildren(list1);
                }
                vos.add(menuVo);
            }
        }
		return vos;
	}

}
