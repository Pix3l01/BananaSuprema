package com.BiF.BananaSuprema.BananaSuprema.Servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "CookieLocaleFilter", urlPatterns = {"/*"})
public class CookieLocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        boolean langSelected = false;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("lang")) {
                    langSelected = true;
                }
            }
        }
        if (cookies == null || !langSelected) {
            Cookie cookie = new Cookie("lang", req.getLocale().toString().substring(0, 2));
            cookie.setMaxAge(60 * 60 * 24 * 365);
            res.addCookie(cookie);
        }
        if (req.getParameter("cookieLocale") != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("lang")) {
                    c.setValue(req.getParameter("cookieLocale"));
                    res.addCookie(c);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}

