package domain.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CodeCallTest {
    @Test
    internal fun shouldBuildFullName() {
        val fullName = CodeCall(Package = "package", NodeName = "nodeName").buildClassFullName()
        assertEquals(fullName, "package.nodeName")
    }

    @Test
    internal fun shouldBuildFullMethodName_WhenIsConstructor() {
        val fullMethodName =
            CodeCall(Package = "package", NodeName = "nodeName", MethodName = "").buildFullMethodName()

        assertEquals(fullMethodName, "package.nodeName")
    }

    @Test
    internal fun shouldBuildFullMethodName_WhenIsNormalMethod() {
        val fullMethodName =
            CodeCall(Package = "package", NodeName = "nodeName", MethodName = "method").buildFullMethodName()

        assertEquals(fullMethodName, "package.nodeName.method")
    }

    @Test
    internal fun shouldHandleSystemOutput() {
        val isSystemOutput = CodeCall(NodeName = "System.out", MethodName = "println").isSystemOutput()

        assertEquals(isSystemOutput, true)
    }

    @Test
    internal fun shouldHandleSleep() {
        val isSleep = CodeCall(NodeName = "Thread", MethodName = "sleep").isThreadSleep()

        assertEquals(isSleep, true)
    }

    @Test
    internal fun shouldHandleHasAssertion() {
        assertEquals(CodeCall(MethodName = "assertEquals").hasAssertion(), true)
        assertEquals(CodeCall(MethodName = "should").hasAssertion(), true)
    }
}
