package com.garret.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.garret.entity.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
