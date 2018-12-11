package com.planner.session;

import javax.faces.context.FacesContext;

public class SessionManager {
	public static Object getValueFromAplicationMap(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
		}

		public static void setValueInAplicationMap(String key, Object value) {
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
		} 
	
	
}
