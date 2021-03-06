package util;

import io.github.sskorol.model.DataSupplierMetaData;
import lombok.var;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IDataProvidable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;

import static io.github.sskorol.utils.ReflectionUtils.getDataSupplierAnnotation;
import static io.github.sskorol.utils.ReflectionUtils.getDataSupplierClass;
import static java.util.Objects.nonNull;

public class DataProviderTransformer implements IAnnotationTransformer {
    @DataProvider
    public Iterator<Object[]> supplySeqData(final ITestContext context, final ITestNGMethod testMethod) {
        return getMetaData(context, testMethod).getTestData().iterator();
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> supplyParallelData(final ITestContext context, final ITestNGMethod testMethod) {
        return getMetaData(context, testMethod).getTestData().iterator();
    }

    @Override
    public void transform(final ITestAnnotation annotation, final Class testClass,
                          final Constructor testConstructor, final Method testMethod) {
        assignCustomDataSupplier(annotation, testMethod, testClass);
    }

    @Override
    public void transform(final IFactoryAnnotation annotation, final Method testMethod) {
        assignCustomDataSupplier(annotation, testMethod, null);
    }

    private DataSupplierMetaData getMetaData(final ITestContext context, final ITestNGMethod testMethod) {
        return new DataSupplierMetaData(context, testMethod);
    }

    @SuppressWarnings("FinalLocalVariable")
    private <T> void assignCustomDataSupplier(final IDataProvidable annotation, final Method testMethod,
                                              final Class<T> testClass) {
        var dataSupplierClass = getDataSupplierClass(annotation, testClass, testMethod);
        var dataSupplierAnnotation = getDataSupplierAnnotation(dataSupplierClass, annotation.getDataProvider());

        if (!annotation.getDataProvider().isEmpty() && nonNull(dataSupplierAnnotation)) {
            annotation.setDataProviderClass(getClass());
            annotation.setDataProvider(dataSupplierAnnotation.runInParallel() ? "supplyParallelData" : "supplySeqData");
        }
    }
}
