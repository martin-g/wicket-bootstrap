package de.agilecoders.wicket.sass;

import java.util.concurrent.CountDownLatch;

import org.apache.wicket.Application;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssPackageResource;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class SassCacheManagerConcurrentTest
{
    public static final int MAX_RETRIES = 200;
    private WicketTester tester;

    @BeforeEach
    public void before()
    {
        tester = new WicketTester(new TestApplication());
    }

    @AfterEach
    public void after()
    {
        tester.destroy();
    }

    @Test
    public void concurrentModificationIssueInImportedSources() throws Exception
    {
        SassResourceReference sassResRef = new SassResourceReference(getClass(),
                "resources/concurrent.scss");

        Class<?> scope = sassResRef.getResource().getScope();
        String name = sassResRef.getName();
        CssPackageResource cpr = new CssPackageResource(scope, name, null, null, null);
        IResourceStream resourceStream = cpr.getResourceStream();

        for (int retries = 0; retries < MAX_RETRIES; retries++)
        {
            SassCacheManager.get().clearCache();

            CountDownLatch latch = new CountDownLatch(1);
            int internalThreadCount = 2;
            Thread[] threads = new Thread[internalThreadCount];
            TestRunnable[] runnables = new TestRunnable[internalThreadCount];

            for (int i = 0; i < internalThreadCount; i++)
            {
                TestRunnable runner = new TestRunnable(tester.getApplication(), latch,
                        resourceStream, scope.getName());
                runnables[i] = runner;

                threads[i] = new Thread(runner, "LessTest_" + i + "_" + retries);
                threads[i].start();
            }

            // Release the waiting threads
            latch.countDown();

            boolean failed = false;
            for (int i = 0; i < internalThreadCount; i++)
            {
                threads[i].join();

                failed = failed || runnables[i].hasFailed();
            }

            assertFalse(failed, "At least one thread reported error");
        }
    }

    private class TestRunnable implements Runnable
    {
        private WebApplication mApplication;
        private CountDownLatch mLatch;
        private boolean mHasFailed;
        private IResourceStream mResourceStream;
        private String mScopeName;

        public TestRunnable(WebApplication application, CountDownLatch latch,
                IResourceStream resourceStream, String scopeName)
        {
            mApplication = application;
            mLatch = latch;
            mResourceStream = resourceStream;
            mScopeName = scopeName;
            mHasFailed = false;
        }

        @Override
        public void run()
        {
            SassResourceStream sassResourceStream = null;
            try
            {
                if (!Application.exists())
                {
                    ThreadContext.setApplication(mApplication);
                }
                sassResourceStream = new SassResourceStream(mResourceStream, mScopeName);

                // Wait for the latch to be released and then compile the sass file
                mLatch.await();
                sassResourceStream.getString();
            }
            catch (Throwable e)
            {
                System.err.println("Thread failed:");
                e.printStackTrace();

                mHasFailed = true;
            }
            finally
            {
                IOUtils.closeQuietly(sassResourceStream);
            }
        }

        public boolean hasFailed()
        {
            return mHasFailed;
        }
    }
}
