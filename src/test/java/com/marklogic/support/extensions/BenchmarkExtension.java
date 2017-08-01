package com.marklogic.support.extensions;

/**
 * Tutorial and code for the JUnit 5 Benchmark Extension are from here:
 * <p>
 * https://github.com/CodeFX-org/demo-junit-5/blob/master/src/main/java/org/codefx/demo/junit5/BenchmarkExtension.java
 */

import com.marklogic.support.annotations.Benchmark;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;
import static java.util.Collections.singletonMap;
import static org.junit.platform.commons.support.AnnotationSupport.isAnnotated;

public class BenchmarkExtension
        implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {

    private static final Namespace NAMESPACE = Namespace.create("com", "marklogic", "support", "BenchmarkExtension");

    // EXTENSION POINTS

    private static boolean shouldBeBenchmarked(ExtensionContext context) {
        return context.getElement()
                .map(el -> isAnnotated(el, Benchmark.class))
                .orElse(false);
    }

    private static void storeNowAsLaunchTime(ExtensionContext context, LaunchTimeKey key) {
        context.getStore(NAMESPACE).put(key, currentTimeMillis());
    }

    private static long loadLaunchTime(ExtensionContext context, LaunchTimeKey key) {
        return context.getStore(NAMESPACE).get(key, long.class);
    }

    private static void report(String unit, ExtensionContext context, long elapsedTime) {
        String message = String.format("%s '%s' took %d ms (%d seconds)", unit, context.getDisplayName(), elapsedTime, TimeUnit.MILLISECONDS.toSeconds(elapsedTime));
        context.publishReportEntry(singletonMap("Benchmark", message));
    }

    // HELPER

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        storeNowAsLaunchTime(context, LaunchTimeKey.CLASS);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        storeNowAsLaunchTime(context, LaunchTimeKey.TEST);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        long launchTime = loadLaunchTime(context, LaunchTimeKey.TEST);
        long runtime = currentTimeMillis() - launchTime;
        report("Test", context, runtime);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        if (!shouldBeBenchmarked(context))
            return;

        long launchTime = loadLaunchTime(context, LaunchTimeKey.CLASS);
        long elapsedTime = currentTimeMillis() - launchTime;
        report("Test container", context, elapsedTime);
    }

    private enum LaunchTimeKey {
        CLASS, TEST
    }

}