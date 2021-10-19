package com.yevheniimakar;


import com.yevheniimakar.config.DataSource;
import com.yevheniimakar.controller.OperationController;
import com.yevheniimakar.controller.ReportController;
import com.yevheniimakar.dto.UserDto;
import com.yevheniimakar.service.UserService;
import com.yevheniimakar.service.impl.UserServiceImpl;
import com.yevheniimakar.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        if (args.length == 3) {
            DataSource.setSessionFactoryWithPasswordAndUser(args[2], args[1]);
        }

        UserService userService = new UserServiceImpl();
        UserDto userDto = userService.getUserDtoByPhoneNumber(args[0]);
        if (userDto != null) {
            System.out.println("press 1 to add new operaion or press 2 to create report");
            String choice = Util.getStringFromConsole().trim();
            switch (choice) {
                case "1": {
                    log.info("coiced add new operation");
                    OperationController operationController = new OperationController();
                    operationController.addOperation(userDto);
                    break;
                }
                case "2": {
                    log.info("coiced create report");
                    ReportController reportController = new ReportController();
                    reportController.createReport(userDto);
                    break;
                }
                default: {
                    log.error("incorrect coice : {}", choice);
                }

            }
        }


    }
}
