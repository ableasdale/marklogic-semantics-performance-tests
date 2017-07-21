package com.marklogic.support.annotations;

import com.marklogic.support.extensions.MarkLogicJavaApiExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by ableasdale on 29/05/2017.
 */
@Target({TYPE, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(MarkLogicJavaApiExtension.class)
public @interface MarkLogicJavaClient {
}
