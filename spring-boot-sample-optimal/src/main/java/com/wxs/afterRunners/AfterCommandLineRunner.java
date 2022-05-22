package com.wxs.afterRunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Spring boot 启动后执行
 *
 * @See private void callRunners(ApplicationContext context, ApplicationArguments args) {
 * List<Object> runners = new ArrayList();
 * runners.addAll(context.getBeansOfType(ApplicationRunner.class).values());
 * runners.addAll(context.getBeansOfType(CommandLineRunner.class).values());
 * AnnotationAwareOrderComparator.sort(runners);
 * Iterator var4 = (new LinkedHashSet(runners)).iterator();
 * <p>
 * while(var4.hasNext()) {
 * Object runner = var4.next();
 * if (runner instanceof ApplicationRunner) {
 * this.callRunner((ApplicationRunner)runner, args);
 * }
 * <p>
 * if (runner instanceof CommandLineRunner) {
 * this.callRunner((CommandLineRunner)runner, args);
 * }
 * }
 * <p>
 * }
 */
@Component
public class AfterCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("AfterCommandLineRunner done~");
    }
}
