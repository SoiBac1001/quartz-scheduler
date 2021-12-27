package com.example.demospringquartz.batch.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

import java.util.List;

@Slf4j
public class ConsoleItemWriter extends AbstractItemStreamItemWriter {
    @Override
    public void write(List list) throws Exception {
        list.stream().forEach(System.out::println);
        log.info("********** Writing each chunk **********");
    }
}
