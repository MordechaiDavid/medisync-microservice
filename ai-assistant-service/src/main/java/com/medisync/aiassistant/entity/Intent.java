package com.medisync.aiassistant.entity;

public enum Intent {
    // User/Auth operations
    GET_ALL_USERS,
    GET_USER_BY_ID,
    CREATE_USER,
    LOGIN_USER,
    
    // Patient operations
    GET_ALL_PATIENTS,
    GET_PATIENT_BY_ID,
    CREATE_PATIENT,
    UPDATE_PATIENT,
    DELETE_PATIENT,
    
    // Doctor operations
    GET_ALL_DOCTORS,
    GET_DOCTOR_BY_ID,
    CREATE_DOCTOR,
    UPDATE_DOCTOR,
    DELETE_DOCTOR,
    
    // Appointment operations
    GET_ALL_APPOINTMENTS,
    GET_APPOINTMENT_BY_ID,
    CREATE_APPOINTMENT,
    UPDATE_APPOINTMENT,
    DELETE_APPOINTMENT,
    
    // Unknown/unsupported
    UNKNOWN
}