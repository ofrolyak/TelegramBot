package com.tatko.api.audit.annotations;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target(value = METHOD)
public @interface InputValidateAnnotation {

}
