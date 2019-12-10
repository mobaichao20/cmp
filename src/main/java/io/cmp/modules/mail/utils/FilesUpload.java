package io.cmp.modules.mail.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FilesUpload {
    private static final int BUFFER_SIZE = 16 * 1024;
    /**
     * 默认的构造函数
     * */
    public FilesUpload(){}

    /**把文件从临时文件夹拷贝到正式存储的路径下*/
    public static void copy(File src, File dst) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src),
                        BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst),
                        BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int offset = 0;
                while ((offset = in.read(buffer, 0, buffer.length)) != -1) {
                    out.write(buffer, 0, offset);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**上传附件*/
    public List<String> uploadFiles(List<File> myFiles,List<String> fileNames,String module){
        List<String> urls = new ArrayList<String>();
        if(myFiles!=null){
            for(int i=0;i<myFiles.size();i++){
                String uploadPath = getUploadPath(module);
                String newFileName = CUID.createUUID()+getSuffix(fileNames.get(i));
                File savedFile = new File(uploadPath,newFileName);
                copy(myFiles.get(i), savedFile);
                urls.add(getSubPath(uploadPath+"/"+newFileName));
            }
        }
        return urls;
    }

    /**得到文件名后缀*/
    public static String getSuffix(String fileName){
        if(fileName.contains(".")){
            return fileName.substring(fileName.lastIndexOf("."));
        }else{//文件无后缀
            return "";
        }

    }

    /**上传附件*/
    public String uploadFile(File myFile,String fileName,String module){
        String url = "";
        if(myFile!=null){
            String uploadPath = getUploadPath(module);
            String newFileName = CUID.createUUID()+getSuffix(fileName);
            File savedFile = new File(uploadPath,newFileName);
            copy(myFile, savedFile);
            url=getSubPath(uploadPath+"/"+newFileName);
        }
        return url;
    }

    /**复制文件,并返回新文件链接*/
    public static String copy(String fileUrl, String module){
        String url = "";
        File myFile = new File(fileUrl);
        if(myFile!=null){
            String uploadPath = getUploadPath(module);
            File savedFile = new File(uploadPath,myFile.getName());
            copy(myFile, savedFile);
            url=getSubPath(uploadPath+"/"+myFile.getName());
        }
        return url;
    }

    /**获得文件相对路径*/
    public static String getSubPath(String path){
        String realPath = "";
        if(path != null && !"".equals(path)){
            int real = path.indexOf("fileUpload");
            realPath = path.substring(real);
        }
        return realPath;
    }

    /**
     * 获得上传文件时的实际存放文件夹
     * */
    public static String getUploadPath(String module){
        String uploadPath = buildFile("") + "/fileUpload" + "/" + module + "/" + getCurrentDateStr() + "/" + getRandomStr();
        File dirFile = new File(uploadPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        return uploadPath;
    }

    /**
     * 获得年月日的字符串
     * */
    public static String getCurrentDateStr(){
        GregorianCalendar gcDate = new GregorianCalendar();
        int year  = gcDate.get(GregorianCalendar.YEAR);
        int month = gcDate.get(GregorianCalendar.MONTH) + 1;
        int day   = gcDate.get(GregorianCalendar.DAY_OF_MONTH);
        String monthStr = month + "";
        if(month < 10) monthStr = "0" + month;
        return "" + year + monthStr + day;
    }

    /**
     * 获得一个随机数的字符串
     * */
    public static String getRandomStr(){
        //获取系统时间
        String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
        //生成随机数
        Random random = new Random();
        int a = random.nextInt(8999) + 1000;
        return a + "/" +nowTime;
    }

    /**递归删除目录下所有文件*/
    public static void deleteDir(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return; // 检查参数
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    /**
     * 删除文件
     * */
    public static void deleteFile(String url){
        File file = new File(buildFile(url));
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * 根据相对路径获得绝对路径
     * */
    public static String buildFile(String url){
        return "C://tools1//Tomcat6-ciic//webapps//platform"+"/"+url;
    }
}
