package petrossov.server.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

//singleton that's provide spring context
@Component
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext appContext = new FileSystemXmlApplicationContext("C:\\Users\\petro\\GwtSpringProject\\src\\main\\resources\\spring-context.xml");

    private AppContext() {
        System.out.println("Set doing" + appContext);
        setApplicationContext(appContext);

    }



    public static <T> T getBean(String name, Class<T> clazz){

        return AppContext.appContext.getBean(name,clazz);
    }

    public static Object getBean(String name){
        return AppContext.appContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        System.out.println("APPCONTEXT " + AppContext.appContext);
        return AppContext.appContext.getBean(clazz);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        AppContext.appContext = applicationContext;
    }
}
