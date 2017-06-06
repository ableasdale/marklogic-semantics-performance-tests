package com.marklogic.support.annotations;

import com.marklogic.support.extensions.MarkLogicReSTApiExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by ableasdale on 06/06/2017.
 */
@Target({TYPE, METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(MarkLogicReSTApiExtension.class)
public @interface MarkLogicReST {
}
