package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.data.entity.WeChatRedpackActivity;
import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.wechat.pojo.RedPack;
import com.jzeen.travel.wechat.pojo.RedPackSentResult;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 17日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class RedPackActivity {

    private static Logger log = LoggerFactory.getLogger(RedPackActivity.class);


    /**
     * 获取Mysql数据库连接
     *
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://transenly.mysql.rds.aliyuncs.com:3306/travel?characterEncoding=UTF-8",
                "travel", "Jzeen_2015");
        return conn;
    }


    /**
     * 释放JDBC资源
     *
     * @param conn 数据库连接
     * @param ps
     * @param rs   记录集
     */
    private void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (null != rs) {
                rs.close();
            }
            if (null != ps) {
                ps.close();
            }
            if (null != conn) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 抽奖
     * 根据获取全部抽奖用户
     *
     * @param fromTime 开始时间
     * @return
     */
    private List<WeChatUser> getRedPackUserList(int fromTime) {
        List<WeChatUser> userList = new ArrayList<WeChatUser>();

        String sql = "select id, user_id, openid, nickname, headimgurl, unionid, groupid, sex, country, province, city, subscribe, " +
                "subscribe_time, first_subscribe_time, cancel_time,remark,create_time " +
                "from t_wechat_user where subscribe = 1 and first_subscribe_time > ?";

        RedPackActivity mysqlUtil = new RedPackActivity();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = mysqlUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fromTime);
            rs = ps.executeQuery();
            WeChatUser user = null;

            while (rs.next()) {
                user = new WeChatUser();
                user.setId(rs.getInt("id"));
                user.setUserId(rs.getInt("user_id"));
                user.setOpenid(rs.getString("openid"));
                user.setNickname(rs.getString("nickname"));
                user.setHeadimgurl(rs.getString("headimgurl"));
                user.setUnionid(rs.getString("unionid"));
                user.setGroupid(rs.getInt("groupid"));
                user.setSex(rs.getInt("sex"));
                user.setCountry(rs.getString("country"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
                user.setSubscribe(rs.getInt("subscribe"));
                user.setSubscribe_time(rs.getInt("subscribe_time"));
                user.setFirst_subscribe_time(rs.getInt("first_subscribe_time"));
                user.setCancel_time(rs.getInt("cancel_time"));
                user.setRemark(rs.getString("remark"));
                user.setCreate_time(rs.getDate("create_time"));

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            mysqlUtil.releaseResources(conn, ps, rs);
        }
        return userList;
    }

    /**
     * 抢红包，获的全部抢中红包的用户列表
     *
     * @return
     */
    private List<WeChatRedpackActivity> getRedpackUsers() {
        List<WeChatRedpackActivity> userList = new ArrayList<WeChatRedpackActivity>();

        String sql = "select id, act_type, openid, result, send_flag, click_time, takepartin_time, create_time from  t_wechat_redpack_activity " +
                "where result = 1 and send_flag = 0";

        RedPackActivity mysqlUtil = new RedPackActivity();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = mysqlUtil.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            WeChatRedpackActivity user = null;

            while (rs.next()) {
                user = new WeChatRedpackActivity();
                user.setId(rs.getInt("id"));
                user.setActType(rs.getString("act_type"));
                user.setOpenid(rs.getString("openid"));
                user.setResult(rs.getInt("result"));
                user.setSendFlag(rs.getInt("send_flag"));
                user.setClickTime(rs.getInt("click_time"));
                user.setTakepartinTime(rs.getDate("takepartin_time"));
                user.setCreateTime(rs.getDate("create_time"));

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            mysqlUtil.releaseResources(conn, ps, rs);
        }
        return userList;
    }


    /**
     *
     * 保存红包发放结果
     *
     * @param mchBillno 商户订单ID
     * @param mchId 商户号
     * @param act_name 活动名称
     * @param reOpenid 微信号
     * @param totalAmount 金额
     * @param sendTime 发放时间
     * @param sendListid 订单号
     */
    private void saveRedPackSent(String mchBillno, String mchId, String act_name, String reOpenid, int totalAmount,
                                        int sendTime, String sendListid) {
        String sql = "insert into t_wechat_redpack_sent(mch_billno, mch_id, act_name, re_openid, total_amount,send_time,send_listid,create_time) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = new RedPackActivity().getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mchBillno);
            ps.setString(2, mchId);
            ps.setString(3, act_name);
            ps.setString(4, reOpenid);
            ps.setInt(5, totalAmount);
            ps.setInt(6, sendTime);
            ps.setString(7, sendListid);
            ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            // 释放资源
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 更新状态，修改为红包已经发送
     * @param openid
     */
    private void updateRedPackRecord(String openid) {
        String sql = "update t_wechat_redpack_activity set send_flag = 1 where act_type = \"1\" and openid = ?";
        try {
            Connection conn = new RedPackActivity().getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, openid);
            ps.executeUpdate();
            // 释放资源
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 抢红包
     * 返回抢中红包的用户openId列表
     *
     * @return
     */
    public List<String> getSendUsers() throws ParseException {

        List<WeChatRedpackActivity> userList = getRedpackUsers();

        List<String> openIds = new ArrayList<String>();

        if (userList != null) {
            for (int i = 0; i < userList.size(); i++) {
                String openId = userList.get(i).getOpenid();
                openIds.add(openId);
                log.info("抢红包用户列表,编号：{}   微信号：{}", i, openId);
            }
        }
        return openIds;
    }


    /**
     * 抽奖
     * 返回待发送的红包用户列表
     * 公有方法，对时间进行处理
     *
     * @param count 发放红包个数
     * @param fromTime yyyy-MM-dd HH:mm:ss
     * @return
     */
    public List<String> getSendUsers(int count, String fromTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(fromTime);

        log.info("开始抽奖发红包，抽奖时间{}", date);
        return getSendUsers(count, Long.valueOf(date.getTime() / 1000).intValue());
    }

    /**
     * 抽奖
     * 返回待发送的红包用户列表
     *
     * @param count
     * @param fromTime
     * @return
     */
    private List<String> getSendUsers(int count, int fromTime) {

        List<WeChatUser> weChatUsers = getRedPackUserList(fromTime);
        List<String> openIds = new ArrayList<String>();

        if (weChatUsers != null && weChatUsers.size() > 0) {
            for (WeChatUser user : weChatUsers) {
                double rand = RandomUtils.nextDouble();
                if (rand * weChatUsers.size() < count) {
                    openIds.add(user.getOpenid());

                    log.info("获奖用户，序号ID：{}  用户ID：{}", openIds.size(), user.getOpenid());

                    // 如果数量达到，则跳出循环
                    if (openIds.size() == count) {
                        break;
                    }
                }
            }
        }

        return openIds;
    }

    /**
     * 红包发送结果
     *
     * @param openIds 发送列表
     * @param amount 金额
     * @param wishing 愿望语
     * @param client_ip ip地址
     * @param act_name 活动名称
     * @param remark 备注
     * @throws IllegalAccessException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws KeyManagementException
     * @throws KeyStoreException
     */
    public void sentRedPack(List<String> openIds, int amount, String wishing, String client_ip, String act_name, String remark) throws IllegalAccessException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        if (openIds == null) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(new Date());
        str = str.replaceAll("-", "").replaceAll(":", "").replace(" ", "");

        for (int i = 0; i < openIds.size(); i++) {
            //构造订单号 RAD + 时间（到秒） + 5位顺序号, RA2015081715311100119
            String mch_billno = "RA" + str + String.format("%05d", i);

            RedPack redPack = new RedPack(mch_billno, openIds.get(i), amount, wishing, client_ip, act_name, remark);
            RedPackSentResult result = RedPackUtil.sendRedPack(redPack);
            updateRedPackRecord(openIds.get(i));
            saveRedPackSent(result.getMchBillno(), result.getMchId(), "关注嘻游抢红包", result.getReOpenid(), result.getTotalAmount(),
                    result.getSendTime(), result.getSendListid());
        }

    }


    public static void main(String[] args) throws ParseException, IllegalAccessException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        RedPackActivity activity = new RedPackActivity();
        //抽奖活动
//        List<String> openIds = activity.getSendUsers(0, "2015-08-05 12:00:00");

        //抢红包活动
//        List<String> openIds = activity.getSendUsers();

        List<String> openIds = new ArrayList<String> ();
        openIds.add("oQDRrt46Lbq4orP-9wMlUV5y2RN4");
        activity.sentRedPack(openIds, 100, "嘻游定制旅行 最专业的定制旅行！", "192.168.1.159", "关注嘻游抢红包活动", "红包多多，惊喜连连，关注嘻游，关注旅游");

    }
}