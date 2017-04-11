/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package optjava.jmh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

// tag::SORT_BENCHMARK[]
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class SortBenchmark {

    private static final int N = 1_000;
    private static final List<Integer> testData = new ArrayList<>();

    @Setup
    public static final void setup() {
        Random randomGenerator = new Random();
        for (int i = 0; i < N; i++) {
            testData.add(randomGenerator.nextInt(Integer.MAX_VALUE));
        }
        System.out.println("Setup Complete");
    }

    @Benchmark
    public List<Integer> classicSort() {
        List<Integer> copy = new ArrayList<Integer>(testData);
        Collections.sort(copy);
        return copy;
    }

    @Benchmark
    public List<Integer> standardSort() {
        return testData.stream().sorted().collect(Collectors.toList());
    }

    @Benchmark
    public List<Integer> parallelSort() {
        return testData.parallelStream().sorted().collect(Collectors.toList());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SortBenchmark.class.getSimpleName()).warmupIterations(100)
                .measurementIterations(5).forks(1)
                .jvmArgs("-server", "-Xms2048m", "-Xmx2048m")
                .addProfiler(GCProfiler.class)
                .addProfiler(StackProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
// end::SORT_BENCHMARK[]

//Benchmark                                                     Mode  Cnt      Score      Error   Units
//SortBenchmark.classicSort                                    thrpt   50  10854.506 ±   72.689   ops/s
//SortBenchmark.classicSort:·gc.alloc.rate                     thrpt   50     75.095 ±    0.503  MB/sec
//SortBenchmark.classicSort:·gc.alloc.rate.norm                thrpt   50   7256.046 ±    0.003    B/op
//SortBenchmark.classicSort:·gc.churn.PS_Eden_Space            thrpt   50     71.288 ±   11.913  MB/sec
//SortBenchmark.classicSort:·gc.churn.PS_Eden_Space.norm       thrpt   50   6894.411 ± 1163.874    B/op
//SortBenchmark.classicSort:·gc.churn.PS_Survivor_Space        thrpt   50      0.293 ±    0.249  MB/sec
//SortBenchmark.classicSort:·gc.churn.PS_Survivor_Space.norm   thrpt   50     28.288 ±   24.052    B/op
//SortBenchmark.classicSort:·gc.count                          thrpt   50     62.000             counts
//SortBenchmark.classicSort:·gc.time                           thrpt   50     38.000                 ms
//SortBenchmark.parallelSort                                   thrpt   50   6905.069 ±  142.185   ops/s
//SortBenchmark.parallelSort:·gc.alloc.rate                    thrpt   50    415.301 ±    8.555  MB/sec
//SortBenchmark.parallelSort:·gc.alloc.rate.norm               thrpt   50  63075.630 ±    5.622    B/op
//SortBenchmark.parallelSort:·gc.churn.PS_Eden_Space           thrpt   50    419.921 ±    9.139  MB/sec
//SortBenchmark.parallelSort:·gc.churn.PS_Eden_Space.norm      thrpt   50  63799.455 ±  913.279    B/op
//SortBenchmark.parallelSort:·gc.churn.PS_Survivor_Space       thrpt   50      0.313 ±    0.046  MB/sec
//SortBenchmark.parallelSort:·gc.churn.PS_Survivor_Space.norm  thrpt   50     47.589 ±    7.079    B/op
//SortBenchmark.parallelSort:·gc.count                         thrpt   50    673.000             counts
//SortBenchmark.parallelSort:·gc.time                          thrpt   50    293.000                 ms
//SortBenchmark.standardSort                                   thrpt   50   9773.986 ±  104.484   ops/s
//SortBenchmark.standardSort:·gc.alloc.rate                    thrpt   50    210.064 ±    2.255  MB/sec
//SortBenchmark.standardSort:·gc.alloc.rate.norm               thrpt   50  22541.269 ±   10.375    B/op
//SortBenchmark.standardSort:·gc.churn.PS_Eden_Space           thrpt   50    211.112 ±    9.432  MB/sec
//SortBenchmark.standardSort:·gc.churn.PS_Eden_Space.norm      thrpt   50  22657.022 ± 1005.949    B/op
//SortBenchmark.standardSort:·gc.churn.PS_Survivor_Space       thrpt   50      0.130 ±    0.023  MB/sec
//SortBenchmark.standardSort:·gc.churn.PS_Survivor_Space.norm  thrpt   50     13.963 ±    2.487    B/op
//SortBenchmark.standardSort:·gc.count                         thrpt   50    252.000             counts
//SortBenchmark.standardSort:·gc.time                          thrpt   50    128.000                 ms
