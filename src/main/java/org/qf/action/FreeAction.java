package org.qf.action;

import org.qf.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class FreeAction {

    @RequestMapping("/test01")
    public String test01(Model model){

        model.addAttribute("test01","lg是zz大zz");
        return "test01";
    }

    @RequestMapping("/test02")
    public ModelAndView test02(){

        List<UserDTO> userDTOList = new ArrayList<UserDTO>();

        userDTOList.add(new UserDTO(1L,"聂释隆","123"));
        userDTOList.add(new UserDTO(2L,"王帅","123"));
        userDTOList.add(new UserDTO(3L,"啦啦啦","123"));

        return new ModelAndView("test02","userLIst",userDTOList);
    }

}
