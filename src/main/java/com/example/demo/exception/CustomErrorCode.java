package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    // 데이터베이스 관련 오류
      DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB_001", "데이터베이스 오류가 발생했습니다.")
    , DATA_INTEGRITY_VIOLATION(HttpStatus.CONFLICT, "DB_002", "데이터 무결성 위반 오류가 발생했습니다.")
    , DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "DB_003", "데이터를 찾을 수 없습니다.")
    , DUPLICATE_ENTRY(HttpStatus.CONFLICT, "DB_004", "중복된 데이터가 있습니다.")

    // 요청 관련 오류
    , BAD_REQUEST(HttpStatus.BAD_REQUEST, "REQ_001", "잘못된 요청입니다.")
    , MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "REQ_002", "필수 매개변수가 누락되었습니다.")
    , INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "REQ_003", "잘못된 매개변수입니다.")
    , UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "REQ_004", "지원하지 않는 미디어 타입입니다.")
    , NULL_REFERENCE(HttpStatus.BAD_REQUEST, "REQ_005", "null 값을 가진 객체를 참조하고 있습니다.")

    // 인증 및 권한 오류
    , UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "AUTH_001", "인증이 필요합니다.")
    , FORBIDDEN_OPERATION(HttpStatus.FORBIDDEN, "AUTH_002", "이 작업은 허용되지 않습니다.")
    , TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "AUTH_003", "토큰이 만료되었습니다.")
    , TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "AUTH_004", "유효하지 않은 토큰입니다.")
    , NO_PERMISSION(HttpStatus.UNAUTHORIZED, "AUTH_005", "권한이 없습니다.")

    // 리소스 관련 오류
    , RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "RES_001", "요청한 리소스를 찾을 수 없습니다.")
    , RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "RES_002", "이미 존재하는 리소스입니다.")
    , RESOURCE_LOCKED(HttpStatus.LOCKED, "RES_003", "리소스가 잠겨 있습니다.")
    , RESOURCE_CONFLICT(HttpStatus.CONFLICT, "RES_004", "리소스 상태와 충돌이 발생했습니다.")

    // 서버 내부 오류
    , INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SYS_001", "서버 내부 오류가 발생했습니다.")
    , SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "SYS_002", "서비스를 일시적으로 사용할 수 없습니다.")
    , TIMEOUT_ERROR(HttpStatus.GATEWAY_TIMEOUT, "SYS_003", "요청 시간이 초과되었습니다.")
    , UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SYS_004", "알 수 없는 오류가 발생했습니다.")

    // 파일 관련 오류
    , FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "FILE_001", "파일을 찾을 수 없습니다.")
    , FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_002", "파일 업로드 중 오류가 발생했습니다.")
    , FILE_DOWNLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_003", "파일 다운로드 중 오류가 발생했습니다.")
    , FILE_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FILE_004", "파일 삭제 중 오류가 발생했습니다.")
    , FILE_SIZE_EXCEEDED(HttpStatus.PAYLOAD_TOO_LARGE, "FILE_005", "파일 크기가 허용된 최대 크기를 초과했습니다.")
    , UNSUPPORTED_FILE_FORMAT(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "FILE_006", "지원하지 않는 파일 형식입니다.")

    // 외부 서비스 통신 오류
    , EXTERNAL_SERVICE_ERROR(HttpStatus.BAD_GATEWAY, "EXT_001", "외부 서비스와의 통신 중 오류가 발생했습니다.")
    , EXTERNAL_SERVICE_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "EXT_002", "외부 서비스와의 통신이 시간 초과되었습니다.")
    , EXTERNAL_SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "EXT_003", "외부 서비스가 일시적으로 사용 불가능합니다.")

    // 유효성 검사 오류
    , VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "VAL_001", "유효성 검사에 실패했습니다.")
    , INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "VAL_002", "잘못된 이메일 형식입니다.")
    , PASSWORD_TOO_WEAK(HttpStatus.BAD_REQUEST, "VAL_003", "비밀번호가 너무 약합니다.")
    , USERNAME_ALREADY_TAKEN(HttpStatus.CONFLICT, "VAL_004", "이미 사용 중인 사용자 이름입니다.")

    // 기타 오류
    , GENERAL_EXCEPTION(HttpStatus.BAD_REQUEST, "E999", "오류가 발생했습니다.");

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    }