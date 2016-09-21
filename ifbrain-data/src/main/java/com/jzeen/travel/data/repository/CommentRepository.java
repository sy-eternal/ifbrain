package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Comment;
import com.jzeen.travel.data.entity.IfbrainIndex;

public interface CommentRepository extends AdvancedJpaRepository<Comment, Integer>
{
	@Query("select c from Comment as c inner join c.ifbrainIndex as i where i.ordinalNumber=:ordinalNumber and i.courseLevel=:courseLevel  order by c.createTime asc")
	List<Comment> findByCommentList(@Param("ordinalNumber") Integer ordinalNumber,@Param("courseLevel") String courseLevel);
	
	public List<Comment> findByIfbrainIndex(IfbrainIndex ifbrainindex);

}
