package com.excilys.computerdb.fconsigny.app.log;

import java.util.logging.Logger;

public abstract class DoLogger {

	public static void doLog(Class<?> inputClass, String message){
		Logger.getLogger(inputClass.getSimpleName() + " " + message);
	}

}
