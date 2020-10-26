package com.haoshuang.sso.demosso.controller;

import com.haoshuang.sso.demosso.common.validate.ValidateCodeBean.PageData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

    public class BaseController {
        public HttpServletRequest getRequest() {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            return request;
        }
        public HttpServletResponse getResponse() {

            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            return response;
        }


        public PageData getPageData(){
            PageData pd = new PageData(this.getRequest());
            return pd;
        }

        public HttpSession getCurrentSession(){
            HttpSession session = this.getRequest().getSession();
            return session;
        }

//    public String getCurrentUser(){
//        return (String) getCurrentSession().getAttribute("USERCODE");	// 大写的OA账号
//    }

        public void setSessionAttribute(String str, Object obj){
            getCurrentSession().setAttribute(str,obj);
        }
        public void removeSessionAttribute(String str){
            getCurrentSession().removeAttribute(str);
        }
        public ModelAndView getModelAndView(){
            return new ModelAndView();
        }


}
