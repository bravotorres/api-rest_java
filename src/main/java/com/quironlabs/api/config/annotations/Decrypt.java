package com.quironlabs.api.config.annotations;


import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Retention(CLASS)
@Target({ PARAMETER, LOCAL_VARIABLE })
public @interface Decrypt {

}
