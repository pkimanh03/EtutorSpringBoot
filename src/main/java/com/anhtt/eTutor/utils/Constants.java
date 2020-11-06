package com.anhtt.eTutor.utils;

public class Constants {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    public static final String API_IMAGE_URI = "http://photos.etutor.top:8021/PhotosManager/api/images";

    public static final String SEND = "SEND";
    public static final String RECEIVE = "RECEIVE";
    public static final String TOPUP = "TOPUP";
    public static final String WWITHDRAWAL = "WITHDRAWAL";
    public static final String PAYMENT = "PAYMENT";

    public static final String ETUTOR_EMAIL = "admin@etutor.top";
    public static final String ETUTOR_PASSWORD = "admin123";
    public static final Long MIN_BALANCE = new Long(100000);

    public static final String TRANSACTION_START = "START TRANSACTION";
    public static final String TRANSACTION_NOT_ENOUGH_BALANCE = "Your balance is not enough for this transaction.";
    public static final String TRANSACTION_NOT_AVAILABLE_BALANCE = "Your balance is not available for this transaction. ";
    public static final String TRANSACTION_FAIL = "FAIL";
    public static final String TRANSACTION_SUCCESS = "SUCCESS";

    public static final String ATTENDANCE_PRESENT = "PRESENT";
    public static final String ATTENDANCE_NOTYET = "NOT_YET";
    public static final String ATTENDANCE_TUTOR = "ONLY_TUTOR";
    public static final String ATTENDANCE_STUDENT = "ONLY_STUDENT";

    public static final String CLASSHOUR_CANCELED = "CANCELED";
    public static final String CLASSHOUR_CREATED = "CREATED";
    public static final String CLASSHOUR_WAIT_ATTEND = "WAIT_ATTEND";
    public static final String CLASSHOR_ATTENDED = "ATTENDED";
    public static final String CLASSHOUR_REMIND = "REMIND";

    public static final String SLOT_MONDAY = "MONDAY";
    public static final String SLOT_TUESDAY = "TUESDAY";
    public static final String SLOT_WEDNESDAY = "WEDNESDAY";
    public static final String SLOT_THURSDAY = "THURSDAY";
    public static final String SLOT_FRIDAY = "FRIDAY";
    public static final String SLOT_SATURDAY = "SATURDAY";
    public static final String SLOT_SUNDAY = "SUNDAY";

    public static final String REGISTRATION_CREATED = "CREATED";
    public static final String REGISTRATION_CANCEL = "CANCELED";
    public static final String REGISTRATION_CLOSE = "CLOSED";
}
