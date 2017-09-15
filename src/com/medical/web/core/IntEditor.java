package com.medical.web.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class IntEditor extends CustomNumberEditor {
	public IntEditor() {
		super(Integer.class, true);
	}

	@Override
	public void setValue(Object value) {
		if (!StringUtils.isNotBlank(value+"")) {
			super.setValue(0);
		} else {
			super.setValue(value);
		}
	}
}
