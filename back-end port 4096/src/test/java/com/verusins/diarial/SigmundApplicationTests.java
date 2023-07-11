package com.verusins.diarial;

import com.verusins.diarial.system.entity.User;
import com.verusins.diarial.system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SigmundApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Test
	void SQLCheck() {
		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::println);
	}

}
