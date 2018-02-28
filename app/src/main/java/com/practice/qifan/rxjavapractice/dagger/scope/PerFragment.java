package com.practice.qifan.rxjavapractice.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by qifan on 2018/2/28.
 */

@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}