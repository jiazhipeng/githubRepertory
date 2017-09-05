package com.toolkit.auto.mybatis.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Data{

    private Data()
    {

    }

    /**
     * 获取系统用户名
     *
     * @return 系统用户名
     */
    public static String getSystemUser()
    {
        Properties p = System.getProperties();
        return p.getProperty("user.name");
    }

    /**
     * 获取路径
     * @return
     */
    public static String getProjectLocalPath()
    {
        try
        {
            String filepath = Data.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            filepath = URLDecoder.decode(filepath, "UTF-8");
            filepath = filepath.substring(0, filepath.lastIndexOf("/") + 1);
            filepath = new File(filepath).getAbsolutePath();
            return filepath;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 居中设置
     * @param com
     */
    public static void setPosition(Component com)
    {
        try
        {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension frameSize = com.getSize();
            com.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 上移
     * @param jList
     */
    public static void moveUp(javax.swing.JList jList)
    {
        int x = jList.getSelectedIndex();
        DefaultListModel model = (DefaultListModel) jList.getModel();
        if (x == -1 || x == 0)
        {
            return;
        }
        model.add(x - 1, model.elementAt(x));
        model.remove(x + 1);
        jList.setSelectedIndex(x - 1);
    }

    /**
     * 下移
     * @param jList
     */
    public static void moveDown(javax.swing.JList jList)
    {
        int x = jList.getSelectedIndex();
        DefaultListModel model = (DefaultListModel) jList.getModel();
        if (x == -1 || x == model.getSize() - 1)
        {
            return;
        }
        model.add(x, model.elementAt(x + 1));
        model.remove(x + 2);
        jList.setSelectedIndex(x + 1);
    }

    /**
     * 获取选中的对象
     * @param jList
     */
    public static Object getSelectedObject(javax.swing.JList jList)
    {
        int x = jList.getSelectedIndex();
        if (x == -1)
        {
            return null;
        }
        DefaultListModel model = (DefaultListModel) jList.getModel();
        return model.elementAt(x);
    }

    /**
     * 添加
     * @param jList
     * @param obj
     */
    public static void addList(javax.swing.JList jList, Object obj)
    {
        DefaultListModel model = (DefaultListModel) jList.getModel();
        model.addElement(obj);
    }

    /**
     * 删除
     * @param jList
     */
    public static void deleteList(javax.swing.JList jList)
    {
        int x = jList.getSelectedIndex();
        if (x == -1)
        {
            return;
        }
        DefaultListModel model = (DefaultListModel) jList.getModel();
        model.remove(x);
    }

    /**
     * 清空
     * @param jList
     */
    public static void clearList(javax.swing.JList jList)
    {
        DefaultListModel modelSrc = (DefaultListModel) jList.getModel();
        int x = modelSrc.size();
        for (int i = 0; i < x; i++)
        {
            modelSrc.remove(0);
        }
    }

    /**
     * 单个右移
     * @param jListLeft		左侧列表
     * @param jListRight	右侧列表
     */
    public static void moveOneToRight(javax.swing.JList jListLeft, javax.swing.JList jListRight)
    {
        moveOneElement(jListLeft, jListRight);
    }

    /**
     * 单个左移
     * @param jListLeft		左侧列表
     * @param jListRight	右侧列表
     */
    public static void moveOneToLeft(javax.swing.JList jListLeft, javax.swing.JList jListRight)
    {
        moveOneElement(jListRight, jListLeft);
    }

    /**
     * 移动单个元素
     * @param jListSrc		移出的list
     * @param jListDest		目标list
     */
    private static void moveOneElement(javax.swing.JList jListSrc, javax.swing.JList jListDest)
    {
        int x = jListSrc.getSelectedIndex();
        if (x == -1)
        {
            return;
        }
        DefaultListModel modelSrc = (DefaultListModel) jListSrc.getModel();
        DefaultListModel modelDest = (DefaultListModel) jListDest.getModel();
        modelDest.addElement(modelSrc.get(x));
        modelSrc.remove(x);
    }

    /**
     * 所有右移
     * @param jListLeft		左侧列表
     * @param jListRight	右侧列表
     */
    public static void moveAllToRight(javax.swing.JList jListLeft, javax.swing.JList jListRight)
    {
        moveAllElements(jListLeft, jListRight);
    }

    /**
     * 所有左移
     * @param jListLeft		左侧列表
     * @param jListRight	右侧列表
     */
    public static void moveAllToLeft(javax.swing.JList jListLeft, javax.swing.JList jListRight)
    {
        moveAllElements(jListRight, jListLeft);
    }

    /**
     * 移动
     * @param jListSrc
     * @param jListDest
     */
    private static void moveAllElements(javax.swing.JList jListSrc, javax.swing.JList jListDest)
    {
        DefaultListModel modelSrc = (DefaultListModel) jListSrc.getModel();
        DefaultListModel modelDest = (DefaultListModel) jListDest.getModel();
        int x = modelSrc.size();
        for (int i = 0; i < x; i++)
        {
            modelDest.addElement(modelSrc.get(0));
            modelSrc.remove(0);
        }
    }

    /**
     * 系统错误
     * @param com
     */
    public static void printERROR(Component com)
    {
        JOptionPane.showMessageDialog(com, "系统出现严重错误", "系统错误", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 提示信息
     * @param com
     * @param info
     */
    public static void printINFO(Component com, String info)
    {
        JOptionPane.showMessageDialog(com, info, "提示信息", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 往系统目录下写入文件
     *
     * @param filename
     *            文件名
     * @param content
     *            内容
     * @return true-写入成功，false-写入失败
     */
    public static boolean writeToSys(String filename, String content)
    {
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(getSystemTempPath() + filename);
            out.print(content);
            out.flush();
            out.close();
            out = null;
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            if (out != null)
            {
                out.close();
                out = null;
            }
        }
    }

    /**
     * 从系统目录下读取文件
     *
     * @param filename
     *            文件名
     * @return 文件的内容
     */
    public static String readFromSys(String filename)
    {
        FileInputStream in = null;
        try
        {
            //            System.out.println(getSystemTempPath());
            in = new FileInputStream(getSystemTempPath() + filename);
            byte[] b = new byte[in.available()];
            in.read(b);
            return new String(b);
        }
        catch (Exception e)
        {
            return null;
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                }
                in = null;
            }
        }
    }

    /**
     * 获取系统临时文件夹
     *
     * @return 系统临时文件夹路径
     */
    public static String getSystemTempPath()
    {
        Properties p = System.getProperties();
        return p.getProperty("java.io.tmpdir");
    }

    public static String getCurrentDateTime()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
