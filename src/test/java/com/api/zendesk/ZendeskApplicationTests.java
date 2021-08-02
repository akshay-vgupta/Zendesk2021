package com.api.zendesk;

import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;


class ZendeskApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test1() {
		Result result = JUnitCore.runClasses(MockRender.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println("All test passed::"+result.wasSuccessful());

		Result resultTest = JUnitCore.runClasses(MockRest.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println("All test passed::"+result.wasSuccessful());

	}
	}