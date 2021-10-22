package hello.servlet.web.sevletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
            - WEB-INF의 자원들은 서버에서 내부적으로 호출해야 이동할 수 있음
            클라이언트에서 절대결로로 요청할 수 없음

            - redirect vs forward
            redirect는 실제로 클라이언트에 응답을 내리고 헤더의 location 정보를 따라 다시 서버에 location을 호출한다
            url 변경이 일어남
            forward는 서버 내부에서 지정된 경로를 다시 호출하는 것이기 때문에 클라이언트는 인지할 수 없음.
            url 변경되지 않음
         */


        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
