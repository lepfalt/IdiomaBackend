package br.com.matrix.idioma;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.matrix.idioma.modelRestAssured.AudioRestAssured;
import br.com.matrix.idioma.modelRestAssured.MarkingRestAssured;
import br.com.matrix.idioma.modelRestAssured.UserRestAssured;

@RunWith(Suite.class)
@SuiteClasses({ AudioRestAssured.class, MarkingRestAssured.class, UserRestAssured.class })
public class SuiteTest {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(SuiteTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
