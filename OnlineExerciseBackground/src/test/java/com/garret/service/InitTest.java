package com.garret.service;

import com.garret.entity.Param;
import com.garret.entity.Question;
import com.garret.entity.User;
import com.garret.mapper.ParamMapper;
import com.garret.mapper.QuestionMapper;
import com.garret.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InitTest {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ParamMapper paramMapper;

    @Test
    void init() {
        initUser();
        initQuestion();
    }

    @Test
    void initUser() {
        List<User> userList = userMapper.selectList(null);
        if (userList.isEmpty())
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setUsername("test" + (i + 1));
                user.setPassword("123456");
                userMapper.insert(user);
                System.out.println(user.getId());
            }
    }

    @Test
    void initQuestion() {
        List<Question> questions = questionMapper.selectList(null);
        if (questions.isEmpty())
            for (int i = 0; i < 20; i++) {
                Question question = new Question();
                question.setStem(i + 1 + ": %param");
                question.setWeight(50);
                question.setChosenTimes(0);
                question.setTotalScore(0);
                question.setCheatTimes(0);
                question.setOption1("test a");
                question.setOption2("test b");
                question.setOption3("test c");
                question.setOption4("test d");
                question.setOption5("test e");
                questionMapper.insert(question);

                Param param = new Param();
                param.setQid(i + 1);
                param.setParam("default param" + (i + 1));
                param.setWeight(50);

                param.setAnswer1(false);
                param.setAnswer2(false);
                param.setAnswer3(false);
                param.setAnswer4(false);
                param.setAnswer5(false);

                paramMapper.insert(param);
            }
    }
}
