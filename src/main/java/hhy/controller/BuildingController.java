package hhy.controller;

import hhy.entity.BuildingEntity;
import hhy.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuildingController {

    @Autowired
    BuildingService buildingService;

    @RequestMapping(value = "/addBuild")
    public String addBuild(BuildingEntity building){
        buildingService.add(building);
       return "/building/buildingInfo";
    }

    @RequestMapping(value = "/updateBuild")
    public void updateBuild(){

    }

    @RequestMapping(value = "/deleteBuild")
    public void deleteBuild(){

    }

    @RequestMapping(value ="/getBuilds")
    public ModelAndView getBuilds(){
        List<BuildingEntity> builds = buildingService.getBuilds();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/building/buildingInfo");
        mv.addObject("builds",builds);
        return mv;
    }

    @RequestMapping(value ="/getBuildsJson")
    @ResponseBody
    public List<BuildingEntity> getBuildss(){
       return  buildingService.getBuilds();
    }

    @RequestMapping(value = "/getBuild")
    @ResponseBody
    public BuildingEntity getBuild(int id ){
        return buildingService.getBuild(id);
    }


}
