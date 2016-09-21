package com.jzeen.travel.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.MaterialComment;
import com.jzeen.travel.data.entity.MaterialType;

public interface MaterialCommentRepository extends AdvancedJpaRepository<MaterialComment, Integer>
{
   List<MaterialComment> findByMaterialIdOrderByCreateTimeDesc(Integer materialId);
}
