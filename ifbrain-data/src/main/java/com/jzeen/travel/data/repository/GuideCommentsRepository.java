package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideComments;
import com.jzeen.travel.data.entity.User;

public interface GuideCommentsRepository extends AdvancedJpaRepository<GuideComments, Integer>
{
}
