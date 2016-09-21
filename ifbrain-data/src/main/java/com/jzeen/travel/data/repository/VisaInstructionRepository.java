package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.InstructionFileRelate;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.VisaInstruction;

public interface VisaInstructionRepository extends AdvancedJpaRepository<VisaInstruction ,Integer>
{
    public VisaInstruction findById(Integer id);
}
