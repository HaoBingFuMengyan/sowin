package com.hbf;

import com.hbf.sys.AdminController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MasterApplication {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	public static void main(String[] args) {
		try{
			SpringApplication.run(MasterApplication.class, args);
			logger.info("Application：应用程序启动成功");
		}catch(Exception e){
			if(StringUtils.isNotBlank(e.getMessage())){
				e.printStackTrace();
			}
		}

	}
}
