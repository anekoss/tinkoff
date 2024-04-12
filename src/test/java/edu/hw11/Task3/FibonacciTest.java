package edu.hw11.Task3;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.objectweb.asm.Opcodes;
import static edu.hw11.Task3.Fibonacci.fibonacci;
import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciTest {
    Class<?> getFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", int.class, Opcodes.ACC_PUBLIC)
            .withParameter(int.class)
            .intercept(new Implementation.Simple(new FibonacciByteCodeAppenderImpl()))
            .make().load(getClass().getClassLoader()).getLoaded();
    }

    @Test
    void classNameTest() {
        assertThat(getFibonacciClass().getSimpleName()).isEqualTo("Fibonacci");
    }

    @ParameterizedTest
    @CsvSource({"-100", "1", "0", "10", "5", "2", "1", "13"})
    void fibTest(int input) throws NoSuchMethodException, InvocationTargetException, InstantiationException,
        IllegalAccessException {
        Class<?> myClass = getFibonacciClass();
        Object myClassObject = myClass.getDeclaredConstructor().newInstance();
        int actualAnswer = (int) myClass.getMethod("fib", int.class).invoke(myClassObject, input);
        assertThat(actualAnswer).isEqualTo(fibonacci(input));
    }

    public static class FibonacciByteCodeAppenderImpl implements ByteCodeAppender {
        @Override
        public @NotNull Size apply(
            MethodVisitor methodVisitor,
            Implementation.@NotNull Context context,
            MethodDescription methodDescription
        ) {
            Label ifNe = new Label();
            Label ifGe = new Label();
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitJumpInsn(Opcodes.IFGE, ifGe);
            methodVisitor.visitInsn(Opcodes.ICONST_0);
            methodVisitor.visitInsn(Opcodes.IRETURN);
            methodVisitor.visitLabel(ifGe);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, ifNe);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.IRETURN);
            methodVisitor.visitLabel(ifNe);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                methodDescription.getDeclaringType().getActualName(),
                methodDescription.getName(),
                methodDescription.getDescriptor(),
                false
            );
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                methodDescription.getDeclaringType().getActualName(),
                methodDescription.getName(),
                methodDescription.getDescriptor(),
                false
            );
            methodVisitor.visitInsn(Opcodes.IADD);
            methodVisitor.visitInsn(Opcodes.IRETURN);
            methodVisitor.visitEnd();
            return new Size(4, 4);
        }
    }
}
