package com.jeelp.platform.modules.bm01login.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.jeelp.platform.modules.bm01login.config.bean.SecurityProperties;
import com.jeelp.platform.modules.bm01login.service.OnlineUserService;
import com.jeelp.platform.modules.bm01login.viewobject.OnlineUser;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * 登录校验filter
 */
@WebFilter(filterName = "TokenFilter", urlPatterns = { "/*" })
public class TokenFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);

    @Value("#{'${token.filter.excludes:\"\"}'.split(',')}")
    public List<String> excludes;

    private final TokenProvider tokenProvider;
    private final SecurityProperties properties;
    private final OnlineUserService onlineUserService;

    /**
     * @param tokenProvider     Token
     * @param properties        JWT
     * @param onlineUserService 用户在线
     */
    public TokenFilter(TokenProvider tokenProvider, SecurityProperties properties, OnlineUserService onlineUserService) {
        this.properties = properties;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
    }
    
    

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
    	System.out.println("----TokenFilter过滤器初始化----");
	}



	@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = resolveToken(httpServletRequest);

        if(excludes!=null){
            for(String exclude:excludes){
                if(exclude != null && !"".equals(exclude.trim())){
                    String path = ((HttpServletRequest) servletRequest).getServletPath();
                    if(path.contains(exclude)){
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    }
                }
            }
        }

        // 对于 Token 为空的不需要去查 Redis
        if (StrUtil.isNotBlank(token)) {
            OnlineUser onlineUser = null;
            try {
                onlineUser = onlineUserService.getOne(properties.getOnlineKey() + token);
            } catch (ExpiredJwtException e) {
                log.error(e.getMessage());
            } finally {
            }
            
            // 如果在redis中没有查询到登录信息，则返回登录超时错误
            if(onlineUser == null){
              ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "登录超时！");
            }
            
            if (StringUtils.hasText(token)) {
                // Token 续期
                tokenProvider.checkRenewal(token);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 初步检测Token
     * @param request /
     * @return /
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(properties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(properties.getTokenStartWith(), "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }
   
}
