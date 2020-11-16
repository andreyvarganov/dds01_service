package ru.telda.dds_01_integration_service.notify_service;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Notify {
    private UUID notifyId;
    private LocalDateTime date;
    private Boolean approved;
}
