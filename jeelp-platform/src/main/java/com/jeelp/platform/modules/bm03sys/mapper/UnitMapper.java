package com.jeelp.platform.modules.bm03sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.jeelp.platform.common.mybatis.mapper.BaseMapper;
import com.jeelp.platform.modules.bm03sys.entity.UnitEntity;

/**
* @Title:              Unti
* @Description: TODO   机构代码管理
* @author                
* @date                2021-10-12
* @version             V1.0
*/
public interface UnitMapper extends BaseMapper<UnitEntity>{

	/**
	 * 根据父机构代码，获取下级最大代码
	 * @param pcode
	 * @return
	 */
	String getSubMaxCode(@Param("pcode") String pcode);
	
}