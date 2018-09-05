package com.sbikchentaev;

import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    @PostMapping(path = "/calculator")
    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        try {
            JaxbClass.prod(req.getReader(), rsp.getWriter());
        } catch (JAXBException e) {
            rsp.sendError(rsp.SC_BAD_REQUEST, "XML reading error");
        }
    }
}
