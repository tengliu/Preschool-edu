package com.DaDa.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DaDa.dao.WebDAO;
import com.DaDa.dao.impl.WebDBJdbcImpl;

import MyWeb.Web;

public class sprServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WebDAO webDAO = new WebDBJdbcImpl();
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String keyWord = req.getParameter("keyWord").trim();
		List<Web> webs = webDAO.getWeb(keyWord);
		req.setAttribute("webs", webs);
		req.getRequestDispatcher("/spr.jsp").forward(req, resp);
    }
}
