package com.csl.mybatis.exception;

public class MybatisDAOException extends RuntimeException {

	public MybatisDAOException() {
		super();
	}

	public MybatisDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public MybatisDAOException(String message) {
		super(message);
	}

	public MybatisDAOException(Throwable cause) {
		super(cause);
	}

}
