package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.pojo.*;
import com.jzeen.travel.wechat.utils.MenuUtil;
import com.jzeen.travel.wechat.conf.WeChatConts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: X2OUR_TRAVEL
 * Description: 菜单管理类,用于菜单的创建
 * Date: 2015年08月02日
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    //	@Test
    public static void main(String[] args) {
        MenuManager.createMenu();
    }

    public static void createMenu() {
        // 调用接口获取access_token
        AccessToken at = WeChatConts.getTokenInst();

        if (null != at) {
            // 调用接口创建菜单
            boolean result = MenuUtil.createMenu(getMenu(), at.getToken());

            // 判断菜单创建结果
            if (result) {
                log.info("菜单创建成功！");
            } else {
                log.info("菜单创建失败，错误码：" + result);
            }
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {

        // 菜单1
    	ViewButton btn11 = new ViewButton();
    	btn11.setName("训练器");
    	btn11.setType("view");
    	btn11.setUrl(WeChatConts.OAUTH_TRIPS_URL);
        /*ViewButton btn11 = new ViewButton();
        btn11.setName("随我定制");
        btn11.setType("view");
        btn11.setUrl(WeChatConts.OAUTH_TRIPS_URL);

        ClickButton btn12 = new ClickButton();
        btn12.setName("帮我定制");
        btn12.setType("click");
        btn12.setKey("12");*/

        // 菜单2
        ClickButton btn21 = new ClickButton();
        btn21.setName("课程介绍");
        btn21.setType("click");
        btn21.setKey("21");
        /*ClickButton btn21 = new ClickButton();
        btn21.setName("美国规划师");
        btn21.setType("click");
        btn21.setKey("21");

        ClickButton btn22 = new ClickButton();
        btn22.setName("美国游记");
        btn22.setType("click");
        btn22.setKey("22");*/

        // 菜单3
        ClickButton btn31 = new ClickButton();
        btn31.setName("活动介绍");
        btn31.setType("click");
        btn31.setKey("31");
       /* ClickButton btn31 = new ClickButton();
        btn31.setName("随便问问");
        btn31.setType("click");
        btn31.setKey("31");

        ViewButton btn32 = new ViewButton();
        btn32.setName("签证攻略");
        btn32.setType("view");
        btn32.setUrl(WeChatConts.webURL + "/wechatmenuevent/visains");

        ViewButton btn33 = new ViewButton();
        btn33.setName("关于我们");
        btn33.setType("view");
        btn33.setUrl(WeChatConts.webURL + "/wechatmenuevent/aboutus");*/

    	
    	//菜单1
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("财脑训练器");
//        mainBtn1.setSub_button(new Button[]{btn11, btn12});
        //去掉 帮我定制
        //mainBtn1.setSub_button(new Button[]{btn11});
        mainBtn1.setSub_button(new Button[]{btn11});
        
        //菜单2
        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("课程");
        //mainBtn2.setSub_button(new Button[]{});
        mainBtn2.setSub_button(new Button[]{btn21});

        //菜单3
       ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("活动");
        //mainBtn3.setSub_button(new Button[]{});
//        mainBtn3.setSub_button(new Button[]{btn31, btn32});
        //签证攻略 修改为 关于我们
        mainBtn3.setSub_button(new Button[]{btn31});

        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1,mainBtn2,mainBtn3});

        return menu;
    }


    /**
     * 本方法仅为测试接口菜单
     * 组装菜单数据
     *
     * @return Menu
     */
    private static Menu gettestMenu() {


        // 菜单1
        ViewButton btn11 = new ViewButton();
        btn11.setName("嘻游首页");
        btn11.setType("view");
//        btn11.setUrl("http://www.x2our.com");
        btn11.setUrl(WeChatConts.oauth2Url);


        ViewButton btn12 = new ViewButton();
        btn12.setName("嘻游介绍");
        btn12.setType("view");
        btn12.setUrl("http://www.x2our.com/home/aboutus");

        ClickButton btn13 = new ClickButton();
        btn13.setName("最新活动");
        btn13.setType("click");
        btn13.setKey("13");

        ClickButton btn14 = new ClickButton();
        btn14.setName("登陆嘻游");
        btn14.setType("click");
        btn14.setKey("14");


        // 菜单2
        ClickButton btn21 = new ClickButton();
        btn21.setName("我的订单");
        btn21.setType("click");
        btn21.setKey("21");

        ClickButton btn22 = new ClickButton();
        btn22.setName("签证服务");
        btn22.setType("click");
        btn22.setKey("22");

        ClickButton btn23 = new ClickButton();
        btn23.setName("个人信息");
        btn23.setType("click");
        btn23.setKey("23");

        ClickButton btn24 = new ClickButton();
        btn24.setName("签到赚积分");
        btn24.setType("click");
        btn24.setKey("24");

        // 菜单3
        ViewButton btn31 = new ViewButton();
        btn31.setName("OAuth授权");
        btn31.setType("view");
        btn31.setUrl(WeChatConts.oauth2Url);

        System.out.println(WeChatConts.oauth2Url);

        ClickButton btn32 = new ClickButton();
        btn32.setName("微信认证");
        btn32.setType("click");
        btn32.setKey("32");

        ClickButton btn33 = new ClickButton();
        btn33.setName("使用帮助");
        btn33.setType("click");
        btn33.setKey("33");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("嘻游介绍");
        mainBtn1.setSub_button(new Button[]{btn11, btn12, btn13, btn14});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("游客中心");
        mainBtn2.setSub_button(new Button[]{btn21, btn22, btn23, btn24});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多");
        mainBtn3.setSub_button(new Button[]{btn31, btn32, btn33});

        /**
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
