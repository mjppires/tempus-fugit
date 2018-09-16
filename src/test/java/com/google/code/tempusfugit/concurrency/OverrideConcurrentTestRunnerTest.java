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

import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import org.junit.AfterClass;

import static com.google.code.tempusfugit.concurrency.OverrideConcurrentTestRunnerTest.OVERRIDDEN_CONCURRENT_COUNT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Concurrent(count = OVERRIDDEN_CONCURRENT_COUNT)
public class OverrideConcurrentTestRunnerTest extends AbstractConcurrentTestRunnerTest {

    protected final static int OVERRIDDEN_CONCURRENT_COUNT = 4;

    @AfterClass
    public static void assertTestThreadsSpawned() {
        assertThat(THREADS.size(), is(OVERRIDDEN_CONCURRENT_COUNT));
    }

    @Override
    protected int getConcurrentCount() {
        return OVERRIDDEN_CONCURRENT_COUNT;
    }

}
