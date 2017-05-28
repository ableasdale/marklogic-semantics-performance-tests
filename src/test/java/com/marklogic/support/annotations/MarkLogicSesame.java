package com.marklogic.support.annotations;

import com.marklogic.support.extensions.MarkLogicSesameExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by ableasdale on 28/05/2017.
 */
@Target({TYPE, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(MarkLogicSesameExtension.class)
public @interface MarkLogicSesame {
}
