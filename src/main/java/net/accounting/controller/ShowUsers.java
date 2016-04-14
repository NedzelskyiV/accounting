package net.accounting.controller;

import net.accounting.Users.User;
import net.accounting.dao.Exceptions.NoSuchUserException;
import net.accounting.dao.impl.SearchUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 14-Apr-16.
 */
public class ShowUsers extends HttpServlet {
    public static final String USER_ID = "id";
    public static final String USER_NAME = "Name";
    public static final String ATTRIBUTE_MODEL_TO_SHOW = "user";
    public static final String PAGE_OK = "user.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    SearchUserDao user = new SearchUserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter(USER_ID);
        if (strId != null) {
            Integer id = Integer.valueOf(strId);
            try {
                User myUser = user.selectByID(id);
                request.setAttribute(ATTRIBUTE_MODEL_TO_SHOW, myUser);
                request.getRequestDispatcher(PAGE_OK).forward(request,response);
                return;
            } catch (NoSuchUserException e) {
              /*NOP*/
            }

        }
        response.sendRedirect(PAGE_ERROR);
    }
}