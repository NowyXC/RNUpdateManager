package com.nowy.patchCreator;



import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.FileReader;
/**
 * 文件操作类
 */
public class FileUtil {


    /**
     * 流转化为字符串
     * @param is
     * @return
     */
    public static String inputStream2String(InputStream is) {
        if (null == is) {
            return null;
        }
        StringBuilder resultSb = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            resultSb = new StringBuilder();
            String len;
            while (null != (len = br.readLine())) {
                resultSb.append(len);
            }
        } catch (Exception ex) {
        } finally {
            closeIO(is);
        }
        return null == resultSb ? null : resultSb.toString();
    }


    /**
     * 从文件中读取文本
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        StringBuilder resultSb = null;
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
        } catch (Exception e) {
        }
        return inputStream2String(is);
    }

    public static String readFile(File file) {
        return readFile(file.getPath());
    }



    public static String getStringFromPat(String patPath) {

        FileReader reader = null;
        String result = "";
        try {
            reader = new FileReader(patPath);
            int ch = reader.read();
            StringBuilder sb = new StringBuilder();
            while (ch != -1) {
                sb.append((char)ch);
                ch  = reader.read();
            }
            reader.close();
            result = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将文本写入到指定目录的文件中，如果文件不存在则自动创建
     * @param file
     * @param content
     * @return
     */
    public static int writeFile(File file, String content) {
        return writeFile(file.getPath(), content,false);
    }


    /**
     * 将流写入到指定目录的文件中，如果文件不存在则自动创建
     * @param file
     * @param stream
     * @return 写入成功返回true
     */
    public static boolean writeFile(File file, InputStream stream) {
        return writeFile(file, stream,false);
    }



    /**
     * 写文本到指定目录的文件中（不存在自动创建文件）
     * @param path
     * @param content
     * @param append 如果是true,内容拼接到文件后面
     * @return
     */
    public static int writeFile(String path, String content, boolean append) {
        if(content == null || content.length() == 0) return -1;

        FileWriter fileWriter = null;
        try {
            getFileAutoCreated(path);
            fileWriter = new FileWriter(path, append);
            fileWriter.write(content);
            return 0;
        } catch (Exception e) {
        } finally {
            closeIO(fileWriter);
        }
        return -1;
    }

    /**
     * write file
     * 将输入流写入指定路径的文件中
     * @param file the file to be opened for writing.
     * @param stream the input stream
     * @param append if <code>true</code>, then bytes will be written to the end of the file rather than the beginning
     * @return return true
     * @throws RuntimeException if an error occurs while operator FileOutputStream
     */
    public static boolean writeFile(File file, InputStream stream, boolean append) {
        OutputStream o = null;
        try {
            getFileAutoCreated(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            closeIO(o,stream);
        }
    }


    /**
     * 获取一个文件对象，如果不存在，则自动创建
     *
     * @param filePath 绝对路径
     * @return
     */
    public static File getFileAutoCreated(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            return file;
        }
        if (file.exists()) {
            return file;
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        return file;
    }



    /**
     * 关闭流
     *
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
            }
        }
    }


}