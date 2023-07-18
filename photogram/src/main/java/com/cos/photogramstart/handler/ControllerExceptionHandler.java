package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import com.cos.photogramstart.util.Script;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // CustomValidationException로 발생한 예외를 낚아챔
    public String validationException(CustomValidationException e){
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script 좋음.
        // 2. Ajax통신 - CMRespDto
        // 3. Android통신 = CMRespDto
        // 정리하자면 클라이언트에게 응답할 떄는 Script 방식이 좋고 개발자에게 응답할 때는 나머지 두가지 방법이 좋음
        if(e.getErrorMap() == null){
            return Script.back(e.getMessage());
        }else{
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class) // CustomValidationException로 발생한 예외를 낚아챔
    public String exception(CustomException e){
        return Script.back(e.getMessage());
    }



    @ExceptionHandler(CustomValidationApiException.class) // CustomValidationException로 발생한 예외를 낚아챔
    public ResponseEntity<?> validationApiException(CustomValidationApiException e){
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()),  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class) // CustomValidationException로 발생한 예외를 낚아챔
    public ResponseEntity<?> apiException(CustomValidationApiException e){
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(-1, e.getMessage(), null),  HttpStatus.BAD_REQUEST);
    }
}
