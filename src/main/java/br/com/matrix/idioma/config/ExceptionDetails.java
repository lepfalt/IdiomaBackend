package br.com.matrix.idioma.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExceptionDetails{

	private String title;
	private int status;
	private String detail;
	private String timeStamp;
	private String devMessage;
}
