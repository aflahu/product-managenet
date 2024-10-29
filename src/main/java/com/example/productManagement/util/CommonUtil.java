package com.example.productManagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName;
        Object principal = authentication.getPrincipal();

        if (principal != null) {
            currentUserName = authentication.getName();
        } else {
            currentUserName = "system";

        }

        return currentUserName;
    }

    public static Instant getInstantAtTimezone(String timezone) {
        ZonedDateTime now = Instant.now().atZone(ZoneId.of(timezone));
        return now.toInstant();
    }

    public static long getDifferentEpochMilliAtTimezone(String time, String timezone) {
        long timeInMillis = Long.parseLong(time);
        ZoneId zoneId = ZoneId.of(timezone);
        Instant nowInstant = Instant.now().atZone(zoneId).toInstant();
        Instant endInstant = Instant.ofEpochMilli(timeInMillis).atZone(zoneId).toInstant();
        return nowInstant.until(endInstant, ChronoUnit.SECONDS);
    }

    public static String getFormattedLocalDateTimeAtTimezone(String time, String timezone, String pattern) {
        long timeInMillis = Long.parseLong(time);
        return getFormattedLocalDateTimeAtTimezoneToString(timeInMillis, timezone, pattern);
    }

    public static String getFormattedLocalDateTimeAtTimezoneToString(long time, String timezone, String pattern) {
        ZoneId zoneId = ZoneId.of(timezone);
        Instant instant = Instant.ofEpochMilli(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime.format(formatter);
    }

    public static String getFormattedZoneDateTimeAtTimezoneToString(long time, String timezone, String pattern) {
        ZoneId zoneId = ZoneId.of(timezone);
        Instant instant = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return zonedDateTime.format(formatter);
    }

    public static JsonNode readJsonNode(String json) throws JsonProcessingException {
        return mapper.readTree(json);
    }

    public static JsonNode readJsonNode(HttpServletRequest request) throws IOException {
        return mapper.readTree(request.getInputStream());
    }

    public static String writeObjectToJson(JsonNode jsonNode) throws JsonProcessingException {
        return mapper.writeValueAsString(jsonNode);
    }

    public static String writeObjectToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
