package org.demo.controller;

import org.demo.domain.Record;

import java.util.Date;

@SuppressWarnings("unused")
public class RecordResource {
    public Date created;
    public Date last_updated;

    public RecordResource() {}

    public RecordResource(Record record) {
        this.created = record.getCreated();
        this.last_updated = record.getLastUpdate();
    }
}
