package cn.cuco.controller.manager.insurance;

import cn.cuco.entity.Insurance;
import cn.cuco.page.PageResult;
import cn.cuco.service.basic.dictionary.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import cn.cuco.annotation.API;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @ClassName:
 * @Description：
 * @author：WS
 * @date：2017年03月11 11:27:00
 */
@RestController
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @API(value = "获取保险公司列表")
    @RequestMapping(value = "/insurance/getInsuranceList",method = RequestMethod.GET)
    private Object getInsuranceList(){
        PageResult<Insurance> pageResult = insuranceService.getInsuranceList(new Insurance());

        return pageResult.getItems();
    }
}
