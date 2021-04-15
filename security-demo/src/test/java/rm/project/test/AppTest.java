package rm.project.test;

import rm.project.controller.RegisterController;
import rm.project.restful.input.RegisterInput;
import rm.project.restful.output.ErrorOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AppTest {

    @Autowired
    RegisterController registerController;

    @Test
    public void testWrongEmailFormat() {
        RegisterInput input = new RegisterInput();
        input.setName("input");
        input.setLanguage("ch");
        input.setCountry("TW");
        input.setType(0);
        input.setValue("aa");
        input.setCheckPassword("password");
        input.setPassword("password");
        Object o = null;
        try {
            o = registerController.register(input);
        } catch (Exception e) {
        }
        assert(o instanceof ErrorOutput);
        assert(((ErrorOutput) o).getErrorMessage().equals("Email格式不正確"));
    }

    @Test
    public void testEmailRegistered() {
        RegisterInput input = new RegisterInput();
        input.setName("input");
        input.setLanguage("ch");
        input.setCountry("TW");
        input.setType(0);
        input.setValue("aa@gmail.com");
        input.setCheckPassword("password");
        input.setPassword("password");
        Object o = registerController.register(input);
        o = registerController.register(input);
        assert(o instanceof ErrorOutput);
        assert(((ErrorOutput) o).getErrorMessage().equals("Email已被使用"));
    }

    @Test
    public void testEmailRegisteredEn() {
        RegisterInput input = new RegisterInput();
        input.setName("input");
        input.setLanguage("en");
        input.setCountry("US");
        input.setType(0);
        input.setValue("aa@gmail.com");
        input.setCheckPassword("password");
        input.setPassword("password");
        Object o = registerController.register(input);
        o = registerController.register(input);
        assert(o instanceof ErrorOutput);
        String errorMessage = ((ErrorOutput) o).getErrorMessage();
        assert(errorMessage.equals("Email has been registered"));
    }

    @Test
    public void testEmailRegisteredJp() {
        RegisterInput input = new RegisterInput();
        input.setName("input");
        input.setLanguage("jp");
        input.setCountry("JP");
        input.setType(0);
        input.setValue("aa@gmail.com");
        input.setCheckPassword("password");
        input.setPassword("password");
        Object o = registerController.register(input);
        o = registerController.register(input);
        assert(o instanceof ErrorOutput);
        assert(((ErrorOutput) o).getErrorMessage().equals("メールが使用されました"));
    }

}
