package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.RgxGen.Companion.parse
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.results.format.ResultFormatType
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

open class PerformanceTests {
    @Disabled("ManualTest")
    @Test
    @Throws(Exception::class)
    fun launchBenchmark() {
        val opt = OptionsBuilder() // Specify which benchmarks to run.
            // You can be more specific if you'd like to run only one benchmark per test.
            .include(
                javaClass
                    .name + ".*"
            ) // Set the following options as needed
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.MICROSECONDS) // .warmupTime(TimeValue.seconds(1))
            .warmupIterations(3)
            .measurementIterations(3)
            .threads(1)
            .forks(1)
            .shouldFailOnError(true)
            .shouldDoGC(true) //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
            //.addProfiler(WinPerfAsmProfiler.class)
            .resultFormat(ResultFormatType.TEXT)
            .result("performance.txt")
            .build()

        Runner(opt).run()
    }

    @Benchmark
    fun generateTest() {
        val rgxGen = parse(
            "(([0-9a-f]{1,4}:){1,1}(:[0-9a-f]{1,4}){1,6})|(([0-9a-f]{1,4}:){1,2}(:[0-9a-f]{1,4}){1,5})|" +
                    "(([0-9a-f]{1,4}:){1,3}(:[0-9a-f]{1,4}){1,4})|(([0-9a-f]{1,4}:){1,4}(:[0-9a-f]{1,4}){1,3})|(([0-9a-f]{1,4}:){1,5}(:[0-9a-f]{1,4}){1,2})|" +
                    "(([0-9a-f]{1,4}:){1,6}(:[0-9a-f]{1,4}){1,1})|((([0-9a-f]{1,4}:){1,7}|:):)|(:(:[0-9a-f]{1,4}){1,7})|(((([0-9a-f]{1,4}:){6})(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)" +
                    "(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}))|((([0-9a-f]{1,4}:){5}[0-9a-f]{1,4}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}))|" +
                    "(([0-9a-f]{1,4}:){5}:[0-9a-f]{1,4}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,1}(:[0-9a-f]{1,4}){1,4}:" +
                    "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,2}(:[0-9a-f]{1,4}){1,3}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\." +
                    "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,3}(:[0-9a-f]{1,4}){1,2}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|" +
                    "(([0-9a-f]{1,4}:){1,4}(:[0-9a-f]{1,4}){1,1}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|((([0-9a-f]{1,4}:){1,5}|:):(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\." +
                    "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(:(:[0-9a-f]{1,4}){1,5}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})"
        )
        rgxGen.generate()
    }

    @Benchmark
    fun generateUniqueTest() {
        val stringIterator = RGXGEN.iterateUnique()
        var i = 0
        while (i < 100 && stringIterator!!.hasNext()) {
            stringIterator.next()
            i++
        }
    }

    companion object {
        private val RGXGEN = parse(
            "(([0-9a-f]{1,4}:){1,1}(:[0-9a-f]{1,4}){1,6})|(([0-9a-f]{1,4}:){1,2}(:[0-9a-f]{1,4}){1,5})|" +
                    "(([0-9a-f]{1,4}:){1,3}(:[0-9a-f]{1,4}){1,4})|(([0-9a-f]{1,4}:){1,4}(:[0-9a-f]{1,4}){1,3})|(([0-9a-f]{1,4}:){1,5}(:[0-9a-f]{1,4}){1,2})|" +
                    "(([0-9a-f]{1,4}:){1,6}(:[0-9a-f]{1,4}){1,1})|((([0-9a-f]{1,4}:){1,7}|:):)|(:(:[0-9a-f]{1,4}){1,7})|(((([0-9a-f]{1,4}:){6})(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)" +
                    "(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}))|((([0-9a-f]{1,4}:){5}[0-9a-f]{1,4}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}))|" +
                    "(([0-9a-f]{1,4}:){5}:[0-9a-f]{1,4}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,1}(:[0-9a-f]{1,4}){1,4}:" +
                    "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,2}(:[0-9a-f]{1,4}){1,3}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\." +
                    "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(([0-9a-f]{1,4}:){1,3}(:[0-9a-f]{1,4}){1,2}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|" +
                    "(([0-9a-f]{1,4}:){1,4}(:[0-9a-f]{1,4}){1,1}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|((([0-9a-f]{1,4}:){1,5}|:):(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\." +
                    "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})|(:(:[0-9a-f]{1,4}){1,5}:(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})"
        )
    }
}
