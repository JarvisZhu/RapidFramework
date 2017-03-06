package com.rapid.framework.web.scope;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class FlashFilter extends OncePerRequestFilter implements Filter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            Flash.setCurrent(Flash.restore(request));
            request.setAttribute("flash", Flash.current().getData());
            chain.doFilter(request, response);
        } finally {
            Flash flash = Flash.current();
            Flash.setCurrent(null);
            if (flash != null) {
                flash.save(request, response);
            }
        }
    }
}

