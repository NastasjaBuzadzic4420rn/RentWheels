package com.raf.restdemo.epoch;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class EpochConverter {

    public LocalDate toLocalDate(long date){
        return Instant.ofEpochSecond(date).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public long toLong(LocalDate date){
        return date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli()/1000;
    }
}
