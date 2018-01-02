package com.ht.baselib.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by huangtao on 16/7/14.
 */
public class FileUtils {

    public static File bytes2File(byte[] bytes, String path) {
        try {

            if (bytes == null || bytes.length == 0){
                throw new IllegalArgumentException("bytes is empty");
            }

            File file = new File(path);

            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes, 0, bytes.length);
            fos.flush();
            fos.close();
            return file;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void inputStream2File(InputStream in, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(in);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                fos.flush();
            }
            fos.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    LogUtils.error(e.getMessage());
                }
            }
        }
    }

    public static byte[] inputStream2Byte(InputStream in){
        byte[] result = null;
        ByteArrayOutputStream bos = null;
        try{
            bos = new ByteArrayOutputStream();
            byte[] bs = new byte[1024];
            int len = -1;
            while ((len = in.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            result = bos.toByteArray();
        } catch (Exception e){
            LogUtils.error(e.getMessage());
        } finally {
            try {

                if (bos != null){
                    bos.close();
                }

                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                LogUtils.error(e.getMessage());
            }
        }
        return result;
    }

}
