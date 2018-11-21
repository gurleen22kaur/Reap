package com.ttn.reap.reapbootcamp.controller;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ttn.reap.reapbootcamp.entity.User;
import com.ttn.reap.reapbootcamp.entity.UserRecogonize;
import com.ttn.reap.reapbootcamp.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ttn.reap.reapbootcamp.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView show(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if(user != null){
            user = userService.findUserByEmail(user.getEmail());
            modelAndView.addObject("user",user);
            modelAndView.setViewName("redirect:/dashboard");
        } else {
            user = new User();
            modelAndView.addObject("user",user);
            modelAndView.setViewName("/home");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/userSignUp", method = RequestMethod.POST)
    public ModelAndView userSignUp(@Valid User user)
    {
        ModelAndView modelAndView=new ModelAndView();
        User userExists=userService.findUserByEmail(user.getEmail());

        if(userExists!=null) {
            modelAndView.addObject("msg1","This email already exists!");
        }
        else {
            System.out.println(user);
            userService.saveOnSignUp(user);
            //userService.saveBadges(user);
            System.out.println("Registered");
            modelAndView.addObject("msg1","User has been registered successfully");
            modelAndView.addObject("user",new User());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }


    @PostMapping("/login")
    public ModelAndView fetchUserFromDatabase(@ModelAttribute("user") User user, HttpServletRequest request) {
        System.out.println("hiii");
        ModelAndView modelAndView = new ModelAndView();
        User user1 = userService.findUserByEmail(user.getEmail());
        if (user1 != null) {
            String userexistPassword = user.getPassword();
            if (!userexistPassword.isEmpty() && userexistPassword.equals(user1.getPassword())) {
                System.out.println("#### " + user.getEmail());
                System.out.println("$$$$ " + user.getPassword() + " %%%%%% " + user.getPassword());
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                modelAndView.addObject("user",user);
                //Integer p=userService.calculatePoints(user);
                //System.out.println("controller"+p);
                //modelAndView.setViewName("dashboard");
              // return modelAndView;
                return new ModelAndView("redirect:/dashboard");
            }
            else{
                modelAndView.addObject("msg","Incorrect Password");
                modelAndView.setViewName("home");
            }
        }else{
            modelAndView.addObject("msg","Email id doesn't Exist. Please register before login");
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }


    @RequestMapping(value = {"/forgotPassword"},method = RequestMethod.GET)
    public ModelAndView forgotPassword(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ModelAndView sendMail(User user){
        mailService.sendMail(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","Email has been sent to "+user.getEmail());
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }


    @GetMapping("/dashboard")
    public ModelAndView viewDashboard(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            user = userService.findUserByEmail(user.getEmail());
            modelAndView.addObject("user", user);

            List<UserRecogonize> userRecogonizeList=userService.find();
            modelAndView.addObject("recList",userRecogonizeList);
            if(userRecogonizeList!=null){
                System.out.println(userRecogonizeList.size());

            }else{
                System.out.println("null");
            }
            modelAndView.setViewName("/dashboard");
        } else {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }



    @PostMapping("/dashboard")
    public ModelAndView viewDashboard(HttpServletRequest request, UserRecogonize userRecogonize) {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(userRecogonize);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            user = userService.findUserByEmail(user.getEmail());



            if((userRecogonize!=null) && userRecogonize.getDestEmail()!=null)
            {

                User destinationUser=userService.findByEmail(userRecogonize.getDestEmail());




                if(destinationUser==null) {
                    modelAndView.addObject("message4","Please enter a valid user");
                    modelAndView.addObject("user", user);
                    modelAndView.setViewName("/dashboard");
                    return modelAndView;
                }


                else {
                    User dest = userService.findUserByEmail(userRecogonize.getDestEmail());
                    Integer id=userRecogonize.getBadgeId();

                    userRecogonize.setDestId(dest.getId());
                    userRecogonize.setSourceId(user.getId());
                    userRecogonize.setSourceEmail(user.getEmail());
                    userRecogonize.setTimestamp(new Date());
                    if(id==1 && user.getGoldCount() <=0)
                    {
                        modelAndView.addObject("message4","You do not have enough badges to share");
                        modelAndView.addObject("user", user);
                        modelAndView.setViewName("/dashboard");
                        return modelAndView;
                    }
                    else if(id==2 && user.getSilverCount() <= 0)
                    {
                        modelAndView.addObject("message4","You do not have enough badges to share");
                        modelAndView.addObject("user", user);
                        modelAndView.setViewName("/dashboard");
                        return modelAndView;
                    }
                    else if(id==3 && user.getBronzeCount() <= 0)
                    {
                        modelAndView.addObject("message4","You do not have enough badges to share");
                        modelAndView.addObject("user", user);
                        modelAndView.setViewName("/dashboard");
                        return modelAndView;
                    }
                    else
                    userService.saveBadgeType(userRecogonize);

                    userService.save(userRecogonize);
                }

            }

            userService.updatePoints(userRecogonize,user);
            userService.save(user);
            userService.updateDestination(userRecogonize,user);
            userService.save(user);
            //userService.calculatePoints(user);
           // userService.save(user);
            modelAndView.addObject("user", user);

            List<UserRecogonize> userRecogonizeList=userService.find();
            modelAndView.addObject("recList",userRecogonizeList);
            if(userRecogonizeList!=null){
                System.out.println(userRecogonizeList.size());

            }else{
                System.out.println("null");
            }

            modelAndView.setViewName("/dashboard");
        }
        else {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }


    @GetMapping("/badges")
    public ModelAndView viewBadges(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            user = userService.findUserByEmail(user.getEmail());
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/badges");
        } else {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

   /* @GetMapping("/badges")
    public ModelAndView viewBadges(HttpServletRequest request) {
        ModelAndView modelAndView=new ModelAndView();
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");

        if(user!=null) {
            List<UserRecogonize> allList = userService.findAllBadges(user);
            modelAndView.addObject("allList", allList);
            modelAndView.setViewName("allBadges");
        }

        else
        {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }*/





    @RequestMapping("/sharedBadges")
    public ModelAndView sharedBadges(HttpServletRequest request,UserRecogonize userRecogonize)
    {
        ModelAndView modelAndView=new ModelAndView();
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if(user!=null) {
            List<UserRecogonize> userRecogonizeList = userService.findSharedBadges(user);
            if(userRecogonizeList!=null) {
                modelAndView.addObject("sharedList", userRecogonizeList);
                modelAndView.setViewName("sharedBadges");
            }
            else
            {
                modelAndView.addObject("nodata","No Data Found");
            }
        }
        else
        {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @RequestMapping("/receivedBadges")
    public ModelAndView receivedBadges(HttpServletRequest request,UserRecogonize userRecogonize)
    {
        ModelAndView modelAndView=new ModelAndView();
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");

        if(user!=null) {
            List<UserRecogonize> userRecogonizeList = userService.findReceivedBadges(user);
            modelAndView.addObject("receivedList", userRecogonizeList);
            modelAndView.setViewName("receivedBadges");

        }
        else
        {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }


    @RequestMapping("/allBadges")
    public ModelAndView allBadges(HttpServletRequest request,UserRecogonize userRecogonize)
    {

        ModelAndView modelAndView=new ModelAndView();
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");

        if(user!=null) {
            List<UserRecogonize> allList = userService.findAllBadges(user);
            modelAndView.addObject("allList", allList);
            modelAndView.setViewName("allBadges");
        }

        else
        {
            user = new User();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;

    }

}



