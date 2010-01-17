/*
 * Copyright (c) 2009-2010, tempus-fugit committers
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

package com.google.code.tempusfugit.temporal;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.google.code.tempusfugit.temporal.Duration.millis;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ConcurrentTestRunner.class)
public class TimeoutTest {

    private final DeterministicDateFactory date = new DeterministicDateFactory();

    @Test
    public void timeoutExpires(){
        Timeout timeout = new Timeout(millis(5), StopWatch.start(date));

        date.setTime(millis(0));
        assertThat(timeout.hasExpired(), is(false));

        date.setTime(millis(5));
        assertThat(timeout.hasExpired(), is(false));

        date.setTime(millis(6));
        assertThat(timeout.hasExpired(), is(true));
    }

    @Test (expected = IllegalArgumentException.class)
    public void zeroTimeout(){
        new Timeout(millis(0), StopWatch.start(date));
    }

    @Test (expected = IllegalArgumentException.class)
    public void negativeTimeout(){
        new Timeout(millis(-1), StopWatch.start(date));
    }

}