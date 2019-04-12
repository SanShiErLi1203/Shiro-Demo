package com.cloudta.shirodemo.exception;

import com.cloudta.shirodemo.common.BaseResult;
import com.cloudta.shirodemo.constant.MsgCodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
　　　　　　　　 NoSuchMethodException,IOException,IndexOutOfBoundsException
　　　　　　　　 以及springmvc自定义异常等，如下：
SpringMVC自定义异常对应的status code  
           Exception                       HTTP Status Code  
ConversionNotSupportedException         500 (Internal Server Error)
HttpMessageNotWritableException         500 (Internal Server Error)
HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
NoSuchRequestHandlingMethodException    404 (Not Found) 
TypeMismatchException                   400 (Bad Request)
HttpMessageNotReadableException         400 (Bad Request)
MissingServletRequestParameterException 400 (Bad Request)
 *
 */
//@ControllerAdvice
public class RestExceptionHandler {
	
	public static Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	// 运行时异常
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public BaseResult runtimeExceptionHandler() {
		LOG.info("Come IN RestExceptionHandler.runtimeExceptionHandler()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.RUNTIME_ERR_MSG.MSG_VALUE());
	}

	// 空指针异常
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public BaseResult nullPointerExceptionHandler() {
		LOG.info("Come IN RestExceptionHandler.nullPointerExceptionHandler()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.NULL_ERR_MSG.MSG_VALUE());
	}

	// 类型转换异常
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public BaseResult classCastExceptionHandler() {
		LOG.info("Come IN RestExceptionHandler.classCastExceptionHandler()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.CLASSCAST_ERR_MSG.MSG_VALUE());
	}

	// IO异常
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public BaseResult iOExceptionHandler() {
		LOG.info("Come IN RestExceptionHandler.iOExceptionHandler()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.IO_ERR_MSG.MSG_VALUE());
	}

	// 未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public BaseResult noSuchMethodExceptionHandler() {
		LOG.info("Come IN RestExceptionHandler.noSuchMethodExceptionHandler()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.METHOD_UNKNOWN_ERR_MSG.MSG_VALUE());
	}

	// 数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseBody
	public BaseResult indexOutOfBoundsExceptionHandler() {
		LOG.info("Come IN RestExceptionHandler.indexOutOfBoundsExceptionHandler()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.INDEX_OUT_ERR_MSG.MSG_VALUE());
	}

	// 400错误
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseBody
	public BaseResult requestNotReadable() {
		LOG.info("Come IN RestExceptionHandler.requestNotReadable()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.E400_ERR_MSG.MSG_VALUE());
	}

	// 400错误
	@ExceptionHandler({ TypeMismatchException.class })
	@ResponseBody
	public BaseResult requestTypeMismatch() {
		LOG.info("Come IN RestExceptionHandler.requestTypeMismatch()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.E400_ERR_MSG.MSG_VALUE());
	}

	// 400错误
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	@ResponseBody
	public BaseResult requestMissingServletRequest() {
		LOG.info("Come IN RestExceptionHandler.requestMissingServletRequest()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.E400_ERR_MSG.MSG_VALUE());
	}

	// 405错误
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	public BaseResult request405() {
		LOG.info("Come IN RestExceptionHandler.request405()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.E405_ERR_MSG.MSG_VALUE());
	}

	// 406错误
	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	@ResponseBody
	public BaseResult request406() {
		LOG.info("Come IN RestExceptionHandler.request406()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.E406_ERR_MSG.MSG_VALUE());
	}

	// 500错误
	@ExceptionHandler({ ConversionNotSupportedException.class, HttpMessageNotWritableException.class })
	@ResponseBody
	public BaseResult server500() {
		LOG.info("Come IN RestExceptionHandler.server500()");
		return BaseResult.jsonData(MsgCodeConstant.FAIL_CODE.CODE_VALUE(), MsgCodeConstant.E500_ERR_MSG.MSG_VALUE());
	}
}