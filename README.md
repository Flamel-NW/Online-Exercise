# OnlineExercise

20道题，五个知识点，每次考试抽取10道，满分20分

## 数据库

    question {
        id: (int not null)
        stem: (varchar not null) // 题干
        weight: (int not null 0 <= weight <= 100) 
        chosen_times: (int not null) // 被抽选的次数
        total_score: (int not null) // 合计得分
        cheat_times: (int not null) // 疑似作弊次数

        option1: (varchar)
        option2: (varchar)
        option3: (varchar)
        option4: (varchar)
        option5: (varchar)
    }

    param {
      id (int not null)
      qid (int not null)
      param (varchar)
      weight: (int not null 0 <= weight <= 100) 

      answer1 (int not null)
      answer2 (int not null)
      answer3 (int not null)
      answer4 (int not null)
      answer5 (int not null)
    }

    exam {
      id: (int not null), 
      uid: (int not null), // 考号
      start_time: (varchar not null),
      score: (int not null) // 满分 20
      finished: (int not null) // 0 未完成, 1 已完成

      type1: (int not null),
      type2: (int not null),
      type3: (int not null),
      type4: (int not null),
      type5: (int not null),
    }

    user {
      id: (int not null),
      username: (varchar not null),
      password: (varchar not null),
    }

    exam_to_question {
      id: (int not null),
      eid: (int not null),
      qid: (int not null),
    }

## 接口

    R: {
      "code": , // 0 成功; 1 操作失败; 2 破坏空字段或外键约束被捕获; 100 服务器异常; 101 数据库异常; 102 权限不足
      "msg": ,
      "data": {

      }
    }

### 前台

- 登录  
  post, ***/foreground/login

      {
        // user
      }
- 开始考试  
  get, ***/foreground/start/{uid}

      {
        // exam, question
      }

- 提交答案      
  put, ***/foreground/submit

      {
        exam.id: ,
        question.id: ,
        answer: 
      }

- 获取下一题  
  get, ***/foreground/next/{exam.id}   

      {
        // exam, question
      }

- 结束考试    
  delete, ***/foreground/end/{exam.id}

### 后台

