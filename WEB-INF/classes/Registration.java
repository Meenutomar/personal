import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public  class Registration extends HttpServlet {

    private RegistrationBackend backend;

    @Override
    public void init() throws ServletException {
        super.init();
        backend = new RegistrationBackend();
    }

  
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //super.doPost(req, resp);
      
            PrintWriter writer = resp.getWriter();
            Person person = new Person();
            person.setName(req.getParameter("name"));
            person.setEmail(req.getParameter("email"));
            person.setPassword(req.getParameter("password"));
            try {
            int num = backend.registerUser(person);
            writer.println("User Registered Successfully " + num + " !");
           
            RequestDispatcher dispatch = req.getRequestDispatcher("welcome");
            dispatch.forward(req, resp);
            } catch(PersonException exc){
                writer.println("Sorry, this user already exists! Try Another");
            }
            writer.close();
    }
   
}

 
    

