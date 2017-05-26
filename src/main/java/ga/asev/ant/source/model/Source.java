package ga.asev.ant.source.model;

import lombok.Data;
import lombok.extern.java.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

@Data
public class Source {
    private String id;
    private String name;
    private String url;
    private String nameAttrId;
    private String itemSelector;
    private Scheduled scheduled;
    private List<SourceAttr> attrs;

    public boolean isNameAttr(String attrId) {
        return nameAttrId.equals(attrId);
    }

    @Data
    public static class Scheduled {
        private String cron;
    }

    @Data
    @Log
    public static class SourceAttr {
        private String id;
        private SourceAttrType type;
        private String selector;
        private String format;

        public Object parse(String value) {
            switch (type) {
                case DATE:
                    return formatDate(value);
                default:
                    return value;
            }
        }

        private Date formatDate(String text) {
            try {
                DateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
                return formatter.parse(text);
            } catch (ParseException e) {
                log.log(Level.SEVERE, e.getMessage(), e);
                return null;
            }
        }


    }

}
