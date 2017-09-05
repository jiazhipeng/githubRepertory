package com.hy.gcar.web.wechat.transferList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.TransferList;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.transferList.TransferListService;
import com.hy.gcar.utils.ResultUtils;

/**
 * Created by 海易出行 on 2016/9/19.
 */
@Controller
@RequestMapping("/transferList")
public class TransferListController {

    @Autowired
    TransferListService transferListService;
    @Autowired
    private ButlerTaskService butlerTaskService;

    /**
     * 上传交接单
     * @param transferList
     * @return
     */
    @RequestMapping("/uploadTransferPicture")
    public @ResponseBody  Map<String,Object> uploadTransferPicture(TransferList transferList){
        try {
            transferListService.uploadTransferPicture(transferList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail("上传失败");
        }
        return ResultUtils.success(transferList);
    }

    /**
     * 上传车辆图片
     * @param transferList
     * @return
     */
    @RequestMapping("/uploadCarPicture")
    public @ResponseBody Map<String,Object> uploadCarPicture(TransferList transferList){
        try {
            transferListService.uploadCarPicture(transferList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail("上传失败");
        }
        return ResultUtils.success(transferList);
    }


    @RequestMapping("/toUpload")
    public String toUpload(int type, Long taskId, String loginId, ModelMap map){

        TransferList t = new TransferList();
        t.setTaskId(taskId);
        t.setType(type);
        List<TransferList> transferLists = transferListService.selectByCondition(t);
        ButlerTask butlerTask = new ButlerTask();
        butlerTask = this.butlerTaskService.getButlerTaskById(taskId);
        map.put("transferLists",transferLists);
        map.put("taskId",taskId);
        map.put("loginId",loginId);
        map.put("butlerTask", butlerTask);
        map.put("type",type);
        return "gcar/butler/sendcar/edit_include_transfer";
    }
}
