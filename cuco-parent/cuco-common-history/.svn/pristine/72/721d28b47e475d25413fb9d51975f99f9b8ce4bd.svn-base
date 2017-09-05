package com.hy.common.utils;

import com.hy.exception.RuntimeExceptionWarn;
import net.coobird.thumbnailator.Thumbnails;
import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by WangShuai on 2016/8/18.
 */
public class ImageUtils {

    /**
     * 根据文件路径获取Image
     * @param filePath
     * @return
     */
    public static Image pathToImage(String filePath){
        try {
            return ImageIO.read(new File(filePath));
        }catch (Exception e){
            throw new RuntimeExceptionWarn("根据路径转换图片对象失败");
        }
    }

    /**
     * 将文件转换为Image
     * @param file
     * @return
     */
    public static Image fileToImage(File file){
        try {
            return ImageIO.read(file);
        }catch (Exception e){
            throw new RuntimeExceptionWarn("根据路径转换图片对象失败");
        }
    }

    public static int getWidth(Image image){
        try {
            return image.getWidth(null);
        }catch (Exception e){
            throw new RuntimeExceptionWarn("获取图片宽度失败");
        }
    }

    public static int getHeight(Image image){
        try {
            return image.getHeight(null);
        }catch (Exception e){
            throw new RuntimeExceptionWarn("获取图片高度失败");
        }
    }

    public static double getWidthHeightRatio(Image image){
        try {
            return getWidth(image)*1.0/getHeight(image);
        }catch (Exception e){
            throw new RuntimeExceptionWarn("获取图片宽高比失败");
        }
    }

    /*
    * 根据尺寸图片居中裁剪
    */
    public static void cutCenterImage(String src,String dest,int w,int h) throws IOException {
        InputStream in = null;
        ImageInputStream iis = null;
        BufferedImage bi = null;
        String fileType = FileUtils.getSuffix(src);
        if(StringUtils.isEmpty(fileType)){
            fileType = "jpg";
        }
        try {
            Iterator iterator = ImageIO.getImageReadersByFormatName(fileType);
            ImageReader reader = (ImageReader)iterator.next();
            in = new FileInputStream(src);
            iis = ImageIO.createImageInputStream(in);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            int imageIndex = 0;
            Rectangle rect = new Rectangle((reader.getWidth(imageIndex)-w)/2, (reader.getHeight(imageIndex)-h)/2, w, h);
            param.setSourceRegion(rect);
            bi = reader.read(0,param);
            ImageIO.write(bi, fileType, new File(dest));
        }catch (Exception e){
            LogUtil.getLogger().error("图片居中剪裁失败",e);
        }finally {
            FileUtils.closeInputStream(in);
            FileUtils.closeImageInputStream(iis);
            if(bi!=null){
                bi.flush();
            }
        }
    }

    public static void compressImage(String filePath,String newFilePath,int width,int height,float quality) {
        try {
            Thumbnails.of(filePath).size(width,height).outputQuality(quality).toFile(newFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
