package org.wallride.web;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wallride.core.domain.Setting;
import org.wallride.core.support.Settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetupCheckInterceptor extends HandlerInterceptorAdapter {

	private Settings settings;

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {
		String defaultLanguage = settings.readSettingAsString(Setting.Key.DEFAULT_LANGUAGE);
		if (StringUtils.hasText(defaultLanguage)) {
			throw new HttpForbiddenException();
		}
		return true;
	}
}
