package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.WeChatMedia;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Title: X2OUR_TRAVEL
 * Description: 文件上传/下载工具类
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class MediaFileUtil {

    private static Logger log = LoggerFactory.getLogger(MediaFileUtil.class);


    /**
     * 上传媒体文件
     *
     * @param type         媒体文件类型（image、voice、video和thumb）
     * @param mediaFileUrl 媒体文件的url
     *
     *上传的多媒体文件有格式和大小限制，如下：
     *
     *图片（image）: 1M，支持JPG格式
     *语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
     *视频（video）：10MB，支持MP4格式
     *缩略图（thumb）：64KB，支持JPG格式
     *
     *媒体文件在后台保存时间为3天，即3天后media_id失效。
     */
    public static WeChatMedia uploadMedia(String type, String mediaFileUrl) {
        WeChatMedia weChatMedia = null;

        String accessToken = WeChatConts.getTokenInst().getToken();
        // 拼装请求地址
        String uploadMediaUrl = WeChatConts.uploadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);


        try {
            URL uploadUrl = new URL(uploadMediaUrl);
            HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod("POST");
            // 设置请求头Content-Type
            uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + WeChatConts.boundary);
            // 获取媒体文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream = uploadConn.getOutputStream();

            URL mediaUrl = new URL(mediaFileUrl);
            HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
            meidaConn.setDoOutput(true);
            meidaConn.setRequestMethod("GET");

            // 从请求头中获取内容类型
            String contentType = meidaConn.getHeaderField("Content-Type");
            // 根据内容类型判断文件扩展名
            String fileExt =  MediaFileUtil.getFileExt(contentType);
            // 请求体开始
            outputStream.write(("--" + WeChatConts.boundary + "\r\n").getBytes());
            outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",  fileExt).getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

            // 获取媒体文件的输入流（读取文件）
            BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                // 将媒体文件写到输出流（往微信服务器写数据）
                outputStream.write(buf, 0, size);
            }
            // 请求体结束
            outputStream.write(("\r\n--" + WeChatConts.boundary + "--\r\n").getBytes());
            outputStream.close();
            bis.close();
            meidaConn.disconnect();

            // 获取媒体文件上传的输入流（从微信服务器读数据）
            InputStream inputStream = uploadConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String str = null;

            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();

            // 释放资源
            inputStream.close();
            inputStream = null;
            uploadConn.disconnect();

            // 使用JSON-lib解析返回结果
            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());

            System.out.println(jsonObject.toString());

            weChatMedia = new WeChatMedia();
            weChatMedia.setType(jsonObject.getString("type"));
            weChatMedia.setCreatedAt(jsonObject.getInt("created_at"));

            // type等于thumb时的返回结果和其它类型不一样
            if ("thumb".equals(type)) {
                // 和缩略图（thumb，主要用于视频与音乐格式的缩略图）
                weChatMedia.setMediaId(jsonObject.getString("thumb_media_id"));
            } else {
                // 图片（image）、语音（voice）、视频（video） 格式
                weChatMedia.setMediaId(jsonObject.getString("media_id"));
            }

            System.out.println("{ type, mediaId, createAt} " + weChatMedia.getType() + "  " + weChatMedia.getMediaId() + "  " + weChatMedia.getCreatedAt());

        } catch (Exception e) {
            weChatMedia = null;
            log.error("上传媒体文件失败：{}", e);
        }
        return weChatMedia;
    }

    /**
     * 下载媒体文件
     *
     * @param mediaId     媒体文件标识
     * @param savePath    文件在服务器上的存储路径
     * @return
     */
    public static String getMedia(String mediaId, String savePath) {

        String accessToken = WeChatConts.getTokenInst().getToken();

        String filePath = null;

        // 拼接请求地址
        String requestUrl = WeChatConts.downloadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace(
                "MEDIA_ID", mediaId);
        System.out.println(requestUrl);

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // 根据内容类型获取扩展名
            String fileExt = getFileExt(conn.getHeaderField("Content-Type"));
            // 将mediaId作为文件名
            filePath = savePath + mediaId + fileExt;

            BufferedInputStream bis = new BufferedInputStream(
                    conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();

            conn.disconnect();
            log.info("下载媒体文件成功，filePath=" + filePath);
        } catch (Exception e) {
            filePath = null;
            log.error("下载媒体文件失败：{}", e);
        }
        return filePath;
    }

    /**
     * 根据内容类型判断文件扩展名
     *
     * @param contentType 内容类型
     * @return
     */
    private static String getFileExt(String contentType) {
        String fileExt = "";
        if (contentType.startsWith("image/jpeg")) {
            fileExt = ".jpg";
        } else if (contentType.startsWith("audio/mpeg")) {
            fileExt = ".mp3";
        } else if (contentType.startsWith("audio/amr")) {
            fileExt = ".amr";
        } else if (contentType.startsWith("video/mp4")) {
            fileExt = ".mp4";
        } else if (contentType.startsWith("video/mpeg4")) {
            fileExt = ".mp4";
        }
        return fileExt;
    }

    public static void main(String args[]) {

        /**
         * 上传多媒体文件
         */
//         WeChatMedia weChatMedia = MediaFileUtil.uploadMedia("image", "/home/limin/app/ideaProjects/travel/travel-website/src/main/resources/static/assets/frontend/onepage/img/wechatQR.jpg");
//         WeChatMedia weChatMedia = MediaFileUtil.uploadMedia("image", "http://www.x2our.com/wechatimage/activity/redpackactfail1.jpg");
//         System.out.println(weChatMedia.getMediaId());
//         System.out.println(weChatMedia.getType());
//         System.out.println(weChatMedia.getCreatedAt());


//        { type, mediaId, createAt} image  yGSzdcdm0Y5ktiyYaG-qflcAgyphF_Q8HGb_RwFSgvUEqJDqZk3HvXrivrqoUw76  1438574520
        //  fail   grr-erLC8-vZzjFl3ah1SFLhfm9BhTAaTzZaJ0hLApnC3CxtE3RLUwzBpRxVWooo
        //  sucess 8xiIxYaYCeLWewYZxriH4w38K74pRJFgPjD7pju0NcxnaGhLeaDtLUFAU1Zctxvg

        /**
         * 下载多媒体文件
         */
        getMedia("grr-erLC8-vZzjFl3ah1SFLhfm9BhTAaTzZaJ0hLApnC3CxtE3RLUwzBpRxVWooo",
                "/home/limin/upload");
    }
}
