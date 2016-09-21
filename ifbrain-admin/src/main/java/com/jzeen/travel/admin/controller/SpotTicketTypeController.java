package com.jzeen.travel.admin.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.QSpotTicketType;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotTicketType;
import com.jzeen.travel.data.repository.SpotRepository;
import com.jzeen.travel.data.repository.SpotTicketTypeRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/spotstickettype")
public class SpotTicketTypeController
{
    @Autowired
    private SpotTicketTypeRepository _spotTicketTypeRepository;

    @Autowired
    private SpotRepository _spotRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request)
    {
        return "/spotstickettype/list";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<SpotTicketType, Integer> search(HttpServletRequest request, Model model, Spot spots)
    {
        Integer spotsTicketId = Integer.parseInt(request.getParameter("spotsTicketId"));
        QSpotTicketType spotTicketType = QSpotTicketType.spotTicketType;
        Predicate predicate = spotTicketType.spots.id.eq(spotsTicketId);
        DataTable<SpotTicketType, Integer> dataTable = DataTable.fromRequest(request, _spotTicketTypeRepository,
                predicate);
        return dataTable;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute SpotTicketType spotstickettype, Model model, Integer spotsTicketId)
    {
        Spot spots = _spotRepository.findOne(spotsTicketId);
        model.addAttribute("spots", spots);
        model.addAttribute("spotstickettype", spotstickettype);
        return "/spotstickettype/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SpotTicketType spotstickettype, BindingResult bindingResult, Model model,
            Integer spotsTicketId)
    {
        if (bindingResult.hasErrors())
        {
            List<Spot> spots = _spotRepository.findAll();
            model.addAttribute("spots", spots);
            return new ModelAndView("/spotstickettype/create", "formErrors", bindingResult.getAllErrors());
        }
        Spot spots = _spotRepository.findById(spotsTicketId);
        spotstickettype.setSpots(spots);
        spotstickettype.setCreateTime(new Date());
        _spotTicketTypeRepository.save(spotstickettype);
        return new ModelAndView("redirect:/spots/tickettype/" + spotsTicketId);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        SpotTicketType spotstickettype = _spotTicketTypeRepository.findOne(id);
        model.addAttribute("spotstickettype", spotstickettype);
        List<Spot> spots = _spotRepository.findAll();
        model.addAttribute("spots", spots);
        return "/spotstickettype/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid SpotTicketType spotstickettype, BindingResult bindingResult, Model model, int id,
            HttpServletRequest request)
    {
        if (bindingResult.hasErrors())
        {
            List<Spot> spots = _spotRepository.findAll();
            model.addAttribute("spots", spots);
            return "/spotstickettype/update";
        }
        SpotTicketType oldspotstickettype = _spotTicketTypeRepository.findOne(spotstickettype.getId());
        Date createTime = oldspotstickettype.getCreateTime();
        Spot spots = oldspotstickettype.getSpots();
        spotstickettype.setCreateTime(createTime);
        spotstickettype.setSpots(spots);
        _spotTicketTypeRepository.save(spotstickettype);
        return "redirect:/spots/tickettype/" + oldspotstickettype.getSpots().getId();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _spotTicketTypeRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getBySpotId", method = RequestMethod.GET)
    public List<SpotTicketType> getBySpotId(@RequestParam Integer spotId)
    {
        List<SpotTicketType> spotTicketTypes = _spotTicketTypeRepository.findBySpotsId(spotId);
        return spotTicketTypes;
    }
  //判断门票类型重复添加
    /*@ResponseBody
    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
    public Boolean valueistrue(String type)
    {
        SpotTicketType spotstic = _spotTicketTypeRepository.findByType(type);
        if (spotstic == null)
        {
            System.out.println("true");
            return true;
        }
        else
        {
            System.out.println("false");
            return false;
        }
    }*/
}
