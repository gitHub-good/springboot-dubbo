package com.xu.springboot.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xu.dubbo.dubbointerface.model.User;
import com.xu.dubbo.dubbointerface.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户信息Controller
 */
@Controller
public class UserController {

    /*<dubbo:reference id="" .....> 必须要指定该引用的版本号*/
    @Reference(version = "1.0.0")/*引用使用zookeeper中注册的UserService接口*/
    private UserService userService;


    /**
     *
     * 项目开始展示数据页面
     * @param curPage
     * @param model
     * @return
     */
    @RequestMapping("/userList")
    public String userList(@RequestParam(value = "curPage", required = false) Integer curPage, Model model){

        //每页展示10条数据
        int pageSize  = 5;
        //数据总数
        int totalRows =  userService.getUserTotal();
        //计算分页页数总数
        int totalPages = totalRows / pageSize;
        int left = totalRows % pageSize;
        if(left > 0){
            totalPages = totalPages+1;
        }
        //如果curPage起始页为null则默认为1，前台传的起始页值curPage
        if(null == curPage || curPage < 1){
            curPage = 1;
        }
        if(curPage>totalPages){
            curPage = totalPages;
        }
        //计算页面开始行数
        int startRow = (curPage - 1) * pageSize;
        Map<String, Object> paramMap = new ConcurrentHashMap<>();
        paramMap.put("startRow",startRow);
        paramMap.put("pageSize",pageSize);
        List<User> userList = userService.getUserGetPage(paramMap);
        model.addAttribute("userList",userList);
        model.addAttribute("curPage",curPage);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("totalRows",totalRows);
        return "index";
    }

    /**
     * 去页面添加用户信息
     * @return
     */
    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "addUser";
    }
    /**
     * 添加用户信息
     * @param user
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String insertUser(User user, Model model){
        int count = userService.insertUser(user);
        String msg = null;
        if(count>0){
            msg = "添加成功";
        }else {
            msg = "添加失败";
        }
        model.addAttribute("msg", msg);
        return "addUser";
        //重定向到列表页
//        return "redirect:/userList";
    }

    /**
     * 查询用户信息传送给前台进行展示修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("id") Integer id, Model model,
                             @RequestParam(value = "curPage", required = false) Integer curPage){
        User user = new User();
        user.setId(id);
        user = userService.selectUser(user);
        model.addAttribute("user", user);
        String msg = null;
        model.addAttribute("msg", msg);
        model.addAttribute("curPage", curPage);
        return "updateUser";
    }
    /**
     * 用户信息进行修改
     * @param model
     * @return
     */
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(Model model, User user, @RequestParam("curPage") Integer curPage){
        int count = userService.updateUser(user);
        String msg = null;
        if(count>0){
            msg = "修改成功";
        }else {
            msg = "修改失败";
        }
        model.addAttribute("curPage", curPage);
        model.addAttribute("msg", msg);
        return "updateUser";
    }

    /**
     * 删除用户信息
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id", required = false) Integer id, @RequestParam("curPage") Integer curPage){
        User user = new User();
        user.setId(id);
        userService.deleteUser(user);
        return "redirect:/userList?curPage="+curPage;
    }

}
