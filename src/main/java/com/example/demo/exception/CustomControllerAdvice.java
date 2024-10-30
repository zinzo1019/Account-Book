package com.example.demo.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
class CustomControllerAdvice {

    /**
     * handleNullPointerException에 대처한다.
     * null 객체 참조에서 메소드를 호출하려고 할 때 발생하는 예외
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CustomResponseDto> handleNullPointerException(NullPointerException e) {
        HttpHeaders resHeaders = setHeadersUTF8();
        CustomResponseDto responseDto = new CustomResponseDto(new CustomException(CustomErrorCode.NULL_REFERENCE));
        return new ResponseEntity<>(responseDto, resHeaders, responseDto.getStatus());
    }

    /**
     * handleIllegalArgumentException에 대처한다.
     * 호출자가 인수로 부적절한 값을 넘길 때 던지는 예외
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpHeaders resHeaders = setHeadersUTF8();
        CustomResponseDto responseDto = new CustomResponseDto(new CustomException(CustomErrorCode.INVALID_PARAMETER));
        return new ResponseEntity<>(responseDto, resHeaders, responseDto.getStatus());
    }

    /**
     * DataAccessException 대처한다.
     * @param e
     * @return
     *
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<CustomResponseDto> handleDataAccessException(DataAccessException e) {
        HttpHeaders resHeaders = setHeadersUTF8();
        CustomResponseDto responseDto = new CustomResponseDto(new CustomException(CustomErrorCode.DATABASE_ERROR));
        return new ResponseEntity<>(responseDto, resHeaders, responseDto.getStatus());
    }

    /**
     * FileNotFoundException 대처한다.
     * 파일을 찾을 수 없을 때 발생하는 예외
     * @param e
     * @return
     */
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<CustomResponseDto> handleFileNotFoundException(FileNotFoundException e) {
        HttpHeaders resHeaders = setHeadersUTF8();
        CustomResponseDto responseDto = new CustomResponseDto(new CustomException(CustomErrorCode.FILE_NOT_FOUND));
        return new ResponseEntity<>(responseDto, resHeaders, responseDto.getStatus());
    }

    /**
     * FileUploadException 대처한다.
     * @param e
     * @return
     */
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<CustomResponseDto> handleFileUploadException(FileUploadException e) {
        HttpHeaders resHeaders = setHeadersUTF8();
        CustomResponseDto responseDto = new CustomResponseDto(new CustomException(CustomErrorCode.FILE_UPLOAD_ERROR));
        return new ResponseEntity<>(responseDto, resHeaders, responseDto.getStatus());
    }

    /**
     * 커스텀 예외에 대처한다.
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponseDto> handleException(CustomException e) {
        HttpHeaders resHeaders = setHeadersUTF8();
        CustomResponseDto responseDto = new CustomResponseDto(e);
        return new ResponseEntity<>(responseDto, resHeaders, responseDto.getStatus());
    }

    /**
     * 그 외의 예외에 대처한다.
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDto> handleGeneralException(Exception e) {
        HttpHeaders resHeaders = setHeadersUTF8();
        CustomResponseDto responseDto = new CustomResponseDto(new CustomException(CustomErrorCode.GENERAL_EXCEPTION));
        return new ResponseEntity<>(responseDto, resHeaders, responseDto.getStatus());
    }

    /**
     * 헤더 UTF-8 세팅
     * @return HttpHeaders
     */
    private HttpHeaders setHeadersUTF8() {
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        return resHeaders;
    }
}
