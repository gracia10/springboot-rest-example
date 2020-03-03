package com.rest.domain.projection;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * (참고용) 수동으로 projection 추가
 * projection과 동일 혹은 하위 클래스에 있어야함
 * 
 */
//@Configuration
public class CustomizedRestMvcConfiguration implements RepositoryRestConfigurer {
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.getProjectionConfiguration().addProjection(UserOnlyContainName.class);
	}
}
