package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.minutes(3))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();
        new Runner(options).run();
    }

    private Student student;
    private Method method;
    private MethodHandle concatMH;
    private Function<Student, String> nameGetter;

    @Setup
    public void setup() throws Exception {
        student = new Student("Alexander", "Biryukov");
        method = Student.class.getMethod("name");
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        MethodType mt = MethodType.methodType(String.class);
        concatMH = publicLookup.findVirtual(Student.class, "name", mt);
        nameGetter = getGetter(MethodHandles.lookup(), concatMH);
    }

    public Function getGetter(MethodHandles.Lookup lookup, MethodHandle getter) throws Exception {
        final CallSite site = LambdaMetafactory.metafactory(lookup, "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            getter,
            getter.type()
        );
        try {
            return (Function) site.getTarget().invokeExact();
        } catch (final Exception e) {
            throw e;
        } catch (final Throwable e) {
            throw new Error(e);
        }
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(method.invoke(student));
    }

    @Benchmark
    public void methodHandler(Blackhole bh) throws Throwable {
        bh.consume(concatMH.invoke(student));
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        bh.consume(nameGetter.apply(student));
    }
}
