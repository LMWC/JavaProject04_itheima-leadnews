package com.itheima.behaviour.controller;


import com.itheima.behaviour.pojo.ApBehaviorEntry;
import com.itheima.behaviour.service.ApBehaviorEntryService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* APP行为实体表,一个行为实体可能是用户或者设备，或者其它 控制器</p>
* @author ljh
* @since 2022-08-03
*/
@Api(value="APP行为实体表,一个行为实体可能是用户或者设备，或者其它",tags = "ApBehaviorEntryController")
@RestController
@RequestMapping("/apBehaviorEntry")
public class ApBehaviorEntryController extends AbstractCoreController<ApBehaviorEntry> {

    private ApBehaviorEntryService apBehaviorEntryService;

    //注入
    @Autowired
    public ApBehaviorEntryController(ApBehaviorEntryService apBehaviorEntryService) {
        super(apBehaviorEntryService);
        this.apBehaviorEntryService=apBehaviorEntryService;
    }

    @GetMapping("/entryOne")
    public ApBehaviorEntry findByUserIdOrEquipmentId(
            @RequestParam(name="userId",required = true) Integer userId,
            @RequestParam(name="type",required = true)Integer type){
        return apBehaviorEntryService.findByUserIdOrEquipmentId(userId,type);
    }

}

