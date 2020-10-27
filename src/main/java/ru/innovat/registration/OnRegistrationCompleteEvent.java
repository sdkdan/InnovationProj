//package ru.innovat.registration;
//
//import java.util.Locale;
//
//
//import org.springframework.context.ApplicationEvent;
//import ru.innovat.models.AppUser;
//
//@SuppressWarnings("serial")
//public class OnRegistrationCompleteEvent extends ApplicationEvent {
//
//    private final String appUrl;
//    private final Locale locale;
//    private final AppUser user;
//
//    public OnRegistrationCompleteEvent(final AppUser user, final Locale locale, final String appUrl) {
//        super(user);
//        this.user = user;
//        this.locale = locale;
//        this.appUrl = appUrl;
//    }
//
//    //
//
//    public String getAppUrl() {
//        return appUrl;
//    }
//
//    public Locale getLocale() {
//        return locale;
//    }
//
//    public AppUser getUser() {
//        return user;
//    }
//
//}