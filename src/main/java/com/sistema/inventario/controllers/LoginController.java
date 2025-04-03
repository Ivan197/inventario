package com.sistema.inventario.controllers;

import com.sistema.inventario.entities.Login;
import com.sistema.inventario.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private LoginService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user , HttpSession session, RedirectAttributes redirectAttributes) {
        Login oauthUser = userService.login(user.getNombre(), user.getContrasena());

        if(oauthUser != null)
        {
            session.setAttribute("user", oauthUser);
            System.out.println("Usuario guardado en sesión: " + oauthUser.getIdusuario());
            return "redirect:/";
        } else {
//            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos.");
            return "redirect:/login";
        }
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response, HttpSession session)
    {
        session.invalidate();
        return "redirect:/login";
    }
}