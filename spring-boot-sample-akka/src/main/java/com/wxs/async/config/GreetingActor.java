package com.wxs.async.config;

import akka.actor.UntypedActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GreetingActor extends UntypedActor {

    private GreetingService greetingService;

    // constructor

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Greet) {
            String name = ((Greet) message).getName();
            getSender().tell(greetingService.greet(name), getSelf());
        } else {
            unhandled(message);
        }
    }

    public static class Greet {

        private String name;

        // standard constructors/getters

    }
}
