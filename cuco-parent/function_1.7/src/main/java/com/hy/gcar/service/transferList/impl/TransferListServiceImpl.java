package com.hy.gcar.service.transferList.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.hy.common.utils.AccessTokenUtils;
import com.hy.common.utils.ImageUtils;
import com.hy.constant.Constant;
import com.hy.gcar.dao.TransferListMapper;
import com.hy.gcar.entity.ButlerTask;
import com.hy.gcar.entity.TransferList;
import com.hy.gcar.service.butlerTask.ButlerTaskService;
import com.hy.gcar.service.member.MemberService;
import com.hy.gcar.service.powerUserd.PowerUsedService;
import com.hy.gcar.service.transferList.TransferListService;
import com.hy.weixin.multiMedia.WiXinMultiMedia;

@Service(value = "tdTransferListService")
public class TransferListServiceImpl implements TransferListService {

    @Autowired
    private TransferListMapper<TransferList>tdTransferListMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ButlerTaskService butlerTaskService;

    @Autowired
    private PowerUsedService powerUsedService;
    
    @Override
    public Integer insertSelective(TransferList tdTransferList) throws Exception {
           return this.tdTransferListMapper.insertSelective(tdTransferList);
        }

    @Override
    public Integer insertBatch(List<TransferList> tdTransferList) throws Exception {
           return this.tdTransferListMapper.insertBatch(tdTransferList) ;
        }

    @Override
    public Integer deleteByPrimaryKey(Object id) {
            return this.tdTransferListMapper.deleteByPrimaryKey(id);
    }

    public Integer deleteBatchByPrimaryKey(List<Object> ids) {
           return this.tdTransferListMapper.deleteBatchByPrimaryKey(ids);
    }

    @Override
    public Integer updateByPrimaryKeySelective(TransferList tdTransferList) {
           return this.tdTransferListMapper.updateByPrimaryKeySelective(tdTransferList);
    }

    @Override
    public TransferList findById(Object id) {
           return (TransferList) this.tdTransferListMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TransferList> selectByCondition(TransferList tdTransferList) {
           return (List<TransferList>) this.tdTransferListMapper.selectByCondition(tdTransferList);
    }

    @Override
    public Integer selectCountByCondition(TransferList tdTransferList) {
           return  this.tdTransferListMapper.selectCountByCondition(tdTransferList);
    }
    //uploadTransferAndCarPicture
    @Override
    public TransferList uploadTransferPicture(TransferList transferList) throws Exception {
        Assert.notNull(transferList,"交接单对象不能为空");
        Assert.notNull(transferList.getImageUrl(),"车辆图片不能为空");
        Assert.notNull(transferList.getTaskId(),"任务id不能为空");
        Assert.notNull(transferList.getType(),"图片类型不能为空");

        return saveTransferList(transferList,0);
    }

    public TransferList saveTransferList(TransferList transferList,int type) throws Exception {
        ButlerTask butlerTask = new ButlerTask();
        butlerTask.setId(transferList.getTaskId());
        List<ButlerTask> butlerTasks = butlerTaskService.selectByCondition(butlerTask);
        if(CollectionUtils.isEmpty(butlerTasks)){
            throw  new Exception("任务不存在.....");
        }
        butlerTask = butlerTasks.get(0);

        String url = transferList.getImageUrl();
        String[] urls = url.split(",");
        List<TransferList> transferLists = Lists.newArrayList();
        TransferList t = null;
        for(String imgUrl:urls){
            if(StringUtils.isEmpty(imgUrl)){
                continue;
            }
            t = new TransferList();
            t.setTaskId(transferList.getTaskId());
            if(imgUrl.indexOf("http://") == 0){
                t.setImageUrl(imgUrl);
            }else{
                String s = this.downloadMediaFromWxBy(imgUrl);
                t.setImageUrl(s);
            }
            t.setType(type);
            t.setPowerUsedId(butlerTask.getPowerUsedId());
            transferLists.add(t);
        }


        this.deleteByTaskID(transferList);
        this.insertBatch(transferLists);
        transferList.setUploadCount(transferLists.size());
        return transferList;
    }

    @Override
    public TransferList uploadCarPicture(TransferList transferList) throws Exception {
        Assert.notNull(transferList,"交接单对象不能为空");
        Assert.notNull(transferList.getImageUrl(),"车辆图片不能为空");
        Assert.notNull(transferList.getTaskId(),"任务id不能为空");
        return saveTransferList(transferList,1);
    }

    @Override
    public String downloadMediaFromWx(String mediaId) throws Exception {
        String token = AccessTokenUtils.getAccessToken();
        String fileSavePath = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path");
        String fileSavePathUrl = WiXinMultiMedia.downloadMediaFromWx(token, mediaId, fileSavePath);
        return fileSavePathUrl;
    }

    @Override
    public String cutCenterImage(String fileSavePathUrl) throws IOException {
        //获取硬盘保存的路径
        String picture_hard_disk_path = Constant.APPLICATION_LOAD.getProperty("picture_hard_disk_path");
        //得到硬盘图片保存的全路径
        String picture_hard_disk_path_url = picture_hard_disk_path + "//" + fileSavePathUrl;
        //裁剪图片
        ImageUtils.cutCenterImage(picture_hard_disk_path_url,picture_hard_disk_path_url,470,294);
        return fileSavePathUrl;
    }

    @Override
    public Integer deleteByTaskID(TransferList transferList) {
        return this.tdTransferListMapper.deleteByTaskID(transferList);
    }

    /**
     * @throws Exception
     *
     * @Title: downloadMediaFromWxByIdcardFront
     * @Description: 从微信下载图片到本地服务器
     * @param @param idcardFront
     * @param @return
     * @return String
     * @author q.p.x
     * @date 2016年8月18日 上午10:57:52
     * @throws
     */
    private String downloadMediaFromWxBy(String mediaID) throws Exception{
        if(StringUtils.isEmpty(mediaID)){
            return null;
        }
        //调用微信接口返回微信图片地址 下载到本地服务器图片服务器硬盘路径
        String fileSavePathUrl = this.downloadMediaFromWx(mediaID);

//        //裁剪图片
//        fileSavePathUrl = this.cutCenterImage(fileSavePathUrl);

        //解析服务器硬盘路径为web服务器的图片路径 返回web路径
        String webPictureUrl =  memberService.getWebPathMemberHeadPictureUrl(fileSavePathUrl);
        return webPictureUrl;
    }

}
