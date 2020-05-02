package com.supervet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandInterface {

    public void executar(HttpServletRequest req, HttpServletResponse res);

}
