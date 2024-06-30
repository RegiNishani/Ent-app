package com.op.bootfaces.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("activeStatusConverter")
public class ActiveStatusConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// Not needed for display purposes
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof String) {
			String status = (String) value;
			return "Y".equals(status) ? "Active" : "Inactive";
		}
		return null;
	}
}
