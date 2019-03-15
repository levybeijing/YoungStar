package com.github.hiteshsondhi88.libffmpeg;

import com.github.hiteshsondhi88.libffmpeg.utils.AssertionHelper;

/**
 * Trying Logging everything I can Log.java class should never throw any error
 */
public class LogTest extends CommonTestCase {

    private Object[] OBJECTS_TO_TEST = {null, "", "test string", 1, 1.00123, 2.45225747946181e-072};
    private Throwable[] EXCEPTIONS_TO_TEST = {new Exception(), new Exception("Test exception"), new Exception("")};

    public void testD() {
        printLog(new PrintLogInterface() {
            @Override
            public void print(Object obj) {
                Log.d(obj);
            }

            @Override
            public void print(Throwable throwable) {
                Log.d(throwable);
            }
        });
    }

    public void testW() {
        printLog(new PrintLogInterface() {
            @Override
            public void print(Object obj) {
                Log.w(obj);
            }

            @Override
            public void print(Throwable throwable) {
                Log.w(throwable);
            }
        });
    }

    public void testI() {
        printLog(new PrintLogInterface() {
            @Override
            public void print(Object obj) {
                Log.i(obj);
            }

            @Override
            public void print(Throwable throwable) {
                Log.i(throwable);
            }
        });
    }

    public void testV() {
        printLog(new PrintLogInterface() {
            @Override
            public void print(Object obj) {
                Log.v(obj);
            }

            @Override
            public void print(Throwable throwable) {
                Log.v(throwable);
            }
        });
    }

    public void testE() {
        printLog(new PrintLogInterface() {
            @Override
            public void print(Object obj) {
                Log.e(obj);
            }

            @Override
            public void print(Throwable throwable) {
                Log.e(throwable);
            }
        });

        for (Object obj : OBJECTS_TO_TEST) {
            for (Throwable throwable : EXCEPTIONS_TO_TEST) {
                try {
                    Log.e(obj, throwable);
                } catch (Exception e) {
                    AssertionHelper.assertError("Logging failed for object " + obj + " and throwable " + throwable);
                }
            }
        }
    }

    private void printLog(PrintLogInterface printLogInterface) {
        for (Object obj : OBJECTS_TO_TEST) {
            try {
                printLogInterface.print(obj);
            } catch (Exception e) {
                AssertionHelper.assertError("Logging failed for object "+obj);
            }
        }
        for (Throwable throwable : EXCEPTIONS_TO_TEST) {
            try {
                printLogInterface.print(throwable);
            } catch (Exception e) {
                AssertionHelper.assertError("Logging failed for throwable "+throwable);
            }
        }
    }

    private interface PrintLogInterface {
        void print(Object obj) throws Exception;
        void print(Throwable throwable) throws Exception;
    }
}