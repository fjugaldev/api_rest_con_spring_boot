package com.mandalorian.api.shared.enums;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("500-internal-server-error"),
    BAD_CREDENTIALS_ERROR("1001-bad-credentials-error"),
    ROLE_NOT_FOUND_ERROR("1002-role-not-found-error"),
    USER_NOT_FOUND_ERROR("1003-user-not-found-error"),
    CUSTOM_JSON_DESERIALIZATION_ERROR("1004-custom-json-deserialization-error"),
    GOOGLE_CLOUD_STORAGE_ERROR("1005-google-cloud-storage-error"),
    MALFORMED_URL_ERROR("1006-malformed-url-error"),
    METAMAP_USER_ID_NOT_FOUND("1007-metamap-user-id-not-found"),
    JSON_SERIALIZATION_ERROR("1008-json-serialization-error"),
    USER_WITHOUT_RELATED_STORE_ERROR("1009-user-without-related-store-error"),
    USER_DISABLED_ERROR("1010-user-disabled-error");

    private final String value;

    public String value() {
        return value;
    }

    ErrorCode(String value) {
        this.value = value;
    }

}
