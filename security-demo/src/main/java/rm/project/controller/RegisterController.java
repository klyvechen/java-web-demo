package rm.project.controller;

import rm.project.enums.RegisterEnum;
import rm.project.facade.RegisterFacade;
import rm.project.restful.input.RegisterInput;
import rm.project.restful.output.ErrorOutput;
import rm.project.restful.output.RegisterOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 註冊Controller
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/")
@Tag(name = "Register APIs")
public class RegisterController {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private RegisterFacade registerFacade;

    @Operation(summary = "註冊", description = "使用手機或Email進行註冊")
    @PostMapping(path = "/user/register",  consumes = "application/json")
    public Object register(@RequestBody(required = false) RegisterInput input) {
        RegisterEnum result;

        result = registerFacade.checkInput(input);

        String language = input.getLanguage();
        String country = input.getCountry();

        if (result != RegisterEnum.REGISTER_SUCCESS) {
            return new ErrorOutput(language, country, result.name());
        }

        try {
            result = registerFacade.register(input);
        } catch (Exception e) {
            e.printStackTrace();
            result = RegisterEnum.UNEXPECTED_EXCEPTION;
            return new ErrorOutput(language, country, result.name());
        }

        if (result == RegisterEnum.REGISTER_SUCCESS) {
            return new RegisterOutput("aslfdkjlsdkj1lrjlkafls");
        }
        return new ErrorOutput(language, country, result.name());
    }

}
