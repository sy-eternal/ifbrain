package com.jzeen.travel.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.repository.DemoUserRepository;

/**
 * RestController的示例类，可以删掉。
 */
@RestController
@RequestMapping("/restful")
public class RestfulController
{
    @Autowired
    DemoUserRepository _userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public DataTable<DemoUser, Integer> users(HttpServletRequest request)
    {
        int draw = 0;
        if (request.getParameter("draw") != null)
        {
            draw = Integer.parseInt(request.getParameter("draw"));
        }
        int start = 0;
        if (request.getParameter("start") != null)
        {
            start = Integer.parseInt(request.getParameter("start"));
        }
        int length = 10;
        if (request.getParameter("length") != null)
        {
            length = Integer.parseInt(request.getParameter("length"));
        }

        int page = start / length;
        int size = length;
        Pageable pageable = new PageRequest(page, size);
        Page<DemoUser> users = _userRepository.findAll(pageable);

        int totalCount = (int) users.getTotalElements();

        DataTable<DemoUser, Integer> dataTable = new DataTable<DemoUser, Integer>();
        dataTable.setDraw(draw);
        dataTable.setRecordsTotal(totalCount);
        dataTable.setRecordsFiltered(totalCount);
        dataTable.setData(users.getContent());

        return dataTable;
    }
}
