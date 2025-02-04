/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.benchmark.time;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(3)
@Warmup(iterations = 10)
@Measurement(iterations = 20)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@SuppressWarnings("unused") // invoked by benchmarking framework
public class NanoTimeVsCurrentTimeMillisBenchmark {
    private volatile long var = 0;

    @Benchmark
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Benchmark
    public long nanoTime() {
        return System.nanoTime();
    }

    /*
    * this acts as upper bound of how time is cached in org.opensearch.threadpool.ThreadPool
    * */
    @Benchmark
    public long accessLongVar() {
        return var++;
    }
}
