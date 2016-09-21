package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.CityPlan;
import com.jzeen.travel.data.entity.CommentReply;

public interface CommentReplyRepository extends AdvancedJpaRepository<CommentReply, Integer>
{
		List <CommentReply> findByMaterialCommentId(Integer materialCommentid);
		
		  public void deleteByMaterialCommentId(Integer materialCommentid);
}
