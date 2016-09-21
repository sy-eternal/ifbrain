package com.jzeen.travel.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/strategyfood")
public class StrategyFoodController {
	
	/**
	 * 默认跳转页面
	 * @return	美国美食界面
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/strategy/foodindex";
    }
}
