package com.rest.domain.projection;

import org.springframework.data.rest.core.config.Projection;

import com.rest.domain.User;

@Projection(name="getOnlyName", types = {User.class})
public interface UserOnlyContainName {
	String getName();
}
