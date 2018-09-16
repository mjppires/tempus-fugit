/*
 * Copyright (c) 2009-2018, toby weston & tempus-fugit committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package com.google.code.tempusfugit.concurrency;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.lang.reflect.Method;

import static com.google.code.tempusfugit.ExceptionWrapper.wrapAsRuntimeException;

public class RepeatingRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return wrapAsRuntimeException(() -> {
            Class<?> test = description.getTestClass();
            Method method = test.getMethod(description.getMethodName());
            return new RunRepeatedly(new FrameworkMethod(method), base);
        });
    }
}
