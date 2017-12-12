package com.epam.trainings.testauto.xml.wsdl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import static javax.jws.soap.SOAPBinding.Style.RPC;

@WebService
@SOAPBinding(style= RPC)
public class Hello {
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
