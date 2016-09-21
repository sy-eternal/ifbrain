package com.jzeen.travel.admin.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jzeen.travel.data.entity.Member;
import com.jzeen.travel.data.entity.Menu;
import com.jzeen.travel.data.repository.MenuRepository;
import com.mysql.jdbc.StringUtils;

@Component
public class LoginFilter implements Filter
{
    @Autowired
    private MenuRepository _menuRepository;

    /**
     * 登录地址。
     */
    private final String _loginPath = "/member/login";
    private final String _activePah="/guidemanage/emailactivation";
    //后台验证码Controller地址
    private final String _imagePath="/codeimg/service";
    //图片
    private final String _materialimg="/materialimg";
    //图片
    private final String _parentmanualimage="/parentmanualimage/show";
    
    private final String _material="/material/uploadimg";
    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException
    {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        session.setMaxInactiveInterval(30*60);
        // 获得用户请求的URI
        String requestPath = servletRequest.getRequestURI();
        if (!needFilter(requestPath))
        {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 从session里取员工工号信息
        Member member = (Member) session.getAttribute("member");

        // 判断如果没有取到员工信息，或者对当前页面没有访问权限，就跳转到登陆页面
        if (member == null || !canVisitUrl(member.getId(), requestPath))
        {
            //
            // 跳转到登录页面
            String queryString = servletRequest.getQueryString();
            String returnUrl = requestPath;
            if (!StringUtils.isNullOrEmpty(queryString))
            {
                returnUrl = requestPath + "?" + queryString;
            }
            returnUrl = URLEncoder.encode(returnUrl, "UTF-8");

            servletResponse.sendRedirect(_loginPath + "?returnUrl=" + returnUrl);
        }
        else
        {
            // 已经登录,继续此次请求
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy()
    {
    }

    private boolean canVisitUrl(Integer memberId, String url)
    {
        // 请求路径没有在菜单地址中出现过，则有权访问
        Menu menu = _menuRepository.findByUrl(url);
        if (menu == null)
        {
            return true;
        }
        long count = _menuRepository.countByMemberIdAndUrl(memberId, url);
        return (count > 0);
    }
    

    
  

    /**
     * 请求地址是否需要过滤。
     * 
     * @param requestPath
     *            请求地址。
     * @return 请求地址是否需要过滤。
     */
    private boolean needFilter(String requestPath)
    {
        // 登录页面无需过滤
        if (requestPath.toLowerCase().startsWith(_loginPath))
        {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_activePah))
        {
            return false;
        }
     
        if (requestPath.toLowerCase().startsWith(_materialimg))
        {
           
            return  false;
        }
        if (requestPath.toLowerCase().startsWith(_parentmanualimage))
        {
           
            return  false;
        }
        if (requestPath.toLowerCase().startsWith(_imagePath))
        {
            return false;
        }
        if (requestPath.toLowerCase().startsWith(_material))
        {
            return false;
        }
        // 静态资源无需过滤
        if (requestPath.toLowerCase().startsWith("/assets/") || requestPath.toLowerCase().startsWith("/css/")
                || requestPath.toLowerCase().startsWith("/css/") || requestPath.toLowerCase().startsWith("/img/")
                || requestPath.toLowerCase().startsWith("/js/") || requestPath.toLowerCase().startsWith("/webjars/"))
        {
            return false;
        }

        return true;
    }
}
