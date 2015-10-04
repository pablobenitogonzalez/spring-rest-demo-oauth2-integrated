package org.demo.service;

@SuppressWarnings("unused")
public interface MessageService {
    String getMessage(String id);
    String getMessage(String id, Object[] objects);
}
