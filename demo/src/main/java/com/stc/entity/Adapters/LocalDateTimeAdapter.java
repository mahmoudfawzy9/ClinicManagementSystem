
        package com.si.entity.adapters;

        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author mahmoud
 */
        public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

        @Override
        public LocalDateTime unmarshal(String dateTimeString) throws Exception {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        @Override
        public String marshal(LocalDateTime localDateTime) throws Exception {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
        }

        }